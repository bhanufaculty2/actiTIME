package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.LoginPage;

public class InvalidLogin extends BaseTest
{
	@Test(priority = 2)
	public void testInvalidLogin() throws Exception
	{
//		get data from excel file
		String un = Utility.getXLData(testDataPath, "InvalidLogin",1, 0);
		String pw = Utility.getXLData(testDataPath,"InvalidLogin",1, 1);
		
//      1. Enter invalid user name
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName(un);
		
//		2. enter invalid password
		loginPage.setPassword(pw);
		
//		3. click on login button
		loginPage.clickLoginButton();
		
//		4. verify that err message is displayed
		boolean res = loginPage.verifyErrMsgDisplayed(wait);
		
//		Assert.assertEquals(res, true);
		Assert.assertTrue(res);
	}
}
