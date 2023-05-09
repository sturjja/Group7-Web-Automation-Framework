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
    public void getCloudDriver(String envName, String os, String osVersion, String browserName, String browserVersion, String username, String password) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("os", os);
        cap.setCapability("os_version", osVersion);
        cap.setCapability("browser", browserName);
        cap.setCapability("browser_version", browserVersion);
        if (envName.equalsIgnoreCase("browserstack")){
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://"+username+":"+password+"@hub-cloud.browserstack.com:80/wd/hub"),cap);
        } else if (envName.equalsIgnoreCase("scaledupit")) {
            driver = new RemoteWebDriver(new URL("http://"+username+":"+password+"@ondemand.automation.scaledupit.com:80/wd.hub"),cap);
        }else if (envName.equalsIgnoreCase("freecrm")) {
            driver = new RemoteWebDriver(new URL("http://"+username+":"+password+"@ondemand.freecrm.com:80/wd.hub"),cap);
        }else if (envName.equalsIgnoreCase("mailchimp")) {
            driver = new RemoteWebDriver(new URL("http://"+username+":"+password+"@ondemand.mailchimp.com:80/wd.hub"),cap);
        }
    }

    public void getLocalDriver(String browserName){
        if (browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
            log.info("chrome browser open success");
        }else if (browserName.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
            log.info("firefox browser open success");
        }else if (browserName.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
            log.info("edge browser open success");
        }
    }

    @Parameters({"useCloudEnv","envName","os","osVersion","browserName","browserVersion","url"})
    @BeforeMethod
    public void setUp(@Optional("false") String useCloudEnv, @Optional("browserstack") String envName, @Optional("windows") String os,
                      @Optional("10") String osVersion, @Optional("chrome") String browserName, @Optional("110") String browserVersion,
                      @Optional("https://automation.scaledupit.com") String url) throws MalformedURLException {
        if (useCloudEnv.equalsIgnoreCase("true")){
            getCloudDriver(envName,os,osVersion,browserName,browserVersion,"samiurturjja_uXPSKT","DcRHG1dHNfvnD4deAqPT");
        } else if(useCloudEnv.equalsIgnoreCase("false")){
            getLocalDriver(browserName);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(url);
    }
    @AfterMethod
    public void tearDown(){
        //close browser
        driver.quit();
        log.info("browser close success");
    }

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