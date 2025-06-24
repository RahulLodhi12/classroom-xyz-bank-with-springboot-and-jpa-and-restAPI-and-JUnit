package com.tcs.training.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.training.bean.Customer;
import com.tcs.training.bean.Transaction;
//import com.tcs.training.security.MyUserDetailsService;
//import com.tcs.training.security.SecurityConfig;
import com.tcs.training.service.ServiceClass;

import org.assertj.core.error.ShouldHaveSameSizeAs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

//import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ControllerClass.class)
//@Import(SecurityConfig.class)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceClass serviceClass;

    private final ObjectMapper objectMapper = new ObjectMapper();
    
//    @MockBean
//    private MyUserDetailsService myUserDetailsService;
    
    @Test
    void testGetAllCustomer_Success() throws Exception {
    	List<Customer> mockCustomers = new ArrayList<>(Arrays.asList(new Customer(11,"rahul lodhi","delhi",3500.89),new Customer(12,"mohit poonia","jaipur",4500.00)));
    	
    	when(serviceClass.getAllCustomer()).thenReturn(mockCustomers);
    	
    	mockMvc.perform(get("/xyz/bank/customers"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$",hasSize(2)));
    		
    }
    
    @Test
    void testGetAllCustomer_Failure() throws Exception {
//    	List<Customer> mockCustomers = new ArrayList<>(Arrays.asList(new Customer(11,"rahul lodhi","delhi",3500.89),new Customer(12,"mohit poonia","jaipur",4500.00)));
    	
    	when(serviceClass.getAllCustomer()).thenReturn(Collections.emptyList());
    	
    	mockMvc.perform(get("/xyz/bank/customers"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$",hasSize(0)));
    		
    }
    
    @Test
    void testCreateAccount_Success() throws Exception {
    	Customer existing = new Customer(101,"roop singh","hindon",4600.66);
        when(serviceClass.createAccount(existing.getAccNo(), "roop singh", "hindon", 4600.66)).thenReturn(true);

        mockMvc.perform(post("/xyz/bank/createAccount")
        		.param("accNo", String.valueOf(existing.getAccNo()))
                .param("name", "roop singh")
                .param("branch", "hindon")
                .param("balance", "4600.66"))
           .andExpect(status().isOk())
           .andExpect(content().string("true"));
    }
    
    @Test
    void testCreateAccount_Failure() throws Exception {
    	Customer existing = new Customer(101,"","hindon",4600.66);
        when(serviceClass.createAccount(existing.getAccNo(), "", "hindon", 4600.66)).thenReturn(false);

        mockMvc.perform(post("/xyz/bank/createAccount")
        		.param("accNo", String.valueOf(existing.getAccNo()))
                .param("name", "")
                .param("branch", "hindon")
                .param("balance", "4600.66"))
           .andExpect(status().isNotFound())
           .andExpect(content().string("Can't Create Account.."));
    }

    @Test
    void testUpdateCustomerByAccountNumber_Success() throws Exception {
        Customer customer = new Customer(11,"Rahul", "Delhi", 7000.0);
        Customer update = new Customer("Rajesh", "Delhi", 7000.0);
        when(serviceClass.updateCustomerByAccountNumber(eq(customer.getAccNo()), any(Customer.class))).thenReturn(true);

        mockMvc.perform(put("/xyz/bank/customers/{accNo}",customer.getAccNo())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update)))
        		.andExpect(status().isOk())
        		.andExpect(content().string("true"));
    }
    
    
    @Test
    void testUpdateCustomerByAccountNumber_Failure() throws Exception {
//        Customer customer = new Customer(11,"Rahul", "Delhi", 7000.0);
        Customer update = new Customer("Rajesh", "Delhi", 7000.0);
        when(serviceClass.updateCustomerByAccountNumber(eq(9999), any(Customer.class))).thenReturn(false);

        mockMvc.perform(put("/xyz/bank/customers/{accNo}",9999)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update)))
        		.andExpect(status().isNotFound())
        		.andExpect(content().string("Update Error.."));
    }
    
    @Test
    void testDeleteCustomerByAccountNumber_Success() throws Exception {
        Customer customer = new Customer(11,"Rahul", "Delhi", 7000.0);
//        Customer update = new Customer("Rajesh", "Delhi", 7000.0);
        when(serviceClass.deleteCustomerByAccountNumber(customer.getAccNo())).thenReturn(true);

        mockMvc.perform(delete("/xyz/bank/customers/{accNo}",customer.getAccNo())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
        		.andExpect(status().isOk())
        		.andExpect(content().string("true"));
    }
    
    @Test
    void testDeleteCustomerByAccountNumber_Failure() throws Exception {
//        Customer customer = new Customer(11,"Rahul", "Delhi", 7000.0);
//        Customer update = new Customer("Rajesh", "Delhi", 7000.0);
        when(serviceClass.deleteCustomerByAccountNumber(9999)).thenReturn(false);

        mockMvc.perform(delete("/xyz/bank/customers/{accNo}",9999)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString("")))
        		.andExpect(status().isNotFound())
        		.andExpect(content().string("Delete Error.."));
    }
    
    @Test
    void testDepositByAccountNumber_Success() throws Exception {
    	 Customer existing = new Customer(11,"roop singh","hindon",4600.66);
//         Transaction transaction = new Transaction(existing.getAccNo(),existing.getAccNo(),450.00,null);
         
         when(serviceClass.depositMoneyByAccountNumber(existing.getAccNo(), 300)).thenReturn(true); //body
         
         mockMvc.perform(post("/xyz/bank/customers/{accNo}/deposit",existing.getAccNo())
        		 .param("amt", String.valueOf(300)))
         	.andExpect(status().isOk())
         	.andExpect(content().string("true")); //body
    }
    
    
    
    @Test
    void testDepositByAccountNumber_Failure() throws Exception {
//    	 Customer existing = new Customer(11,"roop singh","hindon",4600.66);
//         Transaction transaction = new Transaction(existing.getAccNo(),existing.getAccNo(),450.00,null);
         
         when(serviceClass.depositMoneyByAccountNumber(9999, 300)).thenReturn(false); //body
         
         mockMvc.perform(post("/xyz/bank/customers/{accNo}/deposit",9999)
        		 .param("amt", String.valueOf(300)))
         	.andExpect(status().isBadRequest())
         	.andExpect(content().string("Deposit Fail..")); //body
    }
    
    @Test
    void testWithdrawByAccountNumber_Success() throws Exception {
    	 Customer existing = new Customer(11,"roop singh","hindon",4600.66);
//         Transaction transaction = new Transaction(existing.getAccNo(),existing.getAccNo(),450.00,null);
         
         when(serviceClass.withdrawMoneyByAccountNumber(existing.getAccNo(), 300)).thenReturn(true); //body
         
         mockMvc.perform(post("/xyz/bank/customers/{accNo}/withdraw",existing.getAccNo())
        		 .param("amt", String.valueOf(300)))
         	.andExpect(status().isOk())
         	.andExpect(content().string("true")); //body
    }
    
    @Test
    void testWithdrawByAccountNumber_Failure() throws Exception {
//    	 Customer existing = new Customer(11,"roop singh","hindon",4600.66);
//         Transaction transaction = new Transaction(existing.getAccNo(),existing.getAccNo(),450.00,null);
         
         when(serviceClass.withdrawMoneyByAccountNumber(999, 300)).thenReturn(false); //body
         
         mockMvc.perform(post("/xyz/bank/customers/{accNo}/withdraw",999)
        		 .param("amt", String.valueOf(300)))
         	.andExpect(status().isBadRequest())
         	.andExpect(content().string("Withdraw Fail..")); //body
    }
    
    
    @Test
    void testFundTransferByAccountNumber_Success() throws Exception {
    	 Customer customer1 = new Customer(11,"roop singh","hindon",4600.66);
    	 Customer customer2 = new Customer(12,"madhu singh","delhi",7899.00);
//         Transaction transaction = new Transaction(existing.getAccNo(),existing.getAccNo(),450.00,null);
         
         when(serviceClass.fundTransferByAccountNumber(customer1.getAccNo(), 300, customer2.getAccNo())).thenReturn(true); //body
         
         mockMvc.perform(post("/xyz/bank/customers/{accNo1}/fundTransfer",customer1.getAccNo())
        		 .param("amt", String.valueOf(300))
        		 .param("accNo2", String.valueOf(customer2.getAccNo())))
         	.andExpect(status().isOk())
         	.andExpect(content().string("true")); //body
    }
    
    @Test
    void testFundTransferByAccountNumber_Failure() throws Exception {
//    	 Customer customer1 = new Customer(11,"roop singh","hindon",4600.66);
    	 Customer customer2 = new Customer(12,"madhu singh","delhi",7899.00);
//         Transaction transaction = new Transaction(existing.getAccNo(),existing.getAccNo(),450.00,null);
         
         when(serviceClass.fundTransferByAccountNumber(9999, 300, customer2.getAccNo())).thenReturn(false); //body
         
         mockMvc.perform(post("/xyz/bank/customers/{accNo1}/fundTransfer",9999)
        		 .param("amt", String.valueOf(300))
        		 .param("accNo2", String.valueOf(customer2.getAccNo())))
         	.andExpect(status().isOk())
         	.andExpect(content().string("false")); //body
    }
    
    
    @Test
    void testGetCustomerByAccNumber_Success() throws Exception {
        Customer customer = new Customer(11,"rahul","delhi",8999.0);

        when(serviceClass.getCustomerByAccountNumber(customer.getAccNo())).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/xyz/bank/customers/"+customer.getAccNo()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("rahul"))
                .andExpect(jsonPath("$.branch").value("delhi"));

    }
    
    
    @Test
    void testGetCustomerByAccNumber_Failure() throws Exception {

        when(serviceClass.getCustomerByAccountNumber(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/xyz/bank/customers/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer not found"));

    }
    
    
}