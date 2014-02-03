package se.royalspades.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.royalspades.model.Category;
import se.royalspades.service.CategoryService;

@Controller
@RequestMapping(value ="/api/category")
public class CategoryController {
	@Autowired CategoryService categoryService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Category> getAllCategoriesResponse(){
		return categoryService.getAllCategories();
	}
}
