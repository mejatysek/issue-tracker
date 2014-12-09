package cz.cvut.fel.wpa.tracker.bo;

/**
 * Created by mejty on 9.12.14.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Role extends AbstractBusinessObject {

    private String name;

    @OneToMany
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void adduser(User user) {
        if (users == null) {
            users = new LinkedList<User>();
        }
        if (!users.contains(user)) {
            users.add(user);
        }
    }
}
