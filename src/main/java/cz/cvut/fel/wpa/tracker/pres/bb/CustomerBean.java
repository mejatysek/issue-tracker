package cz.cvut.fel.wpa.tracker.pres.bb;

import cz.cvut.fel.wpa.tracker.dto.CustomerDto;
import cz.cvut.fel.wpa.tracker.dto.ProductDto;
import cz.cvut.fel.wpa.tracker.service.CustomerService;
import cz.cvut.fel.wpa.tracker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Author: Adam Uhlíř <uhlir.a@gmail.com>
 * Date: 13.12.14
 */

@Component("customerBean")
@Scope("request")
public class CustomerBean {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    private Long id;

    private String name;
    private String email;
    private String defaultSla;

    private List<ProductDto> products;

    private List<CustomerDto> customers;
    private boolean customersState;

    public String addCustomer(){
        //TODO: Přidat ID aktuálního uživatele
        if((id = customerService.addCustomer(name,email,defaultSla, 1l)) != null)
            return "success?faces-redirect=true&includeViewParams=true";
        else
            return "fail";
    }

    public List<CustomerDto> getCustomers(boolean showActive) {
        if (customers == null || customersState != showActive) {
            customersState = showActive;
            customers = customerService.getCustomersByState(showActive);
        }

        return customers;
    }

    public void deactivateCustomer(Long id) {
        customers = null;
        customerService.deactivateCustomer(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

        CustomerDto customerDto = customerService.getCustomerById(id);
        name = customerDto.getName();
        email = customerDto.getEmail();
        defaultSla = customerDto.getSla();
        products = productService.getCustomerProducts(id);
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

    public String getDefaultSla() {
        return defaultSla;
    }

    public void setDefaultSla(String defaultSla) {
        this.defaultSla = defaultSla;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}