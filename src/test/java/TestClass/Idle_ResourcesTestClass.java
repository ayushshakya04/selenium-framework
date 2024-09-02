package TestClass;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import PageClass.Idle_ResourcesModule;
import Utilities.ReadConfigFile;

@Listeners(Utilities.ExtentReport.class)
public class Idle_ResourcesTestClass extends BaseClass {


	Idle_ResourcesModule idle = new Idle_ResourcesModule(function);
	protected ReadConfigFile config=new ReadConfigFile();
	
	public String DBtotalcountofidleresource=config.get_DB_Totalcount_idleresource();
	public String DBtotalcostofidleresource=config.get_DB_Totalcost_idleresource();
	public String DBtotalcostidleresourcecolumn=config.get_DB_columncost_idleresource();
	public String DBtotalcountidleresourcecolumn=config.get_DB_columncount_idleresource();
	public String DB_Totalcost_IdelResource_availableSavings=config.get_DB_Totalcost_IdelResource_availableSavings();
	public String DB_Totalcost_idleresource_savingtilldate=config.get_DB_Totalcost_idleresource_savingtilldate();
	public String DB_Totalcountcost_idleresource_resourcetype=config.get_DB_Totalcountcost_idleresource_resourcetype();
	public String DB_columncountcost_idleresourcetypes=config.get_DB_columncountcost_idleresourcetypes();
	public String DB_totalcostcount_graph7days_elasticip=config.get_DB_Totalcountcost_graph7days_elasticips();
	public String DB_graph_columnname_cost=config.get_DB_graphIdle_cost_columnname();
	public String DB_graph_columnname_count=config.get_DB_graphIdle_count_columnname();
	public String DB_totalcostcount_graphmorethan31days_VMs=config.get_DB_Totalcountcost_graphmorethan32days_VMs();
	public String DB_Resource_Status=config.get_DB_IdleResource_Status();
	
	@Test(enabled=false)
	public void At_Idle_resources_page_verify_the_values_shown_at_first_section_of_the_Active_Idle_Resources_page() throws TimeoutException, ClassNotFoundException, SQLException, IOException, InterruptedException{
		
		idle.addinfolog("Start of: At Idle Resources page, verify the values shown at first section of the Active Idle Resources page");
		idle.navigate_to_idle_resource_module();                 // User should be at Idle Resource Module
		idle.Verify_fields_under_idle_resource();                // Validate text fields
		idle.addinfolog("Text: 'Idle Resource Count', 'Elastic IPs', 'VMs', 'Disks', 'Load Balancer' is display at idle resource page");
		idle.compare_idle_resource_totalcount(DBtotalcountofidleresource,DBtotalcountidleresourcecolumn);            // validate Total Idle Resource Count 
		idle.addinfolog("Total Idle Resource Count is match form FinOps MCD to Database");
		idle.compare_idle_resource_totalcost(DBtotalcostofidleresource,DBtotalcostidleresourcecolumn);          // validate Total Idle Resource Cost
		idle.addinfolog("Total Idle Resource Cost is match form FinOps MCD to Database");
		idle.capture_resource_type_count_cost(DB_Totalcountcost_idleresource_resourcetype,DB_columncountcost_idleresourcetypes,2, 3);
		idle.addinfolog("Resource Types, Count and Cost are match with databse");
		}
	
	@Test(enabled=false)
	public void Verify_the_Savings_Available_values_shown_at_first_section_of_the_Active_Idle_Resources_Page() throws IOException, TimeoutException, ClassNotFoundException, SQLException {
		idle.addinfolog("Start of: Verify the Savings Available values shown at first section of Idle Resources Page");
		idle.navigate_to_idle_resource_module();
		idle.Verify_fields_under_idle_resource_saving();
		idle.addinfolog("Text: 'SAVINGS AVAILABLE' & 'Saved till date' is display at Idle Resources Page");
		idle.compare_cost_save_till_date(DB_Totalcost_idleresource_savingtilldate,DBtotalcostidleresourcecolumn);            // validate Save Till Date Cost 
		idle.addinfolog("Save Till Date cost is match form FinOps MCD to Database");
		idle.compare_cost_available_savings(DB_Totalcost_IdelResource_availableSavings,DBtotalcostidleresourcecolumn);          // validate Available Savings Cost
		idle.addinfolog("Available Savings Cost is match form FinOps MCD to Database");
		
	}
	
	@Test (enabled=true)
	public void Verify_on_accepting_the_Idle_resource_savings_should_be_decreased() throws TimeoutException, IOException, ClassNotFoundException, SQLException{
		idle.addinfolog("Start of: Verify on accepting the Idle resource savings should be decreased");
		idle.navigate_to_idle_resource_module();
		idle.accept_resource_check_saving_cost(DB_Resource_Status, 1);
	}
	
	@Test(enabled=true)
	public void Verify_on_dismissing_the_Idle_resource_savings_should_be_decreased() throws TimeoutException, IOException, ClassNotFoundException, SQLException{
		idle.addinfolog("Start of: Verify on dismissing the Idle resource savings should be decreased");
		idle.navigate_to_idle_resource_module();
		idle.dismiss_resource_check_saving_cost(DB_Resource_Status, 1);
	}
	
	@Test(enabled=true)
	public void At_Idle_Resources_page_verify_that_dismissed_Idle_resource_of_Resource_Type_Disks_is_being_CountCost_to_Archived_page() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		idle.addinfolog("Start of: At Idle Resources page, verify that dismissed Idle resource of Resource Type- Disks, is being added to Archived page");
		idle.navigate_to_idle_resource_module();
		idle.verify_dismissed_Idleresource_CountCost_added_Archived_page(DB_Resource_Status, 1);
	}
	
	@Test(enabled=true)
	public void Verify_the_on_unarchiving_any_record_of_Idle_resource_from_Archive_page_it_is_being_moved_to_Active_page_of_Idle_Resource() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		idle.addinfolog("Start of: Verify the on unarchiving any record of Idle resource from Archive page, it is being moved to Active page of Idle Resource");
		idle.navigate_to_idle_resource_module();
		idle.verify_unarchivedrecord_added_active_page(DB_Resource_Status, 1);
	}
	
	@Test(enabled=true)
	public void At_Idle_Resource_page_verify_the_total_count_and_Savings_for_resource_type_Elastic_IP_for_Idle_for_7_days() throws TimeoutException, ClassNotFoundException, SQLException {
		idle.addinfolog("Start of: At Idle Resource page, verify the total count and Savings for resource type- Elastic IP for Idle for 0-7 days");
		idle.navigate_to_idle_resource_module();
		idle.compare7dayscountcostofelasticip();     //  Verify Cost and Count form table to graph within frontend
		idle.addinfolog("Total Cost and Count of resource is match with table and graph data");
		idle.compare7dayselasticipcount(DB_totalcostcount_graph7days_elasticip,1);       //verify count of resource with graph and database
		idle.addinfolog("Total Count resource is match with graph and database data");
		idle.compare7dayselasticipcost(DB_totalcostcount_graph7days_elasticip,2);           //verify cost of resource with graph and database
		idle.addinfolog("Total Cost resource is match with graph and database data");
	}
	
	@Test(enabled=true)
	public void At_Idle_Resource_page_verify_the_total_count_and_Savings_for_resource_type_Virtual_Machine_Idle_for_more_than_31_days() throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		idle.addinfolog("Start of: At Idle Resource page, verify the total count and Savings for resource type- Virtual Machine Idle for more than 31 days");
		idle.navigate_to_idle_resource_module();
		idle.comparemorethan31dayscountcostofVMs();     //  Verify Cost and Count form table to graph within frontend
		idle.addinfolog("Total Cost and Count of resource is match with table and graph data");
		idle.comparemorethan31daysVMscount(DB_totalcostcount_graphmorethan31days_VMs,1);       //verify count of resource with graph and database
		idle.addinfolog("Total Count resource is match with graph and database data");
		idle.comparemorethan31daysVMscost(DB_totalcostcount_graphmorethan31days_VMs,2);           //verify cost of resource with graph and database
		idle.addinfolog("Total Cost resource is match with graph and database data");
	}
	
	@Test(enabled=true)
	public void Verify_on_discarding_the_Idle_resource_it_is_moved_to_Discarded_tab() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		idle.addinfolog("Verify on discarding the Idle resource it is moved to 'Discarded' tab");	
		idle.navigate_to_idle_resource_module();
		idle.discardresource(DB_Resource_Status, 1);
		idle.addinfolog("Discraded resource id is added in discard tabel");
		idle.compare_idle_resource_totalcount(DBtotalcountofidleresource,DBtotalcountidleresourcecolumn);            // validate Total Idle Resource Count 
		idle.addinfolog("Total Idle Resource Count is match form FinOps MCD to Database");
		idle.compare_idle_resource_totalcost(DBtotalcostofidleresource,DBtotalcostidleresourcecolumn);          // validate Total Idle Resource Cost
		idle.addinfolog("Total Idle Resource Cost is match form FinOps MCD to Database");
		idle.compare_cost_available_savings(DB_Totalcost_IdelResource_availableSavings,DBtotalcostidleresourcecolumn);          // validate Available Savings Cost
		idle.addinfolog("Available Savings Cost is match form FinOps MCD to Database");
		}
	
	
	@Test(enabled=true)
	public void Verify_that_user_is_able_to_move_the_discarded_Idle_Resource_record_to_Open_tab() throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		idle.addinfolog("Verify that user is able to move the discarded Idle Resource record to Open tab");
		idle.navigate_to_idle_resource_module();
		idle.movetoopenresource(DB_Resource_Status, 1);
		idle.addinfolog("Discraded resource is moved in to open status under open table");
		idle.compare_idle_resource_totalcount(DBtotalcountofidleresource,DBtotalcountidleresourcecolumn);            // validate Total Idle Resource Count 
		idle.addinfolog("Total Idle Resource Count is match form FinOps MCD to Database");
		idle.compare_idle_resource_totalcost(DBtotalcostofidleresource,DBtotalcostidleresourcecolumn);          // validate Total Idle Resource Cost
		idle.addinfolog("Total Idle Resource Cost is match form FinOps MCD to Database");
		idle.compare_cost_available_savings(DB_Totalcost_IdelResource_availableSavings,DBtotalcostidleresourcecolumn);          // validate Available Savings Cost
		idle.addinfolog("Available Savings Cost is match form FinOps MCD to Database");
	}
	
	@Test(enabled=true)
	public void Verify_that_user_is_able_to_move_the_discarded_Idle_Resource_record_to_Archive() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		idle.addinfolog("Verify that user is able to move the discarded Idle Resource record to Archive");
		idle.navigate_to_idle_resource_module();
		idle.archivediscardresource(DB_Resource_Status,1);
		idle.addinfolog("After archive discarded resource, resource is added under archive table");
	}
}
