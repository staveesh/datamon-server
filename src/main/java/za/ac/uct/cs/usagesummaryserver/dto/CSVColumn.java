package za.ac.uct.cs.usagesummaryserver.dto;

import java.util.Date;

public class CSVColumn {
    private String deviceId;
    private Date startTime;
    private Date endTime;
    private String app;
    private Double downloadWifi;
    private Double uploadWifi;
    private String operator1;
    private Double downloadMobile1;
    private Double uploadMobile1;
    private String operator2;
    private Double downloadMobile2;
    private Double uploadMobile2;

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

    public Double getDownloadWifi() {
        return downloadWifi;
    }

    public void setDownloadWifi(Double downloadWifi) {
        this.downloadWifi = downloadWifi;
    }

    public Double getUploadWifi() {
        return uploadWifi;
    }

    public void setUploadWifi(Double uploadWifi) {
        this.uploadWifi = uploadWifi;
    }

    public String getOperator1() {
        return operator1;
    }

    public void setOperator1(String operator1) {
        this.operator1 = operator1;
    }

    public Double getDownloadMobile1() {
        return downloadMobile1;
    }

    public void setDownloadMobile1(Double downloadMobile1) {
        this.downloadMobile1 = downloadMobile1;
    }

    public Double getUploadMobile1() {
        return uploadMobile1;
    }

    public void setUploadMobile1(Double uploadMobile1) {
        this.uploadMobile1 = uploadMobile1;
    }

    public String getOperator2() {
        return operator2;
    }

    public void setOperator2(String operator2) {
        this.operator2 = operator2;
    }

    public Double getDownloadMobile2() {
        return downloadMobile2;
    }

    public void setDownloadMobile2(Double downloadMobile2) {
        this.downloadMobile2 = downloadMobile2;
    }

    public Double getUploadMobile2() {
        return uploadMobile2;
    }

    public void setUploadMobile2(Double uploadMobile2) {
        this.uploadMobile2 = uploadMobile2;
    }
}
