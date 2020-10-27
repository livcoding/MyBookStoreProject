package com.bookstore.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
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

/**
 * author : mohd.uvesh
 * 
 */
@Entity
@Table(name = "book_order")
public class BookOrder implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "order_id", unique = true, nullable = false)
	private Integer orderId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_date", nullable = false)
	private Date orderDate;
	
	@Column(name = "address_line1", nullable = false)
	private String addressLine1;
	
	@Column(name = "address_line2", nullable = false)
	private String addressLine2;
	
	@Column(name = "firstname", nullable = false)
	private String firstName;
	
	@Column(name = "lastname", nullable = false)
	private String lastName;
	
	@Column(name = "phone", nullable = false)
	private String phone;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "state", nullable = false)
	private String state;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "zipcode", nullable = false)
	private String zipcode;
	
	@Column(name = "payment_method", nullable = false)
	private String paymentMethod;
 
	@Column(name = "total", nullable = false)
	private float orderTotal;
	
	@Column(name = "subtotal", nullable = false)
	private float orderSubTotal;
	
	@Column(name = "shipping_fee", nullable = false)
	private float shippingFee;
	
	@Column(name = "tax", nullable = false)
	private float tax;
 
	@Column(name = "status", nullable = false)
	private String orderStatus;
	

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "bookOrder")
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);


	public BookOrder() {
		super();
	}


	public BookOrder(Integer orderId, Customer customer, Date orderDate, String addressLine1, String addressLine2,
			String firstName, String lastName, String phone, String city, String state, String country, String zipcode,
			String paymentMethod, float orderTotal, float orderSubTotal, float shippingFee, float tax,
			String orderStatus, Set<OrderDetail> orderDetails) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
		this.paymentMethod = paymentMethod;
		this.orderTotal = orderTotal;
		this.orderSubTotal = orderSubTotal;
		this.shippingFee = shippingFee;
		this.tax = tax;
		this.orderStatus = orderStatus;
		this.orderDetails = orderDetails;
	}


	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public String getAddressLine1() {
		return addressLine1;
	}


	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	public String getAddressLine2() {
		return addressLine2;
	}


	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public String getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public float getOrderTotal() {
		return orderTotal;
	}


	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}


	public float getOrderSubTotal() {
		return orderSubTotal;
	}


	public void setOrderSubTotal(float orderSubTotal) {
		this.orderSubTotal = orderSubTotal;
	}


	public float getShippingFee() {
		return shippingFee;
	}


	public void setShippingFee(float shippingFee) {
		this.shippingFee = shippingFee;
	}


	public float getTax() {
		return tax;
	}


	public void setTax(float tax) {
		this.tax = tax;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}


	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	
}
