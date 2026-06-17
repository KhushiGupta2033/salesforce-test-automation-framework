package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import WebDriverUtilities.WebDriverMethods;
import reusableClass.CommonLocators;

public class ContactsPage extends HeaderPage {
	CommonLocators cl;
	WebDriverMethods webUtil;
	SoftAssert sf= new SoftAssert();

	@FindBy(xpath = "//h1[.='Contacts']")
	private WebElement contactTxt;

	@FindBy(xpath = "//a[@title='New']")
	private WebElement newBtn;
	
	@FindBy(xpath = "//a[@title='Intelligence View']")
	private WebElement intelligenceViewBtn;
	
	@FindBy(xpath = "//a[@title='Import']")
	private WebElement importBtn;
	
	@FindBy(xpath = "//a[@title='Add to Campaign']")
	private WebElement addTocampaignBtn;
	
	@FindBy(xpath = "//a[@title='Send Email']")
	private WebElement sendEmailBtn;

	public ContactsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
		cl= new CommonLocators(driver);
		webUtil= new WebDriverMethods(driver);
	}

	public WebElement getContactTxt() {
		return contactTxt;
	}

	public WebElement getNewBtn() {
		return newBtn;
	}

	public WebElement getIntelligenceViewBtn() {
		return intelligenceViewBtn;
	}

	public WebElement getImportBtn() {
		return importBtn;
	}

	public WebElement getAddTocampaignBtn() {
		return addTocampaignBtn;
	}

	public WebElement getSendEmailBtn() {
		return sendEmailBtn;
	}
	
	public void clickContacts() {
		webUtil.explicitlyWaitUntilElementVisible(getContactsBtn());
		webUtil.clickOnElementUsingJS(getContactsBtn());
		webUtil.explicitlyWaitUntilElementVisible(contactTxt);
	}
	
	public void contactsObjectVerification() {
		clickContacts();
		sf.assertTrue(getContactTxt().isDisplayed(), "Contacts text is not displayed");
		sf.assertTrue(getNewBtn().isDisplayed(), "New Button is not displayed");
		sf.assertTrue(getIntelligenceViewBtn().isDisplayed(), "Intelligence View Button is not displayed");
		sf.assertTrue(getImportBtn().isDisplayed(), "Import Button is not displayed");
		sf.assertTrue(getAddTocampaignBtn().isDisplayed(), "Add to Campaign Button is not displayed");
		sf.assertTrue(getSendEmailBtn().isDisplayed(), "Send Email Button is not displayed");
		sf.assertTrue(cl.getSearchTheListTF().isDisplayed(), "Send List TF is not displayed");
		sf.assertTrue(cl.getSettingBtn().isDisplayed(), "Settings Btn is not displayed");
		sf.assertTrue(cl.getSelectListDisplayBtn().isDisplayed(), "Select List Display Button is not displayed");
		sf.assertTrue(cl.getRefreshBtn().isDisplayed(), "Refresh Button is not displayed");
		sf.assertTrue(cl.getEditListBtn().isDisplayed(), "Edit List Button is not displayed");
		webUtil.clickOnElementUsingJS(cl.getRecentlyViewedBtn());
		webUtil.explicitlyWaitUntilElementVisible(cl.getListViewsTxt());
		sf.assertTrue(cl.getListViewsTxt().isDisplayed(), "Recently View List is not displayed");
		sf.assertTrue(cl.getAllOtherListTxt().isDisplayed(), "All Other List is not displayed");
		sf.assertAll();
		System.out.println("Contact Page Verfication Completed");
		
		
	}
	
}
