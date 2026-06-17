package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import WebDriverUtilities.WebDriverMethods;
import reusableClass.CommonLocators;

public class AccountsPage extends HeaderPage {
	 CommonLocators cl;
	 WebDriverMethods webUtil;
	 SoftAssert sf= new SoftAssert();
	
	@FindBy(xpath = "//h1[.='Accounts']")
	private WebElement accountsTxt;
	
	@FindBy(xpath = "//a[@title='New']")
	private WebElement newBtn;
	
	@FindBy(xpath = "//a[@title='Import']")
	private WebElement importBtn;
	
	@FindBy(xpath = "(//a[@title='Assign Label'])[1]")
	private WebElement assignLabelBtn;
	
	public AccountsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		cl= new CommonLocators(driver);
		webUtil= new WebDriverMethods(driver);
	}

	public WebElement getAccountsTxt() {
		return accountsTxt;
	}

	public WebElement getNewBtn() {
		return newBtn;
	}

	public WebElement getImportBtn() {
		return importBtn;
	}

	public WebElement getAssignLabelBtn() {
		return assignLabelBtn;
	}
	
	public void clickAccounts() {
		webUtil.waitUntilElementClickble(getAccountsBtn());
		webUtil.clickOnElementUsingJS(getAccountsBtn());
		webUtil.explicitlyWaitUntilElementVisible(accountsTxt);
	}
	
	public void accountsObjectVerfication() {
		clickAccounts();
		sf.assertTrue(getAccountsTxt().isDisplayed(),"Account Text is not displayed");
		sf.assertTrue(getNewBtn().isDisplayed(),"New Button is not displayed");
		sf.assertTrue(getImportBtn().isDisplayed(),"Import Button is not displayed");
		sf.assertTrue(getAssignLabelBtn().isDisplayed(),"Assign Label Button is not displayed");
		sf.assertTrue(cl.getSearchTheListTF().isDisplayed(),"Search List TF is not displayed");
		sf.assertTrue(cl.getSettingBtn().isDisplayed(),"List view control Button is not displayed");
		sf.assertTrue(cl.getSelectListDisplayBtn().isDisplayed(),"Select List Display Button is not displayed");
		sf.assertTrue(cl.getRefreshBtn().isDisplayed(),"Refresh Button is not displayed");
		sf.assertTrue(cl.getColumnSortBtn().isDisplayed(),"Column Sort Button is not displayed");
		sf.assertTrue(cl.getEditListBtn().isDisplayed(),"Edit List Button is not displayed");
		sf.assertTrue(cl.getRecentlyViewedBtn().isDisplayed(),"Recently View Button is not displayed");
		webUtil.clickOnElementUsingJS(cl.getRecentlyViewedBtn());
		webUtil.explicitlyWaitUntilElementVisible(cl.getListViewsTxt());
		sf.assertTrue(cl.getListViewsTxt().isDisplayed(),"List View Text is not displayed");
		sf.assertTrue(cl.getAllOtherListTxt().isDisplayed(),"All Other Text is not displayed");
		System.out.println("Accounts Page Verfication Completed");
		sf.assertAll();
	}
	

}
