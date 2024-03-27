package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.userEntity;
import com.shopping.service.userService;

@RestController
public class userController {

    @Autowired
    private userService userService;

    @PostMapping(value = "/userInsert",consumes = "application/json")
    public HttpStatus addUserShopping(@RequestBody UserShoppingRequest userShoppingRequest) {
        userEntity newUserShopping = new userEntity();
        newUserShopping.setUserName(userShoppingRequest.getName());
        boolean isInserted = userService.addUserShopping(newUserShopping);
        if (isInserted) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
    static class UserShoppingRequest {
        private String name;
        
        public UserShoppingRequest() {}
        
		public UserShoppingRequest(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
        
    }
}
