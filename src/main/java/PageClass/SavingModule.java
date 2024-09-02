package PageClass;



import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import org.testng.Assert;
import Repository.locator_saving;
import Utilities.ReuseMethods;

public class SavingModule extends locator_saving {

	ReuseMethods function;

	public SavingModule(ReuseMethods function) {
		this.function = function;
	}
	 
	 public void addinfolog(String Message) {
		 function.addinfolog(Message);
	 }
	 
	 public void navigate_to_Saving_module() throws TimeoutException {
			function.mouse_moveToElement(Locator_MenuBar);
			function.clickaction(Locator_select_savingmodule);
			function.mouse_moveToElement(Locator_movetoheadersection);
			function.waitFor(3000);
			function.waitForElementIsVisibleFluent(Locator_text_TotalSavings, 400, 5);
			addinfolog("User navigate to Savings module");
		}
	    
	public void verify_savingpage() throws TimeoutException, IOException, InterruptedException { 
		function.deleteFile("C:\\Users\\BalkrushnaNandrajGol\\cloudeq-workspace\\MCD_Automation\\Downloads\\GraphData.csv"); 
		function.waitForElementPresence(Locator_textsaving);
	     function.waitForElementPresence(Locator_textsavingidleresource);
	     function.clickaction(Locator_threedotbutton);
	     function.clickaction(Locator_downloadCSVfile); 
	     List<String> costdata=function.capturecostgraph(Locator_Graph_Bar,Locator_Graph_tooltip,Locator_Graph_tooltip_Cost);
	     function.addinfologArray("Cost from graph",costdata);
	     String path=function.verifyfiledownloadedandgetpath("GraphData.csv");
	     addinfolog("File Path: "+path);
	     function.waitFor(5000);
	     Map<String, List<String>> columnData=function.readCsvData(path);
		List<String> costdatacsv=columnData.get("azure");
		function.addinfologArray("Cost from CSV file",costdatacsv);
	     boolean check=costdata.equals(costdatacsv);
		 if (check==true) {
				Assert.assertTrue(check, "CSV File data and Frontend Table Data is Match");
			}else {
				function.captureScreen("'Idle Resources Savings' CSV File data and Frontend Table Data not Match");
				Assert.fail("CSV File data and Frontend Table Data not Match");
			}
	       }

	 public  String countidleresource() throws TimeoutException {
			function.mouse_moveToElement(Locator_text_IdleResource);
			String count=function.capturetext(Locator_count_idelresource);
			return count;
		}
	 
	 public  String countrightsizing() throws TimeoutException {
			function.mouse_moveToElement(Locator_text_Rightsizing);
			String count=function.capturetext(Locator_count_rightsizing);
			return count;
		}
	 
	 public  String countsnapshots() throws TimeoutException {
			function.mouse_moveToElement(Locator_text_Snapshots);
			String count=function.capturetext(Locator_count_snapshots);
			return count;
		}
	 
	 public String cost_idleresource() throws TimeoutException {
			function.mouse_moveToElement(Locator_cost_idelresource);
			String cost=function.capturetext(Locator_get_costfrom_tooltip);
			String modifiecost= cost.replaceAll("[^0-9.]", "");
			return modifiecost;
		}
	 
	 public String cost_rightsizing() throws TimeoutException {
			function.mouse_moveToElement(Locator_cost_rightsizing);
			String cost=function.capturetext(Locator_get_costfrom_tooltip);
			String modifiecost= cost.replaceAll("[^0-9.]", "");
			return modifiecost;
		}
	 
	 public String cost_snapshots() throws TimeoutException {
			function.mouse_moveToElement(Locator_cost_snapshots);
			String cost=function.capturetext(Locator_get_costfrom_tooltip);
			String modifiecost= cost.replaceAll("[^0-9.]", "");
			return modifiecost;
		}
	 
	 public String cost_projectedsavings() throws TimeoutException {
			function.mouse_moveToElement(Locator_cost_ProjectedSavings);
			String cost=function.capturetext(Locator_get_costfrom_tooltip);
			String modifiecost= cost.replaceAll("[^0-9.]", "");
			return modifiecost;
		}
	 
	 public String cost_savingtilldate() throws TimeoutException {
			function.mouse_moveToElement(Locator_cost_savingtilldate);
			String cost=function.capturetext(Locator_get_costfrom_tooltip);
			String modifiecost= cost.replaceAll("[^0-9.]", "");
			return modifiecost;
		}
	 
	 public String cost_totalsaving() throws TimeoutException {
			function.mouse_moveToElement(Locator_cost_TotalSavings);
			String cost=function.capturetext(Locator_get_costfrom_tooltip_spe);
			String modifiecost= cost.replaceAll("[^0-9.]", "");
			return modifiecost;
		}
	 
