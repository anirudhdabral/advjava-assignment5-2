package com.web.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.util.AuthorDetailsUtil;

import database.AuthorDetails;

@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
@RestController
public class AuthorDetailsDao {
	
	@PostMapping("/saveAuthor")
	public void saveAuthor(AuthorDetails author) {
		Transaction transaction = null;
		try (Session session = AuthorDetailsUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(author);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@PostMapping("/updateAuthor")
	public void updateAuthor(AuthorDetails author) {
		Transaction transaction = null;
		try (Session session = AuthorDetailsUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(author);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@PostMapping("/deleteAuthor")
	public void deleteAuthor(String code) {
		Transaction transaction = null;
		try (Session session = AuthorDetailsUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			AuthorDetails author = session.get(AuthorDetails.class, code);
			if (author != null) {
				session.remove(author);
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

	@PostMapping("/getAuthor")
	public AuthorDetails getAuthor(int aid) {
		AuthorDetails author = null;
		try (Session session = AuthorDetailsUtil.getSessionFactory().openSession()) {
			String query = "from AuthorDetails where id=:aid";
			Query q = session.createQuery(query);
			q.setParameter("code", aid);
			List<AuthorDetails> ls = q.list();
			if (ls.isEmpty()) {
				return null;
			}
			author = (AuthorDetails) session.get(AuthorDetails.class, aid);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return author;
	}

	@PostMapping("/getAllAuthor")
	public List<AuthorDetails> getAllAuthor() {
		List<AuthorDetails> listOfauthor = null;
		try (Session session = AuthorDetailsUtil.getSessionFactory().openSession()) {
			String query = "from AuthorDetails order by id";
			Query q = session.createQuery(query);
			listOfauthor = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfauthor;
	}

}
