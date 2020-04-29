package com.macropro.editor;

import java.beans.PropertyEditorSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomDoubleEditor extends PropertyEditorSupport {

	@Autowired
	private Logger logger;
	
    public CustomDoubleEditor() {
    }

    public String getAsText() {
        Double d = (Double) getValue();
        return d == null ? null : d.toString();
    }

    public void setAsText(String str) {
    	if (str == null || str.trim().isEmpty()) 
    		setValue(null); 
    	else
    		setValue(Double.parseDouble(str));
    }
	
}
