package nz.co.tommyngo.se325assignment1.hibernate.domain;

import java.io.Serializable;
import java.util.List;

public class Bugs implements Serializable {
	private List<BugTracking> _bugs;
	
	public Bugs(){
		
	}
	
	public Bugs(List<BugTracking> bugs){
		this._bugs = bugs;
	}
	
	public List<BugTracking> getBugs(){
		return this._bugs;
	}
	
	public void setBugs(List<BugTracking> bugs){
		this._bugs = bugs;
	}
}
