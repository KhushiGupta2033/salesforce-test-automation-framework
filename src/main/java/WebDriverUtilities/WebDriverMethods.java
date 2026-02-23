package WebDriverUtilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput.ScrollOrigin;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverMethods {
	
	WebDriver driver = null;
	int impWaitTime = 20;
	int explicitWaitTime = 20;
	Actions act = new Actions(driver);
	
	//constructor
	public WebDriverMethods(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}
	
	public void explicitlyWaitTime(int sec) {
		explicitWaitTime = sec;
	}
	
	public void implicitlyWaitTime(int sec) {
		impWaitTime = sec;
	}

	public int getImplicitWait() {
		return impWaitTime;
	}
	
	public void maximizeBrowserWindow() {
		driver.manage().window().maximize();
	}
	
	public void minimizeBrowserWindow() {
		driver.manage().window().minimize();
	}
	
	public void fullScreenBrowserWindow() {
		driver.manage().window().fullscreen();
	}
	
	public Point getPositionOfCurrentWindow() {
		Point position = driver.manage().window().getPosition();
		return position;
	}
	
	public void setPositionOfCurrentWindow(int x, int y) {
		driver.manage().window().setPosition(new Point(x,y));
	}
	
	public void implicitlyWaitForSeconds() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(impWaitTime));
	}
	
	public void waitForPageLoad() {
		//Wait up to seconds for the page to finish loading
		//generally used for get(url), to(), navogate().refresh()
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(impWaitTime));
	}
	
	public String getPageSource() {
		return driver.getPageSource();
	}
	
	public void explicitlyWaitUntilElementVisible(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public boolean waitUntilElementClickble(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean waitUntilTitleContainsExpectedTitle(String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
		try {
			return wait.until(ExpectedConditions.titleContains(title));
		}catch(Exception e) {
			return false;
		}
			
	}
	public boolean waitUntilPageTitleMatchesExpectedTitle(String tilte) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
		try {
			return wait.until(ExpectedConditions.titleIs(tilte));
		}catch(Exception e) {
			return false;
		}
	}
	
	
	public boolean waitUntilUrlContainsExpectedUrl(String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
		try {
			return wait.until(ExpectedConditions.urlContains(url));
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean waitUntilTextOfElementContainsExpectedString(WebElement ele, String expData) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
		try {
			return wait.until(ExpectedConditions.textToBePresentInElement(ele, expData));
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean waitUntilUrlMatchesExpectedUrl(String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
		try {
			return wait.until(ExpectedConditions.urlMatches(url));
		}catch(Exception e) {
			return false;
		}
	}
	
	
	public void waitForSeconds(int sec) throws InterruptedException {
		Thread.sleep(sec);
	}
	
	public void clickOnCurrentCursorPoint() throws AWTException {
		Robot robot = new Robot();
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	public void doubleClickOnCurrentCursorPoint() throws AWTException {
		Robot robot = new Robot();
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
		robot.delay(50);
		
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	public void clickOnElementForNumOfTime(int num) throws AWTException {
		Robot robot = new Robot();
		for(int i=0;i<num;i++) {
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			robot.delay(50);
		}
	}
	
	public void clickOnElement(WebElement ele) {
		act.click(ele).perform();
	}
	
	public void clickOnElementUsingJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

	}
	
	public void enterInputIntoElement(WebElement ele, Object value) {
//		act.sendKeys(ele, value);
	}
	
	public void enterInputIntoElementUsingJS(WebElement element, Object value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].value = arguments[1];",
            element,
            value
        );
        
	}
	
	public void doubleClickOnElement(WebElement element) {
		act.doubleClick(element).perform();
	}
	
	public void doubleClickOnElementUsingJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
		    "arguments[0].dispatchEvent(new MouseEvent('dblclick', {bubbles: true, cancelable: true}));",
		    element
		);

	}
	
	public void contextClickOnElement(WebElement element) {
		act.contextClick(element).perform();
	}
	
	public void moveCurserPointToX_Y(int x, int y) { //moveByOffset
		act.moveByOffset(x, y).perform();
	}
	
	
	public void clickAndHoldOnElement(WebElement element) {
		act.clickAndHold(element).perform();
	}
	
	
	public void release() {
		act.release().perform();
	}
	
	public void moveCursorToElement(WebElement ele) {
		act.moveToElement(ele).perform();
	}
	
	public void moveCursorToElementX_Y(WebElement element, int x, int y) {
		act.moveToElement(element, x, y).perform();
	}
	
	public void scrollPageUntilElementVisibleInUI(WebElement element) {
		act.scrollToElement(element).perform();
	}
	
	public void clickInsideElementByValue(WebElement dropDownEle, WebElement valueEle) { // for dropdown
		((JavascriptExecutor) driver).executeScript(
			    "arguments[1].scrollIntoView({behavior: 'auto', block: 'center'});",
			    dropDownEle, valueEle
			);
			valueEle.click();
	}
	
	public void scrollInsideContainer(WebElement ele, int x, int y) {
		act.scrollFromOrigin(ScrollOrigin.fromElement(ele), x, y).perform();
	}
	
	public void scrollByPixelsX_Y(int x, int y) {
		act.scrollByAmount(x, y).perform();
	}
	
	public void scrollBottomOfWebPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}
	
	public void scrollHorizonatlAndVerticalUntilEleVisible(WebElement element) {
		    ((JavascriptExecutor) driver).executeScript(
		        "arguments[0].scrollIntoView({block:'center', inline:'center'});",
		        element
		    );
		
	}
	
	/**
     * @param driver    WebDriver instance
     * @param direction "UP", "DOWN", "LEFT", "RIGHT"
     * @param pixels    number of pixels to scroll
     */
    public  void scroll(WebDriver driver, String direction, int pixels) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        int x = 0;
        int y = 0;

        switch (direction.toLowerCase()) {
        case "up":
        case "top":
            y = -pixels;
            break;
        case "down":
        case "bottom":
            y = pixels;
            break;
        case "left":
            x = -pixels;
            break;
        case "right":
            x = pixels;
            break;
        default:
            throw new IllegalArgumentException("Invalid direction");
    }
        js.executeScript(
                "window.scrollBy(arguments[0], arguments[1]);",
                x, y
        );
    }
	  
    
    
}


