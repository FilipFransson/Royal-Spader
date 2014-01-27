package se.royalspades.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import se.royalspades.service.GroceryListService;

@Controller
public class GroceryListController {
	@Autowired GroceryListService grocerListService;

}
