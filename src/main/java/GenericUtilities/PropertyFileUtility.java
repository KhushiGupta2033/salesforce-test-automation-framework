package GenericUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class PropertyFileUtility {
	
	String filePath;
	
	/**
	 * This constructor will take the filePath as a argument and assign to non- static variable
	 * @param filePath
	 */
	public PropertyFileUtility(String filePath) {
		this.filePath=filePath;
	}

	/**
	 * This method will take the returns the 'value' based on the key in propertyFile
	 * @param filePath
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String readDataFromPropertiesFile(String key) throws IOException {
		
		File file = new File(filePath);
		
		    // 1. Check if file exists
		    if (!file.exists()) {
		        throw new RuntimeException("Properties file not found at path: " + filePath);
		    }
		    
		    Properties props = new Properties();
		    FileInputStream fis = new FileInputStream(file);
		    props.load(fis);
		 
		    // 2. Check if key exists
		    if (!props.containsKey(key)) {
		        throw new RuntimeException("Key '" + key + "' not found in properties file: " + filePath);
		    }

		    // 5. Retrieve the value
		    String value = props.getProperty(key);

		    // 6. Key exists but value is empty/null
		    if (value == null || value.trim().isEmpty()) {
		        throw new RuntimeException("sKey '" + key + "' exists but has no value in: " + filePath);
		    }

		    return props.getProperty(key);
	}
	
	/**
	 * This method will update the existing key or set the key value to properties file
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public void setValueToKeyInPropertyFile(String key, String value) throws IOException {
		
		File file = new File(filePath);
		if(!file.exists()) {
			 throw new RuntimeException("Properties file not found at path: " + filePath); 
		}
		
	    Properties props = new Properties();
	    FileInputStream fis = new FileInputStream(file);
	    props.load(fis);
	    
	    if (!props.containsKey(key)) {
	        throw new RuntimeException("Key '" + key + "' not found in properties file: " + filePath);
	    }
	    

	    // Update or add the key
	    props.setProperty(key, value);
		
	    // Save the updated properties back to file
	    try (FileOutputStream fos = new FileOutputStream(file)) {
	        props.store(fos, "Updated key: " + key);
	    } catch (Exception e) {
	        throw new RuntimeException("❌ Unable to write to properties file: " + filePath, e);
	    }
	}
	
	/**
	 * This method will returns the all keys as a list<String>
	 * @return
	 * @throws IOException
	 */
	public List<String> getAllKeys() throws IOException{
		File file = new File(filePath);
		if(!file.exists()) {
			 throw new RuntimeException("Properties file not found at path: " + filePath); 
		}
		
	    Properties props = new Properties();
	    FileInputStream fis = new FileInputStream(file);
	    props.load(fis);
	    
	    @SuppressWarnings("unchecked")
		List<String> getAllKeys = (List<String>) props.stringPropertyNames();
		
		return getAllKeys;
		
	}
	
	/**
	 * this method will returns the boolean based on key present or not
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public boolean verifyKeyPresentInPropertyFile(String key) throws IOException {
		boolean flag = true;
		File file = new File(filePath);
		if(!file.exists()) {
			 throw new RuntimeException("Properties file not found at path: " + filePath); 
		}
		
	    Properties props = new Properties();
	    FileInputStream fis = new FileInputStream(file);
	    props.load(fis);
	    
	    if (!props.containsKey(key)) {
	        flag = false;
	    }
		
		return flag;
	}
	
	/**
	 * This method will return all the keys in a sorted order
	 * @return
	 * @throws IOException
	 */
	public List<String> getAllKeysInSorted() throws IOException{
		File file = new File(filePath);
		if(!file.exists()) {
			 throw new RuntimeException("Properties file not found at path: " + filePath); 
		}
		
	    Properties props = new Properties();
	    FileInputStream fis = new FileInputStream(file);
	    props.load(fis);
	    
		List<String> getAllKeys = new ArrayList<>(props.stringPropertyNames());
	    
		Collections.sort(getAllKeys);

		return getAllKeys;
	}

}
