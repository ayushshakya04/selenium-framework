package TestClass;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.Test;

import PageClass.SnapshotsModule;
import Utilities.ReadConfigFile;

public class SnapshotsTestClass extends BaseClass{
	
	SnapshotsModule snap = new SnapshotsModule(function);
	protected ReadConfigFile config=new ReadConfigFile();
	
	public String DBtotalcountcostactivefisrtsection=config.get_DB_Snapshot_Active_FisrSection_All();
	
	@Test
	public void At_Snapshots_page_verify_the_values_shown_at_first_section_of_the_Active_Snapshots_page() throws TimeoutException, ClassNotFoundException, SQLException {
		snap.addinfolog("Start of: At Snapshots page, verify the values shown at first section of the Active Snapshots page");
		snap.navigate_to_snapshot_module();
		snap.addinfolog("User navigate to Snapshots module");
		snap.comparetotalsnapshotcountwithdb(DBtotalcountcostactivefisrtsection, 1);;       // validate total count of snapshots
		snap.addinfolog("Total Count of snapshots are match");
		snap.comparetotalsnapshotcostwithdb(DBtotalcountcostactivefisrtsection, 2);;       // validate total count of snapshots
		snap.addinfolog("Total Cost of snapshots are match");
		snap.comparetotal7dayssnapshotscountwithdb(DBtotalcountcostactivefisrtsection, 3);;       // validate total count of 0-7 days snapshots
		snap.addinfolog("Total Count for 0-7 days snapshots are match");
		snap.comparetotal15dayssnapshotscountwithdb(DBtotalcountcostactivefisrtsection, 4);;       // validate total count of 8-15 days snapshots
		snap.addinfolog("Total Count for 8-15 days snapshots are match");
		snap.comparetotalmorethan31dayssnapshotscountwithdb(DBtotalcountcostactivefisrtsection, 5);;       // validate total count of more than 31 days snapshots
		snap.addinfolog("Total Count for more than 31 days snapshots are match");
		snap.comparetotaluntaggedsnapshotcountwithdb(DBtotalcountcostactivefisrtsection, 6);;       // validate total count of untagged snapshots
		snap.addinfolog("Total Count of untagged snapshots are match");
		snap.comparetotaluntaggedsnapshotcostwithdb(DBtotalcountcostactivefisrtsection, 7);;       // validate total count of untagged snapshots
		snap.addinfolog("Total Cost of untagged snapshots are match");
		snap.comparetotalORPHANEDsnapshotcountwithdb(DBtotalcountcostactivefisrtsection, 8);;       // validate total count of orphaned snapshots
		snap.addinfolog("Total Count of orphaned snapshots are match");
		snap.comparetotalORPHANEDsnapshotcostwithdb(DBtotalcountcostactivefisrtsection, 9);;       // validate total count of orphaned snapshots
		snap.addinfolog("Total Cost of orphaned snapshots are match");
	}
	
	@Test
	public void Verify_on_accepting_the_snapshot_SNAPSHOTS_count_and_cost_and_UNTAGGED_SNAPSHOTS_count_and_cost_is_increased() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		snap.addinfolog("Start of: Verify on accepting the snapshot SNAPSHOTS count and cost and UNTAGGED SNAPSHOTS count and cost is increased");
		snap.navigate_to_snapshot_module();
		snap.addinfolog("User navigate to Snapshots module");
		snap.acceptsnapshotfromactive();                         // accept snapshot and check count cost and check snapshot is removed form table
	    snap.addinfolog("After Accepting snapshot respective snapshot count and cost is added in untagged snapshots and snapshot id is removed form table");
	    snap.comparetotalsnapshotcountwithdb(DBtotalcountcostactivefisrtsection, 1);;       // validate total count of snapshots
		snap.addinfolog("Total Count of snapshots are match");
		snap.comparetotalsnapshotcostwithdb(DBtotalcountcostactivefisrtsection, 2);;       // validate total count of snapshots
		snap.addinfolog("Total Cost of snapshots are match");
		snap.comparetotaluntaggedsnapshotcountwithdb(DBtotalcountcostactivefisrtsection, 6);;       // validate total count of untagged snapshots
		snap.addinfolog("Total Count of untagged snapshots are match");
		snap.comparetotaluntaggedsnapshotcostwithdb(DBtotalcountcostactivefisrtsection, 7);;       // validate total count of untagged snapshots
		snap.addinfolog("Total Cost of untagged snapshots are match");
	}
	
	@Test
	public void Verify_the_on_unarchiving_any_record_of_from_Archive_page_it_is_being_moved_to_Active_page_of_Snapshots() throws TimeoutException, IOException {
		snap.addinfolog("Start of: Verify the on unarchiving any record of  from Archive page, it is being moved to Active page of Snapshots");
		snap.navigate_to_snapshot_module();
		snap.addinfolog("User navigate to Snapshots module");
		snap.unarchiving_any_record_of_from_Archive_page();
		snap.addinfolog("After unarchive record form archive page, respective record is removed form table and count-cost is reduce form total snapshot");
	}

	@Test
	public void At_Snapshots_page_verify_that_dismissed_snapshot_record_is_being_added_to_Archived_page() throws TimeoutException, IOException {
		snap.addinfolog("Start of: At Snapshots page, verify that dismissed snapshot record, is being added to Archived page");
		snap.navigate_to_snapshot_module();
		snap.addinfolog("User navigate to Snapshots module");
		snap.archivesnapshotformactivepage();
		snap.addinfolog("Archive snapshot id is added in archive page table and it's count and cost added in total snapshot count-cost");
	}
	
	@Test
	public void Verify_on_dismissing_the_Snapshot_savings_and_count_of_ORPHANED_SNAPSHOTS_should_be_decreased_in_active_page_of_Snapshots() throws TimeoutException, IOException, ClassNotFoundException, SQLException {
		snap.addinfolog("Start of: Verify on dismissing the Snapshot, savings and count of ORPHANED SNAPSHOTS should be decreased in active page of Snapshots");
		snap.navigate_to_snapshot_module();
		snap.addinfolog("User navigate to Snapshots module");
		snap.archiveorphanedsnapshotactivepage();
		snap.addinfolog("Archive snapshot id is added in archive page table and it's count and cost added in total snapshot count-cost");
		snap.comparetotalORPHANEDsnapshotcountwithdb(DBtotalcountcostactivefisrtsection, 8);;       // validate total count of orphaned snapshots
		snap.addinfolog("Total Count of orphaned snapshots are match");
		snap.comparetotalORPHANEDsnapshotcostwithdb(DBtotalcountcostactivefisrtsection, 9);;       // validate total count of orphaned snapshots
		snap.addinfolog("Total Cost of orphaned snapshots are match");
	}
}
