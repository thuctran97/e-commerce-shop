package eshop.controller;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eshop.entity.Category;
import eshop.entity.Product;
import eshop.entity.Supplier;
import eshop.service.CategoryService;
import eshop.service.ProductService;
import eshop.service.SupplierService;

@Controller
@RequestMapping("product")
public class ProductController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	SupplierService supplierService;
	@Autowired
	ProductService productService; 
	
	@RequestMapping("list-by-category/{id}")
	public String listByCategory(ModelMap model, 
			@PathVariable("id") Integer id) {
		Category category = categoryService.get(id);
		Hibernate.initialize(category.getProducts());
		model.addAttribute("prods",category.getProducts());
		return "user/product/list";
	}
	@RequestMapping("list-by-supplier/{id}")
	public String listBySupplier(ModelMap model, 
			@PathVariable("id") String id) {
		Supplier supplier = supplierService.get(id);
		Hibernate.initialize(supplier.getProducts());
		model.addAttribute("prods",supplier.getProducts());
		return "user/product/list";
	}
	@RequestMapping("search")
	public String search(ModelMap model,
			@RequestParam("Keywords") String keywords) {
		List<Product> products = productService.search(keywords);
		model.addAttribute("prods", products);
		return "user/product/list";
	}
	@RequestMapping("list-by-special/{id}")
	public String listBySpecial(ModelMap model,
			@PathVariable("id") int id) {
		List<Product> products ;
		switch (id) {
		case 0:
				products= productService.getBestSellers();
				break;
		case 1:
				products= productService.getLatestItems();
				break;
		case 2:
			products= productService.getDiscountItems();
				break;
		case 3:
			products= productService.getSpecialItems();
				break;
		case 4:
			products= productService.getMostViewedItems();
				break;
		default: 
			products= productService.list();
			break;
		}
		model.addAttribute("prods", products);
		return "user/product/list";
	}
	
	@RequestMapping("detail/{id}")
	public String listBySupplier(ModelMap model, 
			@PathVariable("id") Integer id) {
		Product product= productService.get(id);
		product.setViews(product.getViews()+1);
		model.addAttribute("prod",product);
		
		Category category = categoryService.get(product.getCategory().getId());
		model.addAttribute("sameCateItems",category.getProducts());
		
		Supplier supplier = supplierService.get(product.getSupplier().getId());
		model.addAttribute("sameSuppItems",supplier.getProducts());
		return "user/product/detail";
	}
}
