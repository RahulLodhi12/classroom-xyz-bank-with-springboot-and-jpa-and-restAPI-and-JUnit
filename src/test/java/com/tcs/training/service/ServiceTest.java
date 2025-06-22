package com.tcs.training.service;

import com.tcs.training.bean.Customer;
import com.tcs.training.bean.Transaction;
import com.tcs.training.repository.CustomerRepo;
import com.tcs.training.repository.TransactionRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private CustomerRepo customerRepo;

    @Mock
    private TransactionRepo transactionsRepo;

    @InjectMocks
    private ServiceClass serviceClass;

    @Test
    void testGetAllCustomer_Success(){
        List<Customer> mockCustomers = new ArrayList<>(Arrays.asList(new Customer(11,"rahul lodhi","delhi",3500.89),new Customer(12,"mohit poonia","jaipur",4500.00)));

        when(customerRepo.findAll()).thenReturn(mockCustomers);

        List<Customer> customerList = serviceClass.getAllCustomer(); //first call to the actual getAllCustomer(), but internally calls to the customerRepo.findAll()

        assertEquals("rahul lodhi",customerList.get(0).getName());
        verify(customerRepo).findAll();
    }

    
    @Test
    void testGetAllCustomer_Failure(){
//        List<Customer> mockCustomers = new ArrayList<>(Arrays.asList(new Customer(11,"rahul lodhi","delhi",3500.89),new Customer(12,"mohit poonia","jaipur",4500.00)));

        when(customerRepo.findAll()).thenReturn(Collections.emptyList());

        List<Customer> customerList = serviceClass.getAllCustomer(); //first call to the actual getAllCustomer(), but internally calls to the customerRepo.findAll()

//        assertEquals("rahul",customerList.get(0).getCustomerName());
        assertTrue(customerList.isEmpty());
        verify(customerRepo).findAll();
    }

	
    @Test
    void testGetCustomerByAccNumber_Success(){
        Customer mockCustomer = new Customer(11,"rahul poonia","jodhpur",8990.00); //auto generate the account number

        when(customerRepo.findById(mockCustomer.getAccNo())).thenReturn(Optional.of(mockCustomer));

        Optional<Customer> customerByAccNumber = serviceClass.getCustomerByAccountNumber(mockCustomer.getAccNo());

        System.out.println(mockCustomer.getAccNo());
        System.out.println(customerByAccNumber.get().getAccNo());

        assertEquals(mockCustomer.getAccNo(),customerByAccNumber.get().getAccNo());
    }

	
    @Test
    void testGetCustomerByAccNumber_Failure(){
//        Customer mockCustomer = new Customer("11","rahul poonia","jodhpur",8990.00); //auto generate the account number

        when(customerRepo.findById(1189)).thenReturn(Optional.empty());

        Optional<Customer> customerByAccNumber = serviceClass.getCustomerByAccountNumber(1189);

//        System.out.println(mockCustomer.getAccountNumber());
//        System.out.println(customerByAccNumber.get().getAccountNumber());

//        assertEquals("1189",customerByAccNumber.get().getAccountNumber());
        assertTrue(customerByAccNumber.isEmpty());
    }

    
    @Test
    void testCreateAccount_Success(){
        Customer mockCustomer = new Customer(11,"rajesh","jodhpur",3456.89);

        when(customerRepo.save(any(Customer.class))).thenReturn(mockCustomer);

        assertTrue(serviceClass.createAccount(11,"rajesh","jodhpur",3456.89));

        verify(customerRepo).save(any(Customer.class));
    }

    
    @Test
    void testCreateAccount_Failure(){
        assertFalse(serviceClass.createAccount(11,"","jodhpur",3456.89));
    }

    
    @Test
    void testUpdateAccountByAccountNumber_Success(){
        Customer existing = new Customer(11,"rahul","hindon",4500.00);
        Customer updateCustomer = new Customer(11,"newName","newBranch",8894.00);

        when(customerRepo.findById(existing.getAccNo())).thenReturn(Optional.of(existing));
        when(customerRepo.save(updateCustomer)).thenReturn(updateCustomer);

        assertTrue(serviceClass.updateCustomerByAccountNumber(existing.getAccNo(),updateCustomer));

        verify(customerRepo).findById(existing.getAccNo());
        verify(customerRepo).save(updateCustomer);
    }

    
    @Test
    void testUpdateAccountByAccountNumber_Failure(){
        Customer existing = new Customer(11,"rahul","hindon",4500.00);
        Customer updateCustomer = new Customer(11,"newName","newBranch",8894.00);

        //Simulating No Customer exist with Given Account Number
        when(customerRepo.findById(existing.getAccNo())).thenReturn(Optional.empty());

        assertFalse(serviceClass.updateCustomerByAccountNumber(existing.getAccNo(),updateCustomer));

        verify(customerRepo).findById(existing.getAccNo());
    }

    
    @Test
    void testDeleteAccountByAccountNumber_Success(){
        Customer existing = new Customer(11,"deepak","gzb",5674.33);

        when(customerRepo.findById(existing.getAccNo())).thenReturn(Optional.of(existing));

        assertTrue(serviceClass.deleteCustomerByAccountNumber(existing.getAccNo()));

        verify(customerRepo).findById(existing.getAccNo());
        verify(customerRepo).delete(existing);
    }

    
    @Test
    void testDeleteAccountByAccountNumber_Failure(){
        Customer existing = new Customer(11,"deepak","gzb",5674.33);

        when(customerRepo.findById(existing.getAccNo())).thenReturn(Optional.empty());

        assertFalse(serviceClass.deleteCustomerByAccountNumber(existing.getAccNo()));

        verify(customerRepo).findById(existing.getAccNo());
    }


	
    @Test
    void testDepositByAccountNumber_Success(){
        Customer existing = new Customer(11,"roop singh","hindon",4600.66);
        Transaction transaction = new Transaction(existing.getAccNo(),existing.getAccNo(),450.00,null);

        when(customerRepo.findById(existing.getAccNo())).thenReturn(Optional.of(existing));
        when(transactionsRepo.save(any(Transaction.class))).thenReturn(transaction); //At run-time, new Transaction is created, that's why use "any"

        assertTrue(serviceClass.depositMoneyByAccountNumber(existing.getAccNo(),660));

        verify(customerRepo).findById(existing.getAccNo());
        verify(transactionsRepo).save(any(Transaction.class));
    }

	
    @Test
    void testDepositByAccountNumber_Failure(){
        Customer existing = new Customer(11,"roop singh","hindon",4600.66);

        when(customerRepo.findById(existing.getAccNo())).thenReturn(Optional.empty());

        assertFalse(serviceClass.depositMoneyByAccountNumber(existing.getAccNo(),660));

        verify(customerRepo).findById(existing.getAccNo());
    }

    
    @Test
    void testWithdrawByAccountNumber_Success(){
        Customer existing = new Customer(11,"roop singh","hindon",4600.66);

        when(customerRepo.findById(existing.getAccNo())).thenReturn(Optional.of(existing));

        assertTrue(serviceClass.withdrawMoneyByAccountNumber(existing.getAccNo(),660));

        verify(customerRepo).findById(existing.getAccNo());
    }

    
    @Test
    void testWithdrawByAccountNumber_LowBalanceCheck(){
        Customer existing = new Customer(11,"roop singh","hindon",4600.66);

        when(customerRepo.findById(existing.getAccNo())).thenReturn(Optional.of(existing));

        assertFalse(serviceClass.withdrawMoneyByAccountNumber(existing.getAccNo(),4660));

        verify(customerRepo).findById(existing.getAccNo());
    }

    
    @Test
    void testWithdrawByAccountNumber_Failure(){
        Customer existing = new Customer(11,"roop singh","hindon",4600.66);

        when(customerRepo.findById(existing.getAccNo())).thenReturn(Optional.empty());

        assertFalse(serviceClass.withdrawMoneyByAccountNumber(existing.getAccNo(),660));

        verify(customerRepo).findById(existing.getAccNo());
    }

	
    @Test
    void testFundTransferByAccountNumber_Success(){
        Customer customer1 = new Customer(11,"vijay singh","delhi",7800.99);
        Customer customer2 = new Customer(11,"roop singh","gzb",9887.99);

        when(customerRepo.findById(customer1.getAccNo())).thenReturn(Optional.of(customer1));
        when(customerRepo.findById(customer2.getAccNo())).thenReturn(Optional.of(customer2));
        when(customerRepo.save(customer1)).thenReturn(customer1);
        when(customerRepo.save(customer2)).thenReturn(customer2);

        assertTrue(serviceClass.fundTransferByAccountNumber(customer1.getAccNo(),800,customer2.getAccNo()));

        verify(customerRepo).findById(customer1.getAccNo());
        verify(customerRepo).findById(customer2.getAccNo());
        verify(customerRepo).save(customer1);
        verify(customerRepo).save(customer2);
    }

    
    @Test
    void testFundTransferByAccountNumber_LowBalanceCheck(){
        Customer customer1 = new Customer(11,"vijay singh","delhi",7800.99);
        Customer customer2 = new Customer(11,"roop singh","gzb",9887.99);

        when(customerRepo.findById(customer1.getAccNo())).thenReturn(Optional.of(customer1));
        when(customerRepo.findById(customer2.getAccNo())).thenReturn(Optional.of(customer2));

        assertFalse(serviceClass.fundTransferByAccountNumber(customer1.getAccNo(),7200,customer2.getAccNo()));

        verify(customerRepo).findById(customer1.getAccNo());
        verify(customerRepo).findById(customer2.getAccNo());
    }

	
    @Test
    void testFundTransferByAccountNumber_Failure(){
        Customer customer1 = new Customer(11,"vijay singh","delhi",7800.99);
        Customer customer2 = new Customer(11,"roop singh","gzb",9887.99);

        when(customerRepo.findById(customer1.getAccNo())).thenReturn(Optional.empty());
        when(customerRepo.findById(customer2.getAccNo())).thenReturn(Optional.of(customer2));

        assertFalse(serviceClass.fundTransferByAccountNumber(customer1.getAccNo(),800,customer2.getAccNo()));

        verify(customerRepo).findById(customer1.getAccNo());
        verify(customerRepo).findById(customer2.getAccNo());
    }
    

}