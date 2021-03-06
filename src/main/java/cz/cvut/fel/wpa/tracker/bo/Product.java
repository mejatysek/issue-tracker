/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.bo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Product extends AbstractBusinessObject {
    @Column(nullable = false)
    private String Name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Issue> issues;

    private String sla;

    private float price;

    private boolean state;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.addProduct(this);
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

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public void addIssue(Issue issue){
        if(this.issues == null){
            issues = new ArrayList<Issue>();
        }
        if(!this.issues.contains(issue)){
            issues.add(issue);
        }
    }
}
