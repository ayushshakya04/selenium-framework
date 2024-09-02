package TestClass;


import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import PageClass.DashboardModule;
import PageClass.SignInPage;
import Utilities.ReadConfigFile;
import Utilities.ReuseMethods;

public class BaseClass{

	
	protected ReuseMethods function = new ReuseMethods();	
	protected SignInPage sign = new SignInPage(function);
	protected DashboardModule dash = new DashboardModule(function);
	protected ReadConfigFile config=new ReadConfigFile();
	
	public String URL=config.getapplicationurl();
	public String emailid=config.getemailid();
	public String password=config.getpassword();
	
	@BeforeClass
	public void launchbrowser() throws InterruptedException, IOException {
		PropertyConfigurator.configure("log4j.properties");
		function.openbrowser();
		function.SetURL(URL);
		function.addinfolog("Browser is launch and FinOps MCD URL is entered");
	}
	
	@BeforeMethod
	public void signintofinops() throws TimeoutException {
		sign.enteremailid(emailid);
		sign.MS_Signin_Process1(emailid,password);
		//sign.MS_Signin_Process(emailid,password);
		function.addinfolog("Sign In to FinOps MCD application successfully");
	}
	
	@AfterMethod
	public void signoutformfinops(ITestResult tr) throws IOException{
		/*
		if(tr.getStatus()==ITestResult.FAILURE) {
		     function.captureScreen(tr.getName());
		     }
		     */
	    dash.signoutprocess();
		function.addinfolog("Sign Out form FinOps application");
	}
	
	@AfterClass
	public void closebrowser() {
		function.teardown();
		function.addinfolog("Browser is closed");
	}


}
