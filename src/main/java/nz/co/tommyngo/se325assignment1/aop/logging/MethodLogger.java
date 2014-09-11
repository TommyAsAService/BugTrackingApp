package nz.co.tommyngo.se325assignment1.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class MethodLogger {
	private Logger _logger = LoggerFactory.getLogger(getClass());
	
	@Before("execution(* nz.co.tommyngo.se325assignment1.hibernate.persistence.BugTrackingDaoImpl.*(..))")
	public void logBeforeAdviceBugTrackingDaoImpl(JoinPoint joinPoint){
		_logger.info("Before execute: " + joinPoint.getSignature().getName());
	}
	@Before("execution(* nz.co.tommyngo.se325assignment1.hibernate.persistence.BugAssigneeDaoImpl.*(..))")
	public void logBeforeAdviceBugAssigneeDaoImpl(JoinPoint joinPoint){
		_logger.info("Before execute: " + joinPoint.getSignature().getName());
	}
	@Before("execution(* nz.co.tommyngo.se325assignment1.hibernate.persistence.BugProjectDaoImpl.*(..))")
	public void logBeforeAdviceBugProjectDaoImpl(JoinPoint joinPoint){
		_logger.info("Before execute: " + joinPoint.getSignature().getName());
	}
	@Before("execution(* nz.co.tommyngo.se325assignment1.mvc.controller.BugProjectMVCControlller.*(..))")
	public void logBeforeAdviceBugProjectMVCControlller(JoinPoint joinPoint){
		_logger.info("Before execute: " + joinPoint.getSignature().getName());
	}
	@Before("execution(* nz.co.tommyngo.se325assignment1.mvc.controller.BugAssigneeMVCController.*(..))")
	public void logBeforeAdviceBugAssigneeMVCController(JoinPoint joinPoint){
		_logger.info("Before execute: " + joinPoint.getSignature().getName());
	}
	@Before("execution(* nz.co.tommyngo.se325assignment1.mvc.controller.BugTrackingMVCController.*(..))")
	public void logBeforeAdviceBugTrackingMVCController(JoinPoint joinPoint){
		_logger.info("Before execute: " + joinPoint.getSignature().getName());
	}
	@Before("execution(* nz.co.tommyngo.se325assignment1.restful.controller.BugAssigneeController.*(..))")
	public void logBeforeAdviceBugAssigneeController(JoinPoint joinPoint){
		_logger.info("Before execute: " + joinPoint.getSignature().getName());
	}
	@Before("execution(* nz.co.tommyngo.se325assignment1.restful.controller.BugProjectController.*(..))")
	public void logBeforeAdviceBugProjectController(JoinPoint joinPoint){
		_logger.info("Before execute: " + joinPoint.getSignature().getName());
	}
	@Before("execution(* nz.co.tommyngo.se325assignment1.restful.controller.BugTrackingController.*(..))")
	public void logBeforeAdviceBugTrackingController(JoinPoint joinPoint){
		_logger.info("Before execute: " + joinPoint.getSignature().getName());
	}
	//========================================AFTER ADVICE==========================================================

	@After("execution(* nz.co.tommyngo.se325assignment1.hibernate.persistence.BugTrackingDaoImpl.*(..))")
	public void logAfterAdviceBugTrackingDaoImpl(JoinPoint joinPoint){
		_logger.info(joinPoint.getSignature().getName() + " executed successfully");
	}
	@After("execution(* nz.co.tommyngo.se325assignment1.hibernate.persistence.BugAssigneeDaoImpl.*(..))")
	public void logAfterAdviceBugAssigneeDaoImpl(JoinPoint joinPoint){
		_logger.info(joinPoint.getSignature().getName() + " executed successfully");
	}
	@After("execution(* nz.co.tommyngo.se325assignment1.hibernate.persistence.BugProjectDaoImpl.*(..))")
	public void logAfterAdviceBugProjectDaoImpl(JoinPoint joinPoint){
		_logger.info(joinPoint.getSignature().getName() + " executed successfully");
	}
	@After("execution(* nz.co.tommyngo.se325assignment1.mvc.controller.BugProjectMVCControlller.*(..))")
	public void logAfterAdviceBugProjectMVCControlller(JoinPoint joinPoint){
		_logger.info(joinPoint.getSignature().getName() + " executed successfully");
	}
	@After("execution(* nz.co.tommyngo.se325assignment1.mvc.controller.BugAssigneeMVCController.*(..))")
	public void logAfterAdviceBugAssigneeMVCController(JoinPoint joinPoint){
		_logger.info(joinPoint.getSignature().getName() + " executed successfully");
	}
	@After("execution(* nz.co.tommyngo.se325assignment1.mvc.controller.BugTrackingMVCController.*(..))")
	public void logAfterAdviceBugTrackingMVCController(JoinPoint joinPoint){
		_logger.info(joinPoint.getSignature().getName() + " executed successfully");
	}
	@After("execution(* nz.co.tommyngo.se325assignment1.restful.controller.BugAssigneeController.*(..))")
	public void logAfterAdviceBugAssigneeController(JoinPoint joinPoint){
		_logger.info(joinPoint.getSignature().getName() + " executed successfully");
	}
	@After("execution(* nz.co.tommyngo.se325assignment1.restful.controller.BugProjectController.*(..))")
	public void logAfterAdviceBugProjectController(JoinPoint joinPoint){
		_logger.info(joinPoint.getSignature().getName() + " executed successfully");
	}
	@After("execution(* nz.co.tommyngo.se325assignment1.restful.controller.BugTrackingController.*(..))")
	public void logAfterAdviceBugTrackingController(JoinPoint joinPoint){
		_logger.info(joinPoint.getSignature().getName() + " executed successfully");
	}
}
