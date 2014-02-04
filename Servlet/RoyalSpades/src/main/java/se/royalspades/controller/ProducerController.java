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
@RequestMapping(value = "/producer")
public class ProducerController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//Redirecta till main
		return "producer/home";
	}
	
	
	@RequestMapping("/main")
	public String main(Locale locale, Model model){
		//Producer - Main page
		model.addAttribute("pageUid", "933c95a0-8d78-11e3-baa8-0800200c9a66" );
		
		return "producer/main";
	}
	
	@RequestMapping("/categories")
	public String categories(Locale locale, Model model){
		//Producer - categories page
		model.addAttribute("pageUid", "9a3b3410-8d78-11e3-baa8-0800200c9a666" );
		
		
		return "producer/categories";
	}
	
	@RequestMapping("/wares")
	public String wares(Locale locale, Model model){
		//Producer - wares page
		model.addAttribute("pageUid", "a1d6aec0-8d78-11e3-baa8-0800200c9a66" );
		
		
		return "producer/wares";
	}
}
