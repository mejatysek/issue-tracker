package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.bo.Issue;
import cz.cvut.fel.wpa.tracker.bo.Operation;
import cz.cvut.fel.wpa.tracker.bo.User;
import cz.cvut.fel.wpa.tracker.dto.UserDto;
import cz.cvut.fel.wpa.tracker.helper.DtoTransformerHelper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mejty on 16.11.14.
 */
@Component
public class UserServiceImp extends AbstractDataAccessService implements UserService {


    @Override
    public Long addUser(String userName, String password, boolean state, String email) {
        return addUser(userName, password, state, email, null);
    }

    @Override
    public Long addUser(String userName, String password, boolean state, String email, List<Long> issues) {
        return addUser(userName, password, state, email, issues, null);
    }

    @Override
    public Long addUser(String userName, String password, boolean state, String email, List<Long> issues, List<Long> operations) {
        User u = new User();
        u.setUserName(userName);
        u.setState(state);
        u.setEmail(email);
        u.setPassword(password);
        List<Issue> issues1 = new ArrayList<Issue>();
        if (issues != null) {
            for (Long issue : issues) {
                issues1.add(genericDao.getById(issue, Issue.class));
            }
        }
        u.setIssues(issues1);
        List<Operation> list = new ArrayList<Operation>();
        if (issues != null) {
            for (Long operation : operations) {
                list.add(genericDao.getById(operation, Operation.class));
            }
        }
        u.setOperations(list);
        return genericDao.saveOrUpdate(u).getId();
    }

    @Override
    public void deactivateUser(Long userId) {
        UserDto u = getUserById(userId);
        u.setState(false);
        editUser(u);
    }

    @Override
    public Long editUser(UserDto user) {
        User user1 = genericDao.getById(user.getId(), User.class);
        user1.setUserName(user.getUserName());
        user1.setState(user.getState());
        user1.setEmail(user.getEmail());
        List<Issue> list = new ArrayList<Issue>();
        if (user.getIssues() != null) {
            for (Long issue : user.getIssues()) {
                list.add(genericDao.getById(issue, Issue.class));
            }
        }
        user1.setIssues(list);

        List<Operation> a = new ArrayList<Operation>();
        if (user.getOperations() != null) {
            for (Long operation : user.getOperations()) {
                a.add(genericDao.getById(operation, Operation.class));
            }
        }
        user1.setOperations(a);
        return genericDao.saveOrUpdate(user1).getId();
    }

    @Override
    public UserDto getUserById(Long id) {
        User u = genericDao.getById(id, User.class);
        if (u != null) {
            return DtofromBo(u);
        }
        return new UserDto();
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> list = new ArrayList<UserDto>();
        List<User> users = genericDao.getAll(User.class);
        if (users != null) {
            for (User u : users) {
                list.add(DtofromBo(u));
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
                list.add(DtofromBo(u));
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
                list.add(DtofromBo(u));
            }
        }
        return list;
    }

    private UserDto DtofromBo(User user) {
        return new UserDto(user.getId(), user.getUserName(), user.isState(), user.getEmail(), DtoTransformerHelper.getIdentifiers(user.getIssues()), DtoTransformerHelper.getIdentifiers(user.getOperations()));
    }

}

