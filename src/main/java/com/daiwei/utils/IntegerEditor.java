
package com.daiwei.utils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

// TODO: Auto-generated Javadoc
/**
 * The Class IntegerEditor.
 */
public class IntegerEditor extends PropertiesEditor {  
    
    /* (non-Javadoc)
     * @see org.springframework.beans.propertyeditors.PropertiesEditor#setAsText(java.lang.String)
     */
    @Override  
    public void setAsText(String text) throws IllegalArgumentException {  
        if (text == null || text.equals("")) {  
            text = "0";  
        }  
        setValue(Integer.parseInt(text));  
    }  
  
    /* (non-Javadoc)
     * @see java.beans.PropertyEditorSupport#getAsText()
     */
    @Override  
    public String getAsText() {  
        return getValue().toString();  
    }  
} 