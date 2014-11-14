package cz.cvut.fel.wpa.tracker.dto;

import java.util.List;

/**
 * Created by mejty on 14.11.14.
 */
public class SalesmanDto extends UserDto {
    List<Long> customers;

    public SalesmanDto() {
        super();
    }

    public SalesmanDto(String userName, boolean state, String email, List<Long> issues, List<Long> operations, List<Long> customers) {
        super(userName, state, email, issues, operations);
        this.customers = customers;
    }

    public List<Long> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Long> customers) {
        this.customers = customers;
    }
}
