package TestClass;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.Test;

import PageClass.BudgetsModule;
import Utilities.ReadConfigFile;

public class BudgetsTestClass extends BaseClass {

	BudgetsModule bud = new BudgetsModule(function);
	protected ReadConfigFile config=new ReadConfigFile();
	 
	
	@Test(enabled=true)
		public void Verify_that_user_is_able_to_download_the_created_budget() throws TimeoutException, IOException, InterruptedException {
			bud.navigate_to_budget();
			bud.budgetform_fill();
		}


	@Test
	public void TC_CreateBudget() throws InterruptedException, TimeoutException {
		bud.addinfolog("Budget Creation Start");
		bud.Create_Budget();
		bud.addinfolog("Budget Successfully Created");
	}

	@Test
	public void TC_SnoozeOff() throws TimeoutException, InterruptedException {
		bud.addinfolog("Verification of the Snooze functionality of Budgets Start");
		bud.VerifyTooltipOff();
		bud.addinfolog("Verification Pass: the Snooze functionality of Budgets verified");
	}

	@Test
	public void TC_SnoozeON() throws TimeoutException, InterruptedException {
		bud.addinfolog("Verification of the UnSnooze functionality of Budgets Start");
		bud.VerifyTooltipOn();
		bud.addinfolog("Verification Pass: the Snooze functionality of Budgets verified");
	}

	@Test
	public void TC_EditBudget() throws TimeoutException, InterruptedException {
		bud.addinfolog("Verification of the Edit Budget functionality of Budgets Start");
		bud.EditBudget();
		bud.addinfolog("Verification Pass: Edit Budget functionality  of Budgets verified");
	}

}
