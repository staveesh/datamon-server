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
import za.ac.uct.cs.usagesummaryserver.config.WebSocketConfig;
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

    @RequestMapping(value = "/raffle", method = RequestMethod.GET)
    public ResponseEntity<?> raffle(){
        Optional<String> deviceId = WebSocketConfig.connections.values().stream().findAny();
        deviceId.ifPresent(s -> messagingTemplate.convertAndSendToUser(s, "/raffle/notification", "VICTORY"));
        if(deviceId.isPresent()) return ResponseEntity.ok().body(deviceId.get());
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void startExport(HttpServletResponse response){
        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=summary.csv";
        response.setHeader(headerKey, headerValue);

        ICsvBeanWriter csvWriter = null;

        try {
            csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
            String[] csvHeader = {"institution", "deviceId", "startTime", "endTime", "app", "downloadWifiBytes", "uploadWifiBytes",
                    "operator1", "downloadMobile1", "uploadMobile1", "operator2", "downloadMobile2", "uploadMobile2"};
            String[] nameMapping = {"institution", "deviceId", "startTime", "endTime", "app", "downloadWifiBytes", "uploadWifiBytes",
                    "operator1", "downloadMobile1", "uploadMobile1", "operator2", "downloadMobile2", "uploadMobile2"};

            List<String> packages = new ArrayList<String>(){{
                add("org.easyweb.browser");
                add("com.whatsapp");
                add("org.telegram.messenger");
                add("org.thoughtcrime.securesms");
                add("com.facebook.katana");
                add("com.facebook.lite");
                add("com.google.android.apps.meetings");
                add("com.microsoft.teams");
                add("us.zoom.videomeetings");
                add("us.zoom.pwa.twa");
                add("org.jitsi.meet");
                add("com.nweave.jitsiconference");
                add("com.vulapplication.vulapackage");
                add("com.android.chrome");
                add("org.mozilla.firefox");
                add("com.sec.android.app.sbrowser");
                add("com.google.android.youtube");
                add("com.google.android.gm");
                add("com.google.android.gm.lite");
                add("com.google.android.apps.searchlite");
                add("com.google.android.apps.docs.editors.docs");
                add("com.microsoft.office.lync15");
                add("com.skype.raider");
                add("com.linkedin.android");
                add("com.linkedin.android.lite");
                add("com.google.android.apps.tachyon");
                add("com.google.android.keep");
                add("com.twitter.android");
                add("com.twitter.android.lite");
                add("com.google.android.apps.docs");
                add("com.Slack");
                add("com.google.android.talk");
                add("com.google.android.calendar");
                add("com.ombiel.campusm.uct");
                add("com.blackboard.android.bbstudent");
                add("com.blackboard.android.bbinstructor");
                add("com.dropbox.android");
                add("com.microsoft.office.onenote");
                add("com.microsoft.skydrive");
                add("com.opera.browser");
                add("com.opera.mini.native");
                add("com.microsoft.office.outlook");
                add("com.udemy.android");
                add("com.udemydownload.udemy.udemydownloader");
                add("com.linkedin.android.learning");
                add("com.microsoft.office.officehubrow");
                add("com.microsoft.office.word");
                add("com.microsoft.office.excel");
                add("com.microsoft.office.powerpoint");
                add("com.paloaltonetworks.globalprotect");
                add("com.cisco.anyconnect.vpn.android.avf");
                add("com.saber.com.ikamvabeta");
                add("za.ac.uct.cs.videodatausageapp");
            }};
            csvWriter.writeHeader(csvHeader);

            List<SummaryData> data = dbManager.getAll();

            for (SummaryData summary : data) {
                String institution = summary.getInstitution();
                String deviceId = summary.getDeviceId();
                Date startTime = summary.getStartTime();
                Date endTime = summary.getEndTime();
                List<CSVColumn> columns = new ArrayList<>();
                Map<String, CSVColumn> columnMap = new HashMap<>();
                for(String pckg : packages){
                    CSVColumn col = new CSVColumn();
                    col.setInstitution(institution);
                    col.setDeviceId(deviceId);
                    col.setStartTime(startTime);
                    col.setEndTime(endTime);
                    col.setApp(pckg);
                    columns.add(col);
                    columnMap.put(pckg, col);
                }
                // Wifi
                for(AppUsage wifiSummary : summary.getWifiSummary()){
                    if(wifiSummary == null)
                        continue;
                    CSVColumn theColumn = columnMap.get(wifiSummary.getApp());
                    theColumn.setDownloadWifiBytes(wifiSummary.getRx());
                    theColumn.setUploadWifiBytes(wifiSummary.getTx());
                }
                List<String> operators = new ArrayList<>();
                // Mobile data
                for(AppUsage mobileSummary : summary.getMobileSummary()){
                    if(mobileSummary == null)
                        continue;
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
