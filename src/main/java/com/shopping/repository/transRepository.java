package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shopping.entity.transEntity;

public interface transRepository extends JpaRepository<transEntity,Integer>{

    @Query(value = "SELECT COUNT(*) FROM order_shopping WHERE userID = ?1 AND orderID = ?2 AND amount = ?3", nativeQuery = true)
    int findRecord(int userId, int orderId,int amount);
    
    @Query(value="SELECT coupon FROM order_shopping WHERE userID = ?1 AND orderID = ?2",nativeQuery = true)
    String getCoupon(int userId, int orderId);
    
    @Query(value="SELECT COUNT(*) FROM trans_shopping WHERE orderID = ?1",nativeQuery = true)
    int checkRecord(int orderId);
    
    @Query(value="SELECT COUNT(*) FROM trans_shopping WHERE orderID = ?1",nativeQuery = true)
    int checkUserRecord(int orderId);
    
    @Query(value="SELECT * FROM trans_shopping WHERE orderID = ?1",nativeQuery = true)
    List<transEntity> findTrans(int orderId);
}
