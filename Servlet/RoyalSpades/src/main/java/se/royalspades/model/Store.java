package se.royalspades.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
 
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@Table(name = "stores", catalog = "spade_db")
public class Store implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String orgNumber;
	private String address;
	private String phone;
	private User user;
    private Set<StoreProduct> storeProducts = new HashSet<StoreProduct>(0);
    
	public Store(){
	}
	
	public Store(String name, String orgNumber, String address, String phone, User user) {
		this.name = name;
		this.orgNumber = orgNumber;
		this.address = address;
		this.phone = phone;
		this.user = user;
	}
	
	public Store(String name, String orgNumber, String address, String phone, User user, Set<StoreProduct> storeProducts) {
		this.name = name;
		this.orgNumber = orgNumber;
		this.address = address;
		this.phone = phone;
		this.user = user;
		this.storeProducts = storeProducts;
	}
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int storeId) {
		this.id = storeId;
	}
	
    @Column(name = "name", length = 45)
	public String getName() {
		return name;
	}
    
	public void setName(String name) {
		this.name = name;
	}
	
    @Column(name = "organisation_number", length = 45)
	public String getOrgNumber() {
		return orgNumber;
	}
    
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	
    @Column(name = "address", length = 45)
	public String getAddress() {
		return address;
	}
    
	public void setAddress(String address) {
		this.address = address;
	}
	
    @Column(name = "phone", length = 45)
	public String getPhone() {
		return phone;
	}
    
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public User getUser(){
		return user;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	@JsonIgnoreProperties(value = { "storeProduct", "storeProducts", "store" })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.store")
	public Set<StoreProduct> getStoreProduct() {
		return storeProducts;
	}

	public void setStoreProduct(Set<StoreProduct> storeProducts) {
		this.storeProducts = storeProducts;
	}
	
}
