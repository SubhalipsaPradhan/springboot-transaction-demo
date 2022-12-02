package com.example.springboot.service;

import java.util.List;

import com.example.springboot.dto.OrderRequest;
import com.example.springboot.dto.OrderResponse;
import com.example.springboot.entity.Order;

public interface OrderService {
	
	OrderResponse placeOrder(OrderRequest orderRequest);
	List<Order> getAllOrders();
	

}
