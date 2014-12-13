package cz.cvut.fel.wpa.tracker.pres.bb;

import cz.cvut.fel.wpa.tracker.dto.CustomerDto;
import cz.cvut.fel.wpa.tracker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Author: Adam Uhlíř <uhlir.a@gmail.com>
 * Date: 13.12.14
 */

@Component("customerBean")
@ViewScoped
public class CustomerBean {

    @Autowired
    private CustomerService customerService;

    private Long id;

    private String name;
    private String email;
    private String defaultSla;

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
}
