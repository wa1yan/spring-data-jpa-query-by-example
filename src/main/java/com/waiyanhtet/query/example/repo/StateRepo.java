package com.waiyanhtet.query.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waiyanhtet.query.example.entity.State;

@Repository
public interface StateRepo extends JpaRepository<State, Integer> {

}
