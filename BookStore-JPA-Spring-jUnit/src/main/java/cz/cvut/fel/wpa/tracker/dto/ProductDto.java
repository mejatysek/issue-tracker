package cz.cvut.fel.wpa.tracker.dto;

/**
 * Created by mejty on 14.11.14.
 */
public class ProductDto {
    private String name;
    private String sla;
    private float price;
    private boolean state;
    private Long customer;

    public ProductDto() {
    }

    public ProductDto(String name, String sla, float price, boolean state, Long customer) {
        this.name = name;
        this.sla = sla;
        this.price = price;
        this.state = state;
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSla() {
        return sla;
    }

    public void setSla(String sla) {
        this.sla = sla;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }
}
