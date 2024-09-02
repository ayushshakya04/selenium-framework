package Repository;

import org.openqa.selenium.By;

public class locator_signinpage {
	
	public static By Locator_EmailTextField=By.xpath("//input[@name='email']");
	
	public static By Locator_SignInButton=By.xpath("//button");
	
	public static By Locator_MSEmailID=By.xpath("//input[@type='email']");
	
	public static By Locator_MSwindowcantAccessAccount=By.xpath("//a[@id='cantAccessAccount']");
	
	public static By Locator_MSNextButton=By.xpath("//input[@value='Next']");
	
	public static By Locator_MSPassword=By.xpath("//input[@type='password']");
	
	public static By Locator_MSSigninButton=By.xpath("//input[@value='Sign in']");
	
	public static By Locator_MSskipsteup=By.xpath("//a[contains(text(),'Skip setup')]");
	
	public static By Locator_MSYesButton=By.xpath("//input[@value='Yes']");
	
	public static By Locator_MSwindowmoreInfoLink=By.xpath("//a[@id='moreInfoLink']");
	
	public static By Locator_MSwindowKeepyouraccountsecure=By.xpath("//h1[contains(text(),'Keep your account secure')]");
	
	public static By Locator_text_more_information_required=By.xpath("//div[contains(text(),'More information required')]");
	
	public static By Locator_text_sign_in=By.xpath("//div[contains(text(),'Sign in')]");
	
}
