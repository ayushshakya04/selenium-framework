package PageClass;

import java.util.concurrent.TimeoutException;

import Repository.locator_signinpage;
import Utilities.ReuseMethods;

public class SignInPage extends locator_signinpage {
	
	ReuseMethods function;
	 public SignInPage(ReuseMethods function) {
		this.function=function;
	}
	
	public void enteremailid(String EmailId) {
		
		function.entertextbysendkeys(Locator_EmailTextField,EmailId);
		function.clickaction(Locator_SignInButton);
	}
	
	
	public void MS_Signin_Process1(String emailid, String password) throws TimeoutException {
		function.verifyDisplayed(Locator_text_sign_in);
		function.entertextbywait_elementclickable(Locator_MSEmailID, emailid);
		function.clickaction(Locator_MSNextButton);
		function.entertextbysendkeys(Locator_MSPassword, password);
		function.clickaction(Locator_MSSigninButton);
        //function.verifyDisplayed(Locator_text_more_information_required);
        //function.mouse_moveToElement(Locator_MSNextButton);
		//function.clickaction(Locator_MSNextButton);
		//function.clickaction(Locator_MSskipsteup);
		function.clickaction(Locator_MSYesButton);
	}
	
	public void MS_Signin_enteremailid(String emailid) {
		
		function.verifyDisplayed(Locator_MSwindowcantAccessAccount);
		function.entertextbysendkeys(Locator_MSEmailID, emailid);
		function.clickaction(Locator_MSNextButton);
		
	}
	
    public void MS_Signin_enterpassword(String password) {
	function.entertextbysendkeys(Locator_MSPassword, password);
	function.clickaction(Locator_MSSigninButton);
    function.verifyDisplayed(Locator_MSwindowmoreInfoLink);
    function.verifyDisplayed(Locator_MSwindowKeepyouraccountsecure);
	function.clickaction(Locator_MSNextButton);
	function.clickaction(Locator_MSskipsteup);
	function.clickaction(Locator_MSYesButton);
		
	}
    
    public void skippassword() {
    	function.clickaction(Locator_MSNextButton);
		function.clickaction(Locator_MSskipsteup);
    }
    
    public boolean verifymessageisdisplaytoskippassword() {
    	return function.verifyDisplayed(Locator_text_more_information_required);
    }
    
  
    public void MS_Signin_Process(String emailid, String password) throws TimeoutException {
    	
    	 boolean messageDisplayed = false;
	        do {
	            try {
	            	boolean status=function.verifyDisplayed(Locator_text_more_information_required);
	                if (status=true) {
	                	function.mouse_moveToElement(Locator_MSNextButton);
	                	function.clickaction(Locator_MSNextButton);
	            		//function.clickaction(Locator_MSskipsteup);
	                    messageDisplayed = true;
	                }
	            } catch (Exception e) {
	            	function.verifyDisplayed(Locator_text_sign_in);
	        		function.entertextbywait_elementclickable(Locator_MSEmailID, emailid);
	        		function.clickaction(Locator_MSNextButton);
	        		function.entertextbysendkeys(Locator_MSPassword, password);
	        		function.clickaction(Locator_MSSigninButton);
	                //function.verifyDisplayed(Locator_text_more_information_required);
	               // function.mouse_moveToElement(Locator_MSNextButton);
	        		//function.clickaction(Locator_MSNextButton);
	        		//function.clickaction(Locator_MSskipsteup);
	        		function.clickaction(Locator_MSYesButton);
	                messageDisplayed = true; 
	            }
	        } while (!messageDisplayed);
    	
    }
 
}
