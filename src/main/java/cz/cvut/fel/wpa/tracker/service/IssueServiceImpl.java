package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.bo.Issue;
import cz.cvut.fel.wpa.tracker.bo.Operation;
import cz.cvut.fel.wpa.tracker.bo.Relation;
import cz.cvut.fel.wpa.tracker.bo.User;
import cz.cvut.fel.wpa.tracker.dto.CustomerDto;
import cz.cvut.fel.wpa.tracker.dto.IssueDto;
import cz.cvut.fel.wpa.tracker.dto.UserDto;
import cz.cvut.fel.wpa.tracker.helper.DtoTransformerHelper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mejty on 9.12.14.
 */
@Component
public class IssueServiceImpl extends AbstractDataAccessService implements IssueService {

    @Override
    public List<IssueDto> getAllIssue() {
        List<IssueDto> issuesDtos = new ArrayList<IssueDto>();
        List<Issue> issues = genericDao.getAll(Issue.class);

        if (issues != null) {
            for (Issue issue : issues) {
                issuesDtos.add(dtoFromBo(issue));
            }
        }

        return issuesDtos;
    }

    @Override
    public List<IssueDto> getUserIssues(UserDto user) {
        return getUserLastIssues(user, 0);
    }

    @Override
    public List<IssueDto> getUserLastIssues(UserDto userId, int count) {
        List<IssueDto> issueDtos = new ArrayList<IssueDto>();
        List<Issue> list;

        if (count > 0) {
            list = genericDao.getByPropertyOrderedDesc("owner", userId, "time", Issue.class);
        }else{
            list = genericDao.getByProperty("owner", userId, Issue.class);
        }

        if (list != null) {
            int counter = 0;
            for (Issue issue : list) {
                if (count > 0 && counter >= count) break;
                issueDtos.add(dtoFromBo(issue));

                counter++;
            }
        }

        return issueDtos;
    }

    @Override
    public List<IssueDto> getProductIssues(Long productId) {
        List<IssueDto> issuesDtos = new ArrayList<IssueDto>();
        List<Issue> issues = genericDao.getByProperty("product", productId, Issue.class);
        if (issues != null) {
            for (Issue issue : issues) {
                issuesDtos.add(dtoFromBo(issue));
            }
        }
        return issuesDtos;
    }

    @Override
    public List<IssueDto> getCustomerIssues(CustomerDto customer) {
        List<IssueDto> issuesDtos = new ArrayList<IssueDto>();
        List<Long> products = customer.getProducts();
        List<Issue> issues = new ArrayList<Issue>();
        if (products != null) {
            for (Long product : products) {
                issues.addAll(genericDao.getByProperty("product", product, Issue.class));
            }
        }
        for (Issue issue : issues) {
            issuesDtos.add(dtoFromBo(issue));
        }
        return issuesDtos;
    }

    @Override
    public IssueDto getIssueById(Long id) {
        Issue issue = genericDao.getById(id, Issue.class);
        if (issue == null) {
            return new IssueDto();
        }
        return dtoFromBo(issue);
    }

    @Override
    public Long editIssue(IssueDto issue) {
        Issue i = new Issue();
        i.setId(issue.getId());
        i.setState(issue.getState());
        i.setTopic(issue.getTopic());
        List<User> usersList = new ArrayList<User>();
        if (issue.getWorkers() != null) {
            for (Long user : issue.getWorkers()) {
                usersList.add(genericDao.getById(user, User.class));
            }
        }
        i.setWorkers(usersList);
        List<Operation> operationList = new ArrayList<Operation>();
        if (issue.getOperations() != null) {
            for (Long operation : issue.getOperations()) {
                operationList.add(genericDao.getById(operation, Operation.class));
            }
        }
        i.setOperations(operationList);
        List<Relation> relationList = new ArrayList<Relation>();
        if (issue.getRelations() != null) {
            for (Long relation : issue.getRelations()) {
                relationList.add(genericDao.getById(relation, Relation.class));
            }
        }
        i.setRelations(relationList);
        return genericDao.saveOrUpdate(i).getId();
    }

    @Override
    public Long addIssue(String topic, short state, Long product, List<Long> workers) {
        return addIssue(topic, state, product, workers, null);
    }

    @Override
    public Long addIssue(String topic, short state, Long product, List<Long> workers, List<Long> operations) {
        return addIssue(topic, state, product, workers, operations, null);
    }

    @Override
    public Long addIssue(String topic, short state, Long product, List<Long> workers, List<Long> operations, List<Long> relations) {
        Issue issue = new Issue();
        issue.setTopic(topic);
        issue.setState(state);
        List<User> usersList = new ArrayList<User>();
        if (workers != null) {
            for (Long user : workers) {
                usersList.add(genericDao.getById(user, User.class));
            }
        }
        issue.setWorkers(usersList);
        List<Operation> operationList = new ArrayList<Operation>();
        if (operations != null) {
            for (Long operation : operations) {
                operationList.add(genericDao.getById(operation, Operation.class));
            }
        }
        issue.setOperations(operationList);
        List<Relation> relationList = new ArrayList<Relation>();
        if (relations != null) {
            for (Long relation : relations) {
                relationList.add(genericDao.getById(relation, Relation.class));
            }
        }
        issue.setRelations(relationList);
        return genericDao.saveOrUpdate(issue).getId();
    }

    private IssueDto dtoFromBo(Issue issue) {
        return new IssueDto(issue.getId(), issue.getTopic(), issue.getState(), issue.getProduct().getId(), DtoTransformerHelper.getIdentifiers(issue.getWorkers()), DtoTransformerHelper.getIdentifiers(issue.getOperations()), DtoTransformerHelper.getIdentifiers(issue.getRelations()));
    }
}
