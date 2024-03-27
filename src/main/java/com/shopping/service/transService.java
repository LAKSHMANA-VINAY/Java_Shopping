package com.shopping.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.entity.transEntity;
import com.shopping.repository.transRepository;

@Service
public class transService {

	@Autowired
	private transRepository transRepository;
	
	public boolean checkDetails(int userId,int orderId,int amount)
	{
		int check=transRepository.findRecord(userId,orderId,amount);
		return check==1;
	}
	
	@Transactional
    public transEntity makePayment(int userId, int orderId, int amount) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = currentDate.format(formatter);
        String coupon=transRepository.getCoupon(userId,orderId);
        transEntity trans=new transEntity();
        trans.setOrderID(orderId);
        trans.setDate(date);
        trans.setCoupon(coupon);
        trans.setAmount(amount);
        trans.setStatus("successful");
        transRepository.save(trans);
        return new transEntity(trans.getTransID(),orderId,date,coupon,amount,trans.getStatus());
    }
	
	public boolean checkDetails(int userId,int orderId)
	{
		int check=transRepository.checkUserRecord(orderId);
		if(check==0)
		{
			return false;
		}
		return true;
	}
    public List<Map<String, Object>> getAllDetails(int userId,int orderId) {
        List<transEntity> userTrans = transRepository.findTrans(orderId);
        List<Map<String, Object>> transResponses = new ArrayList<>();
        for (transEntity trans : userTrans) {
            Map<String, Object> orderResponse = new HashMap<>();
            orderResponse.put("orderId", trans.getOrderID());
            orderResponse.put("amount", trans.getAmount());
            orderResponse.put("date", trans.getDate());
            orderResponse.put("coupon", trans.getCoupon());
            orderResponse.put("transId", trans.getTransID());
            orderResponse.put("status", trans.getStatus());
            transResponses.add(orderResponse);
        }
        return transResponses;
    }
}
