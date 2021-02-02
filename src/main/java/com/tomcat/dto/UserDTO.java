package com.tomcat.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer user_Id;

	private String address;

	private Date birthDay;

	private String email;

	private String firstName;

	private String lastName;

	private String phone;
	
	private String username;
	
	private String password;
	
	private String confirmPassword;
	
	private Byte enable;

	private List<BookingDTO> bookings;

	private List<RoleDTO> roles;

	public UserDTO() {
	}

	public Byte getEnable() {
		return enable;
	}

	public void setEnable(Byte enabled) {
		this.enable = enabled;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Integer getUser_Id() {
		return this.user_Id;
	}

	public void setUser_Id(Integer user_Id) {
		this.user_Id = user_Id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDay() {
		return this.birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<BookingDTO> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<BookingDTO> bookings) {
		this.bookings = bookings;
	}

	public BookingDTO addBooking(BookingDTO booking) {
		getBookings().add(booking);
		booking.setUser(this);

		return booking;
	}

	public BookingDTO removeBooking(BookingDTO booking) {
		getBookings().remove(booking);
		booking.setUser(null);

		return booking;
	}

	public List<RoleDTO> getRoles() {
		return this.roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}