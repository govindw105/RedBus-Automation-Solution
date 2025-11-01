package RedBusAutomation.RedBusAutomation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusAutomation {
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		driver.get("https://www.redbus.in/?utm_source=Couponzania"); // To launch the browser...!!

		WebElement SourceButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//div[contains(@class, \"srcDestWrapper\") and @role=\"button\"]//preceding::div[text()='From']")));
		SourceButton.click();

		// Search suggetion Locator
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'searchSuggestionWrapper')]")));
		WebElement searchTextBoxElement = driver.switchTo().activeElement(); // give me that textbox
		searchTextBoxElement.sendKeys("Mumbai");

		// Search Category Locator
		By searchCategoryLocator = By.xpath("//div[contains(@class, 'searchCategory')]");
		List<WebElement> searchlist = wait
				.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchCategoryLocator, 1));
		System.out.println(searchlist.size());

		// to get the number of results from Serach list
		WebElement locationSearchResult = searchlist.get(0);

		// Changing of WebElements
		By LocationNameLocator = By.xpath(".//div[contains(@class,\"listHeader\")]");
		List<WebElement> locationList = locationSearchResult.findElements(LocationNameLocator);
		System.out.println(locationList.size());

		for (WebElement location : locationList) {
			String lName = location.getText();

			if (lName.equalsIgnoreCase("Mumbai")) {
				location.click();
				break;
			}
		}

		// Focus on the To Section...!!
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(@class, \"searchSuggestionWrapper\")]")));
		WebElement toTextBox = driver.switchTo().activeElement();
		toTextBox.sendKeys("Pune");

		By toSearchCategoryLocator = By.xpath("//div[contains(@class, 'searchCategory')]");
		List<WebElement> toSearchList = wait
				.until(ExpectedConditions.numberOfElementsToBeMoreThan(toSearchCategoryLocator, 1));
		WebElement toLocationCategory = toSearchList.get(0);

		By toLocationNameLocator = By.xpath(".//div[contains(@class,\"listHeader\")]");
		List<WebElement> toLocationList = toLocationCategory.findElements(toLocationNameLocator);

		for (WebElement toLocation : toLocationList) {
			if (toLocation.getText().equalsIgnoreCase("Pune")) {
				toLocation.click();
				break;
			}
		}

		By searchButtonLocator = By.xpath("//button[contains(@class, 'searchButtonWrapper')]");
		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
		searchButton.click();

		By primoButtonLocator = By.xpath("//div[contains(text(), \"Primo Bus\")]");
		WebElement primoButton = wait.until(ExpectedConditions.elementToBeClickable(primoButtonLocator));
		primoButton.click();

		By busTitleLocator = By.xpath("//div[contains(@class, \"busesFoundText\")]");

		WebElement subTitle = null;
		if (wait.until(ExpectedConditions.textToBePresentInElementLocated(busTitleLocator, "buses"))) {
			subTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(busTitleLocator));

		}
		System.out.println(subTitle.getText());

		By tuppleWrapperLocator = By.xpath("//li[contains(@class, \"tupleWrapper\")]"); // Found the row locator
		By busesNameLocator = By.xpath(".//div [contains(@class, \"travelsName\")]"); // bus name locator

		// for one time Scroll
		// List<WebElement> rowList =
		// wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));
		// Initially there will be a set of rows present! row list----> //10
		// System.out.println("total Number of Rows" + rowList.size()); //10

//        for(WebElement row: rowList) {
//        	//Traverse each row one by one ..!!!
//        	System.out.println(row.findElement(busesNameLocator).getText()); //Chaining of webElement
//        }  // First 10 buses name will be printed...!!!

		// How to Scroll in Selenium WebDriver..!!
		// Next set of buses we need to scroll down to 3rd last element
		JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView({behaviour: 'Smooth'})", rowList.get(rowList.size()-3));
//        
//        Thread.sleep(5000);
//        List<WebElement> newRowList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(tuppleWrapperLocator, rowList.size()));
//        System.out.println("Total Number of Buses" + newRowList.size());
//       

		while (true) { // Lazy Loading
			// get the rows from the pages!
			List<WebElement> rowList = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));
			List<WebElement> endOfList = driver.findElements(By.xpath("//span[contains(text(), \"End of list\")]"));

			if (!endOfList.isEmpty()) {
				break; // loop!! exit conditon from the while loop
			}

			js.executeScript("arguments[0].scrollIntoView({behaviour: 'Smooth'})", rowList.get(rowList.size() - 3));
		}

		List<WebElement> rowList = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));
		for (WebElement row : rowList) {
			String BusName = row.findElement(busesNameLocator).getText();
			System.out.println(BusName);
		}

		System.out.println("Total Number of buses Loaded with Primo Filter" + rowList.size());

	}

}
