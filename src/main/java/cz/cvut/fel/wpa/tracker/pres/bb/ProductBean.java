package cz.cvut.fel.wpa.tracker.pres.bb;

import cz.cvut.fel.wpa.tracker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Author: Adam Uhlíř <uhlir.a@gmail.com>
 * Date: 13.12.14
 */

@Component("productBean")
@Scope("request")
public class ProductBean {

    @Autowired
    private ProductService productService;

    private Long id;
    private String name;
    private String sla;
    private Float price;
    private Long customerId;

    public String addProduct(){
        if((id = productService.addProduct(name,sla,price,true, customerId)) != null)
            return "success?faces-redirect=true&includeViewParams=true";
        else
            return "fail";
    }

    public void deactivateProduct(Long id){
        productService.deactivateProduct(id);
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
