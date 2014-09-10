package nz.co.tommyngo.se325assignment1.hibernate.persistence;

import java.util.List;

import nz.co.tommyngo.se325assignment1.hibernate.domain.BugProject;

public interface BugProjectDao {
	public List<BugProject> findAll();
	public List<BugProject> findAllWithDetail();
	public BugProject findProjectById(long id);
	
	public void save(BugProject project);
	public void delete(BugProject project);
}
