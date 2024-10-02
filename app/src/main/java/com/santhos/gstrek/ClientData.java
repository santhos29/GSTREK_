package com.santhos.gstrek;

public class ClientData {
    private String businessName;
    private String gstin;
    private String gstUsername;
    private String gstPassword;
    private String clientName;
    private String clientEmail;
    private String clientMobile;
    private String filingPrice;

    // Default constructor required for calls to DataSnapshot.getValue(ClientData.class)
    public ClientData() {}

    public ClientData(String businessName, String gstin, String gstUsername, String gstPassword,
                      String clientName, String clientEmail, String clientMobile, String filingPrice) {
        this.businessName = businessName;
        this.gstin = gstin;
        this.gstUsername = gstUsername;
        this.gstPassword = gstPassword;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientMobile = clientMobile;
        this.filingPrice = filingPrice;
    }

    // Getters and Setters
    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }
    public String getGstin() { return gstin; }
    public void setGstin(String gstin) { this.gstin = gstin; }
    public String getGstUsername() { return gstUsername; }
    public void setGstUsername(String gstUsername) { this.gstUsername = gstUsername; }
    public String getGstPassword() { return gstPassword; }
    public void setGstPassword(String gstPassword) { this.gstPassword = gstPassword; }
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    public String getClientEmail() { return clientEmail; }
    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }
    public String getClientMobile() { return clientMobile; }
    public void setClientMobile(String clientMobile) { this.clientMobile = clientMobile; }
    public String getFilingPrice() { return filingPrice; }
    public void setFilingPrice(String filingPrice) { this.filingPrice = filingPrice; }
}
