package za.ac.uct.cs.usagesummaryserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AppUsage {
    private String app;
    private String operator;
    private List<UsageBucket> rxBuckets;
    private List<UsageBucket> txBuckets;

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

    @JsonProperty("rxBuckets")
    public List<UsageBucket> getRxBuckets() {
        return rxBuckets;
    }

    public void setRxBuckets(List<UsageBucket> rxBuckets) {
        this.rxBuckets = rxBuckets;
    }

    @JsonProperty("txBuckets")
    public List<UsageBucket> getTxBuckets() {
        return txBuckets;
    }

    public void setTxBuckets(List<UsageBucket> txBuckets) {
        this.txBuckets = txBuckets;
    }
}
