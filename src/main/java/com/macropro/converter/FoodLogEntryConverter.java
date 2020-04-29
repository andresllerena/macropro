package com.macropro.converter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.macropro.model.FoodLogEntry;

@Component
public class FoodLogEntryConverter implements Converter<String, FoodLogEntry> {

	@Autowired
	private Logger logger;
	
	@Override
	public FoodLogEntry convert(String id) {
		logger.info("****:" + id);
		if (id == null) return new FoodLogEntry(1);
		return new FoodLogEntry(Integer.parseInt(id));
	}
	
}
