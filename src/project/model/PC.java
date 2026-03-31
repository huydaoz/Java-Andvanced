package project.model;

public class PC {

    private int id;
    private String pcName;
    private int categoryId;
    private String config;
    private double pricePerHour;
    private String status;

    // ================= CONSTRUCTOR =================
    public PC() {
    }

    public PC(int id, String pcName, int categoryId, String config,
              double pricePerHour, String status) {
        this.id = id;
        this.pcName = pcName;
        this.categoryId = categoryId;
        this.config = config;
        this.pricePerHour = pricePerHour;
        this.status = status;
    }

    public PC(String pcName, int categoryId, String config, double pricePerHour) {
        this.pcName = pcName;
        this.categoryId = categoryId;
        this.config = config;
        this.pricePerHour = pricePerHour;
    }

    // ================= GETTER =================
    public int getId() {
        return id;
    }

    public String getPcName() {
        return pcName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getConfig() {
        return config;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public String getStatus() {
        return status;
    }

    // ================= SETTER =================
    public void setId(int id) {
        this.id = id;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}