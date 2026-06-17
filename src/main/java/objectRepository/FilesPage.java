package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import WebDriverUtilities.WebDriverMethods;

public class FilesPage extends HeaderPage {
	WebDriverMethods webUtil;
	SoftAssert sf= new SoftAssert();
	
	@FindBy(xpath = "//a[@title='Owned by Me']")
	private WebElement ownedByMeTxt;
	
	@FindBy(xpath = "//a[@title='Shared with Me']")
	private WebElement sharedWithMeTxt;
	
	@FindBy(xpath = "//a[@title='Recent']")
	private WebElement recentTxt;
	
	@FindBy(xpath = "//a[@title='Following']")
	private WebElement followingTxt;
	
	@FindBy(xpath = "//a[@title='Libraries']")
	private WebElement librariesTxt;
	
	@FindBy(xpath = "//a[@title='Upload Files']")
	private WebElement uploadFilesBtn;
	
	public FilesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		webUtil= new WebDriverMethods(driver);
	}

	public WebElement getOwnedByMeTxt() {
		return ownedByMeTxt;
	}

	public WebElement getSharedWithMeTxt() {
		return sharedWithMeTxt;
	}

	public WebElement getRecentTxt() {
		return recentTxt;
	}

	public WebElement getFollowingTxt() {
		return followingTxt;
	}

	public WebElement getLibrariesTxt() {
		return librariesTxt;
	}

	public WebElement getUploadFilesBtn() {
		return uploadFilesBtn;
	}
	
	public void clickFiles() {
		webUtil.waitUntilElementClickble(getFilesBtn());
		webUtil.clickOnElementUsingJS(getFilesBtn());
	}
	
	public void fileObjectVerification() {
		clickFiles();
		webUtil.explicitlyWaitUntilElementVisible(uploadFilesBtn);
		sf.assertTrue(getUploadFilesBtn().isDisplayed());
		sf.assertTrue(getOwnedByMeTxt().isDisplayed());
		sf.assertTrue(getSharedWithMeTxt().isDisplayed());
		sf.assertTrue(getRecentTxt().isDisplayed());
		sf.assertTrue(getFollowingTxt().isDisplayed());
		sf.assertTrue(getLibrariesTxt().isDisplayed());
		System.out.println("Files object verification completed");
		sf.assertAll();
	}
	

}
