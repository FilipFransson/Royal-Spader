package se.royalspades.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.royalspades.model.User;
import se.royalspades.service.UserService;

@Controller
@RequestMapping(value = "/api")
public class UserController {
	@Autowired UserService userService;
	

	// will return a list with all users
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/admin/user/all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<User> getAllUsersResponse(){
		return userService.getAllUsers();
	}
}



///royalspades/api/user/all
