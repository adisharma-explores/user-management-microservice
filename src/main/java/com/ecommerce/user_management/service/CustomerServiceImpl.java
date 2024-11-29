package com.ecommerce.user_management.service;


import com.ecommerce.user_management.Exceptions.InvalidUserNameException;
import com.ecommerce.user_management.model.Customer;
import com.ecommerce.user_management.model.Role;
import com.ecommerce.user_management.repository.CustomerRepository;
import com.ecommerce.user_management.repository.RoleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository Cr;
    @Autowired
    private RoleRepo roleRepo;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);


    @Override
    public Customer registerCustomer(Customer customer) {
        logger.error("error");
        customer.setCustomerPassword(new BCryptPasswordEncoder().encode(customer.getCustomerPassword()));
        return Cr.save(customer);
    }

    @Override
    public boolean loginCustomer(Customer customer) {
        logger.info("logging in Customer");

        Customer databaseObject = Cr.findByCustomerUserName(customer.getCustomerUserName());
        if (databaseObject == null) {
            return false;
        }
        if (databaseObject.getCustomerUserName().equals(customer.getCustomerUserName())
                && databaseObject.getCustomerPassword().equals(customer.getCustomerPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> getCustomers() {
        logger.info("get All Customer");

        return Cr.findAll();

    }

    //
    @Override
    public Customer updateCustomerByUserName(String userName, Customer customer) throws Exception {
        logger.info("Updating Customer");

        Customer ans = null;
        Customer databaseObject = Cr.findByCustomerUserName(userName);
        if (databaseObject == null) throw new InvalidUserNameException("username doesnt exist");
        databaseObject.setCustomerUserName(customer.getCustomerUserName());
        databaseObject.setCustomerName(customer.getCustomerName());
        databaseObject.setCustomerPassword(customer.getCustomerPassword());
        databaseObject.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());
        System.out.println("updated the user name");
        return Cr.save(databaseObject);

    }

    @Async
    @Override
    public Customer getCustomerById(int id) {
        logger.info("getting Customer by id");
        return Cr.findById(id).get();
    }

    @Async
    @Override
    public Future<Customer> getCustomerByIdAsync(int id, int num) {
        logger.info("getting Customer by id");
        System.out.println("customer:" + num);
        return new AsyncResult<>(Cr.findById(id).get());
    }

    @Override
    public Customer getCustomerByUserName(String userName) {
        logger.info("getting Customer by username");
        return Cr.findByCustomerUserName(userName);
    }

    @Override
    public Customer getCustomerByPhoneNumber(Long phoneNumber) {
        logger.info("getting Customer by phone number");
        return Cr.findByCustomerPhoneNumber(phoneNumber);
    }

    @Override
    public Role saveRole(Role role) {
        logger.info("saving role");
        return roleRepo.save(role);
    }

    @Override
    public Customer addRoleToUser(String userName, String roleName) throws InvalidUserNameException {
        logger.info("adding role to Customer");
        Customer customer = Cr.findByCustomerUserName(userName);
        Role role = roleRepo.findByName(roleName);
        if (customer == null || role == null) throw new InvalidUserNameException("username doesnt exist");

        customer.getRole().add(role);
        return Cr.save(customer);
    }

}
