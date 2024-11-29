package com.ecommerce.user_management.repository;


import com.ecommerce.user_management.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
    Customer findByCustomerUserName(String customerUserName);
    Customer findByCustomerPhoneNumber(Long customerPhoneNumber);

}
