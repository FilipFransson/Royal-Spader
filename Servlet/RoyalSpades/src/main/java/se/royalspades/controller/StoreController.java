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

import se.royalspades.model.GroceryList;
import se.royalspades.model.Product;
import se.royalspades.model.Store;
import se.royalspades.service.StoreService;

@Controller
@RequestMapping(value ="/api/store")
public class StoreController {
	@Autowired StoreService storeService; 
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Store> getAllStoresResponse(){
		return storeService.getAllStores();
	}
	
	
	// return single product with id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Store getSingleStoreResponse(@PathVariable int id){
		return storeService.getStore(id);
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
   
