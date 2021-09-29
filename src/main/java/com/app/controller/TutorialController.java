package com.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.TutorialDao;
import com.app.model.Tutorial;
import com.app.service.TutorialService;

@RestController
@RequestMapping("/tutorials")
public class TutorialController {
	
	@Autowired
	TutorialDao tutorialDao;
	
	@Autowired
	TutorialService tutorialSvc;
	
	/** Get All of the tutorials
	 * 
	 * @return
	 */
	@GetMapping("/")
	public ResponseEntity<List<Tutorial>> getTutorials() {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();
			//tutorialDao.findAll();
			tutorialSvc.getAllTutorials().forEach(tutorials::add);  //lambda expression to add all of the existing tutorials in the DAO (or service if we were using a svc) to the temp tutorials list
			
			
			//check for empty
			if(tutorials.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<>(tutorials, HttpStatus.OK);
			}
		} catch (Exception e) {  //any exceptions result in server error
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	/** CREATE
	 * 
	 * receives a tutorial object from the POST body and adds it via the tutorialDao.
	 * 
	 * @param tutorial
	 * @return
	 */
	@PostMapping("/")
	public ResponseEntity<Tutorial> addTutorial(@RequestBody Tutorial tutorial){
		Tutorial t = tutorialSvc.addTutorial(new Tutorial(tutorial.getTitle(), tutorial.getDescription()));
		if(t != null) {
			return new ResponseEntity<>(t, HttpStatus.OK);
		}
		return new ResponseEntity<>(t, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	/**GET by Id
	 * 
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable long id){
		Optional<Tutorial> t = tutorialSvc.findTutorialById(id);
		if(t.isPresent()) {
			return new ResponseEntity<>(t.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	/**
	 * PUT
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Tutorial> updatetutorialById(@PathVariable long id, @RequestBody Tutorial updatedTutorial){
		Tutorial t = tutorialSvc.updateTutorial(id, updatedTutorial);
		if(t != null) {
			return new ResponseEntity<>(t, HttpStatus.OK);
		}
		return new ResponseEntity<>(t, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	/**
	 * Delete
	 */
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id){
		tutorialDao.deleteById(id);
	}
	
	

}
