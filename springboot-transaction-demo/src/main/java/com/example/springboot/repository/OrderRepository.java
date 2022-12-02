package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
