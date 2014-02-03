package se.royalspades.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import se.royalspades.model.Product;
import se.royalspades.service.ProductService;

@Controller
@RequestMapping(value ="/api/product")
public class ProductController {
	@Autowired ProductService productService;

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
	
	
	// handle not found exception
	@ExceptionHandler({ EmptyResultDataAccessException.class,
        NullPointerException.class })
	@ResponseStatus(reason = "Reason", value = HttpStatus.NOT_FOUND)
	public void handleEmptyResultDataAccessException(
	        EmptyResultDataAccessException ex, HttpServletRequest request) {
		return;
	}
	
}


