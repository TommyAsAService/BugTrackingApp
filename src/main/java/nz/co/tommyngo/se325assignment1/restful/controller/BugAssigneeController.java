package nz.co.tommyngo.se325assignment1.restful.controller;

import javax.servlet.http.HttpServletResponse;

import nz.co.tommyngo.se325assignment1.hibernate.domain.Assignees;
import nz.co.tommyngo.se325assignment1.hibernate.domain.BugAssignee;
import nz.co.tommyngo.se325assignment1.hibernate.persistence.BugAssigneeDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping(value="/assignees")
public class BugAssigneeController {
	@Autowired
	private BugAssigneeDao assigneeDao;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Assignees listAssignee(WebRequest webRequest){
		return new Assignees(assigneeDao.findAll());
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public BugAssignee create(@RequestBody BugAssignee assignee, HttpServletResponse response){
		assigneeDao.save(assignee);
		response.setHeader("Location",  "/assignees/" + assignee.getId());
		return assignee;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public BugAssignee findAssigneeById(@PathVariable Long id){
		return assigneeDao.findAssigneeById(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody BugAssignee assignee, @PathVariable Long id){
		assigneeDao.save(assignee);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id){
		BugAssignee assignee = assigneeDao.findAssigneeById(id);
		assigneeDao.delete(assignee);
	}
}
