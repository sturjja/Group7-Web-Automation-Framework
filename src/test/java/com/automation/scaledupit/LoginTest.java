package com.automation.scaledupit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.SetUp;

public class LoginTest extends SetUp {
    Logger log = LogManager.getLogger(LoginTest.class.getName());

    @Test
    public void validCredential() {
        String expectedTitle = "Automation – Automate eCommerce";
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        log.info("land on home page success");
        //enter  username, password, and click on login button
        clickOn("a[class='my-account']");
        log.info("click on MyAccount button Success");
        type("#username", "Samtur@example.com");
        log.info("enter username success");
        type("#password", "ABCd1234$piit");
        log.info("enter password success");
        clickOn("button[name ='login'");
        log.info("click on login button Success");
        //check user is logged in
        String expectedHomePageHeader = "My account";
        String actualHomePageHeader = getElementText("//span[contains(text(),'My account')]");
        Assert.assertEquals(expectedHomePageHeader, actualHomePageHeader);
        log.info("user logged in success");

    }

    @Test
    public void missingUsername() {
        String expectedTitle = "Automation – Automate eCommerce";
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        log.info("land on home page success");
        //enter  username, password, and click on login button
        clickOn("a[class='my-account']");
        log.info("click on MyAccount button Success");
        type("#username", "");
        log.info("enter username success");
        type("#password", "ABCd1234$piit");
        log.info("enter password success");
        clickOn("button[name ='login'");
        log.info("click on login button Success");

        //validate the error message
        String expectedError = "Error: Username is required.";
        String actualError = getElementText("//*[@id='post-9']/div/div/div[1]/ul/li");
        Assert.assertEquals(expectedError, actualError);
        log.info("validate error success");
    }

    @Test
    public void missingPassword() {
        String expectedTitle = "Automation – Automate eCommerce";
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        log.info("land on home page success");
        //enter  username, password, and click on login button
        clickOn("a[class='my-account']");
        log.info("click on MyAccount button Success");
        type("#username", "Samtur@example.com,");
        log.info("enter username success");
        type("#password", "");
        log.info("enter password success");
        clickOn("button[name ='login'");
        log.info("click on login button Success");

        //validate the error message
        String expectedError = "Error: The password field is empty.";
        String actualError = getElementText("//*[@id=\"post-9\"]/div/div/div[1]/ul/li");
        Assert.assertEquals(expectedError, actualError);
        log.info("validate error success");
    }
    @Test
    public void noCredentials() {
        String expectedTitle = "Automation – Automate eCommerce";
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        log.info("land on home page success");
        //enter  username, password, and click on login button
        clickOn("a[class='my-account']");
        log.info("click on MyAccount button Success");
        type("#username", "");
        log.info("enter username success");
        type("#password", "");
        log.info("enter password success");
        clickOn("button[name ='login'");
        log.info("click on login button Success");

        //validate the error message
        String expectedError = "Error: Username is required.";
        String actualError = getElementText("//*[@id='post-9']/div/div/div[1]/ul/li");
        Assert.assertEquals(expectedError, actualError);
        log.info("validate error success");
    }
}


