package se.royalspades.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "products", catalog = "spade_db")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private double volume;
	private String unit;
	private Company company;
	private Category category;
	//private Set<Company> productsCompany = new HashSet<Company>(0); 
	private Set<GroceryList> groceryLists = new HashSet<GroceryList>(0);

	public Product(String name, double volume, String unit, Company company,
			Category category) {
		super();
		this.name = name;
		this.volume = volume;
		this.unit = unit;
		this.company = company;
		this.category = category;
	}
	
	public Product(){
		
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
	
    @Column(name = "volume")
	public double getVolume() {
		return volume;
	}
    
	public void setVolume(double volume) {
		this.volume = volume;
	}
	
    @Column(name = "unit", length = 45)
	public String getUnit() {
		return unit;
	}
    
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
	public Set<GroceryList> getGroceryLists() {
		return groceryLists;
	}

	public void setGroceryLists(Set<GroceryList> groceryLists) {
		this.groceryLists = groceryLists;
	}

}
