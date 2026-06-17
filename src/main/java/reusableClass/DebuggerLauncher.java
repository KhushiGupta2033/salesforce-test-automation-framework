package reusableClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class DebuggerLauncher {

    private static final String CHROME_PATH =
            "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

    private static final String USER_DATA_DIR =
            "C:\\Salesforce";

    private static final int DEBUGGER_PORT = 52202;

    public static WebDriver getChromeWithDebugger() throws Exception {

        // STEP 1: Kill only if port is in use
        killExistingDebuggerPortIfNeeded();

        // STEP 2: Launch Chrome with debugger
        ProcessBuilder pb = new ProcessBuilder(
                CHROME_PATH,
                "--remote-debugging-port=" + DEBUGGER_PORT,
                "--user-data-dir=" + USER_DATA_DIR
//                "--start-maximized"
               
        );

        pb.start();

        System.out.println("Chrome launched on port: " + DEBUGGER_PORT);

        // Wait for Chrome to be ready
        Thread.sleep(5000);

        // STEP 3: Attach Selenium
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(
                "debuggerAddress",
                "127.0.0.1:" + DEBUGGER_PORT
        );

        WebDriver driver = new ChromeDriver(options);

        System.out.println("Selenium connected to Chrome debugger.");

        return driver;
    }

    /**
     * Check if port is in use
     */
    private static boolean isPortInUse(int port) {
        try (Socket socket = new Socket("127.0.0.1", port)) {
            return true; // Port is in use
        } catch (Exception e) {
            return false; // Port is free
        }
    }

    /**
     * Kill process only if port is occupied
     */
    @SuppressWarnings("deprecation")
	private static void killExistingDebuggerPortIfNeeded() {

        try {

            if (!isPortInUse(DEBUGGER_PORT)) {
//                System.out.println("Port " + DEBUGGER_PORT + " is free. No need to kill.");
                return;
            }

//            System.out.println("Port " + DEBUGGER_PORT + " is in use. Killing existing process...");

            String command =
                    "cmd /c for /f \"tokens=5\" %a in ('netstat -aon ^| findstr :"
                            + DEBUGGER_PORT +
                            "') do taskkill /F /PID %a";

            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(process.getInputStream()));

//            String line;
//
//            while ((line = reader.readLine()) != null) {
//               System.out.println("Killed PID: " + line);
//            }

            // Give OS time to release port
            Thread.sleep(3000);

            System.out.println("Old debugger session cleared.");

        } catch (Exception e) {
            System.out.println("Error while killing process: " + e.getMessage());
        }
    }
}