package nz.co.tommyngo.se325assignment1.restful.controller;

import javax.servlet.http.HttpServletResponse;

import nz.co.tommyngo.se325assignment1.hibernate.domain.BugProject;
import nz.co.tommyngo.se325assignment1.hibernate.domain.Projects;
import nz.co.tommyngo.se325assignment1.hibernate.persistence.BugProjectDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping(value="/projects")
public class BugProjectController {
	@Autowired
	private BugProjectDao projectDao;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Projects listProject(WebRequest webRequest){
		return new Projects(projectDao.findAllWithDetail());
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public BugProject create(@RequestBody BugProject project, HttpServletResponse response){
		projectDao.save(project);
		response.setHeader("Location",  "/projects/" + project.getId());
		return project;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public BugProject findProjectById(@PathVariable Long id){
		return projectDao.findProjectById(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody BugProject project, @PathVariable Long id){
		projectDao.save(project);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id){
		BugProject project = projectDao.findProjectById(id);
		projectDao.delete(project);
	}
}
