package se.royalspades.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.royalspades.model.User;
import se.royalspades.service.CategoryService;
import se.royalspades.service.CompanyService;
import se.royalspades.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		//redirect to main page
		return "home/home";
	}
	
	
	@RequestMapping("/menu")
	public String main(Map<String, Object> map){
		//Menu
		//if (admin){
		//return "menu/admin";
		//} else if (producer) {
		//return "menu/producer";
		//} else if (shopOwner) {
		//return "menu/shopowner"
		//} else {
		return "menu/default"
		//}
	}
}
