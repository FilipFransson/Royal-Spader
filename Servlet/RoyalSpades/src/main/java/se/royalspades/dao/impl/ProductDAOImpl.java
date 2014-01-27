package se.royalspades.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.royalspades.dao.ProductDAO;
import se.royalspades.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	 private SessionFactory sessionFactory;
	
	@Override
	public void add(Product product) {
		sessionFactory.getCurrentSession().save(product);
	}

	@Override
	public void edit(Product product) {
		sessionFactory.getCurrentSession().update(product);
	}

	@Override
	public void delete(int productId) {
		sessionFactory.getCurrentSession().delete(getProduct(productId));
	}

	@Override
	public Product getProduct(int productId) {
		return (Product)sessionFactory.getCurrentSession().get(Product.class, productId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAllProducts() {
		return sessionFactory.getCurrentSession().createQuery("from products").list();
	}

}
