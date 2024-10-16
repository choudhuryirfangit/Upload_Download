package autoIT;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class fileUploadByAutoIT {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.adobe.com/in/acrobat/online/pdf-to-jpg.html");
		Thread.sleep(2000);
		driver.findElement(By.linkText("Home")).click();
		driver.findElement(By.xpath("//div[@class='FileUpload__chooseButtonContainer___FqbaR']")).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec("C:\\Selenium_2024\\AutoIT\\fileUpload.exe");
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='react-aria9642067605-:rh:']")));
		driver.findElement(By.cssSelector("button[id='react-aria9642067605-:rh:']")).click();
		
		
		
		
	
	}

}
