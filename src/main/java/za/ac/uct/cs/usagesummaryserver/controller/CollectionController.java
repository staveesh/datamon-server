package za.ac.uct.cs.usagesummaryserver.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import za.ac.uct.cs.usagesummaryserver.db.DbManager;
import za.ac.uct.cs.usagesummaryserver.dto.AppUsage;
import za.ac.uct.cs.usagesummaryserver.dto.CSVColumn;
import za.ac.uct.cs.usagesummaryserver.dto.SummaryData;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
public class CollectionController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private DbManager dbManager;

    @RequestMapping(value = "/collect", method = RequestMethod.GET)
    public ResponseEntity<?> startCollection(@NonNull @RequestParam("start") long start, @NonNull @RequestParam("end") long end){
        JSONObject payload = new JSONObject();
        payload.put("start", start);
        payload.put("end", end);
        messagingTemplate.convertAndSend("/queue/control", payload.toString());
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void startExport(HttpServletResponse response){
        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.csv";
        response.setHeader(headerKey, headerValue);

        ICsvBeanWriter csvWriter = null;

        try {
            csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
            String[] csvHeader = {"User ID", "E-mail", "Full Name", "Roles", "Enabled"};
            String[] nameMapping = {"deviceId", "startTime", "endTime", "app", "downloadWifiBytes", "uploadWifiBytes",
                    "operator1", "downloadMobile1", "uploadMobile1", "operator2", "downloadMobile2", "uploadMobile2"};

            List<String> packages = new ArrayList<String>(){{
                add("com.microsoft.teams");
                add("com.whatsapp");
                add("com.google.android.apps.meetings");
                add("us.zoom.videomeetings");
            }};
            csvWriter.writeHeader(csvHeader);

            List<SummaryData> data = dbManager.getAll();

            for (SummaryData summary : data) {
                String deviceId = summary.getDeviceId();
                Date startTime = summary.getStartTime();
                Date endTime = summary.getEndTime();
                List<CSVColumn> columns = new ArrayList<>();
                Map<String, CSVColumn> columnMap = new HashMap<>();
                for(String pckg : packages){
                    CSVColumn col = new CSVColumn();
                    col.setDeviceId(deviceId);
                    col.setStartTime(startTime);
                    col.setEndTime(endTime);
                    col.setApp(pckg);
                    columns.add(col);
                    columnMap.put(pckg, col);
                }
                // Wifi
                for(AppUsage wifiSummary : summary.getWifiSummary()){
                    CSVColumn theColumn = columnMap.get(wifiSummary.getApp());
                    theColumn.setDownloadWifiBytes(wifiSummary.getRx());
                    theColumn.setUploadWifiBytes(wifiSummary.getTx());
                }
                List<String> operators = new ArrayList<>();
                // Mobile data
                for(AppUsage mobileSummary : summary.getMobileSummary()){
                    CSVColumn theColumn = columnMap.get(mobileSummary.getApp());
                    String theOperator = mobileSummary.getOperator();
                    if(!operators.contains(theOperator))
                        operators.add(theOperator);
                    if(operators.indexOf(theOperator) == 0) {
                        theColumn.setOperator1(theOperator);
                        theColumn.setDownloadMobile1(mobileSummary.getRx());
                        theColumn.setUploadMobile1(mobileSummary.getTx());
                    }
                    else if(operators.indexOf(theOperator) == 1) {
                        theColumn.setOperator2(theOperator);
                        theColumn.setDownloadMobile2(mobileSummary.getRx());
                        theColumn.setUploadMobile2(mobileSummary.getTx());
                    }
                }

                for (CSVColumn column: columns){
                    csvWriter.write(column, nameMapping);
                }
            }

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
