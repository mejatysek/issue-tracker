package cz.cvut.fel.wpa.tracker.dto;

import java.util.List;

/**
 * Created by mejty on 14.11.14.
 */
public class ProductDto extends  AbstractDto{
    private String name;
    private String sla;
    private float price;
    private boolean state;
    private Long customer;
    private List<Long> issues;

    public ProductDto() {
    }

    public ProductDto(Long id,String name, String sla, float price, boolean state, Long customer, List<Long> issues) {
        this.id=id;
        this.name = name;
        this.sla = sla;
        this.price = price;
        this.state = state;
        this.customer = customer;
        this.issues = issues;
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
