package Repository;

import org.openqa.selenium.By;

public class locator_budgets {

	public static By Locator_Budget = By.xpath("(//li[@class='single_item'])[3]");
	public static By Locator_MouseActionOnSideBar = By.xpath("//div[@class='inner-sidebar mr-3']");
	public static By Locator_MouseActionOnBudgetPage = By
			.xpath("//img[@src=\"/static/media/Group 2310.ed6e3727f624001882c1f781f826e338.svg\"]");
	public static By Locator_CreateBudgets = By.xpath("(//span[text()='Create Budgets'])[1]");
	public static By Locator_Subcription = By.xpath("(//button[@id=\"dropdown-basic\"])[1]");
	public static By Locator_SubcriptionData = By
			.xpath("(//div[contains(@class, 'dropdown-menu')]//a[@class='dropdown-item'])[1]");
	public static By Locator_Dimention = By.xpath("(//button[@id='dropdown-basic'])[2]");
	public static By Locator_DimentionData = By.xpath("//a[text()='MeterCategory']");
	public static By Locator_Tags = By.xpath("(//button[@id='dropdown-basic'])[3]");
	public static By Locator_TagsData = By.xpath("(//div[@aria-labelledby='dropdown-basic'])[3]//a[4]");
	public static By Locator_BudgetName = By.xpath("//input[@placeholder='Enter a unique name']");
	public static By Locator_Period = By.xpath("(//button[@id='dropdown-basic'])[4]");
	public static By Locator_PeriodData = By.xpath("//a[text()='Monthly']");
	public static By Locator_StartDate = By.xpath("//input[@name=\"START_DATE\"]");
	public static By Locator_StartDate2 = By.xpath("//div[@aria-label='Choose Monday, July 1st, 2024']");
	public static By Locator_EndDate = By.xpath("//input[@placeholder='Enter End Date']");
	public static By Locator_EndDate2 = By.xpath("//div[@aria-label='Choose Wednesday, July 31st, 2024']");
	public static By Locator_BudgetAmount = By.xpath("//input[@placeholder=\"Enter amount\"]");
	public static By Locator_Notification_Type = By.xpath("(//button[@id='dropdown-basic'])[5]");
	public static By Locator_Notification_Type_Data = By.xpath("//a[text()='Actual']");
	public static By Locator_ComparisionOperator = By.xpath("(//button[@id='dropdown-basic'])[6]");
	public static By Locator_ComparisionOperator_Data = By.xpath("//a[text()='GreaterThan']");
	public static By Locator_ThresholdType = By.xpath("(//button[@id='dropdown-basic'])[7]");
	public static By Locator_ThresholdType_Data = By.xpath("//a[text()='PERCENTAGE']");
	public static By Locator_Threshold_Percent = By.xpath("//input[@placeholder='Select type']");
	public static By Locator_Alert_Receipt = By.xpath("//input[@placeholder='example@email.com']");
	public static By Locator_CreateButton = By.xpath("//button[text()='Create']");
	public static By Locator_CreateBudgetSuccess = By
			.xpath("//div[@class='budgets_toastNotification__VzKfe']//span[@class=' text-center']");
	public static final By BELL_ICON = By
			.xpath("//img[@src='/static/media/notifications_active.b43190d7408c8829380f535987fbc062.svg']");
	public static final By NOTIFICATION_OFF = By.xpath("//div[@class='tooltip-inner' and text()='Notification Off']");
	public static final By TOOLTIP_ELEMENT = By.xpath("//div[@class='tooltip-inner']");
	public static final By SUCCESS_MESSAGE = By
			.xpath("//span[contains(@class, 'text-center') and contains(text(), 'Budget snooze under progress.')]");
	public static final By successMessageLoacator = By
			.xpath("//span[contains(@class, 'text-center') and contains(text(), 'Budget snooze under progress.')]");
	public static final By tooltipLocator = By
			.xpath("//span[contains(@class, 'text-center') and contains(text(), 'Budget snooze under progress.')]");
	public static final By bellIcon = By.xpath("//img[@class='notification_bell']");
	public static final By budgetThreeDot = By.xpath(
			"//img[@class='notification_bell']/following-sibling::div/button[@id='budgets_custom_dropdown_toggle__fRjqc']");
	public static final By edit = By
			.xpath("//div[@class='budgets_dropdown_menu__wl6uU dropdown-menu show']/a[text()='Edit']");
	public static final By editAmount = By.xpath("//input[@name='AMOUNT']");
	public static final By save = By.xpath("//button[text()='Save']");
	public static final By BudgetEditSuccess = By
			.xpath("//div[contains(@class, 'budgets_toastNotification')]/span[contains(@class, 'text-center')]");
	public static final By BudgetEditedAmount = By
			.xpath("//div[@class='col usedDetails']//span[contains(@class, 'UsedCost')]");

	
	public static By Locator_budgetimg=By.xpath("//img[@alt='budget']");
	public static By Locator_profilebutton=By.xpath("//button[@class='btn btn-link']");
	public static By Locator_budgetbutton=By.xpath("//span[contains(text(),'Budgets')]");
	public static By Locator_textBudget=By.xpath("//span[.='Budgets']");
	public static By Locator_texbudgetname=By.xpath("//span[contains(text(),'TestBudget1X2')]");
	public static By Locator_threedotbutton=By.xpath("(//div[@id='budgetId']/button)[1]");
	public static By Locator_DownloadCSVfileButton=By.xpath("//a[contains(text(),'Download as CSV')]");

}
