package eshop.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import eshop.entity.Product;

@Component("cart")
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {
	@Autowired
	ProductService productService;
	
	Map<Integer, Product> map = new HashMap<Integer, Product>();
	
	/**
	 * Them mat hang vao gio
	 * @param id la ma mat hang can them 
	 */
	public void add(Integer id) {
		Product product = map.get(id);
		if(product != null){
			product.setQuantity(product.getQuantity()+1);
		}
		else{
			product = productService.get(id);
			product.setQuantity(1);
			map.put(id, product);
		}
	}
	
	/**
	 * Xoa mat hang khoi gio
	 * @param id la ma mat hang can xoa 
	 */
	public void remove(Integer id) {
		map.remove(id);
	}
	
	/**
	 * Xoa sach cac mat hang khoi gio 
	 */
	public void clear() {
		map.clear();
	}
	
	/**
	 * Cap nhat so luong moi cho mot mat hang
	 * @param id la ma mat hang can cap nhat
	 * @param newQuantity la so luong moi 
	 */
	public void update(Integer id, Integer newQuantity) {
		Product product = map.get(id);
		product.setQuantity(newQuantity);
	}
	
	/**
	 * Lay tong so luong mat hang trong gio
	 * @return tong so mat hang trong gio 
	 */
	public int getCount() {
		int total = 0;
		for(Product p : getItems()){
			total += p.getQuantity();
		}
		return total;
	}
	
	/**
	 * Lay tong so tien cac mat hang trong gio
	 * @return tong so tien cua gio hang 
	 */
	public double getAmount() {
		double total = 0;
		for(Product p : getItems()){
			total += p.getQuantity()*p.getUnitPrice()*(1-p.getDiscount());
		}
		return total;
	}
	
	/**
	 * Lay tap hop cac mat hang trong gio
	 * @return tap cac mat hang trong gio 
	 */
	public Collection<Product> getItems() {
		return map.values();
	}
	
	public Product getItem(Integer id) {
		return map.get(id);
	}
}
