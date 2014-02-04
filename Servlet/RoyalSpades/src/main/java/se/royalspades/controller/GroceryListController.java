package se.royalspades.controller;

import java.util.ArrayList;
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
import se.royalspades.service.GroceryListService;

@Controller
@RequestMapping(value = "/api/grocerylist")
public class GroceryListController {
	@Autowired GroceryListService grocerListService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<GroceryList> getAllGroceryLists(){
		return grocerListService.getAllGroceryLists();
	}
	

	// get all grocery lists for a certain user
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<GroceryList> getUserGroceryListsResponse(@PathVariable int id){
		@SuppressWarnings("unchecked")
		List<GroceryList> groceryLists = grocerListService.getAllGroceryLists();
		List<GroceryList> groceryListsToReturn = new ArrayList<GroceryList>();
		
		for(GroceryList groceryList : groceryLists){
			if(groceryList.getListOwner().getId() == id){
				groceryListsToReturn.add(groceryList);
			}
		}
		
		// return the grocery lists that belongs to the user (with path id)
		return groceryListsToReturn;
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




///royalspades/api/grocerylist/all

///royalspades/api/grocerylist/user/1


