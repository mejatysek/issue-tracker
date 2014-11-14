/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.bo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Issue extends AbstractBusinessObject {
    @Column
    private String topic;

    @ManyToMany
    private List<User> workers;

    private short state;

    @OneToMany
    @OrderBy("time asc")
    private List<Operation> operations;

    @ManyToMany(mappedBy = "issues")
    private List<Relation> relations;

    public List<User> getWorkers() {
        return workers;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
//        workers.addBook(this);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public void addRelation(Relation relation){
        if(this.relations == null){
            relations = new ArrayList<Relation>();
        }
        if(!this.relations.contains(relation)){
            relations.add(relation);
        }
    }

    public void addOperation(Operation operation){
        if(this.operations == null){
            operations = new ArrayList<Operation>();
        }
        if(!this.operations.contains(operation)){
            operations.add(operation);
        }
    }

    public void addWorker(User worker){
        if(this.workers == null){
            workers = new ArrayList<User>();
        }
        if(!this.workers.contains(worker)){
            workers.add(worker);
        }
    }
}
