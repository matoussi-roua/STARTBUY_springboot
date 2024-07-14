package com.sb.STARTBUY.entites;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class Role {
public NameRole getNomRole() {
		return nomRole;
	}
	public void setNomRole(NameRole nomRole) {
		this.nomRole = nomRole;
	}
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long idRole;

@Enumerated(EnumType.STRING)
private NameRole nomRole;

public Role() {
	super();
}
public Role(Long idRole, NameRole nomRole) {
	super();
	this.idRole = idRole;
	this.nomRole = nomRole;
}
@Override
public String toString() {
	return "Role [idRole=" + idRole + ", nomRole=" + nomRole + "]";
}

}
