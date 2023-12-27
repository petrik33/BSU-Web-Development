package com.example.lab5sem2.repositories;

import com.example.lab5sem2.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
