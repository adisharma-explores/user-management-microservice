package com.ecommerce.user_management.model;


import jakarta.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "customer_details")
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

//    @Size(min = 3, max = 20, message = "name should be within 3 to 20 characters")
//    @Pattern(regexp = "^[A-Za-z]+([ ][a-zA-Z]+)*$", message = "there should be no special characters.")
    private String customerName;

    @Column(name = "phone_number")
    private Long customerPhoneNumber;

//    @NotNull
    private Date customerRegisterDate = new Date();


//    @Size(min = 3, max = 12, message = "username should be within 3 to 12 characters")
    private String customerUserName;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role = new ArrayList<>();

   private String customerPassword;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(Long customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public Date getCustomerRegisterDate() {
        return customerRegisterDate;
    }

    public void setCustomerRegisterDate(Date customerRegisterDate) {
        this.customerRegisterDate = customerRegisterDate;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
