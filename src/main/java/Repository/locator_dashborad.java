package Repository;

import org.openqa.selenium.By;

public class locator_dashborad {

	public static By Locator_Adminicon=By.xpath("//img[@class='rounded-circle ']");

	public static By Locator_SignOut=By.xpath("//a[contains(text(),'Sign Out')]");
	
	public static By Locator_CostInsights=By.xpath("//div[contains(text(),'Cost Insights')]");
	
	public static By Locator_TextTotalCost=By.xpath("//div[contains(text(),'Total Period Cost')]");
	
	public static By Locator_Textpreviousmonthcost=By.xpath("//div[contains(text(),'Previous Month')]");
	
	public static By Locator_TextThisYear=By.xpath("//div[contains(text(),'This Year')]");
	
	public static By Locator_TextCompute=By.xpath("//span[contains(text(),'COMPUTE')]"); 
	
	public static By Locator_TextServerless=By.xpath("//span[contains(text(),'Serverless')]");
	
	public static By Locator_TextStorage=By.xpath("//span[contains(text(),'Storage')]");
	
	public static By Locator_TextNetwork=By.xpath("//span[contains(text(),'Network')]");
	
	public static By Locator_TextDatabase=By.xpath("//div[contains(text(),'Database')]");
	
	public static By Locator_TextOthers=By.xpath("//div[contains(text(),'Others')]");
	
	public static By Locator_TotalCost=By.xpath("//div[@class='cost_explorer_ComboSECDivFirstDiv__QC01x']//span");
	
	public static By Locator_getcostfromtooltip=By.xpath("//div[@class='tooltip-inner']");
	
	public static By Locator_previousmonthcost=By.xpath("//div[@class='cost_explorer_period_cost__tWTi7']//div[@class='cost_explorer_preceding_cost__1+N6U']//span");
	
	public static By Locator_Computecostarea=By.xpath("//div[@class='cost_explorer_cost_header__pnnL6']//div[1]//div[1]//div[2][@class='cost_explorer_number_container__valen']//span//span");
	
	public static By Locator_databsecostarea=By.xpath("//div[@class='cost_explorer_cost_header__pnnL6']//div[1]//div[1]//div[1][@class='cost_explorer_number_container__valen']//span//span");
	
	public static By Locator_serverlesscostarea=By.xpath("//div[@class='cost_explorer_cost_header__pnnL6']//div[1]//div[2]//div[2][@class='cost_explorer_number_container__valen']//span//span");
	
	public static By Locator_storagecostarea=By.xpath("//div[@class='cost_explorer_cost_header__pnnL6']//div[2]//div[1]//div[2][@class='cost_explorer_number_container__valen']//span//span");
	
	public static By Locator_networkcostarea=By.xpath("//div[@class='cost_explorer_cost_header__pnnL6']//div[2]//div[2]//div[2][@class='cost_explorer_number_container__valen']//span//span");
	
	public static By Locator_othercostarea=By.xpath("//div[@class='cost_explorer_cost_header__pnnL6']//div[2]//div[2]//div[1][@class='cost_explorer_number_container__valen']//span//span");
	
	public static By Locator_ThisYearcost=By.xpath("//div[@class='cost_explorer_ComboSECONDDiv__4qVd-']//div[3]//div[3]//span[1]//span[1]");
	
	public static By Locator_textCustomWidgetstab=By.xpath("//div[contains(text(),'Custom Widgets')]");
	
	public static By Locator_buttonAddNewWidget=By.xpath("//span[contains(text(),'Add New Widget')]");
	
	public static By Locator_headerNewCustomWidgetWindow=By.xpath("//div[contains(text(),'New Custom Widget')]");

	public static By Locator_EnterWidgetTilte=By.xpath("//input[@class='form-control']");
	
	public static By Locator_Entershowprevious=By.xpath("//input[@class='form-input form-control']");
	
	public static By Locator_clickintervalfield=By.xpath("//div[@class='filter_search_trigger']//div[contains(text(),'Days')]");
	
	public static By Locator_clickGranularityfield=By.xpath("//div[@class='filter_search_trigger']//div[contains(text(),'Daily')]");
	
	public static By Locator_clickgraphtypefield=By.xpath("//div[@class='filter_search_trigger']//div[contains(text(),'Choose graph type')]");
	
	public static By Locator_clickDimensionsfield=By.xpath("//div[@class='filter_search_trigger']//div[contains(text(),'Choose dimension')]");
	
	public static By Locator_clickselectfilterfield=By.xpath("//div[contains(text(),'Select Filter')]");
	
	public static By Locator_clickselecttagsfield=By.xpath("//div[contains(text(),'Select Tags')]");
	
	public static By Locator_clicksavebutton=By.xpath("//button[contains(text(),'Save')]");
	
	public static By Locator_savingavailable_costarea=By.xpath("//div[@class='value__CONT']");
	
	public static By Locator_savingtilldate_costarea=By.xpath("//div[@class='saving-main-div']/div[@class='savings-costs']/span/span");
	
	public static By Locator_idleresource_count=By.xpath("//div[@class='idle-main-container']//div[1]//div[@class='idle-costs']");
	
	public static By Locator_rightsizing_count=By.xpath("//div[@class='idle-main-container']//div[2]//div[@class='idle-costs']");
	
