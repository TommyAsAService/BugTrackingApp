package nz.co.tommyngo.se325assignment1;

import java.util.List;
import nz.co.tommyngo.se325assignment1.hibernate.domain.BugProject;
import nz.co.tommyngo.se325assignment1.hibernate.domain.BugTracking;
import nz.co.tommyngo.se325assignment1.hibernate.domain.BugAssignee;
import nz.co.tommyngo.se325assignment1.hibernate.persistence.BugAssigneeDao;
import nz.co.tommyngo.se325assignment1.hibernate.persistence.BugProjectDao;
import nz.co.tommyngo.se325assignment1.hibernate.persistence.BugTrackingDao;

import org.springframework.context.support.GenericXmlApplicationContext;

public class BugTrackingMain {
	public static void main(String[] args){
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext( );
		ctx.load( "classpath:app-context.xml" );
		ctx.refresh( );
		BugTrackingDao bugDao = ctx.getBean("bugDao", BugTrackingDao.class);
		BugProjectDao projectDao = ctx.getBean("projectDao", BugProjectDao.class);
		BugAssigneeDao assigneeDao = ctx.getBean("assigneeDao", BugAssigneeDao.class);
		
		List<BugTracking> bugs = bugDao.findAllWithDetail();
		for (BugTracking b : bugs){
			System.out.println(b);
			System.out.println(b.getBugAssignee());
			System.out.println(b.getProject());
		}
		List<BugAssignee>assignees = assigneeDao.findAllWithDetail();
		for (BugAssignee a : assignees){
			System.out.println(a);
			System.out.println(a.getBug());
		}
		List<BugProject>projects = projectDao.findAllWithDetail();
		for (BugProject p : projects){
			System.out.println(p);
			for(BugTracking b : p.getBugs()){
				System.out.println(b);
			}
		}
		
		System.out.println(projectDao.findProjectById(1));
		System.out.println(bugDao.findBugById(2));
		System.out.println(assigneeDao.findAssigneeById(3));
		
		
//		BugTracking b = bugDao.findBugById(1);
//		b.setReporter("Shay");
//		bugDao.save(b);
//		System.out.println(bugDao.findBugById(1));
////		List<BugTracking> bugs = bugDao.findAll();
//		for (BugTracking b : bugs){
//			System.out.println(b);
//		}
//		List<BugProject> projects = projectDao.findAll();
//		for (BugProject p : projects){
//			System.out.println(p);
//		}
//		BugTracking bug= bugDao.findBugById(1);
//		System.out.println(bug);
//		bugs = bugDao.findBugByAssignee("Shay");
//		for (BugTracking b : bugs){
//			System.out.println(b);
//		}
//		BugProject project = new BugProject("Application Manager");
//		BugTracking bug1 = new BugTracking(3,"Shay","Application fails","New",5);
//		project.addBug(bug1);
//		bug1 = new BugTracking(3, "Matan","Application has wrong font","New",3);
//		project.addBug(bug1);
//		projectDao.save(project);
//		List<BugProject>projects = projectDao.findAll();
//		for (BugProject p : projects){
//			System.out.println(p);
//		}
//		List<BugAssignee> assignees = assigneeDao.findAll();
//		for (BugAssignee a : assignees){
//			System.out.println(a);
//		}
		
	}	
}
