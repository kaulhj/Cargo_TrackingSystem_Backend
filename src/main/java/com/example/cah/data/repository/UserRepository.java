package com.example.cah.data.repository;

import com.example.cah.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


//빈으로 등록이 되나요? 자동으로 bean으로 등록이 된다. 어노테이션 생략 가능

@Repository
public interface UserRepository extends JpaRepository<User, Integer> { //pk는 정수

    Optional<User>findByUsername(String username);

    Optional<User>findByUserId(int userId);

    Optional<User> findByEmail(String email);




}
