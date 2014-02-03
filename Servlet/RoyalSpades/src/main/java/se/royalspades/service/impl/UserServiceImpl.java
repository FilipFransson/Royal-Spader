package se.royalspades.service.impl;

import java.util.List;

//import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.royalspades.dao.UserDAO;
import se.royalspades.model.User;
import se.royalspades.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Transactional
	public void add(User user) {
		userDAO.add(user);
	}

	@Transactional
	public void edit(User user) {
		userDAO.edit(user);
	}

	@Transactional
	public void delete(int userId) {
		userDAO.delete(userId);
	}

	@Transactional
	public User getUser(int userId) {
		return userDAO.getUser(userId);
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List getAllUsers() {
		return userDAO.getAllUsers();
	}

}
