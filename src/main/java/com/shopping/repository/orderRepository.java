package com.shopping.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopping.entity.orderEntity;

@Repository
public interface orderRepository extends JpaRepository<orderEntity,Integer>{

    @Query(value="SELECT COALESCE(SUM(o.qty), 0) FROM order_shopping o",nativeQuery=true)
    int sumQuantities();
    
    @Query(value = "SELECT COUNT(*) FROM order_shopping WHERE userID = ?1 AND coupon = ?2", nativeQuery = true)
    int countByUserIdAndCoupon(int userId, String coupon);
    
    @Modifying
    @Query(value = "INSERT INTO order_shopping (userID, date, coupon, amount, qty) VALUES (:userID, :date, :coupon, :amount, :qty)", nativeQuery = true)
    void insertOrder(@Param("userID") int userID,
                     @Param("date") String date,
                     @Param("coupon") String coupon,
                     @Param("amount") int amount,
                     @Param("qty") int qty);
    
    @Query(value = "SELECT * FROM order_shopping WHERE userID = ?1", nativeQuery = true)
    List<orderEntity> findByUserId(int userId);
    
    @Query(value = "SELECT COUNT(*) FROM order_shopping WHERE userID = ?1", nativeQuery = true)
    int checkRecord(int userId);

}
