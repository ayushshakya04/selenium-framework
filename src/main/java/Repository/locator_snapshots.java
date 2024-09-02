package Repository;

import org.openqa.selenium.By;

public class locator_snapshots {

	public static By Locator_MenuBar=By.xpath("//div[@class='sidebar-menu-container  pt-4']");

	public static By Locator_selectsnapshotmodule=By.xpath("//a//span[contains(text(),'Snapshots')]");
	
	public static By Locator_pagination_rowcount_dropdown=By.xpath("//button[@id='pagination_dropdown_item_button__NhLXn']");

	public static By Locator_select_50rows_in_table=By.xpath("//div[@class='dropdown-menu show']//a[contains(text(),'50')]");
	
	public static By Locator_columnstatus=By.xpath("//th[contains(text(),'Status')]");
	
	public static By Locator_button_switch=By.xpath("//button[@id='snapshots_snap_dropdown_header__B0UZ4']");

	public static By Locator_switchto_Active=By.xpath("//div[@class='dropdown-menu show']//button[contains(text(),'Active')]");

	public static By Locator_switchto_Archive=By.xpath("//div[@class='dropdown-menu show']//button[contains(text(),'Archive')]");

	public static By Locator_movetoheadersection=By.xpath("//div[@class='col-sm-10 col-md-10 col-10 text-right flex-header-menu justify-content-end']");

	public static By Locator_textsnapshots=By.xpath("//div[@class='snapshots_snapshots_main__f8VJ4']//span[contains(text(),'Snapshots')]");

	public static By Locator_get_costfrom_tooltip=By.xpath("//div[@class='tooltip-inner']");

	public static By Locator_count_totalsnapshots=By.xpath("//div[@class='snapshots_snapshots_main__f8VJ4']//span[@class='snapshots_snapshot_value__F5wS-']");

	public static By Locator_costarea_totalsnapshots=By.xpath("//div[@class='snapshots_snapshots_main__f8VJ4']//div//span[@class='snapshots_snap_cost_value__Z0Sqk']//span//span");

	public static By Locator_count_UNTAGGEDSNAPSHOTS=By.xpath("//div[@class='snapshots_untagged_snapshot_main__B4djQ'][1]//span[@class='snapshots_snapshot_value__F5wS-']");

	public static By Locator_costarea_UNTAGGEDSNAPSHOTS=By.xpath("//div[@class='snapshots_untagged_snapshot_main__B4djQ']//div//span[@class='snapshots_snap_cost_value__Z0Sqk']//span//span");

	public static By Locator_count_ORPHANEDSNAPSHOTS=By.xpath("//div[@class='snapshots_untagged_snapshot_main__B4djQ'][2]//span[@class='snapshots_snapshot_value__F5wS-']");

	public static By Locator_costarea_ORPHANEDSNAPSHOTS=By.xpath("//span[@class='snapshots_snap_cost_value__Z0Sqk snapshots_snapshot_orphaned_color__+JF1a']//span//span");

	public static By Locator_snapshotage_7days=By.xpath("//div[@class='snapshots_snapshot_age_main__w+-+b']//div[@class='snapshots_value_on_age__igIDc'][1]//span[@class='snapshots_values_cost__7M6zl']");
	
	public static By Locator_snapshotage_15days=By.xpath("//div[@class='snapshots_snapshot_age_main__w+-+b']//div[@class='snapshots_value_on_age__igIDc'][2]//span[@class='snapshots_values_cost__7M6zl']");
	
	public static By Locator_snapshotage_morethan30days=By.xpath("//div[@class='snapshots_snapshot_age_main__w+-+b']//div[@class='snapshots_value_on_age__igIDc'][3]//span[@class='snapshots_values_cost__7M6zl']");

	public static By Locator_checkbox_orphanedsnapshot=By.xpath("//div[@class='snapshots_filter_main_div__d2q7s']//div//div[1]//div//input[@class='form-check-input']");

}
