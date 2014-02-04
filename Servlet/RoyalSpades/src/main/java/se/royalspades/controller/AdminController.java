package se.royalspades.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//Redirecta till main
		return "admin/home";
	}
	
	
	@RequestMapping("/main")
	public String main(Locale locale, Model model){
		//Admin - Main page
		model.addAttribute("pageUid", "9e60de60-8d77-11e3-baa8-0800200c9a66" );
		
		return "admin/main";
	}
	
	@RequestMapping("/shop")
	public String shop(Locale locale, Model model){
		//Admin - shop page
		model.addAttribute("pageUid", "a82b9520-8d77-11e3-baa8-0800200c9a66" );
		
		
		return "admin/shop";
	}
	
	@RequestMapping("/supplier")
	public String supplier(Locale locale, Model model){
		//Admin - supplier page
		model.addAttribute("pageUid", "ae8fef60-8d77-11e3-baa8-0800200c9a66" );
		
		
		return "admin/supplier";
	}
	
	@RequestMapping("/user")
	public String user(Locale locale, Model model){
		//Admin - user page
		model.addAttribute("pageUid", "b8d6db00-8d77-11e3-baa8-0800200c9a66" );
		
		
		return "admin/user";
	}
}
