package nz.co.tommyngo.se325assignment1.hibernate.persistence;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import nz.co.tommyngo.se325assignment1.hibernate.domain.BugProject;
import nz.co.tommyngo.se325assignment1.hibernate.domain.BugTracking;
@Repository("projectDao")
@Transactional
public class BugProjectDaoImpl implements BugProjectDao{

	private Logger _logger = LoggerFactory.getLogger(BugProjectDaoImpl.class);
	private SessionFactory _sessionFactory;
	
	@Resource
	public void setSessionFactory(SessionFactory session){
		_sessionFactory = session;
		_logger.debug("SessionFactory class: " + session.getClass().getName());
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<BugProject> findAll() {
		return _sessionFactory.getCurrentSession().createQuery("from BugProject p").list();
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public List<BugProject> findAllWithDetail() {
		return _sessionFactory.getCurrentSession().getNamedQuery("BugProject.findAllWithDetail").list();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public BugProject findProjectById(long id) {
		// TODO Auto-generated method stub
		return (BugProject) _sessionFactory.getCurrentSession().getNamedQuery("BugProject.findProjectById").setParameter("id", id).uniqueResult();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void save(BugProject project) {
		// TODO Auto-generated method stub
		_sessionFactory.getCurrentSession().saveOrUpdate(project);
		_logger.info("Project saved with id: " + project.getId());
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(BugProject project) {
		// TODO Auto-generated method stub
		_sessionFactory.getCurrentSession().delete(project);
		_logger.info("Project deleted with id: " + project.getId());
	}

}
