package tests;

import jdk.jfr.Timespan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NewTaskTest {
    public WebDriver navegador;
    @Before
    public void setUp(){
        // Abrir navegador (setar o diretório onde se encontra o webdriver)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vinic\\Documents\\Projects\\Browser\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        // Entrar no Mantis
        navegador.get("https://mantis-prova.base2.com.br/login_page.php");
        navegador.manage().window().maximize();

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
    public void createBug(){
        //Clicar no botão Criar Tarefa
        WebElement btnCriarTarefa = navegador.findElement(By.xpath("//a[@class='btn btn-primary btn-sm']"));
        btnCriarTarefa.click();

        //Selecionar "[Todos os Projetos] nova categoria" no campo Categoria
        WebElement slctCategoria = navegador.findElement(By.xpath("//select[@id='category_id']"));
        new Select(slctCategoria).selectByVisibleText("[Todos os Projetos] nova categoria");

        //Selecionar "aleatório" no campo Frequencia
        WebElement slctFrequencia = navegador.findElement(By.xpath("//select[@id='reproducibility']"));
        new Select(slctFrequencia).selectByVisibleText("aleatório");

        //Selecionar "travamento" no campo Gravidade
        WebElement slctGravidade = navegador.findElement(By.xpath("//select[@id='severity']"));
        new Select(slctGravidade).selectByVisibleText("travamento");

        //Selecionar "urgente" no campo Prioridade
        WebElement slctPrioridade = navegador.findElement(By.xpath("//select[@id='priority']"));
        new Select(slctPrioridade).selectByVisibleText("urgente");

        //Expandir seleção de perfil
        //WebElement btnProfile = navegador.findElement(By.xpath("//td/div/a/i"));
        //btnProfile.click();

        //Digitar "Test Platform" no campo Plataforma
        //WebElement inpPlataforma = navegador.findElement(By.xpath("//input[@id='platform']"));
        //inpPlataforma.sendKeys("Test Platform");

        //Digitar "Windows" no campo SO
        //WebElement inpSO = navegador.findElement(By.xpath("//input[@id='os']"));
        //inpSO.sendKeys("Windows");

        //Digitar "12" no campo Versão SO
        //WebElement inpVersaoSO = navegador.findElement(By.xpath("//input[@id='os_build']"));
        //inpVersaoSO.sendKeys("12");

        //Digitar "Teste resumo" no campo Resumo
        WebElement inpResumo = navegador.findElement(By.xpath("//input[@id='summary']"));
        inpResumo.sendKeys("Teste resumo");

        //Digitar "Teste Descrição" no campo Descrição
        WebElement inpDescricao = navegador.findElement(By.xpath("//textarea[@id='description']"));
        inpDescricao.sendKeys("Teste Descrição");

        //Digitar Passos 1 e 2 no campo Passos para reproduzir
        WebElement inpSteps = navegador.findElement(By.xpath("//textarea[@id='steps_to_reproduce']"));
        inpSteps.sendKeys("Passo 1\nPasso 2");

        //Digitar "Teste Informações Adicionais" no campo Informações Adicionais
        WebElement inpAddInfo = navegador.findElement(By.xpath("//textarea[@id='additional_info']"));
        inpAddInfo.sendKeys("Teste Informações Adicionais");

        //Selecionar "Desenvolvimento" no campo Aplicar Marcadores
        WebElement slctTags = navegador.findElement(By.xpath("//select[@id='tag_select']"));
        new Select(slctTags).selectByVisibleText("Desenvolvimento");

        //Selecionar "marcadores" no campo Aplicar Marcadores
        new Select(slctTags).selectByVisibleText("marcadores");

        // Selecionar visibilidade "privado"
        WebElement chkVisibilidade = navegador.findElement(By.xpath("//span[normalize-space()='privado']"));
        chkVisibilidade.click();

        //Clicar em Criar Nova Tarefa
        navegador.findElement(By.xpath("//input[@value='Criar Nova Tarefa']")).click();

    }
    @Test
    public void validateBug(){
        //Clicar em ver tarefas
        WebElement btnVerTarefas = navegador.findElement(By.xpath("//span[normalize-space()='Ver Tarefas']"));
        btnVerTarefas.click();

        //Clicar no bug 0000414
        WebElement btnBug = navegador.findElement(By.xpath("//td[@class='column-id']//a[contains(text(),'0000414')]"));
        btnBug.click();

        //Aguardar até que os detalhes da tarefa apareçam
        WebElement detalhesTarefa = navegador.findElement(By.xpath("//h4[normalize-space()='Ver Detalhes da Tarefa']"));
        WebDriverWait aguardo = new WebDriverWait(navegador, Duration.ofSeconds(10));
        aguardo.until(ExpectedConditions.visibilityOf(detalhesTarefa));

        //Validar se campo relator está como Vinicius_Pereira
        WebElement bugReporter = navegador.findElement(By.xpath("//td[@class='bug-reporter']/a"));
        assertEquals("Vinicius_Pereira",bugReporter.getText());

        //Validar se campo Prioridade está como urgente
        WebElement bugPriority = navegador.findElement(By.xpath("//td[@class='bug-priority']"));
        assertEquals("urgente",bugPriority.getText());

        //Validar se campo Plataforma está como Test Platform
        //WebElement bugPlatform = navegador.findElement(By.xpath("//td[@class='bug-platform']"));
        //assertEquals("Test Platform",bugPlatform.getText());

        /// Validar se campo Resumo está como numero do teste + "Teste resumo"
        WebElement bugResumo = navegador.findElement(By.xpath("//td[@class='bug-summary']"));
        assertEquals("0000414: Teste resumo",bugResumo.getText());

        // Validar se campo Descrição está como Teste Descrição
        WebElement bugDescription = navegador.findElement(By.xpath("//td[@class='bug-description']"));
        assertEquals("Teste Descrição",bugDescription.getText());

        /// Validar se campo Passos para Reproduzir contém o texto "Passo 1"
        WebElement bugSteps = navegador.findElement(By.xpath("//td[@class='bug-steps-to-reproduce']"));
        assertEquals("Passo 1\nPasso 2",bugSteps.getText());

        // Validar se campo Informações Adicionais está como Teste Informações Adicionais
        WebElement bugAddInfo = navegador.findElement(By.xpath("//td[@class='bug-additional-information']"));
        assertEquals("Teste Informações Adicionais",bugAddInfo.getText());

        // Validar se campo Categoria está como [Todos os Projetos] nova categoria
        WebElement bugCategory = navegador.findElement(By.xpath("//td[@class='bug-category']"));
        assertEquals("[Todos os Projetos] nova categoria",bugCategory.getText());

        // Validar se campo Visibilidade está como privado
        WebElement bugVisibility = navegador.findElement(By.xpath("//td[@class='bug-view-status']"));
        assertEquals("privado",bugVisibility.getText());

        // Validar se campo Gravidade está como travamento
        WebElement bugSeverity = navegador.findElement(By.xpath("//td[@class='bug-severity']"));
        assertEquals("travamento",bugSeverity.getText());

        // Validar se campo SO está como Windows
        //WebElement bugSO = navegador.findElement(By.xpath("//td[@class='bug-os']"));
        //assertEquals("Windows",bugSO.getText());

        // Validar se campo Frequencia está como aleatório
        WebElement bugFrequency = navegador.findElement(By.xpath("//td[@class='bug-reproducibility']"));
        assertEquals("aleatório",bugFrequency.getText());

        // Validar se campo Versão SO está como 12
        // WebElement bugOSVersion = navegador.findElement(By.xpath("//td[@class='bug-os-version']"));
        //assertEquals("12",bugOSVersion.getText());
    }


    //@After
    //public void shutDown(){
        //Fechar navegador
        //navegador.quit();
    //}
}
