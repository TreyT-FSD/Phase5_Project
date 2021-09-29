package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UserDao;
import com.app.model.User;

/**
 * 
 * @author Trey-9900K
 * 
 * @Service Annotation:
 * Can be used with classes and annotations.
 * Behaves the way we use @Component.
 * Helps make the classes more readable.
 * Known as Domain or Business Logic
 * 
 *
 * presumably, we would implement the biz logic here as needed
 *
 */
@Service
public class UserServiceImp implements UserService{
	
	//We auto wire to the UserDao Interface so that we can access the user data (not the actual implementation class) 
	@Autowired
	UserDao userDao;

	@Override
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	public User findUserById(long id) {
		return userDao.findUserById(id);
	}

	@Override
	public void createNewUser(User user) {
		userDao.createNewUser(user);
	}

	@Override
	public User deleteUserById(long id) {
		return userDao.deleteUserById(id);
	}

	@Override
	public User update(User user, long id) {
		return userDao.update(user, id);
	}

}
