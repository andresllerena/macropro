package com.macropro.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.macropro.model.User;

public class PasswordMatchesValidator 
implements ConstraintValidator<PasswordMatches, Object> { 
   
  @Override
  public void initialize(PasswordMatches constraintAnnotation) {       
  }
  
  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context){   
	  User user = (User) obj;
	  
	  if (user.getPassword() == null || user.getMatchingPassword() == null)
		  return false;
	  
      return user.getPassword().equals(user.getMatchingPassword());    
  }     
}