	 public String cost_previousmonth() throws TimeoutException {
			function.mouse_moveToElement(Locator_cost_previousmonth);
			String cost=function.capturetext(Locator_get_costfrom_tooltip_spe);
			String modifiecost= cost.replaceAll("[^0-9.]", "");
			return modifiecost;
		}
	 
	 public String cost_thisyear() throws TimeoutException {
		 function.mouse_moveToElement(Locator_text_thisyear);	
		 function.mouse_moveToElement(Locator_cost_thisyear);
			String cost=function.capturetext(Locator_get_costfrom_tooltip_spe);
			String modifiecost= cost.replaceAll("[^0-9.]", "");
			return modifiecost;
		}
	 
	 public void comparetotalsavingwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
			String cost=cost_totalsaving();
			String countfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
			addinfolog("Total Savings Cost=> Frontend Cost is:"+cost+" & Database Cost is:"+countfromDB);
			Assert.assertEquals(cost,countfromDB,"Total Saving cost is match with Database");
		}
	 
	 public void compareprevioumonthwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
			String cost=cost_previousmonth();
			String countfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
			addinfolog("Previous Month Cost=> Frontend Cost is:"+cost+" & Database Cost is:"+countfromDB);
			Assert.assertEquals(cost,countfromDB,"Previous month cost is match with Database");
		}
	 
	 public void comparethisyearwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
			String cost=cost_thisyear();
			String countfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
			addinfolog("This Year Cost=> Frontend Cost is:"+cost+" & Database Cost is:"+countfromDB);
			Assert.assertEquals(cost,countfromDB,"This year cost is match with Database");
		}
	 
	 public void compareidleresourcewithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
			String cost=cost_idleresource();
			String countfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
			addinfolog("Idle Resource Cost=> Frontend Cost is:"+cost+" & Database Cost is:"+countfromDB);
			Assert.assertEquals(cost,countfromDB,"Idle Resource cost is match with Database");
		}
	 
	 public void comparerightsizinghwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
			String cost=cost_rightsizing();
			String countfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
			addinfolog("Right Sizing Cost=> Frontend Cost is:"+cost+" & Database Cost is:"+countfromDB);
			Assert.assertEquals(cost,countfromDB,"Rightsizing cost is match with Database");
		}
	 
	 public void comparesnapshotswithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
			String cost=cost_snapshots();
			String countfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
			addinfolog("Snapshots Cost=> Frontend Cost is:"+cost+" & Database Cost is:"+countfromDB);
			Assert.assertEquals(cost,countfromDB,"Snapshots cost is match with Database");
		}
	 
	 public void compareProjectedsavingwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
			String cost=cost_projectedsavings();
			String countfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
			addinfolog("Projected Savings Cost=> Frontend Cost is:"+cost+" & Database Cost is:"+countfromDB);
			Assert.assertEquals(cost,countfromDB,"Projected saving cost is match with Database");
		}
	 
	 public void comparesavingtilldatewithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
			String cost=cost_savingtilldate();
			String countfromDB= function.capturedatabsesinglecolumncostByIndex(sql,columnnumber);
			addinfolog("Saving Till Date Cost=> Frontend Cost is:"+cost+" & Database Cost is:"+countfromDB);
			Assert.assertEquals(cost,countfromDB,"Saving Till Date cost is match with Database");
		}
	 
	 public void countcompareidleresourcewithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
			String cost=countidleresource();
			String countfromDB= function.capturedatabsesinglecolumncountByIndex(sql,columnnumber);
			addinfolog("Idle Resource Count=> Frontend Count is:"+cost+" & Database Count is:"+countfromDB);
			Assert.assertEquals(cost,countfromDB,"Idle Resource Count is match with Database");
		}
	 
	 public void countcomparesnapshotswithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
			String cost=countsnapshots();
			String countfromDB= function.capturedatabsesinglecolumncountByIndex(sql,columnnumber);
			addinfolog("Snapshots Count=> Frontend Count is:"+cost+" & Database Count is:"+countfromDB);
			Assert.assertEquals(cost,countfromDB,"Snapshots Count is match with Database");
		}
	 
	 public void countcomparerightsizingwithdb(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException {
			String cost=countrightsizing();
			String countfromDB= function.capturedatabsesinglecolumncountByIndex(sql,columnnumber);
			addinfolog("Right Sizing Count=> Frontend Count is:"+cost+" & Database Count is:"+countfromDB);
			Assert.assertEquals(cost,countfromDB,"Rightsizing Count is match with Database");
		}


}
