package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebDriverUtilities.WebDriverMethods;

public class LoginPage {
	
	@FindBy(id = "username")
	private WebElement usernameTF;

	@FindBy(id = "password")
	private WebElement passwordTF;
	
	@FindBy(id = "Login")
	private WebElement loginBtn;
	
	@FindBy(name = "emc")
	private WebElement verificationTf;
	
	@FindBy(xpath = "//input[@title=\"Verify\"]")
	private WebElement verifyBtn;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	public WebElement getUsernameTF() {
		return usernameTF;
	}

	public WebElement getPasswordTF() {
		return passwordTF;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	public void logIn(String username, String password, WebDriverMethods webutil) {
		
		webutil.explicitlyWaitUntilElementVisible(usernameTF);
		usernameTF.sendKeys(username);
		passwordTF.sendKeys(password);
		loginBtn.click();
		
	}

}
