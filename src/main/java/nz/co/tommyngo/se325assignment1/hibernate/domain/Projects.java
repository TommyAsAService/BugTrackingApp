package nz.co.tommyngo.se325assignment1.hibernate.domain;

import java.io.Serializable;
import java.util.List;

public class Projects implements Serializable {
	private List<BugProject> _projects;
	
	public Projects(){
		
	}
	
	public Projects(List<BugProject> projects){
		this._projects = projects;
	}
	
	public List<BugProject> getProjects(){
		return this._projects;
	}
	
	public void setProjects(List<BugProject> projects){
		this._projects = projects;
	}
}
