package eshop.admin.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import eshop.entity.Category;
import eshop.entity.Product;
import eshop.entity.Supplier;
import eshop.service.CategoryService;
import eshop.service.ProductService;
import eshop.service.SupplierService;
//product có phần upload file
@Controller
@RequestMapping("admin/product")
public class AdminProductController {
	@Autowired
	ProductService productService;
	@Autowired
	SupplierService supplierService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ServletContext app;
	//tinh duong dan
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("item", new Product());
		return "admin/product/index";
	}
	
	@ModelAttribute("items")
	public List<Product> getProductList() {
		return productService.list();
	}
	@ModelAttribute("citems")
	public List<Category> getCategoryList() {
		return categoryService.list();
	}
	@ModelAttribute("sitems")
	public List<Supplier> getSupplierList() {
		return supplierService.list();
	}
	//đổ vào 2 combobox trong giao diện
	@RequestMapping("insert")
	public String insert(Model model, @ModelAttribute("item") Product product,
			@RequestParam("uplogo") MultipartFile logo) {
		//x
		
		try {
			if (logo.isEmpty()) {
				product.setImage("product.png");
			} else {
				product.setImage(logo.getOriginalFilename());
				String path= app.getRealPath("/images/products/"+product.getImage());
				logo.transferTo(new File(path));
			}
			productService.insert(product);
			model.addAttribute("message", "Them moi thanh cong");
			return "redirect:/admin/product/index.php";
		} 
		catch (Exception e) {
			model.addAttribute("message", "Them moi that bai");
		}
		return "admin/product/index";
	}
	
	@RequestMapping("update")
	public String update(Model model, @ModelAttribute("item") Product product,
			@RequestParam("uplogo") MultipartFile logo) {
		try {
			if (!logo.isEmpty()) {
				product.setImage(logo.getOriginalFilename());
				String path= app.getRealPath("/images/products/"+product.getImage());
				logo.transferTo(new File(path));
			}
			productService.update(product);
			model.addAttribute("message", "Cap nhat thanh cong");
			return "redirect:/admin/product/edit/"+product.getId()+".php";
	} 
		catch (Exception e) {
			model.addAttribute("message", "Cap nhat that bai");
		}
		return "admin/product/index";
	}
	
	@RequestMapping("delete")
	public String delete(Model model, @ModelAttribute("item") Product product) {
		try {
			productService.delete(product);
			model.addAttribute("message", "Xoa thanh cong");
			return "redirect:/admin/product/index.php";
		} 
		catch (Exception e) {
			model.addAttribute("message", "Xoa that bai");
		}
		return "admin/product/index";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Product product = productService.get(id);
		model.addAttribute("item", product);
		return "admin/product/index";
	}
	@RequestMapping("loadpage")
	public String page(ModelMap model,
			@RequestParam(value="pageNo", defaultValue="0") int pageNo,
			@RequestParam(value="pageSize", defaultValue="6") int pageSize
			) {
		List<Product> items=productService.loadpage(pageNo,pageSize);
		model.addAttribute("items",items);
		return "blank/product/gridview";
	}
	
	@ResponseBody
	@RequestMapping("pagecount") //đếm số page
	public String getPageCount(Model model,
			@RequestParam(value="pageSize", defaultValue="6") int pageSize) {
		int rowCount = productService.list().size();
		int pageCount = (int) Math.ceil(1.0*rowCount/pageSize);
		return String.valueOf(pageCount);
	}
}
