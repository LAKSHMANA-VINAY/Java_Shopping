package com.shopping.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.entity.orderEntity;
import com.shopping.repository.orderRepository;
import com.shopping.repository.userRepository;

@Service
public class orderService {

    @Autowired
    private orderRepository orderRepository;
        
    @Autowired
    private userRepository userRepository;

    private int totalAmount;
    
    public Map<String, Object> getInventoryDetails() {
        int totalQty = orderRepository.sumQuantities();
        Map<String, Object> response = new HashMap<>();
        response.put("ordered", 0); 
        response.put("price", 100);
        response.put("available", 100 - totalQty);
        return response;
    }
    
    @Transactional
    public orderEntity createOrder(int userId, int qty, String coupon) {
        
        int price = 100;
        double discount = calculateDiscount(coupon);
        totalAmount = (int) (qty * price * (1 - discount));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = currentDate.format(formatter);
        
        orderEntity order = new orderEntity();
        order.setUserID(userId);
        order.setDate(date);
        order.setCoupon(coupon);
        order.setAmount(totalAmount);
        order.setQty(qty);
        orderRepository.save(order);

        return new orderEntity(order.getOrderID(), userId, date,coupon,totalAmount,qty);
    }
    public boolean isValidCoupon(int userId, String coupon) {
        int count = orderRepository.countByUserIdAndCoupon(userId, coupon);
        return count < 1;
    }

    public boolean isValidQuantity(int qty) {
        int totalQty = orderRepository.sumQuantities();
        int available = 100 - totalQty;
        return available > totalAmount; 
    }
    
    public boolean checkUserAndCoupon(int userId,String coupon)
    {
		int user=userRepository.findUser(userId);
		return user>=1;
    }

    private double calculateDiscount(String coupon) {
        if ("OFF5".equals(coupon)) {
            return 0.05; 
        } else if ("OFF10".equals(coupon)) {
            return 0.10;
        } else {
            return 0;
        }
    }
    public List<Map<String, Object>> getUserOrders(int userId) {
        List<orderEntity> userOrders = orderRepository.findByUserId(userId);
        List<Map<String, Object>> orderResponses = new ArrayList<>();
        for (orderEntity order : userOrders) {
            Map<String, Object> orderResponse = new HashMap<>();
            orderResponse.put("orderId", order.getOrderID());
            orderResponse.put("amount", order.getAmount());
            orderResponse.put("date", order.getDate());
            orderResponse.put("coupon", order.getCoupon());
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }
}
