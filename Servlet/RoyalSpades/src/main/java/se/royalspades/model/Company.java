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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
 
@Entity
@Table(name = "companies", catalog = "spade_db")
public class Company implements Serializable{

	private static final long serialVersionUID = 1L;
	private int companyId;
	private String name;
	private String orgNumber;
	private String address;
	private String phone;
	private String type;
	private User user;
    private Set<Product> companyProducts = new HashSet<Product>(0);

	public Company(){
	}
	
	public Company(String name, String orgNumber, String address, String phone,
			String type, User user) {
		this.name = name;
		this.orgNumber = orgNumber;
		this.address = address;
		this.phone = phone;
		this.type = type;
		this.user = user;
	}
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return companyId;
	}
	
	public void setId(int companyId) {
		this.companyId = companyId;
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
	
    @Column(name = "type", nullable = false, length = 45)
	public String getType() {
		return type;
	}
    
	public void setType(String type) {
		this.type = type;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public User getUser(){
		return user;
	}
	
	public void setUser(User user){
		this.user = user;
	}

	////////// skip inverse?
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<Product> getCompanyProducts() {
		return companyProducts;
	}

	public void setCompanyProducts(Set<Product> companyProducts) {
		this.companyProducts = companyProducts;
	}
}
