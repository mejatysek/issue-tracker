package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.bo.*;
import cz.cvut.fel.wpa.tracker.dto.RoleDto;
import cz.cvut.fel.wpa.tracker.dto.UserDto;
import cz.cvut.fel.wpa.tracker.helper.DtoTransformerHelper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mejty on 16.11.14.
 */
@Component
public class UserServiceImpl extends AbstractDataAccessService implements UserService {


    @Override
    public Long addUser(String userName, String password, boolean state, Long role, String email) {
        return addUser(userName, password, state, role, email, null);
    }

    @Override
    public Long addUser(String userName, String password, boolean state, Long role, String email, List<Long> issues) {
        return addUser(userName, password, state, role, email, issues, null);
    }

    @Override
    public Long addUser(String userName, String password, boolean state, Long role, String email, List<Long> issues, List<Long> operations) {
        return addUser(userName, password, state, role, email, issues, operations, null);
    }

    @Override
    public Long addUser(String userName, String password, boolean state, Long role, String email, List<Long> issues, List<Long> operations, List<Long> customers) {
        User u = new User();
        u.setUserName(userName);
        u.setState(state);
        u.setEmail(email);
        u.setPassword(password);
        u.setRole(genericDao.getById(role, Role.class));
        List<Issue> issueList = new ArrayList<Issue>();
        if (issues != null) {
            for (Long issue : issues) {
                issueList.add(genericDao.getById(issue, Issue.class));
            }
        }
        u.setIssues(issueList);

        List<Operation> operationList = new ArrayList<Operation>();
        if (operations != null) {
            for (Long operation : operations) {
                operationList.add(genericDao.getById(operation, Operation.class));
            }
        }
        u.setOperations(operationList);

        List<Customer> customerList = new ArrayList<Customer>();
        if (customers != null) {
            for (Long customer : customers) {
                customerList.add(genericDao.getById(customer, Customer.class));
            }
        }
        u.setCustomers(customerList);

        return genericDao.saveOrUpdate(u).getId();
    }

    @Override
    public void deactivateUser(Long userId) {
        UserDto u = getUserById(userId);
        u.setState(false);
        editUser(u);
    }

    @Override
    public Long editUser(UserDto userDto) {
        User user = genericDao.getById(userDto.getId(), User.class);
        user.setUserName(userDto.getUserName());
        user.setState(userDto.getState());
        user.setEmail(userDto.getEmail());

        List<Issue> issueList = new ArrayList<Issue>();
        if (userDto.getIssues() != null) {
            for (Long issue : userDto.getIssues()) {
                issueList.add(genericDao.getById(issue, Issue.class));
            }
        }
        user.setIssues(issueList);

        List<Operation> operationList = new ArrayList<Operation>();
        if (userDto.getOperations() != null) {
            for (Long operation : userDto.getOperations()) {
                operationList.add(genericDao.getById(operation, Operation.class));
            }
        }
        user.setOperations(operationList);

        List<Customer> customerList = new ArrayList<Customer>();
        if (userDto.getCustomers() != null) {
            for (Long customer : userDto.getCustomers()) {
                customerList.add(genericDao.getById(customer, Customer.class));
            }
        }
        user.setCustomers(customerList);

        return genericDao.saveOrUpdate(user).getId();
    }

    @Override
    public UserDto getUserById(Long id) {
        User u = genericDao.getById(id, User.class);
        if (u != null) {
            return dtoFromBo(u);
        }
        return new UserDto();
    }

    @Override
    public List<UserDto> getIssueUsers(Long issueId) {
        List<UserDto> list = new ArrayList<UserDto>();
        List<User> relations = genericDao.getById(issueId, Issue.class).getWorkers();

        if (relations != null) {
            for (User u : relations) {
                list.add(dtoFromBo(u));
            }
        }
        return list;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> list = new ArrayList<UserDto>();
        List<User> users = genericDao.getAll(User.class);
        if (users != null) {
            for (User u : users) {
                list.add(dtoFromBo(u));
            }
        }
        return list;
    }

    @Override
    public List<UserDto> getUserByUsername(String username) {
        List<UserDto> list = new ArrayList<UserDto>();
        List<User> users = genericDao.getByProperty("userName", username, User.class);
        if (users != null) {
            for (User u : users) {
                list.add(dtoFromBo(u));
            }
        }
        return list;
    }

    @Override
    public List<UserDto> getUserByState(boolean state) {
        List<UserDto> list = new ArrayList<UserDto>();
        List<User> users = genericDao.getByProperty("state", state, User.class);
        if (users != null) {
            for (User u : users) {
                list.add(dtoFromBo(u));
            }
        }
        return list;
    }

    @Override
    public List<UserDto> getUserByRole(RoleDto role) {
        List<UserDto> list = new ArrayList<UserDto>();
        List<User> users = genericDao.getByProperty("role", role.getId(), User.class);
        if (users != null) {
            for (User u : users) {
                list.add(dtoFromBo(u));
            }
        }
        return list;
    }

    private UserDto dtoFromBo(User user) {
        return new UserDto(user.getId(), user.getUserName(), user.isState(), user.getRole().getId(), user.getEmail(), DtoTransformerHelper.getIdentifiers(user.getIssues()), DtoTransformerHelper.getIdentifiers(user.getOperations()), DtoTransformerHelper.getIdentifiers(user.getCustomers()));
    }

}

