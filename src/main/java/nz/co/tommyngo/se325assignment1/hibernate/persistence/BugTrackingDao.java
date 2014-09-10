package nz.co.tommyngo.se325assignment1.hibernate.persistence;

import java.util.List;
import java.util.Set;

import nz.co.tommyngo.se325assignment1.hibernate.domain.BugTracking;

public interface BugTrackingDao {
	public List<BugTracking> findAll();
	public List<BugTracking> findAllWithDetail();
	public List<BugTracking> findBugByAssignee(String name);
	public BugTracking findBugById(long i);
	
	public void save(BugTracking bug);
	public void delete(BugTracking bug);
	
}
