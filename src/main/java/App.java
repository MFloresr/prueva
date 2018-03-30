import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;


import javax.swing.plaf.synth.SynthStyle;
import javax.xml.bind.Element;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.Thread.sleep;



public class App {


    public static void main(String[] args) throws InterruptedException, AWTException {

        String numero = "";
        float precioinicial = (float) 0,precioactual = 0,precioanterior = 0;//7435.00
        boolean activarinicial=true;
        boolean activaranterior=true;
        float moneda_principal = 0;
        double BTC = 0.049146;
        double USDT = 0;
        double TRX = 0.65500000;
        double DGX  = 0.00068200;
        double comision = 0.1;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        System.setProperty("webdriver.chrome.driver",
                "F:\\Librerias\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.navigate().to("https://es.tradingview.com/");
        driver.findElement(By.name("query")).sendKeys("BINANCE:BTCUSDT");
        driver.findElement(By.className("tv-header-search__icon")).click();

        driver.findElement(By.linkText("Gráfico")).click();
        sleep(1000);
        driver.findElement(By.className("input-3lfOzLDc-")).sendKeys("BINANCE:BTCUSDT");
        driver.findElement(By.className("input-3lfOzLDc-")).sendKeys(Keys.RETURN);
        driver.findElement(By.className("wizard-tooltip-stop")).click();

        //To open a new tab
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_T);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_T);
//To switch to the new tab
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
//To navigate to new link/URL in 2nd new tab
        driver.get("https://www.binance.com/login.html");
        driver.findElement(By.name("email")).sendKeys("mrsitofull@gmail.com");

        driver.findElement(By.id("pwd")).sendKeys("Qcmmer2sa!!!");
        sleep(10000);
        driver.findElement(By.id("login-btn")).click();
        sleep(60000);

        driver.get("https://www.binance.com/trade.html");

        sleep(10000);
        driver.get("https://www.binance.com/trade.html?symbol="+"BTC_USDT");



        //Change tab a tradingview
        driver.switchTo().window(tabs.get(0));

        //binance full pruevas

/*        //comprar
        driver.findElement(By.id("buyPrice")).clear();
        driver.findElement(By.id("buyPrice")).sendKeys("0");
        driver.findElement(By.id("sellQuanity")).clear();
        driver.findElement(By.id("sellQuanity")).sendKeys("0");
        driver.findElement(By.className("btn-buy")).click();

        // vender
        driver.findElement(By.id("sellPrice")).clear();
        driver.findElement(By.id("sellPrice")).sendKeys("0");
        driver.findElement(By.id("sellQuanity")).clear();
        driver.findElement(By.id("sellQuanity")).sendKeys("0");


        driver.findElement(By.className("btn-sell")).click();

   */



        String[] vocales = {"0", "2", "3", "4", "5", "6", "7", "8", "9"};





        int count = 1;
        while (count < 10000) {

            String elemHtml = driver.findElement(By.className("dl-header-price")).getText();


            precioactual=  Float.parseFloat(elemHtml);
            if(activarinicial == true){
                precioinicial=precioactual;
                precioanterior= precioactual;
                System.out.println("Comemzamos con : "+precioinicial);
                activarinicial = false;
            }

            float porcentage=((precioactual*100)/precioinicial)-100;



          /*  if (precioactual>precioactual){
                System.out.println(precioanterior+ " a: " + precioactual+ " sube ++++");
            }
            if (precioactual<precioanterior){
                System.out.println(precioanterior + " a: " + precioactual+ " baja ----");
            }
            if (precioactual==precioanterior){
                System.out.println(precioanterior + " a: " + precioactual+ " se mantiene");
            }*/
            precioanterior = precioactual;
            Float absporcentaje = Math.abs(porcentage);
            double NoComicion= absporcentaje-0.1;

            if(porcentage >0.1){
                System.out.println( " vender a ***************************"+ precioactual +" porcentage : "+ Math.abs(porcentage));
                System.out.println("sin comicion : "+ NoComicion );
            }
            if(porcentage < -0.1){
                System.out.println( " comprar a ..........................."+ precioactual + "porcentage : " +Math.abs(porcentage));
                System.out.println("sin comicion : "+ NoComicion );
            }


            Calendar calendario = Calendar.getInstance();
            int hora =calendario.get(Calendar.HOUR_OF_DAY);
            int minutos = calendario.get(Calendar.MINUTE);
            int segundos = calendario.get(Calendar.SECOND);
            //System.out.println(numeroinicial + " hora "+ hora+":"+minutos+":"+segundos);
            sleep(2000);


            if (BTC==0 & porcentage<-0.1){
                driver.switchTo().window(tabs.get(1));
                driver.findElement(By.id("buyPrice")).clear();
                driver.findElement(By.id("buyPrice")).sendKeys(Float.toString(precioactual) );
                driver.findElement(By.id("buyQuanity")).clear();
                driver.findElement(By.id("buyQuanity")).sendKeys(Double.toString(USDT));
                System.out.println("Comprado a precio : "+ precioactual);
                //driver.findElement(By.className("btn-buy")).click();
                Double ganacias = (USDT*NoComicion)/100;
                System.out.println("ganacias : "+ ganacias + "total de la operacion : " + (USDT+ganacias));

                BTC=precioactual*USDT;
                System.out.println(BTC);
                USDT= 0;
                precioinicial=precioactual;
                driver.switchTo().window(tabs.get(0));

            }if(USDT==0 & porcentage>0.1){
                driver.switchTo().window(tabs.get(1));
                driver.findElement(By.id("sellPrice")).clear();
                driver.findElement(By.id("sellPrice")).sendKeys(Float.toString(precioactual));
                driver.findElement(By.id("sellQuanity")).clear();
                driver.findElement(By.id("sellQuanity")).sendKeys(Double.toString(BTC));
                System.out.println("Comprado a precio : "+ precioactual);
                //driver.findElement(By.className("btn-sell")).click();
                Double ganacias = (BTC*NoComicion)/100;
                System.out.println("ganacias : "+ ganacias + "total de la operacion : " + (BTC+ganacias));
                USDT=precioactual*BTC;
                System.out.println(USDT);
                BTC= 0;
                precioinicial=precioactual;
                driver.switchTo().window(tabs.get(0));
            }





            count++;

        }

        System.exit(0);








        //driver.navigate().to("http://moodle.iescendrassos.net");
        //driver.findElement(By.linkText("Inicia la sessió")).click();
        //driver.findElement(By.id("username")).sendKeys("d23");
        //driver.findElement(By.id("password")).sendKeys("ViperStarZ!!!");
        //driver.findElement(By.id("loginbtn")).click();
        //driver.findElement(By.linkText("MÒDUL 3: PROGRAMACIÓ 2")).click();
        //System.out.println(driver.findElements(By.partialLinkText("Tasca")).size()+1+"chocolata");




    }
}






