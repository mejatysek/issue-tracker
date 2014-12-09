package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.bo.Role;
import cz.cvut.fel.wpa.tracker.bo.User;
import cz.cvut.fel.wpa.tracker.dto.RoleDto;
import cz.cvut.fel.wpa.tracker.helper.DtoTransformerHelper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mejty on 9.12.14.
 */
@Component
public class RoleServiceImpl extends AbstractDataAccessService implements RoleService {

    @Override
    public Long addRole(String name) {
        return addRole(name, null);
    }

    @Override
    public Long addRole(String name, List<Long> users) {
        Role r = new Role();
        r.setName(name);
        List<User> usersList = new ArrayList<User>();
        if (users != null) {
            for (Long user : users) {
                usersList.add(genericDao.getById(user, User.class));
            }
        }
        return genericDao.saveOrUpdate(r).getId();
    }

    @Override
    public Long editRole(RoleDto role) {
        Role r = genericDao.getById(role.getId(), Role.class);
        r.setName(role.getName());
        List<User> userList = new ArrayList<User>();
        if (role.getUsers() != null) {
            for (Long user : role.getUsers()) {
                userList.add(genericDao.getById(user, User.class));
            }
        }
        r.setUsers(userList);
        return genericDao.saveOrUpdate(r).getId();
    }

    @Override
    public RoleDto getRoleById(Long id) {
        Role r = genericDao.getById(id, Role.class);
        if (r != null) {
            return dtoFromBo(r);
        }
        return new RoleDto();
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<RoleDto> list = new ArrayList<RoleDto>();
        List<Role> roles = genericDao.getAll(Role.class);
        if (roles != null) {
            for (Role r : roles) {
                list.add(dtoFromBo(r));
            }
        }
        return list;
    }

    @Override
    public List<RoleDto> getRoleByName(String name) {
        List<RoleDto> list = new ArrayList<RoleDto>();
        List<Role> roles = genericDao.getByProperty("name", name, Role.class);
        if (roles != null) {
            for (Role r : roles) {
                list.add(dtoFromBo(r));
            }
        }
        return list;
    }


    private RoleDto dtoFromBo(Role r) {
        return new RoleDto(r.getId(), r.getName(), DtoTransformerHelper.getIdentifiers(r.getUsers()));
    }

}


