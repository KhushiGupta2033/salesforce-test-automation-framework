package demo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.function.Function;

public class CentralizedWaitManager {

    private final WebDriver driver;
    private final int MAX_TIMEOUT_SECONDS = 90;
    private final int STABILITY_WINDOW_MS = 2000;
    private final int POLLING_MS = 500;

    public CentralizedWaitManager(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilApplicationStable() {
        // Capture URL at the start to detect transition
        String initialUrl = driver.getCurrentUrl();

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(MAX_TIMEOUT_SECONDS))
                .pollingEvery(Duration.ofMillis(POLLING_MS))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);

        wait.until(new StabilityCondition(initialUrl));
    }

    private class StabilityCondition implements Function<WebDriver, Boolean> {
        private long stableStartTime = -1;
        private String lastContentHash = "";
        private final String startingUrl;

        public StabilityCondition(String startingUrl) {
            this.startingUrl = startingUrl;
        }

        @Override
        public Boolean apply(WebDriver driver) {
            // 1. Detect if we are in the "Redirect Gap" (Scenario 1)
            // If the URL hasn't changed yet after a login click, we aren't stable.
            if (driver.getCurrentUrl().equals(startingUrl)) {
                // Note: Only use this if you KNOW a navigation is supposed to happen.
                // For "Save" buttons (Scenario 2), we ignore this.
            }

            boolean ready = isDocumentReady() 
                            && isNetworkIdle() 
                            && !isUiBlocked() 
                            && isVisualContentStable();

            if (ready) {
                if (stableStartTime == -1) stableStartTime = System.currentTimeMillis();
                return (System.currentTimeMillis() - stableStartTime) >= STABILITY_WINDOW_MS;
            }

            stableStartTime = -1; // Reset if any check fails
            return false;
        }

        private boolean isDocumentReady() {
            return ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").equals("complete");
        }

        private boolean isNetworkIdle() {
            // Updated to handle both jQuery and modern Fetch/XHR
            String script = 
                "var jqueryIdle = window.jQuery ? jQuery.active === 0 : true;" +
                "var fetchIdle = window.performance.getEntriesByType('resource')" +
                "    .filter(r => r.initiatorType === 'fetch' || r.initiatorType === 'xmlhttprequest')" +
                "    .every(r => r.responseEnd > 0);" + 
                "return jqueryIdle && fetchIdle;";
            return (Boolean) ((JavascriptExecutor) driver).executeScript(script);
        }

        private boolean isVisualContentStable() {
            // Instead of just counting tags, we hash the text content length and structure
            // This detects if text is still loading into existing elements
            String currentHash = (String) ((JavascriptExecutor) driver)
                    .executeScript("return document.body.innerText.length + '-' + document.getElementsByTagName('*').length;");
            
            boolean stable = currentHash.equals(lastContentHash);
            lastContentHash = currentHash;
            return stable;
        }

        private boolean isUiBlocked() {
            // Improved to check for 'pointer-events' which is how modern overlays actually block you
            String script = 
                "var el = document.elementFromPoint(window.innerWidth/2, window.innerHeight/2);" +
                "if(!el) return false;" +
                "var style = window.getComputedStyle(el);" +
                "return (style.position === 'fixed' || style.position === 'absolute') " +
                "&& (parseInt(style.zIndex) > 100 || style.pointerEvents === 'all' && el.tagName === 'DIV' && el.innerText === '');";
            return (Boolean) ((JavascriptExecutor) driver).executeScript(script);
        }
    }
}