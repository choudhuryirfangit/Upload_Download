import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UploadDownload {

	public static void main(String[] args) throws IOException, InterruptedException {
		String fruitName="Papaya";
		String fileName="C:\\Selenium_2024\\download.xlsx";
		String updatedValue="777";
		
		int col=getColNum(fileName,"price");
		int row=getRowNum(fileName,fruitName);
		Assert.assertTrue(updateCell(fileName,col,row,updatedValue));
		
		
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();		
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		
		//Download
		driver.findElement(By.cssSelector("#downloadButton")).click();
		Thread.sleep(3000);
		//Upload
		WebElement upload=driver.findElement(By.cssSelector("input[type='file']"));
		upload.sendKeys(fileName);
		
		//wait for popup message to disappear
		By toastLocator=By.cssSelector(".Toastify__toast-body div:nth-child(2)");
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
		String toastText=driver.findElement(toastLocator).getText();
		System.out.println(toastText);
		Assert.assertEquals("Updated Excel Data Successfully.", toastText);
		w.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
		
		String priceColumn= driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		String actualPrice=driver.findElement(By.xpath("//div[text()='"+fruitName+"']/parent::div/parent::div/div[@data-column-id='"+priceColumn+"']")).getText();
		
		System.out.println(priceColumn);
		System.out.println(actualPrice);
		Assert.assertEquals(actualPrice, updatedValue);
		driver.quit();
		}


	private static boolean updateCell(String fileName, int col, int row, String updatedValue) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> a=new ArrayList<String>();
		FileInputStream fis=new FileInputStream(fileName);
		XSSFWorkbook workBook=new XSSFWorkbook(fis);
//		int sheets=workBook.getNumberOfSheets();
		XSSFSheet sheet=workBook.getSheet("Sheet1");
		Row rowField=sheet.getRow(row-1);
		Cell cellfield=rowField.getCell(col-1);
		cellfield.setCellValue(updatedValue);
		FileOutputStream fos=new FileOutputStream(fileName);
		workBook.write(fos);
		workBook.close();
		fis.close();
		return true;
		
	}


	private static int getRowNum(String fileName, String text) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> a=new ArrayList<String>();
		FileInputStream fis=new FileInputStream(fileName);
		XSSFWorkbook workBook=new XSSFWorkbook(fis);
		//int sheets=workBook.getNumberOfSheets();
		XSSFSheet sheet=workBook.getSheet("Sheet1");
		
		Iterator<Row> rows= sheet.iterator();
		int k=1;
		int rowIndex=-1;
		while(rows.hasNext()) {
			
			Row row=rows.next();
			Iterator<Cell> cells=row.cellIterator();
			
			while(cells.hasNext()) {
				Cell cell=cells.next();
				if(cell.getCellType()==CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(text)) {
					rowIndex=k;
				}
			}
			k++;
		}
		System.out.println(rowIndex);
		return rowIndex;
		
		
	}

	private static int getColNum(String fileName, String colName) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> a=new ArrayList<String>();
		FileInputStream fis=new FileInputStream(fileName);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
//		int sheets=workBook.getNumberOfSheets();
		XSSFSheet sheet=workBook.getSheet("Sheet1");
				
//				Indentify TestCase column by scanning entire first row
				Iterator<Row> rows= sheet.iterator(); //sheet is collection of rows
				Row firstRow=rows.next();
				Iterator<Cell> ce= firstRow.cellIterator(); //row is collection of cells
				int k=1;
				int column=0;
				while (ce.hasNext()) {
					Cell value=ce.next();
					if(value.getStringCellValue().equalsIgnoreCase(colName)) {
						column=k;
					}
					k++;
				}
				System.out.println(column);

		return column;
	}
	}

