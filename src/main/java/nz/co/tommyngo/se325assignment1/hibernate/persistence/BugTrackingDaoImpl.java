package nz.co.tommyngo.se325assignment1.hibernate.persistence;

import nz.co.tommyngo.se325assignment1.hibernate.domain.BugTracking;

import org.hibernate.SessionFactory;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository("bugDao")
@Transactional
public class BugTrackingDaoImpl implements BugTrackingDao {
	
	private Logger _logger = LoggerFactory.getLogger(BugTrackingDaoImpl.class);
	private SessionFactory _sessionFactory;
	
	@Resource
	public void setSessionFactory(SessionFactory session){
		_sessionFactory = session;
		_logger.debug("SessionFactory class: " + session.getClass().getName());
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<BugTracking> findAll() {
		return _sessionFactory.getCurrentSession().createQuery("from BugTracking b").list();
	}
	public BugTracking findBugById(long id) {
		return (BugTracking) _sessionFactory.getCurrentSession().getNamedQuery("BugTracking.findBugById").setParameter("id", id).uniqueResult();
	}
	public void save(BugTracking bug) {
		_sessionFactory.getCurrentSession().saveOrUpdate(bug);
		_logger.info("Bug saved with id: " + bug.getId());
	}
	public void delete(BugTracking bug) {
		_sessionFactory.getCurrentSession().delete(bug);
		_logger.info("Bug deleted with id: " + bug.getId());

	}

	public List<BugTracking> findAllWithDetail() {
		// TODO Auto-generated method stub
		return _sessionFactory.getCurrentSession().getNamedQuery("BugTracking.findAllWithDetail").list();
	}
}
