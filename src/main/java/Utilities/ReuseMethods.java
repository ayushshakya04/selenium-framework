package Utilities;



import java.io.BufferedReader;
import java.io.Reader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReuseMethods {

	public final int numberOfSecondsForImplicitWait;
	public Actions act;
	public WebDriver driver;
	private int elementWaitTime;
	String mainWindow;
	private String parentWindowHandle;
	public Connection con;
	public Statement stmt;
	public Logger logger;


	
	public ReuseMethods() 
	{
		this.numberOfSecondsForImplicitWait = 20;
		this.elementWaitTime = 20;
		this.mainWindow = null;
	}

	public Logger getlogger() {
		return this.logger;
	}
	
	public void addinfolog(String message) {
		this.logger=Logger.getLogger("FinOps MCD");
		PropertyConfigurator.configure("log4j.properties");
		this.logger.info(message);
	}
	
	public void addinfologException(Exception message) {
		this.logger=Logger.getLogger("FinOps MCD");
		PropertyConfigurator.configure("log4j.properties");
		this.logger.error(message);
	}
	
	public void addinfologArray(String message,List<String> Array) {
		this.logger=Logger.getLogger("FinOps MCD");
		PropertyConfigurator.configure("log4j.properties");
		this.logger.info(message+Array);
	}
	
	public Statement getstatement() throws ClassNotFoundException, SQLException {
		
		 String url = "jdbc:mysql://mcd-finops-prod-db.mysql.database.azure.com/finopsmcd";
		 String user = "testteam";
		 String password = "test@123";
		 String dbdriver = "com.mysql.cj.jdbc.Driver";
		 
		 Class.forName(dbdriver);
		 
		 con=DriverManager.getConnection(url,user,password);
		 stmt=con.createStatement();
		 return stmt;
		
	}
	

	public ResultSet getdata(String sql) throws ClassNotFoundException, SQLException {
		
		ResultSet data=getstatement().executeQuery(sql);
		return data;
		
	}
	
	public String capturedatabsesinglecolumnvalue(String sql,String columnname) throws ClassNotFoundException, SQLException{
		 String cost = null;
		 String finalcost=null;
			ReuseMethods db=new ReuseMethods();
			ResultSet data=db.getdata(sql);
			System.out.println(data);
			
			while (data.next()) {
			cost=data.getString(columnname);
			if(cost==null) {
				finalcost="0.00";
			}else {
				cost=cost.replaceAll("[^0-9 .]", "");
				finalcost=roundingDBStringValue(cost);
			}
			}
			return finalcost;
		}
	public String capturedatabsesinglecolumncostByIndex(String sql,int columnnumber) throws ClassNotFoundException, SQLException{
		 String cost = null;
		 String finalcost=null;
			ReuseMethods db=new ReuseMethods();
			ResultSet data=db.getdata(sql);
			System.out.println(data);
			
			while (data.next()) {
			cost=data.getString(columnnumber);
			if(cost==null) {
				finalcost="0.00";
			}else {
				cost=cost.replaceAll("[^0-9 .]", "");
				finalcost=roundingDBStringValue(cost);
			}
			}
			return finalcost;
		}
	
	public String capturedatabsesinglecolumncostByIndexdataformatter(String sql,int columnnumber) throws ClassNotFoundException, SQLException{
		 String cost = null;
		 String finalcost=null;
			ReuseMethods db=new ReuseMethods();
			ResultSet data=db.getdata(sql);
			System.out.println(data);
			
			while (data.next()) {
			BigDecimal totalOrphanedSavings = data.getBigDecimal(columnnumber);
			DecimalFormat df = new DecimalFormat("0.##############################"); // Adjust the number of # symbols as needed
			cost = df.format(totalOrphanedSavings);
			if(cost==null) {
				finalcost="0.00";
			}else {
				cost=cost.replaceAll("[^0-9 .]", "");
				finalcost=roundingDBStringValue(cost);
			}
			}
			return finalcost;
		}
	
	public String capturedatabsesinglecolumncountByIndex(String sql,int columnnumber) throws ClassNotFoundException, SQLException{
		 String modifiecost = null;		
			ReuseMethods db=new ReuseMethods();
			ResultSet data=db.getdata(sql);
			System.out.println(data);
			
			while (data.next()) {
				
				String cost=data.getString(columnnumber);
				modifiecost=cost;
			}
			return modifiecost;
		}
	public String capturedatabsesinglecolumnvaluecount(String sql,String columnname) throws ClassNotFoundException, SQLException{
		 String modifiecost = null;		
			ReuseMethods db=new ReuseMethods();
			ResultSet data=db.getdata(sql);
			System.out.println(data);
			
			while (data.next()) {
				
				String count=data.getString(columnname);
				modifiecost=count;
			}
			return modifiecost;
		}
	 
	 public void printCharCodes(String[] array) {
		    for (String str : array) {
		        System.out.println("String: \"" + str + "\"");
		        for (char c : str.toCharArray()) {
		            System.out.printf("Char: '%c' Code: %d%n", c, (int) c);
		        }
		        System.out.println();
		    }
	 }

	 public List<String[]> readCSVData(String csvFilePath) throws IOException {
	        List<String[]> data = new ArrayList<>();
	        BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] row = line.split(",");
	            data.add(row);
	        }
	        reader.close();
	        return data;
	    }
	 
	 public void readdata(String path) throws IOException{
		    List<String> column = new ArrayList();
			
			Reader reader = new FileReader(path);
	        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
	        List<String> headerMap = csvParser.getHeaderNames();
	        
	        for (String header : headerMap) {
	            column.add(header);
	        }
	        
	        List<String> columnrows = new ArrayList();
	    	
	        
	        for (CSVRecord csvRecord : csvParser) {
	            for (String header : headerMap) {
	                String value = csvRecord.get(header);
	                columnrows.add(value);
	            }
	        }
	 }
	 
	 public Map<String, List<String>> readCsvData(String path) throws InterruptedException{
		 Map<String, List<String>> columnData = new HashMap<>();
	        
	        try (Reader reader = new FileReader(path);
	             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
	            // Get header map (column names)
	            Map<String, Integer> headerMap = csvParser.getHeaderMap();
	            
	            // Initialize lists for each column
	            for (String header : headerMap.keySet()) {
	                columnData.put(header, new ArrayList<>());
	            }
	            // Process each record in the CSV
	            for (CSVRecord csvRecord : csvParser) {
	                for (String header : headerMap.keySet()) {
	                    String value = csvRecord.get(header);
	                    columnData.get(header).add(value);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        Thread.sleep(10000);
	        return columnData;
	 }

	public void setImplicitlyWait(int waitInSec) {
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitInSec));
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	public void refreshpage() {
		this.driver.navigate().refresh();
	}
	
	public String verifyfiledownloaded(String filenamewithextension) {
		String path = null;
		File downloadedFile = new File((System.getProperty("user.dir") + File.separator + "Downloads") + File.separator + filenamewithextension); // Adjust filename as per your download
		if (downloadedFile.exists()) {
			path=downloadedFile.getAbsolutePath();
			addinfolog("File downloaded successfully to: " + downloadedFile.getAbsolutePath());
        } else {
        	addinfolog("File download failed or file does not exist.");
        }
		return path;
	}
	
	public String verifyfiledownloadedandgetpath(String filenamewithextension) {
		String filepath;
		File downloadedFile = new File((System.getProperty("user.dir") + File.separator + "Downloads") + File.separator + filenamewithextension); // Adjust filename as per your download
		if (downloadedFile.exists()) {
			addinfolog("File downloaded successfully to: " + downloadedFile.getAbsolutePath());
        } else {
        	addinfolog("File download failed or file does not exist.");
        }
		filepath=downloadedFile.getAbsolutePath();
		return filepath;
	}

	public  WebDriver openbrowser() {
		String downloadDir = System.getProperty("user.dir") + File.separator + "Downloads";
        new File(downloadDir).mkdir();
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadDir);
        options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("profile.directory.profile=10");
		this.driver = (WebDriver)new ChromeDriver(options);
		this.driver.manage().window().maximize();
		setImplicitlyWait(numberOfSecondsForImplicitWait);
		return this.driver;
	}
	
	public void teardown()
	{
		try 
		{
			//this.logTestPass("Browser Closed Successfully");
			if (this.driver != null)
			{
				this.driver.quit();
			}
			this.driver = null;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void SetURL(String URL) {
		this.driver.get(URL);
	}
	
	
	public WebElement getElement(final By locator)
	{
		return this.driver.findElement(locator);
	}
	
	
	public void entertextbysendkeys(By locator, String text) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(elementWaitTime));
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
		element.sendKeys(text);
	}
	
	public void entertextbywait_elementclickable(By locator, String text) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(elementWaitTime));
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.clear();
		element.sendKeys(text);
	}
	
	public void clickaction(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(elementWaitTime));
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.click();
	}
	
	public String capturetext(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(elementWaitTime));
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		String actualtext=element.getText();
		return actualtext;
		
	}
	
	public void clickByJavaScript(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(elementWaitTime));
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
	
	public Boolean verifyDisplayed( By locator) 
	{
		try 
		{
			if (this.getElement(locator).isDisplayed()) 
			{
				//logTestPass("Element is displayed");
				return true;
			}
			return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public Boolean titleispresent(String title,By locator) {
		
		List<WebElement> elements = this.driver.findElements(locator);
		 
		// text is presenet or not
		 boolean isTextFound = false;
	        String elementText = "";
 
	        // Iterate  the list of elements to find the matching text
	        for (WebElement element : elements) {
	            elementText = element.getText();
	            System.out.println("Text from the web element: " + elementText);
 
	            if (title.equals(elementText)) {
	                isTextFound = true;
	                break;
	            }
	        }
	       return isTextFound;
	}
	
	public void mouse_moveToElement(By locator) throws TimeoutException 
	{
		this.act = new Actions(this.driver);
		final WebElement el = getElement(locator);
		this.act.moveToElement(el).build().perform();
		waitForElementPresence(locator);
	}
	

	public void selectallcheckboxes(By locator) {
		try 
		{
        	List<WebElement> checkboxes = this.driver.findElements(locator);
        	for (WebElement checkbox : checkboxes) {
        		if (!checkbox.isSelected()) {
	        	System.out.println(checkbox.getText());
	                checkbox.click();
	            }
        	}
			
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	
	public void selectcheckboxbychoice(By locator, String value) {
		
		try 
		{
        	List<WebElement> checkboxes = this.driver.findElements(locator);
        	for (WebElement checkbox : checkboxes) {
        		if ((!checkbox.isSelected()) && (checkbox.getText()=="value")) {
	                checkbox.click();
	            }
        	}
			
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public void waitForElementIsVisible(final By xpathLocator, final int numberOfSeconds) 
	{
		final WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(numberOfSeconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xpathLocator));
	}

	public void waitForElementIsVisibleFluent(final By xpathLocator, final int numberOfSeconds, final int fluent) 
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(numberOfSeconds))
				.pollingEvery(Duration.ofMillis(fluent))
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(xpathLocator));
	}
	
	public String getAttribute(By by, String attrValue) {
		return getElement(by).getAttribute(attrValue);
	}
	
	public void dragAndDrop(By source, By target) {
		this.act = new Actions(driver);
		WebElement weSourceElement = getElement(source);
		this.act.clickAndHold(weSourceElement).moveByOffset(50, 50).release().build().perform();
		WebElement weTargetElement = getElement(target);
		this.act.dragAndDrop(weSourceElement,weTargetElement).perform();
		//logTestInfo("Drag and Drop event is successful");
	}
	
	public void clickbyactionclass(By locator) {
		this.act = new Actions(driver);
		final WebElement el = getElement(locator);
		this.act.moveToElement(el).click().build().perform();
	}
	
	public List<WebElement> getElements(final By locator) 
	{
		return (List<WebElement>)this.driver.findElements(locator);
	}
	
	public void waitForTextMatches(final By locator,String text) throws TimeoutException {
		final WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}
	
	public void waitForFrameAndSwitch(final By xpathLocator,final String elementName) throws TimeoutException 
	{
		final WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(xpathLocator));

	}
	
	public void waitForElementisvisible(final By xpathLocator) throws TimeoutException {
		final WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xpathLocator));
	}
	
	public void waitForElementToBeClickable(final By xpathLocator) throws TimeoutException {
		final WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(xpathLocator));
	}
	
	public void waitForElementPresence(final By xpathLocator) throws TimeoutException {
		final WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(xpathLocator));
	}
	
	public void waitForAlertIsPresent(final int numberOfSeconds) throws TimeoutException 
	{
		final WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(numberOfSeconds));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void selectDropdownValueByValue(final By xpathLocator, final String value) 
	{
		try 
		{
			final WebElement element = this.driver.findElement(xpathLocator);
			final Select oSelect = new Select(element);
			oSelect.selectByValue(value);
			//final String val = this.driver.findElement(xpathLocator).getAttribute("value");
			//this.logTestPassSnap("selectDropdownValueByValue : " + value + " - is selected from dropdown : " + ElementName + "with option : " + val);
		}
		catch (Exception e) 
		{
			//this.logTestFailSnap("selectDropdownValueByValue : Not able to select option :" + value + " from dropdown : " + ElementName);
			System.out.println(e);
		}
	}
	
	public void waitFor(final int milliSeconds) 
	{
		try 
		{
			Thread.sleep(milliSeconds);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getcurrentdatetime() {
		LocalDateTime currentDateTime = LocalDateTime.now();

        // Define the format you want
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-yy HH:mm");
        // Format the current date and time
        String formattedDateTime = currentDateTime.format(formatter);
        String datetimeformate=formattedDateTime.replaceAll("[^0-9]", "");
		return datetimeformate;
	}
	
	public void captureScreen(String tname) throws IOException {
		String datetime=getcurrentdatetime();
		try 
		{
			TakesScreenshot ts= (TakesScreenshot) this.driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			File target=new File(System.getProperty("user.dir")+"\\screenshots\\"+tname+datetime+".png");
			FileUtils.copyFile(source,target);
			addinfolog("Screenshot Taken path :"+target.getAbsolutePath());
		}
		catch (Exception e) 
		{
			System.out.println(e);
			addinfolog("Screenshot not taken");
		}
	}
	
	public String generatemailid() {
		Faker str=new Faker();
		String firstname=str.name().firstName();
		String lastname=str.name().lastName();
		String emailid=firstname+lastname+"@cloudeq.com";
		return emailid;
	}
	
	public String generateamount(int lenght) {
		RandomStringUtils ran=new RandomStringUtils();
		String amount=ran.randomNumeric(lenght);
		return amount;
	}
	
	public String generatetitle() {
		Faker str=new Faker();
		String title=str.company().name();
		return title;
	}
	
	public String generateURL() {
		Faker str=new Faker();
		String website=str.company().url();
		String URL="https://"+website;
		return URL;
	}
	
	public String generateticketID() {
		Faker str=new Faker();
		String number=str.number().digits(10);
		String idstring="CloudEQ_"+number;
		return idstring;
	}
	
	public String generatecomment() {
		Faker str=new Faker();
		String number=str.company().profession();
		String comment="CloudEQ "+number;
		return comment;
	}
	
	public String roundingDBStringValue(String value) {
        // Convert the string to BigDecimal for precise handling
        BigDecimal originalValue = new BigDecimal(value);
        // Round the value to two decimal places using HALF_UP rounding mode
        BigDecimal roundedValue = originalValue.setScale(2, RoundingMode.HALF_UP);
        // Convert back to string for output
        String roundedString = roundedValue.toString();
        return roundedString;
	}
	
	public double roundingDBdounleValue(String value) {
        // Convert the string to BigDecimal for precise handling
        BigDecimal originalValue = new BigDecimal(value);
        // Round the value to two decimal places using HALF_UP rounding mode
        BigDecimal roundedValue = originalValue.setScale(2, RoundingMode.HALF_UP);
        // Convert back to string for output
        String roundedString = roundedValue.toString();
        double Cost = Double.parseDouble(roundedString);
        return Cost;
	}
	
	 public String capitalizeFirstLetter(String input) {
	        if (input == null || input.isEmpty()) {
	            return input; // Return input if null or empty
	        }
	        // Convert first letter to uppercase and rest to lowercase
	        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	    }
	 
	 public List<String> capturecostformtable(String columnlocator) throws TimeoutException {
		 List<String> frontEndData=new ArrayList<>();
		 By columnslocator= By.xpath(columnlocator);
		 mouse_moveToElement(columnslocator);
		 List <WebElement> list=this.driver.findElements(columnslocator);
		 int columnnumbers=list.size();
		 
		 for(int i=2;i<=columnnumbers;i++) {
			 String text=this.driver.findElement(By.xpath(columnlocator+"["+i+"]")).getText();
			 text=text.replaceAll("[^0-9.]", "");
			 frontEndData.add(text);
		 }
		return frontEndData;
	 }
	 
	 public List<String> capturecostgraph(By graphbarlocator,By tooltiplocator,By graphcostlocator) throws TimeoutException{
		 List<String> frontEndData=new ArrayList<>();
		 waitForElementisvisible(graphbarlocator);
		 List <WebElement> list=this.driver.findElements(graphbarlocator);
		 for(WebElement element:list) {
			 this.act.moveToElement(element).click().build().perform();
			 waitFor(1000);
			 waitForElementisvisible(tooltiplocator);
			 waitFor(1000);
			 String cost=this.driver.findElement(graphcostlocator).getText();
			 cost=cost.replaceAll("[^0-9.]", "");
			 frontEndData.add(cost);
		 }
		return frontEndData;
	 }
	 
	 public List<String> convertedarreycostdata(List<String> data){
		 List<String> convertedcsvData=new ArrayList<>();
		 
		 for(String convertdata:data) {
			 String datatoconvert=convertdata;
			 String newdata=roundingDBStringValue(datatoconvert);
			 convertedcsvData.add(newdata);
		 }
		 return convertedcsvData;
	 }
	 
	 public void deleteFile(String filePath) {
	        File file = new File(filePath);
	       try {
	    	   System.out.println("File Exists: "+file.exists());
	    	   waitFor(5000);
	        if (file.exists()) {
	            if (file.delete()) {
	                System.out.println("File deleted successfully");
	            } else {
	                System.out.println("Failed to delete the file");
	            }
	        } else {
	            System.out.println("File does not exist");
	        }
	        }catch(Exception e) {
	        	System.out.println(e);
	        }
	    }
	 
	 public void deletefile_FileUtils(String filePath) {
		        File file = new File(filePath);
		        try {
		            FileUtils.forceDelete(file);
		            System.out.println("File deleted successfully");
		        } catch (IOException e) {
		            System.out.println("Failed to delete the file");
		            e.printStackTrace();
		        }
		    }
	 
	 public String table_idleresource_capture_resourceID_value_archivediscardresource(){
		 this.act = new Actions(driver);
			String captureresourceId = "";
			try {
				 // Locators
	            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");       //**********
	            By resourcerows = By.xpath("//table//tr//td[4]//span");  ///***************
	            // Get total number of pages
	            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li"); ///**********
		        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
		        int elementcount=totalpaginationelements.size();
		        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
		        By commentarea=By.xpath("//div[@class='d-flex flex-column ']//textarea[@name='Comment']");       ////****************
		        By submit=By.xpath("//button[contains(text(),'Submit')]");
		        // Get total number of pages
		      
		        String  result = driver.findElement(getTotalPages).getText(); 
		        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
		        // Implement a method to get total pages
		        int pagecount = Integer.parseInt(totalPages);
		        System.out.println("Page Count is "+pagecount);
	           

	            boolean found = false;
	          String resourceIdCost = "";

	            // Loop through each page
	            for (int page = 1; page <= pagecount; page++) {
	                // Get all rows in the current page
	                List<WebElement> rows = this.driver.findElements(resourcerows);
	                int totalrows=rows.size();

	                // Iterate through each row to check cost greater than zero
	              //  for (WebElement row : rows) 
	                for(int i=1;i<=totalrows;i++)
	                {
	                    // Extract resource id and cost
	                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
	                  //  String resourceId = resourceIdElement.getText().trim();
	                	WebElement currentrow = this.driver.findElement(By.xpath("//table//tr["+i+"]//td[4]//span"));      ///td3 can be
	                	act.moveToElement(currentrow);
	                	String resourceId = currentrow.getText().trim();
	                	waitFor(300);
	                	WebElement costcheck = this.driver.findElement(By.xpath("//table//tr["+i+"]//td[9]//button"));  ////2nd table      ////td10 can be
	                	String rowText = costcheck.getText().trim();
	                	boolean check=rowText.equals("$0");
	                    waitFor(1000);
	                    if (check==false) {
	                        resourceIdCost = resourceId + " - Status is: " + rowText;
	                        captureresourceId = resourceId;
	                        found = true;
	                        waitFor(300);
	                        
	                        // Perform action based on dropdown selection (accept or dismiss)
	                        WebElement threedots = this.driver.findElement(By.xpath("//table//tr["+i+"]//td[11]//button"));
	                        //act.moveToElement(threedots).click().build().perform();
	                        threedots.click();
	                        waitFor(500);
	                        
	                        WebElement Archivetext = this.driver.findElement(By.xpath("//a//span[contains(text(),'Archive')]"));
	                        Archivetext.click();
	                        waitFor(1000);
	                        WebElement entercomment=this.driver.findElement(commentarea);
	                        String comment=generatetitle();
	                        entercomment.sendKeys(comment);
	                        waitFor(500);
	                        WebElement submitbutton=this.driver.findElement(submit);
	                        submitbutton.click();
	                        waitFor(1000);
	                        break; // Exit the loop if cost is found
	                    }
	                }

	                if (found) {
	                    break; // Exit the loop if cost is found
	                } else {
	                    // Navigate to the next page if cost is not found
	                    if (page < pagecount) {
	                        // Click next page button or implement pagination logic
	                        // Example: Implement logic to click next page button
	                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
	                        act.moveToElement(nextPageButton).click().build().perform();
	                       // nextPageButton.click();
	                        waitFor(3000);
	                        // Replace with actual logic to navigate to next page
	                    }
	                }
	            }

	            if (found) {
	                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
	            } else {
	                System.out.println("Status Open is not exists for all resources in table.");
	            }
			
		}catch (WebDriverException e) {
	        // Handle WebDriverException
	        System.err.println("WebDriverException occurred: " + e.getMessage());
	    }
			return captureresourceId;
		 
	 }
	 
	 public String table_idleresource_capture_resourceID_value_aftermovedtoopenresource(){
			this.act = new Actions(driver);
			String captureresourceId = "";
			try {
				 // Locators
	            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");       //**********
	            By resourcerows = By.xpath("//table//tr//td[4]//span");  ///***************
	            // Get total number of pages
	            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li"); ///**********
		        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
		        int elementcount=totalpaginationelements.size();
		        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
		        // Get total number of pages
		      
		        String  result = driver.findElement(getTotalPages).getText(); 
		        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
		        // Implement a method to get total pages
		        int pagecount = Integer.parseInt(totalPages);
		        System.out.println("Page Count is "+pagecount);
	           

	            boolean found = false;
	          String resourceIdCost = "";

	            // Loop through each page
	            for (int page = 1; page <= pagecount; page++) {
	                // Get all rows in the current page
	                List<WebElement> rows = this.driver.findElements(resourcerows);
	                int totalrows=rows.size();

	                // Iterate through each row to check cost greater than zero
	              //  for (WebElement row : rows) 
	                for(int i=1;i<=totalrows;i++)
	                {
	                    // Extract resource id and cost
	                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
	                  //  String resourceId = resourceIdElement.getText().trim();
	                	WebElement currentrow = this.driver.findElement(By.xpath("//table//tr["+i+"]//td[4]//span"));      ///td3 can be
	                	act.moveToElement(currentrow);
	                	String resourceId = currentrow.getText().trim();
	                	waitFor(300);
	                	WebElement costcheck = this.driver.findElement(By.xpath("//table//tr["+i+"]//td[9]//button"));  ////2nd table      ////td10 can be
	                	String rowText = costcheck.getText().trim();
	                	boolean check=rowText.equals("$0");
	                    waitFor(1000);
	                	
	                    if (check==false) {
	                        resourceIdCost = resourceId + " - Status is: " + rowText;
	                        captureresourceId = resourceId;
	                        found = true;
	                        waitFor(300);
	                        
	                        // Perform action based on dropdown selection (accept or dismiss)
	                        WebElement threedots = this.driver.findElement(By.xpath("//table//tr["+i+"]//td[11]//button"));
	                        //act.moveToElement(threedots).click().build().perform();
	                        threedots.click();
	                        waitFor(500);
	                        
	                        WebElement MovetoOpentext = this.driver.findElement(By.xpath("//a//span[contains(text(),'Move to Open')]"));
	                        MovetoOpentext.click();
	                        waitFor(1000);
	                        break; // Exit the loop if cost is found
	                    }
	                }

	                if (found) {
	                    break; // Exit the loop if cost is found
	                } else {
	                    // Navigate to the next page if cost is not found
	                    if (page < pagecount) {
	                        // Click next page button or implement pagination logic
	                        // Example: Implement logic to click next page button
	                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
	                        act.moveToElement(nextPageButton).click().build().perform();
	                       // nextPageButton.click();
	                        waitFor(3000);
	                        // Replace with actual logic to navigate to next page
	                    }
	                }
	            }

	            if (found) {
	                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
	            } else {
	                System.out.println("Status Open is not exists for all resources in table.");
	            }
			
		}catch (WebDriverException e) {
	        // Handle WebDriverException
	        System.err.println("WebDriverException occurred: " + e.getMessage());
	    }
			return captureresourceId;
		 
	 }
	
		public String table_idleresource_capture_resourceID_value_afterDiscardresource() throws TimeoutException { 
			this.act = new Actions(driver);
			String captureresourceId = "";
			try {
				 // Locators
	            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");       //**********
	            By resourcerows = By.xpath("//table//tbody//tr//td[4]");  ///***************
	            // Get total number of pages
	            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li"); ///**********
		        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
		        int elementcount=totalpaginationelements.size();
		        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
		        By textDiscard=By.xpath("//a//span[contains(text(),'Discard')]");  //////****************
		        By commentarea=By.xpath("//div[@class='d-flex flex-column ']//textarea[@name='Comment']");       ////****************
		        By submit=By.xpath("//button[contains(text(),'Submit')]");
		        // Get total number of pages
		      
		        String  result = driver.findElement(getTotalPages).getText(); 
		        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
		        // Implement a method to get total pages
		        int pagecount = Integer.parseInt(totalPages);
		        System.out.println("Page Count is "+pagecount);
	           

	            boolean found = false;
	          String resourceIdCost = "";

	            // Loop through each page
	            for (int page = 1; page <= pagecount; page++) {
	                // Get all rows in the current page
	                List<WebElement> rows = this.driver.findElements(resourcerows);
	                int totalrows=rows.size();

	                // Iterate through each row to check cost greater than zero
	              //  for (WebElement row : rows) 
	                for(int i=1;i<=totalrows;i++)
	                {
	                    // Extract resource id and cost
	                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
	                  //  String resourceId = resourceIdElement.getText().trim();
	                	WebElement currentrow = this.driver.findElement(By.xpath("//table//tbody//tr["+i+"]//td[4]"));      ///td3 can be
	                	act.moveToElement(currentrow);
	                	String resourceId = currentrow.getText().trim();
	                	waitFor(300);
	                    WebElement statusrow = this.driver.findElement(By.xpath("//table//tbody//tr["+i+"]//td[10]//span"));  ////2nd table      ////td10 can be
	                    String rowText = statusrow.getText().trim();
	                    // Check if text is equal to 'Open'
	                    String statuscheck="Open";
	                    boolean check=rowText.equals(statuscheck);
	                    waitFor(1000);
	                    if (check==true) {
	                        resourceIdCost = resourceId + " - Status is: " + rowText;
	                        captureresourceId = resourceId;
	                        found = true;
	                        waitFor(300);
	                        
	                        // Perform action based on dropdown selection (accept or dismiss)
	                        WebElement threedots = this.driver.findElement(By.xpath("//table[@class='table table-hover ']//tbody//tr["+i+"]//td[11]//button"));
	                        //act.moveToElement(threedots).click().build().perform();
	                        threedots.click();
	                        waitFor(500);
	                        
	                        WebElement accepttext = this.driver.findElement(By.xpath("//a//span[contains(text(),'Accept')]"));
	                        accepttext.click();
	                        waitFor(1000);
	                        WebElement ticketid=this.driver.findElement(By.xpath("//input[@name='Ticket ID']"));
	                        String id=generateticketID();
	                        ticketid.sendKeys(id);
	                        waitFor(500);
	                        WebElement enterURL=this.driver.findElement(By.xpath("//input[@name='Ticket URL']"));
	                        String URL=generateURL();
	                        enterURL.sendKeys(URL);
	                        waitFor(500);
	                        WebElement entercomment=this.driver.findElement(commentarea);
	                        String comment=generatetitle();
	                        entercomment.sendKeys(comment);
	                        waitFor(500);
	                        WebElement submitbutton=this.driver.findElement(submit);
	                        submitbutton.click();
	                        waitFor(8000);
	                        threedots.click();
	                        waitFor(6000);
	                        waitForElementPresence(textDiscard);
	                        WebElement Discardtext = this.driver.findElement(textDiscard);
	                        Discardtext.click();
	                        waitFor(5000);
	                        String comment1=generatetitle();
	                        clickbyactionclass(commentarea);
	                        WebElement entercomment2=this.driver.findElement(commentarea);
	                        entercomment2.clear();
	                        entercomment2.sendKeys(comment1);
	                        waitFor(500);
	                        clickbyactionclass(submit);
	                        waitFor(4000);
	                        break; // Exit the loop if cost is found
	                    }
	                }

	                if (found) {
	                    break; // Exit the loop if cost is found
	                } else {
	                    // Navigate to the next page if cost is not found
	                    if (page < pagecount) {
	                        // Click next page button or implement pagination logic
	                        // Example: Implement logic to click next page button
	                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
	                        act.moveToElement(nextPageButton).click().build().perform();
	                       // nextPageButton.click();
	                        waitFor(3000);
	                        // Replace with actual logic to navigate to next page
	                    }
	                }
	            }

	            if (found) {
	                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
	            } else {
	                System.out.println("Status Open is not exists for all resources in table.");
	            }
			
		}catch (WebDriverException e) {
	        // Handle WebDriverException
	        System.err.println("WebDriverException occurred: " + e.getMessage());
	    }
			return captureresourceId;
	}
	 
	public String table_archive_idle_resource_capture_resourceID_value() {
		this.act = new Actions(driver);
		String resourceIdWithCost = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");
            By resourcerows = By.xpath("//table[@class='table table-hover ']//tbody//tr//td[4]");
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li");
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        // Get total number of pages
	        
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("//table[@class='table table-hover ']//tbody//tr["+i+"]//td[4]"));
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                    WebElement currentrowcost = this.driver.findElement(By.xpath("//table[@class='table table-hover ']//tbody//tr["+i+"]//td[8]"));
                    String costText = currentrowcost.getText().trim();
                    costText = costText.replaceAll("[^\\d.]", "");
                    double cost = Double.parseDouble(costText);
                    // Check if cost is greater than zero
                    if (cost >= 1.00) {
                        resourceIdCost = resourceId + " - $" + costText;
                    	resourceIdWithCost = resourceId;
                        found = true;
                        waitFor(300);
                        // Perform action based on dropdown selection (accept or dismiss)
                        WebElement unarchivebutton = this.driver.findElement(By.xpath("//table[@class='table table-hover ']//tbody//tr["+i+"]//td//button[@class='fgvhjikj']"));
                        unarchivebutton.click();
                        waitFor(3000);
                        WebElement entercomment=this.driver.findElement(By.xpath("//textarea[@name='Comment']"));
                        String comment=generatetitle();
                        entercomment.sendKeys(comment);
                        waitFor(1000);
                        WebElement submitbutton=this.driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
                        submitbutton.click();
                        waitFor(1000);
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                        // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with cost greater than $0.00 is found: " + resourceIdCost);
            } else {
                System.out.println("Cost is zero for all resources in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return resourceIdWithCost;

}
	
	public String table_archive_VMS_Module_capture_resourceID_value(int columnindex) {
		this.act = new Actions(driver);
		String resourceIdWithCost = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");
            By resourcerows = By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr//td[1]");
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li");
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        // Get total number of pages
	        
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();
                System.out.println("total Rows="+totalrows+"//");

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr/td["+columnindex+"]/span[1]"));
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                    WebElement currentrowcost = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr["+i+"]//td[1]"));
                    String costText = currentrowcost.getText().trim();
                    costText = costText.replaceAll("[^\\d.]", "");
                    double cost = Double.parseDouble(costText);
                    // Check if cost is greater than zero
                    if (cost >= 1.00) {
                        resourceIdCost = resourceId + " - $" + costText;
                    	resourceIdWithCost = resourceId;
                        found = true;
                        waitFor(300);
                        // Perform action based on dropdown selection (accept or dismiss)
                        WebElement unarchivebutton = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr["+i+"]//td[3]"));
                        unarchivebutton.click();
                        waitFor(3000);
                        WebElement entercomment=this.driver.findElement(By.xpath("//textarea[@name='Comment']"));
                        String comment=generatetitle();
                        entercomment.sendKeys(comment);
                        waitFor(1000);
                        WebElement submitbutton=this.driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
                        submitbutton.click();
                        waitFor(1000);
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                        // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with cost greater than $0.00 is found: " + resourceIdCost);
            } else {
                System.out.println("Cost is zero for all resources in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return resourceIdWithCost;

}
	
	public String table_archive_idle_resource_capture_resourceID_value_afterAcceptresource() throws TimeoutException { 
		this.act = new Actions(driver);
		String captureresourceId = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");
            By resourcerows = By.xpath("//table[@class='table table-hover ']//tbody//tr//td[4]");
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li");
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        By textApprove=By.xpath("//a//span[contains(text(),'Approve')]");
	        By textDone=By.xpath("//a//span[contains(text(),'Done')]");
	        // Get total number of pages
	      
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("//table[@class='table table-hover ']//tbody//tr["+i+"]//td[4]"));
                	act.moveToElement(currentrow);
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                    WebElement statusrow = this.driver.findElement(By.xpath("//table[@class='table table-hover ']//tbody//tr["+i+"]//td[10]//span"));
                    String rowText = statusrow.getText().trim();
                    // Check if text is equal to 'Open'
                    String statuscheck="Open";
                    boolean check=rowText.equals(statuscheck);
                    waitFor(1000);
                    if (check==true) {
                        resourceIdCost = resourceId + " - Status is: " + rowText;
                        captureresourceId = resourceId;
                        found = true;
                        waitFor(300);
                        
                        // Perform action based on dropdown selection (accept or dismiss)
                        WebElement threedots = this.driver.findElement(By.xpath("//table[@class='table table-hover ']//tbody//tr["+i+"]//td[11]//button"));
                        act.moveToElement(threedots).click().build().perform();
                        waitFor(500);
                        WebElement accepttext = this.driver.findElement(By.xpath("//a//span[contains(text(),'Accept')]"));
                        act.moveToElement(accepttext).click().build().perform();
                        //accepttext.click();
                        waitFor(1000);
                        WebElement ticketid=this.driver.findElement(By.xpath("//input[@name='Ticket ID']"));
                        String id=generateticketID();
                        ticketid.sendKeys(id);
                        waitFor(500);
                        WebElement enterURL=this.driver.findElement(By.xpath("//input[@name='Ticket URL']"));
                        String URL=generateURL();
                        enterURL.sendKeys(URL);
                        waitFor(500);
                        WebElement entercomment=this.driver.findElement(By.xpath("//textarea[@name='Comment']"));
                        String comment=generatetitle();
                        entercomment.sendKeys(comment);
                        waitFor(500);
                        WebElement submitbutton=this.driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
                        submitbutton.click();
                        waitFor(8000);
                        threedots.click();
                        waitFor(6000);
                        waitForElementPresence(textApprove);
                        WebElement Approvetext = this.driver.findElement(textApprove);
                        Approvetext.click();
                        waitFor(6000);
                        threedots.click();
                        waitFor(6000);
                        waitForElementPresence(textDone);
                        WebElement Donetext = this.driver.findElement(textDone);
                        Donetext.click();
                        waitFor(1000);
                       
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                       // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
            } else {
                System.out.println("Status Open is not exists for all resources in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return captureresourceId;

}

	public String table_archive_idle_resource_capture_resourceID_value_afterdismissresource() throws TimeoutException { 
		this.act = new Actions(driver);
		String captureresourceId = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");
            By resourcerows = By.xpath("//table[@class='table table-hover ']//tbody//tr//td[4]");
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li");
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        By textApprove=By.xpath("//a//span[contains(text(),'Approve')]");
	        By textDone=By.xpath("//a//span[contains(text(),'Done')]");
	        // Get total number of pages
	      
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("//table[@class='table table-hover ']//tbody//tr["+i+"]//td[4]"));
                	act.moveToElement(currentrow).build().perform();
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                    WebElement statusrow = this.driver.findElement(By.xpath("//table[@class='table table-hover ']//tbody//tr["+i+"]//td[10]//span"));
                    String rowText = statusrow.getText().trim();
                    // Check if text is equal to 'Open'
                    String statuscheck="Open";
                    boolean check=rowText.equals(statuscheck);
                    waitFor(1000);
                    if (check==true) {
                        resourceIdCost = resourceId + " - Status is: " + rowText;
                        captureresourceId = resourceId;
                        found = true;
                        waitFor(300);
                        
                        // Perform action based on dropdown selection (accept or dismiss)
                        WebElement threedots = this.driver.findElement(By.xpath("//table[@class='table table-hover ']//tbody//tr["+i+"]//td[11]//button"));
                        act.moveToElement(threedots).build().perform();
                        threedots.click();
                        waitFor(2000);
                        WebElement accepttext = this.driver.findElement(By.xpath("//a//span[contains(text(),'Archive')]"));
                        act.moveToElement(accepttext).click().build().perform();
                        waitFor(2000);
                        WebElement entercomment=this.driver.findElement(By.xpath("//textarea[@name='Comment']"));
                        String comment=generatetitle();
                        entercomment.sendKeys(comment);
                        waitFor(500);
                        WebElement submitbutton=this.driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
                        submitbutton.click();
                        waitFor(1000);
                       
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                       // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
            } else {
                System.out.println("Status Open is not exists for all resources in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return captureresourceId;

}
	//////////////////////////////////////////////////////////////////
	/////////////////////////////////
	////////////////////////////////////////
	public String table_snapshot_active_accept_snapshot() throws TimeoutException { 
		this.act = new Actions(driver);
		String captureresourceId = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//a[contains(text(),'Next')]");       //**********
            By resourcerows = By.xpath("//table[1]/tbody[1]/tr/td[4]/span[1]");  ///***************
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li"); ///**********
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        By textApprove=By.xpath("//span[contains(text(),'Approve')]");  //////****************
	        By textDone=By.xpath("//span[contains(text(),'Done')]");       ////****************
	        // Get total number of pages
	      
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("//table[1]/tbody[1]/tr["+i+"]/td[4]/span[1]"));      ///td3 can be
                	act.moveToElement(currentrow);
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                    WebElement statusrow = this.driver.findElement(By.xpath("//div//table//tbody[@class='snapshots_snap_table_data__Class__9Ub7x']//tr["+i+"]//td//span"));  ////2nd table      ////td10 can be
                    String rowText = statusrow.getText().trim();
                    // Check if text is equal to 'Open'
                    String statuscheck="Open";
                    boolean check=rowText.equals(statuscheck);
                    waitFor(1000);
                    if (check==true) {
                        resourceIdCost = resourceId + " - Status is: " + rowText;
                        captureresourceId = resourceId;
                        found = true;
                        waitFor(300);
                        
                        // Perform action based on dropdown selection (accept or dismiss)
                        By xpaththreedots=By.xpath("//div//table//tbody[@class='snapshots_snap_table_data__Class__9Ub7x']//tr["+i+"]//td//button[@class='snapshots_dropdown_toggle__f4g5P dropdown-toggle btn btn-link']");
                        WebElement threedots = this.driver.findElement(xpaththreedots);
                        threedots.click();
                        waitFor(500);
                        WebElement accepttext = this.driver.findElement(By.xpath("//span[contains(text(),'Accept')]"));
                        accepttext.click();
                        waitFor(1000);
                        WebElement ticketid=this.driver.findElement(By.xpath("//input[@name='Ticket ID']"));
                        String id=generateticketID();
                        ticketid.sendKeys(id);
                        waitFor(500);
                        WebElement enterURL=this.driver.findElement(By.xpath("//input[@name='Ticket URL']"));
                        String URL=generateURL();
                        enterURL.sendKeys(URL);
                        waitFor(500);
                        WebElement entercomment=this.driver.findElement(By.xpath("//textarea[@name='Comment']"));
                        String comment=generatetitle();
                        entercomment.sendKeys(comment);
                        waitFor(500);
                        WebElement submitbutton=this.driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
                        submitbutton.click();
                        waitFor(10000);
                        //driver.switchTo().defaultContent();
                        mouse_moveToElement(xpaththreedots);
                        waitForElementPresence(xpaththreedots);
                        clickbyactionclass(xpaththreedots);
                        waitFor(6000);
                        waitForElementPresence(textApprove);
                        WebElement Approvetext = this.driver.findElement(textApprove);
                        Approvetext.click();
                        waitFor(6000);
                        mouse_moveToElement(xpaththreedots);
                        waitForElementPresence(xpaththreedots);
                        clickbyactionclass(xpaththreedots);
                        waitFor(6000);
                        waitForElementPresence(textDone);
                        WebElement Donetext = this.driver.findElement(textDone);
                        Donetext.click();
                        waitFor(1000);
                       
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                       // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
            } else {
                System.out.println("Status Open is not exists for all resources in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return captureresourceId;
}
	
	public String table_snapshot_active_archive_snapshot() throws TimeoutException { 
		this.act = new Actions(driver);
		String captureresourceId = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//a[contains(text(),'Next')]");       //**********
            By resourcerows = By.xpath("//table[1]/tbody[1]/tr/td[4]/span[1]");  ///***************
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li"); ///**********
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        // Get total number of pages
	      
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("//table[1]/tbody[1]/tr["+i+"]/td[4]/span[1]"));      ///td3 can be
                	act.moveToElement(currentrow);
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                    WebElement statusrow = this.driver.findElement(By.xpath("//div//table//tbody[@class='snapshots_snap_table_data__Class__9Ub7x']//tr["+i+"]//td//span"));  ////2nd table      ////td10 can be
                    String rowText = statusrow.getText().trim();
                    // Check if text is equal to 'Open'
                    String statuscheck="Open";
                    boolean check=rowText.equals(statuscheck);
                    waitFor(1000);
                    if (check==true) {
                        resourceIdCost = resourceId + " - Status is: " + rowText;
                        captureresourceId = resourceId;
                        found = true;
                        waitFor(300);
                        
                        // Perform action based on dropdown selection (accept or dismiss)
                        By xpaththreedots=By.xpath("//div//table//tbody[@class='snapshots_snap_table_data__Class__9Ub7x']//tr["+i+"]//td//button[@class='snapshots_dropdown_toggle__f4g5P dropdown-toggle btn btn-link']");
                        WebElement threedots = this.driver.findElement(xpaththreedots);
                        threedots.click();
                        waitFor(500);
                        WebElement archivetext = this.driver.findElement(By.xpath("//span[contains(text(),'Archive')]"));
                        archivetext.click();
                        WebElement entercomment=this.driver.findElement(By.xpath("//textarea[@name='Comment']"));
                        String comment=generatetitle();
                        entercomment.sendKeys(comment);
                        waitFor(500);
                        WebElement submitbutton=this.driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
                        submitbutton.click();
                        waitFor(5000);
                       
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                       // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
            } else {
                System.out.println("Status Open is not exists for all resources in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return captureresourceId;
}
	
	public boolean verifysnapshotidintablesnapshotcolumn(String id,int columnindex) {
		this.act = new Actions(driver);
		boolean found = false;
		try {	
			 String resourceIdToSearch = id;

		        // Locators
		        By rowsLocator = By.xpath("//table[1]/tbody[1]/tr/td["+columnindex+"]/span[1]");
		        By nextPageButtonLocator = By.xpath("//span[contains(text(),'Next')]"); 
		        
		        By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li");
		        List<WebElement> totalpaginationelements=driver.findElements(totalelementsinpagination);
		        waitFor(1000);
		        int elementcount=totalpaginationelements.size();
		        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
		        // Get total number of pages
		        String  result = driver.findElement(getTotalPages).getText(); 
		        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
		        // Implement a method to get total pages
		        int pagecount = Integer.parseInt(totalPages);
		        System.out.println("Page Count is "+pagecount);

		        // Loop through each page
		        for (int page = 1; page <= pagecount; page++) {
		            // Get all rows in the current page
		            List<WebElement> rows = driver.findElements(rowsLocator);

		            // Iterate through each row to find the resource id
		            for (WebElement row : rows) {
		                // Check if the resource id text is present in the current row
		                //WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
		                
		                String resourceIdText = row.getText();
		                //System.out.println(resourceIdText);
		                waitFor(300);
		                if (resourceIdText.equals(resourceIdToSearch)) {
		                    System.out.println("Text found in table!");
		                    found = true;
		                    break; // Exit the loop if text is found
		                }
		            }

		            if (found) {
		                break; // Exit the loop if text is found
		            } else {
		                // Navigate to the next page if text is not found
		                if (page < pagecount) {
		                    WebElement nextPageButton = driver.findElement(nextPageButtonLocator);
		                    act.moveToElement(nextPageButton).click().build().perform();
		                    waitFor(2000);
		                    // Wait for page to load, implement wait mechanism if needed
		                }
		            }
		        }

		        if (!found) {
		            System.out.println("Text not found in table.");
		        }
			 }
		        catch (WebDriverException e) {
		            // Handle WebDriverException
		            System.err.println("WebDriverException occurred: " + e.getMessage());
		        }
		return found;
	}
	
	public String table_archive_Snapshot_capture_resourceID_value(int columnindex) {
		this.act = new Actions(driver);
		String resourceIdWithCost = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//a[contains(text(),'Next')]");
            By resourcerows = By.xpath("//table[1]/tbody[1]/tr/td[4]/span[1]");
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li");
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        // Get total number of pages
	        
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();
                System.out.println("total Rows="+totalrows+"//");

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("//table[1]/tbody[1]/tr["+i+"]/td["+columnindex+"]/span[1]"));
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                    WebElement currentrowcost = this.driver.findElement(By.xpath("//div//table//tbody[@class='snapshots_snap_table_data__Class__9Ub7x']//tr["+i+"]//td[2]//div//button"));
                    String costText = currentrowcost.getText().trim();
                    costText = costText.replaceAll("[^\\d.]", "");
                    double cost = Double.parseDouble(costText);
                    // Check if cost is greater than zero
                    if (cost >= 1.00) {
                        resourceIdCost = resourceId + " - $" + costText;
                    	resourceIdWithCost = resourceId;
                        found = true;
                        waitFor(300);
                        // Perform action based on dropdown selection (accept or dismiss)
                        WebElement unarchivebutton = this.driver.findElement(By.xpath("//div//table//tbody[@class='snapshots_snap_table_data__Class__9Ub7x']//tr["+i+"]//td[4]//button"));
                        unarchivebutton.click();
                        waitFor(3000);
                        WebElement entercomment=this.driver.findElement(By.xpath("//textarea[@name='Comment']"));
                        String comment=generatetitle();
                        entercomment.sendKeys(comment);
                        waitFor(1000);
                        WebElement submitbutton=this.driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
                        submitbutton.click();
                        waitFor(1000);
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                        // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with cost greater than $0.00 is found: " + resourceIdCost);
            } else {
                System.out.println("Cost is zero for all resources in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return resourceIdWithCost;

}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	public String table_Downsize_VMS_capture_resourceID_value_afterAcceptresource() throws TimeoutException { 
		this.act = new Actions(driver);
		String captureresourceId = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");       //**********
            By resourcerows = By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr/td[3]/span[1]");  ///***************
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li"); ///**********
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        By textApprove=By.xpath("//a//span[contains(text(),'Approve')]");  //////****************
	        By textDone=By.xpath("//a//span[contains(text(),'Done')]");       ////****************
	        By commentarea=By.xpath("//textarea[@name='Comment']");       ////****************
	        // Get total number of pages
	      
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr["+i+"]/td[3]/span[1]"));      ///td3 can be
                	act.moveToElement(currentrow);
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                    WebElement statusrow = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr["+i+"]//td[2]//span"));  ////2nd table      ////td10 can be
                    String rowText = statusrow.getText().trim();
                    // Check if text is equal to 'Open'
                    String statuscheck="Open";
                    boolean check=rowText.equals(statuscheck);
                    waitFor(1000);
                    if (check==true) {
                        resourceIdCost = resourceId + " - Status is: " + rowText;
                        captureresourceId = resourceId;
                        found = true;
                        waitFor(300);
                        
                        // Perform action based on dropdown selection (accept or dismiss)
                        WebElement threedots = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr["+i+"]//td[3]//button"));
                        threedots.click();
                        waitFor(500);
                        WebElement accepttext = this.driver.findElement(By.xpath("//a//span[contains(text(),'Accept')]"));
                        accepttext.click();
                        waitFor(1000);
                        WebElement ticketid=this.driver.findElement(By.xpath("//input[@name='Ticket ID']"));
                        String id=generateticketID();
                        ticketid.sendKeys(id);
                        waitFor(500);
                        WebElement enterURL=this.driver.findElement(By.xpath("//input[@name='Ticket URL']"));
                        String URL=generateURL();
                        enterURL.sendKeys(URL);
                        waitFor(500);
                        WebElement entercomment=this.driver.findElement(commentarea);
                        String comment=generatetitle();
                        entercomment.sendKeys(comment);
                        waitFor(500);
                        WebElement submitbutton=this.driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
                        submitbutton.click();
                        waitFor(8000);
                        threedots.click();
                        waitFor(6000);
                        waitForElementPresence(textApprove);
                        WebElement Approvetext = this.driver.findElement(textApprove);
                        Approvetext.click();
                        waitFor(6000);
                        threedots.click();
                        waitFor(6000);
                        waitForElementPresence(textDone);
                        WebElement Donetext = this.driver.findElement(textDone);
                        Donetext.click();
                        waitFor(1000);
                       
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                       // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
            } else {
                System.out.println("Status Open is not exists for all resources in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return captureresourceId;
}
	
	public String table_Downsize_VMS_capture_resourceID_value_afterDiscardresource() throws TimeoutException { 
		this.act = new Actions(driver);
		String captureresourceId = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");       //**********
            By resourcerows = By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr/td[3]/span[1]");  ///***************
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li"); ///**********
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        By textDiscard=By.xpath("//a//span[contains(text(),'Discard')]");  //////****************
	        By commentarea=By.xpath("//div[@class='d-flex flex-column ']//textarea[@name='Comment']");       ////****************
	        By submit=By.xpath("//button[contains(text(),'Submit')]");
	        // Get total number of pages
	      
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr["+i+"]/td[3]/span[1]"));      ///td3 can be
                	act.moveToElement(currentrow);
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                    WebElement statusrow = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr["+i+"]//td[2]//span"));  ////2nd table      ////td10 can be
                    String rowText = statusrow.getText().trim();
                    // Check if text is equal to 'Open'
                    String statuscheck="Open";
                    boolean check=rowText.equals(statuscheck);
                    waitFor(1000);
                    if (check==true) {
                        resourceIdCost = resourceId + " - Status is: " + rowText;
                        captureresourceId = resourceId;
                        found = true;
                        waitFor(300);
                        
                        // Perform action based on dropdown selection (accept or dismiss)
                        WebElement threedots = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr["+i+"]//td[3]//button"));
                        //act.moveToElement(threedots).click().build().perform();
                        threedots.click();
                        waitFor(500);
                        
                        WebElement accepttext = this.driver.findElement(By.xpath("//a//span[contains(text(),'Accept')]"));
                        accepttext.click();
                        waitFor(1000);
                        WebElement ticketid=this.driver.findElement(By.xpath("//input[@name='Ticket ID']"));
                        String id=generateticketID();
                        ticketid.sendKeys(id);
                        waitFor(500);
                        WebElement enterURL=this.driver.findElement(By.xpath("//input[@name='Ticket URL']"));
                        String URL=generateURL();
                        enterURL.sendKeys(URL);
                        waitFor(500);
                        WebElement entercomment=this.driver.findElement(commentarea);
                        String comment=generatetitle();
                        entercomment.sendKeys(comment);
                        waitFor(500);
                        WebElement submitbutton=this.driver.findElement(submit);
                        submitbutton.click();
                        waitFor(8000);
                        threedots.click();
                        waitFor(6000);
                        waitForElementPresence(textDiscard);
                        WebElement Discardtext = this.driver.findElement(textDiscard);
                        Discardtext.click();
                        waitFor(5000);
                        String comment1=generatetitle();
                        clickbyactionclass(commentarea);
                        WebElement entercomment2=this.driver.findElement(commentarea);
                        entercomment2.clear();
                        entercomment2.sendKeys(comment1);
                        waitFor(500);
                        clickbyactionclass(submit);
                        waitFor(4000);
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                       // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
            } else {
                System.out.println("Status Open is not exists for all resources in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return captureresourceId;
}
	
	public String table_Discard_VMS_capture_resourceID_value_afterDiscardresource() throws TimeoutException { 
		this.act = new Actions(driver);
		String captureresourceId = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");       //**********
            By resourcerows = By.xpath("//table//tr//td[3]//span");  ///***************
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li"); ///**********
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        // Get total number of pages
	      
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("//table//tr["+i+"]//td[3]//span"));      ///td3 can be
                	act.moveToElement(currentrow);
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                	WebElement costcheck = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table//tbody//tr["+i+"]//td[1]"));  ////2nd table      ////td10 can be
                	String rowText = costcheck.getText().trim();
                	boolean check=rowText.contains("-");
                    waitFor(1000);
                    if (check==false) {
                        resourceIdCost = resourceId + " - Status is: " + rowText;
                        captureresourceId = resourceId;
                        found = true;
                        waitFor(300);
                        
                        // Perform action based on dropdown selection (accept or dismiss)
                        WebElement threedots = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr["+i+"]//td[3]//button"));
                        //act.moveToElement(threedots).click().build().perform();
                        threedots.click();
                        waitFor(500);
                        
                        WebElement MovetoOpentext = this.driver.findElement(By.xpath("//a//span[contains(text(),'Move to Open')]"));
                        MovetoOpentext.click();
                        waitFor(1000);
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                       // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
            } else {
                System.out.println("Status Open is not exists for all resources in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return captureresourceId;
}
	
	public String table_Discard_Upsize_VMS_capture_resourceID_value_afterDiscardresource() throws TimeoutException { 
		this.act = new Actions(driver);
		String captureresourceId = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");       //**********
            By resourcerows = By.xpath("//table//tr//td[3]//span");  ///***************
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li"); ///**********
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        // Get total number of pages
	      
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("//table//tr["+i+"]//td[3]//span"));      ///td3 can be
                	act.moveToElement(currentrow);
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                	WebElement costcheck = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table//tbody//tr["+i+"]//td[1]"));  ////2nd table      ////td10 can be
                	String rowText = costcheck.getText().trim();
                	boolean check=rowText.contains("-");
                    waitFor(1000);
                    if (check==true) {
                        resourceIdCost = resourceId + " - Status is: " + rowText;
                        captureresourceId = resourceId;
                        found = true;
                        waitFor(300);
                        
                        // Perform action based on dropdown selection (accept or dismiss)
                        WebElement threedots = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr["+i+"]//td[3]//button"));
                        //act.moveToElement(threedots).click().build().perform();
                        threedots.click();
                        waitFor(500);
                        
                        WebElement MovetoOpentext = this.driver.findElement(By.xpath("//a//span[contains(text(),'Move to Open')]"));
                        MovetoOpentext.click();
                        waitFor(1000);
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                       // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
            } else {
                System.out.println("Upsize resources not exists in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return captureresourceId;
}
	
	public String table_Discard_VMS_capture_resourceID_value_afterarchiveresource() throws TimeoutException { 
		this.act = new Actions(driver);
		String captureresourceId = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");       //**********
            By resourcerows = By.xpath("//table//tr//td[3]//span");  ///***************
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li"); ///**********
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        By commentarea=By.xpath("//div[@class='d-flex flex-column ']//textarea[@name='Comment']");       ////****************
	        By submit=By.xpath("//button[contains(text(),'Submit')]");
	        // Get total number of pages
	      
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("//table//tr["+i+"]//td[3]//span"));      ///td3 can be
                	act.moveToElement(currentrow);
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                	WebElement costcheck = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table//tbody//tr["+i+"]//td[1]"));  ////2nd table      ////td10 can be
                	String rowText = costcheck.getText().trim();
                	boolean check=rowText.contains("-");
                    waitFor(1000);
                    if (check==false) {
                        resourceIdCost = resourceId + " - Status is: " + rowText;
                        captureresourceId = resourceId;
                        found = true;
                        waitFor(300);
                        
                        // Perform action based on dropdown selection (accept or dismiss)
                        WebElement threedots = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr["+i+"]//td[3]//button"));
                        //act.moveToElement(threedots).click().build().perform();
                        threedots.click();
                        waitFor(500);
                        
                        WebElement Archivetext = this.driver.findElement(By.xpath("//a//span[contains(text(),'Archive')]"));
                        Archivetext.click();
                        waitFor(1000);
                        WebElement entercomment=this.driver.findElement(commentarea);
                        String comment=generatetitle();
                        entercomment.sendKeys(comment);
                        waitFor(500);
                        WebElement submitbutton=this.driver.findElement(submit);
                        submitbutton.click();
                        waitFor(1000);
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                       // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
            } else {
                System.out.println("Status Open is not exists for all resources in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return captureresourceId;
}
	
	public String table_Discard_Upsize_VMS_capture_resourceID_value_afterarchiveresource() throws TimeoutException { 
		this.act = new Actions(driver);
		String captureresourceId = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");       //**********
            By resourcerows = By.xpath("//table//tr//td[3]//span");  ///***************
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li"); ///**********
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        By commentarea=By.xpath("//div[@class='d-flex flex-column ']//textarea[@name='Comment']");       ////****************
	        By submit=By.xpath("//button[contains(text(),'Submit')]");
	        // Get total number of pages
	      
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("//table//tr["+i+"]//td[3]//span"));      ///td3 can be
                	act.moveToElement(currentrow);
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                	WebElement costcheck = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table//tbody//tr["+i+"]//td[1]"));  ////2nd table      ////td10 can be
                	String rowText = costcheck.getText().trim();
                	boolean check=rowText.contains("-");
                    waitFor(1000);
                    if (check==true) {
                        resourceIdCost = resourceId + " - Status is: " + rowText;
                        captureresourceId = resourceId;
                        found = true;
                        waitFor(300);
                        
                        // Perform action based on dropdown selection (accept or dismiss)
                        WebElement threedots = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr["+i+"]//td[3]//button"));
                        //act.moveToElement(threedots).click().build().perform();
                        threedots.click();
                        waitFor(500);
                        
                        WebElement Archivetext = this.driver.findElement(By.xpath("//a//span[contains(text(),'Archive')]"));
                        Archivetext.click();
                        waitFor(1000);
                        WebElement entercomment=this.driver.findElement(commentarea);
                        String comment=generatetitle();
                        entercomment.sendKeys(comment);
                        waitFor(500);
                        WebElement submitbutton=this.driver.findElement(submit);
                        submitbutton.click();
                        waitFor(1000);
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                       // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
            } else {
                System.out.println("Status Open is not exists for all resources in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return captureresourceId;
}
	
	public boolean checkupsizerecordexitsintable() throws TimeoutException { 
		this.act = new Actions(driver);
		String resourceIdCost = null;
		boolean exits = false;
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");       //**********
            By resourcerows = By.xpath("//table//tr//td[3]//span");  ///***************
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li"); ///**********
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        // Get total number of pages
	      
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("//table//tr["+i+"]//td[3]//span"));      ///td3 can be
                	act.moveToElement(currentrow);
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                	WebElement costcheck = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table//tbody//tr["+i+"]//td[1]"));  ////2nd table      ////td10 can be
                	String rowText = costcheck.getText().trim();
                	boolean check=rowText.contains("-");
                    waitFor(1000);
                    if (check==true) {
                      exits=true;
                      resourceIdCost=resourceId;
                        break; // Exit the loop if cost is found
                    }
                }

                if (exits) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                       // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (exits) {
                System.out.println("Upsize VM is found: " + resourceIdCost);
            } else {
                System.out.println("Upsize VM record not exits in table");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return exits;
}
	
	public String table_Downsize_VMS_module_capture_resourceID_value_afterArchiveresource() throws TimeoutException { 
		this.act = new Actions(driver);
		String captureresourceId = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");       //**********
            By resourcerows = By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr/td[3]/span[1]");  ///***************
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li"); ///**********
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        // Get total number of pages
	      
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr["+i+"]/td[3]/span[1]"));      ///td3 can be
                	act.moveToElement(currentrow);
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                    WebElement statusrow = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr["+i+"]//td[2]//span"));  ////2nd table      ////td10 can be
                    String rowText = statusrow.getText().trim();
                    // Check if text is equal to 'Open'
                    String statuscheck="Open";
                    boolean check=rowText.equals(statuscheck);
                    waitFor(1000);
                    if (check==true) {
                        resourceIdCost = resourceId + " - Status is: " + rowText;
                        captureresourceId = resourceId;
                        found = true;
                        waitFor(300);
                        
                        // Perform action based on dropdown selection (accept or dismiss)
                        WebElement threedots = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr["+i+"]//td[3]//button"));
                        threedots.click();
                        waitFor(500);
                        WebElement archivetext = this.driver.findElement(By.xpath("//a//span[contains(text(),'Archive')]"));
                        archivetext.click();
                        waitFor(1000);
                        WebElement entercomment=this.driver.findElement(By.xpath("//textarea[@name='Comment']"));
                        String comment=generatetitle();
                        entercomment.sendKeys(comment);
                        waitFor(500);
                        WebElement submitbutton=this.driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
                        submitbutton.click();
                        waitFor(8000);
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                       // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
            } else {
                System.out.println("Status Open is not exists for all resources in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return captureresourceId;
}
	
	public String table_Downsize_VMS_capture_resourceID_value_afterArchiveresource() throws TimeoutException { 
		this.act = new Actions(driver);
		String captureresourceId = "";
		try {
			 // Locators
            By nextPageButtonLocator=By.xpath("//span[contains(text(),'Next')]");       //**********
            By resourcerows = By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr/td[3]/span[1]");  ///***************
            // Get total number of pages
            By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li"); ///**********
	        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
	        int elementcount=totalpaginationelements.size();
	        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
	        By textApprove=By.xpath("//a//span[contains(text(),'Approve')]");  //////****************
	        By textDone=By.xpath("//a//span[contains(text(),'Done')]");       ////****************
	        // Get total number of pages
	      
	        String  result = driver.findElement(getTotalPages).getText(); 
	        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
	        // Implement a method to get total pages
	        int pagecount = Integer.parseInt(totalPages);
	        System.out.println("Page Count is "+pagecount);
           

            boolean found = false;
          String resourceIdCost = "";

            // Loop through each page
            for (int page = 1; page <= pagecount; page++) {
                // Get all rows in the current page
                List<WebElement> rows = this.driver.findElements(resourcerows);
                int totalrows=rows.size();

                // Iterate through each row to check cost greater than zero
              //  for (WebElement row : rows) 
                for(int i=1;i<=totalrows;i++)
                {
                    // Extract resource id and cost
                  //  WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
                  //  String resourceId = resourceIdElement.getText().trim();
                	WebElement currentrow = this.driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr["+i+"]/td[3]/span[1]"));      ///td3 can be
                	act.moveToElement(currentrow);
                	String resourceId = currentrow.getText().trim();
                	waitFor(300);
                    WebElement statusrow = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr["+i+"]//td[2]//span"));  ////2nd table      ////td10 can be
                    String rowText = statusrow.getText().trim();
                    // Check if text is equal to 'Open'
                    String statuscheck="Open";
                    boolean check=rowText.equals(statuscheck);
                    waitFor(1000);
                    if (check==true) {
                        resourceIdCost = resourceId + " - Status is: " + rowText;
                        captureresourceId = resourceId;
                        found = true;
                        waitFor(300);
                        
                        // Perform action based on dropdown selection (accept or dismiss)
                        WebElement threedots = this.driver.findElement(By.xpath("//div[@class='rightSizing_fixed__U3UpO']//table[@class='table-hover']//tbody//tr["+i+"]//td[3]//button"));
                        threedots.click();
                        waitFor(500);
                        WebElement archivetext = this.driver.findElement(By.xpath("//a//span[contains(text(),'Archive')]"));
                        archivetext.click();
                        waitFor(1000);
                        WebElement entercomment=this.driver.findElement(By.xpath("//textarea[@name='Comment']"));
                        String comment=generatetitle();
                        entercomment.sendKeys(comment);
                        waitFor(500);
                        WebElement submitbutton=this.driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
                        submitbutton.click();
                        waitFor(8000);   
                        break; // Exit the loop if cost is found
                    }
                }

                if (found) {
                    break; // Exit the loop if cost is found
                } else {
                    // Navigate to the next page if cost is not found
                    if (page < pagecount) {
                        // Click next page button or implement pagination logic
                        // Example: Implement logic to click next page button
                        WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
                        act.moveToElement(nextPageButton).click().build().perform();
                       // nextPageButton.click();
                        waitFor(3000);
                        // Replace with actual logic to navigate to next page
                    }
                }
            }

            if (found) {
                System.out.println("Resource with status 'Open' is found: " + resourceIdCost);
            } else {
                System.out.println("Status Open is not exists for all resources in table.");
            }
		
	}catch (WebDriverException e) {
        // Handle WebDriverException
        System.err.println("WebDriverException occurred: " + e.getMessage());
    }
		return captureresourceId;
}
	
	public String getresourcestatus(String searchid,String resourcerows,String statuscolumn) {
		this.act = new Actions(driver);
		boolean found = false;
		String status = null;
		String resourcerows1stpart=row1stpart(resourcerows);
		String resourcerows2ndpart=row2ndpart(resourcerows);
		String status1stpart=row1stpart(statuscolumn);
		String status2ndpart=row2ndpart(statuscolumn);
		try {	
			 String resourceIdToSearch = searchid;

		        // Locators
		        By rowsLocator = By.xpath(resourcerows);
		        By nextPageButtonLocator = By.xpath("//span[contains(text(),'Next')]"); ////////////
		        
		        By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li");////////
		        List<WebElement> totalpaginationelements=driver.findElements(totalelementsinpagination);
		        waitFor(2000);
		        int elementcount=totalpaginationelements.size();
		        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
		        
		        String  result = driver.findElement(getTotalPages).getText(); 
		        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
		        int pagecount = Integer.parseInt(totalPages);

		        for (int page = 1; page <= pagecount; page++) {

		            List<WebElement> rows = driver.findElements(rowsLocator);
		            int count=rows.size();

		            for (int i=1;i<=count;i++) {
		                String resourceIdText = driver.findElement(By.xpath(resourcerows1stpart+"["+i+"]"+resourcerows2ndpart)).getText();
		                waitFor(300);
		                if (resourceIdText.equals(resourceIdToSearch)) {
		                    found = true;
		                    status=driver.findElement(By.xpath(status1stpart+"["+i+"]"+status2ndpart)).getText().trim();
		                    break; 
		                }
		            }

		            if (found) {
		                break;
		            } else {
		                if (page < pagecount) {
		                    WebElement nextPageButton = driver.findElement(nextPageButtonLocator);
		                    act.moveToElement(nextPageButton).click().build().perform();
		                    waitFor(3000);
		                }
		            }
		        }

		        if (!found) {
		            addinfolog("Resource ID found in table: "+searchid);
		        }
			 }
		        catch (WebDriverException e) {
		            // Handle WebDriverException
		            System.err.println("WebDriverException occurred: " + e.getMessage());
		        }
		return status;
	}
	
	public boolean verifyresourceidpresentintable(String id) {
		this.act = new Actions(driver);
		boolean found = false;
		try {	
			 String resourceIdToSearch = id;

		        // Locators
		        By rowsLocator = By.xpath("//table[@class='table table-hover ']//tbody//tr//td[4]");
		        By resourceIdColumnLocator = By.xpath("//table[@class='table table-hover ']//td[4]"); 
		        By nextPageButtonLocator = By.xpath("//span[contains(text(),'Next')]"); 
		        
		        By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li");
		        List<WebElement> totalpaginationelements=driver.findElements(totalelementsinpagination);
		        waitFor(2000);
		        int elementcount=totalpaginationelements.size();
		        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
		        // Get total number of pages
		        
		        String  result = driver.findElement(getTotalPages).getText(); 
		        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
		        // Implement a method to get total pages
		        int pagecount = Integer.parseInt(totalPages);
		        System.out.println("Page Count is "+pagecount);
		        

		        // Loop through each page
		        for (int page = 1; page <= pagecount; page++) {
		            // Get all rows in the current page
		            List<WebElement> rows = driver.findElements(rowsLocator);

		            // Iterate through each row to find the resource id
		            for (WebElement row : rows) {
		                // Check if the resource id text is present in the current row
		                //WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
		                act.moveToElement(row).build().perform();
		                String resourceIdText = row.getText();
		                //System.out.println(resourceIdText);
		                waitFor(300);
		                if (resourceIdText.equals(resourceIdToSearch)) {
		                    System.out.println("Text found in table!");
		                    found = true;
		                    break; // Exit the loop if text is found
		                }
		            }

		            if (found) {
		                break; // Exit the loop if text is found
		            } else {
		                // Navigate to the next page if text is not found
		                if (page < pagecount) {
		                    WebElement nextPageButton = driver.findElement(nextPageButtonLocator);
		                    act.moveToElement(nextPageButton).click().build().perform();
		                    waitFor(3000);
		                    // Wait for page to load, implement wait mechanism if needed
		                }
		            }
		        }

		        if (!found) {
		            System.out.println("Text not found in table.");
		        }
			 }
		        catch (WebDriverException e) {
		            // Handle WebDriverException
		            System.err.println("WebDriverException occurred: " + e.getMessage());
		        }
		return found;
	}
	
	public boolean verifyresourceidpresentintablepatametercolumnindex(String id,int columnindex) {
		this.act = new Actions(driver);
		boolean found = false;
		try {	
			 String resourceIdToSearch = id;

		        // Locators
		        By rowsLocator = By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr/td["+columnindex+"]/span[1]");
		        By resourceIdColumnLocator = By.xpath("//table[@class='table table-hover ']//td["+columnindex+"]"); 
		        By nextPageButtonLocator = By.xpath("//span[contains(text(),'Next')]"); 
		        
		        By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li");
		        List<WebElement> totalpaginationelements=driver.findElements(totalelementsinpagination);
		        waitFor(1000);
		        int elementcount=totalpaginationelements.size();
		        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
		        // Get total number of pages
		        String  result = driver.findElement(getTotalPages).getText(); 
		        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
		        // Implement a method to get total pages
		        int pagecount = Integer.parseInt(totalPages);
		        System.out.println("Page Count is "+pagecount);

		        // Loop through each page
		        for (int page = 1; page <= pagecount; page++) {
		            // Get all rows in the current page
		            List<WebElement> rows = driver.findElements(rowsLocator);

		            // Iterate through each row to find the resource id
		            for (WebElement row : rows) {
		                // Check if the resource id text is present in the current row
		                //WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
		                
		                String resourceIdText = row.getText();
		                //System.out.println(resourceIdText);
		                waitFor(300);
		                if (resourceIdText.equals(resourceIdToSearch)) {
		                    System.out.println("Text found in table!");
		                    found = true;
		                    break; // Exit the loop if text is found
		                }
		            }

		            if (found) {
		                break; // Exit the loop if text is found
		            } else {
		                // Navigate to the next page if text is not found
		                if (page < pagecount) {
		                    WebElement nextPageButton = driver.findElement(nextPageButtonLocator);
		                    act.moveToElement(nextPageButton).click().build().perform();
		                    waitFor(2000);
		                    // Wait for page to load, implement wait mechanism if needed
		                }
		            }
		        }

		        if (!found) {
		            System.out.println("Text not found in table.");
		        }
			 }
		        catch (WebDriverException e) {
		            // Handle WebDriverException
		            System.err.println("WebDriverException occurred: " + e.getMessage());
		        }
		return found;
	}
	
	public boolean verifyresourceidpresentintablepatametercolumnindexrowpath(String id,int columnindex) { //discard
		this.act = new Actions(driver);
		boolean found = false;
		try {	
			 String resourceIdToSearch = id;

		        // Locators
		        By rowsLocator = By.xpath("//table//tr//td["+columnindex+"]//span");
		        By nextPageButtonLocator = By.xpath("//span[contains(text(),'Next')]"); 
		        
		        By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li");
		        List<WebElement> totalpaginationelements=driver.findElements(totalelementsinpagination);
		        waitFor(1000);
		        int elementcount=totalpaginationelements.size();
		        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
		        // Get total number of pages
		        String  result = driver.findElement(getTotalPages).getText(); 
		        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
		        // Implement a method to get total pages
		        int pagecount = Integer.parseInt(totalPages);
		        System.out.println("Page Count is "+pagecount);

		        // Loop through each page
		        for (int page = 1; page <= pagecount; page++) {
		            // Get all rows in the current page
		            List<WebElement> rows = driver.findElements(rowsLocator);

		            // Iterate through each row to find the resource id
		            for (WebElement row : rows) {
		                // Check if the resource id text is present in the current row
		                //WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
		            	waitFor(200);
		                String resourceIdText = row.getText().trim();
		                waitFor(1000);
		                if (resourceIdText.equals(resourceIdToSearch)) {
		                    System.out.println("Text found in table!");
		                    found = true;
		                    break; // Exit the loop if text is found
		                }
		            }

		            if (found) {
		                break; // Exit the loop if text is found
		            } else {
		                // Navigate to the next page if text is not found
		                if (page < pagecount) {
		                    WebElement nextPageButton = driver.findElement(nextPageButtonLocator);
		                    act.moveToElement(nextPageButton).click().build().perform();
		                    waitFor(2000);
		                    // Wait for page to load, implement wait mechanism if needed
		                }
		            }
		        }

		        if (!found) {
		            System.out.println("Text not found in table.");
		        }
			 }
		        catch (WebDriverException e) {
		            // Handle WebDriverException
		            System.err.println("WebDriverException occurred: " + e.getMessage());
		        }
		return found;
	}
	
	public boolean verifyresourceidpresentintablediscardtable(String id,int columnindex) { //discard
		this.act = new Actions(driver);
		boolean found = false;
		try {	
			 String resourceIdToSearch = id;

		        // Locators
		        By rowsLocator = By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr/td["+columnindex+"]/span[1]");
		        By resourceIdColumnLocator = By.xpath("//table[@class='table table-hover ']//td["+columnindex+"]"); 
		        By nextPageButtonLocator = By.xpath("//span[contains(text(),'Next')]"); 
		        
		        By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li");
		        List<WebElement> totalpaginationelements=driver.findElements(totalelementsinpagination);
		        waitFor(1000);
		        int elementcount=totalpaginationelements.size();
		        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
		        // Get total number of pages
		        String  result = driver.findElement(getTotalPages).getText(); 
		        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
		        // Implement a method to get total pages
		        int pagecount = Integer.parseInt(totalPages);
		        System.out.println("Page Count is "+pagecount);

		        // Loop through each page
		        for (int page = 1; page <= pagecount; page++) {
		            // Get all rows in the current page
		            List<WebElement> rows = driver.findElements(rowsLocator);

		            // Iterate through each row to find the resource id
		            for (WebElement row : rows) {
		                // Check if the resource id text is present in the current row
		                //WebElement resourceIdElement = row.findElement(resourceIdColumnLocator);
		            	waitFor(2000);
		                String resourceIdText = row.getText().trim();
		                waitFor(2000);
		                System.out.println(resourceIdText);
		                waitFor(1000);
		                if (resourceIdText.equals(resourceIdToSearch)) {
		                    System.out.println("Text found in table!");
		                    found = true;
		                    break; // Exit the loop if text is found
		                }
		            }

		            if (found) {
		                break; // Exit the loop if text is found
		            } else {
		                // Navigate to the next page if text is not found
		                if (page < pagecount) {
		                    WebElement nextPageButton = driver.findElement(nextPageButtonLocator);
		                    act.moveToElement(nextPageButton).click().build().perform();
		                    waitFor(2000);
		                    // Wait for page to load, implement wait mechanism if needed
		                }
		            }
		        }

		        if (!found) {
		            System.out.println("Text not found in table.");
		        }
			 }
		        catch (WebDriverException e) {
		            // Handle WebDriverException
		            System.err.println("WebDriverException occurred: " + e.getMessage());
		        }
		return found;
	}
	
	public String sumofcostforallresource() {
		 double totalCost = 0;
		 int totalrows = 0;
		 String OutputString = null;
		 this.act = new Actions(driver);
		try {
			 By rowsLocator = By.xpath("//table[@class='table table-hover ']//tbody//tr");
		        By nextPageButtonLocator = By.xpath("//span[contains(text(),'Next')]"); 
		        By totalelementsinpagination = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li");
		        By tooltiplocator=By.xpath("//div[@class='fade tooltip_overlay__RSVLa show']");
		        List<WebElement> totalpaginationelements=this.driver.findElements(totalelementsinpagination);
		        waitFor(2000);
		        int elementcount=totalpaginationelements.size();
		        By getTotalPages = By.xpath("//ul[@class='pagination_pagination__YKHjL pagination pagination-sm']//li["+(elementcount-1)+"]");
		        // Get total number of pages
		        
		        String  result = driver.findElement(getTotalPages).getText(); 
		        String totalPages =  result.replaceAll("\\s*\\(current\\)", "");
		        // Implement a method to get total pages
		        int pagecount = Integer.parseInt(totalPages);
		        System.out.println("Page Count is "+pagecount);
			 for (int currentPage = 1; currentPage <= pagecount; currentPage++) {
		            // Count rows on current page
		            List<WebElement> rows = this.driver.findElements(rowsLocator);
		            waitFor(1000);
		            totalrows=totalrows+rows.size();
		            waitFor(1000);

		            // Sum costs on current page
		            List<WebElement> costElements = this.driver.findElements(By.xpath("//table[@class='table table-hover ']//tbody//tr/td[9]//div//button"));
		            waitFor(1000);
		            int count=costElements.size();
		            for (int i = 1; i <=count; i++) {
		            	WebElement element=this.driver.findElement(By.xpath("//table[@class='table table-hover ']//tbody//tr["+i+"]/td[9]//div//button"));
		            	act.moveToElement(element).click().build().perform();
		            	 waitFor(2000);
		            	WebElement tooltipcost=this.driver.findElement(tooltiplocator);
		            	waitFor(1000);
		            	String costText = tooltipcost.getText().replaceAll("[^\\d.]", ""); // Replace non-numeric characters
		                if (!costText.isEmpty()) {
		                    double costValue = Double.parseDouble(costText);
		                    totalCost=totalCost+costValue;
		                }
		            }

		            // Navigate to the next page if not on the last page
		            if (currentPage < pagecount) {
		                WebElement nextPageButton = this.driver.findElement(nextPageButtonLocator);
		            	act.moveToElement(nextPageButton).click().build().perform();
		                waitFor(3000);
		                // You might need to wait here to ensure the next page is loaded before proceeding
		            }
		        }

		        // Output results
		       // System.out.println("Total number of rows: " + rowCount);
		        System.out.println("Total sum of costs: " + totalCost);
		        System.out.println("Total Rows "+totalrows);
		        OutputString=totalrows+"cost"+totalCost;
		        System.out.println(OutputString);
		        }catch (WebDriverException e) {
           // Handle WebDriverException
           System.err.println("WebDriverException occurred: " + e.getMessage());
		
	} return OutputString;
}
	public String rowsplictformstring(String s) {
		String rows;
		// Find the index of "cost"
        int index = s.indexOf("cost");
        // Extract the integer part before "cost"
        rows = s.substring(0, index);
		return rows;
	}
	
	public String costsplictformstring(String s) {
		String modifiecost;
		// Find the index of "cost"
        int index = s.indexOf("cost");
        // Extract the part after "cost"
        String cost = s.substring(index + "cost".length());
        modifiecost=roundingDBStringValue(cost);
		return modifiecost;
	}
	
	public String matchtoDBStatus(String status) {
		String ActualStatus;
		if(status.equals("Open")) {
			ActualStatus=null;
		}else if(status.equals("Pending Approval")) {
			ActualStatus="accepted";
		}else if(status.equals("In Progress")) {
			ActualStatus="inprogress";
		}else if(status.equals("Done")) {
			ActualStatus="done";
		}else if(status.equals("Discarded")) {
			ActualStatus="discarded";
		}else if(status.equals("Archived")) {
			ActualStatus="archived";
		}else {
			ActualStatus=null;
		}
		return ActualStatus;
	}
	
	public String changenullstatus(String status) {
		String finalstatus=null;
		if(status==null) {
			finalstatus="0";
		}else {
			finalstatus=status;
		}
		return finalstatus;
	}
	
	public String row1stpart(String input) {
		  // Find the index where "//tr" starts
		 String splitString = "//tr";
		 // Check if the split string is found
		 int index = input.indexOf(splitString);
		 if (index == -1) {
	            System.out.println("Split string not found.");
	        }
		 // Extract the first output
	        String part = input.substring(0, index + splitString.length());
		return part;
	}
	
	public String row2ndpart(String input) {
		  // Find the index where "//tr" starts
		 String splitString = "//tr";
		 // Check if the split string is found
		 int index = input.indexOf(splitString);
		 if (index == -1) {
	            System.out.println("Split string not found.");
	        }
		 // Extract the first output
	        String part = input.substring(index + splitString.length());
		return part;
	}

	
	public void scrollDown(int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) this.driver;
		js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
	}
	
	public void mouse_moveToElementUsingWebElement(WebElement el) throws TimeoutException {
		this.act = new Actions(this.driver);
		this.act.moveToElement(el).build().perform();

	}
	
	public void clickactionUsingWebElement(WebElement element) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(elementWaitTime));
//		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.click();
	}

}
