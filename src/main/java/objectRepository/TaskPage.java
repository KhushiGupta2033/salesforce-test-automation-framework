package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import WebDriverUtilities.WebDriverMethods;
import junit.framework.Assert;
import reusableClass.CommonLocators;

public class TaskPage extends HeaderPage{
	CommonLocators cl;
	WebDriverMethods webUtil;
	SoftAssert sf= new SoftAssert();
	
@FindBy(xpath = "(//button[@title='This list is pinned.'])[last()]") private WebElement pinBtn;

@FindBy(xpath = "(//button[@title='Select list display'])[last()]") private WebElement selectListDisplayBtn;

@FindBy(xpath = "(//button[@title='Refresh'])[last()]") private WebElement refresh;

@FindBy(xpath = "//button[@title='Select list display']") private WebElement searchListBtn ;

@FindBy(xpath = "//button[@title='Select a List View: Tasks']") private WebElement listViewBtn;

@FindBy(xpath = "//a[@title='Show 2 more actions']") private WebElement showActionsBtn;

@FindBy(xpath = "//a[@title='New Task']") private WebElement newTaskBtn;

@FindBy(xpath = "(//a[@title='Assign Label'])[last()]") private WebElement assignLabelBtn;

public TaskPage(WebDriver driver) {
	super(driver);
	PageFactory.initElements(driver,this);
	cl= new CommonLocators(driver);
	webUtil = new WebDriverMethods(driver);
}

public WebElement getPinBtn() {
	return pinBtn;
}

public WebElement getSelectListDisplayBtn() {
	return selectListDisplayBtn;
}

public WebElement getRefresh() {
	return refresh;
}

public WebElement getSearchListBtn() {
	return searchListBtn;
}

public WebElement getListViewBtn() {
	return listViewBtn;
}

public void clickTask() {
	  webUtil.waitUntilElementClickble(getTaskBtn());
     webUtil.clickOnElementUsingJS(getTaskBtn());
}

public CommonLocators getCl() {
	return cl;
}

public WebDriverMethods getWebUtil() {
	return webUtil;
}

public WebElement getShowActionsBtn() {
	return showActionsBtn;
}

public WebElement getNewTaskBtn() {
	return newTaskBtn;
}

public WebElement getAssignLabelBtn() {
	return assignLabelBtn;
}
	
public void taksObjectVerification() {
	clickTask();
	webUtil.explicitlyWaitTime(2000);
    webUtil.refreshPage();
    webUtil.explicitlyWaitTime(2000);
    webUtil.waitForPageLoad();
	webUtil.explicitlyWaitUntilElementVisible(searchListBtn);
	sf.assertTrue(getSearchListBtn().isDisplayed());
	sf.assertTrue(getPinBtn().isDisplayed());
	sf.assertTrue(getRefresh().isDisplayed());
	sf.assertTrue(getSearchListBtn().isDisplayed());
	sf.assertTrue(getShowActionsBtn().isDisplayed());
	webUtil.clickOnElementUsingJS(showActionsBtn);
	webUtil.explicitlyWaitUntilElementVisible(newTaskBtn);
	sf.assertTrue(getNewTaskBtn().isDisplayed());
	sf.assertTrue(getAssignLabelBtn().isDisplayed());
	webUtil.clickOnElementUsingJS(showActionsBtn);
	webUtil.clickOnElementUsingJS(listViewBtn);
	webUtil.explicitlyWaitUntilElementVisible(cl.getListViewsTxt());
	Assert.assertEquals(true,cl.getListViewsTxt().isDisplayed());
	   System.out.println("Task Object Verified");
	   sf.assertAll();


}

}
