package se.royalspades.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import se.royalspades.service.ProductService;

@Controller
public class ProductController {
	@Autowired ProductService productService;

}
