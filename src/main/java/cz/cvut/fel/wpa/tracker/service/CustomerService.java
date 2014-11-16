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
     * @param salesmanId identifier of the salesman or admin
     * @return users customers
     */
    @Transactional(readOnly = true)
    public List<CustomerDto> getSalesmanCustomers(Long salesmanId);

    public Long addCustomer(String name, String email, String sla, Long salesman);

    public Long addCustomer(String name, String email, String sla, Long salesman, boolean state);

    public CustomerDto getCustomerById(Long customerId);

    public Long editCustomer(CustomerDto customer);

    /**
     * Deactivate customer in system
     *
     * @param customerId identifier of the customer to be deactivate
     */
    public void deactivateCustomer(Long customerId);
}
