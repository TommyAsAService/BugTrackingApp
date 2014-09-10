package nz.co.tommyngo.se325assignment1.mvc.controller;

import java.util.List;

import nz.co.tommyngo.se325assignment1.hibernate.domain.BugTracking;
import nz.co.tommyngo.se325assignment1.hibernate.domain.Bugs;
import nz.co.tommyngo.se325assignment1.hibernate.persistence.BugTrackingDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/bug")
public class BugTrackingMVCController {
	
	final static Logger _logger = LoggerFactory.getLogger(BugTrackingMVCController.class);
	@Autowired
	private BugTrackingDao bugDao;
	
	/**
	 * Handler method to process URLs that end with "/bugs" and that are invoked by HTTP GET 
	 * methods.
	 * @param uiModel the model supplied the MVC framework. This will be populated with a list of 
	 *        Contact objects.
	 * @return logical name of the view to render the model.
	 */	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model uiModel){
		List<BugTracking> bugs = bugDao.findAll();
		uiModel.addAttribute("bugs", bugs);
		return "bugs/list";
	}
	
	/**
	 * Handler method to process URLs ending with /bugs/<id> and that are invoked by HTTP GET 
	 * methods. This method finds a Bug with the specified id and adds it to the model.
	 * @param id the unique id of the Bug required.
	 * @param uiModel the model that is supplied by the MVC framework and which will be used to 
	 *        store the Contact.
	 * @return logical name of the view to render the model.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		// Lookup the Contact.
		BugTracking bug = bugDao.findBugById(id);
		
		// Add the Contact to the model.
		uiModel.addAttribute("bug", bug);
		
		// Return the logical view name that will render the model.
		return "bug/show";
	}
}
