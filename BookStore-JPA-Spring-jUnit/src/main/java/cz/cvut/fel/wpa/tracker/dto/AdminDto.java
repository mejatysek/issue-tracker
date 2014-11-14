package cz.cvut.fel.wpa.tracker.dto;

import java.util.List;

/**
 * Created by mejty on 14.11.14.
 */
public class AdminDto extends SalesmanDto{

    public AdminDto() {
        super();
    }

    public AdminDto(String userName, boolean state, String email, List<Long> issues, List<Long> operations, List<Long> customers) {
        super(userName, state, email, issues, operations, customers);
    }
}
