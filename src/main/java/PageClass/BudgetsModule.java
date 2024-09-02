package PageClass;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import Repository.locator_budgets;
import Utilities.ReuseMethods;

public class BudgetsModule extends locator_budgets {

	ReuseMethods function;


	public BudgetsModule(ReuseMethods function) {
		this.function = function;
	}
	
	public void addinfolog(String Message) {
		function.addinfolog(Message);
	}


	public void Create_Budget() throws InterruptedException, TimeoutException {
		try {
			function.mouse_moveToElement(Locator_MouseActionOnSideBar);
			function.clickaction(Locator_Budget);
			function.mouse_moveToElement(Locator_MouseActionOnBudgetPage);
			function.clickaction(Locator_CreateBudgets);
			function.waitFor(3000);
			function.clickaction(Locator_Subcription);
			function.waitFor(3000);
			function.clickaction(Locator_SubcriptionData);
			function.clickaction(Locator_Dimention);
			function.clickaction(Locator_DimentionData);
			function.clickaction(Locator_Tags);
			function.clickaction(Locator_TagsData);
			String title = function.generatetitle();
			title = title.replace(" ", "_");
			function.entertextbysendkeys(Locator_BudgetName, title);
			function.clickaction(Locator_Period);
			function.clickaction(Locator_PeriodData);
			function.clickaction(Locator_StartDate);
			function.clickaction(Locator_StartDate2);
			function.clickaction(Locator_EndDate);
			function.clickaction(Locator_EndDate2);
			String amount = function.generateamount(4);
			function.entertextbysendkeys(Locator_BudgetAmount, amount);
			function.clickaction(Locator_Notification_Type);
			function.clickaction(Locator_Notification_Type_Data);
			function.clickaction(Locator_ComparisionOperator);
			function.clickaction(Locator_ComparisionOperator_Data);
			function.clickaction(Locator_ThresholdType);
			function.clickaction(Locator_ThresholdType_Data);
			String threshold = function.generateamount(3);
			function.entertextbysendkeys(Locator_Threshold_Percent, threshold);
			String mailid = function.generatemailid();
			function.entertextbysendkeys(Locator_Alert_Receipt, mailid);
			function.clickaction(Locator_CreateButton);
			function.waitFor(2000);
			String actualMessage = function.capturetext(Locator_CreateBudgetSuccess);
			String expectedMessage = "Budget creation under progress.";
			Assert.assertEquals(actualMessage, expectedMessage);
			function.waitFor(3000);
		} catch (TimeoutException e) {
			System.out.println("Timeout occurred: " + e.getMessage());
			// handle timeout exception, possibly retry or log the error
		}
		 catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			// handle other exceptions, possibly log or rethrow
		}

	}

	public void VerifyTooltipOff() throws TimeoutException, InterruptedException {
		// Move to the required element and perform initial actions
		function.mouse_moveToElement(Locator_MouseActionOnSideBar);
		function.clickaction(Locator_Budget);
		function.mouse_moveToElement(Locator_MouseActionOnBudgetPage);
		function.waitFor(3000);

		// Get the list of bell icons
		List<WebElement> bellIcons = function.getElements(BELL_ICON);
		Thread.sleep(5000);
		// Iterate through each bell icon
		for (WebElement bellIcon : bellIcons) {
			// Move to each bell icon and capture the tooltip text
			// function.mouse_moveToElement(bellIcon);
			Thread.sleep(5000);
			function.mouse_moveToElementUsingWebElement(bellIcon);
			Thread.sleep(2000);
			String tooltipText = function.capturetext(TOOLTIP_ELEMENT);

			// Verify the tooltip text whether it is "Notification Off"
			if (tooltipText.contains("Notification Off")) {
				System.out
						.println("Tooltip displays 'Notification Off' for one of the bell icons. Verification passed.");

				// Click on the bell icon to turn on notification
				// function.mouse_moveToElement(bellIcon);
				function.mouse_moveToElementUsingWebElement(bellIcon);
				// function.clickaction(bellIcon);
				function.clickactionUsingWebElement(bellIcon);
				// Verify the tooltip text has changed to "Notification On"
				String tooltipText2 = function.capturetext(TOOLTIP_ELEMENT);
				System.out.println("Tooltip text after clicking: " + tooltipText2);
				Assert.assertTrue(tooltipText2.contains("Notification On"), "Tooltip should display 'Notification On'");

				// Get and verify the success message
				String successMessage = function.capturetext(SUCCESS_MESSAGE);
				System.out.println("Success Message: " + successMessage);
				Assert.assertEquals(successMessage, "Budget snooze under progress.");
			} else {
				System.out.println(
						"Tooltip does not display 'Notification Off' for one of the bell icons. Verification failed.");
				Assert.fail("Tooltip should display 'Notification Off'");
			}
		}
	}

	public void VerifyTooltipOn() throws TimeoutException, InterruptedException {
		// Move to the required element and perform initial actions
		function.mouse_moveToElement(Locator_MouseActionOnSideBar);
		function.clickaction(Locator_Budget);
		function.mouse_moveToElement(Locator_MouseActionOnBudgetPage);
		function.waitFor(2000);

		// Get the list of bell icons
		List<WebElement> bellIcons = function.getElements(BELL_ICON);
		function.waitFor(4000);
		// Iterate through each bell icon
		for (WebElement bellIcon : bellIcons) {
			// Move to each bell icon and capture the tooltip text
			// function.mouse_moveToElement(bellIcon);
			function.waitFor(3000);
			function.mouse_moveToElementUsingWebElement(bellIcon);
			function.waitFor(3000);
			String tooltipText = function.capturetext(TOOLTIP_ELEMENT);

			// Verify the tooltip text whether it is "Notification Off"
			if (tooltipText.contains("Notification On")) {
				System.out
						.println("Tooltip displays 'Notification On' for one of the bell icons. Verification passed.");

				// Click on the bell icon to turn on notification
				function.mouse_moveToElementUsingWebElement(bellIcon);
				// function.clickaction(bellIcon);
				function.clickactionUsingWebElement(bellIcon);
				// Verify the tooltip text has changed to "Notification On"
				String tooltipText2 = function.capturetext(TOOLTIP_ELEMENT);
				System.out.println("Tooltip text after clicking: " + tooltipText2);
				Assert.assertTrue(tooltipText2.contains("Notification Off"),
						"Tooltip should display 'Notification Off'");

				// Get and verify the success message
				String successMessage = function.capturetext(SUCCESS_MESSAGE);
				System.out.println("Success Message: " + successMessage);
				Assert.assertEquals(successMessage, "Budget snooze under progress.");
			} else {
				System.out.println(
						"Tooltip does not display 'Notification Off' for one of the bell icons. Verification failed.");
				Assert.fail("Tooltip should display 'Notification On'");
			}
		}
	}

	public void EditBudget() throws TimeoutException, InterruptedException {
		function.waitFor(2000);
		function.mouse_moveToElement(Locator_MouseActionOnSideBar);
		function.clickaction(Locator_Budget);
		function.mouse_moveToElement(Locator_MouseActionOnBudgetPage);
		function.waitFor(2000);
		function.clickaction(budgetThreeDot);
		function.waitFor(2000);
		function.clickaction(edit);
		String EditedAmount = function.generateamount(5);
		function.entertextbysendkeys(editAmount, EditedAmount);
		function.waitFor(2000);
		function.clickaction(save);
		function.waitFor(2000);
		// Get and verify the success message
		String ActualResult = function.capturetext(BudgetEditSuccess);
		String ExpectedResult ="Budget Updation under progress.";
		Assert.assertEquals(ActualResult, ExpectedResult);
		function.waitFor(2000);
		String BudgetAmount = function.capturetext(BudgetEditedAmount);
		System.out.println("Budget Amount=" + BudgetAmount);
	}



	public void navigate_to_budget() throws TimeoutException {
		    function.clickaction(Locator_budgetimg);
		    function.mouse_moveToElement(Locator_profilebutton);  			 
	}
	
	public void budgetform_fill() throws TimeoutException, IOException, InterruptedException {       
		     function.waitForElementPresence(Locator_textBudget);
		     function.waitForElementPresence(Locator_texbudgetname);
		     function.clickaction(Locator_threedotbutton);
		     function.clickaction(Locator_DownloadCSVfileButton);
		     function.waitFor(5000);
		  }

	}


