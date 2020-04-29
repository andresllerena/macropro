package com.macropro.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macropro.editor.CustomDoubleEditor;
import com.macropro.model.Food;
import com.macropro.model.FoodLog;
import com.macropro.model.FoodLogEntry;
import com.macropro.model.FoodLogEntryWrapper;
import com.macropro.model.User;
import com.macropro.service.IFoodService;

@Controller
@RequestMapping("/food")
public class FoodController {

	@Autowired
	private Logger logger;

	@Autowired
	private IFoodService foodService;
	
	private Map<Integer, String> mealMap;
	private User currentUser;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		dataBinder.registerCustomEditor(Double.class, new CustomDoubleEditor());
		
		currentUser = getCurrentUser();
	}
	
	public FoodController () {
		mealMap = new HashMap<>();
		mealMap.put(0, "Breakfast");
		mealMap.put(1, "Lunch");
		mealMap.put(2, "Dinner");
		mealMap.put(3, "Snack 1");
		mealMap.put(4, "Snack 2");
		mealMap.put(5, "Snack 3");
	}

	@RequestMapping(value = "/diary", method = { RequestMethod.GET, RequestMethod.POST })
	public String showFoodDiaryWithParams(@RequestParam(value="date", required=false) String logDate, 
						RedirectAttributes attr, Model m) {
		logger.info("Entering Food Diary screen.");
		
		LocalDate date;
		LocalDateTime datetime;
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		if (logDate == null) {
			date = LocalDate.parse(LocalDate.now().toString(), dtf);
		} else {
			try {
				date = LocalDate.parse(logDate, dtf);
			}
			catch(DateTimeParseException e) {
				logger.error("Invalid date");
				return "redirect:/food/diary";
			}
		}
		
		logger.info(date.toString());
		User currentUser = getCurrentUser();
		
		try { 
			FoodLog log = foodService.getFoodLog(date, currentUser);
			
			if (log == null) {
				logger.info("Food log for " + date.toString() + " does not exist.");
				logger.info("Adding food log for " + date.toString() + " does not exist.");
				log = foodService.addFoodLog(date, currentUser);
			}
			
			m.addAttribute("log", log);
			m.addAttribute("date", date.toString());
		}
		catch(Exception e) {
			logger.error("Error retrieving food log for " + date.toString());
			logger.error(e.toString());
			logger.error(e.getCause().getMessage());
			return "diary";
		}

		return "diary";
	}
	
	@GetMapping("/diary/add")
	public String showAddFoods(@RequestParam(value="date", required=true) String logDate,
						@RequestParam(value="meal", required=true) int meal,
						RedirectAttributes attr,
						Model m) {
		
		LocalDate date;
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {
			date = LocalDate.parse(logDate, dtf);
		}
		catch(DateTimeParseException e) {
			logger.error("Invalid date");
			return "redirect:/food/diary";
		}
		
		// get foods user private foods and all public foods 
		try {
			FoodLog log = foodService.getFoodLog(date, currentUser);
			List<FoodLogEntry> foodLogEntries = foodService.getFoodLogEntries(date, currentUser);
			
			FoodLogEntryWrapper wrapper = new FoodLogEntryWrapper();
			wrapper.setEntries(foodLogEntries);
			
			logger.debug("foodLogEntries isEmpty: " + foodLogEntries.isEmpty());
			
			m.addAttribute("foodlog", log);
			m.addAttribute("foodLogEntries", foodLogEntries);
			m.addAttribute("wrapper", wrapper);
		}
		catch(Exception e) {
			logger.error("Error retrieving food log for " + date.toString());
			logger.error(e.toString());
			logger.error(e.getCause().getMessage());
			return "redirect:/food/diary?date=" + logDate;
		}

		m.addAttribute("date", logDate.toString());
		m.addAttribute("meal", meal);
		return "add-foods-to-log";
	}

	@PostMapping("/diary/add-foods")
	public String addFoodsToLog (
				 @ModelAttribute FoodLogEntryWrapper wrapper,
		         @RequestParam(value="date", required=true) String logDate,
		         @RequestParam(value="meal", required=true) int meal
	         ) {
		
			List<FoodLogEntry> selected = getSelectedEntries(wrapper.getEntries());
			
			LocalDate date;
			final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			logger.info("Adding " + selected.size() + " items to " + mealMap.get(meal));
			
			try {
				date = LocalDate.parse(logDate, dtf);
				foodService.addFoodsToLog(selected, meal, date, currentUser);
			} catch (Exception e) {
				logger.error("Error adding items to log");
				logger.error(e.toString());
				logger.error(e.getCause().getMessage());
				return "redirect:/food/diary?date=" + logDate;
			}

			logger.info("Successfully added " + selected.size() + " items to " + mealMap.get(meal));
		
		return "redirect:/food/diary?date=" + logDate;
	}
	

	@PostMapping("/diary/remove-food")
	public String removeFoodFromLog (
	         	 @RequestParam(value="entry_index", required=true) int entryIndex,
		         @RequestParam(value="date", required=true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
		         @RequestParam(value="meal", required=true) int meal
	         ) {
		
			logger.info("Removing entry at index " + entryIndex + " of " + mealMap.get(meal) 
							+ " list (" + date.toString() + ")");
			
			try {
				foodService.removeFoodFromLog(entryIndex, meal, date, currentUser);
			} catch (Exception e) {
				logger.error("Error removing entry from log");
				logger.error(e.toString());
				logger.error(e.getCause().getMessage());
				return "redirect:/food/diary?date=" + date.toString();
			}

			logger.info("Successfully removed entry at index " + entryIndex + " of " + mealMap.get(meal)
							+ " list (" + date.toString() + ")");
		
		return "redirect:/food/diary?date=" + date.toString();
	}

	@GetMapping("/search-foods")
	public String showSearchFoods(Model m) {
		logger.info("Entering Search Foods screen.");
		return "search-foods";
	}

	@GetMapping("/new")
	public String showCreateNewFood(Model m) {
		logger.info("Entering Create New Food screen.");
		loadFoodFields(m);
		return "create-new-food";
	}

	@GetMapping("/my-foods")
	public String showMyFoods(Model m) {
		logger.info("Entering My Foods screen.");

		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		m.addAttribute("myfoods", currentUser.getMyFoods());

		return "my-foods";
	}

	@GetMapping("/my-meals")
	public String showMyMeals(Model m) {
		logger.info("Entering My Meals screen.");
		return "my-meals";
	}

	@PostMapping("/create")
	public String createFood(@Valid @ModelAttribute("food") Food newFood, BindingResult br, RedirectAttributes attr,
			Model m) throws Exception {
		if (br.hasErrors()) {
			logger.error(br.toString());
			attr.addFlashAttribute("org.springframework.validation.BindingResult.food", br);
			attr.addFlashAttribute("food", newFood);
			return "redirect:/food/new";
		} else {
			User updatedUser;
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			logger.info(newFood.toString());

			try {
				newFood.setCalories(Food.generateTotalCalories(newFood.getCarbsGrams(), newFood.getFatGrams(), newFood.getProteinGrams()));
				updatedUser = foodService.addFood(currentUser.getUsername(), newFood);
			}

			catch (Exception e) {
				logger.error("Unable to add food.");
				logger.error(e.getMessage());
				attr.addFlashAttribute("org.springframework.validation.BindingResult.usergoal", br);
				attr.addFlashAttribute("food", newFood);
				return "redirect:/food/new";
			}

			logger.info("Successfully added food.");

			Authentication authentication = new PreAuthenticatedAuthenticationToken(updatedUser,
					updatedUser.getPassword(), updatedUser.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		return "redirect:/food/my-foods";
	}

	public void loadFoodFields(Model m) {
		List<String> foodMeasurements = new ArrayList<String>();
		foodMeasurements.add("tsp");
		foodMeasurements.add("tbsp");
		foodMeasurements.add("fl oz");
		foodMeasurements.add("cup");
		foodMeasurements.add("pint");
		foodMeasurements.add("quart");
		foodMeasurements.add("gal");
		foodMeasurements.add("mL");
		foodMeasurements.add("L");
		foodMeasurements.add("lb");
		foodMeasurements.add("oz");
		foodMeasurements.add("mg");
		foodMeasurements.add("g");
		foodMeasurements.add("kg");
		m.addAttribute("foodMeasurements", foodMeasurements);

		if (!m.containsAttribute("food")) {
			Food newFood = new Food();
			newFood.setPublicFlag(true);
			newFood.setCalories(Double.valueOf(0.0));

			m.addAttribute("food", newFood);
		}
	}
	
	private User getCurrentUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	private List<FoodLogEntry> getSelectedEntries(List<FoodLogEntry> entries) {
		List<FoodLogEntry> selected = new ArrayList<>();
		for (FoodLogEntry entry : entries) {
			if (entry.isSelected())
				selected.add(entry);
		}
		
		return selected;
	}
}
