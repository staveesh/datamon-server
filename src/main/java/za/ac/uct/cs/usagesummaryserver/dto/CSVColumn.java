package za.ac.uct.cs.usagesummaryserver.dto;

import java.util.Date;

public class CSVColumn {
    private String deviceId;
    private Date startTime;
    private Date endTime;
    private String app;
    private long downloadWifiBytes;
    private long uploadWifiBytes;
    private String operator1;
    private long downloadMobile1;
    private long uploadMobile1;
    private String operator2;
    private long downloadMobile2;
    private long uploadMobile2;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public long getDownloadWifiBytes() {
        return downloadWifiBytes;
    }

    public void setDownloadWifiBytes(long downloadWifiBytes) {
        this.downloadWifiBytes = downloadWifiBytes;
    }

    public long getUploadWifiBytes() {
        return uploadWifiBytes;
    }

    public void setUploadWifiBytes(long uploadWifiBytes) {
        this.uploadWifiBytes = uploadWifiBytes;
    }

    public String getOperator1() {
        return operator1;
    }

    public void setOperator1(String operator1) {
        this.operator1 = operator1;
    }

    public long getDownloadMobile1() {
        return downloadMobile1;
    }

    public void setDownloadMobile1(long downloadMobile1) {
        this.downloadMobile1 = downloadMobile1;
    }

    public long getUploadMobile1() {
        return uploadMobile1;
    }

    public void setUploadMobile1(long uploadMobile1) {
        this.uploadMobile1 = uploadMobile1;
    }

    public String getOperator2() {
        return operator2;
    }

    public void setOperator2(String operator2) {
        this.operator2 = operator2;
    }

    public long getDownloadMobile2() {
        return downloadMobile2;
    }

    public void setDownloadMobile2(long downloadMobile2) {
        this.downloadMobile2 = downloadMobile2;
    }

    public long getUploadMobile2() {
        return uploadMobile2;
    }

    public void setUploadMobile2(long uploadMobile2) {
        this.uploadMobile2 = uploadMobile2;
    }
}
