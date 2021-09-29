package com.app.dao;

import java.util.List;

import com.app.model.User;

public interface UserDao {
	
	public List<User> getUsers();
	public User findUserById(long id);
	public void createNewUser(User user);
	public User deleteUserById(long id);  //we are returning the user we are deleting
	public User update(User user, long id);

}
