package nz.co.tommyngo.se325assignment1.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import nz.co.tommyngo.se325assignment1.hibernate.domain.BugProject;
import nz.co.tommyngo.se325assignment1.hibernate.persistence.BugProjectDao;

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
@RequestMapping(value="/project")

public class BugProjectMVCController {
	final static Logger _logger = LoggerFactory.getLogger(BugProjectMVCController.class);
	@Autowired
	private BugProjectDao projectDao;
	
	/**
	 * Handler method to process URLs that end with "/projects" and that are invoked by HTTP GET 
	 * methods.
	 * @param uiModel the model supplied the MVC framework. This will be populated with a list of 
	 *        project objects.
	 * @return logical name of the view to render the model.
	 */	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model uiModel){
		_logger.info("Process form GET list");
		List<BugProject> projects = projectDao.findAll();
		uiModel.addAttribute("projects", projects);
		_logger.info("Process form GET list successful");
		return "projects/list";
	}
	
	/**
	 * Handler method to process URLs ending with /projects/<id> and that are invoked by HTTP GET 
	 * methods. This method finds a project with the specified id and adds it to the model.
	 * @param id the unique id of the project required.
	 * @param uiModel the model that is supplied by the MVC framework and which will be used to 
	 *        store the project.
	 * @return logical name of the view to render the model.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		// Lookup the project.
		_logger.info("Process form GET show");
		BugProject project = projectDao.findProjectById(id);
		
		// Add the project to the model.
		uiModel.addAttribute("project", project);
		_logger.info("Process form GET show successful");
		// Return the logical view name that will render the model.
		return "project/show";
	}
	/**
	 * Handler method to process URLs ending with /projects/<id>, that are invoked by HTTP POST
	 * methods, and which are a query parameter named "form". The purpose of this method is to 
	 * update an existing and specified project based on form data that is submitted with the POST 
	 * request.
	 * @param project the project object, created by the MCV framework, that is represented by the
	 *        POSTed content.
	 * @param bindingResult stores any errors associated with failed validation constraints. The 
	 *        MVC framework automatically applies validation constraints to the freshly created
	 *        project object and populated the BindingResult if errors are detected. 
	 * @param uiModel the model that is supplied by the MVC framework. This method adds status 
	 *        messages to the model that will be rendered in a view. In cases where validation
	 *        constraints have failed, this method also adds the project object to the model so that
	 *        its properties (name, DoB etc.) can be displayed and edited.
	 * @param redirectAttributes holds a status message temporarily during a HTTP redirect response.
	 *        When the browser requests the redirected page, the status message is available to be
	 *        rendered as part of the page.
	 * @return logical name of the view to render the model.
	 */
	
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(@Valid BugProject project, BindingResult bindingResult, Model uiModel, RedirectAttributes redirectAttributes) {
		_logger.info("Process form POST");
		if(bindingResult.hasErrors()) {
			// Validation constraint(s) have failed.
			
			// Add the project that has been POSTed (and edited incorrectly by the user) to the 
			// model.
			uiModel.addAttribute("project", project);
			
			// Add a Message to the model explaining that the project could not be saved due to
			// failed validation constraints.
			Message message = Message.createFailureSaveMessage();
			uiModel.addAttribute("message", message);
			
			// Return the logical name of a view that will redisplay the form, populated with the 
			// project object and message.
			return "project/update";
		}
		
		_logger.info("Updating project: " + project);
		// Validation constraints have passed, so create a "successful save" message and save the
		// project.
		Message message = Message.createSuccessfulSaveMessage();
		
		projectDao.save(project);
		
		// Use a RedirectAttribute to temporarily hold the message during the redirect.
		redirectAttributes.addFlashAttribute("message", message);
		
		// Redirect the browser to a page that displays the updated project.
		return "redirect:/project/" + project.getId();
	}
	
	/**
	 * Handler method to process URLs ending with /projects/<id>, that are invoked by HTTP GET
	 * methods, and which have a query parameter named "form". The purpose of this method is to
	 * retrieve a form that users can use to edit the specified project.
	 * @param id the id of the project to edit.
	 * @param uiModel the model supplied by the MVC framework. This method will populate the model
	 * with the required project so that is can be included in the view.  
	 * @return logical view name.
	 */
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		// Lookup the project and add it to the model.
		uiModel.addAttribute("project", projectDao.findProjectById(id));
		
		return "project/update";
	}
	
	/**
	 * Similar to handler method update(), but enables new projects to be created as opposed to 
	 * existing projects being edited.
	 */
	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String create(@Valid BugProject project, BindingResult bindingResult, Model uiModel, RedirectAttributes redirectAttributes) {
		_logger.info("Creating project");
		if(bindingResult.hasErrors()) {
			uiModel.addAttribute("project", project);
			Message message = Message.createFailureSaveMessage();
			uiModel.addAttribute("message", message);
			return "project/create";
		}
		
		Message message = Message.createSuccessfulSaveMessage();
		redirectAttributes.addFlashAttribute("message", message);
		
		projectDao.save(project);
		return "redirect:/project/" + project.getId();
	}
	
	/**
	 * Similar to handler method updateForm(), but returns a form that allows new projects to be
	 * created rather than edited.
	 */
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		BugProject project = new BugProject();
		uiModel.addAttribute("project",project);
		return "project/create";
	}
}
