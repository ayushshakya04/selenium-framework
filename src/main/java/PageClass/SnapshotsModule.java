package PageClass;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.testng.Assert;

import Repository.locator_snapshots;
import Utilities.ReuseMethods;

public class SnapshotsModule extends locator_snapshots{

	ReuseMethods function;
	public SnapshotsModule(ReuseMethods function) {
		this.function=function;
	}
	
	public void addinfolog(String Message) {
		 function.addinfolog(Message);
	 }

	public void navigate_to_snapshot_module() throws TimeoutException {
		function.mouse_moveToElement(Locator_MenuBar);
		function.clickaction(Locator_selectsnapshotmodule);
		function.mouse_moveToElement(Locator_movetoheadersection);
		function.waitForElementIsVisibleFluent(Locator_textsnapshots, 400, 5);
	}
	
	public void movearchivepage() {
		function.clickbyactionclass(Locator_button_switch);
		function.clickbyactionclass(Locator_switchto_Archive);
		function.waitFor(4000);
		function.verifyDisplayed(Locator_textsnapshots);

	}
	public void moveactivepage() {
		function.clickbyactionclass(Locator_button_switch);
		function.clickbyactionclass(Locator_switchto_Active);
		function.waitFor(4000);
		function.verifyDisplayed(Locator_textsnapshots);
	}

	public void totalrowsintable50() {
		function.clickbyactionclass(Locator_pagination_rowcount_dropdown);
		function.clickbyactionclass(Locator_select_50rows_in_table);
		function.waitForElementIsVisibleFluent(Locator_columnstatus, 400, 3);
	}

	public  int counttotalsnapshots() throws TimeoutException {
		function.mouse_moveToElement(Locator_count_totalsnapshots);
		String count=function.capturetext(Locator_count_totalsnapshots);
		int intcount = Integer.parseInt(count);
		return intcount;
	}

	public double capturetotalcostofsnapshots() throws TimeoutException {
		function.mouse_moveToElement(Locator_costarea_totalsnapshots);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		double doublecost = Double.parseDouble(modifiecost);
		return doublecost ;
	}

	public  int counttotaluntaggedsnapshots() throws TimeoutException {
		function.mouse_moveToElement(Locator_count_UNTAGGEDSNAPSHOTS);
		String count=function.capturetext(Locator_count_UNTAGGEDSNAPSHOTS);
		int intcount = Integer.parseInt(count);
		return intcount;
	}

	public double capturetotalcostofuntaggedsnapshots() throws TimeoutException {
		function.mouse_moveToElement(Locator_costarea_UNTAGGEDSNAPSHOTS);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		double doublecost = Double.parseDouble(modifiecost);
		return doublecost ;
	}

	public  int counttotalORPHANEDsnapshots() throws TimeoutException {
		function.mouse_moveToElement(Locator_count_ORPHANEDSNAPSHOTS);
		String count=function.capturetext(Locator_count_ORPHANEDSNAPSHOTS);
		int intcount = Integer.parseInt(count);
		return intcount;
	}

	public double capturetotalcostofORPHANEDsnapshots() throws TimeoutException {
		function.mouse_moveToElement(Locator_costarea_ORPHANEDSNAPSHOTS);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_get_costfrom_tooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		double doublecost = Double.parseDouble(modifiecost);
		return doublecost ;
	}
	
	public  int counttotal7dayssnapshots() throws TimeoutException {
		function.mouse_moveToElement(Locator_snapshotage_7days);
		String count=function.capturetext(Locator_snapshotage_7days);
		int intcount = Integer.parseInt(count);
		return intcount;
	}
	
	public  int counttotal15dayssnapshots() throws TimeoutException {
		function.mouse_moveToElement(Locator_snapshotage_15days);
		String count=function.capturetext(Locator_snapshotage_15days);
		int intcount = Integer.parseInt(count);
		return intcount;
	}
	
	public  int counttotalmorethan31dayssnapshots() throws TimeoutException {
		function.mouse_moveToElement(Locator_snapshotage_morethan30days);
		String count=function.capturetext(Locator_snapshotage_morethan30days);
		int intcount = Integer.parseInt(count);
		return intcount;
	}
	
