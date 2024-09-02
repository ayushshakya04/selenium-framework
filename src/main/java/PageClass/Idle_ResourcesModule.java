package PageClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.testng.Assert;
import org.testng.ITestResult;

import Repository.locator_idle_resources;
import Utilities.ReuseMethods;

public class Idle_ResourcesModule extends locator_idle_resources{

	ReuseMethods function;
	public Idle_ResourcesModule(ReuseMethods function) {
		this.function=function;
	}

	public void addinfolog(String Message) {
		function.addinfolog(Message);
	}

	public void navigate_to_idle_resource_module() throws TimeoutException {
		function.mouse_moveToElement(Locator_MenuBar);
		function.clickaction(Locator_selectidleresourcesmodule);
		function.mouse_moveToElement(Locator_movetoheadersection);
		function.waitForElementIsVisibleFluent(Locator_text_idle_resource_count, 400, 5);
	}

	public String validatetotalElastic_IPs() throws TimeoutException {
		function.mouse_moveToElement(Locator_text_Elastic_IPs);
		function.mouse_moveToElement(Locator_totalcountof_ElasticIPs);
		String count=function.capturetext(Locator_totalcountof_ElasticIPs);
		return count;
	}

	public String validatetotalcostofElastic_IPs() throws TimeoutException {
		function.mouse_moveToElement(Locator_totalcostarea_ElasticIPs);
		function.waitFor(1000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		return modifiecost;
	}
	public String validatetotalVMs() throws TimeoutException {
		function.mouse_moveToElement(Locator_text_VMs);
		function.mouse_moveToElement(Locator_totalcountof_VMs);
		String count=function.capturetext(Locator_totalcountof_VMs);
		return count;
	}

	public String validatetotalcostofVMs() throws TimeoutException {
		function.mouse_moveToElement(Locator_totalcostarea_VMs);
		function.waitFor(1000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		return modifiecost;
	}


	public String validatetotalDisks() throws TimeoutException {
		String count=function.capturetext(Locator_totalcountof_Disks);
		return count;
	}

	public String validatetotalcostofDisks() throws TimeoutException {
		function.mouse_moveToElement(Locator_totalcostarea_Disks);
		function.waitFor(1000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		return modifiecost;
	}


	public String validatetotalLoad_Balancer() throws TimeoutException {
		String count=function.capturetext(Locator_totalcountof_Load_Balancer);
		return count;
	}

	public String validatetotalcostofLoad_Balancer() throws TimeoutException {
		function.mouse_moveToElement(Locator_totalcostarea_Load_Balancer);
		function.waitFor(1000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		return modifiecost;
	}

	public void switch_to_Archive() throws TimeoutException {
		function.mouse_moveToElement(Locator_movetoheadersection);
		function.clickbyactionclass(Locator_button_switch);
		function.clickbyactionclass(Locator_switchto_Archive);
		function.waitFor(2000);
		function.verifyDisplayed(Locator_text_OPPORTUNITY_MISSED);

	}

	public void switch_to_Active() throws TimeoutException {
		function.mouse_moveToElement(Locator_button_switch);
		function.clickbyactionclass(Locator_button_switch);
		function.mouse_moveToElement(Locator_switchto_Active);
		function.clickbyactionclass(Locator_switchto_Active);
		function.waitFor(2000);
		function.verifyDisplayed(Locator_text_SAVINGS_AVAILABLE);
	}

	public String validatetotalcostofOPPORTUNITY_MISSED() throws TimeoutException {
		function.mouse_moveToElement(Locator_totalcostarea_OPPORTUNITY_MISSED);
		function.waitFor(1000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		return modifiecost;
	}

	public String validatetotalcountof_idle_resource() throws TimeoutException {
		function.waitForElementIsVisible(Locator_totalcountof_idle_resource, 45);
		String count=function.capturetext(Locator_totalcountof_idle_resource);
		return count;
	}

	public String validatetotalidle_resource_cost() throws TimeoutException {
		function.mouse_moveToElement(Locator_totalcostarea_idle_resource);
		function.waitFor(1000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		return modifiecost;
	}

	public void totalrowsintable50() throws TimeoutException {
		function.waitForElementIsVisibleFluent(Locator_pagination_rowcount_dropdown, 200, 20);
		function.clickbyactionclass(Locator_pagination_rowcount_dropdown);
		function.clickbyactionclass(Locator_select_50rows_in_table);
		function.waitForElementIsVisibleFluent(Locator_columnResourceID, 200, 3);
	}

	public void verify_dismissed_Idleresource_CountCost_added_Archived_page(String SQL,int number) throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		switch_to_Archive();
		String archive_ResourceCount=validatetotalcountof_idle_resource();
		int archive_ResourceCount_before = Integer.parseInt(archive_ResourceCount);
		String archive_diskstotalCount=validatetotalDisks();
		int archive_disksCount_before = Integer.parseInt(archive_diskstotalCount);
		String opportunity_missed_before=validatetotalcostofOPPORTUNITY_MISSED();
		double archive_opportunity_missed_before = Double.parseDouble(opportunity_missed_before);
		switch_to_Active();
		function.clickbyactionclass(Locator_Resource_type_dropdown);
		function.clickaction(Locator_select_Disk);
		function.waitForElementIsVisibleFluent(Locator_columnResourceID,200, 3);
		function.mouse_moveToElement(Locator_columnResourceID);
		String resourceid=function.table_archive_idle_resource_capture_resourceID_value_afterdismissresource();
		addinfolog("Open Status Resource ID:"+resourceid);
		switch_to_Archive();
		String current_archive_ResourceCount=validatetotalcountof_idle_resource();
		int archive_ResourceCount_after = Integer.parseInt(current_archive_ResourceCount);
		String current_archive_diskstotalCount=validatetotalDisks();
		int archive_disksCount_after = Integer.parseInt(current_archive_diskstotalCount);
		String opportunity_missed_after=validatetotalcostofOPPORTUNITY_MISSED();
		double archive_opportunity_missed_after = Double.parseDouble(opportunity_missed_after);
		
		boolean check=function.verifyresourceidpresentintable(resourceid);

		if(check==true) {
			String frontendstatus="archived";
			String SQLQuery=SQL+resourceid+"'";
			String DB_Status=function.capturedatabsesinglecolumncountByIndex(SQLQuery, number);
			addinfolog("Resource ID Status is=> Frontend Status:"+frontendstatus+" & Database Status:"+DB_Status);
			if(frontendstatus.equals(DB_Status)) {
				Assert.assertEquals(frontendstatus, DB_Status);
			}else {
				Assert.fail("Resource Status Not Match");
			}
		}
		
		// Check for Idle Resource count added with +1 
		if(archive_ResourceCount_after==(archive_ResourceCount_before+1)) {
			Assert.assertNotEquals(archive_ResourceCount_after, archive_ResourceCount_before, "Idle Resource count should added with +1 after dismiss record");
		}else {
			function.captureScreen("Idle_Resource_Count_Not_Changed");
			Assert.fail("Idle Resource count should added with +1 after dismiss record");
		}
		// Check for disks count added with +1 
		if(archive_disksCount_after==(archive_disksCount_before+1)) {
			Assert.assertNotEquals(archive_disksCount_after, archive_disksCount_before, "Disk count should added with +1 after dismiss record");
		}else {
			function.captureScreen("Disks_Count_Not_Changed");
			Assert.fail("Disk count should added with +1 after dismiss record");
		}
		// Check for Opportunity Missed cost
		if(archive_opportunity_missed_after>archive_opportunity_missed_before) {
			Assert.assertNotEquals(archive_opportunity_missed_after, archive_opportunity_missed_before, "Dismiss Resource Cost Added In OPPORTUNITY MISSED");
		}else {
			function.captureScreen("OPPORTUNITY_MISSED_Cost_Not_Changed");
			Assert.fail("Dismiss Resource Cost Added In OPPORTUNITY MISSED");
		}

	}

	public void verify_unarchivedrecord_added_active_page(String SQL,int number) throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		try {
		String active_avilablesaving_cost=validatetotalcostofsavingavilable();
		double active_avilablesaving_cost_before = Double.parseDouble(active_avilablesaving_cost);
		switch_to_Archive();
		String opportunity_missed_before=validatetotalcostofOPPORTUNITY_MISSED();
		double archive_opportunity_missed_before = Double.parseDouble(opportunity_missed_before);
		String resourceID_movedto_active=function.table_archive_idle_resource_capture_resourceID_value();
		String opportunity_missed_after=validatetotalcostofOPPORTUNITY_MISSED();
		double archive_opportunity_missed_after = Double.parseDouble(opportunity_missed_after);
		switch_to_Active();
		String active_avilablesaving_cost_after=validatetotalcostofsavingavilable();
		double Current_archive_opportunity_missed_after = Double.parseDouble(active_avilablesaving_cost_after);
		totalrowsintable50();
		boolean check=function.verifyresourceidpresentintable(resourceID_movedto_active);
		String status=function.getresourcestatus(resourceID_movedto_active,Locator_ResourceID_TotalRows,Locator_Status_Column_Rows);
		String frontendstatus=function.matchtoDBStatus(status);
		String SQLQuery=SQL+resourceID_movedto_active+"'";
		String DB_Status=function.capturedatabsesinglecolumncountByIndex(SQLQuery, number);
		addinfolog("Resource ID Status is=> Frontend Status:"+frontendstatus+" & Database Status:"+DB_Status);
		//String frontendstatus1=function.changenullstatus(frontendstatus);
		//String DB_Status1=function.changenullstatus( DB_Status);
		//addinfolog("Resource ID Status is=> Frontend Status:"+frontendstatus1+" & Database Status:"+DB_Status1);
		if(frontendstatus.equals(DB_Status)) {
			Assert.assertEquals(frontendstatus, DB_Status);
		}else {
			Assert.fail("Resource Status Not Match");
		}

		if(archive_opportunity_missed_before>archive_opportunity_missed_after) {
			Assert.assertNotEquals(archive_opportunity_missed_before, archive_opportunity_missed_after, "Opportunity Missed cost is Decreased");
		}else {
			function.captureScreen("Opportunity_Missed_Cost_Not_Changed");
			Assert.assertNotEquals(archive_opportunity_missed_before, archive_opportunity_missed_after, "Opportunity Missed cost is Decreased");
		}
		if(Current_archive_opportunity_missed_after>active_avilablesaving_cost_before) {
			Assert.assertNotEquals(Current_archive_opportunity_missed_after, active_avilablesaving_cost_before, "Available Saving cost is Increased");
		}else {
			function.captureScreen("Available_Saving_Cost_Not_Changed");
			Assert.assertNotEquals(Current_archive_opportunity_missed_after, active_avilablesaving_cost_before, "Available Saving cost is Increased");
		}
		if(check=true) {
			Assert.assertTrue(check, "Moved Resource ID found in Active page");
		}else {
			function.captureScreen("Resource_ID_not_found_in_Active_page");
			Assert.assertTrue(check, "Moved Resource ID found in Active page");
		}
		}catch(Exception e) {
			function.captureScreen("Verify_the_on_unarchiving_any_record_of_Idle_resource_from_Archive_page_it_is_being_moved_to_Active_page_of_Idle_Resource");
		}

	}

	public void capture_resource_type_count_cost(String sql,String columnname,int index1, int index2) throws ClassNotFoundException, TimeoutException {
		String finalcost = null;
		String dbResourceType = null;
		String dbCount = null;
		int index = 0;
		List<String> resourceTypes = new ArrayList<>();
		List<String> savings = new ArrayList<>();
		List<String> counts = new ArrayList<>();

		String disks_count=validatetotalDisks();
		String disks_cost=validatetotalcostofDisks();
		resourceTypes.add("Disk");
		counts.add(disks_count);
		savings.add(disks_cost);
		String elasticip_count=validatetotalElastic_IPs();
		String elasticip_cost=validatetotalcostofElastic_IPs();
		resourceTypes.add("Public IP Address");
		counts.add(elasticip_count);
		savings.add(elasticip_cost);
		String vms_count=validatetotalVMs();
		String vms_cost=validatetotalcostofVMs();
		resourceTypes.add("Virtual Machine");
		counts.add(vms_count);
		savings.add(vms_cost);
		String Load_Balancer_count=validatetotalLoad_Balancer();
		String Load_Balancer_cost=validatetotalcostofLoad_Balancer();
		resourceTypes.add("Load Balancer");
		counts.add(Load_Balancer_count);
		savings.add(Load_Balancer_cost);

		try {
			ReuseMethods db=new ReuseMethods();
			ResultSet data=db.getdata(sql);
			System.out.println(data);

			while (data.next()) {
				dbResourceType = data.getString("resource_type");
				dbCount=data.getString(index2);
				String dbSaving=data.getString(index1);
				if(dbSaving==null) {
					finalcost="0.00";
				}else {
					dbSaving=dbSaving.replaceAll("[^0-9 .]", "");
					finalcost=function.roundingDBStringValue(dbSaving);
				}
				index = resourceTypes.indexOf(dbResourceType);
				if (index != -1) {
					Assert.assertEquals(savings.get(index), finalcost, "Saving does not match for resource type: " + dbResourceType);
					Assert.assertEquals(counts.get(index), dbCount, "Count does not match for resource type: " + dbResourceType);
					addinfolog("cost and count of "+dbResourceType+" is match with database");
				} else {
					Assert.fail("Resource type '" + dbResourceType + "' found in database but not on webpage.");
					addinfolog("cost and count of"+dbResourceType+"is mot match with database");
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public String validatetotalcostofsavingavilable() throws TimeoutException {
		function.mouse_moveToElement(Locator_totalcostarea_SAVINGS_AVAILABLE);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		return modifiecost;
	}

	public String validatetotalcostofSaved_till_date() throws TimeoutException {
		function.mouse_moveToElement(Locator_totalcostarea_Saved_till_date);
		function.waitFor(1000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		return modifiecost;
	}



	public void compare7dayscountcostofelasticip() throws TimeoutException {
		String totalcount;
		String totalcost;
		function.clickaction(Locator_textbox_Subscription_Name);
		function.waitFor(1000);
		function.mouse_moveToElement(Locator_optionarea);
		function.waitForElementToBeClickable(Locator_select_selectall);
		function.clickaction(Locator_select_selectall);
		function.waitForElementToBeClickable(Locator_textbox_Region);
		function.clickaction(Locator_textbox_Region);
		function.waitFor(1000);
		function.mouse_moveToElement(Locator_optionarea);
		function.waitForElementToBeClickable(Locator_select_selectall);
		function.clickaction(Locator_select_selectall);
		function.clickbyactionclass(Locator_Resource_type_dropdown);
		function.clickbyactionclass(Locator_select_ElasticIP);
		function.clickbyactionclass(Locator_idle_days_dropdown);
		function.clickbyactionclass(Locator_select_7Days);
		function.waitFor(2000);
		boolean flag=function.verifyDisplayed(Locator_text_DataAbsent);

		if(flag==true) {
			totalcount="0";
			totalcost="0.00";
		}else {
			function.waitForElementIsVisibleFluent(Locator_columnResourceID, 200, 3);
			function.mouse_moveToElement(Locator_columnResourceID);
			String rowcost=function.sumofcostforallresource();
			function.waitFor(2000);
			String totalrow=function.rowsplictformstring(rowcost);
			String sumtotalcost=function.costsplictformstring(rowcost);
			totalcount=totalrow;
			totalcost=sumtotalcost;
		}
		function.mouse_moveToElement(Locator_cost_elasticIps);
		String cost=function.capturetext(Locator_cost_elasticIps);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		function.mouse_moveToElement(Locator_count_7days_elasticIps);
		String count=function.capturetext(Locator_count_7days_elasticIps);
		Assert.assertEquals(count, totalcount,"Total Count is match form table data to graph data");
		Assert.assertEquals(modifiecost, totalcost,"Total Cost is match form table data to graph data");
	}

	public void compare7dayselasticipcount(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		function.mouse_moveToElement(Locator_count_7days_elasticIps);
		String count=function.capturetext(Locator_count_7days_elasticIps);
		String countfromDB= function.capturedatabsesinglecolumncountByIndex(sql,columnnumber);
		addinfolog("7 Days Elastic IP Count=> Frontend Count is:"+count+" & Database Count is:"+countfromDB);
		Assert.assertEquals(count,countfromDB,"Total Count is match form graph data with Database");
	}

	public void compare7dayselasticipcost(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		function.mouse_moveToElement(Locator_count_7days_elasticIps);
		function.mouse_moveToElement(Locator_cost_elasticIps);
		String cost=function.capturetext(Locator_cost_elasticIps);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		String costfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
		addinfolog("7 Days Elastic IP Cost=> Frontend Cost is:"+cost+" & Database Cost is:"+costfromDB);
		Assert.assertEquals(modifiecost,costfromDB,"Total Count is match form graph data with Database");

	}

	public void comparemorethan31dayscountcostofVMs() throws TimeoutException, IOException {
		try {
		String totalcount = null;
		String totalcost = null;
		function.clickaction(Locator_textbox_Subscription_Name);
		function.waitFor(1000);
		function.mouse_moveToElement(Locator_optionarea);
		function.waitForElementToBeClickable(Locator_select_selectall);
		function.clickaction(Locator_select_selectall);
		function.waitForElementToBeClickable(Locator_textbox_Region);
		function.clickaction(Locator_textbox_Region);
		function.waitFor(1000);
		function.mouse_moveToElement(Locator_optionarea);
		function.waitForElementToBeClickable(Locator_select_selectall);
		function.clickaction(Locator_select_selectall);
		function.clickbyactionclass(Locator_Resource_type_dropdown);
		function.clickbyactionclass(Locator_select_VirtualMachine);
		function.clickbyactionclass(Locator_idle_days_dropdown);
		function.clickbyactionclass(Locator_select_Morethan31Days);
		function.waitFor(2000);
		boolean flag=function.verifyDisplayed(Locator_text_DataAbsent);
		function.waitFor(2000);
		if(flag==true) {
			totalcount="0";
			totalcost="0.00";
		}
		if (flag==false) {
			function.waitForElementIsVisibleFluent(Locator_columnResourceID, 200, 3);
			function.mouse_moveToElement(Locator_columnResourceID);
			String rowcost=function.sumofcostforallresource();
			function.waitFor(2000);
			System.out.println(rowcost);
			String totalrow=function.rowsplictformstring(rowcost);
			String sumtotalcost=function.costsplictformstring(rowcost);
			totalcount=totalrow;
			totalcost=sumtotalcost;
		}
		function.mouse_moveToElement(Locator_cost_VMS);
		String cost=function.capturetext(Locator_cost_VMS);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		function.mouse_moveToElement(Locator_count_morethan31days_Vms);
		String count=function.capturetext(Locator_count_morethan31days_Vms);
		Assert.assertEquals(count, totalcount,"Total Count is match form table data to graph data");
		Assert.assertEquals(modifiecost, totalcost,"Total Cost is match form table data to graph data");
		}catch(Exception e) {
			function.captureScreen("At_Idle_Resource_page_verify_the_total_count_and_Savings_for_resource_type_Virtual_Machine_Idle_for_more_than_31_days");
		}
	}

	public void comparemorethan31daysVMscount(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		function.waitFor(5000);
		function.mouse_moveToElement(Locator_count_morethan31days_Vms);
		String count=function.capturetext(Locator_count_morethan31days_Vms);
		String countfromDB= function.capturedatabsesinglecolumncountByIndex(sql,columnnumber);
		addinfolog("More Than 31 Dyas VMs Count=> Frontend Count is:"+count+" & Database Count is:"+countfromDB);
		Assert.assertEquals(count,countfromDB,"Total Count is match form graph data with Database");
	}

	public void comparemorethan31daysVMscost(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		function.waitFor(5000);
		function.mouse_moveToElement(Locator_count_morethan31days_Vms);
		String count=function.capturetext(Locator_count_morethan31days_Vms);
		boolean countcheck=count.equals("0");
		function.waitFor(2000);
		if(countcheck==false) {
			function.mouse_moveToElement(Locator_cost_VMS);
			String cost=function.capturetext(Locator_cost_VMS);
			String modifiecost= cost.replaceAll("[^0-9.]", "");;
			String costfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
			addinfolog("More Than 31Days VMs Cost=> Frontend Cost is:"+cost+" & Database Cost is:"+costfromDB);
			Assert.assertEquals(modifiecost,costfromDB,"Total Count is match form graph data with Database");
		}
		if(countcheck==true){
			addinfolog("Total Resource Count is Zero, that means it cost will be zero.");
		}

	}

	public void accept_resource_check_saving_cost(String SQL,int number) throws IOException {
		try {
		String Available_Saving_Before=validatetotalcostofsavingavilable();
		double Cost_Available_Saving_Before = Double.parseDouble(Available_Saving_Before);
		function.waitForElementIsVisibleFluent(Locator_columnResourceID,200, 3);
		function.mouse_moveToElement(Locator_columnResourceID);
		String resourceid=function.table_archive_idle_resource_capture_resourceID_value_afterAcceptresource();
		//boolean check=function.verifyresourceidpresentintable(resourceid);
		String status=function.getresourcestatus(resourceid,Locator_ResourceID_TotalRows,Locator_Status_Column_Rows);
		String frontendstatus=function.matchtoDBStatus(status);
		String SQLQuery=SQL+resourceid+"'";
		String DB_Status=function.capturedatabsesinglecolumncountByIndex(SQLQuery, number);
		addinfolog("Resource ID Status is=> Frontend Status:"+frontendstatus+" & Database Status:"+DB_Status);
		String Available_Saving_After=validatetotalcostofsavingavilable();
		double Cost_Available_Saving_After = Double.parseDouble(Available_Saving_After);
		if(frontendstatus.equals(DB_Status)) {
			Assert.assertEquals(frontendstatus, DB_Status);
		}else {
			Assert.fail("Resource Status Not Match");
		}
		/*
		if(check==false) {
			function.captureScreen("respective_ResourceID_is_not_removed_form_table");
			Assert.assertFalse(check,"After accepting resource, respective 'Resource ID' get removed form table");
		}else {
			Assert.assertFalse(check,"After accepting resource, respective 'Resource ID' get removed form table");
		}
		
		if(Cost_Available_Saving_Before>Cost_Available_Saving_After) {
		
			Assert.assertNotEquals(Cost_Available_Saving_After, Cost_Available_Saving_Before, "Available Saving Cost is Decreased");
		}else {
		function.captureScreen("Verify_on_accepting_the_Idle_resource_savings_should_be_decreased");
			Assert.assertNotEquals(Cost_Available_Saving_After, Cost_Available_Saving_Before, "Available Saving Cost is Decreased");
		}
		*/
		}catch(Exception e) {
			function.captureScreen("Verify_on_accepting_the_Idle_resource_savings_should_be_decreased");
		}
		
	}


	public void dismiss_resource_check_saving_cost(String SQL,int number) throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		String Available_Saving_Before=validatetotalcostofsavingavilable();
		double Cost_Available_Saving_Before = Double.parseDouble(Available_Saving_Before);
		function.waitForElementIsVisibleFluent(Locator_columnResourceID,200, 3);
		function.mouse_moveToElement(Locator_columnResourceID);
		String resourceid=function.table_archive_idle_resource_capture_resourceID_value_afterdismissresource();
		switch_to_Archive();
		function.waitForElementIsVisibleFluent(Locator_columnResourceID,200, 3);
		boolean checkstatus=function.verifyresourceidpresentintable(resourceid);
		if(checkstatus==true) {
			String frontendstatus="archived";
			String SQLQuery=SQL+resourceid+"'";
			String DB_Status=function.capturedatabsesinglecolumncountByIndex(SQLQuery, number);
			addinfolog("Resource ID Status is=> Frontend Status:"+frontendstatus+" & Database Status:"+DB_Status);
			if(frontendstatus.equals(DB_Status)) {
				Assert.assertEquals(frontendstatus, DB_Status);
			}else {
				Assert.fail("Resource Status Not Match");
			}
		}
		switch_to_Active();
		String Available_Saving_After=validatetotalcostofsavingavilable();
		double Cost_Available_Saving_After = Double.parseDouble(Available_Saving_After);
		totalrowsintable50();
		boolean check=function.verifyresourceidpresentintable(resourceid);

		// Check for: Available Saving Cost is Decreased
		
		if(Cost_Available_Saving_Before>Cost_Available_Saving_After) {
			Assert.assertNotEquals(Cost_Available_Saving_After, Cost_Available_Saving_Before, "Available Saving Cost is Decreased");
		}else {
			function.captureScreen("Available_Saving_Cost_Not_Changed");
			Assert.assertNotEquals(Cost_Available_Saving_After, Cost_Available_Saving_Before, "Available Saving Cost is Decreased");
		}
		// Check for: After dismissing resource, respective 'Resource ID' get removed form table

		if(check==false) {
			function.captureScreen("respective_ResourceID_is_not_removed_form_table");
			Assert.assertFalse(check,"After accepting resource, respective 'Resource ID' get removed form table");

		}else {
			Assert.assertFalse(check,"After accepting resource, respective 'Resource ID' get removed form table");
		}

	}

	public void Verify_fields_under_idle_resource() throws IOException {
		boolean a=function.verifyDisplayed(Locator_text_idle_resource_count);     // Verify "Text: 'Idle Resource Count' is display    
		if (a==true) {
			Assert.assertTrue(a,"Text: 'Idle Resource Count' is display at Idle Resources module");
		}else
		{
			Assert.assertTrue(a,"Text: 'Idle Resource Count' is display at Idle Resources module");
			function.captureScreen("Idle_Resource_Count_is_missing_at_IdleResources_module");
		}

		boolean b=function.verifyDisplayed(Locator_text_Elastic_IPs);          // verify "Text: 'Elastic IPs' is display
		if (b==true) {
			Assert.assertTrue(b,"Text: 'Elastic IPs' is display at Idle Resources module");
		}else
		{
			Assert.assertTrue(b,"Text: 'Elastic IPs' is display at Idle Resources module");
			function.captureScreen("Elastic IPs_is_missing_at_IdleResources_module");
		}

		boolean c=function.verifyDisplayed(Locator_text_VMs);        // verify "Text: 'VMs' is display
		if (c==true) {
			Assert.assertTrue(c,"Text: 'VMs' is display at Idle Resources module");
		}else
		{
			Assert.assertTrue(c,"Text: 'VMs' is display at Idle Resources module");
			function.captureScreen("VMs_is_missing_at_IdleResources_module");
		}

		boolean d=function.verifyDisplayed(Locator_text_Disks);                  // verify "Text: 'Disks' is display
		if (d==true) {
			Assert.assertTrue(d,"Text: 'Disks' is display at Idle Resources module");
		}else
		{
			Assert.assertTrue(d,"Text: 'Disks' is display at Idle Resources module");
			function.captureScreen("Disks_is_missing_at_IdleResources_module");
		}

		boolean e=function.verifyDisplayed(Locator_text_Load_Balancer);         // verify "Text: 'Load Balancer' is display
		if (e==true) {
			Assert.assertTrue(e,"Text: 'Load Balancer' is display at Idle Resources module");
		}else
		{
			Assert.assertTrue(e,"Text: 'Load Balancer' is display at Idle Resources module");
			function.captureScreen("Load_Balancer_is_missing_at_IdleResources_module");
		}
	}

	public void Verify_fields_under_idle_resource_saving() throws IOException {
		boolean a=function.verifyDisplayed(Locator_text_SAVINGS_AVAILABLE);     // Verify "Text: 'SAVINGS AVAILABLE' is display    
		if (a==true) {
			Assert.assertTrue(a,"Text: 'SAVINGS AVAILABLE' is display at Idle Resources module");
		}else
		{
			Assert.assertTrue(a,"Text: 'SAVINGS AVAILABLE' is display at Idle Resources module");
			function.captureScreen("SAVINGS_AVAILABLE_is_missing_at_IdleResources_module");
		}

		boolean b=function.verifyDisplayed(Locator_text_Saved_till_date);          // verify "Text: 'Saved till date' is display
		if (b==true) {
			Assert.assertTrue(b,"Text: 'Saved till date' is display at Idle Resources module");
		}else
		{
			Assert.assertTrue(b,"Text: 'Saved till date' is display at Idle Resources module");
			function.captureScreen("Saved_till_date_is_missing_at_IdleResources_module");
		}
	}

	public void compare_idle_resource_totalcost(String sql,String columnname) throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		String totalidelresourcecost=validatetotalidle_resource_cost();
		String totalidelresourcecostfromDB=function.capturedatabsesinglecolumnvalue(sql, columnname);
		addinfolog("Idle Resource Cost=> Frontend Cost is:"+totalidelresourcecost+" & Database Cost is:"+totalidelresourcecostfromDB);
		if(totalidelresourcecost.equals(totalidelresourcecostfromDB)) {
			Assert.assertEquals(totalidelresourcecost, totalidelresourcecostfromDB,"Total Idel Resource Cost is match"); 
		}else {
			function.captureScreen("Total Idel Resource Cost is not match");
			Assert.assertEquals(totalidelresourcecost, totalidelresourcecostfromDB,"Total Idel Resource Cost is match");
		}
	}

	public void compare_idle_resource_totalcount(String sql,String columnname) throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		String totalidelresourcecount=validatetotalcountof_idle_resource();
		String totalidelresourcecountfromDB=function.capturedatabsesinglecolumnvaluecount(sql, columnname);
		addinfolog("Idle Resource Count=> Frontend Count is:"+totalidelresourcecount+" & Database Count is:"+totalidelresourcecountfromDB);
		if(totalidelresourcecount.equals(totalidelresourcecountfromDB)) {
			Assert.assertEquals(totalidelresourcecount, totalidelresourcecountfromDB,"Total Idel Resource Count is match"); 
		}else {
			//function.captureScreenreport("At_Idle_resources_page_verify_the_values_shown_at_first_section_of_the_Active_Idle_Resources_page");
			Assert.assertEquals(totalidelresourcecount, totalidelresourcecountfromDB,"Total Idel Resource Count is match");
		}
	}

	public void compare_cost_available_savings(String sql,String columnname) throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		function.mouse_moveToElement(Locator_totalcostarea_SAVINGS_AVAILABLE);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String availablesavingscost=cost.replaceAll("[^0-9.]", "");
		String availablesavingscostfromDB=function.capturedatabsesinglecolumnvalue(sql, columnname);
		addinfolog("Available Savings Cost=> Frontend Cost is:"+availablesavingscost+" & Database Cost is:"+availablesavingscostfromDB);
		if(availablesavingscost.equals(availablesavingscostfromDB)) {
			Assert.assertEquals(availablesavingscost, availablesavingscostfromDB,"Available Savings Cost is match"); 
		}else {
			function.captureScreen("Available Savings is not match");
			Assert.assertEquals(availablesavingscost, availablesavingscostfromDB,"Available Savings Cost is match");
		}
	}

	public void compare_cost_save_till_date(String sql,String columnname) throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		function.mouse_moveToElement(Locator_totalcostarea_Saved_till_date);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String totalidelresourcecost=cost.replaceAll("[^0-9.]", "");
		String cost_save_till_datecostfromDB=function.capturedatabsesinglecolumnvalue(sql, columnname);
		addinfolog("Saving Till Date Cost=> Frontend Cost is:"+totalidelresourcecost+" & Database Cost is:"+cost_save_till_datecostfromDB);
		if(totalidelresourcecost.equals(cost_save_till_datecostfromDB)) {
			Assert.assertEquals(totalidelresourcecost, cost_save_till_datecostfromDB,"Savings Till Date Cost is match"); 
		}else {
			function.captureScreen("Savings Till Date Cost is not match");
			Assert.assertEquals(totalidelresourcecost, cost_save_till_datecostfromDB,"Savings Till Date Cost is match");
		}
	}

	public void discardresource(String SQL,int number) throws TimeoutException, IOException, ClassNotFoundException, SQLException{
		String count_totalidleresource_before=validatetotalcountof_idle_resource();
		int count_idleresource_before=Integer.parseInt(count_totalidleresource_before);
		String cost_idleresource_before=validatetotalidle_resource_cost();
		double Cost_IdleResource_Before = Double.parseDouble(cost_idleresource_before);
		String cost_avialbelsavings_before=validatetotalcostofsavingavilable();
		double Cost_Available_Saving_Before = Double.parseDouble(cost_avialbelsavings_before);
		function.waitForElementIsVisibleFluent(Locator_columnResourceID,200, 3);
		function.mouse_moveToElement(Locator_pagination_rowcount_dropdown);
		String resourceid=function.table_idleresource_capture_resourceID_value_afterDiscardresource();
		function.clickbyactionclass(Locator_tab_discard);
		function.waitForElementIsVisibleFluent(Locator_columnResourceID,200, 3);
		boolean check=function.verifyresourceidpresentintable(resourceid);
		String count_totalidleresource_after=validatetotalcountof_idle_resource();
		int count_idleresource_after=Integer.parseInt(count_totalidleresource_after);
		String cost_idleresource_after=validatetotalidle_resource_cost();
		double Cost_IdleResource_after = Double.parseDouble(cost_idleresource_after);
		String cost_avialbelsavings_after=validatetotalcostofsavingavilable();
		double Cost_Available_Saving_after = Double.parseDouble(cost_avialbelsavings_after);
		String status=function.getresourcestatus(resourceid,Locator_ResourceID_TotalRows,Locator_Status_Column_Rows);
		String frontendstatus=function.matchtoDBStatus(status);
		String SQLQuery=SQL+resourceid+"'";
		String DB_Status=function.capturedatabsesinglecolumncountByIndex(SQLQuery, number);
		addinfolog("Resource ID Status is=> Frontend Status:"+frontendstatus+" & Database Status:"+DB_Status);
		if(frontendstatus.equals(DB_Status)) {
			Assert.assertEquals(frontendstatus, DB_Status);
		}else {
			Assert.fail("Resource Status Not Match");
		}
/*
		if(Cost_Available_Saving_Before>Cost_Available_Saving_after) {
			Assert.assertNotEquals(Cost_Available_Saving_after, Cost_Available_Saving_Before, "Available Saving Cost is Decreased");
		}else {
			function.captureScreen("Available_Saving_Cost_Not_Changed");
			Assert.fail("After discarding resource, Available Saving Cost should Decreased");
		}

		if(Cost_IdleResource_Before>Cost_IdleResource_after) {
			Assert.assertNotEquals(Cost_IdleResource_after, Cost_IdleResource_Before, "Idle Resource Cost is Decreased");
		}else {
			function.captureScreen("Idle_Resource_Cost_Not_Changed");
			Assert.fail("After discarding resource, Idle Resource Cost should Decreased");
		}

		if(count_idleresource_after==(count_idleresource_before-1)) {
			Assert.assertNotEquals(count_idleresource_after, count_idleresource_before, "Idle Resource Count is Decreased");
		}else {
			function.captureScreen("Idle_Resource_Count_Not_Changed");
			Assert.fail("After discarding resource, Idle Resource Count should Decreased");
		}

		if(check==true) {
			Assert.assertTrue(check,"After discarding resource, respective 'Resource ID' is added in discard table");

		}else {
			function.captureScreen("respective_ResourceID_is_not_removed_form_table");
			Assert.fail("After discarding resource, respective 'Resource ID' is not added in discard table");
		}
		*/
	}

	public void movetoopenresource(String SQL,int number) throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		String count_totalidleresource_before=validatetotalcountof_idle_resource();
		int count_idleresource_before=Integer.parseInt(count_totalidleresource_before);
		String cost_idleresource_before=validatetotalidle_resource_cost();
		double Cost_IdleResource_Before = Double.parseDouble(cost_idleresource_before);
		String cost_avialbelsavings_before=validatetotalcostofsavingavilable();
		double Cost_Available_Saving_Before = Double.parseDouble(cost_avialbelsavings_before);
		function.clickbyactionclass(Locator_tab_discard);
		function.waitForElementIsVisibleFluent(Locator_columnResourceID,200, 3);
		function.mouse_moveToElement(Locator_pagination_rowcount_dropdown);
		String resourceid=function.table_idleresource_capture_resourceID_value_aftermovedtoopenresource();
		function.waitFor(5000);
		String count_totalidleresource_after=validatetotalcountof_idle_resource();
		int count_idleresource_after=Integer.parseInt(count_totalidleresource_after);
		String cost_idleresource_after=validatetotalidle_resource_cost();
		double Cost_IdleResource_after = Double.parseDouble(cost_idleresource_after);
		String cost_avialbelsavings_after=validatetotalcostofsavingavilable();
		double Cost_Available_Saving_after = Double.parseDouble(cost_avialbelsavings_after);
		function.clickbyactionclass(Locator_tab_open);
		function.mouse_moveToElement(Locator_pagination_rowcount_dropdown);
		boolean check=function.verifyresourceidpresentintable(resourceid);
		String status=function.getresourcestatus(resourceid,Locator_ResourceID_TotalRows,Locator_Status_Column_Rows);
		String frontendstatus=function.matchtoDBStatus(status);
		String SQLQuery=SQL+resourceid+"'";
		String DB_Status=function.capturedatabsesinglecolumncountByIndex(SQLQuery, number);
		addinfolog("Resource ID Status is=> Frontend Status:"+frontendstatus+" & Database Status:"+DB_Status);
		if(frontendstatus.equals(DB_Status)) {
			Assert.assertEquals(frontendstatus, DB_Status);
		}else {
			Assert.fail("Resource Status Not Match");
		}
/*
		if(Cost_Available_Saving_Before<Cost_Available_Saving_after) {
			Assert.assertNotEquals(Cost_Available_Saving_after, Cost_Available_Saving_Before, "Available Saving Cost is Increased");
		}else {
			function.captureScreen("Available_Saving_Cost_Not_Changed");
			Assert.fail("After moving discard resource to open, Available Saving Cost should Increased");
		}

		if(Cost_IdleResource_Before<Cost_IdleResource_after) {
			Assert.assertNotEquals(Cost_IdleResource_after, Cost_IdleResource_Before, "Idle Resource Cost is Increased");
		}else {
			function.captureScreen("Idle_Resource_Cost_Not_Changed");
			Assert.fail("After moving discard resource to open, Idle Resource Cost should Increased");
		}

		if(count_idleresource_after==(count_idleresource_before+1)) {
			Assert.assertNotEquals(count_idleresource_after, count_idleresource_before, "Idle Resource Count is Increased");
		}else {
			function.captureScreen("Idle_Resource_Count_Not_Changed");
			Assert.fail("After moving discard resource to open, Idle Resource Count should Increased");
		}

		if(check==false) {
			Assert.assertFalse(check,"After moving discard resource to open, respective 'Resource ID' got removed form discard table");

		}else {
			function.captureScreen("respective_ResourceID_is_not_removed_form_table");
			Assert.fail("After discarding resource, respective 'Resource ID' is not added in discard table");
		}
		*/
	}

	public void archivediscardresource(String SQL, int number) throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		switch_to_Archive();
		String count_totalidleresource_before=validatetotalcountof_idle_resource();
		int count_idleresource_before=Integer.parseInt(count_totalidleresource_before);
		String cost_idleresource_before=validatetotalidle_resource_cost();
		double Cost_IdleResource_Before = Double.parseDouble(cost_idleresource_before);
		String cost_OPPORTUNITY_MISSED_before=validatetotalcostofOPPORTUNITY_MISSED();
		double Cost_OPPORTUNITY_MISSED_Before = Double.parseDouble(cost_OPPORTUNITY_MISSED_before);
		switch_to_Active();
		function.clickbyactionclass(Locator_tab_discard);
		function.waitForElementIsVisibleFluent(Locator_columnResourceID,200, 3);
		function.mouse_moveToElement(Locator_pagination_rowcount_dropdown);
		String resourceid=function.table_idleresource_capture_resourceID_value_archivediscardresource();
		function.mouse_moveToElement(Locator_button_switch);
		switch_to_Archive();
		String count_totalidleresource_after=validatetotalcountof_idle_resource();
		int count_idleresource_after=Integer.parseInt(count_totalidleresource_after);
		String cost_idleresource_after=validatetotalidle_resource_cost();
		double Cost_IdleResource_after = Double.parseDouble(cost_idleresource_after);
		String cost_OPPORTUNITY_MISSED_after=validatetotalcostofsavingavilable();
		double Cost_OPPORTUNITY_MISSED_after = Double.parseDouble(cost_OPPORTUNITY_MISSED_after);
		function.mouse_moveToElement(Locator_pagination_rowcount_dropdown);
		boolean check=function.verifyresourceidpresentintable(resourceid);

		if(check==true) {
			String frontendstatus="archived";
			String SQLQuery=SQL+resourceid+"'";
			String DB_Status=function.capturedatabsesinglecolumncountByIndex(SQLQuery, number);
			addinfolog("Resource ID Status is=> Frontend Status:"+frontendstatus+" & Database Status:"+DB_Status);
			if(frontendstatus.equals(DB_Status)) {
				Assert.assertEquals(frontendstatus, DB_Status);
			}else {
				Assert.fail("Resource Status Not Match");
			}
		}
/*
		if(Cost_OPPORTUNITY_MISSED_Before<Cost_OPPORTUNITY_MISSED_after) {
			Assert.assertNotEquals(Cost_OPPORTUNITY_MISSED_after, Cost_OPPORTUNITY_MISSED_Before, "OPPORTUNITY MISSED Cost is Increased");
		}else {
			function.captureScreen("OPPORTUNITY_MISSED_Cost_Not_Changed");
			Assert.fail("After archiving discard resource, OPPORTUNITY MISSED Cost should Increased");
		}

		if(Cost_IdleResource_Before<Cost_IdleResource_after) {
			Assert.assertNotEquals(Cost_IdleResource_after, Cost_IdleResource_Before, "Idle Resource Cost is Increased");
		}else {
			function.captureScreen("Idle_Resource_Cost_Not_Changed");
			Assert.fail("After archiving discard resource, Idle Resource Cost should Increased");
		}

		if(count_idleresource_after==(count_idleresource_before+1)) {
			Assert.assertNotEquals(count_idleresource_after, count_idleresource_before, "Idle Resource Count is Increased");
		}else {
			function.captureScreen("Idle_Resource_Count_Not_Changed");
			Assert.fail("After archiving discard resource, Idle Resource Count should Increased");
		}

		if(check==true) {
			Assert.assertTrue(check,"After archiving discard resource, respective 'Resource ID' is added in archive table");

		}else {
			function.captureScreen("respective_ResourceID_is_not_added_in_archive_table");
			Assert.fail("After archiving discard resource, respective 'Resource ID' is not added in archive table");
		}
*/
	}

}
