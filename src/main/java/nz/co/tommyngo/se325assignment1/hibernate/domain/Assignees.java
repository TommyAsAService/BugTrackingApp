package nz.co.tommyngo.se325assignment1.hibernate.domain;

import java.io.Serializable;
import java.util.List;

public class Assignees implements Serializable{
	private List<BugAssignee> _assignees;
	public Assignees(){
		
	}
	public Assignees(List<BugAssignee> assignees){
		this._assignees = assignees;
	}
	public List<BugAssignee> getAssignees(){
		return _assignees;
	}
	public void setAssignees(List<BugAssignee>assignees){
		this._assignees = assignees;
	}
	
}
