package nz.co.tommyngo.se325assignment1.hibernate.persistence;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nz.co.tommyngo.se325assignment1.hibernate.domain.BugProject;
import nz.co.tommyngo.se325assignment1.hibernate.domain.BugTracking;
@Repository("projectDao")
@Transactional
public class BugProjectDaoImpl implements BugProjectDao{

	private SessionFactory _sessionFactory;
	
	@Resource
	public void setSessionFactory(SessionFactory session){
		_sessionFactory = session;
	}
	
	@Transactional(readOnly=true)
	public List<BugProject> findAll() {
		return _sessionFactory.getCurrentSession().createQuery("from BugProject p").list();
	}

	@Override
	public List<BugProject> findAllWithDetail() {
		return _sessionFactory.getCurrentSession().getNamedQuery("BugProject.findAllWithDetail").list();
	}

	@Override
	public BugProject findProjectById(long id) {
		// TODO Auto-generated method stub
		return (BugProject) _sessionFactory.getCurrentSession().getNamedQuery("BugProject.findProjectById").setParameter("id", id).uniqueResult();
	}

	@Override
	public void save(BugProject project) {
		// TODO Auto-generated method stub
		_sessionFactory.getCurrentSession().saveOrUpdate(project);
	}

	@Override
	public void delete(BugProject project) {
		// TODO Auto-generated method stub
		_sessionFactory.getCurrentSession().delete(project);
	}

}
