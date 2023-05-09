package com.automation.scaledupit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.SetUp;

public class LogoutTest extends SetUp {
    Logger log = LogManager.getLogger(LogoutTest.class.getName());

    @Test
    public void logout() throws InterruptedException {
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
        //click on account details button
        clickOn("//*[@id=\"post-9\"]/div/div/nav/ul/li[5]/a");
        log.info("click on Account details button Success");
        //click on logout button
        clickOn("//*[@id=\"post-9\"]/div/div/nav/ul/li[6]");
        log.info("click on logout button Success");
        //check user is landed to the login page
        boolean loginPageHeaderIsDisplayed = isVisible("//span[contains(text(),'My account')]");
        Assert.assertTrue(loginPageHeaderIsDisplayed);
        log.info("login page header is displayed");
        String expectedLoginPageHeaderText = "My account";
        String expectedHomePageHeaderText = "My account";
        String actualHomePageHeaderText = getElementText("//span[contains(text(),'My account')]");
        Assert.assertEquals(expectedHomePageHeader, actualHomePageHeader);
        log.info("login page header text validation success");
    }
}
