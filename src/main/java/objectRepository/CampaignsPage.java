package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import WebDriverUtilities.WebDriverMethods;
import reusableClass.CommonLocators;

public class CampaignsPage extends HeaderPage{
	WebDriverMethods webUtil;
	CommonLocators cl;
	SoftAssert sf= new SoftAssert();
	
	@FindBy(xpath = "//h1[.='Campaigns']")
	private WebElement campaignsTxt;
	
	@FindBy(xpath = "//a[@title='New']")
	private WebElement newBtn;

    @FindBy(xpath = "//a[@title='Assign Label']")
    private WebElement assignLabelBtn;
    
    public CampaignsPage(WebDriver driver) {
    	super(driver);
    	PageFactory.initElements(driver,this);
    	webUtil=new  WebDriverMethods(driver);
    	cl= new CommonLocators(driver);	
    }

	public WebElement getCampaignsTxt() {
		return campaignsTxt;
	}

	public WebElement getNewBtn() {
		return newBtn;
	}

	public WebElement getAssignLabelBtn() {
		return assignLabelBtn;
	}
    
	public void clickCampaigns() {
		webUtil.explicitlyWaitUntilElementVisible(getCampgBtn());
		webUtil.clickOnElementUsingJS(getCampgBtn());
		webUtil.explicitlyWaitUntilElementVisible(getCampaignsTxt());
	}
	
	public void campaignObjectVerification() {
		clickCampaigns();
		webUtil.refreshPage();
        webUtil.explicitlyWaitUntilElementVisible(getCampaignsTxt());
        sf.assertTrue(getCampaignsTxt().isDisplayed(), "Campaigns Text is not dispalyed");
        sf.assertTrue(getNewBtn().isDisplayed(), "New Button is not dispalyed");
        sf.assertTrue(getAssignLabelBtn().isDisplayed(), "Assign Button is not dispalyed");
        sf.assertTrue(getAssignLabelBtn().isDisplayed(), "Assign Button is not dispalyed");
        sf.assertTrue(cl.getSearchTheListTF().isDisplayed(), "Search TF is not dispalyed");
        sf.assertTrue(cl.getSettingBtn().isDisplayed(), "Settigs Btn is not dispalyed");
        sf.assertTrue(cl.getRefreshBtn().isDisplayed(), "Refersh Btn is not dispalyed");
        sf.assertTrue(cl.getColumnSortBtn().isDisplayed(), "Column Btn is not dispalyed");
        sf.assertTrue(cl.getEditListBtn().isDisplayed(), "Edit List Btn is not dispalyed");
        sf.assertTrue(cl.getRecentlyViewedBtn().isDisplayed(), "Recently Viewed Btn is not dispalyed");
        webUtil.clickOnElementUsingJS(cl.getRecentlyViewedBtn());
        webUtil.explicitlyWaitUntilElementVisible(cl.getListViewsTxt());
        sf.assertTrue(cl.getListViewsTxt().isDisplayed(), "List View Text is not dispalyed");
        sf.assertTrue(cl.getAllOtherListTxt().isDisplayed(), "All other list Text is not dispalyed");
        System.out.println("Campaign Object Verification Completed");
        sf.assertAll();
	}

}
