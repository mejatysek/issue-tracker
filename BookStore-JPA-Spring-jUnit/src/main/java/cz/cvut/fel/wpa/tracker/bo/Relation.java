/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.bo;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Relation extends AbstractBusinessObject {
    private short type;

    @ManyToMany
    private List<Issue> issues;

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public boolean addIssue(Issue issue){
        if(this.issues != null) {
            if (this.issues.size() > 1) {
                return false;
            } else {
                if(!this.issues.contains(issue)) {
                    this.issues.add(issue);
                    issue.addRelation(this);
                    return true;
                }else {
                    return false;
                }

            }
        }else {
            this.issues = new ArrayList<Issue>();
            this.issues.add(issue);
            return true;
        }
    }
}
