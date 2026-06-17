package objectRepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebDriverUtilities.WebDriverMethods;
import reusableClass.FetchOtpUsingSubject;

public class LoginPage extends HeaderPage {
	HeaderPage hp;
	
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
		super(driver);
		PageFactory.initElements(driver, this);
		hp= new HeaderPage(driver);
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
	
	public WebElement getVerificationTf() {
		return verificationTf;
	}


	public WebElement getVerifyBtn() {
		return verifyBtn;
	}
	
	public void logIn(String username, String password, WebDriverMethods webutil) {

        webutil.explicitlyWaitTime(3000);
        //Checking Username
        boolean isDisplayed= false;
	   try {
		   isDisplayed=getUsernameTF().isDisplayed();
	   }
	   catch (Exception e) {
		isDisplayed=false;
	}
	    
	    if (isDisplayed) {
	            System.out.println("Login page detected");

	            usernameTF.sendKeys(username);
	            passwordTF.sendKeys(password);
	            loginBtn.click();

	            webutil.explicitlyWaitTime(3000);
	          //Checking for OTP req
	            try {
	            	isDisplayed=getVerificationTf().isDisplayed();
	            	
	            }catch (Exception e) {
					isDisplayed=false;
				}
	            
	           if(isDisplayed==false) {
	        	   webutil.explicitlyWaitUntilElementVisible(hp.getAppLauncherBtn());
	           }

	    }
    //Fetching OTP
	    if (isDisplayed) {
	        try {
	            System.out.println("OTP page detected");

	            String otp = FetchOtpUsingSubject.getOtp();
	            verificationTf.sendKeys(otp);

	            webutil.waitUntilElementClickble(verifyBtn);
	            verifyBtn.click();
	            
	            webutil.explicitlyWaitUntilElementVisible(hp.getAppLauncherBtn());

	        } catch (Exception e) {
	            System.out.println("OTP handling failed: " + e);
	        }
	    }
	    webutil.explicitlyWaitUntilElementVisible(hp.getAppLauncherBtn());
	}

}
