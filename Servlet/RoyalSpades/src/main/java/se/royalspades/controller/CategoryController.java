package se.royalspades.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import se.royalspades.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired CategoryService categoryService;	

}
