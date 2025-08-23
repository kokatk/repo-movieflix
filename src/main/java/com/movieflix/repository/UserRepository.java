package com.movieflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieflix.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
