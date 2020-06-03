package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BaseTest {
	public WebDriver driver;

	@BeforeTest
	public void initDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--no-sandbox"); //
//		option.addArguments("--headless");
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshot() {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		return scrShot.getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "Log", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}

	@Attachment(value = "Export File")
	public List<String> saveExportFile(String filePath, String charsetName) throws Exception {
		File file = new File(filePath);

		Charset charset = null;

		if ("UTF-8".equals(charsetName)) {
			charset = StandardCharsets.UTF_8;
		} else {
			charset = Charset.forName(charsetName);
		}
		return Files.readAllLines(Paths.get(file.getAbsolutePath()), charset);
	}

	@AfterMethod
	public void cleanOrKeepTrack(ITestResult result) {
		// if (result.getStatus() == ITestResult.FAILURE) {
		saveScreenshot();
		// }
	}
}
