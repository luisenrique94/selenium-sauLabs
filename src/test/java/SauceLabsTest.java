import org.openqa.selenium.WebDriver;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;


public class SauceLabsTest {
        private WebDriver driver;

        @BeforeEach
        public void setUp() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        @Test
        public void testLoginSauceLabs() throws InterruptedException {
            // Abrir Google
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");

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
            btnLogin.click();


            // Esperar un poco para visualizar los resultados (puedes mejorar esto con WebDriverWait)
            Thread.sleep(3000);

            // Capturar y mostrar los títulos de los primeros resultados
            for (WebElement result : driver.findElements(By.className("app_logo"))) {
                System.out.println("el texto encontrado es:" +result.getText());
            }

            // Verificar que aparecen resultados
           // Assertions.assertTrue(driver.findElements(By.cssSelector("h3")).size() > 0);
        }

        @AfterEach
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }

