package TestClass;

import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;
import PageClass.DashboardModule;
import Utilities.ReadConfigFile;

public class DashboardTestClass extends BaseClass {

	DashboardModule dash = new DashboardModule(function);
	protected ReadConfigFile config = new ReadConfigFile();

	public String DBtotalcost6days = config.get_DB_totalcost_6days();
	public String DBthisyearcost = config.get_DB_thisyear();
	public String DBpreviousmonthcost = config.get_DB_previousmonth();
	public String DBtotalcost6days_columnname = config.get_DB_totalcost_columnname();
	public String DBsavingtilldate = config.get_DB_savingtilldate();
	public String DBsnapshotcount = config.get_DB_snapshotcount();
	public String DBrightsizingcount = config.get_DB_rightsizing_count();
	public String DBidleresource = config.get_DB_IdleResourceCount();
	public String DBsavingavailable = config.get_DB_SavingsAvailable();
	public String DB_COSTSBYSERVICES = config.get_DB_COSTSBYSERVICES();
	public String get_DB_MeterValue = config.get_DB_MeterValue();

	@Test(enabled = false)
	public void verify_the_fields_and_values_shown_in_TOTALPERIODCOST_at_First_section_of_the_page() throws ClassNotFoundException, SQLException, IOException, TimeoutException {
		dash.addinfolog("Start of: At Cost Explorer page, verify the fields and values shown in 'TOTAL PERIOD COST' at First section of the page");
		dash.Verify_fields_first_section();      //validate all fields at first section of page
		dash.addinfolog("'Total Period Cost', 'Previous Month', 'This Year' fields are display at dashborad page");
		dash.compare_total_Period_cost(DBtotalcost6days,DBtotalcost6days_columnname);            // validate total period cost for 6 days
		dash.addinfolog("Total Period Cost for 6 days is match form FinOps MCD to Database");
		dash.compare_previous_month_cost(DBpreviousmonthcost,DBtotalcost6days_columnname);          // validate previous month cost
		dash.addinfolog("Previous Month Cost is match form FinOps MCD to Database");
		dash.compare_this_year_cost(DBthisyearcost,DBtotalcost6days_columnname);                     // validate this year cost
		dash.addinfolog("This Year Cost is match form FinOps MCD to Database");
		dash.addinfolog("End of: At Cost Explorer page, verify the fields and values shown in 'TOTAL PERIOD COST' at First section of the page");
	}
	
	@Test(enabled = false)
	public void At_Dashboard_page_verify_the_Savings_Available_values_shown_at_first_section_of_the_page_as_per_the_selected_date_range() throws ClassNotFoundException, TimeoutException, SQLException, IOException {
		dash.addinfolog("Start of: At Dashboard page, verify the Savings Available values shown at first section of the page as per the selected date range");
		dash.compare_savingsavialble_cost(DBsavingavailable, 1);       // validate savings available
		dash.addinfolog("Savings Available cost is match form FinOps MCD to Database");
		dash.compare_idleresource_count(DBidleresource, 1);         // validate idle resource count
		dash.addinfolog("Idle resource count is match form FinOps MCD to Database");
		dash.compare_rightsizing_count(DBrightsizingcount, 1);       // validate right sizing count
		dash.addinfolog("Right Sizing count is match form FinOps MCD to Database");
		dash.compare_snpashot_count(DBsnapshotcount, 1);            // validate snapshot count
		dash.addinfolog("Snapshot count is match form FinOps MCD to Database");
		dash.compare_savingtilldate_cost(DBsavingtilldate, 1);         // validate saving till date cost
		dash.addinfolog("Saving Till date cost is match form FinOps MCD to Database");
	}
	
	@Test(enabled = false)
	public void At_Cost_Explorer_page_verify_the_fields_and_values_shown_in_COST_BY_SERVICES_at_First_section_of_the_page() throws ClassNotFoundException, TimeoutException {
		dash.addinfolog("Start of: At Cost Explorer page, verify the fields and values shown in 'COST BY SERVICES' at First section of the page");
		dash.capture_services_type_cost(DB_COSTSBYSERVICES,1 , 2);
		dash.addinfolog("Costs By Services is match with database");
	}

	 @Test(enabled = true)
		public void At_Dashboard_page_Verify_that_user_is_able_to_download_the_Daily_Costs_and_Usage_data() throws TimeoutException, InterruptedException, IOException {
			dash.addinfolog("Start of: Download the daily costs and usage data");
			dash.download_Daily_Costs_and_Usage_CSV_file();
			dash.addinfolog("'Cost By Dimensions' CSV File data and Frontend Table Data is Match");
		}

	    @Test(enabled = false)
		public void To_verify_that_user_is_able_to_create_widget_by_selecting_filter_Subscription() throws IOException, InterruptedException, TimeoutException {
			 dash.addinfolog("Start of: At Dashboard page, verify that user is able to create widget by selecting filter Subscription");			       
			 dash.customWidget_form_fill();
			 dash.addinfolog("Filled out and submitted the custom widget form");
		}
		@Test(enabled = true)
		public void To_verify_that_user_is_able_to_download_the_widget_data() throws TimeoutException, IOException, InterruptedException {
			dash.addinfolog("Start of: To verify that user is able to download the widget data");	
			dash.downloadcustomwidget();
			dash.addinfolog("'Cost of Custom Widgets' CSV File data and Frontend Table Data is Match");
		}
		
	@Test
	public void Test_Meter_Value() throws InterruptedException, TimeoutException, ClassNotFoundException, SQLException {
		dash.addinfolog("Start verifiction og Dashboard Meter Value");
		// String BackendSide = dash.MeterValueFromDB(get_DB_MeterValue);
		String FrontendSide = dash.MeterValueFromUI();
		// Assert.assertEquals(BackendSide, FrontendSide);
		dash.addinfolog("Meter value Match from Database");
	}

}
