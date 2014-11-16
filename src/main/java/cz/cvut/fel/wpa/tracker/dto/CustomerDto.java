package cz.cvut.fel.wpa.tracker.dto;

import java.util.List;

/**
 * Created by mejty on 14.11.14.
 */
public class CustomerDto extends AbstractDto{
    private String name;
    private String email;
    private String sla;
    private boolean state;
    private List<Long> products;
    private Long salesman;

    public CustomerDto() {

    }

    public CustomerDto(Long id,String name, String email, String sla, boolean state, List<Long> products, Long salesman) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.sla = sla;
        this.state = state;
        this.products = products;
        this.salesman = salesman;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSla() {
        return sla;
    }

    public void setSla(String sla) {
        this.sla = sla;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }

    public Long getSalesman() {
        return salesman;
    }

    public void setSalesman(Long salesman) {
        this.salesman = salesman;
    }
}
