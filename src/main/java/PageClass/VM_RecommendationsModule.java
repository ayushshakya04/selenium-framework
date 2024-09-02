package PageClass;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.testng.Assert;

import Repository.locator_vm_recommendations;
import Utilities.ReuseMethods;

public class VM_RecommendationsModule extends locator_vm_recommendations{

	ReuseMethods function;
	public VM_RecommendationsModule(ReuseMethods function) {
		this.function=function;
	}
	
	 public void addinfolog(String Message) {
		 function.addinfolog(Message);
	 }

	public void navigate_to_vm_recmmendations_module() throws TimeoutException {
		function.mouse_moveToElement(Locator_MenuBar);
		function.clickaction(Locator_selectVMRecommendationsmodule);
		function.mouse_moveToElement(Locator_movetoheadersection);
		function.waitForElementIsVisibleFluent(Locator_text_Total_VM_RECOMMENDATIONS, 400, 5);
	}

	public void switchtoupsidetab() {
		function.clickaction(Locator_tab_Upsize);
	}

	public void movearchivepage() {
		function.clickbyactionclass(Locator_button_switch);
		function.clickbyactionclass(Locator_switchto_Archive);
		function.waitFor(4000);
		function.verifyDisplayed(Locator_text_Archived_VM_Resources);

	}
	public void moveactivepage() {
		function.clickbyactionclass(Locator_button_switch);
		function.clickbyactionclass(Locator_switchto_Active);
		function.waitFor(4000);
		function.verifyDisplayed(Locator_text_Total_VM_RECOMMENDATIONS);
	}

	public void totalrowsintable50() {
		function.clickbyactionclass(Locator_pagination_rowcount_dropdown);
		function.clickbyactionclass(Locator_select_50rows_in_table);
		function.waitForElementIsVisibleFluent(Locator_columnResourceID, 400, 3);
	}

	public boolean text_downsize() {
		return function.verifyDisplayed(Locator_text_DOWNSIZE);
	} 

	public  int counttotalVMs() throws TimeoutException {
		function.mouse_moveToElement(Locator_totalcountof_VMRecommendations);
		String count=function.capturetext(Locator_totalcountof_VMRecommendations);
		int intcount = Integer.parseInt(count);
		return intcount;
	}

	public  int counttotaldownsize() throws TimeoutException {
		function.mouse_moveToElement(Locator_totalcountof_Downsize);
		String count=function.capturetext(Locator_totalcountof_Downsize);
		int intcount = Integer.parseInt(count);
		return intcount;
	}

	public  int counttotalupsize() throws TimeoutException {
		function.mouse_moveToElement(Locator_totalcountof_Upsize);
		String count=function.capturetext(Locator_totalcountof_Upsize);
		int intcount = Integer.parseInt(count);
		return intcount;
	}

	public double capturetotalcostofDownsize_SAVINGScount() throws TimeoutException {
		function.mouse_moveToElement(Locator_costarea_DOWNSIZE_savings);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		double doublecost = Double.parseDouble(modifiecost);
		return doublecost ;
	}

	public double capturetotalcostofUpsize() throws TimeoutException {
		function.mouse_moveToElement(Locator_costarea_UPSIZE_savings);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		double doublecost = Double.parseDouble(modifiecost);
		return doublecost ;
	}

	public double capturetotalcostofCurrent_Costs() throws TimeoutException {
		function.mouse_moveToElement(Locator_costarea_Current_Costs);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		double doublecost = Double.parseDouble(modifiecost);
		return doublecost ;
	}

	public double capturetotalcostofProjected_Costs() throws TimeoutException {
		function.mouse_moveToElement(Locator_costarea_Projected_Costs);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		double doublecost = Double.parseDouble(modifiecost);
		return doublecost ;
	}

	public double capturetotalcostofOPPORTUNITY_MISSED() throws TimeoutException {
		function.mouse_moveToElement(Locator_costarea_OPPORTUNITY_MISSED);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		double doublecost = Double.parseDouble(modifiecost);
		return doublecost ;
	}

	public boolean text_PROJECTED_SAVINGS_isdisplay() {
		return function.verifyDisplayed(Locator_text_PROJECTED_SAVINGS);
	} 

	public double capturetotalcostofPROJECTED_SAVINGScount() throws TimeoutException {
		function.mouse_moveToElement(Locator_costarea_PROJECTED_SAVINGS);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		double doublecost = Double.parseDouble(modifiecost);
		return doublecost ;
	}


	public void acceptresourceunderdownsizetab() throws TimeoutException, IOException {
		int totalcount_VMs_before=counttotalVMs();
		int totalcount_downsize_before=counttotaldownsize();
		double totalcost_downsize_before=capturetotalcostofDownsize_SAVINGScount();
		double totalcost_projectedsaving_before=capturetotalcostofPROJECTED_SAVINGScount();
		totalrowsintable50();
		function.waitForElementIsVisibleFluent(Locator_columnResourceID,400, 3);
		function.mouse_moveToElement(Locator_columnResourceID);
		String resourceid=function.table_Downsize_VMS_capture_resourceID_value_afterAcceptresource();
		function.waitFor(3000);
		//boolean check=function.verifyresourceidpresentintablepatametercolumnindex(resourceid,3);
		function.mouse_moveToElement(Locator_text_Total_VM_RECOMMENDATIONS);
		int totalcount_VMs_after=counttotalVMs();
		int totalcount_downsize_after =counttotaldownsize();
		double totalcost_downsize_after=capturetotalcostofDownsize_SAVINGScount();
		double totalcost_projectedsaving_after=capturetotalcostofPROJECTED_SAVINGScount();	
		System.out.println(totalcount_VMs_after+"//"+totalcount_downsize_after+"//"+totalcost_downsize_after+"//"+totalcost_projectedsaving_after);
		/*
		if(check==false) {
			Assert.assertFalse(check,"After accepting resource, respective 'Resource ID' get removed form table");

		}else {
			function.captureScreen("respective_ResourceID_is_not_removed_form_table");
			Assert.assertFalse(check,"After accepting resource, respective 'Resource ID' get removed form table");

		}

		if(totalcount_VMs_after==(totalcount_VMs_before-1)) {
			Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM RECOMMENDATIONS count should reduce by -1 after accepting record");
		}else {
			function.captureScreen("Downsize_Count_Not_Changed");
			Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM RECOMMENDATIONS count should reduce by -1 after accepting record");
		}

		if(totalcount_downsize_after==(totalcount_downsize_before-1)) {
			Assert.assertNotEquals(totalcount_downsize_after, totalcount_downsize_before, "Downsize count should reduce by -1 after accepting record");
		}else {
			function.captureScreen("Downsize_Count_Not_Changed");
			Assert.assertNotEquals(totalcount_downsize_after, totalcount_downsize_before, "Downsize count should reduce by -1 after accepting record");
		}
		// Check for disks count added with +1 
		if(totalcost_downsize_after<totalcost_downsize_before) {
			Assert.assertNotEquals(totalcost_downsize_after, totalcost_downsize_before, "Accepted records cost is reduce from downsize saving cost");
		}else {
			function.captureScreen("Downsize_cost_Not_Changed");
			Assert.assertNotEquals(totalcost_downsize_after, totalcost_downsize_before, "Accepted records cost is reduce from downsize saving cost");
		}
		// Check for Opportunity Missed cost
		if(totalcost_projectedsaving_after<totalcost_projectedsaving_before) {
			Assert.assertNotEquals(totalcost_projectedsaving_after, totalcost_projectedsaving_before, "Accepted records cost is reduce from projected saving cost");
		}else {
			function.captureScreen("Projected_saving_Cost_Not_Changed");
			Assert.assertNotEquals(totalcost_projectedsaving_after, totalcost_projectedsaving_before, "Accepted records cost is reduce from projected saving cost");
		}
		*/
	}

