package nz.co.tommyngo.se325assignment1.hibernate.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name="BUG")
@NamedQueries({@NamedQuery(name="BugTracking.findBugById", 
			   query="select distinct b from BugTracking b where b.id = :id"), 
			   @NamedQuery(name="BugTracking.findAllWithDetail", 
               query="select distinct b from BugTracking b left join fetch b.project p left join fetch b.bugAssignee a")})
public class BugTracking implements Serializable {
	/**
	 * 
	 */
	private static final Logger _logger = LoggerFactory.getLogger(BugTracking.class);
	private long _id;
	private long _projectId;
	private String _reporter;
	private String _title;
	private Date _created_date;
	private int _priority;
	private String _status;
	private String _assignee;
	private BugAssignee _bugAssignee;
	private BugProject _project;
		
	public BugTracking(){
		
	}
	
	public BugTracking(int projectId, String reporter, String title, String status, int priority){
		_projectId = projectId;
		_reporter = reporter;
		_title = title;
		_priority = priority;
		_status = status;				
	}
	public BugTracking(int projectId, String reporter, String title, String status, int priority, Date date){
		_projectId = projectId;
		_reporter = reporter;
		_title = title;
		_priority = priority;
		_status = status;	
		_created_date = date;
	}
	
	public BugTracking(int projectId, String reporter, String title, String status, int priority, String assignee){
		_projectId = projectId;
		_reporter = reporter;
		_title = title;
		_priority = priority;
		_status = status;	
		_assignee = assignee;
	}
	public BugTracking(int projectId, String reporter, String title, String status, int priority, Date date, String assignee){
		_projectId = projectId;
		_reporter = reporter;
		_title = title;
		_priority = priority;
		_status = status;	
		_created_date = date;
		_assignee = assignee;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public long getId(){
		return _id;
	}
	
	public void setId(Long id){
		this._id = id;
	}
	
	@Column(name="PROJECT_ID")
	public long getProjectId(){
		return _projectId;
	}
	
	public void setProjectId(long id){
		this._projectId = id;
	}
	
	@Column(name = "REPORTER")
	public String getReporter(){
		return _reporter;
	}
	
	public void setReporter(String reporter){
		this._reporter = reporter;
	}
	
	@Column(name = "TITLE")
	public String getTitle(){
		return _title;
	}
	
	public void setTitle(String title){
		this._title = title;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE")
	public Date getCreatedDate(){
		return _created_date;
	}
	
	public void setCreatedDate(Date date){
		this._created_date = date;
	}
	
	@Column(name = "PRIORITY")
	public int getPriority(){
		return _priority;
	}
	
	public void setPriority(int priority){
		this._priority = priority;
	}
	
	@Column(name="STATUS")
	public String getStatus(){
		return _status;
	}
	
	public void setStatus(String status){
		this._status = status;
	}
	
	@Column(name= "ASSIGNEE_ID")
	public String getAssignee(){
		return _assignee;
	}
	
	public void setAssignee(String assignee){
		this._assignee = assignee;
	}
	@OneToOne(mappedBy="bug",
			  cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			  targetEntity = BugAssignee.class,
			  fetch=FetchType.EAGER)
	public BugAssignee getBugAssignee(){
		return _bugAssignee;
	}
	
	public void setBugAssignee(BugAssignee assignee){
		this._bugAssignee = assignee;
		
	}
//	@ManyToOne
//	@JoinColumn(name="ASSIGNEE_ID", insertable=false, updatable=false)
//	public BugAssignee getBugAssignee(){
//		return _bugAssignee;
//	}
//	
//	public void setBugAssignee(BugAssignee assignee){
//		this._bugAssignee = assignee;
//	}
//	@ManyToMany(mappedBy="bugs", fetch=FetchType.EAGER)
//	public Set<BugAssignee> getBugAssignees(){
//		return _bugAssignees;
//	}
//	
//	public void setBugAssignees(Set<BugAssignee> assignees){
//		this._bugAssignees = assignees;	
//	}
	
	@ManyToOne
	@JoinColumn(name="PROJECT_ID", insertable=false, updatable=false)
	public BugProject getProject(){
		return _project;
	}
	
	public void setProject(BugProject project){
		this._project = project;
		
	}
	
	public void addBugAssignee(BugAssignee assignee){
		this._bugAssignee = assignee;
		assignee.setBug(this);		
	}
	
	public String toString() {		
		return "Bug - Id: " + _id + ", Reporter: " + _reporter 
				+ ", Title: " + _title + ", Created date: " + _created_date;
	}	
}
