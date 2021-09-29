package com.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.model.User;

/***
 * 
 * @author Trey-9900K
 * 
 * @Repository annotation:
 * 
 * This is used with DAO interfaces.
 * Helps the internal implementation in spring.
 *
 */
@Repository
public class UserDaoImp implements UserDao {
	
	//list of users will be used as static data
	private static List<User> userList = new ArrayList<User>();
	
	//constructor to create our static data
	public UserDaoImp() {
		userList.add(new User(101, "Tim", "Tebo", "TimTebo@gmail.com"));
		userList.add(new User(102, "Jack", "Bower", "JackBower@gmail.com"));
		userList.add(new User(103, "Bob", "Builder", "BobBuilder@gmail.com"));
		userList.add(new User(104, "Rick", "Morty", "RickMorty@gmail.com"));
	}

	@Override
	public List<User> getUsers() {
		return userList;
	}

	@Override
	public User findUserById(long id) {
		for(User user : userList) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public void createNewUser(User user) {
		userList.add(user);
	}

	@Override
	public User deleteUserById(long id) {
		User user = findUserById(id);
		if(user != null) {
			userList.remove(user);
		}
		return user;
	}

	@Override
	public User update(User user, long id) {
		
		//find existing user
		User usr = findUserById(id);
		
		//make sure not null
		if(usr != null) {
			
			//Update via setters/getters the existing user with the passed in user's data
			usr.setEmail(user.getEmail());
			usr.setFirstName(user.getFirstName());
			usr.setId(user.getId());
			usr.setLastName(user.getLastName());
			return usr;
		}
		return null;
	}
}
