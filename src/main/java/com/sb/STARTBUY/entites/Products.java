package com.sb.STARTBUY.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

// persistance yaani tasnaa table fl base de donnees
@Entity
@Table
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idproduct;
	private String title;
	private String description;
	private Number price;
	private String category;
	
	@ManyToMany(mappedBy = "listfavourite")
	Set<Users> listusers = new HashSet<>();
	
	@ManyToMany(mappedBy = "shoppinglist")
	Set<Users> listusersforshopping = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_image", referencedColumnName = "idimage")
    private ImageProduct imageproduct;

    public Products(Long idproduct, String title, String description, Double rating, Number price, String category,
            Set<Users> listusers, Set<Users> listusersforshopping, ImageProduct imageproduct) {
        this.idproduct = idproduct;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.listusers = listusers;
        this.listusersforshopping = listusersforshopping;
        this.imageproduct = imageproduct;
    }
	@Override
	public String toString() {
		return "Products [idproduct=" + idproduct + ", title=" + title + ", description=" + description + ", price=" + price + ", category=" + category + ", listusers=" + listusers
				+ ", listusersforshopping=" + listusersforshopping + ", imageproduct=" + imageproduct + "]";
	}

	public ImageProduct getImageproduct() {
		return imageproduct;
	}

	public void setImageproduct(ImageProduct imageproduct) {
		this.imageproduct = imageproduct;
	}

	public Set<Users> getListusersforshopping() {
		return listusersforshopping;
	}

	public void setListusersforshopping(Set<Users> listusersforshopping) {
		this.listusersforshopping = listusersforshopping;
	}

	public Long getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(Long idproduct) {
		this.idproduct = idproduct;
	}

		public Products() {
		super();
	}

	public Set<Users> getListusers() {
		return listusers;
	}

	public void setListusers(Set<Users> listusers) {
		this.listusers = listusers;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Number getPrice() {
		return price;
	}

	public void setPrice(Number price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
