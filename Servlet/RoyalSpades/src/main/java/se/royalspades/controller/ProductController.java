package se.royalspades.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import se.royalspades.model.Brand;
import se.royalspades.model.Category;
import se.royalspades.model.Product;
import se.royalspades.model.User;
import se.royalspades.service.BrandService;
import se.royalspades.service.CategoryService;
import se.royalspades.service.ProductService;
import se.royalspades.service.UserService;

@Controller
@RequestMapping(value ="/api/product")
public class ProductController {
	@Autowired ProductService productService;
	@Autowired CategoryService categoryService;
	@Autowired BrandService brandService;
	@Autowired UserService userService;

	// return all products
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Product> getAllProductsResponse(){
		return productService.getAllProducts();
	}
	
	
	// return single product with id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Product getSingleProductResponse(@PathVariable int id){
		return productService.getProduct(id);
	}
	
	// test
	@RequestMapping(value = "/add_test", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String getTest(){
		 
		 Category category = categoryService.getCategory(1);
		 User user = userService.getUser(1);	 
		 Brand brand = brandService.getBrand(1);

		 Product product = new Product("Milk", 1, "Liter", brand, category);
		 productService.add(product);
		 return "Done";
	}
	
	// add product
	@RequestMapping(value="/add_product", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity <String> addProduct(@RequestBody Product product){
		/*
		try{
		} catch (Exception ex){
			return new ResponseEntity <String>("Internal error, The Product could not be added!", HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		*/
			
		productService.add(product);
		
		return new ResponseEntity <String>("Product added successfully", HttpStatus.OK);	
	}
	
	
	
	
	// handle not found exception
	@ExceptionHandler({ EmptyResultDataAccessException.class,
        NullPointerException.class })
	@ResponseStatus(reason = "Reason", value = HttpStatus.NOT_FOUND)
	public void handleEmptyResultDataAccessException(
	        EmptyResultDataAccessException ex, HttpServletRequest request) {
		return;
	}
	
}


///royalspades/api/product/1
///royalspades/api/product/all