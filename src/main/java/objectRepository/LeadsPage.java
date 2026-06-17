package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebDriverUtilities.WebDriverMethods;
import junit.framework.Assert;

public class LeadsPage extends HeaderPage {
	
	WebDriverMethods webUtil;
	
	@FindBy(xpath = "//button[@title='Lead View Settings']")
	private WebElement settingsBtn;
	
	@FindBy(xpath = "(//button[@title='Refresh'])[last()]")
	private WebElement refreshBtn;
	
	@FindBy(xpath = "(//button[@title='Edit List'])[last()]")
	private WebElement editListBtn;
	
	@FindBy(xpath = "//button[@name='New']")
	private WebElement newBtn;
	
	@FindBy(xpath = "//button[text()='List View']")
	private WebElement listViewBtn;
	
	@FindBy(xpath = "//button[text()='Add to Campaign']")
	private WebElement addToCampgainBtn;
	
	@FindBy(xpath = "//button[text()='Change Status']")
	private WebElement changeStatusBtn;
	
	@FindBy(xpath = "//button[text()='Change Owner']")
	private WebElement changeOwnerBtn;
	
	@FindBy(xpath = "//button[text()='Send Email']")
	private WebElement sendEmailBtn;
	
	@FindBy(xpath = "//button[text()='Assign Label']")
	private WebElement assignLabelBtn;
	
	@FindBy(xpath = "//button[contains(@aria-label,'Time Period Filter:')]")
	private WebElement thisQuaterBtn;
	
	@FindBy(xpath = "//button[contains(@aria-label,'Me')]")
	private WebElement meBtn;
	
	@FindBy(xpath = "//button[@title='Important Leads']")
	private WebElement importnatLeadsBtn;
	
	@FindBy(xpath = "//button[@title='Show filters']")
	private WebElement showFiltersBtn;
	
	@FindBy(xpath = "//p[.='Total Leads']")
	private WebElement totalLeadsTxt;
	
	@FindBy(xpath = "//p[.='No Activity']")
	private WebElement noActivityTxt;
	
	@FindBy(xpath = "//p[.='Idle']")
	private WebElement idleTxt;
	
	@FindBy(xpath = "//p[.='No Upcoming']")
	private WebElement noUpcomingTxt;
	
	@FindBy(xpath = "//p[.='Overdue']")
	private WebElement overdueTxt;
	
	@FindBy(xpath = "//p[.='Due Today']")
	private WebElement dueTodayTxt;
	
	@FindBy(xpath = "//p[.='Upcoming']")
	private WebElement upcomingTxt;
	
	@FindBy(xpath = "//button[@title='Select a List View: Leads']")
	private WebElement myLeadsBtn;
	
	public LeadsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
		webUtil= new WebDriverMethods(driver);
	}
	
	public WebElement getMyLeadsBtn() {
		return myLeadsBtn;
	}
	
	public WebDriverMethods getWebUtil() {
		return webUtil;
	}

	public WebElement getTotalLeadsTxt() {
		return totalLeadsTxt;
	}

	public WebElement getNoActivityTxt() {
		return noActivityTxt;
	}

	public WebElement getIdleTxt() {
		return idleTxt;
	}

	public WebElement getNoUpcomingTxt() {
		return noUpcomingTxt;
	}

	public WebElement getOverdueTxt() {
		return overdueTxt;
	}

	public WebElement getDueTodayTxt() {
		return dueTodayTxt;
	}

	public WebElement getUpcomingTxt() {
		return upcomingTxt;
	}

	public WebElement getSettingsBtn() {
		return settingsBtn;
	}

	public WebElement getRefreshBtn() {
		return refreshBtn;
	}

	public WebElement getEditListBtn() {
		return editListBtn;
	}

	public WebElement getNewBtn() {
		return newBtn;
	}

	public WebElement getListViewBtn() {
		return listViewBtn;
	}

	public WebElement getAddToCampgainBtn() {
		return addToCampgainBtn;
	}

	public WebElement getChangeStatusBtn() {
		return changeStatusBtn;
	}

	public WebElement getChangeOwnerBtn() {
		return changeOwnerBtn;
	}

	public WebElement getSendEmailBtn() {
		return sendEmailBtn;
	}

	public WebElement getAssignLabelBtn() {
		return assignLabelBtn;
	}

	public WebElement getThisQuaterBtn() {
		return thisQuaterBtn;
	}

	public WebElement getMeBtn() {
		return meBtn;
	}

	public WebElement getImportnatLeadsBtn() {
		return importnatLeadsBtn;
	}

	public WebElement getShowFiltersBtn() {
		return showFiltersBtn;
	}
	
	public void clickLead() {
		  webUtil.waitUntilElementClickble(getLeadsBtn());
	       webUtil.clickOnElementUsingJS(getLeadsBtn());
	}
	
	public void leadObjVerification() {
		clickLead();
		webUtil.explicitlyWaitUntilElementVisible(newBtn);
		Assert.assertEquals(true, getSettingsBtn().isDisplayed());
		Assert.assertEquals(true, getRefreshBtn().isDisplayed());
		Assert.assertEquals(true, getEditListBtn().isDisplayed());
		Assert.assertEquals(true, getNewBtn().isDisplayed());
		Assert.assertEquals(true, getListViewBtn().isDisplayed());
		Assert.assertEquals(true, getThisQuaterBtn().isDisplayed());
		Assert.assertEquals(true, getMeBtn().isDisplayed());
		Assert.assertEquals(true, getImportnatLeadsBtn().isDisplayed());
		Assert.assertEquals(true, getShowFiltersBtn().isDisplayed());
		Assert.assertEquals(true, getAddToCampgainBtn().isDisplayed());
		Assert.assertEquals(true, getChangeStatusBtn().isDisplayed());
		Assert.assertEquals(true, getChangeOwnerBtn().isDisplayed());
		Assert.assertEquals(true, getSendEmailBtn().isDisplayed());
		Assert.assertEquals(true, getAssignLabelBtn().isDisplayed());
		Assert.assertEquals(true, getTotalLeadsTxt().isDisplayed());
		Assert.assertEquals(true, getNoActivityTxt().isDisplayed());
		Assert.assertEquals(true, getIdleTxt().isDisplayed());
		Assert.assertEquals(true, getUpcomingTxt().isDisplayed());
		Assert.assertEquals(true, getOverdueTxt().isDisplayed());
		Assert.assertEquals(true, getDueTodayTxt().isDisplayed());
		Assert.assertEquals(true, getUpcomingTxt().isDisplayed());
		Assert.assertEquals(true, webUtil.isClickable(myLeadsBtn));
		   System.out.println("Lead Object Verified");

	}

}
