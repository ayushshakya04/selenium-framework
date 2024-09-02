package TestClass;



import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;
import org.testng.annotations.Test;
import PageClass.SavingModule;
import Utilities.ReadConfigFile;

public class SavingTestClass extends BaseClass {

	SavingModule sav = new SavingModule(function);
	protected ReadConfigFile config = new ReadConfigFile();

	public String Cost_TotalSaving = config.get_DB_Savings_TotalSavings();
	public String Cost_ReviousMonth = config.get_DB_Savings_PreviousMonth();
	public String Cost_ThisYear = config.get_DB_Savings_ThisYear();
	public String Cost_Count_IdleResource = config.get_DB_Savings_IdleResource();
	public String Cost_Count_Rightsizing = config.get_DB_Savings_Rightsizing();
	public String Cost_Count_Snapshots = config.get_DB_Savings_Snapshots();
	public String Cost_ProjectedSaving = config.get_DB_Savings_ProjectedSavings();
	public String Cost_SavingTillDate = config.get_DB_Savings_SavingTillDate();

	
	@Test(enabled = false)
	public void At_Saving_page_verify_the_values_for_TOTAL_SAVINGS_shown_at_first_section_of_the_page_as_per_the_default_date_range() throws TimeoutException, ClassNotFoundException, SQLException {
		sav.addinfolog("Start of: At Saving page, verify the values for TOTAL SAVINGS shown at first section of the page as per the default date range");
		sav.navigate_to_Saving_module();
		sav.comparetotalsavingwithdb(Cost_TotalSaving, 1);      //validate total saving cost
		sav.addinfolog("Total Saving Cost match with database");
	    sav.compareprevioumonthwithdb(Cost_ReviousMonth, 1);      //validate previous month cost
	    sav.addinfolog("Previous Month Cost match with database");
	    sav.comparethisyearwithdb(Cost_ThisYear, 1);      //validate this year cost
	    sav.addinfolog("This Year Cost match with database");
	}
	
	@Test(enabled = false)
	public void At_Saving_page_verify_the_values_shown_for_IDLE_RESOURCES_RIGHTSIZING_SNAPSHOTS_at_first_section_of_the_page_as_per_the_default_date_range() throws TimeoutException, ClassNotFoundException, SQLException {
		sav.addinfolog("Start of: At Saving page, verify the values shown for IDLE RESOURCES, RIGHTSIZING, SNAPSHOTS at first section of the page as per the default date range");
		sav.navigate_to_Saving_module();
	    sav.countcompareidleresourcewithdb(Cost_Count_IdleResource, 1);      //validate Idle Resource count
	    sav.addinfolog("Idle Resource Count match with database");
		sav.compareidleresourcewithdb(Cost_Count_IdleResource, 2);      //validate Idle Resource cost
	    sav.addinfolog("Idle Resource Cost match with database");
	    sav.countcomparerightsizingwithdb(Cost_Count_Rightsizing, 1);      //validate Rightsizing count
	    sav.addinfolog("Rightsizing Count match with database");
	    sav.comparerightsizinghwithdb(Cost_Count_Rightsizing, 2);      //validate Rightsizing cost
	    sav.addinfolog("Rightsizing Cost match with database");
	    sav.countcomparesnapshotswithdb(Cost_Count_Snapshots, 1);      //validate Snapshots count
	    sav.addinfolog("Snapshots Count match with database");
	    sav.comparesnapshotswithdb(Cost_Count_Snapshots, 2);      //validate Snapshots cost
	    sav.addinfolog("Idle Resource Cost match with database");
	}
	
	@Test(enabled = false)
	public void At_Saving_page_verify_the_Projected_Savings_values_shown_at_first_section_of_the_page_as_per_the_selected_date_range() throws TimeoutException, ClassNotFoundException, SQLException {
		sav.addinfolog("Start of: At Saving page, verify the Projected Savings values shown at first section of the page as per the selected date range");
		sav.navigate_to_Saving_module();	
		sav.compareProjectedsavingwithdb(Cost_ProjectedSaving, 1);      //validate projected saving cost
	    sav.addinfolog("Projected Saving Cost match with database");
	    sav.comparesavingtilldatewithdb(Cost_SavingTillDate, 1);      //validate saving till date cost
	    sav.addinfolog("Saving Till Date Cost match with database");
	}
	
	   @Test(enabled=true)
		public void At_Savings_page_Verify_that_user_is_able_to_download_the_Idle_Resources_Saving_Detail_data() throws TimeoutException, IOException, InterruptedException {
			sav.addinfolog("Start of: At Savings page, Verify that user is able to download the Idle Resources's Saving Detail data");
			sav.navigate_to_Saving_module();
			sav.verify_savingpage();
			sav.addinfolog("'Idle Resources Savings' CSV File data and Frontend Table Data is Match");
	}


}
