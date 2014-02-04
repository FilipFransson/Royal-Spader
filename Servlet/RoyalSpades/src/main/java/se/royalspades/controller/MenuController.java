package se.royalspades.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {
	
	boolean admin = false;
	boolean producer = false;
	boolean shopowner = true;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String yo(Locale locale, Model model) {
		
		//redirect to main page
		if(admin){
			return "menu/admin";
		} else if(producer){
			return "menu/producer";
		} else if(shopowner){
			return "menu/shopowner";
		}
		return "menu/default";
	}
	
	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String menu(Locale locale, Model model) {
		
		//redirect to main page
		return "menu/default";
	}
	
	@RequestMapping(value = "/shopowner", method = RequestMethod.GET)
	public String shopowner(Locale locale, Model model) {
		
		//redirect to main page
		return "menu/shopowner";
	}
	
	@RequestMapping(value = "/producer", method = RequestMethod.GET)
	public String producer(Locale locale, Model model) {
		
		//redirect to main page
		return "menu/producer";
	}

}
