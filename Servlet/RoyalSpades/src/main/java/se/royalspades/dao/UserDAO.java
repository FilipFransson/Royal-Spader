package se.royalspades.dao;

import java.util.List;

import se.royalspades.model.User;

public interface UserDAO {
	public void add(User user);
	public void edit(User user);
	public void delete(int userId);
	public User getUser(int userId);
	@SuppressWarnings("rawtypes")
	public List getAllUsers();
}
