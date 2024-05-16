package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import support.Generator;
import support.Screenshot;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    public WebDriver driver;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){

        // Abrir navegador (setar o diretório onde se encontra o webdriver)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vinic\\Documents\\Projects\\Browser\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        // Entrar no Mantis
        driver.get("https://mantis-prova.base2.com.br/login_page.php");
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginSuccess(){

        String username = "Vinicius_Pereira";
        String password = "Testemantis321";


        //Digitar no campo username "Vinicius_Pereira"
        WebElement inpUsername = driver.findElement(By.id("username"));
        inpUsername.sendKeys(username);

        //Clicar no botão entrar
        WebElement btnEnter = driver.findElement(By.xpath("//input[@value='Entrar']"));
        btnEnter.click();

        //Digitar no campo de senha "Testemantis321"
        WebElement inpPassword = driver.findElement(By.xpath("//input[@id='password']"));
        inpPassword.sendKeys(password);

        //Desmarcar checkbox "Somente permitir que sua sessão seja utilizada a partir deste endereço IP."
        WebElement chkIP = driver.findElement(By.xpath("//span[contains(text(),'Somente permitir que sua sessão seja utilizada a p')]"));
        chkIP.click();

        //Clicar em entrar
        WebElement btnEnter2 = driver.findElement(By.xpath("//input[@value='Entrar']"));
        btnEnter2.click();

        //Verificar se o usuário está logado
        WebElement userInfo = driver.findElement(By.xpath("//span[@class='user-info']"));
        assertEquals(username,userInfo.getText());

        //Tirar Screenshot (selecionar diretório a ser enviada a screenshot)
        String screenshotFile = "C:\\Users\\vinic\\Documents\\Projects\\test-report\\" + Generator.dateTimeForFile() + test.getMethodName() + ".png";
        Screenshot.take(driver, screenshotFile);
    }

    @Test
    public void testLoginErrorUsername(){

        String username = "Vinicius_Pereira2";
        String password = "Testemantis321";


        //Digitar no campo username um nome de usuário inválido
        WebElement inpUsername = driver.findElement(By.id("username"));
        inpUsername.sendKeys(username);

        //Clicar no botão entrar
        WebElement btnEnter = driver.findElement(By.xpath("//input[@value='Entrar']"));
        btnEnter.click();

        //Digitar no campo de senha uma senha válida
        WebElement inpPassword = driver.findElement(By.xpath("//input[@id='password']"));
        inpPassword.sendKeys(password);

        //Desmarcar checkbox "Somente permitir que sua sessão seja utilizada a partir deste endereço IP."
        WebElement chkIP = driver.findElement(By.xpath("//span[contains(text(),'Somente permitir que sua sessão seja utilizada a p')]"));
        chkIP.click();

        //Clicar em entrar
        WebElement btnEnter2 = driver.findElement(By.xpath("//input[@value='Entrar']"));
        btnEnter2.click();

        //Verificar se foi exibida mensagem de erro de login
        WebElement errorLogin = driver.findElement(By.xpath("//p[contains(text(),'Sua conta pode estar desativada ou bloqueada ou o nome de usuário e a senha que você digitou não estão corretos.')]"));

        //Tirar Screenshot (selecionar diretório a ser enviada a screenshot)
        String screenshotFile = "C:\\Users\\vinic\\Documents\\Projects\\test-report\\" + Generator.dateTimeForFile() + test.getMethodName() + ".png";
        Screenshot.take(driver, screenshotFile);
    }

    @Test
    public void testLoginErrorPassword(){

        String username = "Vinicius_Pereira";
        String password = "Testemantis123";


        //Digitar no campo username um nome de usuário inválido
        WebElement inpUsername = driver.findElement(By.id("username"));
        inpUsername.sendKeys(username);

        //Clicar no botão entrar
        WebElement btnEnter = driver.findElement(By.xpath("//input[@value='Entrar']"));
        btnEnter.click();

        //Digitar no campo de senha uma senha válida
        WebElement inpPassword = driver.findElement(By.xpath("//input[@id='password']"));
        inpPassword.sendKeys(password);

        //Desmarcar checkbox "Somente permitir que sua sessão seja utilizada a partir deste endereço IP."
        WebElement chkIP = driver.findElement(By.xpath("//span[contains(text(),'Somente permitir que sua sessão seja utilizada a partir')]"));
        chkIP.click();

        //Clicar em entrar
        WebElement btnEnter2 = driver.findElement(By.xpath("//input[@value='Entrar']"));
        btnEnter2.click();

        //Verificar se foi exibida mensagem de erro de login
        driver.findElement(By.xpath("//p[contains(text(),'Sua conta pode estar desativada ou bloqueada ou o nome de usuário e a senha que você digitou não estão corretos.')]"));

        //Tirar Screenshot (selecionar diretório a ser enviada a screenshot)
        String screenshotFile = "C:\\Users\\vinic\\Documents\\Projects\\test-report\\" + Generator.dateTimeForFile() + test.getMethodName() + ".png";
        Screenshot.take(driver, screenshotFile);
    }

    @After
    public void shutDown(){
        //Fechar navegador
        driver.quit();
    }
}
