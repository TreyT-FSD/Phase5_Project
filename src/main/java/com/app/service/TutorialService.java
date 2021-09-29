package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.TutorialDao;
import com.app.model.Tutorial;

@Service
public class TutorialService {
	
	@Autowired
	TutorialDao tutorialDao;
	
	public List<Tutorial> getAllTutorials(){
		return tutorialDao.findAll();
	}
	
	public Tutorial addTutorial(Tutorial tutorial) {
		//return tutorialDao.save(tutorial);
		return tutorialDao.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription()));
		
	}
	
	public Optional<Tutorial> findTutorialById(long id) {
		return tutorialDao.findById(id);
	}
	
	//PUT (replace the existing or save the new tutorial)
	public Tutorial updateTutorial(long id, Tutorial newTutorial) {
		//epic one liner using find and map and handles case were its not found
		return tutorialDao.findById(id)
			.map(tutorial -> {
			tutorial.setTitle(newTutorial.getTitle());
			tutorial.setDescription(newTutorial.getDescription());
			return tutorialDao.save(tutorial);
		})
		.orElseGet(() -> {
			newTutorial.setId(id);
			return tutorialDao.save(newTutorial);
		});
	}
	
	//DELETE
	
}
