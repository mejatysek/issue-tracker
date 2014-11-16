/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.dto.AdminDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AdminService {

    public Long addAdmin(String userName, boolean state, String email);

    public Long addAdmin(String userName, boolean state, String email, List<Long> issues);

    public Long addAdmin(String userName, boolean state, String email, List<Long> issues, List<Long> operations);

    public Long addAdmin(String userName, boolean state, String email, List<Long> issues, List<Long> operations, List<Long> customers);

    public Long editAdmin(AdminDto user);

    @Transactional(readOnly = true)
    public AdminDto getAdminById(Long id);

    @Transactional(readOnly = true)
    public List<AdminDto> getAllAdmins();

    @Transactional(readOnly = true)
    public List<AdminDto> getAdminByState(boolean state);

    @Transactional(readOnly = true)
    public List<AdminDto> getAdminByUsername(String username);

    public void deactivateAdmin(Long id);

}
