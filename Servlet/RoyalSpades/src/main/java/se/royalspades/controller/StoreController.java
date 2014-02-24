package se.royalspades.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import se.royalspades.model.GroceryList;
import se.royalspades.model.Product;
import se.royalspades.model.Store;
import se.royalspades.model.User;
import se.royalspades.service.StoreService;
import se.royalspades.service.UserService;

@Controller
@RequestMapping(value ="/api/store")
public class StoreController {
	@Autowired StoreService storeService; 
	@Autowired UserService userService;
	
	
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
	
	// add new store, send user id as a url parameter
	@RequestMapping(value = "/admin/add_store/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public String addStore(@Valid Store store, @PathVariable int userId){
		
		// throw and handle exceptions, javax.validation.ConstraintViolationException  etc
	    // http://www.ibm.com/developerworks/library/wa-restful/
		// http://www.jayway.com/2013/02/03/improve-your-spring-rest-api-part-iii/
		// , produces = { MediaType.APPLICATION_JSON_VALUE }   can't return json here, ajax not prepared for it
		// a store can only be administrated by the correct user
		
		// set the store user
		User user = userService.getUser(userId);
		store.setUser(user);
		
		System.out.println(store.getName() + " " + userId);
		storeService.add(store);
		return "Butik " + store.getName() + " skapad!";	
	} 
	
	     
	// edit store
	@RequestMapping(value = "/admin/edit_store", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity <String> editStore(@RequestBody @Valid Store store){
		storeService.edit(store);
		return new ResponseEntity <String>("Butik " + store.getName() + " ändrad!", HttpStatus.OK);	
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
   
