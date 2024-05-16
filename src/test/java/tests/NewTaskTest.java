package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class NewTaskTest {

    public WebDriver driver;

    @Before
    public void setUp(){
        // Abrir navegador (setar o diretório onde se encontra o webdriver)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vinic\\Documents\\Projects\\Browser\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        // Entrar no Mantis
        driver.get("https://mantis-prova.base2.com.br/login_page.php");
        driver.manage().window().maximize();

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
    }

    @Test
    public void createBug(){
        //Clicar no botão Criar Tarefa
        WebElement btnCreateBug = driver.findElement(By.xpath("//a[@class='btn btn-primary btn-sm']"));
        btnCreateBug.click();

        //Selecionar "[Todos os Projetos] nova categoria" no campo Categoria
        WebElement slctCategory = driver.findElement(By.xpath("//select[@id='category_id']"));
        new Select(slctCategory).selectByVisibleText("[Todos os Projetos] nova categoria");

        //Selecionar "aleatório" no campo Frequencia
        WebElement slctReproducibility = driver.findElement(By.xpath("//select[@id='reproducibility']"));
        new Select(slctReproducibility).selectByVisibleText("aleatório");

        //Selecionar "travamento" no campo Gravidade
        WebElement slctSeverity = driver.findElement(By.xpath("//select[@id='severity']"));
        new Select(slctSeverity).selectByVisibleText("travamento");

        //Selecionar "urgente" no campo Prioridade
        WebElement slctPriority = driver.findElement(By.xpath("//select[@id='priority']"));
        new Select(slctPriority).selectByVisibleText("urgente");

        //Expandir seleção de perfil
        //WebElement btnProfile = driver.findElement(By.xpath("//td/div/a/i"));
        //btnProfile.click();

        //Digitar "Test Platform" no campo Plataforma
        //WebElement inpPlataform = driver.findElement(By.xpath("//input[@id='platform']"));
        //inpPlataform.sendKeys("Test Platform");

        //Digitar "Windows" no campo SO
        //WebElement inpSO = driver.findElement(By.xpath("//input[@id='os']"));
        //inpSO.sendKeys("Windows");

        //Digitar "12" no campo Versão SO
        //WebElement inpVersaoSO = driver.findElement(By.xpath("//input[@id='os_build']"));
        //inpVersaoSO.sendKeys("12");

        //Digitar "Teste resumo" no campo Resumo
        WebElement inpSummary = driver.findElement(By.xpath("//input[@id='summary']"));
        inpSummary.sendKeys("Teste resumo");

        //Digitar "Teste Descrição" no campo Descrição
        WebElement inpDescription = driver.findElement(By.xpath("//textarea[@id='description']"));
        inpDescription.sendKeys("Teste Descrição");

        //Digitar Passos 1 e 2 no campo Passos para reproduzir
        WebElement inpSteps = driver.findElement(By.xpath("//textarea[@id='steps_to_reproduce']"));
        inpSteps.sendKeys("Passo 1\nPasso 2");

        //Digitar "Teste Informações Adicionais" no campo Informações Adicionais
        WebElement inpAddInfo = driver.findElement(By.xpath("//textarea[@id='additional_info']"));
        inpAddInfo.sendKeys("Teste Informações Adicionais");

        //Selecionar "Desenvolvimento" no campo Aplicar Marcadores
        WebElement slctTags = driver.findElement(By.xpath("//select[@id='tag_select']"));
        new Select(slctTags).selectByVisibleText("Desenvolvimento");

        //Selecionar "marcadores" no campo Aplicar Marcadores
        new Select(slctTags).selectByVisibleText("marcadores");

        // Selecionar visibilidade "privado"
        WebElement chkVisibility = driver.findElement(By.xpath("//span[normalize-space()='privado']"));
        chkVisibility.click();

        //Clicar em Criar Nova Tarefa
        driver.findElement(By.xpath("//input[@value='Criar Nova Tarefa']")).click();

    }

    @Test
    public void validateBug(){
        //Clicar em ver tarefas
        WebElement btnViewBugs = driver.findElement(By.xpath("//span[normalize-space()='Ver Tarefas']"));
        btnViewBugs.click();

        //Clicar no bug 0000414
        WebElement btnBug = driver.findElement(By.xpath("//td[@class='column-id']//a[contains(text(),'0000414')]"));
        btnBug.click();

        //Aguardar até que os detalhes da tarefa apareçam
        WebElement detailsBug = driver.findElement(By.xpath("//h4[normalize-space()='Ver Detalhes da Tarefa']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(detailsBug));

        //Validar se campo relator está como Vinicius_Pereira
        WebElement bugReporter = driver.findElement(By.xpath("//td[@class='bug-reporter']/a"));
        assertEquals("Vinicius_Pereira",bugReporter.getText());

        //Validar se campo Prioridade está como urgente
        WebElement bugPriority = driver.findElement(By.xpath("//td[@class='bug-priority']"));
        assertEquals("urgente",bugPriority.getText());

        //Validar se campo Plataforma está como Test Platform
        //WebElement bugPlatform = driver.findElement(By.xpath("//td[@class='bug-platform']"));
        //assertEquals("Test Platform",bugPlatform.getText());

        /// Validar se campo Resumo está como numero do teste + "Teste resumo"
        WebElement bugSummary = driver.findElement(By.xpath("//td[@class='bug-summary']"));
        assertEquals("0000414: Teste resumo",bugSummary.getText());

        // Validar se campo Descrição está como Teste Descrição
        WebElement bugDescription = driver.findElement(By.xpath("//td[@class='bug-description']"));
        assertEquals("Teste Descrição",bugDescription.getText());

        /// Validar se campo Passos para Reproduzir contém o texto "Passo 1"
        WebElement bugSteps = driver.findElement(By.xpath("//td[@class='bug-steps-to-reproduce']"));
        assertEquals("Passo 1\nPasso 2",bugSteps.getText());

        // Validar se campo Informações Adicionais está como Teste Informações Adicionais
        WebElement bugAddInfo = driver.findElement(By.xpath("//td[@class='bug-additional-information']"));
        assertEquals("Teste Informações Adicionais",bugAddInfo.getText());

        // Validar se campo Categoria está como [Todos os Projetos] nova categoria
        WebElement bugCategory = driver.findElement(By.xpath("//td[@class='bug-category']"));
        assertEquals("[Todos os Projetos] nova categoria",bugCategory.getText());

        // Validar se campo Visibilidade está como privado
        WebElement bugVisibility = driver.findElement(By.xpath("//td[@class='bug-view-status']"));
        assertEquals("privado",bugVisibility.getText());

        // Validar se campo Gravidade está como travamento
        WebElement bugSeverity = driver.findElement(By.xpath("//td[@class='bug-severity']"));
        assertEquals("travamento",bugSeverity.getText());

        // Validar se campo SO está como Windows
        //WebElement bugSO = driver.findElement(By.xpath("//td[@class='bug-os']"));
        //assertEquals("Windows",bugSO.getText());

        // Validar se campo Frequencia está como aleatório
        WebElement bugFrequency = driver.findElement(By.xpath("//td[@class='bug-reproducibility']"));
        assertEquals("aleatório",bugFrequency.getText());

        // Validar se campo Versão SO está como 12
        // WebElement bugOSVersion = driver.findElement(By.xpath("//td[@class='bug-os-version']"));
        //assertEquals("12",bugOSVersion.getText());
    }

    @After
    public void shutDown(){
        //Fechar navegador
        driver.quit();
    }
}
