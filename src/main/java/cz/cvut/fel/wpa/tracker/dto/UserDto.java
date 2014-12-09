/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.dto;

import java.util.List;


public class UserDto extends AbstractDto{
    private String userName;
    private boolean state;
    private String email;
    private List<Long> issues;
    private List<Long> operations;
    private List<Long> customers;
    private Long role;

    public UserDto() {
    }

    public UserDto(Long id, String userName, boolean state, Long role, String email, List<Long> issues, List<Long> operations) {
        this.id = id;
        this.userName = userName;
        this.state = state;
        this.email = email;
        this.issues = issues;
        this.operations = operations;
        this.role = role;
    }

    public UserDto(Long id, String userName, boolean state, Long role, String email, List<Long> issues, List<Long> operations, List<Long> customers) {
        this(id, userName, state, role, email, issues, operations);
        this.customers = customers;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean getState() {return this.state;}

    public void setState(boolean state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getIssues() {
        return issues;
    }

    public void setIssues(List<Long> issues) {
        this.issues = issues;
    }

    public List<Long> getOperations() {
        return operations;
    }

    public void setOperations(List<Long> operations) {
        this.operations = operations;
    }

    public List<Long> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Long> customers) {
        this.customers = customers;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

}