	public void archiveresourceunderdownsizetab() throws IOException, TimeoutException {
		int totalcount_VMs_before=counttotalVMs();
		int totalcount_downsize_before=counttotaldownsize();
		double totalcost_downsize_before=capturetotalcostofDownsize_SAVINGScount();
		double totalcost_projectedsaving_before=capturetotalcostofPROJECTED_SAVINGScount();
		totalrowsintable50();
		function.waitForElementIsVisibleFluent(Locator_columnResourceID,400, 3);
		function.mouse_moveToElement(Locator_columnResourceID);
		String resourceid=function.table_Downsize_VMS_capture_resourceID_value_afterArchiveresource();
		System.out.println("Open Status Resource ID"+resourceid);
		function.waitFor(3000);
		boolean check=function.verifyresourceidpresentintablepatametercolumnindex(resourceid,3);
		System.out.println(check+"  is status");
		function.mouse_moveToElement(Locator_text_Total_VM_RECOMMENDATIONS);
		int totalcount_VMs_after=counttotalVMs();
		int totalcount_downsize_after =counttotaldownsize();
		double totalcost_downsize_after=capturetotalcostofDownsize_SAVINGScount();
		double totalcost_projectedsaving_after=capturetotalcostofPROJECTED_SAVINGScount();	
		// check for resource is moved form table or not
		if(check==false) {
			Assert.assertFalse(check,"After accepting resource, respective 'Resource ID' get removed form table");

		}else {
			function.captureScreen("respective_ResourceID_is_not_removed_form_table");
			Assert.assertFalse(check,"After accepting resource, respective 'Resource ID' get removed form table");

		}
		// Check for total VM count
		if(totalcount_VMs_after==(totalcount_VMs_before-1)) {
			Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM RECOMMENDATIONS count should reduce by -1 after accepting record");
		}else {
			function.captureScreen("Downsize_Count_Not_Changed");
			Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM RECOMMENDATIONS count should reduce by -1 after accepting record");
		}
		// Check for downsize saving count
		if(totalcount_downsize_after==(totalcount_downsize_before-1)) {
			Assert.assertNotEquals(totalcount_downsize_after, totalcount_downsize_before, "Downsize count should reduce by -1 after accepting record");
		}else {
			function.captureScreen("Downsize_Count_Not_Changed");
			Assert.assertNotEquals(totalcount_downsize_after, totalcount_downsize_before, "Downsize count should reduce by -1 after accepting record");
		}
		// Check for downsize saving cost
		if(totalcost_downsize_after<totalcost_downsize_before) {
			Assert.assertNotEquals(totalcost_downsize_after, totalcost_downsize_before, "Accepted records cost is reduce from downsize saving cost");
		}else {
			function.captureScreen("Downsize_cost_Not_Changed");
			Assert.assertNotEquals(totalcost_downsize_after, totalcost_downsize_before, "Accepted records cost is reduce from downsize saving cost");
		}
		// Check for Projected Saving cost
		if(totalcost_projectedsaving_after<totalcost_projectedsaving_before) {
			Assert.assertNotEquals(totalcost_projectedsaving_after, totalcost_projectedsaving_before, "Accepted records cost is reduce from projected saving cost");
		}else {
			function.captureScreen("Projected_saving_Cost_Not_Changed");
			Assert.assertNotEquals(totalcost_projectedsaving_after, totalcost_projectedsaving_before, "Accepted records cost is reduce from projected saving cost");
		}
	}

