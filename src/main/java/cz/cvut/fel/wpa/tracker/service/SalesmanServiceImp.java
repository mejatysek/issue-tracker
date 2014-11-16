package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.bo.Customer;
import cz.cvut.fel.wpa.tracker.bo.Issue;
import cz.cvut.fel.wpa.tracker.bo.Operation;
import cz.cvut.fel.wpa.tracker.bo.Salesman;
import cz.cvut.fel.wpa.tracker.dto.SalesmanDto;
import cz.cvut.fel.wpa.tracker.helper.DtoTransformerHelper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mejty on 16.11.14.
 */
@Component
public class SalesmanServiceImp extends AbstractDataAccessService implements SalesmanService {

    protected SalesmanDto DtofromBo(Salesman user) {
        return new SalesmanDto(user.getId(), user.getUserName(), user.isState(), user.getEmail(), DtoTransformerHelper.getIdentifiers(user.getIssues()), DtoTransformerHelper.getIdentifiers(user.getOperations()), DtoTransformerHelper.getIdentifiers(user.getCustomers()));
    }

    @Override
    public Long addSalesman(String userName, String password, boolean state, String email) {
        return addSalesman(userName, password, state, email, null);
    }

    @Override
    public Long addSalesman(String userName, String password, boolean state, String email, List<Long> issues) {
        return addSalesman(userName, password, state, email, issues,null);
    }

    @Override
    public Long addSalesman(String userName, String password, boolean state, String email, List<Long> issues, List<Long> operations) {
        return addSalesman(userName, password, state, email, issues, operations,null);
    }

    @Override
    public Long addSalesman(String userName, String password, boolean state, String email, List<Long> issues, List<Long> operations, List<Long> customers) {
        Salesman salesman = new Salesman();
        salesman.setUserName(userName);
        salesman.setState(state);
        salesman.setEmail(email);
        salesman.setPassword(password);
        List<Issue> issues1 = new ArrayList<Issue>();
        if (issues != null) {
            for (Long issue : issues) {
                issues1.add(genericDao.getById(issue, Issue.class));
            }
        }
        salesman.setIssues(issues1);
        List<Operation> list = new ArrayList<Operation>();
        if (operations != null) {
            for (Long operation : operations) {
                list.add(genericDao.getById(operation, Operation.class));
            }
        }
        salesman.setOperations(list);
        List<Customer> list2 = new ArrayList<Customer>();
        if (customers != null) {
            for (Long customer : customers) {
                list2.add(genericDao.getById(customer, Customer.class));
            }
        }
        salesman.setCustomers(list2);
        return genericDao.saveOrUpdate(salesman).getId();
    }

    @Override
    public Long editSalesman(SalesmanDto user) {
        Salesman salesman = genericDao.getById(user.getId(),Salesman.class);
        salesman.setUserName(user.getUserName());
        salesman.setState(user.getState());
        salesman.setEmail(user.getEmail());
        List<Issue> issues1 = new ArrayList<Issue>();
        if (user.getIssues() != null) {
            for (Long issue : user.getIssues()) {
                issues1.add(genericDao.getById(issue, Issue.class));
            }
        }
        salesman.setIssues(issues1);
        List<Operation> list = new ArrayList<Operation>();
        if (user.getOperations() != null) {
            for (Long operation : user.getOperations()) {
                list.add(genericDao.getById(operation, Operation.class));
            }
        }
        salesman.setOperations(list);
        List<Customer> list2 = new ArrayList<Customer>();
        if (user.getCustomers() != null) {
            for (Long customer : user.getCustomers()) {
                list2.add(genericDao.getById(customer, Customer.class));
            }
        }
        salesman.setCustomers(list2);
        return genericDao.saveOrUpdate(salesman).getId();
    }

    @Override
    public SalesmanDto getSalesmanById(Long id) {
        Salesman u = genericDao.getById(id, Salesman.class);
        if (u != null) {
            return DtofromBo(u);
        }
        return new SalesmanDto();
    }

    @Override
    public List<SalesmanDto> getAllSalesmans() {
        List<SalesmanDto> list = new ArrayList<SalesmanDto>();
        List<Salesman> users = genericDao.getAll(Salesman.class);
        if (users != null) {
            for (Salesman u : users) {
                list.add(DtofromBo(u));
            }
        }
        return list;
    }

    @Override
    public List<SalesmanDto> getSalesmanByState(boolean state) {
        List<SalesmanDto> list = new ArrayList<SalesmanDto>();
        List<Salesman> users = genericDao.getByProperty("state",state,Salesman.class);
        if (users != null) {
            for (Salesman u : users) {
                list.add(DtofromBo(u));
            }
        }
        return list;
    }

    @Override
    public List<SalesmanDto> getSalesmanByUsername(String username) {
        List<SalesmanDto> list = new ArrayList<SalesmanDto>();
        List<Salesman> users = genericDao.getByProperty("userName",username,Salesman.class);
        if (users != null) {
            for (Salesman u : users) {
                list.add(DtofromBo(u));
            }
        }
        return list;
    }

    @Override
    public void deactivateSalesman(Long salesman) {
        SalesmanDto u = getSalesmanById(salesman);
        u.setState(false);
        editSalesman(u);
    }
}