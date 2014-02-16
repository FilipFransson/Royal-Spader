package se.royalspades.controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.royalspades.model.User;
import se.royalspades.service.UserService;

@Controller
@RequestMapping(value = "/api")
public class UserController {
	@Autowired UserService userService;
	
	
	// return a list with all users
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/admin/user/all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<User> getAllUsersResponse(){
		return userService.getAllUsers();
	}
	
	// return a list with all shop managers
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/admin/user/shop_managers", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<User> getAllShopManagersResponse(){
		List<User> users = userService.getAllUsers();
		List<User> shopManagers = new ArrayList<User>();
		
		for(User user : users){
			if(user.getRole().equals("shopowner")){
				// if the user is a shop manager
				// add to that list
				shopManagers.add(user);
			}
		}
		
		return shopManagers;
	}
	
	// return a list with all brand managers
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/admin/user/brand_managers", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<User> getAllBrandManagersResponse(){
		List<User> users = userService.getAllUsers();
		List<User> brandManagers = new ArrayList<User>();
		
		for(User user : users){
			if(user.getRole().equals("producer")){
				// if the user is a brand manager
				// add to that list
				brandManagers.add(user);
			}
		}
		
		return brandManagers;
	}
	
	
	// add a new user
	@RequestMapping(value ="/admin/user/new_user", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(@Valid User user){
		
		System.out.println(user.getUsername());
		
		// check if username is in use
		User oldUser = null;
		oldUser = userService.getUserByUsername(user.getUsername());
		
		if(oldUser != null){
			return "Användarnamnet är upptaget! Välj ett annat.";	
		} else {
			// create salt
			String salt = BCrypt.gensalt(10,new SecureRandom(Long.valueOf(new Date().getTime()).toString().getBytes()));
			user.setSalt(salt);
			
			// role for everyone, update at request
			user.setRole("user");
			
			// salt password
			user.setPassword(BCrypt.hashpw(user.getPassword(), salt));
			
			userService.add(user);
			
			return "Användarkonto för " + user.getUsername() + " skapat!";	
		}
	}
	
	
	
	// modify existing user
	@RequestMapping(value="/user/update_user", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity <String> updateUser(@RequestBody @Valid User user){
		
		// find values for that user account
		User oldUser = userService.getUserByUsername(user.getUsername());
		
		//System.out.println("check:"+BCrypt.checkpw(user.getPassword(), hashed)); // check:true
		
	    // username should be unique
		// check if values for old password are correct
		
		userService.edit(user);
		
		return new ResponseEntity <String>("Ändringar för " + user.getUsername() + " sparade!", HttpStatus.OK);
	}
	
}


// handle exceptions 