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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SauceLabsTest {
        private WebDriver driver;

        @BeforeEach
        public void setUp() {
            cleanFolder("./screenshots");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        @Test
        public void testLoginSauceLabs() throws InterruptedException {
            // Abrir Google
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");
            takeScreenshot("testLoginSauceLabs_success_web");

            // Encontrar el campo de userName
            WebElement userName = driver.findElement(By.name("user-name"));
            // Ingresar una userName
            userName.sendKeys("standard_user");
            // Encontrar el campo de passWord
            WebElement passWord = driver.findElement(By.id("password"));
            // Ingresar una userName
            passWord.sendKeys("secret_sauce");
            //Encontrar boton login
            WebElement btnLogin = driver.findElement(By.id("login-button"));
            // Ingresar login
            takeScreenshot("testLoginSauceLabs_success_datos_ingresados");
            btnLogin.click();


            // Esperar un poco para visualizar los resultados (puedes mejorar esto con WebDriverWait)
            Thread.sleep(3000);

            // Capturar y mostrar los títulos de los primeros resultados
            for (WebElement result : driver.findElements(By.className("app_logo"))) {
                System.out.println("el texto encontrado es:" +result.getText());
            }

            takeScreenshot("testLoginSauceLabs_success");
            // Verificar que aparecen resultados
           // Assertions.assertTrue(driver.findElements(By.cssSelector("h3")).size() > 0);
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

