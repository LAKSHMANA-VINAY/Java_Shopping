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

import com.shopping.entity.transEntity;
import com.shopping.repository.transRepository;
import com.shopping.service.transService;

@RestController
public class transController {

    @Autowired
    private transService transService;
    
    @Autowired
    private transRepository transRepository;
    
    @PostMapping("/{userId}/{orderId}/pay")
    public ResponseEntity<?> processPayment(@PathVariable int userId,
                                            @PathVariable int orderId,
                                            @RequestParam int amount) {
        if (!transService.checkDetails(userId, orderId,amount)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "POrder Not Found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
		int checkRecord=transRepository.checkRecord(orderId);
		if(checkRecord==1)
		{
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Payment Already Done");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
        transEntity response = transService.makePayment(userId, orderId, amount);
        Map<String, Object> list = new HashMap<>();
        list.put("userId", userId);
        list.put("orderId",response.getOrderID());
        list.put("transId",response.getTransID());
        list.put("status",response.getStatus());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping("/{userId}/orders/{orderId}")
    public ResponseEntity<?> getTransDetails(@PathVariable int userId,@PathVariable int orderId)
    {
        if (!transService.checkDetails(userId, orderId)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "No Records Found or Details May Wrong");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        List<Map<String, Object>> response = transService.getAllDetails(userId, orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
