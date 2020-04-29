package com.macropro.converter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.macropro.model.Food;

@Component
public class FoodConverter implements Converter<String, Food> {

	@Autowired
	private Logger logger;
	
	@Override
	public Food convert(String id) {
		logger.info("****:" + id);
		if (id == null) return new Food();
		return new Food(Integer.parseInt(id));
	}
	
}
