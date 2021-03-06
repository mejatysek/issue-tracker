package cz.cvut.fel.wpa.tracker.dto;

import java.util.List;

/**
 * Created by mejty on 14.11.14.
 */
public class IssueDto extends  AbstractDto{
    private String topic;
    private short state;
    private Long product;
    private List<Long> workers;
    private List<Long> operations;
    private List<Long> relations;

    public IssueDto() {
    }

    public IssueDto(Long id, String topic, short state, Long product, List<Long> workers, List<Long> operations, List<Long> relations) {
        this.id = id;
        this.topic = topic;
        this.state = state;
        this.product = product;
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

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }
}
