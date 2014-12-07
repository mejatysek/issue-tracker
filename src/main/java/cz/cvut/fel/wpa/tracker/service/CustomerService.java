/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.dto.CustomerDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface CustomerService {
    /**
     * Get all customers in the system
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomer();

    /**
     * Get all customers in given state from  the system
     *
     * @param state true for active customers false for inactive
     * @return
     */
    @Transactional(readOnly = true)
    public List<CustomerDto> getCustomersByState(boolean state);

    /**
     * Get all customers owned by the given user
     *
     * @param userId identifier of the salesman or admin
     * @return users customersg
     */
    @Transactional(readOnly = true)
    public List<CustomerDto> getUserCustomers(Long userId);

    public Long addCustomer(String name, String email, String sla, Long user);

    public Long addCustomer(String name, String email, String sla, Long user, boolean state);

    public CustomerDto getCustomerById(Long customerId);

    public Long editCustomer(CustomerDto customer);

    /**
     * Deactivate customer in system
     *
     * @param customerId identifier of the customer to be deactivate
     */
    public void deactivateCustomer(Long customerId);
}
