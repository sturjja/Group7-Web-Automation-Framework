package us.piit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SetUp {
    Logger log = LogManager.getLogger(SetUp.class.getName());
    WebDriver driver;










    //------------------------------------------------------------------------------------------------------------------
    //                                              selenium methods
    //------------------------------------------------------------------------------------------------------------------.
    public String getCurrentTitle(){
        return driver.getTitle();
    }
    public String getElementText(String locator){
        try {
            return driver.findElement(By.cssSelector(locator)).getText();
        }catch (Exception e){
            return driver.findElement(By.xpath(locator)).getText();
        }
    }
    public void clickOn(String locator){
        try {
            driver.findElement(By.cssSelector(locator)).click();
        }catch (Exception e){
            driver.findElement(By.xpath(locator)).click();
        }
    }
    public void type(String locator, String text){
        try {
            driver.findElement(By.cssSelector(locator)).sendKeys(text);
        }catch (Exception e){
            driver.findElement(By.xpath(locator)).sendKeys(text);
        }
    }
    public void hoverOver(String locator){
        Actions actions = new Actions(driver);
        try {
            actions.moveToElement(driver.findElement(By.cssSelector(locator))).build().perform();
        }catch (Exception e){
            actions.moveToElement(driver.findElement(By.xpath(locator))).build().perform();
        }
    }
    public void waitFor(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isVisible(String locator){
        try {
            return driver.findElement(By.cssSelector(locator)).isDisplayed();
        }catch (Exception e){
            return driver.findElement(By.xpath(locator)).isDisplayed();
        }
    }
    public boolean isInteractible(String locator){
        try {
            return driver.findElement(By.cssSelector(locator)).isEnabled();
        }catch (Exception e){
            return driver.findElement(By.xpath(locator)).isEnabled();
        }
    }
    public boolean isChecked(String locator){
        try {
            return driver.findElement(By.cssSelector(locator)).isSelected();
        }catch (Exception e){
            return driver.findElement(By.xpath(locator)).isSelected();
        }
    }
}