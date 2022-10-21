package com.web.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.util.BookDetailsUtil;

import database.BookDetails;

@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
@RestController
public class BookDetailsDao {

	@PostMapping("/saveBook")
	public void saveBook(BookDetails book) {
		Transaction transaction = null;
		try (Session session = BookDetailsUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(book);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@PutMapping("/updateBook")
	public void updateBook(BookDetails book) {
		Transaction transaction = null;
		try (Session session = BookDetailsUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(book);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@DeleteMapping("/book/{code}")
	public void deleteBook(String code) {
		Transaction transaction = null;
		try (Session session = BookDetailsUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			BookDetails book = session.get(BookDetails.class, code);
			if (book != null) {
				session.remove(book);
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

	@RequestMapping("/book/{code}")
	public BookDetails getBook(String code) {
		BookDetails book = null;
		try (Session session = BookDetailsUtil.getSessionFactory().openSession()) {
			String query = "from BookDetails where bookCode=:code";
			Query q = session.createQuery(query);
			q.setParameter("code", code);
			List<BookDetails> ls = q.list();
			if (ls.isEmpty()) {
				return null;
			}
			book = (BookDetails) session.get(BookDetails.class, code);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	@RequestMapping("/getAllBook")
	public List<BookDetails> getAllBook() {
		List<BookDetails> listOfBook = null;
		try (Session session = BookDetailsUtil.getSessionFactory().openSession()) {
			String query = "from BookDetails order by bookcode";
			Query q = session.createQuery(query);
			listOfBook = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfBook;
	}

}
