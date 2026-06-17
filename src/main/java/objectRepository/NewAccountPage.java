package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebDriverUtilities.WebDriverMethods;
import genericUtilities.RandomGeneration;

public class NewAccountPage {
	HeaderPage hp;
	AccountsPage ap;
	WebDriverMethods webUtil;
	WebDriver driver;
	RandomGeneration ran = new RandomGeneration();
	
	@FindBy(xpath = "//input[@name='Name']")
	private WebElement accountName;
	
	@FindBy(xpath = "//label[.='Type']/ancestor::lightning-combobox/descendant::button[@data-value='--None--']")
	private WebElement typeDD;
	
	@FindBy(xpath = "//label[.='Industry']/ancestor::lightning-combobox/descendant::button[@data-value='--None--']")
	private WebElement industryDD;
	
	@FindBy(xpath = "//input[@name='Website']")
	private WebElement websiteTf;
	
	@FindBy(xpath = "//input[@name='NumberOfEmployees']")
	private WebElement noOfEMployeesTf;
	
	@FindBy(xpath = "//lightning-textarea[.='Billing Street']/descendant::textarea")
	private WebElement billingStreetTF;
	
	@FindBy(xpath = "//lightning-textarea[.='Shipping Street']/descendant::textarea")
	private WebElement shippingStreetTF;
	
	@FindBy(xpath = "//label[.='Billing City']/../descendant::input")
	private WebElement billingCityTF;
	
	@FindBy(xpath = "//label[.='Shipping City']/../descendant::input")
	private WebElement shippingCityTF;
	
	@FindBy(xpath = "//label[.='Billing Zip/Postal Code']/../descendant::input")
	private WebElement billingCodeTF;
	
	@FindBy(xpath = "//label[.='Shipping Zip/Postal Code']/../descendant::input")
	private WebElement shippingCodeTF;
	
	@FindBy(xpath = "//label[.='Billing State/Province']/../descendant::input")
	private WebElement billingStateTF;
	
	@FindBy(xpath = "//label[.='Shipping State/Province']/../descendant::input")
	private WebElement shippingStateTF;
	
	@FindBy(xpath = "//label[.='Billing Country']/../descendant::input")
	private WebElement billingCountryTF;
	
	@FindBy(xpath = "//label[.='Shipping Country']/../descendant::input")
	private WebElement shippingCountryTF;
	
	@FindBy(xpath = "//button[.='Save']")
	private WebElement saveBtnTF;
	
	public NewAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		hp= new HeaderPage(driver);
		ap= new AccountsPage(driver);
		webUtil = new WebDriverMethods(driver);
	}

	public WebElement getAccountName() {
		return accountName;
	}

	public WebElement getTypeDD() {
		return typeDD;
	}

	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getWebsiteTf() {
		return websiteTf;
	}

	public WebElement getNoOfEMployeesTf() {
		return noOfEMployeesTf;
	}

	public WebElement getBillingStreetTF() {
		return billingStreetTF;
	}

	public WebElement getShippingStreetTF() {
		return shippingStreetTF;
	}

	public WebElement getBillingCityTF() {
		return billingCityTF;
	}

	public WebElement getShippingCityTF() {
		return shippingCityTF;
	}

	public WebElement getBillingCodeTF() {
		return billingCodeTF;
	}

	public WebElement getShippingCodeTF() {
		return shippingCodeTF;
	}

	public WebElement getBillingStateTF() {
		return billingStateTF;
	}

	public WebElement getShippingStateTF() {
		return shippingStateTF;
	}

	public WebElement getBillingCountryTF() {
		return billingCountryTF;
	}

	public WebElement getShippingCountryTF() {
		return shippingCountryTF;
	}

	public WebElement getSaveBtnTF() {
		return saveBtnTF;
	}
	
	
	public void createAccount(String accName, String websiteName, String type, String industry, String street, String city, String code, String state, String country) {
		System.out.println("Account Creation Started");
		webUtil.clickOnElementUsingJS(ap.getNewBtn());;
		webUtil.implicitlyWaitTime(5);
		webUtil.explicitlyWaitUntilElementVisible(accountName);
		getAccountName().sendKeys(accName);
		webUtil.scrollPageUntilElementVisibleInUI(websiteTf);
		getWebsiteTf().sendKeys(websiteName);
		webUtil.scrollPageUntilElementVisibleInUI(typeDD);
		getTypeDD().click();
		webUtil.explicitlyWaitUntilElementVisible(TypeValue(type));
		TypeValue(type).click();
		webUtil.scrollPageUntilElementVisibleInUI(industryDD);
		getIndustryDD().click();
		webUtil.explicitlyWaitUntilElementVisible(TypeValue(industry));
		TypeValue(industry).click();
		getNoOfEMployeesTf().sendKeys(ran.ranNum());
		webUtil.scrollPageUntilElementVisibleInUI(getBillingStreetTF());
		getBillingStreetTF().sendKeys(street);
		getShippingStreetTF().sendKeys(street);
		getBillingCityTF().sendKeys(city);
		getShippingCityTF().sendKeys(city);
		webUtil.scrollPageUntilElementVisibleInUI(billingCodeTF);
		getBillingCodeTF().sendKeys(code);
		getShippingCodeTF().sendKeys(code);
		getBillingStateTF().sendKeys(state);
		getShippingStateTF().sendKeys(state);
		webUtil.scrollPageUntilElementVisibleInUI(billingCountryTF);
		getBillingCountryTF().sendKeys(country);
		getShippingCountryTF().sendKeys(country);
		getSaveBtnTF().click();
	}
	
	public WebElement TypeValue(String Type) {
		driver=webUtil.getWebDriver();
		WebElement ele= driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='"+Type+"']"));
		return ele;
	}

	

}
