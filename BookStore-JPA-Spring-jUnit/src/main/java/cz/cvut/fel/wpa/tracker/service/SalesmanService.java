/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.dto.SalesmanDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SalesmanService {

    public Long addSalesman(String userName, String password, boolean state, String email);

    public Long addSalesman(String userName, String password, boolean state, String email, List<Long> issues);

    public Long addSalesman(String userName, String password, boolean state, String email, List<Long> issues, List<Long> operations);

    public Long addSalesman(String userName, String password, boolean state, String email, List<Long> issues, List<Long> operations, List<Long> customers);

    public Long editSalesman(SalesmanDto user);

    @Transactional(readOnly = true)
    public SalesmanDto getSalesmanById(Long id);

    @Transactional(readOnly = true)
    public List<SalesmanDto> getAllSalesmans();

    @Transactional(readOnly = true)
    public List<SalesmanDto> getSalesmanByState(boolean state);

    @Transactional(readOnly = true)
    public List<SalesmanDto> getSalesmanByUsername(String username);

    public void deactivateSalesman(Long id);

}
