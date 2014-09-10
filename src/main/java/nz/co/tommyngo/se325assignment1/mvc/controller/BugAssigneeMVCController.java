package nz.co.tommyngo.se325assignment1.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import nz.co.tommyngo.se325assignment1.hibernate.domain.BugAssignee;
import nz.co.tommyngo.se325assignment1.hibernate.persistence.BugAssigneeDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/assignee")
public class BugAssigneeMVCController {
	final static Logger _logger = LoggerFactory.getLogger(BugTrackingMVCController.class);
	@Autowired
	private BugAssigneeDao assigneeDao;
	
	/**
	 * Handler method to process URLs that end with "/bugs" and that are invoked by HTTP GET 
	 * methods.
	 * @param uiModel the model supplied the MVC framework. This will be populated with a list of 
	 *        Contact objects.
	 * @return logical name of the view to render the model.
	 */	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model uiModel){
		List<BugAssignee> assignees = assigneeDao.findAllWithDetail();
		uiModel.addAttribute("assignees", assignees);
		return "assignees/list";
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
		BugAssignee assignee = assigneeDao.findAssigneeById(id);
		
		// Add the Contact to the model.
		uiModel.addAttribute("assignee", assignee);
		
		// Return the logical view name that will render the model.
		return "assignee/show";
	}
	/**
	 * Handler method to process URLs ending with /contacts/<id>, that are invoked by HTTP POST
	 * methods, and which are a query parameter named "form". The purpose of this method is to 
	 * update an existing and specified Contact based on form data that is submitted with the POST 
	 * request.
	 * @param contact the Contact object, created by the MCV framework, that is represented by the
	 *        POSTed content.
	 * @param bindingResult stores any errors associated with failed validation constraints. The 
	 *        MVC framework automatically applies validation constraints to the freshly created
	 *        Contact object and populated the BindingResult if errors are detected. 
	 * @param uiModel the model that is supplied by the MVC framework. This method adds status 
	 *        messages to the model that will be rendered in a view. In cases where validation
	 *        constraints have failed, this method also adds the Contact object to the model so that
	 *        its properties (name, DoB etc.) can be displayed and edited.
	 * @param redirectAttributes holds a status message temporarily during a HTTP redirect response.
	 *        When the browser requests the redirected page, the status message is available to be
	 *        rendered as part of the page.
	 * @return logical name of the view to render the model.
	 */
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(@Valid BugAssignee assignee, BindingResult bindingResult, Model uiModel, RedirectAttributes redirectAttributes) {
		_logger.info("Process form POST");
		if(bindingResult.hasErrors()) {
			// Validation constraint(s) have failed.
			
			// Add the Contact that has been POSTed (and edited incorrectly by the user) to the 
			// model.
			uiModel.addAttribute("assignee", assignee);
			
			// Add a Message to the model explaining that the Contact could not be saved due to
			// failed validation constraints.
			Message message = Message.createFailureSaveMessage();
			uiModel.addAttribute("message", message);
			
			// Return the logical name of a view that will redisplay the form, populated with the 
			// Contact object and message.
			return "assignee/update";
		}
		
		_logger.info("Updating assignee: " + assignee);
		// Validation constraints have passed, so create a "successful save" message and save the
		// Contact.
		Message message = Message.createSuccessfulSaveMessage();
		
		assigneeDao.save(assignee);
		
		// Use a RedirectAttribute to temporarily hold the message during the redirect.
		redirectAttributes.addFlashAttribute("message", message);
		
		// Redirect the browser to a page that displays the updated Contact.
		return "redirect:/assignee/" + assignee.getId();
	}
	
	/**
	 * Handler method to process URLs ending with /contacts/<id>, that are invoked by HTTP GET
	 * methods, and which have a query parameter named "form". The purpose of this method is to
	 * retrieve a form that users can use to edit the specified Contact.
	 * @param id the id of the Contact to edit.
	 * @param uiModel the model supplied by the MVC framework. This method will populate the model
	 * with the required Contact so that is can be included in the view.  
	 * @return logical view name.
	 */
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		// Lookup the Contact and add it to the model.
		uiModel.addAttribute("assignee", assigneeDao.findAssigneeById(id));
		
		return "assignee/update";
	}
	
	/**
	 * Similar to handler method update(), but enables new Contacts to be created as opposed to 
	 * existing Contacts being edited.
	 */
	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String create(@Valid BugAssignee assignee, BindingResult bindingResult, Model uiModel, RedirectAttributes redirectAttributes) {
		_logger.info("Creating assignee");
		
		if(bindingResult.hasErrors()) {
			uiModel.addAttribute("assignee", assignee);
			Message message = Message.createFailureSaveMessage();
			uiModel.addAttribute("message", message);
			return "assignee/create";
		}
		
		Message message = Message.createSuccessfulSaveMessage();
		redirectAttributes.addFlashAttribute("message", message);
		
		assigneeDao.save(assignee);
		return "redirect:/assignee/" + assignee.getId();
	}
	
	/**
	 * Similar to handler method updateForm(), but returns a form that allows new Contacts to be
	 * created rather than edited.
	 */
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		BugAssignee assignee = new BugAssignee();
		uiModel.addAttribute("assignee", assignee);
		return "assignee/create";
	}
}
