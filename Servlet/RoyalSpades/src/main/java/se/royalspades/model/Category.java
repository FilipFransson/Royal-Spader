package se.royalspades.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories", catalog = "spade_db")
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	
	public Category(){
		
	}
	
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

}
