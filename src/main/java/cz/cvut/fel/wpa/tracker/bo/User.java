/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.bo;

import cz.cvut.fel.wpa.tracker.provider.SHA1Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity, which represents user of the system
 * @author mickapa1
 */
@Entity
@Table(name = "users") //user je SQL klicove slovo, nejde ho pouzit po pojmenovani tabulky
@Configurable(preConstruction=true)
public class User extends AbstractBusinessObject {

    @Column(nullable = false, unique = true)
    private String userName; //max 255 chars
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String salt;
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String password;
    private String email; //vse, co neni oanotovano a neni transient, je @Column
    private boolean state;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    @OrderBy("time desc")
    private List<Operation> operations;
    @ManyToMany
    private List<Issue> issues;
    @OneToMany
    private List<Customer> customers;
    @ManyToOne
    private Role role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.salt = SHA1Provider.computeHash(System.nanoTime() + "");
        this.password = SHA1Provider.computeHash(password + salt);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public void addOperation(Operation operation){
        if(this.operations == null){
            operations = new ArrayList<Operation>();
        }
        if(!this.operations.contains(operation)){
            operations.add(operation);
        }
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
        role.adduser(this);
    }
}
