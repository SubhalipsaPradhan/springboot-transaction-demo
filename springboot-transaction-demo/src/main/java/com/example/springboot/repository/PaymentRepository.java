package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment , Long> {

}
