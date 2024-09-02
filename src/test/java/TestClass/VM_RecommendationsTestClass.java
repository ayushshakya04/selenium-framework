package TestClass;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import PageClass.VM_RecommendationsModule;
import Utilities.ReadConfigFile;
@Listeners(Utilities.ExtentReport.class)
public class VM_RecommendationsTestClass extends BaseClass{
	
	VM_RecommendationsModule vm = new VM_RecommendationsModule(function);
	protected ReadConfigFile config=new ReadConfigFile();
	
	public String DBtotalallcostcountofVMSActivefirstsection=config.get_DB_alltypesof_costcount_VMS_Active_firstsection();
	public String DBtotalallcostcountofVMSArchivefirstsection=config.get_DB_alltypesof_costcount_VMS_Archive_firstsection();
	public String DBTatalallcostcountupsizeactivepage=config.get_DB_alltypesof_costcount_VMS_Active_Upsize();
	public String DBtotalallcostcountofVMSArchiveUpsize=config.get_DB_alltypesof_costcount_VMS_Archive_Upsize(); 

	@Test(enabled=true)
	public void Verify_on_accepting_the_Downsize_VM_Recommendations_savings_should_be_decreased() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		vm.addinfolog("Start of: Verify on accepting the Downsize VM Recommendations savings should be decreased");
		vm.navigate_to_vm_recmmendations_module();
		vm.acceptresourceunderdownsizetab();        // functionality wise verification. (check count and cost after accepting downsize VM)
		vm.addinfolog("After Accepting resource, count and cost are reduce form downsize and savings");
		vm.comparetotalvmcountwithdb(DBtotalallcostcountofVMSActivefirstsection,1);  //  Verify Total Count VMs with databse
		vm.addinfolog("Total Count VM Recommendations match with databse");
		vm.comparetotaldownsizecountwithdb(DBtotalallcostcountofVMSActivefirstsection, 2);    // Verify Total count of downsize with databse
		vm.addinfolog("Total count of downsize match with databse");
		vm.comparetotaldownsizecostwithdb(DBtotalallcostcountofVMSActivefirstsection, 3);     // Verify cost of downsize saving with database
		vm.addinfolog("Total cost of downsize savings match with databse");
		vm.compareprojectedsavingcostwithdb(DBtotalallcostcountofVMSActivefirstsection, 6);  //  verify cost of projected saving with database
		vm.addinfolog("Total cost of projected savings match with databse");
	}
	
	@Test(enabled=true)
	public void Verify_on_Archive_the_Downsize_VM_Recommendations_Projected_Savings_should_be_decreased() throws ClassNotFoundException, TimeoutException, SQLException, IOException {
		vm.addinfolog("Start of: Verify on Archive the Downsize VM Recommendations Projected Savings  should be decreased");
		vm.navigate_to_vm_recmmendations_module();
		vm.archiveresourceunderdownsizetab();        // functionality wise verification. (check count and cost after archive downsize VM)
		vm.addinfolog("After Archive resource, count and cost are reduce form downsize and savings");
		vm.comparetotalvmcountwithdb(DBtotalallcostcountofVMSActivefirstsection,1);  //  Verify Total Count VMs with databse
		vm.addinfolog("Total Count VM Recommendations match with databse");
		vm.comparetotaldownsizecountwithdb(DBtotalallcostcountofVMSActivefirstsection, 2);    // Verify Total count of downsize with databse
		vm.addinfolog("Total count of downsize match with databse");
		vm.comparetotaldownsizecostwithdb(DBtotalallcostcountofVMSActivefirstsection, 3);     // Verify cost of downsize saving with database
		vm.addinfolog("Total cost of downsize savings match with databse");
		vm.compareprojectedsavingcostwithdb(DBtotalallcostcountofVMSActivefirstsection, 6);  //  verify cost of projected saving with database
		vm.addinfolog("Total cost of projected savings match with databse");
	}
	
	@Test(enabled=true)
	public void At_VM_Recommendations_page_verify_that_archive_downsize_record_is_being_added_to_Archived_page() throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		vm.addinfolog("Start of: At VM Recommendations page, verify that archive downsize record, is being added to Archived page");
		vm.navigate_to_vm_recmmendations_module();
		vm.verifycountandcostatarchivepageafterremovingrecordformactive();
		vm.addinfolog("Archive downsize record's cost and count, is being added to Archived page");
		vm.comparetotalvmcountwithdb(DBtotalallcostcountofVMSArchivefirstsection,1);  //  Verify Total Count VMs with databse
		vm.addinfolog("Total Count VM Recommendations match with databse");
		vm.comparetotaldownsizecountwithdb(DBtotalallcostcountofVMSArchivefirstsection, 5);    // Verify Total count of downsize with databse
		vm.addinfolog("Total count of downsize match with databse");
		vm.comparetotaldownsizecostwithdb(DBtotalallcostcountofVMSArchivefirstsection, 6);     // Verify cost of downsize saving with database
		vm.addinfolog("Total cost of downsize savings match with databse");
		vm.comparecurrentcostwithdb(DBtotalallcostcountofVMSArchivefirstsection, 8);     // Verify Current Cost with database
		vm.addinfolog("Current Cost match with databse");
		vm.compareprojectedcostwithdb(DBtotalallcostcountofVMSArchivefirstsection, 9);     // Verify Projected Cost with database
		vm.addinfolog("Projected Cost match with databse");
		vm.comparemissedcostwithdb(DBtotalallcostcountofVMSArchivefirstsection, 10);     // Verify Oppertunity Missed Cost with database
		vm.addinfolog("Oppertunity Missed Cost match with databse");
	}
	
	
	@Test(enabled=true)
	public void Verify_the_on_unarchiving_any_record_of_from_Archive_page_it_is_being_moved_to_Active_page_of_recommendation() throws TimeoutException, IOException {
		vm.addinfolog("Start of: Verify the on unarchiving any record of from Archive page, it is being moved to Active page of recommendation");
		vm.navigate_to_vm_recmmendations_module();
		vm.unarchiving_any_record_of_from_Archive_page();
		vm.addinfolog("Unarchiving any record of from Archive page, moved to Active page of recommendation");
	}
	
	@Test(enabled=true)
	public void Verify_on_Accept_the_Upsize_VM_Recommendations_Upsize_costing_and_Current_cost_should_be_decreased() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		vm.addinfolog("Start of: Verify on Accept the Upsize VM Recommendations, Upsize costing and Current cost should be decreased");
		vm.navigate_to_vm_recmmendations_module();
		vm.Accepting_resource_count_and_cost_are_reduce_form_upsize();         // functionality wise verification. (check count and cost after accepting Upsize VM)
		vm.addinfolog("After Accepting resource, count and cost are reduce form upsize and savings");
		vm.comparetotalupsizecountwithdb(DBTatalallcostcountupsizeactivepage, 2);    // Verify Total count of Upsize with databse
		vm.addinfolog("Total count of Upsize match with databse");
		vm.compareupsizesavingcostwithdb(DBTatalallcostcountupsizeactivepage, 3);     // Verify cost of Upsize saving with database
		vm.addinfolog("Total cost of Upsize savings match with databse");
		vm.comparecurrentcostwithdb(DBTatalallcostcountupsizeactivepage, 4);  //  verify current cost with database
		vm.addinfolog("Current Cost match with databse");
	}
	
	@Test(enabled=false)
	public void Verify_on_Archive_the_Upsize_VM_Recommendations_Upsize_costing_and_Current_cost_should_be_decreased() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		vm.addinfolog("Start of: Verify on Archive the Upsize VM Recommendations, Upsize costing and Current cost should be decreased");
		vm.navigate_to_vm_recmmendations_module();
		vm.archiveresourceunderupsizetab();        // functionality wise verification. (check count and cost after archive Upsize VM)
		vm.addinfolog("After Archive resource, count and cost are reduce form Upsize and savings");
		vm.comparetotalupsizecountwithdb(DBTatalallcostcountupsizeactivepage, 2);    // Verify Total count of Upsize with databse
		vm.addinfolog("Total count of Upsize match with databse");
		vm.compareupsizesavingcostwithdb(DBTatalallcostcountupsizeactivepage, 3);     // Verify cost of Upsize saving with database
		vm.addinfolog("Total cost of Upsize savings match with databse");
		vm.comparecurrentcostwithdb(DBTatalallcostcountupsizeactivepage, 4);  //  verify current cost with database
		vm.addinfolog("Current Cost match with databse");
	}
	
	@Test(enabled=false)
	public void At_VM_Recommendations_page_verify_that_archive_upsize_record_is_being_added_to_Archived_page() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		vm.addinfolog("Start of: At VM Recommendations page, verify that archive upsize record, is being added to Archived page");
		vm.navigate_to_vm_recmmendations_module();
		vm.verifyarchiveupsizerecordaddedinarchivepage();
		vm.addinfolog("Archive Upsize record's cost and count, is being added to Archived page");
		vm.comparetotalvmcountwithdb(DBtotalallcostcountofVMSArchiveUpsize,1);  //  Verify Total Count VMs with databse
		vm.addinfolog("Total Count VM Recommendations match with databse");
		vm.comparetotalupsizecountwithdb(DBtotalallcostcountofVMSArchiveUpsize, 2);    // Verify Total count of Upsize with databse
		vm.addinfolog("Total count of Upsize match with databse");
		vm.compareupsizesavingcostwithdb(DBtotalallcostcountofVMSArchiveUpsize, 3);     // Verify cost of Upsize saving with database
		vm.addinfolog("Total cost of Upsize savings match with databse");
		vm.comparecurrentcostwithdb(DBtotalallcostcountofVMSArchiveUpsize, 4);  //  verify cost of projected saving with database
		vm.addinfolog("Current Cost match with databse");
	}
	
	@Test(enabled=true)
	public void Verify_the_on_unarchive_any_record_of_Upsize_from_Archive_page_it_is_being_moved_to_Active_page_of_VM_Recommendations_under_Upsize_tab() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		vm.addinfolog("Start of: Verify the on unarchive any record of Upsize from Archive page, it is being moved to Active page of VM Recommendations under Upsize tab");
		vm.navigate_to_vm_recmmendations_module();
		vm.unarchiving_any_Upsizerecord_of_from_Archive_page();
		vm.addinfolog("Unarchiving any record of from Archive page, moved to Active page of recommendation");
		vm.comparetotalvmcountwithdb(DBTatalallcostcountupsizeactivepage,1);  //  Verify Total Count VMs with databse
		vm.addinfolog("Total Count VM Recommendations match with databse");
		vm.comparetotalupsizecountwithdb(DBTatalallcostcountupsizeactivepage, 2);    // Verify Total count of Upsize with databse
		vm.addinfolog("Total count of Upsize match with databse");
		vm.compareupsizesavingcostwithdb(DBTatalallcostcountupsizeactivepage, 3);     // Verify cost of Upsize saving with database
		vm.addinfolog("Total cost of Upsize savings match with databse");
		vm.comparecurrentcostwithdb(DBTatalallcostcountupsizeactivepage, 4);  //  verify cost of projected saving with database
		vm.addinfolog("Current Cost match with databse");
	}
	
	@Test
	public void Verify_on_Discard_the_Downsize_record_Projected_savings_should_be_decreased() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		vm.addinfolog("Start of: Verify on Discard the Downsize  record,Projected savings should be decreased");
		vm.navigate_to_vm_recmmendations_module();
		vm.Discard_the_Downsize_record_activepage();      // Functinality part is cover
		vm.addinfolog("Discarded resource is added in discard tab in active page");
		vm.comparetotalvmcountwithdb(DBtotalallcostcountofVMSActivefirstsection,1);  //  Verify Total Count VMs with databse
		vm.addinfolog("Total Count VM Recommendations match with databse");
		vm.comparetotaldownsizecountwithdb(DBtotalallcostcountofVMSActivefirstsection, 2);    // Verify Total count of downsize with databse
		vm.addinfolog("Total count of downsize match with databse");
		vm.comparetotaldownsizecostwithdb(DBtotalallcostcountofVMSActivefirstsection, 3);     // Verify cost of downsize saving with database
		vm.addinfolog("Total cost of downsize savings match with databse");
		vm.comparecurrentcostwithdb(DBtotalallcostcountofVMSActivefirstsection, 4);     // Verify Current Cost with database
		vm.addinfolog("Current Cost match with databse");
		vm.compareprojectedsavingcostwithdb(DBtotalallcostcountofVMSActivefirstsection, 6);  //  verify cost of projected saving with database
		vm.addinfolog("Total cost of projected savings match with databse");
	}
	
	@Test
	public void Verify_the_Move_To_Open_discarded_Downsize_record_Projected_savings_should_be_increased() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		vm.addinfolog("Start of: Verify the Move To Open  discarded Downsize  record, Projected savings should be increased");
		vm.navigate_to_vm_recmmendations_module();
		vm.Move_To_Open_discarded_Downsize();
		vm.addinfolog("Moved resource is added in downsize tab of active page");
		vm.comparetotalvmcountwithdb(DBtotalallcostcountofVMSActivefirstsection,1);  //  Verify Total Count VMs with databse
		vm.addinfolog("Total Count VM Recommendations match with databse");
		vm.comparetotaldownsizecountwithdb(DBtotalallcostcountofVMSActivefirstsection, 2);    // Verify Total count of downsize with databse
		vm.addinfolog("Total count of downsize match with databse");
		vm.comparetotaldownsizecostwithdb(DBtotalallcostcountofVMSActivefirstsection, 3);     // Verify cost of downsize saving with database
		vm.addinfolog("Total cost of downsize savings match with databse");
		vm.comparecurrentcostwithdb(DBtotalallcostcountofVMSActivefirstsection, 4);     // Verify Current Cost with database
		vm.addinfolog("Current Cost match with databse");
		vm.compareprojectedsavingcostwithdb(DBtotalallcostcountofVMSActivefirstsection, 6);  //  verify cost of projected saving with database
		vm.addinfolog("Total cost of projected savings match with databse");
	}
	
	@Test
	public void Verify_that_Archive_discarded_downsize_record_is_being_added_to_Archive_page() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		vm.addinfolog("Start of: Verify that Archive discarded downsize  record, is being added to Archive page");
		vm.navigate_to_vm_recmmendations_module();
		vm.Archive_discarded_downsize_record_is_being_added_to_Archive_page();
		vm.addinfolog("Archive discarded resource is moved to archive page table");
		vm.comparetotalvmcountwithdb(DBtotalallcostcountofVMSArchivefirstsection,1);  //  Verify Total Count VMs with databse
		vm.addinfolog("Total Count VM Recommendations match with databse");
		vm.comparetotaldownsizecountwithdb(DBtotalallcostcountofVMSArchivefirstsection, 5);    // Verify Total count of downsize with databse
		vm.addinfolog("Total count of downsize match with databse");
		vm.comparetotaldownsizecostwithdb(DBtotalallcostcountofVMSArchivefirstsection, 6);     // Verify cost of downsize saving with database
		vm.addinfolog("Total cost of downsize savings match with databse");
		vm.comparecurrentcostwithdb(DBtotalallcostcountofVMSArchivefirstsection, 8);     // Verify Current Cost with database
		vm.addinfolog("Current Cost match with databse");
		vm.comparemissedcostwithdb(DBtotalallcostcountofVMSArchivefirstsection, 10);     // Verify Oppertunity Missed Cost with database
		vm.addinfolog("Oppertunity Missed Cost match with databse");
	}
	
	@Test
	public void Verify_on_Discard_the_Upsize_record_TOTAL_VM_RECOMMENDATIONS_Upsize_count_costing_and_Current_cost_should_be_decreased() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		vm.addinfolog("Start of: Verify on Discard the Upsize  record, TOTAL VM RECOMMENDATIONS, Upsize count, costing and Current cost should be decreased");
		vm.navigate_to_vm_recmmendations_module();
		vm.Discard_the_Upsize_record();
		vm.addinfolog("Discard Upsize record is get removed form upside table under active page");
		vm.comparetotalvmcountwithdb(DBTatalallcostcountupsizeactivepage,1);  //  Verify Total Count VMs with databse
		vm.addinfolog("Total Count VM Recommendations match with databse");
		vm.comparetotalupsizecountwithdb(DBTatalallcostcountupsizeactivepage, 2);    // Verify Total count of Upsize with databse
		vm.addinfolog("Total count of Upsize match with databse");
		vm.compareupsizesavingcostwithdb(DBTatalallcostcountupsizeactivepage, 3);     // Verify cost of Upsize saving with database
		vm.addinfolog("Total cost of Upsize savings match with databse");
		vm.comparecurrentcostwithdb(DBTatalallcostcountupsizeactivepage, 4);  //  verify cost of projected saving with database
		vm.addinfolog("Current Cost match with databse");
	}
	
	@Test
	public void Verify_the_Move_To_Open_discarded_Upsize_record_Total_VM_Recommendation_Upsize_count_costing_and_Current_cost_should_be_increased() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		vm.addinfolog("Start of: Verify the Move To Open  discarded Upsize record, Total VM Recommendation, Upsize count, costing and Current cost should be increased.");
		vm.navigate_to_vm_recmmendations_module();
		vm.Moved_discarded_Upsize_record();
		vm.addinfolog("Moved upsize resource's count and cost added in upsize stats");
		vm.comparetotalvmcountwithdb(DBTatalallcostcountupsizeactivepage,1);  //  Verify Total Count VMs with databse
		vm.addinfolog("Total Count VM Recommendations match with databse");
		vm.comparetotalupsizecountwithdb(DBTatalallcostcountupsizeactivepage, 2);    // Verify Total count of Upsize with databse
		vm.addinfolog("Total count of Upsize match with databse");
		vm.compareupsizesavingcostwithdb(DBTatalallcostcountupsizeactivepage, 3);     // Verify cost of Upsize saving with database
		vm.addinfolog("Total cost of Upsize savings match with databse");
		vm.comparecurrentcostwithdb(DBTatalallcostcountupsizeactivepage, 4);  //  verify cost of projected saving with database
		vm.addinfolog("Current Cost match with databse");
	}
	
	@Test
	public void Verify_that_Archive_discarded_upsize_record_is_being_added_to_Archive_Recommendation_page() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		vm.addinfolog("Start of: Verify that Archive discarded upsize  record, is being added to Archive Recommendation page");
		vm.navigate_to_vm_recmmendations_module();
		vm.Archive_discarded_upsize_record();
		vm.addinfolog("Discarded upsize record is added in upsize archive table");
		vm.comparetotalvmcountwithdb(DBtotalallcostcountofVMSArchiveUpsize,1);  //  Verify Total Count VMs with databse
		vm.addinfolog("Total Count VM Recommendations match with databse");
		vm.comparetotalupsizecountwithdb(DBtotalallcostcountofVMSArchiveUpsize, 2);    // Verify Total count of Upsize with databse
		vm.addinfolog("Total count of Upsize match with databse");
		vm.compareupsizesavingcostwithdb(DBtotalallcostcountofVMSArchiveUpsize, 3);     // Verify cost of Upsize saving with database
		vm.addinfolog("Total cost of Upsize savings match with databse");
		vm.comparecurrentcostwithdb(DBtotalallcostcountofVMSArchiveUpsize, 4);  //  verify cost of projected saving with database
		vm.addinfolog("Current Cost match with databse");
	}
}
