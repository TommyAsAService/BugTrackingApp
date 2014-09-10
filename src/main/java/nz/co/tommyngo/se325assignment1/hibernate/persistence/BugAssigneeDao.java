package nz.co.tommyngo.se325assignment1.hibernate.persistence;

import java.util.List;

import nz.co.tommyngo.se325assignment1.hibernate.domain.BugAssignee;

public interface BugAssigneeDao {
	public List<BugAssignee> findAll();
	public List<BugAssignee> findAllWithDetail();
	public BugAssignee findAssigneeById(long id);
	
	public void save(BugAssignee project);
	public void delete(BugAssignee project);
}
