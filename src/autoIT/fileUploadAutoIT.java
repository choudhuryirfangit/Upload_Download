package autoIT;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class fileUploadAutoIT {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String downloadPath=System.getProperty("user.dir");
//		System.setProperty("webdriver.chrome.driver","C:\\work\\chromedriver.exe");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);

		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);

		WebDriver driver=new ChromeDriver(options);
		driver.get("https://www.adobe.com/in/acrobat/online/pdf-to-jpg.html");
		driver.findElement(By.cssSelector("div[class*='FileUpload']")).click();

		Thread.sleep(3000);

		Runtime.getRuntime().exec("C:\\Selenium_2024\\AutoIT\\fileUpload.exe");

		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='react-aria9642067605-:rh:']")));
		driver.findElement(By.cssSelector("button[id='react-aria9642067605-:rh:']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='react-aria4201161606-:r1a:']")));
		driver.findElement(By.cssSelector("button[id='react-aria4201161606-:r1a:']")).click();

		Thread.sleep(5000);

		File f=new File(downloadPath+"/converted.zip");
		if(f.exists())

		{
		Assert.assertTrue(f.exists());
		System.out.println("File downloaded successfully");
//		if(f.delete())
//		System.out.println("file deleted");

		}

		}

	}


