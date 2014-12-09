package cz.cvut.fel.wpa.tracker.service;

/**
 * Created by mejty on 9.12.14.
 */

import cz.cvut.fel.wpa.tracker.dto.RoleDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RoleService {

    public Long addRole(String name);

    public Long addRole(String name, List<Long> users);

    public Long editRole(RoleDto role);

    @Transactional(readOnly = true)
    public RoleDto getRoleById(Long id);

    @Transactional(readOnly = true)
    public List<RoleDto> getAllRoles();

    @Transactional(readOnly = true)
    public List<RoleDto> getRoleByName(String name);

}
