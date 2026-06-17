package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebDriverUtilities.WebDriverMethods;
import junit.framework.Assert;
import reusableClass.CommonLocators;

public class OpportunityPage extends HeaderPage {
	
	WebDriverMethods webUtil;
	CommonLocators cl;
	
	@FindBy(xpath ="//a[@title='New']" )
	private WebElement newBtn;
	
	@FindBy(xpath ="//a[@title='Assign Label']" )
	private WebElement assignLabelBtn;
	
	@FindBy(xpath = "//input[@name='Opportunity-search-input']")
	private WebElement searchInputTF ;
	
	@FindBy(xpath = "//button[@title='List View Controls']")
	private WebElement settingsBtn ;
	
	@FindBy(xpath = "//button[@title='Select list display']")
	private WebElement listDisplayBtn ;
	
	@FindBy(xpath = "//button[@title='Refresh']")
	private WebElement refreshBtn ;
	
	@FindBy(xpath = "//button[@title='Column sort']")
	private WebElement columnSortBtn ;
	
	@FindBy(xpath = "//button[@title='Edit List']")
	private WebElement editListBtn ;
	
	@FindBy(xpath = "//button[@title=This list is pinned.']")
	private WebElement pinIcon ;
	
	@FindBy(xpath = "//button[contains(@title,'Select a List View')]")
	private WebElement recentlyViewedBtn ;
	
	public OpportunityPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
		cl= new CommonLocators(driver);
		webUtil= new WebDriverMethods(driver);
	}

	public WebElement getNewBtn() {
		return newBtn;
	}

	public WebElement getAssignLabelBtn() {
		return assignLabelBtn;
	}

	public WebElement getSearchInputTF() {
		return searchInputTF;
	}

	public WebElement getSettingsBtn() {
		return settingsBtn;
	}

	public WebElement getListDisplayBtn() {
		return listDisplayBtn;
	}

	public WebElement getRefreshBtn() {
		return refreshBtn;
	}

	public WebElement getColumnSortBtn() {
		return columnSortBtn;
	}

	public WebElement getEditListBtn() {
		return editListBtn;
	}

	public WebElement getPinIcon() {
		return pinIcon;
	}

	public WebElement getRecentlyViewedBtn() {
		return recentlyViewedBtn;
	}
	
	public void clickOpp() {
		  webUtil.waitUntilElementClickble(getOppBtn());
	       webUtil.clickOnElementUsingJS(getOppBtn());
	}
	
	public boolean isNewBtnDisplayed() {
		return getNewBtn().isDisplayed();
	}
	
	public boolean isAssignLabelDisplayed() {
		return getAssignLabelBtn().isDisplayed();
	}
	
	public boolean isSearchListDisplayed() {
		return getSearchInputTF().isDisplayed();
	}
	public boolean isListDisplayDisplayed() {
		return getListDisplayBtn().isDisplayed();
	}
	
	public boolean isListViewControlDisplayed() {
		return getSettingsBtn().isDisplayed();
	}
	public boolean isRefreshDisplayed() {
		return getRefreshBtn().isDisplayed();
	}
	public boolean isColumnsortDisplayed() {
		return getColumnSortBtn().isDisplayed();
	}
	
	public boolean isEditListDisplayed() {
		return getEditListBtn().isDisplayed();
	}
	
	public boolean isPinIconDisplayed() {
		return getPinIcon().isDisplayed();
	}
	
	public boolean isRecentlyViewDisplayed() {
		return getRecentlyViewedBtn().isDisplayed();
	}
	
   public boolean isRecentlyListViewDisplayed() {
	   return cl.getListViewsTxt().isDisplayed();
   }
   
   public boolean isAllOtherListDisplayed() {
	   return cl.getAllOtherListTxt().isDisplayed();
   }
   
   //Opportunity Object Verification
   public void OppObjectVerification() {
   clickOpp();
	webUtil.waitUntilElementClickble(getNewBtn());
	Assert.assertEquals(true,isNewBtnDisplayed());
   Assert.assertEquals(true, isAssignLabelDisplayed());
   Assert.assertEquals(true, isSearchListDisplayed());
   Assert.assertEquals(true, isListViewControlDisplayed());
   Assert.assertEquals(true, isSearchListDisplayed());
   Assert.assertEquals(true, isRefreshDisplayed());
   Assert.assertEquals(true, isColumnsortDisplayed());
   Assert.assertEquals(true, isEditListDisplayed());
   Assert.assertEquals(true, isRecentlyViewDisplayed());
   getRecentlyViewedBtn().click();
   webUtil.explicitlyWaitUntilElementVisible(cl.getListViewsTxt());
   Assert.assertEquals(true, isRecentlyListViewDisplayed());
   Assert.assertEquals(true, isAllOtherListDisplayed());
   System.out.println("Opportunity Object Verified");
   }
	
   
}
