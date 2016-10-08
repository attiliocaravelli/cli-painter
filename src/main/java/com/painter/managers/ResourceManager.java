/**
 * This class introduces a message handler 
 * for the internalization of the error messages
 * 
 * @author Attilio Caravelli
 *
 */
package com.painter.managers;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {

	private static final ResourceBundle messages = ResourceBundle.getBundle("i8n/messages", Locale.US);

	private ResourceManager() {
	}
	
    public static String getString(String key) {
		String ret = new String(); // empty string
		try {
			ret = messages.getString(key);
		} catch(Exception e) {
			// just nothing!
		}
		return ret;
	}
}
