package com.sb.STARTBUY.entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class ImageUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idimage;
	private String titleimage;
	//@Column(length = 420000)
	private byte[] tailleimage;
	private String typeimage;
	
	@OneToOne(mappedBy = "imageuser")
	@JsonIgnore
	private Users user;
	
	public ImageUser(String title, String typeimage,byte[] tailleimage) {
		super();
		this.titleimage = title;
		this.tailleimage = tailleimage;
		this.typeimage=typeimage;
	}

	public ImageUser() {
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	
	
	
}
