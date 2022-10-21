package com.web.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.web.util.LoginDetailsUtil;

import database.LoginDetails;

@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
public class LoginDetailsDao {
	
	public void saveLog(LoginDetails log) {
		Transaction transaction = null;
		try (Session session = LoginDetailsUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(log);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateLog(LoginDetails log) {
		Transaction transaction = null;
		try (Session session = LoginDetailsUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(log);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteLog(String username) {
		Transaction transaction = null;
		try (Session session = LoginDetailsUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			LoginDetails log = session.get(LoginDetails.class, username);
			if (log != null) {
				session.remove(log);
			}
			transaction.commit();
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public LoginDetails getLog(String username) {
		LoginDetails log = null;
		try (Session session = LoginDetailsUtil.getSessionFactory().openSession()) {
			String query = "from LoginDetails where bookCode=:code";
			Query q = session.createQuery(query);
			q.setParameter("code", username);
			List<LoginDetails> ls = q.list();
			if (ls.isEmpty()) {
				return null;
			}
			log = (LoginDetails) session.get(LoginDetails.class, username);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return log;
	}
	
	public boolean isValidLog(String username, String password) {
		try (Session session = LoginDetailsUtil.getSessionFactory().openSession()) {
			
			String query = "from LoginDetails where username=:uname and password=:pass";
			Query q = session.createQuery(query);
			q.setParameter("uname", username);
			q.setParameter("pass", password);
			List<LoginDetails> ls = q.list();
			if (ls.isEmpty()) {
				return false;
			}
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public List<LoginDetails> getAllLog() {
		List<LoginDetails> listOfLog = null;
		try (Session session = LoginDetailsUtil.getSessionFactory().openSession()) {
			String query = "from LoginDetails";
			Query q = session.createQuery(query);
			listOfLog = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfLog;
	}

}
