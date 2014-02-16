package se.royalspades.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.royalspades.model.Brand;
import se.royalspades.model.Store;
import se.royalspades.model.User;
import se.royalspades.service.BrandService;
import se.royalspades.service.UserService;

@Controller
@RequestMapping(value ="/api/brand")
public class BrandController {
	@Autowired BrandService brandService;
	@Autowired UserService userService;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Brand> getAllBrandsResponse(){
		return brandService.getAllBrands();
	}
	
	
	// add new supplier
	@RequestMapping(value = "/admin/add_brand/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public String add(@Valid Brand brand, @PathVariable int userId){
		
		// throw and handle exceptions, javax.validation.ConstraintViolationException  etc

		// set the store user
		User user = userService.getUser(userId);
		brand.setUser(user);
		
		System.out.println(brand.getName() + brand.getUser().getFirstName());
		brandService.add(brand);
		return "Butik " + brand.getName() + " skapad!";	
	} 
}
  
