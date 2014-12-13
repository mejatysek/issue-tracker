package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.bo.Customer;
import cz.cvut.fel.wpa.tracker.bo.Product;
import cz.cvut.fel.wpa.tracker.bo.User;
import cz.cvut.fel.wpa.tracker.dto.CustomerDto;
import cz.cvut.fel.wpa.tracker.helper.DtoTransformerHelper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Adam Uhlíř <uhlir.a@gmail.com>
 * Date: 10.12.14
 */
@Component
public class CustomerServiceImpl extends AbstractDataAccessService implements CustomerService {
    @Override
    public List<CustomerDto> getAllCustomer() {
        List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
        List<Customer> list = genericDao.getAll(Customer.class);

        if (list != null) {
            for (Customer issue : list) {
                customerDtos.add(dtoFromBo(issue));
            }
        }

        return customerDtos;
    }

    @Override
    public List<CustomerDto> getCustomersByState(boolean state) {
        List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
        List<Customer> list = genericDao.getByProperty("state", state, Customer.class);
        if (list != null) {
            for (Customer issue : list) {
                customerDtos.add(dtoFromBo(issue));
            }
        }
        return customerDtos;
    }

    @Override
    public List<CustomerDto> getUserCustomers(Long userId) {
        List<CustomerDto> issuesDtos = new ArrayList<CustomerDto>();
        List<Customer> issues = genericDao.getByProperty("user", userId, Customer.class);
        if (issues != null) {
            for (Customer issue : issues) {
                issuesDtos.add(dtoFromBo(issue));
            }
        }
        return issuesDtos;
    }

    @Override
    public Long addCustomer(String name, String email, String sla, Long user) {
        return addCustomer(name,email,sla,user, true);
    }

    @Override
    public Long addCustomer(String name, String email, String sla, Long user, boolean state) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setSla(sla);
        customer.setUser(genericDao.getById(user, User.class));
        customer.setState(state);
        
        return genericDao.saveOrUpdate(customer).getId();
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        Customer customer = genericDao.getById(customerId, Customer.class);
        if (customer == null) {
            return new CustomerDto();
        }
        return dtoFromBo(customer);
    }

    @Override
    public Long editCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setState(customerDto.isState());
        customer.setSla(customerDto.getSla());
        customer.setUser(genericDao.getById(customerDto.getUser(), User.class));
        
        List<Product> products = new ArrayList<Product>();
        if (customerDto.getProducts() != null) {
            for (Long product : customerDto.getProducts()) {
                products.add(genericDao.getById(product, Product.class));
            }
        }
        customer.setProducts(products);

        return genericDao.saveOrUpdate(customer).getId();
    }

    @Override
    public void deactivateCustomer(Long customerId) {
        CustomerDto customerDto = getCustomerById(customerId);
        customerDto.setState(false);
        editCustomer(customerDto);
    }

    private CustomerDto dtoFromBo(Customer issue) {
        return new CustomerDto(issue.getId(), issue.getName(), issue.getEmail(), issue.getSla(), issue.isState(), DtoTransformerHelper.getIdentifiers(issue.getProducts()), issue.getUser().getId());
    }
}
