package com.shopping.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopping.entity.userEntity;

public interface userRepository extends JpaRepository<userEntity,Integer>{

    @Modifying
    @Query(value = "INSERT INTO user_shopping(userName) VALUES (:userName)", nativeQuery = true)
    void insertUser(@Param("userName") String userName);
    
    @Query(value = "SELECT COUNT(*) FROM user_shopping WHERE userID = ?1", nativeQuery = true)
    int findUser(int userId);
}
