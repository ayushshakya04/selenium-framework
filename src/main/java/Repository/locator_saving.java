package Repository;

import org.openqa.selenium.By;


public class locator_saving {

	public static By Locator_MenuBar=By.xpath("//div[@class='sidebar-menu-container  pt-4']");

	public static By Locator_select_savingmodule=By.xpath("//a//span[contains(text(),'Saving')]");

	public static By Locator_movetoheadersection=By.xpath("//div[@class='col-sm-10 col-md-10 col-10 text-right flex-header-menu justify-content-end']");

	public static By Locator_text_TotalSavings=By.xpath("//span[contains(text(),'Total Savings')]");

	public static By Locator_text_IdleResource=By.xpath("//div[@class='savings_id_first_div__703Ac']//span[contains(text(),'Idle Resources')]");

	public static By Locator_text_Rightsizing=By.xpath("//div[@class='savings_id_first_div__703Ac']//span[contains(text(),'Rightsizing')]");

	public static By Locator_text_Snapshots=By.xpath("//div[@class='savings_id_first_div__703Ac']//span[contains(text(),'Snapshots')]");

	public static By Locator_text_ProjectedSavings=By.xpath("//div[contains(text(),'PROJECTED SAVINGS')]");

	public static By Locator_text_savingtilldate=By.xpath("//div[contains(text(),'Saved till date')]");

	public static By Locator_text_previousmonth=By.xpath("//span[contains(text(),'Previous Month')]");

	public static By Locator_text_thisyear=By.xpath("//div[contains(text(),'This Year')]");
	
	public static By Locator_get_costfrom_tooltip=By.xpath("//div[@class='tooltip-inner']");
	
	public static By Locator_get_costfrom_tooltip_spe=By.xpath("//div[@class='fade show tooltip bs-tooltip-top'][2]//div[@class='tooltip-inner']");

	public static By Locator_cost_TotalSavings=By.xpath("//div[@class='savings_totalSavingsBox__Gn-e0']//div[@class='savings_id_span_value__Vyqbs']//span");

	public static By Locator_cost_previousmonth=By.xpath("//div[@class='savings_ComboSECONDInnerDIv1__av4n9']//div[1]//span[@class='savings_preceding_cost__2dnMj']//span//span//span//span");

	public static By Locator_cost_thisyear=By.xpath("//div[@class='savings_ComboSECONDInnerDIv1__av4n9']//div//div[@class='savings_preceding_cost__2dnMj']");

	public static By Locator_cost_idelresource=By.xpath("//div[@class='savings_second_part__i8bMG']//div[1]//div[@class='savings_id_first_div__703Ac']//div//span//span");

	public static By Locator_cost_rightsizing=By.xpath("//div[@class='savings_second_part__i8bMG']//div[2]//div[@class='savings_id_first_div__703Ac']//div//span//span");

	public static By Locator_cost_snapshots=By.xpath("//div[@class='savings_second_part__i8bMG']//div[3]//div[@class='savings_id_first_div__703Ac']//div//span//span");

	public static By Locator_cost_ProjectedSavings=By.xpath("//div[@class='container savings_innerContainer_text_2__eUmnd']//span");

	public static By Locator_cost_savingtilldate=By.xpath("//div[@class='container savings_innerSecondContainer_num__2o+ir']//span");

	public static By Locator_count_idelresource=By.xpath("//div[@class='savings_second_part__i8bMG']//div[1]//div[@class='savings_id_second_div__ewfIX']//div//span[@class='savings_id_value_css__5uNhz']");

	public static By Locator_count_rightsizing=By.xpath("//div[@class='savings_second_part__i8bMG']//div[2]//div[@class='savings_id_second_div__ewfIX']//div//span[@class='savings_id_value_css__5uNhz']");

	public static By Locator_count_snapshots=By.xpath("//div[@class='savings_second_part__i8bMG']//div[3]//div[@class='savings_id_second_div__ewfIX']//div//span[@class='savings_id_value_css__5uNhz']");

	public static By Locator_saving_button=By.xpath("//img[@alt='saving']");
		
	public static By Locator_profilebutton=By.xpath("//button[@class='btn btn-link']");
		
	public static By Locator_textsaving=By.xpath("(//div[contains(text(),'Savings')])[1]");
		
	public static By Locator_textsavingidleresource=By.xpath("//div[contains(text(),'Idle Resources Savings')]");
		
	public static By Locator_threedotbutton=By.xpath("//div[@class='savings_menu_icon_css1__3AjLe']/div/button");

	public static By Locator_downloadCSVfile=By.xpath("//a[contains(text(),'Download as CSV')]");
	
	public static By Locator_Graph_Bar=By.cssSelector(".recharts-rectangle");
	
	public static By Locator_Graph_tooltip=By.xpath("//div[@class='savings_custom_tooltip__LdrX+']");
	
	public static By Locator_Graph_tooltip_Cost=By.xpath("//div[@class='savings_custom_tooltip__LdrX+']//table//tbody//tr//td[2]");

}
