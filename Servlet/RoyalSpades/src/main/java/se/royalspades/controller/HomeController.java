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
import se.royalspades.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		//redirect to main page
		return "index";
	}
	
	
	@RequestMapping("/main")
	public String main(Locale locale, Model model){
		//Default user - Main page
		model.addAttribute("pageUid", "1a6d5630-8d75-11e3-baa8-0800200c9a66" );
		
		return "home/main";
	}
	
	@RequestMapping("/help")
	public String help(Locale locale, Model model){
		//Default user - Help page
		model.addAttribute("pageUid", "ecae7380-8d76-11e3-baa8-0800200c9a66" );
		
		return "home/help";
	}
	
	@RequestMapping("/newgrocerybag")
	public String newgrocerybag(Locale locale, Model model){
		//Default user - New grocery bag page
		model.addAttribute("pageUid", "f5cb8520-8d76-11e3-baa8-0800200c9a66" );
		
		return "home/newgrocerybag";
	}
	
	@RequestMapping("/settings")
	public String settings(Locale locale, Model model){
		//Default user - New grocery bag page
		model.addAttribute("pageUid", "fd2e63a0-8d76-11e3-baa8-0800200c9a66" );
		
		return "home/settings";
	}
	
	
}
