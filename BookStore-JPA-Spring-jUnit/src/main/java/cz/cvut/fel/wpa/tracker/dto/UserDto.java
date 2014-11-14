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


    public UserDto() {
    }

    public UserDto(String userName, boolean state, String email, List<Long> issues, List<Long> operations) {
        this.userName = userName;
        this.state = state;
        this.email = email;
        this.issues = issues;
        this.operations = operations;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isState() {
        return state;
    }

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
}
