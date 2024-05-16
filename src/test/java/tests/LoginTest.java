package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    public WebDriver navegador;
    @Before
    public void setUp(){

        // Abrir navegador (setar o diretório onde se encontra o webdriver)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vinic\\Documents\\Projects\\Browser\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        // Entrar no Mantis
        navegador.get("https://mantis-prova.base2.com.br/login_page.php");
    }

    @Test
    public void testLoginSucesso(){

        String username = "Vinicius_Pereira";
        String password = "Testemantis321";


        //Digitar no campo username "Vinicius_Pereira"
        WebElement inpUsername = navegador.findElement(By.id("username"));
        inpUsername.sendKeys(username);

        //Clicar no botão entrar
        WebElement btnEntrar = navegador.findElement(By.xpath("//input[@value='Entrar']"));
        btnEntrar.click();

        //Digitar no campo de senha "Testemantis321"
        WebElement inpSenha = navegador.findElement(By.xpath("//input[@id='password']"));
        inpSenha.sendKeys(password);

        //Desmarcar checkbox "Somente permitir que sua sessão seja utilizada a partir deste endereço IP."
        WebElement chkIP = navegador.findElement(By.xpath("//span[contains(text(),'Somente permitir que sua sessão seja utilizada a p')]"));
        chkIP.click();

        //Clicar em entrar
        WebElement btnEntrar2 = navegador.findElement(By.xpath("//input[@value='Entrar']"));
        btnEntrar2.click();

        //Verificar se o usuário está logado
        WebElement userInfo = navegador.findElement(By.xpath("//span[@class='user-info']"));
        assertEquals(username,userInfo.getText());
    }

    @Test
    public void testLoginErroUsername(){

        String username = "Vinicius_Pereira2";
        String password = "Testemantis321";


        //Digitar no campo username um nome de usuário inválido
        WebElement inpUsername = navegador.findElement(By.id("username"));
        inpUsername.sendKeys(username);

        //Clicar no botão entrar
        WebElement btnEntrar = navegador.findElement(By.xpath("//input[@value='Entrar']"));
        btnEntrar.click();

        //Digitar no campo de senha uma senha válida
        WebElement inpSenha = navegador.findElement(By.xpath("//input[@id='password']"));
        inpSenha.sendKeys(password);

        //Desmarcar checkbox "Somente permitir que sua sessão seja utilizada a partir deste endereço IP."
        WebElement chkIP = navegador.findElement(By.xpath("//span[contains(text(),'Somente permitir que sua sessão seja utilizada a p')]"));
        chkIP.click();

        //Clicar em entrar
        WebElement btnEntrar2 = navegador.findElement(By.xpath("//input[@value='Entrar']"));
        btnEntrar2.click();

        //Verificar se foi exibida mensagem de erro de login
        WebElement erroLogin = navegador.findElement(By.xpath("//p[contains(text(),'Sua conta pode estar desativada ou bloqueada ou o nome de usuário e a senha que você digitou não estão corretos.')]"));
    }

    @Test
    public void testLoginErroPassword(){

        String username = "Vinicius_Pereira";
        String password = "Testemantis123";


        //Digitar no campo username um nome de usuário inválido
        WebElement inpUsername = navegador.findElement(By.id("username"));
        inpUsername.sendKeys(username);

        //Clicar no botão entrar
        WebElement btnEntrar = navegador.findElement(By.xpath("//input[@value='Entrar']"));
        btnEntrar.click();

        //Digitar no campo de senha uma senha válida
        WebElement inpSenha = navegador.findElement(By.xpath("//input[@id='password']"));
        inpSenha.sendKeys(password);

        //Desmarcar checkbox "Somente permitir que sua sessão seja utilizada a partir deste endereço IP."
        WebElement chkIP = navegador.findElement(By.xpath("//span[contains(text(),'Somente permitir que sua sessão seja utilizada a partir')]"));
        chkIP.click();

        //Clicar em entrar
        WebElement btnEntrar2 = navegador.findElement(By.xpath("//input[@value='Entrar']"));
        btnEntrar2.click();

        //Verificar se foi exibida mensagem de erro de login
        WebElement erroLogin = navegador.findElement(By.xpath("//p[contains(text(),'Sua conta pode estar desativada ou bloqueada ou o nome de usuário e a senha que você digitou não estão corretos.')]"));

    }
    @After
    public void shutDown(){
        //Fechar navegador
        navegador.quit();
    }
}
