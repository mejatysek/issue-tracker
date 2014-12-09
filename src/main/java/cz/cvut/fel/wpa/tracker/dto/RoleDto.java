package cz.cvut.fel.wpa.tracker.dto;

import java.util.List;

/**
 * Created by mejty on 9.12.14.
 */
public class RoleDto extends AbstractDto {
    private String name;
    private List<Long> users;

    public RoleDto() {
    }

    public RoleDto(Long id, String name, List<Long> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }
}

