package com.waiyanhtet.query.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waiyanhtet.query.example.entity.District;

public interface DistrictRepo extends JpaRepository<District, Integer> {

}
