/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.bo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Operation")
@NamedQuery(name="Operation.findAll", query="SELECT o FROM Operation o")
public class Operation extends AbstractBusinessObject {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP")
    private Date time;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Issue issue;

    private String body;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
        owner.addOperation(this);
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
        issue.addOperation(this);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
