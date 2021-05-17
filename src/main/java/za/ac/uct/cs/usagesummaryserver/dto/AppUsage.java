package za.ac.uct.cs.usagesummaryserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppUsage {
    private String app;
    private String operator;
    private Long rx;
    private Long tx;

    @JsonProperty("app")
    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    @JsonProperty("operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @JsonProperty("rx")
    public Long getRx() {
        return rx;
    }

    public void setRx(Long rx) {
        this.rx = rx;
    }

    @JsonProperty("tx")
    public Long getTx() {
        return tx;
    }

    public void setTx(Long tx) {
        this.tx = tx;
    }
}
