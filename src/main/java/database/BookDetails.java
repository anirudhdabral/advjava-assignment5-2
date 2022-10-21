package database;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BookDetails {
	@Id
	@Column(length = 50)
	private String bookCode;
	
	@Column(length = 50)
	private String bookName;
	
	@Column(length = 50)
	private String authorName;
	
	private Date addedDate;
	
	
	public BookDetails() {
		super();
	}

	public BookDetails(String bookCode, String bookName, String authorName, Date addedDate) {
		super();
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.authorName = authorName;
		this.addedDate = addedDate;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	
	

}
