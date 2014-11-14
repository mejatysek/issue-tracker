/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.bo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Customer extends AbstractBusinessObject {
    @Column(nullable = false)
    private String name;

    private String email;

    private String sla;

    private boolean state;


    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Product> products;

    @ManyToOne
    private Salesman salesman;

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

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
        salesman.addCustomer(this);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        if(this.products == null){
            products = new ArrayList<Product>();
        }
        if(!this.products.contains(product)){
            products.add(product);
        }
    }
}
