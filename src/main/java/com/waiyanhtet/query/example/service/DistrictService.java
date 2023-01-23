package com.waiyanhtet.query.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.waiyanhtet.query.example.dto.DistrictClassDTO;
import com.waiyanhtet.query.example.dto.DistrictDTO;
import com.waiyanhtet.query.example.entity.District;
import com.waiyanhtet.query.example.entity.State;
import com.waiyanhtet.query.example.repo.DistrictRepo;

@Service
public class DistrictService {

	@Autowired
	private DistrictRepo repo;

	public List<District> findByStateNameOrRegion(String stateName, String region) {

		var state = new State();
		state.setRegion(region);
		state.setName(stateName);
		var probe = new District();
		probe.setState(state);

		var example = Example.of(probe, ExampleMatcher.matchingAny().withIgnorePaths("id", "state.id", "population")
				.withStringMatcher(StringMatcher.STARTING).withIgnoreCase());
		return repo.findAll(example);
	}

	public List<District> findByStateNameLikeOrRegion(String stateName, String region) {

		var state = new State();
		state.setRegion(region);
		state.setName(stateName);
		var probe = new District();
		probe.setState(state);
		
		var customMatcher = ExampleMatcher.matching()
				.withIgnorePaths("id","population","state.id")
				.withMatcher("state.name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
				.withMatcher("state.region", ExampleMatcher.GenericPropertyMatchers.exact());

		var example = Example.of(probe, customMatcher);
		return repo.findAll(example);
	}
	
	public List<DistrictDTO> findDTOByStateNameLikeOrRegion(String name, String region){
		var state = new State();
		state.setRegion(region);
		state.setName(name);
		var probe = new District();
		probe.setState(state);
		var matcher = ExampleMatcher.matchingAny()
				.withIgnorePaths("id","state.id")
				.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
				.withMatcher("region", ExampleMatcher.GenericPropertyMatchers.ignoreCase());
		
		var example = Example.of(probe, matcher);
		
		return repo.findBy(example, query -> 
					query.as(DistrictDTO.class).sortBy(Sort.by("name")).all());
		
	}
	
	public List<DistrictClassDTO> findDTOByStateNameAndRegion(String name, String region) {
		var state = new State();
		state.setRegion(region);
		state.setName(name);
		var probe = new District();
		probe.setState(state);
		
		var matcher = ExampleMatcher.matching()
						.withMatcher("name", ExampleMatcher.GenericPropertyMatchers
										.startsWith().ignoreCase());
		var example = Example.of(probe, matcher);						
		return repo.findBy(example, query -> query.as(DistrictClassDTO.class).all());
	}

}
