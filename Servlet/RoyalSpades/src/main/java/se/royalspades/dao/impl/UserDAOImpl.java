package se.royalspades.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.royalspades.dao.UserDAO;
import se.royalspades.model.Company;
import se.royalspades.model.User;

@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	 private SessionFactory sessionFactory;
	  
	@Override
	public void add(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void edit(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public void delete(int userId) {
		sessionFactory.getCurrentSession().delete(getUser(userId));
	}
	
	@Override
	public User getUser(int userId) {
		return (User)sessionFactory.getCurrentSession().get(User.class, userId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAllUsers() {
		return sessionFactory.getCurrentSession().createCriteria(User.class).list();
				//createQuery("from users").list();
	}
}