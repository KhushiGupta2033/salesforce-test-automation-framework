package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage {
	
	@FindBy(xpath = "//button[@title='App Launcher']")
	private WebElement appLauncherBtn;
	
	@FindBy(xpath = "//input[@placeholder='Search apps and items...']")
	private WebElement searchAppTf;
	
	@FindBy(xpath = "//a[@data-label='Sales']")
	private WebElement salesBtn;
	
	@FindBy(xpath = "//a[@title='Opportunities']")
	private WebElement oppBtn;
	
	@FindBy(xpath = "//a[@title='Leads']")
	private WebElement leadsBtn;
	
	@FindBy(xpath = "//a[@title='Tasks']")
	private WebElement taskBtn;
	
	@FindBy(xpath = "//a[@title='Files']")
	private WebElement filesBtn;
	
	@FindBy(xpath = "//a[@title='Accounts']")
	private WebElement accountsBtn;
	
	@FindBy(xpath = "//a[@title='Contacts']")
	private WebElement contactsBtn;
	
	@FindBy(xpath = "//a[@title='Campaigns']")
	private WebElement campgBtn;
	
	@FindBy(xpath = "//a[@title='Dashboards']")
	private WebElement dashboardsBtn;
	
	@FindBy(xpath = "//a[@title='Reports']")
	private WebElement reportsBtn;
	
	@FindBy(xpath = "//a[@title='Chatter']")
	private WebElement chatterBtn;
	
	@FindBy(xpath = "//a[@title='//span[.='More']/..']")
	private WebElement moreBtn;
	
	@FindBy(xpath = "//a[@title='Groups']")
	private WebElement groupsBtn;
	
	@FindBy(xpath = "//a[@title='Calendar']")
	private WebElement calendarBtn;
	
	@FindBy(xpath = "//a[@title='People']")
	private WebElement peopleBtn;
	
	@FindBy(xpath = "//a[@title='Cases']")
	private WebElement casesBtn;
	
	@FindBy(xpath = "//a[@title='Forecasts']")
	private WebElement forecastsBtn;

	@FindBy(xpath = "//a[@title='Quotes']")
	private WebElement quotesBtn;
	
	
	public HeaderPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public WebElement getAppLauncherBtn() {
		return appLauncherBtn;
	}

	public WebElement getSearchAppTf() {
		return searchAppTf;
	}

	public WebElement getSalesBtn() {
		return salesBtn;
	}

	public WebElement getOppBtn() {
		return oppBtn;
	}

	public WebElement getLeadsBtn() {
		return leadsBtn;
	}

	public WebElement getTaskBtn() {
		return taskBtn;
	}

	public WebElement getFilesBtn() {
		return filesBtn;
	}

	public WebElement getAccountsBtn() {
		return accountsBtn;
	}

	public WebElement getContactsBtn() {
		return contactsBtn;
	}

	public WebElement getCampgBtn() {
		return campgBtn;
	}

	public WebElement getDashboardsBtn() {
		return dashboardsBtn;
	}

	public WebElement getReportsBtn() {
		return reportsBtn;
	}

	public WebElement getChatterBtn() {
		return chatterBtn;
	}

	public WebElement getMoreBtn() {
		return moreBtn;
	}

	public WebElement getGroupsBtn() {
		return groupsBtn;
	}

	public WebElement getCalendarBtn() {
		return calendarBtn;
	}

	public WebElement getPeopleBtn() {
		return peopleBtn;
	}

	public WebElement getCasesBtn() {
		return casesBtn;
	}

	public WebElement getForecastsBtn() {
		return forecastsBtn;
	}

	public WebElement getQuotesBtn() {
		return quotesBtn;
	}


}
