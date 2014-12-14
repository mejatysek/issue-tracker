package cz.cvut.fel.wpa.tracker.pres.bb;

import cz.cvut.fel.wpa.tracker.bo.Operation;
import cz.cvut.fel.wpa.tracker.bo.User;
import cz.cvut.fel.wpa.tracker.dto.IssueDto;
import cz.cvut.fel.wpa.tracker.dto.OperationDto;
import cz.cvut.fel.wpa.tracker.dto.RelationDto;
import cz.cvut.fel.wpa.tracker.dto.UserDto;
import cz.cvut.fel.wpa.tracker.service.IssueService;
import cz.cvut.fel.wpa.tracker.service.OperationService;
import cz.cvut.fel.wpa.tracker.service.RelationService;
import cz.cvut.fel.wpa.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Adam Uhlíř <uhlir.a@gmail.com>
 * Date: 13.12.14
 */
@Component("issueBean")
@Scope("request")
public class IssueBean {

    @Autowired
    private IssueService issueService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private RelationService relationService;

    @Autowired
    private UserService userService;

    private Long id;
    private Long detailId;

    private String topic;
    private Long productId;
    private String description;

    private short state;
    private List<OperationDto> operations;
    private List<UserDto> workers;
    private List<RelationDto> relations;

    public String addIssue(){
        // TODO: Definovat states
        short s = 1;
        id = issueService.addIssue(topic, s, productId, null);

        // TODO: Přiřadit aktuální uživatele k Operations
        operationService.addOperation(new Date(), 1l, id, description);

        return "/issue/detail.xhtml?faces-redirect=true&id=" + id;
    }

    public List<IssueDto> getIssues() {return issueService.getAllIssue();}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
        this.id = detailId;

        IssueDto issueDto = issueService.getIssueById(detailId);
        topic = issueDto.getTopic();
        productId = issueDto.getProduct();
        state = issueDto.getState();

        operations = operationService.getIssueOperations(id);
        relations = relationService.getIssueRelations(id);
        workers = userService.getIssueUsers(id);
    }

    public String getUserNameFromId(Long id){
        return userService.getUserById(id).getUserName();
    }

    public String getIssueNamesFromIds(List<Long> ids){
        StringBuilder builder = new StringBuilder();

        for(Long id : ids){
            builder.append(issueService.getIssueById(id).getTopic()).append(" ");
        }

        return builder.toString();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OperationDto> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationDto> operations) {
        this.operations = operations;
    }

    public List<UserDto> getWorkers() {
        return workers;
    }

    public void setWorkers(List<UserDto> workers) {
        this.workers = workers;
    }

    public List<RelationDto> getRelations() {
        return relations;
    }

    public void setRelations(List<RelationDto> relations) {
        this.relations = relations;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }
}
