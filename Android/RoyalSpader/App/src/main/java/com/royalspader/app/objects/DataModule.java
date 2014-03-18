package com.royalspader.app.objects;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artwar on 2014-02-05.
 * On projekt App
 */
public class DataModule {

    public volatile static List<Bag> bags = new ArrayList<Bag>();
	public volatile static List<Product> products = new ArrayList<Product>();
	public volatile static List<Store> stores = new ArrayList<Store>();
	public volatile static List<Brand> brands = new ArrayList<Brand>();
	public volatile static List<Category> categories = new ArrayList<Category>();

	public static void instantiate(){
        bags = new ArrayList<Bag>();
        products = new ArrayList<Product>();
		stores = new ArrayList<Store>();
		brands = new ArrayList<Brand>();
		categories = new ArrayList<Category>();
	}

    public static Bag getBag(int index) {
        return bags.get(index);
    }
    public static Category getCategory(int categoryToken) {
        for(Category category : categories){
            if(category.tokenId == categoryToken){
                return category;
            }
        }
        return null;
    }
	public static Store getStore(int storeToken) {
		for(Store store: stores){
			if(store.tokenId == storeToken){
				return store;
			}
		}
		return null;
	}
	public static Brand getBrand (int brandToken) {
		for(Brand brand : brands){
			if(brand.tokenId == brandToken){
				return brand;
			}
		}
		return null;
	}
	public static Product getProduct(int productToken) {
		for(Product product : products){
			if(product.tokenId == productToken){
				return product;
			}
		}
		return null;
	}
}
