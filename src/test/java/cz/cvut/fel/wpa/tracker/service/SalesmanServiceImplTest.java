package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.dto.SalesmanDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by mejty on 16.11.14.
 */
public class SalesmanServiceImplTest extends AbstractServiceTest {
    @Autowired
    private SalesmanService salesmanServiceService;

    @Test
    public void testAddAndRetrieveSalesman() {

        String SalesmanName = "SalesmanName";
        String passwd = "passwd";
        boolean state = true;
        String email = "abc@abd.cz";
        List<Long> customers = new ArrayList<Long>();

        Long id = salesmanServiceService.addSalesman(SalesmanName, passwd, state, email, null, null, customers);
        SalesmanDto SalesmanDto = salesmanServiceService.getSalesmanById(id);

        assertEquals(SalesmanName, SalesmanDto.getUserName());
        assertEquals(state, SalesmanDto.getState());
        assertEquals(email, SalesmanDto.getEmail());
        assertEquals(customers,SalesmanDto.getCustomers());
    }

    @Test
    public void testDeactivateSalesman() {

        String SalesmanName = "SalesmanName";
        String passwd = "passwd";
        boolean state = true;
        String email = "abc@abd.cz";
        List<Long> customers = new ArrayList<Long>();

        Long id = salesmanServiceService.addSalesman(SalesmanName, passwd, state, email, null, null, customers);
        SalesmanDto SalesmanDto = salesmanServiceService.getSalesmanById(id);
        assertEquals(1, salesmanServiceService.getAllSalesmans().size());
        assertEquals(1, salesmanServiceService.getSalesmanByState(true).size());
        assertEquals(0, salesmanServiceService.getSalesmanByState(false).size());
        salesmanServiceService.deactivateSalesman(id);
        assertEquals(1, salesmanServiceService.getAllSalesmans().size());
        assertEquals(0, salesmanServiceService.getSalesmanByState(true).size());
        assertEquals(1, salesmanServiceService.getSalesmanByState(false).size());
    }

    @Test
    public void testEditSalesman() {

        String SalesmanName = "SalesmanName";
        String passwd = "passwd";
        boolean state = true;
        String email = "abc@abd.cz";
        List<Long> customers = new ArrayList<Long>();

        Long id = salesmanServiceService.addSalesman(SalesmanName, passwd, state, email, null, null, customers);
        SalesmanDto SalesmanDto = salesmanServiceService.getSalesmanById(id);
        assertEquals(SalesmanName, SalesmanDto.getUserName());
        assertEquals(state, SalesmanDto.getState());
        assertEquals(email, SalesmanDto.getEmail());

        String changedEmail = "abder@bdc.cz";
        SalesmanDto.setEmail(changedEmail);
        salesmanServiceService.editSalesman(SalesmanDto);
        SalesmanDto = salesmanServiceService.getSalesmanById(id);

        assertEquals(SalesmanName, SalesmanDto.getUserName());
        assertEquals(state, SalesmanDto.getState());
        assertEquals(changedEmail, SalesmanDto.getEmail());
        assertEquals(1, salesmanServiceService.getAllSalesmans().size());

    }

    @Test

    public void getSalesmanByUsername(){
        String SalesmanName = "SalesmanName";
        String passwd = "passwd";
        boolean state = true;
        String email = "abc@abd.cz";
        List<Long> customers = new ArrayList<Long>();

        Long id = salesmanServiceService.addSalesman(SalesmanName, passwd, state, email, null, null, customers);
        SalesmanDto SalesmanDto = salesmanServiceService.getSalesmanById(id);
        assertEquals(1, salesmanServiceService.getSalesmanByUsername(SalesmanName).size());
        assertEquals(SalesmanName,SalesmanDto.getUserName());
        assertEquals(email,SalesmanDto.getEmail());
        assertEquals(customers,SalesmanDto.getCustomers());
        assertEquals(0, salesmanServiceService.getSalesmanByUsername("999999999").size());
    }
}
