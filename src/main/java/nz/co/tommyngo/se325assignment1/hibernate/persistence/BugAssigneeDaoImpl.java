package nz.co.tommyngo.se325assignment1.hibernate.persistence;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import nz.co.tommyngo.se325assignment1.hibernate.domain.BugAssignee;
import nz.co.tommyngo.se325assignment1.hibernate.domain.BugProject;

@Repository("assigneeDao")
@Transactional
public class BugAssigneeDaoImpl implements BugAssigneeDao {

private SessionFactory _sessionFactory;
	
	@Resource
	public void setSessionFactory(SessionFactory session){
		_sessionFactory = session;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<BugAssignee> findAll() {
		// TODO Auto-generated method stub
		return _sessionFactory.getCurrentSession().createQuery("from BugAssignee a").list();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public List<BugAssignee> findAllWithDetail() {
		return _sessionFactory.getCurrentSession().getNamedQuery("BugAssignee.findAllWithDetail").list();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public BugAssignee findAssigneeById(long id) {
		// TODO Auto-generated method stub
		return (BugAssignee) _sessionFactory.getCurrentSession().getNamedQuery("BugAssignee.findAssigneeById").setParameter("id", id).uniqueResult();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void save(BugAssignee project) {
		// TODO Auto-generated method stub
		_sessionFactory.getCurrentSession().saveOrUpdate(project);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(BugAssignee project) {
		// TODO Auto-generated method stub
		_sessionFactory.getCurrentSession().delete(project);
	}

}
