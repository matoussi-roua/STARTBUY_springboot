package com.sb.STARTBUY.entites;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageProduct implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long idimage;
	private String titleimage;
	//@Column(length = 420000) 
	@Lob
	@Column(length = 1000000000)//(columnDefinition = "LONGBLOB")
	private byte[] tailleimage;
	private String typeimage;
	
//	@OneToOne(mappedBy = "imageproduct")
//	@JsonIgnore
//	private Products product;
	
	public ImageProduct(String title, String typeimage,byte[] tailleimage) {
		super();
		this.titleimage = title;
		this.tailleimage = tailleimage;
		this.typeimage=typeimage;
	}

	public ImageProduct() {
	}

	public Long getIdimage() {
		return idimage;
	}

	public void setIdimage(Long idimage) {
		this.idimage = idimage;
	}

	public String getTitleimage() {
		return titleimage;
	}

	public void setTitleimage(String titleimage) {
		this.titleimage = titleimage;
	}

	public byte[] getTailleimage() {
		return tailleimage;
	}

	public void setTailleimage(byte[] tailleimage) {
		this.tailleimage = tailleimage;
	}

	public String getTypeimage() {
		return typeimage;
	}

	public void setTypeimage(String typeimage) {
		this.typeimage = typeimage;
	}

//	public Products getProduct() {
//		return product;
//	}
//
//	public void setProduct(Products product) {
//		this.product = product;
//	}
//
//	@Override
//	public String toString() {
//		return "ImageProduct [idimage=" + idimage + ", titleimage=" + titleimage + ", tailleimage="
//				+ Arrays.toString(tailleimage) + ", typeimage=" + typeimage + "]";
//	}
	
	
}
