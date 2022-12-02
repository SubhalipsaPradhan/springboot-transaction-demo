package com.example.springboot.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.dto.OrderRequest;
import com.example.springboot.dto.OrderResponse;
import com.example.springboot.entity.Order;
import com.example.springboot.entity.Payment;
import com.example.springboot.exception.PaymentException;
import com.example.springboot.repository.OrderRepository;
import com.example.springboot.repository.PaymentRepository;
import com.example.springboot.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	private OrderRepository orderRepository;
	private PaymentRepository paymentRepository;
	
	

	public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
		this.orderRepository = orderRepository;
		this.paymentRepository = paymentRepository;
	}



	@Override
	@Transactional
	public OrderResponse placeOrder(OrderRequest orderRequest) {
		Order order = orderRequest.getOrder();
		//System.out.println(order);
		order.setStatus("INPROGRESS");
		order.setOrderTrackingNumber(UUID.randomUUID().toString());
		orderRepository.save(order);
		
		Payment payment = orderRequest.getPayment();
		
		if(!payment.getType().equals("DEBIT")) {
			throw new PaymentException("The Card type is not supported");
		}
		
		payment.setOrderId(order.getId());
		paymentRepository.save(payment);
		
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
		orderResponse.setStatus(order.getStatus());
		orderResponse.setMessage("SUCCESS");		
		return orderResponse;
	}


	@Override
	public List<Order> getAllOrders() {
		 List<Order> orders = orderRepository.findAll();
		return orders;
	}

}
