package se.royalspades.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {
	
	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String menu(Locale locale, Model model) {
		
		//redirect to main page
		return "menu/default";
	}

}
