package PageClass;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import Repository.locator_dashborad;
import Utilities.ReuseMethods;

public class DashboardModule extends locator_dashborad {
	ReuseMethods function;

	public DashboardModule(ReuseMethods function) {
		this.function = function;
	}

	public void signoutprocess() {
		function.clickaction(Locator_Adminicon);
		function.clickaction(Locator_SignOut);
	}

	public void addinfolog(String Message) {
		function.addinfolog(Message);
	}

	public void Verify_fields_first_section() throws IOException {
		function.waitForElementIsVisibleFluent(Locator_TextTotalCost, 500, 15);
		boolean a=function.verifyDisplayed(Locator_CostInsights);     // Verify "Text: 'Cost Explorer' is display
		if (a==true) {
			Assert.assertTrue(a,"Text: 'Cost Insights' is display at dashboard module");
		}else
		{
			function.captureScreen("CostInsights_is_missing_at_dashboard_module");
			Assert.assertTrue(a,"Text: 'Cost Insights' is display at dashboard module");
		}

		boolean b=function.verifyDisplayed(Locator_TextTotalCost);          // verify "Text: 'Total Period Cost' is display
		if (b==true) {
			Assert.assertTrue(b,"Text: 'Total Period Cost' is display under cost explorer");
		}else
		{
			function.captureScreen("TotalPeriodCost_is_missing_at_dashboard_module");
			Assert.assertTrue(b,"Text: 'Total Period Cost' is display under cost explorer");
		}

		boolean c=function.verifyDisplayed(Locator_Textpreviousmonthcost);        // verify "Text: 'Previous Month' is display
		if (c==true) {
			Assert.assertTrue(c,"Text: 'Previous Month' is display under cost explorer");
		}else
		{
			function.captureScreen("PreviousMonth_is_missing_at_dashboard_module");
			Assert.assertTrue(c,"Text: 'Previous Month' is display under cost explorer");
		}

		boolean d=function.verifyDisplayed(Locator_TextThisYear);                  // verify "Text: 'This Year' is display
		if (d==true) {
			Assert.assertTrue(d,"Text: 'This Year' is display under cost explorer");
		}else
		{
			function.captureScreen("ThisYear_is_missing_at_dashboard_module");
			Assert.assertTrue(d,"Text: 'This Year' is display under cost explorer");
		}
	}

