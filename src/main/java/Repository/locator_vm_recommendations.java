package Repository;

import org.openqa.selenium.By;


public class locator_vm_recommendations {

	public static By Locator_MenuBar=By.xpath("//div[@class='sidebar-menu-container  pt-4']");

	public static By Locator_selectVMRecommendationsmodule=By.xpath("//a//span[contains(text(),'VM Recommendations')]");

	public static By Locator_totalcountof_VMRecommendations=By.xpath("//div[@class='container rightSizing_rec_number_container__KhylI']");

	public static By Locator_movetoheadersection=By.xpath("//div[@class='col-sm-10 col-md-10 col-10 text-right flex-header-menu justify-content-end']");

	public static By Locator_text_Total_VM_RECOMMENDATIONS=By.xpath("//div[contains(text(),'Total VM RECOMMENDATIONS')]");

	public static By Locator_text_PROJECTED_SAVINGS=By.xpath("//div[contains(text(),'PROJECTED SAVINGS')]");
	
	public static By Locator_text_OPPORTUNITY_MISSED=By.xpath("//div[contains(text(),'OPPORTUNITY MISSED')]");
	
	public static By Locator_costarea_OPPORTUNITY_MISSED=By.xpath("//div[@class='container innerContainer-text-2']//span");
	
    public static By Locator_text_Current_Costs=By.xpath("//div[contains(text(),'Current Costs')]");
	
	public static By Locator_costarea_Current_Costs=By.xpath("//div[@class='rightSizing_currunt_cost__PWZdZ']//span//span");
	
    public static By Locator_text_Projected_Costs=By.xpath("//div[contains(text(),'Projected Costs')]");
	
	public static By Locator_costarea_Projected_Costs=By.xpath("//div[@class='container  rightSizing_project_cost__f+Yc6']//span//span");

	public static By Locator_costarea_PROJECTED_SAVINGS=By.xpath("//div[@class='container innerContainer-text-2']//span");

	public static By Locator_text_DOWNSIZE=By.xpath("//div[contains(text(),'DOWNSIZE')]");

	public static By Locator_totalcountof_Downsize=By.xpath("//div[@class=' col rightSizing_rec_content__fvHlK'][1]//div[@class='rightSizing_reclowerContent_numbers__g4dbF']");

	public static By Locator_costarea_DOWNSIZE_savings=By.xpath("//div[@class=' container-fluid row']//div[1]//div[@class='container  rightSizing_rec_saving_cost__I5Wad']//span//span");

	public static By Locator_text_UPSIZE=By.xpath("//div[contains(text(),'uPSIZE')]");
	
	public static By Locator_costarea_UPSIZE_savings=By.xpath("//div[@class=' container-fluid row']//div[2]//div[@class='container  rightSizing_rec_saving_cost__I5Wad']//span//span");

	public static By Locator_button_switch=By.xpath("//button[@id='rightSizing_right_dropdown_header__966tY']");

	public static By Locator_switchto_Active=By.xpath("//div[@class='dropdown-menu show']//button[contains(text(),'Active')]");

	public static By Locator_switchto_Archive=By.xpath("//div[@class='dropdown-menu show']//button[contains(text(),'Archive')]");

	public static By Locator_totalcountof_Upsize=By.xpath("//div[@class=' col rightSizing_rec_content__fvHlK'][2]//div[@class='rightSizing_reclowerContent_numbers__g4dbF']");

	public static By Locator_get_costfrom_tooltip=By.xpath("//div[@class='tooltip-inner']");

	public static By Locator_tab_Downsize=By.xpath("//button[contains(text(),'Downsize')]");

	public static By Locator_tab_Upsize=By.xpath("//button[contains(text(),'Upsize')]");
	
	public static By Locator_tab_Discarded=By.xpath("//button[contains(text(),'Discarded')]");
	
	public static By Locator_text_No_UpsizeDAta=By.xpath("//div[@class='states_main_container__4BPIt']");

	public static By Locator_columnSavings=By.xpath("//table//thead/tr//th[contains(text(),'Savings')][1]");

	public static By Locator_fisrt_Row_value_column_Savings=By.xpath("//table//tbody//tr[1]/td[@class='rightSizing_accountCol__2VZxR rightSizing_accountCol_weight__Evh-- rightSizing_accountCol_center__nZDUs rightSizing_accountCol_green__aaNl0']");

	public static By Locator_columnResourceID=By.xpath("//table//thead//tr//th[@class='rightSizing_tableHead__KqYc1'][3]");

	public static By Locator_fisrt_Row_value_column_ResourceID=By.xpath("//table//tbody//tr[1]/td[@class='rightSizing_accountCol__2VZxR rightSizing_accountColNowrap__j7uAl rightSizing_accountTitleCol__12H5W rightSizing_accountTitleColData__uyrtt'][2]");

	public static By Locator_fisrt_Row_three_dots=By.xpath("//table//tbody//tr[1]//td//div//button/img");

	public static By Locator_threedots_click_on_accept=By.xpath("//a[contains(text(),'Accept')]");

	public static By Locator_threedots_click_on_dissmiss=By.xpath("//a[contains(text(),'Dismiss')]");

	public static By Locator_pagination_rowcount_dropdown=By.xpath("//button[@id='pagination_dropdown_item_button__NhLXn']");

	public static By Locator_select_50rows_in_table=By.xpath("//div[@class='dropdown-menu show']//a[contains(text(),'50')]");

	public static By Locator_text_Archived_VM_Resources=By.xpath("//div[contains(text(),'Archived VM Recommendations')]");
}
