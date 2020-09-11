package domain;

public class ProductRequest {
    private final Integer id;
    private final Double cost;
    private final String description;
    private final Integer qty;
    private final String transactionId;

    private ProductRequest(Builder builder){
    this.id = builder.id;
    this.cost=builder.cost;
    this.description=builder.description;
    this.qty=builder.qty;
    this.transactionId=builder.transactionId;
    }

    public Integer getId() {
        return id;
    }

    public Double getCost() {
        return cost;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQty() {
        return qty;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private Double cost;
        private String description;
        private Integer qty;
        private String transactionId;

        public Builder withRequestId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withCost(Double cost) {
            this.cost = cost;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withTransactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder withQty(Integer qty) {
            this.qty = qty;
            return this;
        }

        public ProductRequest build() {
            return new ProductRequest(this);
        }
    }

}