	public void compare_total_Period_cost(String sql,String columnname) throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		function.mouse_moveToElement(Locator_TotalCost);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_getcostfromtooltip);
		String totalcostfinops= cost.replaceAll("[^0-9.]", "");
		String totalCost6daysfromDB=function.capturedatabsesinglecolumnvalue(sql, columnname);
		addinfolog("Total Period Cost=> Frontend Cost is:"+totalcostfinops+" & Database Cost is:"+totalCost6daysfromDB);
		if(totalcostfinops.equals(totalCost6daysfromDB)) {
			Assert.assertEquals(totalcostfinops, totalCost6daysfromDB,"Total Period Cost for 6 days is match"); 
		}else {
			function.captureScreen("Total Period Cost for 6 days is not match form FinOps MCD to Databas");
			Assert.assertEquals(totalcostfinops, totalCost6daysfromDB,"Total Period Cost for 6 days is match"); 
		}
	}

	public void compare_previous_month_cost(String sql,String columnname) throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		function.mouse_moveToElement(Locator_previousmonthcost);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_getcostfromtooltip);
		String previousmonthcostfinops= cost.replaceAll("[^0-9.]", "");
		String previousmonthCostfromDB=function.capturedatabsesinglecolumnvalue(sql, columnname);
		addinfolog("Previous Month Cost=> Frontend Cost is:"+previousmonthcostfinops+" & Database Cost is:"+previousmonthCostfromDB);
		if(previousmonthcostfinops.equals(previousmonthCostfromDB)) {
			Assert.assertEquals(previousmonthcostfinops, previousmonthCostfromDB,"Previous Month cost is match"); 
		}else {
			function.captureScreen("Previous Month cost is not match form FinOps MCD to Databas");
			Assert.assertEquals(previousmonthcostfinops, previousmonthCostfromDB,"Previous Month cost is match"); 
		}
	}

	public void compare_this_year_cost(String sql,String columnname) throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		function.mouse_moveToElement(Locator_ThisYearcost);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_getcostfromtooltip);
		String thisyearcostfinops= cost.replaceAll("[^0-9.]", "");
		String thisyearCostfromDB=function.capturedatabsesinglecolumnvalue(sql, columnname);
		addinfolog("This Year Cost=> Frontend Cost is:"+thisyearcostfinops+" & Database Cost is:"+thisyearCostfromDB);
		if(thisyearcostfinops.equals(thisyearCostfromDB)) {
			Assert.assertEquals(thisyearcostfinops, thisyearCostfromDB,"This year cost is match"); 
		}else {
			function.captureScreen("This year cost not match form FinOps MCD to Databas");
			Assert.assertEquals(thisyearcostfinops, thisyearCostfromDB,"This year cost is match"); 
		}
	}

	public void compare_savingsavialble_cost(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		function.waitForElementIsVisibleFluent(Locator_savingavailable_costarea, 50, 15);
		function.mouse_moveToElement(Locator_savingavailable_costarea);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_getcostfromtooltip);
		String savingavailablefinops= cost.replaceAll("[^0-9.]", "");
		String savingavailablefromDB=function.capturedatabsesinglecolumncostByIndex(sql, columnnumber);
		addinfolog("Savings Available Cost=> Frontend Cost is:"+savingavailablefinops+" & Database Cost is:"+savingavailablefromDB);
		if(savingavailablefinops.equals(savingavailablefromDB)) {
			Assert.assertEquals(savingavailablefinops, savingavailablefromDB,"Savings Availbale cost is match"); 
		}else {
			function.captureScreen("Available Saving Cost is not match form FinOps MCD to Databas");
			Assert.assertEquals(savingavailablefinops, savingavailablefromDB,"Savings Availbale cost is match"); 
		}
	}

	public void compare_savingtilldate_cost(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		function.waitForElementIsVisibleFluent(Locator_savingavailable_costarea, 500, 15);
		function.mouse_moveToElement(Locator_savingtilldate_costarea);
		function.waitFor(2000);
		String cost=function.capturetext(Locator_getcostfromtooltip);
		String savingtilldatefinops= cost.replaceAll("[^0-9.]", "");
		String savingtilldatefromDB=function.capturedatabsesinglecolumncostByIndex(sql, columnnumber);
		addinfolog("Saving Till Date Cost=> Frontend Cost is:"+savingtilldatefinops+" & Database Cost is:"+savingtilldatefromDB);
		if(savingtilldatefinops.equals(savingtilldatefromDB)) {
			Assert.assertEquals(savingtilldatefinops, savingtilldatefromDB,"Saving Till date cost is match"); 
		}else {
			function.captureScreen("Saving till date Cost is not match form FinOps MCD to Databas");
			Assert.assertEquals(savingtilldatefinops, savingtilldatefromDB,"Saving Till date cost is match"); 
		}
	}

	public void compare_idleresource_count(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		function.mouse_moveToElement(Locator_idleresource_count);
		function.waitFor(2000);
		String count=function.capturetext(Locator_idleresource_count);
		String idleresourcecountfinops= count.replaceAll("[^0-9.]", "");
		String idleresourcecountfromDB=function.capturedatabsesinglecolumncountByIndex(sql, columnnumber);
		addinfolog("Idle Resource Count=> Frontend Count is:"+idleresourcecountfinops+" & Database Count is:"+idleresourcecountfromDB);
		if(idleresourcecountfinops.equals(idleresourcecountfromDB)) {
			Assert.assertEquals(idleresourcecountfinops, idleresourcecountfromDB,"Idle Resource count is match"); 
		}else {
			function.captureScreen("Idle Resource cont is not match form FinOps MCD to Databas");
			Assert.assertEquals(idleresourcecountfinops, idleresourcecountfromDB,"Idle Resource count is match"); 
		}
	}

	public void compare_rightsizing_count(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		function.mouse_moveToElement(Locator_rightsizing_count);
		function.waitFor(2000);
		String count=function.capturetext(Locator_rightsizing_count);
		String rightsizingcountfinops= count.replaceAll("[^0-9.]", "");
		String rightsizingcountfromDB=function.capturedatabsesinglecolumncountByIndex(sql, columnnumber);
		addinfolog("Right Sizing Count=> Frontend Count is:"+rightsizingcountfinops+" & Database Count is:"+rightsizingcountfromDB);
		if(rightsizingcountfinops.equals(rightsizingcountfromDB)) {
			Assert.assertEquals(rightsizingcountfinops, rightsizingcountfromDB,"Right Sizing count is match"); 
		}else {
			function.captureScreen("Right sizing count is not match form FinOps MCD to Databas");
			Assert.assertEquals(rightsizingcountfinops, rightsizingcountfromDB,"Right Sizing count is match"); 
		}
	}

	public void compare_snpashot_count(String sql,int columnnumber) throws TimeoutException, ClassNotFoundException, SQLException, IOException {
		function.mouse_moveToElement(Locator_snapshot_count);
		function.waitFor(2000);
		String count=function.capturetext(Locator_snapshot_count);
		String snpashotcountfinops= count.replaceAll("[^0-9.]", "");
		String snpashotcountfromDB=function.capturedatabsesinglecolumncountByIndex(sql, columnnumber);
		addinfolog("Snapshots Count=> Frontend Count is:"+snpashotcountfinops+" & Database Count is:"+snpashotcountfromDB);
		if(snpashotcountfinops.equals(snpashotcountfromDB)) {
			Assert.assertEquals(snpashotcountfinops, snpashotcountfromDB,"Snapshot count is match"); 
		}else {
			function.captureScreen("Snapshot count is not match form FinOps MCD to Databas");
			Assert.assertEquals(snpashotcountfinops,snpashotcountfromDB,"Snapshot count is match"); 
		}
	}

	public String validatecostofothers() throws TimeoutException {
		function.mouse_moveToElement(Locator_othercostarea);
		function.waitFor(1000);
		String cost=function.capturetext(Locator_getcostfromtooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		return modifiecost;
	}

	public String validatecostofcompute() throws TimeoutException {
		function.mouse_moveToElement(Locator_Computecostarea);
		function.waitFor(1000);
		String cost=function.capturetext(Locator_getcostfromtooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		return modifiecost;
	}

	public String validatecostofserverless() throws TimeoutException {
		function.mouse_moveToElement(Locator_serverlesscostarea);
		function.waitFor(1000);
		String cost=function.capturetext(Locator_getcostfromtooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		return modifiecost;
	}

	public String validatecostofnetwrok() throws TimeoutException {
		function.mouse_moveToElement(Locator_networkcostarea);
		function.waitFor(1000);
		String cost=function.capturetext(Locator_getcostfromtooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		return modifiecost;
	}

	public String validatecostofstorage() throws TimeoutException {
		function.mouse_moveToElement(Locator_storagecostarea);
		function.waitFor(1000);
		String cost=function.capturetext(Locator_getcostfromtooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		return modifiecost;
	}

	public String validatecostofdatabse() throws TimeoutException {
		function.mouse_moveToElement(Locator_databsecostarea);
		function.waitFor(1000);
		String cost=function.capturetext(Locator_getcostfromtooltip);
		String modifiecost= cost.replaceAll("[^0-9.]", "");
		return modifiecost;
	}

	public void capture_services_type_cost(String sql, int index1, int index2) throws ClassNotFoundException, TimeoutException {
		String finalcost = null;
		List<String> service = new ArrayList<>();
		List<String> cost = new ArrayList<>();

		String compute = function.capitalizeFirstLetter(function.capturetext(Locator_TextCompute));
		String computecost = validatecostofcompute();
		service.add(compute);
		cost.add(computecost);

		// String
		// database=function.capitalizeFirstLetter(function.capturetext(Locator_TextDatabase));
		String databasecost = validatecostofdatabse();
		String database = "Databases";
		service.add(database);
		cost.add(databasecost);

		String netwrok = function.capitalizeFirstLetter(function.capturetext(Locator_TextNetwork));
		String networkcost = validatecostofnetwrok();
		service.add(netwrok);
		cost.add(networkcost);

		// String
		// others=function.capitalizeFirstLetter(function.capturetext(Locator_TextOthers));
		String otherscost = validatecostofothers();
		String others = "Other";
		service.add(others);
		cost.add(otherscost);

		String serverless = function.capitalizeFirstLetter(function.capturetext(Locator_TextServerless));
		String serverlesscost = validatecostofserverless();
		service.add(serverless);
		cost.add(serverlesscost);

		String storage = function.capitalizeFirstLetter(function.capturetext(Locator_TextStorage));
		String storagecost = validatecostofstorage();
		service.add(storage);
		cost.add(storagecost);
		List<String> servicesInDatabase = new ArrayList<>();
		List<String> costsInDatabase = new ArrayList<>();
		try {

			ReuseMethods db = new ReuseMethods();
			ResultSet data = db.getdata(sql);
			System.out.println(data);

			while (data.next()) {
				String dbservice = data.getString(index1);
				String dbSaving = data.getString(index2);

				if (dbSaving == null) {
					finalcost = "0.00";
				} else {
					dbSaving = dbSaving.replaceAll("[^0-9 .]", "");
					finalcost = function.roundingDBStringValue(dbSaving);
				}
				servicesInDatabase.add(dbservice);
				costsInDatabase.add(finalcost);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//download Daily Costs & Usage CSV file 

	public void download_Daily_Costs_and_Usage_CSV_file() throws TimeoutException, InterruptedException, IOException{

		function.deleteFile("C:\\Users\\BalkrushnaNandrajGol\\cloudeq-workspace\\MCD_Automation\\Downloads\\GraphData.csv");
		function.waitForElementPresence(Locator_textGranuality);
		function.clickaction(Locator_clickGranularityfield);
		function.clickaction(Locator_clickGranualityValue);
		function.waitForElementIsVisibleFluent(Locator_textDisplaydimension, 100,30);
		function.clickaction(Locator_clickThreedotbutton);
		function.waitForElementPresence(Locator_clickDownloadCSV);
		function.clickaction(Locator_clickDownloadCSV);
		function.waitFor(5000);
		String path=function.verifyfiledownloaded("GraphData.csv");
		function.waitFor(10000);
		Map<String, List<String>> columnData=function.readCsvData(path);
		List<String> costdata=function.capturecostformtable(Table_Column_Locator);
		List<String> costdatacsv=columnData.get("Dimensions");
		List<String> actualcostdatacsv=function.convertedarreycostdata(costdatacsv);
		boolean check=costdata.equals(actualcostdatacsv);
		System.out.println(costdata+"=="+actualcostdatacsv);
		if (check==true) {
			Assert.assertTrue(check, "CSV File data and Frontend Table Data is Match");
		}else {
			function.captureScreen("'Cost By Dimensions' CSV File data and Frontend Table Data not Match");
			Assert.fail("CSV File data and Frontend Table Data not Match");
		}

	}


	public String MeterValueFromUI() throws InterruptedException, TimeoutException {

		function.clickaction(Locator_DashBoard_Select_Dimention);
		function.waitFor(2000);
		function.clickaction(Locator_DashBoard_Select_Meter);
		function.waitFor(2000);
		WebElement AddFilter = function.getElement(Locator_addFilter);
		function.waitFor(2000);
		function.scrollDown(200);
		function.waitFor(2000);
		AddFilter.click();
		function.clickaction(Locator_ClickMeter);
		function.mouse_moveToElement(Locator_value);
		function.clickaction(Locator_value);
		function.waitFor(2000);
		function.waitForElementToBeClickable(Locator_selectAll);
		function.clickaction(Locator_selectAll);
		function.clickaction(ClickOnDashPage);
		String text = function.capturetext(MeterValuePlusSubscription);
		// Remove the leading "+ " and convert the remaining part to an integer
		int currentValue = Integer.parseInt(text.replace("+", "").trim());
		// Increment the value
		int newValue = currentValue + 1;
		// Convert the incremented value back to a string
		String newText = String.valueOf(newValue);
		System.out.println("New text: " + newText); // Output: New text: 465
		return newText;
	}

	public String MeterValueFromDB(String sql) throws ClassNotFoundException, SQLException {
		return function.capturedatabsesinglecolumncostByIndex(sql, 1);
	}

	// create custom widget

	public void customWidget_form_fill() throws InterruptedException, TimeoutException, IOException {
		function.clickaction(Locator_CustomWidgetButtom);
		// function.waitForElementIsVisibleFluent(Locator_widgetText, 500, 15);
		function.clickaction(Locator_buttonAddNewWidget);
		function.verifyDisplayed(Locator_headerNewCustomWidgetWindow);
		String title = function.generatetitle().trim();
		function.entertextbysendkeys(Locator_EnterWidgetTilte, title);
		function.entertextbysendkeys(Locator_Entershowprevious, "31");
		function.clickaction(Locator_clickgraphtypefield);
		function.clickaction(Locator_clickgrapghtypevalue);
		function.clickaction(Locator_clickDimensionsfield);
		function.clickaction(Locator_clickDimensionsvalue);
		function.clickaction(Locator_clickselectfilterfield);
		function.clickaction(Locator_clickselectfiltervalue);
		function.clickaction(Locator_clickchoosevaluebutton);
		function.clickaction(Locator_clickselectfiltersubvalue);
		function.clickaction(Locator_clickselecttagsfield);
		function.clickaction(Locator_clickselecttag);
		function.clickaction(Locator_clickselecttagvalue);
		function.clickaction(Locator_clickslecttagfiltergroupvalue);
		function.clickaction(Locator_clicksavebutton);
		function.refreshpage();
		function.waitForElementPresence(Locator_CustomWidgetButtom);
		function.clickaction(Locator_CustomWidgetButtom);

		try {
			function.waitForElementIsVisible(Locator_graphbox, 70);
		} catch (WebDriverException e) {
			// Handle WebDriverException
			System.err.println("WebDriverException occurred: " + e.getMessage());
		}
		/*
		 * List<WebElement> elements = function.getElements(Locator_widgetText);
		 * System.out.println(elements.size()); // text is presenet or not boolean
		 * isTextFound = false; String elementText = "";
		 * 
		 * // Iterate the list of elements to find the matching text for (WebElement
		 * element : elements) { elementText = element.getText();
		 * System.out.println("Text from the web element: " + elementText);
		 * 
		 * if (title.equals(elementText)) { isTextFound = true; break; } }
		 */
		boolean status = function.titleispresent(title, Locator_widgetText);

		if (status == true) {
			Assert.assertTrue(status, "The title is match from actual text");
		} else {
			function.captureScreen("The title is not match form actual text");
			Assert.fail("Text not found in any of the elements");
		}
	}

	//download Custom widget

	public void downloadcustomwidget() throws TimeoutException, IOException, InterruptedException {
		function.clickaction(Locator_CustomWidgetButtom);
		function.waitForElementIsVisibleFluent(Locator_widgetText, 700, 100);
		String widgettitle=function.capturetext(Locator_Text_firstCustomWidget_Card);
		String filename="widget-data-"+widgettitle+".csv";
		function.clickaction(Locator_clickthreedot);
		function.clickaction(Locator_Option_Bar_Graph);
		List<String> costdata=function.capturecostgraph(Locator_Graph_Bar,Locator_Graph_tooltip,Locator_Graph_tooltip_Cost);
		function.addinfologArray("Frontend Data is: ",costdata);
		function.clickaction(Locator_clickthreedot);
		function.clickaction(Locator_clickdownloadcsvbutton);
		function.waitFor(5000);
		String path=function.verifyfiledownloaded(filename);
		function.waitFor(5000);
		Map<String, List<String>> columnData=function.readCsvData(path);
		List<String> costdatacsv=columnData.get("Basic User");
		function.addinfologArray("CSV File Data is: ",costdatacsv);
		boolean check=costdata.equals(costdatacsv);
		if (check==true) {
			Assert.assertTrue(check, "CSV File data and Frontend Table Data is Match");
		}else {
			function.captureScreen("'Cost of Custom Widgets' CSV File data and Frontend Table Data not Match");
			Assert.fail("CSV File data and Frontend Table Data not Match");
		}

	}
}
