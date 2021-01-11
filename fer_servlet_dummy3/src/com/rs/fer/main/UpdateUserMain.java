package com.rs.fer.main;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class UpdateUserMain {

	public static void main(String[] args) {
		
		  int userId  = 1;
		  
		  FERService ferService = new FERServiceImpl();
		  
		  User user = ferService.getUser(userId);
		  
		  user.setFirstName("ABCD");
		  user.setMiddleName("");
		  user.setLastName("sandeep");
		  
		  user.setEmail("sandeep453@gmail.com");
		  user.setMobile("9010861414");
		  
		  Address address = user.getAddress();
		  
		  address.setLineOne("flatNo:204");
		  address.setLineTwo("Nandigama");
		  address.setCity("vijayawada");
		  address.setState("AndhraPradesh");
		  address.setPinCode("521185");
		  address.setCountry("India");
		  address.setUserId(user.getId());
		  
		  boolean isUpdate = ferService.updateUser(user);
		  
		  if(isUpdate) {
			  System.out.println("User updated successfully");
		  }
		  else {
			  System.out.println("User update failed");
		  }
		  
		  
		
		
		
	}

}
