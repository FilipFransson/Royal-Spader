package se.royalspades.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "grocery_lists", catalog = "spade_db")
public class GroceryList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private User listOwner;
	private Set<Product> products = new HashSet<Product>(0); 

	public GroceryList(){
		
	}
	
	public GroceryList(String name, User listOwner) {
		super();
		this.name = name;
		this.listOwner = listOwner;
	}
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
    @Column(name = "name", length = 45)
	public String getName() {
		return name;
	}
    
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public User getListOwner() {
		return listOwner;
	}

	public void setListOwner(User listOwner) {
		this.listOwner = listOwner;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    @JoinTable(name = "grocery_lists_has_products", catalog = "spade_db", joinColumns = {
	        @JoinColumn(name = "grocery_lists_id", nullable = false, updatable = false) },
	            inverseJoinColumns = { @JoinColumn(name = "products_id",
	            nullable = false, updatable = false) })
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
