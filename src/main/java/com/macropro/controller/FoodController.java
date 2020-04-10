package com.macropro.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macropro.service.IUserService;

@Controller
@RequestMapping("/food")
public class FoodController {

		@Autowired
		private Logger logger;
		
		@Autowired
		private IUserService userService;
		
		@GetMapping("/diary")
		public String showFoodDiary(Model m) {
			logger.info("Entering Food Diary screen.");
			return "diary";
		}
		
		@GetMapping("/search-foods")
		public String showSearchFoods(Model m) {
			logger.info("Entering Search Foods screen.");
			return "search-foods";
		}
		
		@GetMapping("/my-foods")
		public String showMyFoods(Model m) {
			logger.info("Entering My Foods screen.");
			return "my-foods";
		}
		
		@GetMapping("/my-meals")
		public String showMyMeals(Model m) {
			logger.info("Entering My Meals screen.");
			return "my-meals";
		}
}
