package com.shopping.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.orderEntity;
import com.shopping.repository.orderRepository;
import com.shopping.service.orderService;

@RestController
public class orderController {

    @Autowired
    private orderService orderService;
    
    @Autowired
    private orderRepository orderRepository;

    @GetMapping(value="/inventory", produces="application/json")
    public Map<String, Object> getInventory() {
        return orderService.getInventoryDetails();
    }
    
    @GetMapping(value = "/fetchCoupons", produces = "application/json")
    public Map<String, Integer> fetchCoupons() {
        Map<String, Integer> coupons = new HashMap<>();
        coupons.put("OFF5", 5);
        coupons.put("OFF10", 10);
        return coupons;
    }
    
    @PostMapping(value="/{userId}/order")
    public ResponseEntity<?> createOrder(@PathVariable int userId,
                                         @RequestParam int qty,
                                         @RequestParam String coupon) {
        if (!orderService.checkUserAndCoupon(userId, coupon)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "user not found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (!orderService.isValidCoupon(userId, coupon)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid coupon");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (!orderService.isValidQuantity(qty)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid Qty");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        orderEntity response = orderService.createOrder(userId, qty, coupon);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/{userId}/orders")
    public ResponseEntity<?> getUserOrders(@PathVariable int userId) {
		int checkRecord=orderRepository.checkRecord(userId);
		if(checkRecord==0)
		{
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order not found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
        List<Map<String, Object>> orders = orderService.getUserOrders(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
