package com.sb.STARTBUY.repository;

import java.util.List;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sb.STARTBUY.entites.Products;
import com.sb.STARTBUY.entites.Users;
 //jpa api rest jebha hibernate 
@Repository 
public interface UsersRepository extends JpaRepository<Users, Long> {
	 @Query("SELECT u.listfavourite FROM Users u WHERE u.iduser = ?1 ")
	 Set<Products> favouriteListById(Long iduser);
	 @Query("SELECT u.shoppinglist FROM Users u WHERE u.iduser = ?1 ")
	 Set<Products> shoppingListById(Long iduser);
	 @Query("SELECT u.email FROM Users u WHERE u.email =  ?1")
	 List<Users> findByEmail(String email);
	Users findByEmailAndPassword(String email, String password);
	Boolean existsByEmail(String email);

}
