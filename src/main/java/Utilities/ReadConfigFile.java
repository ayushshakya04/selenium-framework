package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigFile {
	
	
	  Properties pro;
		public  ReadConfigFile(){
			File src=new File("./testdata/config.properties");
			try {
				FileInputStream fis= new FileInputStream(src);
						pro=new Properties();
				pro.load(fis);
				
			}catch (Exception e) {
				System.out.println("exception is"+e.getMessage());
			}
		}
		
		public String getapplicationurl() {
			
			String URL=pro.getProperty("testdata_UrlMCD");
			return URL;
		}
		
	public String getemailid() {
			
			String emailid=pro.getProperty("testdata_Emailid");
			return emailid;
		}
	
	public String getpassword() {
		
		String password=pro.getProperty("testdata_password");
		return password;
	}
	
	public String get_DB_totalcost_6days() {
		
		String dbtotalcost=pro.getProperty("SQL_Dashboard_TotalPeriodCost_6days");
		return dbtotalcost;
	}
	
public String get_DB_totalcost_columnname() {
		
		String DB_totalcost_columnname=pro.getProperty("SQL_Dashboard_TotalPeriodCost_6days_columnname");
		return DB_totalcost_columnname;
	}
	
	public String get_DB_previousmonth() {
		
		String dbtotalcost=pro.getProperty("SQL_Dashboard_PreviousMonthCost");
		return dbtotalcost;
	}

	public String get_DB_thisyear() {
		
		String dbtotalcost=pro.getProperty("SQL_Dashboard_ThisYearCost");
		return dbtotalcost;
	}
public String get_DB_SavingsAvailable() {
		
		String DB_SavingsAvailable=pro.getProperty("SQL_Dashboard_SavingsAvailable");
		return DB_SavingsAvailable;
	}
public String get_DB_IdleResourceCount() {
	
	String dbIdleResourceCount=pro.getProperty("SQL_Dashboard_IdleResourceCount");
	return dbIdleResourceCount;
}
public String get_DB_rightsizing_count() {
	
	String dbrightsizing_count=pro.getProperty("SQL_Dashboard_rightsizing_count");
	return dbrightsizing_count;
}
public String get_DB_snapshotcount() {
	
	String dbsnapshotcount=pro.getProperty("SQL_Dashboard_snapshotcount");
	return dbsnapshotcount;
}
public String get_DB_savingtilldate() {
	
	String dbsavingtilldate=pro.getProperty("SQL_Dashboard_savingtilldate");
	return dbsavingtilldate;
}
public String get_DB_COSTSBYSERVICES() {
	
	String dbCOSTSBYSERVICES=pro.getProperty("SQL_Dashboard_COSTSBYSERVICES");
	return dbCOSTSBYSERVICES;
}

	
    public String get_DB_Totalcost_idleresource() {
	    String DB_Totalcountcost_idleresource=pro.getProperty("SQL_IdleResource_Total_IdleResource_Cost");
		return DB_Totalcountcost_idleresource;
	}
    public String get_DB_Totalcount_idleresource() {
	    String DB_Totalcountcost_idleresource=pro.getProperty("SQL_IdleResource_Total_IdleResource_Count");
		return DB_Totalcountcost_idleresource;
	}
    
public String get_DB_columncount_idleresource() {
	
	String DB_columncount_idleresource=pro.getProperty("SQL_IdleResource_Total_IdleResource_Count_columnname");
	return DB_columncount_idleresource;
}

public String get_DB_columncost_idleresource() {
	
	String DB_columncost_idleresource=pro.getProperty("SQL_IdleResource_Total_IdleResourceCost_columnname");
	return DB_columncost_idleresource;
}
public String get_DB_Totalcost_IdelResource_availableSavings() {
    String DB_Totalcountcost_idleresource=pro.getProperty("SQL_IdleResource_AvailableSavings_Cost");
	return DB_Totalcountcost_idleresource;
}
public String get_DB_Totalcost_idleresource_savingtilldate() {
    String DB_Totalcountcost_idleresource=pro.getProperty("SQL_IdleResource_SavingstillDate_Cost");
	return DB_Totalcountcost_idleresource;
}

public String get_DB_Totalcountcost_idleresource_resourcetype() {
    String DB_Totalcountcost_idleresource=pro.getProperty("SQL_IdleResource_ResourceType_Cost_Count");
	return DB_Totalcountcost_idleresource;
}

public String get_DB_columncountcost_idleresourcetypes() {
	
	String DB_columncost_idleresource=pro.getProperty("SQL_IdleResource_ResourceType_Cost_Count_columnname");
	return DB_columncost_idleresource;
}

public String get_DB_Totalcountcost_graph7days_elasticips() {

	String DB_Totalcountcost_graph7days_elasticips=pro.getProperty("SQL_IdleResource_Graph_7days_Cost_Count_ElasticIps");
	return DB_Totalcountcost_graph7days_elasticips;
}
public String get_DB_graphIdle_count_columnname() {
	String DB_graphIdle_count_columnname=pro.getProperty("SQL_IdleResource_Graph_Count_ElasticIp_columnname");
	return DB_graphIdle_count_columnname;
	
}
public String get_DB_graphIdle_cost_columnname() {
	String DB_graphIdle_cost_columnname=pro.getProperty("SQL_IdleResource_Graph_Cost_ElasticIp_columnname");
	return DB_graphIdle_cost_columnname;
	
}
public String get_DB_Totalcountcost_graphmorethan32days_VMs() {

	String DB_Totalcountcost_graphmorethan32days_VMs=pro.getProperty("SQL_IdleResource_Graph_morethan31days_Cost_Count_VMs");
	return DB_Totalcountcost_graphmorethan32days_VMs;
}
public String get_DB_alltypesof_costcount_VMS_Active_firstsection() {
	String DB_alltypesof_costcount_VMS_firstsection=pro.getProperty("SQL_VM_RECOMMENDATIONS_Active_Frist_section_ALLtypes_Cost_Count");
	return DB_alltypesof_costcount_VMS_firstsection;
}
public String get_DB_alltypesof_costcount_VMS_Archive_firstsection() {
	String DB_alltypesof_costcount_VMS_firstsection=pro.getProperty("SQL_VM_RECOMMENDATIONS_Archive_Frist_section_ALLtypes_Cost_Count");
	return DB_alltypesof_costcount_VMS_firstsection;
}

public String get_DB_alltypesof_costcount_VMS_Active_Upsize() {
	String DB_alltypesof_costcount_VMS_firstsectionActive_Upsize=pro.getProperty("SQL_VM_RECOMMENDATIONS_Active_Upsize_ALLtypes_Cost_Count");
	return DB_alltypesof_costcount_VMS_firstsectionActive_Upsize;
}
public String get_DB_alltypesof_costcount_VMS_Archive_Upsize() {
	String DB_alltypesof_costcount_VMS_Archive_Upsize=pro.getProperty("SQL_VM_RECOMMENDATIONS_Archive_Upsize_ALLtypes_Cost_Count");
	return DB_alltypesof_costcount_VMS_Archive_Upsize;
}

public String get_DB_MeterValue() {
	String DB_Meter_Value =pro.getProperty("SQL_Dashboard_MeterValue");
	return DB_Meter_Value;
}

public String get_DB_Snapshot_Active_FisrSection_All() {
	String DB_Snapshot_Active_FisrSection_All=pro.getProperty("SQL_Snapshot_Active_FisrSection_All");
	return DB_Snapshot_Active_FisrSection_All;
}
public String get_DB_Savings_TotalSavings() {
	String DB_Savings_TotalSavings=pro.getProperty("SQL_Saving_Cost_TotalSavings");
	return DB_Savings_TotalSavings;
}
public String get_DB_Savings_PreviousMonth() {
	String DB_Savings_PreviousMonth=pro.getProperty("SQL_Saving_Cost_PreviousMonth");
	return DB_Savings_PreviousMonth;
}
public String get_DB_Savings_ThisYear() {
	String DB_Savings_ThisYear=pro.getProperty("SQL_Saving_Cost_ThisYear");
	return DB_Savings_ThisYear;
}
public String get_DB_Savings_ProjectedSavings() {
	String DB_Savings_ProjectedSavings=pro.getProperty("SQL_Saving_Cost_ProjectedSavings");
	return DB_Savings_ProjectedSavings;
}
public String get_DB_Savings_SavingTillDate() {
	String DB_Savings_SavingTillDate=pro.getProperty("SQL_Saving_Cost_SavingTillDate");
	return DB_Savings_SavingTillDate;
}
public String get_DB_Savings_IdleResource() {
	String DB_Savings_IdleResource=pro.getProperty("SQL_Saving_Cost_IdleResource");
	return DB_Savings_IdleResource;
}
public String get_DB_Savings_Rightsizing() {
	String DB_Savings_Rightsizing=pro.getProperty("SQL_Saving_Cost_Rightsizing");
	return DB_Savings_Rightsizing;
}
public String get_DB_Savings_Snapshots() {
	String DB_Savings_Snapshots=pro.getProperty("SQL_Saving_Cost_Snapshots");
	return DB_Savings_Snapshots;
}
public String get_DB_IdleResource_Status() {
	String DB_IdleResource_Status=pro.getProperty("SQL_IdleREsource_Status");
	return DB_IdleResource_Status;
}


}
