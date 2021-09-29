package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.model.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	//need to inject the user service dependency
	@Autowired
	UserService userService;

	@RequestMapping("/")
	public List<User> getUsers() {
		List<User> users = userService.getUsers();
		return users;
	}
	
	
	// This is perfectly valid but lacks flexibility and error handling
	//request mapping is looking for a url at /users/ that has a path variable in it
//	@RequestMapping("/{userId}")
//	public User getUserById(@PathVariable long userId) {  //pass the path variable into the method as a parameter so we can use it
//		User user = userService.findUserById(userId);
//		return user;
//	}
	
	//Better way to do it. Use response Entity so that we can set the body and response status code 
	@RequestMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable long userId) {
		User user = userService.findUserById(userId);
		if(user == null)
			return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	//CREATE
	//adding a new user
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Void> addNewUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
		
		//create the user
		userService.createNewUser(user);
		
		//we are going to set a header to indicate where the new user's details can be found
		HttpHeaders headers = new HttpHeaders();
		
		//will use URI builder to create the path to the new user
		headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
		
		//returns a response entity with the header set and status created
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	
	
	//DELETE operation	
	//URI Path Var and the Variable in the method must have same name
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)  
	public ResponseEntity<User> deleteUser(@PathVariable long userId){  
		User user = userService.deleteUserById(userId);
		if(user == null)
				return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	//UPDATE (request method is PUT)
	//user object will be in the passed in request body as json. 
	//we consume it and use it to update existing user.
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<User> updateUser(@RequestBody User user){
		User udpatedUser = userService.update(user, user.getId());
		if(udpatedUser == null) {
			return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
}
