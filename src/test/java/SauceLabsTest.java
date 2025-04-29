import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SauceLabsTest {
    private static final Logger log = LoggerFactory.getLogger(SauceLabsTest.class);
    private WebDriver driver;
     WebElement inputUserName;
     WebElement inputPassWord;
     WebElement btnLogin;
     WebElement labelMessage;

    @BeforeEach
        public void setUp() {
           cleanFolder("./screenshots");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            Allure.step("Abro la página de login");
            driver.get("https://www.saucedemo.com/");
           takeScreenshot("testLoginSauceLabs_success_web");
        }
        public void login(String user, String pass){
            inputUserName = driver.findElement(By.name("user-name"));
            inputUserName.sendKeys(user);

            inputPassWord = driver.findElement(By.id("password"));
            inputPassWord.sendKeys(pass);

            btnLogin = driver.findElement(By.id("login-button"));
            takeScreenshot("testLoginSauceLabs_success_datos_ingresados");
            btnLogin.click();



        }
        @Test
        public void testLoginSauceLabsPass() throws InterruptedException {
            login("standard_user","secret_sauce");
            Thread.sleep(3000);
             takeScreenshot("testLoginSauceLabs_success");


        }
    @Test
    public void testLoginSauceLabsLocked() throws InterruptedException {
        login("locked_out_user","secret_sauce");
        Thread.sleep(3000);
        labelMessage = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3"));
        System.out.println("mensaje obtenido:" +labelMessage.getText());
         takeScreenshot("testLoginSauceLabs_success_datos_ingresados_incorrectos");
        Assertions.assertEquals(labelMessage.getText(),"Epic sadface: Sorry, this user has been locked out.");
    }
    @Test
    public void testLoginSauceLabsProblemUser() throws InterruptedException {
        login("problem_user","secret_sauce");
        Thread.sleep(3000);
         takeScreenshot("testLoginSauceLabs_success_datos_ingresados_problem_user");
    }
    @Test
    public void testLoginSauceLabsPerformanceUser() throws InterruptedException {
        login("performance_glitch_user","secret_sauce");
        Thread.sleep(3000);
        takeScreenshot("testLoginSauceLabs_success_datos_ingresados_performance_user");
    }
    @Test
    public void testLoginSauceLabsErrorUser() throws InterruptedException {
        login("error_user","secret_sauce");
        Thread.sleep(3000);
        takeScreenshot("testLoginSauceLabs_success_datos_ingresados_error_user");
    }
    @Test
    public void testLoginSauceLabsVisualUser() throws InterruptedException {
        login("visual_user","secret_sauce");
        Thread.sleep(3000);
        takeScreenshot("testLoginSauceLabs_success_datos_ingresados_visual_user");
    }

    public void takeScreenshot(String testName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        try {
            FileUtils.copyFile(screenshot, new File("./screenshots/" + testName + "_" + timestamp + ".png"));
            System.out.println("Captura de pantalla guardada: " + testName + "_" + timestamp + ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cleanFolder(String folderPath) {
        File folder = new File(folderPath);
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File f : files) {
                    f.delete();
                }
            }
        }
    }


    @AfterEach
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }

