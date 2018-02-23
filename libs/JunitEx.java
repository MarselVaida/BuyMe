import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

    public class JunitEx {

        private static WebDriver driverFire;
        // private static WebDriver driver;
        login login= new login();


    @BeforeClass
    public static void setup ()
    {

        //System.setProperty("webdriver.chrome.driver","D:\\Selenium\\ChromeDriver\\chromedriver.exe");
        //driver = new ChromeDriver();
        System.setProperty("webdriver.gecko.driver","D:\\Selenium\\FirefoxDriver\\geckodriver.exe");
        //System.setProperty("webdriver.opera.driver","D:\\Selenium\\OperaDriver\\operadriver.exe");
        driverFire = new FirefoxDriver();
       // driverFire = new OperaDriver();

    }

    @Test
    public void test01_newChrome(){
        login.test01_newChromeDriver(driverFire);
    }

    @Test
    public void test02_printURL(){
        login.test02_printURL(driverFire);
    }

//    @AfterClass
//    public static void afterClass ()
//    {
//        driverFire.quit();
//    }
//



}
