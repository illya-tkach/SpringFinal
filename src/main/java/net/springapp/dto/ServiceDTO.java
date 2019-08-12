package net.springapp.dto;

public class ServiceDTO {
    private int id;

    private String serviceName;

    private int serviceCost;

    public int getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getServiceCost() {
        return serviceCost;
    }

    public static ServiceDTO.Builder newBuilder() {
        return new ServiceDTO().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public ServiceDTO.Builder setId(int serviceId) {
            ServiceDTO.this.id = serviceId;

            return this;
        }

        public ServiceDTO.Builder setName(String serviceName) {
            ServiceDTO.this.serviceName = serviceName;

            return this;
        }

        public ServiceDTO.Builder setCost(int serviceCost) {
            ServiceDTO.this.serviceCost = serviceCost;

            return this;
        }

        public ServiceDTO build() {
            return ServiceDTO.this;
        }

    }
}
