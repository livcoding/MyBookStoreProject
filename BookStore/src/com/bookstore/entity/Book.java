package com.bookstore.entity;
import java.io.Serializable;
import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.swing.event.TreeSelectionEvent;

/**
 * author: mohd.uvesh
 */
@Entity
@Table(name = "book")
public class Book implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "book_id", unique = true, nullable = false)
	private Integer bookId;
	
	@Column(name = "title", unique = true, nullable = false)
	private String title;
	
	@Column(name = "author", nullable = false)
	private String author;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "isbn", nullable = false)
	private String isbn;
	
	@Column(name = "image", nullable = false)
	private byte[] image;
	
	@Column(name = "price", nullable = false)
	private float price;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "publish_date", nullable = false)
	private Date publishDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update_time", nullable = false, length = 19)
	private Date lastUpdateTime;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	private String base64Image;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
	private Set<Review> reviews = new HashSet<Review>(0);
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);

	public Book() {
	}
	
	public Book(Integer bookId) {
		super();
		this.bookId = bookId;
	}

	public Book(Category category, String title, String author, String description, String isbn, byte[] image,
			float price, Date publishDate, Date lastUpdateTime, Set<Review> reviews, Set<OrderDetail> orderDetails) {
		this.category = category;
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdateTime = lastUpdateTime;
		this.reviews = reviews;
		this.orderDetails = orderDetails;
	}

		public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}


	public Set<Review> getReviews() {
		TreeSet<Review> sortedReviews = new TreeSet<>(new Comparator<Review>() {

			@Override
			public int compare(Review review1, Review review2) {
				return review2.getReviewTime().compareTo(review1.getReviewTime());
			}
			
		});
		
		sortedReviews.addAll(reviews);
		return sortedReviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	@Transient
	public String getBase64Image() {
		this.base64Image = Base64.getEncoder().encodeToString(this.image);
		return this.base64Image;
	}
	
	@Transient
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	
	
	
	@Transient
	public float getAverageRating() {
		float averageRating = 0.0f;
		float sum = 0.0f;
		
		if (reviews.isEmpty()) {
			return 0.0f;
		}
		
		for (Review review : reviews) {
			sum += review.getRating();
		}
		
		averageRating = sum / reviews.size();
		
		return averageRating;
	}
	
	@Transient
	public String getRatingStars() {
		float averageRating = getAverageRating();
		
		return getRatingString(averageRating);
	}
	
	@Transient
	public String getRatingString(float averageRating) {
		String result = "";
		
		int numberOfStarsOn = (int) averageRating;
		
		for (int i = 1; i <= numberOfStarsOn; i++) {
			result += "on,";
		}
		
		int next = numberOfStarsOn + 1;
		
		if (averageRating > numberOfStarsOn) {
			result += "half,";
			next++;
		}
		
		for (int j = next; j <= 5; j++) {
			result += "off,";
		}
		
		return result.substring(0, result.length() - 1);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		return true;
	}

}