	public void comparetotal7dayssnapshotscountwithdb(String sql,int columnnumber) throws ClassNotFoundException, SQLException, TimeoutException {
		int count_snapshot=counttotal7dayssnapshots();
		String stringValue = String.valueOf(count_snapshot);
		String countsnapshotfromDB= function.capturedatabsesinglecolumncountByIndex(sql,columnnumber);
		addinfolog("Total 7 Days Snapshot Count=> Frontend Count is:"+stringValue+" & Database Count is:"+countsnapshotfromDB);
		Assert.assertEquals(stringValue,countsnapshotfromDB,"Total Count of 0-7 days snapshots is match with Database");
	}
	
	public void comparetotal15dayssnapshotscountwithdb(String sql,int columnnumber) throws ClassNotFoundException, SQLException, TimeoutException {
		int count_snapshot=counttotal15dayssnapshots();
		String stringValue = String.valueOf(count_snapshot);
		String countsnapshotfromDB= function.capturedatabsesinglecolumncountByIndex(sql,columnnumber);
		addinfolog("Total 15 Days Snapshot Count=> Frontend Count is:"+stringValue+" & Database Count is:"+countsnapshotfromDB);
		Assert.assertEquals(stringValue,countsnapshotfromDB,"Total Count of 8-15 days snapshots is match with Database");
	}
	
	public void comparetotalmorethan31dayssnapshotscountwithdb(String sql,int columnnumber) throws ClassNotFoundException, SQLException, TimeoutException {
		int count_snapshot=counttotalmorethan31dayssnapshots();
		String stringValue = String.valueOf(count_snapshot);
		String countsnapshotfromDB= function.capturedatabsesinglecolumncountByIndex(sql,columnnumber);
		addinfolog("Total 31 Days Snapshot Count=> Frontend Count is:"+stringValue+" & Database Count is:"+countsnapshotfromDB);
		Assert.assertEquals(stringValue,countsnapshotfromDB,"Total Count of more than 31 days snapshots is match with Database");
	}
	
	public void comparetotalsnapshotcountwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		int count_snapshot=counttotalsnapshots();
		String stringValue = String.valueOf(count_snapshot);
		String countsnapshotfromDB= function.capturedatabsesinglecolumncountByIndex(sql,columnnumber);
		addinfolog("Total Snapshot Count=> Frontend Count is:"+stringValue+" & Database Count is:"+countsnapshotfromDB);
		Assert.assertEquals(stringValue,countsnapshotfromDB,"Total Count of snapshots is match with Database");
	}

	public void comparetotalsnapshotcostwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		double totalcost_snapshot=capturetotalcostofsnapshots();
		String costsnapshotfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
		double stringValue = Double.parseDouble(costsnapshotfromDB);
		addinfolog("Total Snapshots Cost=> Frontend Cost is:"+totalcost_snapshot+" & Database Cost is:"+stringValue);
		Assert.assertEquals(totalcost_snapshot,stringValue,"Total Cost of snapshots is match with Database");
	}
	
	public void comparetotaluntaggedsnapshotcountwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		int count_snapshot=counttotaluntaggedsnapshots();
		String stringValue = String.valueOf(count_snapshot);
		String countsnapshotfromDB= function.capturedatabsesinglecolumncountByIndex(sql,columnnumber);
		addinfolog("Total Untagged Snapshot Count=> Frontend Count is:"+stringValue+" & Database Count is:"+countsnapshotfromDB);
		Assert.assertEquals(stringValue,countsnapshotfromDB,"Total Count of untagged snapshots is match with Database");
	}

	public void comparetotaluntaggedsnapshotcostwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		double totalcost_snapshot=capturetotalcostofuntaggedsnapshots();
		String costsnapshotfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
		double stringValue = Double.parseDouble(costsnapshotfromDB);
		addinfolog("Total Untagged Snapshots Cost=> Frontend Cost is:"+totalcost_snapshot+" & Database Cost is:"+stringValue);
		Assert.assertEquals(totalcost_snapshot,stringValue,"Total Cost of untagged snapshots is match with Database");
	}
	
	public void comparetotalORPHANEDsnapshotcountwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		int count_snapshot=counttotalORPHANEDsnapshots();
		String stringValue = String.valueOf(count_snapshot);
		String countsnapshotfromDB= function.capturedatabsesinglecolumncountByIndex(sql,columnnumber);
		addinfolog("Total Orphaned Snapshot Count=> Frontend Count is:"+stringValue+" & Database Count is:"+countsnapshotfromDB);
		Assert.assertEquals(stringValue,countsnapshotfromDB,"Total Count of orphaned snapshots is match with Database");
	}

	public void comparetotalORPHANEDsnapshotcostwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
		double totalcost_snapshot=capturetotalcostofORPHANEDsnapshots();
		String costsnapshotfromDB= function.capturedatabsesinglecolumncostByIndexdataformatter(sql,columnnumber);
		double stringValue = Double.parseDouble(costsnapshotfromDB);
		addinfolog("Total Orphaned Snapshots Cost=> Frontend Cost is:"+totalcost_snapshot+" & Database Cost is:"+stringValue);
		Assert.assertEquals(totalcost_snapshot,stringValue,"Total Cost of orphaned snapshots saving is match with Database");
	}
	
	public void acceptsnapshotfromactive() throws IOException, TimeoutException {
		int count_snapshotbefore=counttotalsnapshots();
		int count_untaggedsnapshotbefore=counttotaluntaggedsnapshots();
		double totalcost_snapshotbefore=capturetotalcostofsnapshots();
		double totalcost_untaggedsnapshotbefore=capturetotalcostofuntaggedsnapshots();
		totalrowsintable50();
		function.waitForElementIsVisibleFluent(Locator_columnstatus,400, 3);
		function.mouse_moveToElement(Locator_columnstatus);
		String resourceid=function.table_snapshot_active_accept_snapshot();
		System.out.println("Open Status Resource ID"+resourceid);
		function.waitFor(3000);
		boolean check=function.verifysnapshotidintablesnapshotcolumn(resourceid,4);
		System.out.println(check+"  is status");
		function.mouse_moveToElement(Locator_textsnapshots);
		int count_snapshotafter=counttotalsnapshots();
		int count_untaggedsnapshotafter=counttotaluntaggedsnapshots();
		double totalcost_snapshotafter=capturetotalcostofsnapshots();
		double totalcost_untaggedsnapshotafter=capturetotalcostofuntaggedsnapshots();	
		
		if(check==false) {
			Assert.assertFalse(check,"After accepting snapshot, respective 'Snapshot ID' get removed form table");

		}else {
			function.captureScreen("respective_SnapshotID_is_not_removed_form_table");
			Assert.assertFalse(check,"After accepting snapshot, respective 'Snapshot ID' get removed form table");

		}
		
		if(count_snapshotafter==(count_snapshotbefore+1)) {
			Assert.assertNotEquals(count_snapshotafter, count_snapshotbefore, "Total snapshots count should added by +1 after accepting record");
		}else {
			function.captureScreen("Snapshot_Count_Not_Changed");
			Assert.assertNotEquals(count_snapshotafter, count_snapshotbefore, "Total snapshots count should added by +1 after accepting record");
		}
/*
		if(count_untaggedsnapshotafter==(count_untaggedsnapshotbefore+1)) {
			Assert.assertNotEquals(count_untaggedsnapshotafter, count_untaggedsnapshotbefore, "Untagged snapshots count should added by +1 after accepting record");
		}else {
			function.captureScreen("Untagged_snapshot_Count_Not_Changed");
			Assert.assertNotEquals(count_untaggedsnapshotafter, count_untaggedsnapshotbefore, "Untagged snapshots count should added by +1 after accepting record");
		}
	
		if(totalcost_untaggedsnapshotafter>totalcost_untaggedsnapshotbefore) {
			Assert.assertNotEquals(totalcost_untaggedsnapshotafter, totalcost_untaggedsnapshotbefore, "Accepted records cost is added in untagged snapshots cost");
		}else {
			function.captureScreen("Untagged_snapshot_cost_Not_Changed");
			Assert.assertNotEquals(totalcost_untaggedsnapshotafter, totalcost_untaggedsnapshotbefore, "Accepted records cost is added in untagged snapshots cost");
		}
		*/
	
		if(totalcost_snapshotafter>totalcost_snapshotbefore) {
			Assert.assertNotEquals(totalcost_snapshotafter, totalcost_snapshotbefore, "Accepted records cost is added in snapshot cost");
		}else {
			function.captureScreen("Snapshot_Cost_Not_Changed");
			Assert.assertNotEquals(totalcost_snapshotafter, totalcost_snapshotbefore, "Accepted records cost is added in snapshot cost");
		}
	}
	
	public void unarchiving_any_record_of_from_Archive_page() throws TimeoutException, IOException {
		movearchivepage();
		int count_snapshotbefore=counttotalsnapshots();
		int count_untaggedsnapshotbefore=counttotaluntaggedsnapshots();
		int count_orphanedsnapshotbefore=counttotalORPHANEDsnapshots();
		double totalcost_snapshotbefore=capturetotalcostofsnapshots();
		double totalcost_untaggedsnapshotbefore=capturetotalcostofuntaggedsnapshots();
		double totalcost_orphanedsnapshotbefore=capturetotalcostofORPHANEDsnapshots();
		function.waitForElementIsVisibleFluent(Locator_columnstatus,400, 3);
		function.mouse_moveToElement(Locator_columnstatus);
		String resourceid=function.table_archive_Snapshot_capture_resourceID_value(4);
		function.waitFor(3000);
		boolean check=function.verifysnapshotidintablesnapshotcolumn(resourceid,4);
		function.clickbyactionclass(Locator_textsnapshots);
		function.waitFor(3000);
		int count_snapshotafter=counttotalsnapshots();
		int count_untaggedsnapshotafter=counttotaluntaggedsnapshots();
		int count_orphanedsnapshotafter=counttotalORPHANEDsnapshots();
		double totalcost_snapshotafter=capturetotalcostofsnapshots();
		double totalcost_untaggedsnapshotafter=capturetotalcostofuntaggedsnapshots();
		double totalcost_orphanedsnapshotafter=capturetotalcostofORPHANEDsnapshots();

		if(check==false) {
			Assert.assertFalse(check,"After unarchive snapshot, respective 'Snapshot ID' get removed form table");

		}else {
			function.captureScreen("respective_SnapshotID_is_not_removed_form_table");
			Assert.assertFalse(check,"After unarchive snapshot, respective 'Snapshot ID' get removed form table");

		}
		
		if(count_snapshotafter==(count_snapshotbefore-1)) {
			Assert.assertNotEquals(count_snapshotafter, count_snapshotbefore, "Total snapshots count should reduce by -1 after unarchive record");
		}else {
			function.captureScreen("Snapshot_Count_Not_Changed");
			Assert.assertNotEquals(count_snapshotafter, count_snapshotbefore, "Total snapshots count should reduce by -1 after unarchive record");
		}
/*
		if(count_untaggedsnapshotafter==(count_untaggedsnapshotbefore-1)) {
			Assert.assertNotEquals(count_untaggedsnapshotafter, count_untaggedsnapshotbefore, "Untagged snapshots count should reduce by -1 after unarchive record");
		}else {
			function.captureScreen("Untagged_snapshot_Count_Not_Changed");
			Assert.assertNotEquals(count_untaggedsnapshotafter, count_untaggedsnapshotbefore, "Untagged snapshots count should reduce by -1 after unarchive record");
		}
		// Check for disks count added with +1 
		if(totalcost_untaggedsnapshotafter<totalcost_untaggedsnapshotbefore) {
			Assert.assertNotEquals(totalcost_untaggedsnapshotafter, totalcost_untaggedsnapshotbefore, "unarchive records cost is reduce from Untagged snapshots cost");
		}else {
			function.captureScreen("Untagged_snapshot_cost_Not_Changed");
			Assert.assertNotEquals(totalcost_untaggedsnapshotafter, totalcost_untaggedsnapshotbefore, "unarchive records cost is reduce from Untagged snapshots cost");
		}
		*/
		// Check for Opportunity Missed cost
		if(totalcost_snapshotafter<totalcost_snapshotbefore) {
			Assert.assertNotEquals(totalcost_snapshotafter, totalcost_snapshotbefore, "unarchive records cost is reduce from total snapshot cost");
		}else {
			function.captureScreen("Snapshot_Cost_Not_Changed");
			Assert.assertNotEquals(totalcost_snapshotafter, totalcost_snapshotbefore, "unarchive records cost is reduce from total snapshot cost");
		}
	}
	
	public void archivesnapshotformactivepage() throws IOException, TimeoutException {
		movearchivepage();
		int count_snapshotbefore=counttotalsnapshots();
		int count_orphanedsnapshotbefore=counttotalORPHANEDsnapshots();
		double totalcost_snapshotbefore=capturetotalcostofsnapshots();
		double totalcost_orphanedsnapshotbefore=capturetotalcostofORPHANEDsnapshots();
		moveactivepage();
		totalrowsintable50();
		function.waitForElementIsVisibleFluent(Locator_columnstatus,400, 3);
		function.mouse_moveToElement(Locator_columnstatus);
		String resourceid=function.table_snapshot_active_archive_snapshot();
		function.waitFor(3000);
		movearchivepage();
		int count_snapshotafter=counttotalsnapshots();
		int count_orphanedsnapshotafter=counttotalORPHANEDsnapshots();
		double totalcost_snapshotafter=capturetotalcostofsnapshots();
		double totalcost_orphanedsnapshotafter=capturetotalcostofORPHANEDsnapshots();
		function.waitForElementIsVisibleFluent(Locator_columnstatus,400, 3);
		function.mouse_moveToElement(Locator_columnstatus);
		boolean check=function.verifysnapshotidintablesnapshotcolumn(resourceid,4);
		System.out.println(check+"  is status");
		// check for resource is moved form table or not
		if(check==true) {
			Assert.assertTrue(check, "Snapshot ID found in archive table");

		}else {
			function.captureScreen("respective_SnapshotID_is_not_found_form_table");
			Assert.assertTrue(check, "Snapshot ID found in archive table");

		}
		if(count_snapshotafter==(count_snapshotbefore+1)) {
			Assert.assertNotEquals(count_snapshotafter, count_snapshotbefore, "Total Snapshot count should added by +1 after archive record");
		}else {
			function.captureScreen("Snapshot_Count_Not_Changed");
			Assert.assertNotEquals(count_snapshotafter, count_snapshotbefore, "Total Snapshot count should added by +1 after archive record");
		}
		if(totalcost_snapshotafter>totalcost_snapshotbefore) {
			Assert.assertNotEquals(totalcost_snapshotafter, totalcost_snapshotbefore, "archive records cost is reduce from total snapshot cost");
		}else {
			function.captureScreen("Snapshot_cost_Not_Changed");
			Assert.assertNotEquals(totalcost_snapshotafter, totalcost_snapshotbefore, "archive records cost is reduce from total snapshotcost");
		}
	}
	
	public void archiveorphanedsnapshotactivepage() throws TimeoutException, IOException {
		int count_orphanedsnapshotbefore=counttotalORPHANEDsnapshots();
		double totalcost_orphanedsnapshotbefore=capturetotalcostofORPHANEDsnapshots();
		function.clickbyactionclass(Locator_checkbox_orphanedsnapshot);
		totalrowsintable50();
		function.waitForElementIsVisibleFluent(Locator_columnstatus,400, 3);
		function.mouse_moveToElement(Locator_columnstatus);
		String resourceid=function.table_snapshot_active_archive_snapshot();
		function.waitFor(3000);
		int count_orphanedsnapshotafter=counttotalORPHANEDsnapshots();
		double totalcost_orphanedsnapshotafter=capturetotalcostofORPHANEDsnapshots();
		function.waitForElementIsVisibleFluent(Locator_columnstatus,400, 3);
		function.mouse_moveToElement(Locator_columnstatus);
		boolean check=function.verifysnapshotidintablesnapshotcolumn(resourceid,4);
		System.out.println(check+"  is status");
		// check for resource is moved form table or not
		if(check==false) {
			Assert.assertFalse(check,"Snapshot ID not found in archive table");

		}else {
			function.captureScreen("respective_SnapshotID_is_not_removed_form_table");
			Assert.assertTrue(check, "Snapshot ID not found in archive table");
		}
		if(count_orphanedsnapshotafter==(count_orphanedsnapshotbefore-1)) {
			Assert.assertNotEquals(count_orphanedsnapshotafter, count_orphanedsnapshotbefore, "Orphaned snapshot count should reduce by -1 after archive record");
		}else {
			function.captureScreen("Orphaned_Snapshot_Count_Not_Changed");
			Assert.assertNotEquals(count_orphanedsnapshotafter, count_orphanedsnapshotbefore, "Orphaned snapshot count should reduce by -1 after archive record");
		}
		if(totalcost_orphanedsnapshotafter>totalcost_orphanedsnapshotbefore) {
			Assert.assertNotEquals(totalcost_orphanedsnapshotafter, totalcost_orphanedsnapshotbefore, "archive records cost is reduce from Orphaned snapshot cost");
		}else {
			function.captureScreen("Orphaned_Snapshot_cost_Not_Changed");
			Assert.assertNotEquals(totalcost_orphanedsnapshotafter, totalcost_orphanedsnapshotbefore, "archive records cost is reduce from Orphaned snapshotcost");
		}
	}
}
