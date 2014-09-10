package nz.co.tommyngo.se325assignment1.restful.controller;


import javax.servlet.http.HttpServletResponse;

import nz.co.tommyngo.se325assignment1.hibernate.domain.BugTracking;
import nz.co.tommyngo.se325assignment1.hibernate.domain.Bugs;
import nz.co.tommyngo.se325assignment1.hibernate.persistence.BugTrackingDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;


@Controller
@RequestMapping(value="/bugs")
public class BugTrackingController {

	final Logger logger = LoggerFactory.getLogger(BugTrackingController.class);
	@Autowired
	private BugTrackingDao bugDao;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Bugs listBug(WebRequest webRequest){
		return new Bugs(bugDao.findAll());
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public BugTracking create(@RequestBody BugTracking bug, HttpServletResponse response){
		logger.info("Creating bug: " + bug);
		bugDao.save(bug);
		logger.info("Bug created successfully with info: " + bug);
		response.setHeader("Location",  "/bugs/" + bug.getId());
		return bug;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public BugTracking findBugById(@PathVariable Long id){
		return bugDao.findBugById(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody BugTracking bug, @PathVariable Long id){
		logger.info("Updating bug: " + bug);
		bugDao.save(bug);
		logger.info("Bug updated successfully with info: " + bug);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id){
		logger.info("Deleting bug with id: " + id);
		BugTracking bug = bugDao.findBugById(id);
		bugDao.delete(bug);
		logger.info("Bug deleted successfully");
	}
}
