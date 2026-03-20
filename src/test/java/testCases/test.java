package testCases;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class test {
	public static void main(String[] args) {
		String path="C:/Salesforce";
		
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--user-data-dir="+path);
		ChromeDriver driver= new ChromeDriver(options);
		
	}

}
