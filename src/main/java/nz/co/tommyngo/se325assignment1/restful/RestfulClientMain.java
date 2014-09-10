package nz.co.tommyngo.se325assignment1.restful;

import java.sql.Date;

import nz.co.tommyngo.se325assignment1.hibernate.domain.Assignees;
import nz.co.tommyngo.se325assignment1.hibernate.domain.BugAssignee;
import nz.co.tommyngo.se325assignment1.hibernate.domain.BugTracking;
import nz.co.tommyngo.se325assignment1.hibernate.domain.Bugs;

import org.joda.time.DateTime;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

public class RestfulClientMain {
	private static final String URL_GET_ALL_BUGS= "http://localhost:8080/spring-utility/bugs";
	private static final String URL_GET_BUG_BY_ID = "http://localhost:8080/spring-utility/bugs/{id}";
	private static final String URL_CREATE_BUG = "http://localhost:8080/spring-utility/bugs/";
	private static final String URL_UPDATE_BUG = "http://localhost:8080/spring-utility/bugs/{id}";
	private static final String URL_DELETE_BUG = "http://localhost:8080/spring-utility/bugs/{id}";
	
	private static final String URL_GET_ALL_ASSIGNEES= "http://localhost:8080/spring-utility/assignees";
	private static final String URL_GET_ASSIGNEE_BY_ID = "http://localhost:8080/spring-utility/assignees/{id}";
	private static final String URL_CREATE_ASSIGNEE = "http://localhost:8080/spring-utility/assignees/";
	private static final String URL_UPDATE_ASSIGNEE = "http://localhost:8080/spring-utility/assignees/{id}";
	private static final String URL_DELETE_ASSIGNEE = "http://localhost:8080/spring-utility/assignees/{id}";	
	
	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		
		Bugs bugs;
		BugTracking bug;
		
		RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);
		
		// Test retrieve all Bugs
		System.out.println("Testing retrieve all bugs:");
		bugs = restTemplate.getForObject(URL_GET_ALL_BUGS, Bugs.class);
		listBugs(bugs); 
		
		// Test retrieve contact by id
		System.out.println("Testing retrieve a bug by id :");
		bug = restTemplate.getForObject(URL_GET_BUG_BY_ID, BugTracking.class, 1);
		System.out.println(bug);
		System.out.println("");
		
//		// Test update contact
//		bug = restTemplate.getForObject(URL_UPDATE_BUG, BugTracking.class, 1);
//		bug.setReporter("Kim Fung");
//		bug.setAssignee("Tommy");
//		bug.setPriority(1);
//		bug.setProjectId(1);
//		System.out.println("Testing update bug by id :");
//		restTemplate.put(URL_UPDATE_BUG, bug, 1);
//		System.out.println("Bug update successfully: " + bug);
//		System.out.println("");	
		
		// Testing delete contact
		restTemplate.delete(URL_DELETE_BUG, 1);
		System.out.println("Testing delete bug by id :");
        bugs = restTemplate.getForObject(URL_GET_ALL_BUGS, Bugs.class);
        listBugs(bugs);
        
		// Testing create contact
        System.out.println("Testing create bug :");
		BugTracking bugNew = new BugTracking();
		bugNew.setProjectId(1);
		bugNew.setReporter("JJ");
		bugNew.setTitle("Gosling");
		bugNew.setStatus("New");
		bugNew.setCreatedDate(new Date(20140909));
		bugNew = restTemplate.postForObject(URL_CREATE_BUG, bugNew, BugTracking.class);
		System.out.println("Bug created successfully: " + bugNew);       
		
//========================================================================================================		
		
		Assignees assignees;
		BugAssignee assignee;		
		
		// Test retrieve all assignees
		System.out.println("Testing retrieve all assignees:");
		assignees = restTemplate.getForObject(URL_GET_ALL_ASSIGNEES, Assignees.class);
		listAssignees(assignees); 
		
		// Test retrieve contact by id
		System.out.println("Testing retrieve a assignee by id :");
		assignee = restTemplate.getForObject(URL_GET_ASSIGNEE_BY_ID, BugAssignee.class, 1);
		System.out.println(assignee);
		System.out.println("");
		
//		// Test update contact
//		assignee = restTemplate.getForObject(URL_UPDATE_ASSIGNEE, BugAssignee.class, 1);
//		assignee.setAssignee("Kung Fu");;
//		System.out.println("Testing update assignee by id :");
//		restTemplate.put(URL_UPDATE_BUG, assignee, 1);
//		System.out.println("Assignee update successfully: " + assignee);
//		System.out.println("");	
//		
		// Testing delete contact
		restTemplate.delete(URL_DELETE_ASSIGNEE, 2);
		System.out.println("Testing delete assignee by id :");
        assignees = restTemplate.getForObject(URL_GET_ALL_ASSIGNEES, Assignees.class);
        listAssignees(assignees);
        
		// Testing create contact
        System.out.println("Testing create assignee :");
		BugAssignee assigneeNew = new BugAssignee();
		assigneeNew.setAssignee("Matthew Shay");
		assigneeNew.setBugId(2);
		assigneeNew.setPosition("Intern");
		assigneeNew = restTemplate.postForObject(URL_CREATE_ASSIGNEE, assigneeNew, BugAssignee.class);
		System.out.println("Bug created successfully: " + assigneeNew); 

	}
	
	private static void listBugs(Bugs bugs) {
		for (BugTracking bug: bugs.getBugs()) {
			System.out.println(bug);
		}	
		System.out.println("");
	}
	private static void listAssignees(Assignees assignees) {
		for (BugAssignee assignee: assignees.getAssignees()) {
			System.out.println(assignee);
		}	
		System.out.println("");
	}
}
