package cz.cvut.fel.wpa.tracker.dto;

import java.util.List;

/**
 * Created by mejty on 14.11.14.
 */
public class IssueDto {
    private String topic;
    private short state;
    private List<Long> workers;
    private List<Long> operations;
    private List<Long> relations;

    public IssueDto() {
    }

    public IssueDto(String topic, short state, List<Long> workers, List<Long> operations, List<Long> relations) {
        this.topic = topic;
        this.state = state;
        this.workers = workers;
        this.operations = operations;
        this.relations = relations;
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

    public List<Long> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Long> workers) {
        this.workers = workers;
    }

    public List<Long> getOperations() {
        return operations;
    }

    public void setOperations(List<Long> operations) {
        this.operations = operations;
    }

    public List<Long> getRelations() {
        return relations;
    }

    public void setRelations(List<Long> relations) {
        this.relations = relations;
    }
}
