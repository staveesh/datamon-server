package za.ac.uct.cs.usagesummaryserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Document("usage_data")
public class SummaryData {
    private String institution;
    private String deviceId;
    private Date startTime;
    private Date endTime;
    private List<AppUsage> wifiSummary;
    private List<AppUsage> mobileSummary;

    @JsonProperty("institution")
    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    @JsonProperty("deviceId")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @JsonProperty("startTime")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("endTime")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("wifiSummary")
    public List<AppUsage> getWifiSummary() {
        return wifiSummary;
    }

    public void setWifiSummary(List<AppUsage> wifiSummary) {
        this.wifiSummary = wifiSummary;
    }

    @JsonProperty("mobileSummary")
    public List<AppUsage> getMobileSummary() {
        return mobileSummary;
    }

    public void setMobileSummary(List<AppUsage> mobileSummary) {
        this.mobileSummary = mobileSummary;
    }
}
