package com.waiyanhtet.query.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.waiyanhtet.query.example.entity.State;
import com.waiyanhtet.query.example.service.DistrictService;
import com.waiyanhtet.query.example.service.StateCriteriaService;

@SpringBootTest
class SpringDataQueryByExampleApplicationTests {
	
	@Autowired
	private DistrictService service;
	
	@Autowired
	private StateCriteriaService stateCriteriaService;

	@Test
	void contextLoads() {
	}
	
	@Disabled
	@Test
	void find_by_state_name_test() {
		var result = service.findByStateNameLikeOrRegion("ya", "Lower");
		
		for(var d : result) {
			System.out.println(d.getName());
		}
		
	}
	
	@Disabled
	@Test
	void find_dto_by_state_name_test() {
		var result = service.findDTOByStateNameLikeOrRegion("ya", "Lower");
		
		for(var d : result) {
			System.out.println(d.getName() + " " + d.getState().getPopulation());
		}
		
	}
	
	@Disabled
	@Test
	void test_criteria() {
		var result = stateCriteriaService.findByRegion("Lower");
		for(State s : result) {
			System.out.println(s.getName() + " " + s.getPopulation());
		}
	}
	
	@Disabled
	@Test
	void find_class_dto_by_state_name_test() {
		var result = service.findDTOByStateNameAndRegion("ya", "Lower");
		
		for(var d : result) {
			System.out.println(d.getName() + " " + d.getState().getPopulation());
		}
	}
		

}
