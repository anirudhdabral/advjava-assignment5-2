package database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthorDetails {
	@Id
	@Column(length = 5)
	private int id;
	
	@Column(length = 30)
	private String name;

	public AuthorDetails() {
		super();
	}

	public AuthorDetails(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
