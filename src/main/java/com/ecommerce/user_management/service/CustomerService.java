package com.ecommerce.user_management.service;


import com.ecommerce.user_management.Exceptions.InvalidUserNameException;
import com.ecommerce.user_management.model.Customer;
import com.ecommerce.user_management.model.Role;

import java.util.List;
import java.util.concurrent.Future;

public interface CustomerService {

    Customer registerCustomer(Customer customer);

    boolean loginCustomer(Customer customer);

    List<Customer> getCustomers();

    Customer updateCustomerByUserName(String userName, Customer customer) throws Exception;

    Customer getCustomerById(int id);

    Future<Customer> getCustomerByIdAsync(int id, int num);

    Customer getCustomerByUserName(String userName);

    Customer getCustomerByPhoneNumber(Long phoneNumber);

    Role saveRole(Role role);

    Customer addRoleToUser(String userName, String roleName) throws InvalidUserNameException;

}
