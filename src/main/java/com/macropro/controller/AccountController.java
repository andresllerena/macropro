package com.macropro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CharacterEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macropro.model.User;
import com.macropro.model.UserGoal;
import com.macropro.service.IUserService;
import com.macropro.service.UserExistsException;

@Controller
@RequestMapping("/account")
public class AccountController {

	private final String PASSWORD_MISMATCH_MESSAGE = "passwords don't match";
	
	@Autowired
	private Logger logger;
	
	@Autowired
	private IUserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		CharacterEditor charEditor = new CharacterEditor(false);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		dataBinder.registerCustomEditor(Character.class, charEditor);
	}
	
	@GetMapping("/list")
	public String listUsers(Model m) {
		List<User> users = userService.getUsers();
		m.addAttribute("users", users);
		return "";
	}
	
	@GetMapping("/")
	public String showHome() {
		logger.info("Entering home screen.");
		return "home";
	}
	
	@GetMapping("/goals")
	public String showGoals(Model m) {
		logger.info("Entering goals screen.");
		loadGoalFields(m);
		return "goals";
	}
	
	@GetMapping("/login")
	public String showLogin(@ModelAttribute("message") String message,
			Model m) {
		logger.info("Entering login screen.");
		
		if (message.equals(""))
			message = null;

		m.addAttribute("message", message);
		
		return "login";
	}
	
	@GetMapping("/signup")
	public String showSignup(@ModelAttribute("password_mismatch") String passwordMismatch,
			@ModelAttribute("username_exists") String usernameExists,
			@ModelAttribute("email_exists") String emailExists,
			Model m) {
		logger.info("Entering signup screen.");
		User newUser = new User();
		
		if (!m.containsAttribute("user")) {
			m.addAttribute("user", newUser);
		}
		
		if (passwordMismatch.equals(""))
			passwordMismatch = null;
		
		if (usernameExists.equals(""))
			usernameExists = null;
		
		if (emailExists.equals(""))
			emailExists = null;
		
		m.addAttribute("password_mismatch", passwordMismatch);
		m.addAttribute("username_exists", usernameExists);
		m.addAttribute("email_exists", emailExists);
		return "signup";
	}
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") User newUser,
							BindingResult br,
							RedirectAttributes attr,
							Model m) throws Exception {
		if (br.hasErrors()) {
			logger.error("Error(s) in form validation.");
			
			boolean passwordError = false;
			for (FieldError e : br.getFieldErrors()) {
				if (e.getField().equals("password"))
					passwordError = true;
			}
			for (ObjectError e : br.getAllErrors()) {
				if (e.getDefaultMessage().equals(PASSWORD_MISMATCH_MESSAGE) && !passwordError) {
					//m.addAttribute("password_mismatch", PASSWORD_MISMATCH_MESSAGE);
				    attr.addFlashAttribute("password_mismatch", PASSWORD_MISMATCH_MESSAGE);
				}
			}
			
			attr.addFlashAttribute("org.springframework.validation.BindingResult.user", br);
		    attr.addFlashAttribute("user", newUser);
			
			return "redirect:/account/signup"; //"signup";
			
		} else {
			try {
				userService.addUser(newUser);
			}
			catch(UserExistsException e) {
				logger.error("Unable to create user");
				logger.error(e.getMessage());
				
				if (e.getExceptionSet().contains("username")) {
					//m.addAttribute("username_exists", "username is already in use");
					attr.addFlashAttribute("username_exists", "username is already in use");
				}
				
				if(e.getExceptionSet().contains("email")) {
					//m.addAttribute("email_exists", "email is already in use");
					attr.addFlashAttribute("email_exists", "email is already in use");
				}

				attr.addFlashAttribute("org.springframework.validation.BindingResult.user", br);
			    attr.addFlashAttribute("user", newUser);
			    
				return "redirect:/account/signup"; //"signup";
			}
			
			logger.info("User has been successfully created");
			attr.addFlashAttribute("message", "User has been successfully created");
			return "redirect:/account/login"; 
		}
	}
	
	@PostMapping("/addUserGoal")
	public String addUserGoal(@Valid @ModelAttribute("usergoal") UserGoal newUserGoal,
			BindingResult br,
			RedirectAttributes attr,
			Model m) throws Exception {
		if (br.hasErrors()) {
			logger.error(br.toString());
			attr.addFlashAttribute("org.springframework.validation.BindingResult.usergoal", br);
		    attr.addFlashAttribute("usergoal", newUserGoal);
			return "redirect:/account/goals";
		}
		else {
			logger.info(newUserGoal.toString());
			
			User currentUser = (User) SecurityContextHolder.getContext()
													.getAuthentication()
													.getPrincipal();
			User updatedUser;
			
			logger.info(currentUser.toString());
			
			if (currentUser.getGoal() != null && currentUser.getGoal().equals(newUserGoal)) {
				logger.info("Goal info for user has not changed");
			} else {
				try {
					updatedUser = userService.addGoalAndMacroPlan(currentUser.getUsername(), newUserGoal);
				}
				catch(Exception e) {
					logger.error("Unable to create goal/macro plan");
					logger.error(e.getMessage());
					attr.addFlashAttribute("org.springframework.validation.BindingResult.usergoal", br);
				    attr.addFlashAttribute("usergoal", newUserGoal);
					return "redirect:/account/goals";
				}

				Authentication authentication = new PreAuthenticatedAuthenticationToken(updatedUser, updatedUser.getPassword(), updatedUser.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		return "redirect:/account/";
	}
	
	public void loadGoalFields(Model m) {
		Map<String, String> activityLevels = new HashMap<String, String>();
		activityLevels.put("1", "Sedentary or light activity");
		activityLevels.put("2", "Active or moderately active");
		activityLevels.put("3", "Vigorously active");
		m.addAttribute("activityLevels", activityLevels);
		
		User currentUser = (User) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
		
		if (currentUser.getGoal() == null) {
			UserGoal newUserGoal = new UserGoal();    
			if (!m.containsAttribute("usergoal")) {
				m.addAttribute("usergoal", newUserGoal);
			}
		}
		
		else {    
			if (!m.containsAttribute("usergoal")) {
				m.addAttribute("usergoal", currentUser.getGoal());
			}
		}
	}
}
