package reusableClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonLocators {
	
	@FindBy(xpath = "//h3[contains(@title,'List Views')]")
	private WebElement listViewsTxt;
	
	@FindBy(xpath = "//h3[contains(@title,'All Other Lists')]")
	private WebElement allOtherListTxt;
	
	@FindBy(xpath = "//button[contains(@title,'Select a List View: ')]")
	private WebElement recentlyViewedBtn;
	
	@FindBy(xpath = "//input[@placeholder='Search this list...']")
	private WebElement searchTheListTF;

	@FindBy(xpath = "//button[@title='List View Controls']")
	private WebElement settingBtn;
	
	@FindBy(xpath = "//button[@title='Select list display']")
	private WebElement selectListDisplayBtn;

	@FindBy(xpath = "(//button[@title='Refresh'])[1]")
	private WebElement refreshBtn;

	@FindBy(xpath = "//button[@title='Column sort']")
	private WebElement columnSortBtn;
	
	@FindBy(xpath = "//button[@title='Edit List']")
	private WebElement editListBtn;
	

	public CommonLocators(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public WebElement getRecentlyViewedBtn() {
		return recentlyViewedBtn;
	}

	public WebElement getListViewsTxt() {
		return listViewsTxt;
	}

	public WebElement getAllOtherListTxt() {
		return allOtherListTxt;
	}

	public WebElement getSearchTheListTF() {
		return searchTheListTF;
	}

	public WebElement getSettingBtn() {
		return settingBtn;
	}

	public WebElement getSelectListDisplayBtn() {
		return selectListDisplayBtn;
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
			

}
