package se.royalspades.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		return "index";
	}
	
	
	@RequestMapping("/main")
	public String main(Locale locale, Model model){
		//Admin - Main page
		model.addAttribute("pageUid", "9e60de60-8d77-11e3-baa8-0800200c9a66" );
		
		return "admin/main";
	}
	
	@RequestMapping("/shops")
	public String shop(Locale locale, Model model){
		//Admin - shop page
		model.addAttribute("pageUid", "a82b9520-8d77-11e3-baa8-0800200c9a66" );
		
		
		return "admin/shops";
	}
	
	@RequestMapping("/suppliers")
	public String supplier(Locale locale, Model model){
		//Admin - supplier page
		model.addAttribute("pageUid", "ae8fef60-8d77-11e3-baa8-0800200c9a66" );
		
		
		return "admin/suppliers";
	}
	
	@RequestMapping("/user")
	public String user(Locale locale, Model model){
		//Admin - user page
		model.addAttribute("pageUid", "b8d6db00-8d77-11e3-baa8-0800200c9a66" );
		
		
		return "admin/user";
	}
	
	@RequestMapping("/editShop")
	public String editShop(Locale locale, Model model, @RequestParam(value = "id", required = true) int id){
		//Admin - edit shop page
		model.addAttribute("pageUid", "9d175af0-f947-4827-8e93-cd44a8531d1a" );
		
		model.addAttribute("id", id);
		
		return "admin/editShop";
	}
	
	@RequestMapping("/newShop")
	public String newShop(Locale locale, Model model){
		//Admin - new shop page
		model.addAttribute("pageUid", "cf3daa14-80ef-4da3-9d4e-e00ad67174cf" );
		
		
		return "admin/newShop";
	}
	
	@RequestMapping("/editSupplier")
	public String editSupplier(Locale locale, Model model, @RequestParam(value = "id", required = true) int id){
		//Admin - edit supplier page
		model.addAttribute("pageUid", "387a42ea-ab92-4420-9c84-a6d58706ff2c" );
		
		model.addAttribute("id", id);
		
		return "admin/editSupplier";
	}
	
	@RequestMapping("/newSupplier")
	public String newSupplier(Locale locale, Model model){
		//Admin - new supplier page
		model.addAttribute("pageUid", "2e7898b9-5e3f-42a1-a02d-d0735da229c4" );
		
		
		return "admin/newSupplier";
	}
	
	
}
