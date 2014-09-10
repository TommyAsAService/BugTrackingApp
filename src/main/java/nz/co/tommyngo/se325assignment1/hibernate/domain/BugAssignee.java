package nz.co.tommyngo.se325assignment1.hibernate.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="BUG_ASSIGNEE")
@NamedQueries({@NamedQuery (name="BugAssignee.findAssigneeById", query="select distinct a from BugAssignee a where a.id=:id"),
			   @NamedQuery (name="BugAssignee.findAllWithDetail", query="select distinct a from BugAssignee a left join fetch a.bug b")
			 })

public class BugAssignee implements Serializable {

	private long _id;
	private String _position;
	private BugTracking _bug;
	private long _bugId;
	private String _assignee;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public long getId(){
		return _id;
	}
	
	public void setId(long id){
		this._id = id;
	}
	
	@Column(name="BUG_ID")
	public long getBugId(){
		return _bugId;
	}
	
	public void setBugId(long id){
		this._bugId = id;
	}
	
	@Column(name = "POSITION_TITLE")
	public String getPosition(){
		return _position;
	}
	
	public void setPosition(String position){
		this._position = position;
	}
	
	@Column(name="ASSIGNEE_ID")
	public String getAssignee(){
		return _assignee;
	}
	
	public void setAssignee(String assignee){
		this._assignee = assignee;
	}

//	@ManyToMany
//	@JoinTable(name="BUG",
//			   joinColumns=@JoinColumn(name="BUG_ID"),
//			   inverseJoinColumns=@JoinColumn(name="ASSIGNEE_ID"))
//	public Set<BugTracking> getBugs(){
//		return _bugs;
//	}
//	
//	public void setBugs(Set<BugTracking> bugs){
//		this._bugs = bugs;
//	}
	@OneToOne
	@JoinColumn(name="BUG_ID", nullable = true, insertable=false, updatable=false)
	public BugTracking getBug(){
		return _bug;
	}
	
	public void setBug(BugTracking bug){
		this._bug = bug;
	}
	
//	@OneToMany(mappedBy="bugAssignee",
//			   cascade=CascadeType.ALL,
//			   orphanRemoval=true,
//			   targetEntity=BugTracking.class,
//			   fetch=FetchType.EAGER)
//	public Set<BugTracking> getBugs(){
//		return _bugs;
//	}
//	
//	public void setBugs(Set<BugTracking> bugs){
//		this._bugs = bugs;
//	}
	public String toString(){
		return "Assignee ID: " + _id + " Name: " + _assignee + " Bug ID: " + _bugId + " Title: " + _position;
	}
}
