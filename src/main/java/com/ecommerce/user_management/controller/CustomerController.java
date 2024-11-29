package com.ecommerce.user_management.controller;


import com.ecommerce.user_management.Exceptions.InvalidPhoneNumberException;
import com.ecommerce.user_management.Exceptions.InvalidUserNameException;
import com.ecommerce.user_management.model.Customer;
import com.ecommerce.user_management.model.Role;
import com.ecommerce.user_management.pojo.CustomerRequest;
import com.ecommerce.user_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user/apis/v2")
public class CustomerController {
    @Autowired
    CustomerService Cs;

    @PostMapping("/registerCustomer")
    public Customer registerCustomer(@RequestBody Customer customer) throws InvalidPhoneNumberException, InvalidUserNameException {
        if (customer.getCustomerPhoneNumber().toString().length() != 10) {
            throw new InvalidPhoneNumberException("Phone number length should be 10!");
        }
        //checking for special characters in customer's username
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(customer.getCustomerUserName());
        if (matcher.find()) {
            throw new InvalidUserNameException("User name cant have special characters");
        }
        return Cs.registerCustomer(customer);
    }

    @PostMapping("/customerPhone")
    public Customer getCustomerByPhone(@RequestBody Customer customer) {
        return Cs.getCustomerByPhoneNumber(customer.getCustomerPhoneNumber());
    }

    @GetMapping("/loginCustomer")
    public String loginCustomer(@RequestBody Customer customer) {
        if (Cs.loginCustomer(customer)) {
            return "Logged in successfully";
        }
        return "Cant login! Invalid credentials";
    }

    //  @PreAuthorize("hasAuthority('SCOPE_profile.read')")
    @GetMapping("/getCustomers")
    public List<Customer> getCustomer(
            @RequestHeader(value = "X-User", required = false) String username,
            @RequestHeader(value = "X-Roles", required = false) String roles,
            @RequestHeader(value = "X-UserID", required = false) String userId) {
        if (username == null || roles == null || userId == null) {
            System.out.println("Missing required headers");
        }
        return Cs.getCustomers();
    }

    @PutMapping("/updateCustomerByUserName/{userName}")
    public Customer updateCustomerByUserName(@PathVariable("userName") String userName, @RequestBody Customer customer)
            throws Exception {
        return Cs.updateCustomerByUserName(userName, customer);
    }

    @GetMapping("/getCustomerById/{id}")
    public Customer getCustomerById(@PathVariable("id") int id) {
        return Cs.getCustomerById(id);
    }

    @GetMapping("/getCustomerByUserName/{userName}")
    public Customer getCustomerByUserName(@PathVariable("userName") String userName) {
        return Cs.getCustomerByUserName(userName);
    }


}
