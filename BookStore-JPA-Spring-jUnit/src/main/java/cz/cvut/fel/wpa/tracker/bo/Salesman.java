/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.bo;

import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Salesman")
@Configurable(preConstruction=true)
public class Salesman extends User {

    @OneToMany
    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer){
        if(this.customers == null){
            customers = new ArrayList<Customer>();
        }
        if(!this.customers.contains(customer)){
            customers.add(customer);
        }
    }

}
