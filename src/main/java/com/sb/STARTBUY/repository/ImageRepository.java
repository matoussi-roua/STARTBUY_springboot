package com.sb.STARTBUY.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.STARTBUY.entites.ImageUser;
@Repository
public interface ImageRepository extends JpaRepository<ImageUser,Long> {

}