	public static By Locator_snapshot_count=By.xpath("//div[@class='idle-main-container']//div[3]//div[@class='idle-costs']");

	public static By Locator_textGranuality=By.xpath("//div[text()='Granularity']");
	
	public static By Locator_clickGranualityValue=By.xpath("//ul[@class='list-unstyled flex-fill overflow-y-auto']/a/span/span[text()='Weekly']");
	
	public static By Locator_clickGranualityValue_Monthly=By.xpath("//ul[@class='list-unstyled flex-fill overflow-y-auto']/a/span/span[text()='Monthly']");
	
	public static By Locator_textDisplaydimension=By.xpath("//span[.='Dimensions']");
	
	public static By Locator_clickThreedotbutton=By.xpath("(//div[@class='dropdown']/button)[2]");
	
	public static By Locator_clickDownloadCSV=By.xpath("//a[normalize-space()='Download as CSV']");
	
	public static By Locator_CustomWidgetButtom=By.xpath("(//div[contains(text(),'Custom Widgets')])[1]");

	public static By Locator_widgetText=By.xpath("//div[@class='custom-widget_finops_project_cost__fARhj']");
	
	public static By Locator_clickgrapghtypevalue=By.xpath("//ul[@class='list-unstyled flex-fill overflow-y-auto']/a/span/span[text()='Line Chart']");
	
	public static By Locator_clickDimensionsvalue=By.xpath("//div[@class='p-0 filter-search_dropdown_menu__pwjz2 dropdown-menu show']/div/ul/a/span/span[text()='Meter']");
	
	public static By Locator_clickselecttag=By.xpath("//div[@class='p-0 filter-search_dropdown_menu__pwjz2 dropdown-menu show']/div/ul/a/span/span[text()='_Organizationname_']");
	
	public static By Locator_clickselecttagvalue=By.xpath("(//div[@class='w-100 dropdown']/div/div/span[text()='Choose Values'])[2]");
	
	public static By Locator_clickslecttagfiltergroupvalue=By.xpath("//div[@class='p-0 filter-search_dropdown_menu__pwjz2 dropdown-menu show']/div/ul/a/span/span[text()='Mcd-Gtes']");
	
	public static By Locator_graphBox=By.xpath("(//div[@class='content-wrapper pt-4']/div)[2]");
	
	public static By Locator_graphbox=By.xpath("//body[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/*[name()='svg']/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']");
	
	public static By Locator_textcustomwidgetName=By.xpath("//div[normalize-space()='test widget']");
	
	public static By Locator_clickthreedot=By.xpath("((//div[@class='dropdown']/button)[2])");
	
	public static By Locator_Text_firstCustomWidget_Card=By.xpath("((//div[@class='custom-widget_finops_project_cost__fARhj'])[1])");
	
	public static By Locator_clickdownloadcsvbutton=By.xpath("//a[text()='Download as CSV']");

	public static By Locator_Option_Bar_Graph=By.xpath("//a[text()='Bar Chart']");
	
	public static By Locator_clickselectfiltervalue=By.xpath("//span[@class='col overflow-hidden text-truncate' and @title='Subscription'])");
	
	public static By Locator_clickchoosevaluebutton=By.xpath("(//div[@class='w-100 dropdown']/div/div/span[text()='Choose Values'])[1]");
	
	public static By Locator_clickselectfiltersubvalue=By.xpath("//div[@class='p-0 filter-search_dropdown_menu__pwjz2 dropdown-menu show']/div/ul/a/span/span[text()='MCD-POLAND-PROD-01']");

	public static By Locator_clickSubscriptionBox=By.xpath("//span[@class='d-flex g-0 filter-search_dropdown_item__qSjs7']");
	
	public static By Locator_Bar_Graph=By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/*[name()='svg'][1]/*[name()='g'][4]/*[name()='g'][1]/*[name()='g'][1]/*[name()='g']/*[name()='path']");

	public static By Locator_Bar_cost_value=By.xpath("//table//tbody//tr//td[@class='cost_explorer_cost_cell__k0Z-6']");

	public String Table_Column_Locator="//div[@class='table_main_container']//table//tbody//tr//td";
	
	public static By Locator_Graph_Bar=By.cssSelector(".recharts-rectangle");
	
	public static By Locator_Graph_tooltip=By.xpath("//div[@class='cost_explorer_custom_tooltip__CCNBD']");
	
	public static By Locator_Graph_tooltip_Cost=By.xpath("//div[@class='cost_explorer_custom_tooltip__CCNBD']//table//tbody//tr//td[2]");
	
	public static By Locator_DashBoard_Select_Dimention = By.xpath("//div[text()='-- Select Dimension --']");

	public static By Locator_DashBoard_Select_Meter = By.xpath("//span[text()='Meter']");

	public static By Locator_addFilter = By.xpath("//div[text()='Add Filter']");
	
	public static By Locator_ClickMeter = By.xpath("(//ul/a[@role='button'])[6]");
	
	public static By Locator_value = By.xpath("//div[text()='Value']");
	
	public static By Locator_selectAll = By.xpath("//a//span[text()='Select All']");
	
	public static By ClickOnDashPage = By.xpath("//div[@class='recharts-legend-wrapper']");
	
	public static By MeterValuePlusSubscription = By.xpath("//span[@class='filter-section_chip_content__7xHr4']/following-sibling::span");



}
