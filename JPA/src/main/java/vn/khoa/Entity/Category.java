package vn.khoa.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="Category")
@NamedQuery(name="Category.findAll", query="SELECT C FROM Category c")
public class Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CategoryId")
	private int categoryId;
	
	@Column(name="Categorycode")
	private String categorycode;
	
	@Column(name="Categoryname")
	private String categoryname;
	
	@Column(name="Images")
	private String images;
	
	@Column(name="Status")
	private boolean status;

	public Category() {
		super();
	}

	public Category(int categoryId, String categorycode, String categoryname, String images, boolean status) {
		super();
		this.categoryId = categoryId;
		this.categorycode = categorycode;
		this.categoryname = categoryname;
		this.images = images;
		this.status = status;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategorycode() {
		return categorycode;
	}

	public void setCategorycode(String categorycode) {
		this.categorycode = categorycode;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
