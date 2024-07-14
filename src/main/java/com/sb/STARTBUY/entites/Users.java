package com.sb.STARTBUY.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iduser;
	private String firstName;
	private String lastName;
	private String email;
	private String city;
	private String country;
	private Number phone;
	private String password;
	private String role;
	private Number postCode;
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "favourite_list", joinColumns = @JoinColumn(name = "iduser"), inverseJoinColumns = @JoinColumn(name = "idproduct"))
	private Set<Products> listfavourite = new HashSet<>();

	public Set<Products> getListfavourite() {
		return listfavourite;
	}

	public void setListfavourite(Set<Products> listfavourite) {
		this.listfavourite = listfavourite;
	}

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "shopping_list", joinColumns = @JoinColumn(name = "iduser"), inverseJoinColumns = @JoinColumn(name = "idproduct"))
	private Set<Products> shoppinglist = new HashSet<>();

	public Set<Products> getShoppinglist() {
		return shoppinglist;
	}

	public void setShoppinglist(Set<Products> shoppinglist) {
		this.shoppinglist = shoppinglist;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_image", referencedColumnName = "idimage")
	private ImageUser imageuser;

	public ImageUser getImageuser() {
		return imageuser;
	}

	public void setImageuser(ImageUser imageuser) {
		this.imageuser = imageuser;
	}

	public Users() {
		super();
	}

	public Users(Long iduser, String firstName, String lastName, String email, String city, String country,
			Number phone, String password, String role, Number postCode, Set<Products> listfavourite,
			Set<Products> shoppinglist, ImageUser imageuser) {
		super();
		this.iduser = iduser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
		this.country = country;
		this.phone = phone;
		this.password = password;
		this.role = role;
		this.postCode = postCode;
		this.listfavourite = listfavourite;
		this.shoppinglist = shoppinglist;
		this.imageuser = imageuser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public Number getPhone() {
		return phone;
	}

	public void setPhone(Number phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Number getPostCode() {
		return postCode;
	}

	public void setPostCode(Number postCode) {
		this.postCode = postCode;
	}

	public Long getIduser() {
		return iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}

	@Override
	public String toString() {
		return "Users [iduser=" + iduser + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", city=" + city + ", country=" + country + ", phone=" + phone + ", password=" + password + ", role="
				+ role + ", postCode=" + postCode + ", listfavourite=" + listfavourite + ", shoppinglist="
				+ shoppinglist + ", imageuser=" + imageuser + "]";

	}
}