	public void comparetotalvmcountwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		int totalcount_VMs=counttotalVMs();
		String stringValue = String.valueOf(totalcount_VMs);
		String countofVMsfromDB= function.capturedatabsesinglecolumncountByIndex(sql,columnnumber);
		addinfolog("Total VMs Count=> Frontend Count is:"+stringValue+" & Database Count is:"+countofVMsfromDB);
		Assert.assertEquals(stringValue,countofVMsfromDB,"Total Count of VM RECOMMENDATIONS is match with Database");
	}

	public void comparetotaldownsizecountwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		int totalcount_downsize=counttotaldownsize();
		String stringValue = String.valueOf(totalcount_downsize);
		String countofdownsizefromDB= function.capturedatabsesinglecolumncountByIndex(sql,columnnumber);
		addinfolog("Total Downsize Count=> Frontend Count is:"+stringValue+" & Database Count is:"+countofdownsizefromDB);
		Assert.assertEquals(stringValue,countofdownsizefromDB,"Total Count of downsize is match with Database");
	}

	public void comparetotaldownsizecostwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		double totalcost_downsize=capturetotalcostofDownsize_SAVINGScount();
		String costofdownsizefromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
		double stringValue = Double.parseDouble(costofdownsizefromDB);
		addinfolog("Total Downsize Cost=> Frontend Cost is:"+totalcost_downsize+" & Database Cost is:"+stringValue);
		Assert.assertEquals(totalcost_downsize,stringValue,"Total Cost of downsize saving is match with Database");
	}

	public void compareprojectedsavingcostwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		double totalcost_projectedsaving=capturetotalcostofPROJECTED_SAVINGScount();	
		String costofprojectedsavingfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
		double stringValue = Double.parseDouble(costofprojectedsavingfromDB);
		addinfolog("Total Projected Saving Cost=> Frontend Cost is:"+totalcost_projectedsaving+" & Database Cost is:"+stringValue);
		Assert.assertEquals(totalcost_projectedsaving,stringValue,"Total Cost of Projected Savings is match with Database");
	}

	public void comparecurrentcostwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		double currentcost=capturetotalcostofCurrent_Costs();	
		String currentcostfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
		double stringValue = Double.parseDouble(currentcostfromDB);
		addinfolog("Current Cost=> Frontend Cost is:"+currentcost+" & Database Cost is:"+stringValue);
		Assert.assertEquals(currentcost,stringValue,"Current Cost is match with Database");
	}

	public void compareprojectedcostwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		double projectedcost=capturetotalcostofProjected_Costs();	
		String projectedcostfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
		double stringValue = Double.parseDouble(projectedcostfromDB);
		addinfolog("Projected Cost=> Frontend Cost is:"+projectedcost+" & Database Cost is:"+stringValue);
		Assert.assertEquals(projectedcost,stringValue,"Projected Cost is match with Database");
	}

	public void comparemissedcostwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		double missedcost=capturetotalcostofOPPORTUNITY_MISSED();	
		String missedcostfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
		double stringValue = Double.parseDouble(missedcostfromDB);
		addinfolog("Missed Cost=> Frontend Cost is:"+missedcost+" & Database Cost is:"+stringValue);
		Assert.assertEquals(missedcost,stringValue,"Total Cost of Oppertunity Missed is match with Database");
	}

	public void verifycountandcostatarchivepageafterremovingrecordformactive() throws TimeoutException, IOException {
		movearchivepage();
		int totalcount_VMs_before=counttotalVMs();
		int totalcount_downsize_before=counttotaldownsize();
		double totalcost_downsize_before=capturetotalcostofDownsize_SAVINGScount();
		double CurrentCost_before=capturetotalcostofCurrent_Costs();
		double ProjectedCost_before=capturetotalcostofProjected_Costs();
		double OpportunityMissedCost_before=capturetotalcostofOPPORTUNITY_MISSED();
		moveactivepage();
		totalrowsintable50();
		function.waitForElementIsVisibleFluent(Locator_columnResourceID,400, 3);
		function.mouse_moveToElement(Locator_columnResourceID);
		String resourceid=function.table_Downsize_VMS_module_capture_resourceID_value_afterArchiveresource();
		System.out.println("Open Status Resource ID"+resourceid);
		movearchivepage();
		totalrowsintable50();
		function.waitForElementIsVisibleFluent(Locator_columnResourceID,400, 3);
		function.mouse_moveToElement(Locator_columnResourceID);
		boolean check=function.verifyresourceidpresentintablepatametercolumnindex(resourceid,3);
		System.out.println(check+"  is status");
		function.mouse_moveToElement(Locator_text_Archived_VM_Resources);
		int totalcount_VMs_after=counttotalVMs();
		int totalcount_downsize_after=counttotaldownsize();
		double totalcost_downsize_after=capturetotalcostofDownsize_SAVINGScount();
		double CurrentCost_after=capturetotalcostofCurrent_Costs();
		double ProjectedCost_after=capturetotalcostofProjected_Costs();
		double OpportunityMissedCost_after=capturetotalcostofOPPORTUNITY_MISSED();
		// check for resource is added in table or not
		if(check==true) {
			Assert.assertTrue(check,"After archive resource, respective 'Resource ID' get added in Archive table");

		}else {
			function.captureScreen("respective_ResourceID_is_not_added_in_archive_table");
			Assert.assertTrue(check,"After archive resource, respective 'Resource ID' get added in Archive table");

		}
		// check for total VM count
		if(totalcount_VMs_after==(totalcount_VMs_before+1)) {
			Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM RECOMMENDATIONS count should added by +1 after archive record");
		}else {
			function.captureScreen("Downsize_Count_Not_Changed");
			Assert.fail("Total VM RECOMMENDATIONS count should added by +1 after archive record");
		}
		// check for downsize count
		if(totalcount_downsize_after==(totalcount_downsize_before+1)) {
			Assert.assertNotEquals(totalcount_downsize_after, totalcount_downsize_before, "Downsize count should added by +1 after archive record");
		}else {
			function.captureScreen("Downsize_Count_Not_Changed");
			Assert.fail("Downsize count should added by +1 after archive record");
		}
		// Check for downsize saving cost 
		if(totalcost_downsize_after>totalcost_downsize_before) {
			Assert.assertNotEquals(totalcost_downsize_after, totalcost_downsize_before, "Accepted records cost is added in downsize saving cost");
		}else {
			function.captureScreen("Downsize_cost_Not_Changed");
			Assert.fail("Accepted records cost is added in downsize saving cost");
		}
		// Check for projected cost
		if(ProjectedCost_after>ProjectedCost_before) {
			Assert.assertNotEquals(ProjectedCost_after, ProjectedCost_before, "Accepted records cost is added in projected cost");
		}else {
			function.captureScreen("Projected_saving_Cost_Not_Changed");
			Assert.fail("Accepted records cost is added in projected cost");
		}
		// Check for current cost
		if(CurrentCost_after>CurrentCost_before) {
			Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Accepted records cost is added in Current cost");
		}else {
			function.captureScreen("Current_Cost_Not_Changed");
			Assert.fail("Accepted records cost is added in Current cost");
		}
		// Check for Oppertunity Missed cost
		if(OpportunityMissedCost_after>OpportunityMissedCost_before) {
			Assert.assertNotEquals(OpportunityMissedCost_after, OpportunityMissedCost_before, "Accepted records cost is added in Opportunity Missed cost");
		}else {
			function.captureScreen("OpportunityMissed_Cost_Not_Changed");
			Assert.fail("Accepted records cost is added in Opportunity Missed cost");
		}		
	}

	public void unarchiving_any_record_of_from_Archive_page() throws TimeoutException, IOException {
		movearchivepage();
		int totalcount_VMs_before=counttotalVMs();
		int totalcount_downsize_before=counttotaldownsize();
		double totalcost_downsize_before=capturetotalcostofDownsize_SAVINGScount();
		double ProjectedCost_before=capturetotalcostofProjected_Costs();
		double OpportunityMissedCost_before=capturetotalcostofOPPORTUNITY_MISSED();
		String resourceid=function.table_archive_VMS_Module_capture_resourceID_value(3);
		function.clickbyactionclass(Locator_text_Archived_VM_Resources);
		function.waitFor(3000);
		int totalcount_VMs_after=counttotalVMs();
		int totalcount_downsize_after=counttotaldownsize();
		double totalcost_downsize_after=capturetotalcostofDownsize_SAVINGScount();
		double ProjectedCost_after=capturetotalcostofProjected_Costs();
		double OpportunityMissedCost_after=capturetotalcostofOPPORTUNITY_MISSED();
		// check for total VM count
		if(totalcount_VMs_after==(totalcount_VMs_before-1)) {
			Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM RECOMMENDATIONS count should reduce by -1 after unarchive record");
		}else {
			function.captureScreen("Downsize_Count_Not_Changed");
			Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM RECOMMENDATIONS count should reduce by -1 after unarchive record");
		}
		// check for downsize count
		if(totalcount_downsize_after==(totalcount_downsize_before-1)) {
			Assert.assertNotEquals(totalcount_downsize_after, totalcount_downsize_before, "Downsize count should reduce by -1 after unarchive record");
		}else {
			function.captureScreen("Downsize_Count_Not_Changed");
			Assert.assertNotEquals(totalcount_downsize_after, totalcount_downsize_before, "Downsize count should reduce by -1 after unarchive record");
		}
		// Check for downsize saving cost 
		if(totalcost_downsize_after<totalcost_downsize_before) {
			Assert.assertNotEquals(totalcost_downsize_after, totalcost_downsize_before, "Accepted records cost is deduct form downsize saving cost");
		}else {
			function.captureScreen("Downsize_cost_Not_Changed");
			Assert.assertNotEquals(totalcost_downsize_after, totalcost_downsize_before, "Accepted records cost is deduct form downsize saving cost");
		}
		// Check for projected cost
		if(ProjectedCost_after<ProjectedCost_before) {
			Assert.assertNotEquals(ProjectedCost_after, ProjectedCost_before, "Accepted records cost is deduct form projected cost");
		}else {
			function.captureScreen("Projected_saving_Cost_Not_Changed");
			Assert.assertNotEquals(ProjectedCost_after, ProjectedCost_before, "Accepted records cost is deduct form projected cost");
		}
		// Check for Oppertunity Missed cost
		if(OpportunityMissedCost_after<OpportunityMissedCost_before) {
			Assert.assertNotEquals(OpportunityMissedCost_after, OpportunityMissedCost_before, "Accepted records cost is deduct form Opportunity Missed cost");
		}else {
			function.captureScreen("OpportunityMissed_Cost_Not_Changed");
			Assert.assertNotEquals(OpportunityMissedCost_after, OpportunityMissedCost_before, "Accepted records cost is deduct form Opportunity Missed cost");
		}
		moveactivepage();
		totalrowsintable50();
		function.waitForElementIsVisibleFluent(Locator_columnResourceID,400, 3);
		function.mouse_moveToElement(Locator_columnResourceID);
		boolean check=function.verifyresourceidpresentintablepatametercolumnindex(resourceid,3);
		System.out.println(check+"  is status");
		// check for resource is added in table or not
		if(check==true) {
			Assert.assertTrue(check,"After archive resource, respective 'Resource ID' get added in active downsize table");

		}else {
			function.captureScreen("respective_ResourceID_is_not_added_in_active_downsize_table");
			Assert.assertTrue(check,"After archive resource, respective 'Resource ID' get added in active downsize table");

		}
	}

	public void Accepting_resource_count_and_cost_are_reduce_form_upsize() throws TimeoutException, IOException {
		int upsidecount=counttotalupsize();
		System.out.println("//"+upsidecount+"//");
		switchtoupsidetab();
		boolean NoUpsidedata=function.verifyDisplayed(Locator_text_No_UpsizeDAta);
		if ((NoUpsidedata==true)&&(upsidecount==0)) {
			System.out.println("No Upside Data is Available in table");
		}else {
			int totalcount_upsize_before=counttotalupsize();
			double totalcost_upsize_before=capturetotalcostofUpsize();
			double CurrentCost_before=capturetotalcostofCurrent_Costs();

			totalrowsintable50();
			function.waitForElementIsVisibleFluent(Locator_columnResourceID,400, 3);
			function.mouse_moveToElement(Locator_columnResourceID);
			String resourceid=function.table_Downsize_VMS_capture_resourceID_value_afterAcceptresource();
			function.waitFor(3000);
			//boolean check=function.verifyresourceidpresentintablepatametercolumnindex(resourceid,3);

			function.mouse_moveToElement(Locator_text_Total_VM_RECOMMENDATIONS);
			int totalcount_upsize_after=counttotalupsize();
			double totalcost_upsize_after=capturetotalcostofUpsize();
			double CurrentCost_after=capturetotalcostofCurrent_Costs();
			//check for resource id is removed form table
			/*
			if(check==false) {
				Assert.assertFalse(check,"After accepting resource, respective 'Resource ID' get removed form table");

			}else {
				function.captureScreen("respective_ResourceID_is_not_removed_form_table");
				Assert.assertFalse(check,"After accepting resource, respective 'Resource ID' get removed form table");

			}
			// check for upsize count
			if(totalcount_upsize_after==(totalcount_upsize_before-1)) {
				Assert.assertNotEquals(totalcount_upsize_after, totalcount_upsize_before, "Upsize count should reduce by -1 after accepting record");
			}else {
				function.captureScreen("Upsize_Count_Not_Changed");
				Assert.assertNotEquals(totalcount_upsize_after, totalcount_upsize_before, "Upsize count should reduce by -1 after accepting record");
			}
			// Check for upsize saving cost 
			if(totalcost_upsize_after<totalcost_upsize_before) {
				Assert.assertNotEquals(totalcost_upsize_after, totalcost_upsize_before, "Accepted records cost is reduce from upsize saving cost");
			}else {
				function.captureScreen("Upsize_cost_Not_Changed");
				Assert.assertNotEquals(totalcost_upsize_after, totalcost_upsize_before, "Accepted records cost is reduce from upsize saving cost");
			}
			// Check for current cost
			if(CurrentCost_after<CurrentCost_before) {
				Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Accepted records cost is reduce from current cost");
			}else {
				function.captureScreen("Current_Cost_Not_Changed");
				Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Accepted records cost is reduce from current cost");
			}
			*/

		}
	}

	public void compareupsizesavingcostwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		double upsizecost=capturetotalcostofUpsize();	
		String upsizecostfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
		double stringValue = Double.parseDouble(upsizecostfromDB);
		addinfolog("Upsize Cost=> Frontend Cost is:"+upsizecost+" & Database Cost is:"+stringValue);
		Assert.assertEquals(upsizecost,stringValue,"Upsize Saving cost is match with Database");
	}

	public void comparetotalupsizecountwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		int totalcount_upsize=counttotalupsize();
		String stringValue = String.valueOf(totalcount_upsize);
		String countofupsizefromDB= function.capturedatabsesinglecolumncountByIndex(sql,columnnumber);
		addinfolog("Total Upsize Count=> Frontend Count is:"+stringValue+" & Database Count is:"+countofupsizefromDB);
		Assert.assertEquals(stringValue,countofupsizefromDB,"Total Count of downsize is match with Database");
	}

	public void archiveresourceunderupsizetab() throws IOException, TimeoutException {
		int upsidecount=counttotalupsize();
		switchtoupsidetab();
		boolean NoUpsidedata=function.verifyDisplayed(Locator_text_No_UpsizeDAta);
		if ((NoUpsidedata==true)&&(upsidecount==0)) {
			System.out.println("No Upside Data is Available in table");
		}else {
			int totalcount_upsize_before=counttotalupsize();
			double totalcost_upsize_before=capturetotalcostofUpsize();
			double CurrentCost_before=capturetotalcostofCurrent_Costs();
			totalrowsintable50();
			function.waitForElementIsVisibleFluent(Locator_columnResourceID,400, 3);
			function.mouse_moveToElement(Locator_columnResourceID);
			String resourceid=function.table_Downsize_VMS_capture_resourceID_value_afterArchiveresource();
			System.out.println("Open Status Resource ID"+resourceid);
			function.waitFor(3000);
			boolean check=function.verifyresourceidpresentintablepatametercolumnindex(resourceid,3);
			System.out.println(check+"  is status");
			function.mouse_moveToElement(Locator_text_Total_VM_RECOMMENDATIONS);
			int totalcount_upsize_after=counttotalupsize();
			double totalcost_upsize_after=capturetotalcostofUpsize();
			double CurrentCost_after=capturetotalcostofCurrent_Costs();
			// check resource is removed form table
			if(check==false) {
				Assert.assertFalse(check,"After accepting resource, respective 'Resource ID' get removed form table");

			}else {
				function.captureScreen("respective_ResourceID_is_not_removed_form_table");
				Assert.assertFalse(check,"After accepting resource, respective 'Resource ID' get removed form table");

			}
			// check upsize count
			if(totalcount_upsize_after==(totalcount_upsize_before-1)) {
				Assert.assertNotEquals(totalcount_upsize_after, totalcount_upsize_before, "Upsize count should reduce by -1 after accepting record");
			}else {
				function.captureScreen("Upsize_Count_Not_Changed");
				Assert.assertNotEquals(totalcount_upsize_after, totalcount_upsize_before, "Upsize count should reduce by -1 after accepting record");
			}
			// Check for upsize saving cost 
			if(totalcost_upsize_after<totalcost_upsize_before) {
				Assert.assertNotEquals(totalcost_upsize_after, totalcost_upsize_before, "Accepted records cost is reduce from upsize saving cost");
			}else {
				function.captureScreen("Upsize_cost_Not_Changed");
				Assert.assertNotEquals(totalcost_upsize_after, totalcost_upsize_before, "Accepted records cost is reduce from upsize saving cost");
			}
			// Check for current cost
			if(CurrentCost_after<CurrentCost_before) {
				Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Accepted records cost is reduce from current cost");
			}else {
				function.captureScreen("Current_Cost_Not_Changed");
				Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Accepted records cost is reduce from current cost");
			}
		}			
	}

	public void verifyarchiveupsizerecordaddedinarchivepage() throws IOException, TimeoutException {
		movearchivepage();
		int upsidecount=counttotalupsize();
		switchtoupsidetab();
		boolean NoUpsidedata=function.verifyDisplayed(Locator_text_No_UpsizeDAta);
		if ((NoUpsidedata==true)&&(upsidecount==0)) {
			System.out.println("No Upside Data is Available in table");
		}else {
			int totalcount_VMs_before=counttotalVMs();
			int totalcount_upsize_before=counttotalupsize();
			double totalcost_upsize_before=capturetotalcostofUpsize();
			double CurrentCost_before=capturetotalcostofCurrent_Costs();
			moveactivepage();
			totalrowsintable50();
			function.waitForElementIsVisibleFluent(Locator_columnResourceID,400, 3);
			function.mouse_moveToElement(Locator_columnResourceID);
			String resourceid=function.table_Downsize_VMS_module_capture_resourceID_value_afterArchiveresource();
			System.out.println("Open Status Resource ID"+resourceid);
			function.waitFor(3000);
			movearchivepage();
			switchtoupsidetab();
			totalrowsintable50();
			function.waitForElementIsVisibleFluent(Locator_columnResourceID,400, 3);
			function.mouse_moveToElement(Locator_columnResourceID);
			boolean check=function.verifyresourceidpresentintablepatametercolumnindex(resourceid,3);
			System.out.println(check+"  is status");
			function.mouse_moveToElement(Locator_text_Total_VM_RECOMMENDATIONS);
			int totalcount_VMs_after=counttotalVMs();
			int totalcount_upsize_after=counttotalupsize();
			double totalcost_upsize_after=capturetotalcostofUpsize();
			double CurrentCost_after=capturetotalcostofCurrent_Costs();
			// check for resource is added in table or not
			if(check==true) {
				Assert.assertTrue(check,"After archive resource, respective 'Resource ID' get added in Archive table");

			}else {
				function.captureScreen("respective_ResourceID_is_not_added_in_archive_table");
				Assert.assertTrue(check,"After archive resource, respective 'Resource ID' get added in Archive table");

			}
			// check total vm count 
			if(totalcount_VMs_after==(totalcount_VMs_before+1)) {
				Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM count should added by +1 after archive record");
			}else {
				function.captureScreen("Total_VM_Count_Not_Changed");
				Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM should added by +1 after archive record");
			}
			// check for upsize count
			if(totalcount_upsize_after==(totalcount_upsize_before+1)) {
				Assert.assertNotEquals(totalcount_upsize_after, totalcount_upsize_before, "Upsize count should reduce by +1 after archive record");
			}else {
				function.captureScreen("Upsize_Count_Not_Changed");
				Assert.assertNotEquals(totalcount_upsize_after, totalcount_upsize_before, "Upsize count should reduce by +1 after archive record");
			}
			// Check for upsize cost 
			if(totalcost_upsize_after>totalcost_upsize_before) {
				Assert.assertNotEquals(totalcost_upsize_after, totalcost_upsize_before, "Archived records cost is added in upsize saving cost");
			}else {
				function.captureScreen("Upsize_cost_Not_Changed");
				Assert.assertNotEquals(totalcost_upsize_after, totalcost_upsize_before, "Archived records cost is added in upsize saving cost");
			}
			// Check for current cost
			if(CurrentCost_after>CurrentCost_before) {
				Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Archived records cost is added in current cost");
			}else {
				function.captureScreen("Current_Cost_Not_Changed");
				Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Archived records cost is added in current cost");
			}
		}
	}

	public void unarchiving_any_Upsizerecord_of_from_Archive_page() throws TimeoutException, IOException {
		movearchivepage();
		int upsidecount=counttotalupsize();
		switchtoupsidetab();
		boolean NoUpsidedata=function.verifyDisplayed(Locator_text_No_UpsizeDAta);
		if ((NoUpsidedata==true)&&(upsidecount==0)) {
			System.out.println("No Upside Data is Available in table");
		}else {
			moveactivepage();
			int totalcount_VMs_before=counttotalVMs();
			int totalcount_upsize_before=counttotalupsize();
			double totalcost_upsize_before=capturetotalcostofUpsize();
			double CurrentCost_before=capturetotalcostofCurrent_Costs();
			movearchivepage();
			switchtoupsidetab();
			totalrowsintable50();
			function.waitForElementIsVisibleFluent(Locator_columnResourceID,400, 3);
			function.mouse_moveToElement(Locator_columnResourceID);
			String resourceid=function.table_archive_VMS_Module_capture_resourceID_value(3);
			System.out.println("Open Status Resource ID"+resourceid);
			function.waitFor(3000);
			moveactivepage();
			switchtoupsidetab();
			totalrowsintable50();
			function.waitForElementIsVisibleFluent(Locator_columnResourceID,400, 3);
			function.mouse_moveToElement(Locator_columnResourceID);
			boolean check=function.verifyresourceidpresentintablepatametercolumnindex(resourceid,3);
			System.out.println(check+"  is status");
			function.mouse_moveToElement(Locator_text_Total_VM_RECOMMENDATIONS);
			int totalcount_VMs_after=counttotalVMs();
			int totalcount_upsize_after=counttotalupsize();
			double totalcost_upsize_after=capturetotalcostofUpsize();
			double CurrentCost_after=capturetotalcostofCurrent_Costs();
			// check for resource is added in table or not
			if(check==true) {
				Assert.assertTrue(check,"After archive resource, respective 'Resource ID' get added in Archive table");

			}else {
				function.captureScreen("respective_ResourceID_is_not_added_in_archive_table");
				Assert.assertTrue(check,"After archive resource, respective 'Resource ID' get added in Archive table");
			}
			// check total vm count 
			if(totalcount_VMs_after==(totalcount_VMs_before+1)) {
				Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM count should added by +1 after unarchive record");
			}else {
				function.captureScreen("Total_VM_Count_Not_Changed");
				Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM should added by +1 after unarchive record");
			}
			// check for upsize count
			if(totalcount_upsize_after==(totalcount_upsize_before+1)) {
				Assert.assertNotEquals(totalcount_upsize_after, totalcount_upsize_before, "Upsize count should reduce by +1 after unarchive record");
			}else {
				function.captureScreen("Upsize_Count_Not_Changed");
				Assert.assertNotEquals(totalcount_upsize_after, totalcount_upsize_before, "Upsize count should reduce by +1 after unarchive record");
			}
			// Check for upsize cost 
			if(totalcost_upsize_after>totalcost_upsize_before) {
				Assert.assertNotEquals(totalcost_upsize_after, totalcost_upsize_before, "Unarchived records cost is added in upsize saving cost");
			}else {
				function.captureScreen("Upsize_cost_Not_Changed");
				Assert.assertNotEquals(totalcost_upsize_after, totalcost_upsize_before, "Unarchived records cost is added in upsize saving cost");
			}
			// Check for current cost
			if(CurrentCost_after>CurrentCost_before) {
				Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Unarchived records cost is added in current cost");
			}else {
				function.captureScreen("Current_Cost_Not_Changed");
				Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Unarchived records cost is added in current cost");
			}
		}
	}

	public void Discard_the_Downsize_record_activepage() throws TimeoutException, IOException {
		int totalcount_VMs_before=counttotalVMs();
		int totalcount_downsize_before=counttotaldownsize();
		double totalcost_downsize_before=capturetotalcostofDownsize_SAVINGScount();
		double CurrentCost_before=capturetotalcostofCurrent_Costs();
		double ProjectedSavings_before=capturetotalcostofPROJECTED_SAVINGScount();
		//totalrowsintable50();
		String resourceid=function.table_Downsize_VMS_capture_resourceID_value_afterDiscardresource();
		System.out.println("Open Status Resource ID"+resourceid);
		function.waitFor(4000);
		function.mouse_moveToElement(Locator_tab_Discarded);
		function.clickaction(Locator_tab_Discarded);
		function.waitFor(6000);

		int totalcount_VMs_after=counttotalVMs();
		int totalcount_downsize_after=counttotaldownsize();
		double totalcost_downsize_after=capturetotalcostofDownsize_SAVINGScount();
		double CurrentCost_after=capturetotalcostofCurrent_Costs();
		double ProjectedSavings_after=capturetotalcostofPROJECTED_SAVINGScount();
		boolean check=function.verifyresourceidpresentintablepatametercolumnindexrowpath(resourceid,3);
		System.out.println(check+"  is status");
		// check for resource is added in table or not

		if(check==true) {
			Assert.assertTrue(check,"After discard resource, respective 'Resource ID' get added in discarded table");

		}else {
			function.captureScreen("respective_ResourceID_is_not_added_in_discarded_table");
			Assert.fail("After discard resource, respective 'Resource ID' get added in discarded table");
		}

		// Check for total VM count
		if(totalcount_VMs_after==(totalcount_VMs_before-1)) {
			Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM RECOMMENDATIONS count should reduce by -1 after Discarding record");
		}else {
			function.captureScreen("Downsize_Count_Not_Changed");
			Assert.fail("Total VM RECOMMENDATIONS count should reduce by -1 after Discarding record");
		}
		// Check for downsize saving count
		if(totalcount_downsize_after==(totalcount_downsize_before-1)) {
			Assert.assertNotEquals(totalcount_downsize_after, totalcount_downsize_before, "Downsize count should reduce by -1 after Discarding record");
		}else {
			function.captureScreen("Downsize_Count_Not_Changed");
			Assert.fail("Downsize count should reduce by -1 after Discarding record");
		}
		// Check for downsize saving cost
		if(totalcost_downsize_after<totalcost_downsize_before) {
			Assert.assertNotEquals(totalcost_downsize_after, totalcost_downsize_before, "Discarded records cost is reduce from downsize saving cost");
		}else {
			function.captureScreen("Downsize_cost_Not_Changed");
			Assert.fail("Discarded records cost is reduce from downsize saving cost");
		}
		// Check for Projected Saving cost
		if(ProjectedSavings_after<ProjectedSavings_before) {
			Assert.assertNotEquals(ProjectedSavings_after, ProjectedSavings_before, "Discarded records cost is reduce from projected saving cost");
		}else {
			function.captureScreen("Projected_saving_Cost_Not_Changed");
			Assert.fail("Discarded records cost is reduce from projected saving cost");
		}
		// Check for current cost
		if(CurrentCost_after<CurrentCost_before) {
			Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Discarded records cost is reduce from current cost");
		}else {
			function.captureScreen("Current_Cost_Not_Changed");
			Assert.fail("Discarded records cost is reduce from current cost");
		}
	}

	public void Move_To_Open_discarded_Downsize() throws IOException, TimeoutException {
		int totalcount_VMs_before=counttotalVMs();
		int totalcount_downsize_before=counttotaldownsize();
		double totalcost_downsize_before=capturetotalcostofDownsize_SAVINGScount();
		double CurrentCost_before=capturetotalcostofCurrent_Costs();
		double ProjectedSavings_before=capturetotalcostofPROJECTED_SAVINGScount();
		function.clickaction(Locator_tab_Discarded);
		function.waitForElementIsVisible(Locator_fisrt_Row_three_dots, 70);
		String resourceid=function. table_Discard_VMS_capture_resourceID_value_afterDiscardresource();
		System.out.println("Open Status Resource ID"+resourceid);
		function.waitFor(4000);
		int totalcount_VMs_after=counttotalVMs();
		int totalcount_downsize_after=counttotaldownsize();
		double totalcost_downsize_after=capturetotalcostofDownsize_SAVINGScount();
		double CurrentCost_after=capturetotalcostofCurrent_Costs();
		double ProjectedSavings_after=capturetotalcostofPROJECTED_SAVINGScount();
		boolean check=function.verifyresourceidpresentintablepatametercolumnindexrowpath(resourceid,3);
		System.out.println(check+"  is status");
		// check for resource is added in table or not

		if(check==false) {
			Assert.assertFalse(check,"After Move To Open resource, respective 'Resource ID' get removed in discarded table");

		}else {
			function.captureScreen("respective_ResourceID_is_not_removed_from_discarded_table");
			Assert.fail("After Move To Open resource, respective 'Resource ID' get removed in discarded table");
		}

		// Check for total VM count
		if(totalcount_VMs_after==(totalcount_VMs_before+1)) {
			Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM RECOMMENDATIONS count should added by +1 after moved to open records");
		}else {
			function.captureScreen("Downsize_Count_Not_Changed");
			Assert.fail("Total VM RECOMMENDATIONS count should added by +1 after moved to open records");
		}
		// Check for downsize saving count
		if(totalcount_downsize_after==(totalcount_downsize_before+1)) {
			Assert.assertNotEquals(totalcount_downsize_after, totalcount_downsize_before, "Downsize count should added by +1 after moved to open records");
		}else {
			function.captureScreen("Downsize_Count_Not_Changed");
			Assert.fail("Downsize count should added by +1 after moved to open records");
		}
		// Check for downsize saving cost
		if(totalcost_downsize_after>totalcost_downsize_before) {
			Assert.assertNotEquals(totalcost_downsize_after, totalcost_downsize_before, "Moved to open records cost is added in downsize saving cost");
		}else {
			function.captureScreen("Downsize_cost_Not_Changed");
			Assert.fail("Moved records cost is added in downsize saving cost");
		}
		// Check for Projected Saving cost
		if(ProjectedSavings_after>ProjectedSavings_before) {
			Assert.assertNotEquals(ProjectedSavings_after, ProjectedSavings_before, "Moved to open records cost is added in projected saving cost");
		}else {
			function.captureScreen("Projected_saving_Cost_Not_Changed");
			Assert.fail("Moved records cost is added in projected saving cost");
		}
		// Check for current cost
		if(CurrentCost_after>CurrentCost_before) {
			Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Moved to open records cost is added in current cost");
		}else {
			function.captureScreen("Current_Cost_Not_Changed");
			Assert.fail("Moved records cost is added in current cost");
		}
	}

	public void Archive_discarded_downsize_record_is_being_added_to_Archive_page() throws TimeoutException, IOException {
		movearchivepage();
		int totalcount_VMs_before=counttotalVMs();
		int totalcount_downsize_before=counttotaldownsize();
		double totalcost_downsize_before=capturetotalcostofDownsize_SAVINGScount();
		double CurrentCost_before=capturetotalcostofCurrent_Costs();
		double OpportunityMissedCost_before=capturetotalcostofOPPORTUNITY_MISSED();
		moveactivepage();
		function.clickaction(Locator_tab_Discarded);
		String resourceid=function.table_Discard_VMS_capture_resourceID_value_afterarchiveresource();
		System.out.println("Open Status Resource ID"+resourceid);
		function.waitFor(4000);
		movearchivepage();
		boolean check=function.verifyresourceidpresentintablepatametercolumnindex(resourceid,3);
		System.out.println(check+"  is status");
		int totalcount_VMs_after=counttotalVMs();
		int totalcount_downsize_after=counttotaldownsize();
		double totalcost_downsize_after=capturetotalcostofDownsize_SAVINGScount();
		double CurrentCost_after=capturetotalcostofCurrent_Costs();
		double OpportunityMissedCost_after=capturetotalcostofOPPORTUNITY_MISSED();
		// check for resource is added in table or not
		if(check==true) {
			Assert.assertTrue(check,"After archive resource, respective 'Resource ID' get added in Archive table");

		}else {
			function.captureScreen("respective_ResourceID_is_not_added_in_archive_table");
			Assert.assertTrue(check,"After archive resource, respective 'Resource ID' get added in Archive table");

		}
		// check for total VM count
		if(totalcount_VMs_after==(totalcount_VMs_before+1)) {
			Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM RECOMMENDATIONS count should added by +1 after archive record");
		}else {
			function.captureScreen("Downsize_Count_Not_Changed");
			Assert.fail("Total VM RECOMMENDATIONS count should added by +1 after archive record");
		}
		// check for downsize count
		if(totalcount_downsize_after==(totalcount_downsize_before+1)) {
			Assert.assertNotEquals(totalcount_downsize_after, totalcount_downsize_before, "Downsize count should added by +1 after archive record");
		}else {
			function.captureScreen("Downsize_Count_Not_Changed");
			Assert.fail("Downsize count should added by +1 after archive record");
		}
		// Check for downsize saving cost 
		if(totalcost_downsize_after>totalcost_downsize_before) {
			Assert.assertNotEquals(totalcost_downsize_after, totalcost_downsize_before, "Accepted records cost is added in downsize saving cost");
		}else {
			function.captureScreen("Downsize_cost_Not_Changed");
			Assert.fail("Accepted records cost is added in downsize saving cost");
		}

		// Check for current cost
		if(CurrentCost_after>CurrentCost_before) {
			Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Accepted records cost is added in Current cost");
		}else {
			function.captureScreen("Current_Cost_Not_Changed");
			Assert.fail("Accepted records cost is added in Current cost");
		}

		// Check for Oppertunity Missed cost
		if(OpportunityMissedCost_after>OpportunityMissedCost_before) {
			Assert.assertNotEquals(OpportunityMissedCost_after, OpportunityMissedCost_before, "Accepted records cost is added in Opportunity Missed cost");
		}else {
			function.captureScreen("OpportunityMissed_Cost_Not_Changed");
			Assert.fail("Accepted records cost is added in Opportunity Missed cost");
		}
	}

	public void Discard_the_Upsize_record() throws IOException, TimeoutException {
		switchtoupsidetab();
		int upsidecount=counttotalupsize();
		boolean NoUpsidedata=function.verifyDisplayed(Locator_text_No_UpsizeDAta);
		if ((NoUpsidedata==true)&&(upsidecount==0)) {
			System.out.println("No Upside Data is Available in table");
		}else {
			int totalcount_VMs_before=counttotalVMs();
			int totalcount_upsize_before=counttotalupsize();
			double totalcost_upsize_before=capturetotalcostofUpsize();
			double CurrentCost_before=capturetotalcostofCurrent_Costs();
			function.waitForElementIsVisibleFluent(Locator_columnResourceID,400, 3);
			function.mouse_moveToElement(Locator_columnResourceID);
			String resourceid=function.table_Downsize_VMS_capture_resourceID_value_afterDiscardresource();
			System.out.println("Open Status Resource ID"+resourceid);
			function.mouse_moveToElement(Locator_tab_Discarded);
			function.mouse_moveToElement(Locator_text_Total_VM_RECOMMENDATIONS);
			int totalcount_VMs_after=counttotalVMs();
			int totalcount_upsize_after=counttotalupsize();
			double totalcost_upsize_after=capturetotalcostofUpsize();
			double CurrentCost_after=capturetotalcostofCurrent_Costs();
			boolean check=function.verifyresourceidpresentintablepatametercolumnindex(resourceid,3);
			System.out.println(check+"  is status");
			// check for resource is added in table or not
			if(check==false) {
				Assert.assertFalse(check,"After discard resource, respective 'Resource ID' get removed from Discard table");

			}else {
				function.captureScreen("respective_ResourceID_is_not_added_in_archive_table");
				Assert.fail("After discard resource, respective 'Resource ID' get should get removed form Discard table");

			}
			// Check for total VM count
			if(totalcount_VMs_after==(totalcount_VMs_before-1)) {
				Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM RECOMMENDATIONS count should reduce by -1 after moving record");
			}else {
				function.captureScreen("Total_VM_Count_Not_Changed");
				Assert.fail("Total VM RECOMMENDATIONS count should reduce by -1 after Discarding record");
			}
			// Check for upsize saving count
			if(totalcount_upsize_after==(totalcount_upsize_before-1)) {
				Assert.assertNotEquals(totalcount_upsize_after, totalcount_upsize_before, "Upsize count should reduce by -1 after moving record");
			}else {
				function.captureScreen("Upsize_Count_Not_Changed");
				Assert.fail("Upsize count should reduce by -1 after Discarding record");
			}
			// Check for upsize saving cost
			if(totalcost_upsize_after<totalcost_upsize_before) {
				Assert.assertNotEquals(totalcost_upsize_after, totalcost_upsize_before, "Moved records cost  reduce from upsize saving cost");
			}else {
				function.captureScreen("Upsize_cost_Not_Changed");
				Assert.fail("Moved records cost is reduce from downsize saving cost");
			}
			// Check for current cost
			if(CurrentCost_after<CurrentCost_before) {
				Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Moved records cost reduce from current cost");
			}else {
				function.captureScreen("Current_Cost_Not_Changed");
				Assert.fail("Moved records cost is reduce from current cost");
			}
		}	
	}

	public void Moved_discarded_Upsize_record() throws TimeoutException, IOException {
		function.clickaction(Locator_tab_Discarded);
		boolean upsizerecord=function.checkupsizerecordexitsintable();		
		if (upsizerecord==true) {
			int totalcount_VMs_before=counttotalVMs();
			int totalcount_upsize_before=counttotalupsize();
			double totalcost_upsize_before=capturetotalcostofUpsize();
			double CurrentCost_before=capturetotalcostofCurrent_Costs();
			function.waitForElementIsVisible(Locator_fisrt_Row_three_dots, 70);
			String resourceid=function.table_Discard_Upsize_VMS_capture_resourceID_value_afterDiscardresource();
			System.out.println("Open Status Resource ID"+resourceid);
			function.clickaction(Locator_tab_Discarded);
			function.mouse_moveToElement(Locator_text_Total_VM_RECOMMENDATIONS);
			int totalcount_VMs_after=counttotalVMs();
			int totalcount_upsize_after=counttotalupsize();
			double totalcost_upsize_after=capturetotalcostofUpsize();
			double CurrentCost_after=capturetotalcostofCurrent_Costs();
			boolean check=function.verifyresourceidpresentintablepatametercolumnindexrowpath(resourceid,3);
			System.out.println(check+"  is status");
			// check for resource is added in table or not
			if(check==false) {
				Assert.assertFalse(check,"After Move To Open resource, respective 'Resource ID' get removed in discarded table");

			}else {
				function.captureScreen("respective_ResourceID_is_not_removed_from_discarded_table");
				Assert.fail("After Move To Open resource, respective 'Resource ID' get removed in discarded table");
			}

			// Check for total VM count
			if(totalcount_VMs_after==(totalcount_VMs_before+1)) {
				Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM RECOMMENDATIONS count should added by +1 after moved to open records");
			}else {
				function.captureScreen("Total_VM_Count_Not_Changed");
				Assert.fail("Total VM RECOMMENDATIONS count should added by +1 after moved to open records");
			}
			// Check for upsize saving count
			if(totalcount_upsize_after==(totalcount_upsize_before+1)) {
				Assert.assertNotEquals(totalcount_upsize_after, totalcount_upsize_before, "Upsize count should added by +1 after moved to open records");
			}else {
				function.captureScreen("Upsize_Count_Not_Changed");
				Assert.fail("Upsize count should added by +1 after moved to open records");
			}
			// Check for upsize saving cost
			if(totalcost_upsize_after>totalcost_upsize_before) {
				Assert.assertNotEquals(totalcost_upsize_after, totalcost_upsize_before, "Moved to open records cost is added in upsize saving cost");
			}else {
				function.captureScreen("Upsize_cost_Not_Changed");
				Assert.fail("Moved records cost is added in upsize saving cost");
			}

			// Check for current cost
			if(CurrentCost_after>CurrentCost_before) {
				Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Moved to open records cost is added in current cost");
			}else {
				function.captureScreen("Current_Cost_Not_Changed");
				Assert.fail("Moved records cost is added in current cost");
			}
		}else if(upsizerecord==false) {
			Assert.assertFalse(upsizerecord,"**********Upsize Record Not Exists in table*******");	
		}
	}
	
	public void Archive_discarded_upsize_record() throws TimeoutException, IOException {
		movearchivepage();
		int totalcount_VMs_before=counttotalVMs();
		int totalcount_upsize_before=counttotalupsize();
		double totalcost_upsize_before=capturetotalcostofUpsize();
		double CurrentCost_before=capturetotalcostofCurrent_Costs();
		moveactivepage();
		function.clickaction(Locator_tab_Discarded);
		function.waitForElementIsVisibleFluent(Locator_columnResourceID,400, 3);
		function.mouse_moveToElement(Locator_columnResourceID);
		boolean upsizerecord=function.checkupsizerecordexitsintable();		
		if (upsizerecord==true) {
			
			String resourceid=function.table_Discard_Upsize_VMS_capture_resourceID_value_afterarchiveresource();
			System.out.println("Open Status Resource ID"+resourceid);
			function.waitFor(3000);
			movearchivepage();
			switchtoupsidetab();
			totalrowsintable50();
			function.waitForElementIsVisibleFluent(Locator_columnResourceID,400, 3);
			function.mouse_moveToElement(Locator_columnResourceID);
			boolean check=function.verifyresourceidpresentintablepatametercolumnindex(resourceid,3);
			System.out.println(check+"  is status");
			function.mouse_moveToElement(Locator_text_Total_VM_RECOMMENDATIONS);
			int totalcount_VMs_after=counttotalVMs();
			int totalcount_upsize_after=counttotalupsize();
			double totalcost_upsize_after=capturetotalcostofUpsize();
			double CurrentCost_after=capturetotalcostofCurrent_Costs();
			
			// check for resource is added in table or not
					if(check==true) {
						Assert.assertTrue(check,"After archived discarded resource, respective 'Resource ID' get added in Archive upsize table");

					}else {
						function.captureScreen("respective_ResourceID_is_not_added_in_archive_table");
						Assert.fail("After archived discarded resource, respective 'Resource ID' should get added in Archive upsize table");
					}

					// Check for total VM count
					if(totalcount_VMs_after==(totalcount_VMs_before+1)) {
						Assert.assertNotEquals(totalcount_VMs_after, totalcount_VMs_before, "Total VM RECOMMENDATIONS count should added by +1 after archive upsize discarded records");
					}else {
						function.captureScreen("Total_VM_Count_Not_Changed");
						Assert.fail("Total VM RECOMMENDATIONS count should added by +1 after archive upsize discarded records");
					}
					// Check for upsize saving count
					if(totalcount_upsize_after==(totalcount_upsize_before+1)) {
						Assert.assertNotEquals(totalcount_upsize_after, totalcount_upsize_before, "Upsize count should added by +1 after archive upsize discarded records");
					}else {
						function.captureScreen("Upsize_Count_Not_Changed");
						Assert.fail("Upsize count should added by +1 after archive upsize discarded records");
					}
					// Check for upsize saving cost
					if(totalcost_upsize_after>totalcost_upsize_before) {
						Assert.assertNotEquals(totalcost_upsize_after, totalcost_upsize_before, "Moved to open records cost is added in upsize saving cost");
					}else {
						function.captureScreen("Upsize_cost_Not_Changed");
						Assert.fail("Archived records cost is added in upsize saving cost");
					}

					// Check for current cost
					if(CurrentCost_after>CurrentCost_before) {
						Assert.assertNotEquals(CurrentCost_after, CurrentCost_before, "Moved to open records cost is added in current cost");
					}else {
						function.captureScreen("Current_Cost_Not_Changed");
						Assert.fail("Archived records cost is added in current cost");
					}	
		}else if(upsizerecord==false) {
			Assert.assertFalse(upsizerecord,"**********Upsize Record Not Exists in table*******");
			
		}
	}
}
