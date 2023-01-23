package com.waiyanhtet.query.example.dto;

import com.waiyanhtet.query.example.entity.State.Type;

public interface StateDTO {

	int getId();
	String getName();
	Type getType();
	String getRegion();
	int getPopulation();
	String getCapital();
}
