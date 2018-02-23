import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.w3c.dom.Document;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@FixMethodOrder (MethodSorters.NAME_ASCENDING)

public class tests {

    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentHtmlReporter htmlReport;
    private static ExtentTest myTests;
    private static String website;
    private static String imagePath = "C:\\Users\\marsell21\\IdeaProjects\\BuyMe\\Screenshots";
    private static String reportFilePath ="C:\\Users\\marsell21\\IdeaProjects\\BuyMe\\BuyMeReport.html";

    public static SignUp registr;
    public static Login login;
    public static businessPage business;
    public static senderReceiverPage senderReceiver;
    public static stepOneWhichGift stepOneWhichGift;
    public static sendBySms sendBySms;
    public static loadingScreen loadingScreen;
    public static HomeScreen homeScreen;

    @Rule
    public TestName name= new TestName();

    @BeforeClass
    public static void setup() throws Exception {
        extent = new ExtentReports();
        htmlReport = new ExtentHtmlReporter(reportFilePath);
        extent.attachReporter(htmlReport);
        myTests = extent.createTest("myTest", "BuyMe tests");

        String browserType = readFromFile("browserType");
        setBrowser(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        website = readFromFile("websiteURL");
        driver.get(website);
        myTests.log(Status.INFO,"Start automation test on website: "+website);

    }


    public static String readFromFile(String keyData) throws Exception {

            File xmlFile = new File("C:\\Users\\marsell21\\IdeaProjects\\BuyMe\\config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

        return doc.getElementsByTagName(keyData).item(0).getTextContent();
    }



    public static void setBrowser (String browser){

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\ChromeDriver\\chromedriver.exe");
                ChromeOptions option = new ChromeOptions();
                option.addArguments("-incognito");
                option.addArguments("--disable-popup-blocking");
                driver = new ChromeDriver(option);
                break;

            case "InternetExplorer":
                System.setProperty("webdriver.ie.driver","D:\\Selenium\\ExplorerDriver\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;

            case "fireFox":
                System.setProperty("webdriver.gecko.driver","D:\\Selenium\\FirefoxDriver\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            case "opera":
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Program Files\\Opera\\launcher.exe");
                System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\OperaDriver\\operadriver.exe");
                driver = new ChromeDriver(options);
                break;

        }

    }

    //BuyMe site - loading page
    //The test prints the size of the point on the loading screen
    @Test
    public void test01_printDotSize() throws InterruptedException {
        myTests.log(Status.INFO,name.getMethodName()+":Test start");
        loadingScreen = new loadingScreen(driver);
        driver.navigate().refresh();
        loadingScreen.printDotSize();

    }

    //BuyMe site - Login screen
    //The test verifies that an error message appears when the user does not enter the necessary information
    @Test
    public void test02_verifyErrorMessage() throws InterruptedException, IOException {
        homeScreen = new HomeScreen(driver);
        myTests.log(Status.INFO,"details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(imagePath+"\\"+System.currentTimeMillis())).build());
        homeScreen.clickOnLoginButton();

        myTests.log(Status.INFO,""+name.getMethodName()+":Test start");
        login = new Login(driver);
        myTests.log(Status.INFO,"details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(imagePath+"\\"+System.currentTimeMillis())).build());
        login.clickOnLoginToSite();
        Thread.sleep(1000);
        Assert.assertTrue(login.verifyErrorMessage());
        myTests.log(Status.PASS,"Test:"+name.getMethodName());

    }


    //BuyMe site - SignUp screen
    //The test registers a new user to the site
    @Test
    public void test03_registration() throws InterruptedException, IOException {
        driver.navigate().back();
        homeScreen = new HomeScreen(driver);
        myTests.log(Status.INFO,"details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(imagePath+"\\"+System.currentTimeMillis())).build());
        homeScreen.clickOnLoginButton();
        login = new Login(driver);
        Thread.sleep(1000);
        login.clickOnNotSignUpButton();

        myTests.log(Status.INFO,name.getMethodName()+":Test start");
        registr = new SignUp(driver);
        myTests.log(Status.INFO,"details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(imagePath+"\\"+System.currentTimeMillis())).build());
        registr.FillInTheRegistrationDetails();
        registr.selectAllowButton();
        registr.clickToRegister();
        Assert.assertEquals(login.getLoginPageURL(), driver.getCurrentUrl());
        myTests.log(Status.PASS,"Test:"+name.getMethodName());

    }

    @Before
    public void test04_wait() throws InterruptedException {
        Thread.sleep(1000);
    }

    //BuyME site - Home page
    //The test performs a search of a gift in the filtration of sum, area and category
    @Test
    public void test04_searchGiftByFiltering() throws IOException {
        myTests.log(Status.INFO,name.getMethodName()+":Test start");
        homeScreen = new HomeScreen(driver);
        myTests.log(Status.INFO,"details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(imagePath+"\\"+System.currentTimeMillis())).build());
        homeScreen.chooseAmount();
        homeScreen.chooseArea();
        homeScreen.chooseCategory();
        homeScreen.searchGift();

    }

    //BuyMe site - Home page --> Gifts page
    //The test makes sure that it has been passed over to the gift page after filtering
    @Test
    public void test05_giftPage() throws InterruptedException {
        myTests.log(Status.INFO,name.getMethodName()+":Test start");
        Thread.sleep(1000);
        String giftPageUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(giftPageUrl,HomeScreen.loginUrl);
        myTests.log(Status.PASS,name.getMethodName()+":Test pass. different URL");
    }

    //BuyMe site - Gifts page
    //The test makes a choice of one coupon from the list of results
    @Test
    public void test06_chooseBuisness() throws InterruptedException, IOException {
        myTests.log(Status.INFO,name.getMethodName()+":Test start");
        business = new businessPage(driver);
        myTests.log(Status.INFO,"details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(imagePath+"\\"+System.currentTimeMillis())).build());
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        myTests.log(Status.INFO,"details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(imagePath+"\\"+System.currentTimeMillis())).build());
        Thread.sleep(1000);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",business.getCartersBuisness());
        business.clickOnBusinessButton();

    }

    //BuyMe site - Step 1: Which gift card
    //The test operation puts the required amount and moves to the next stage
    @Test
    public void test07_stepOneWhichGigtCard() throws InterruptedException, IOException {
        myTests.log(Status.INFO,name.getMethodName()+":Test start");
        myTests.log(Status.INFO,"details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(imagePath+"\\"+System.currentTimeMillis())).build());
        stepOneWhichGift = new  stepOneWhichGift (driver);
        stepOneWhichGift.enterAmountAndSubmit();

    }

    //BuyMe site - Step 2: Who to send the gift card
    //The test gives all the details required to send a gift to someone else
    @Test
    public void test08_stepTwoWhoToSend() throws InterruptedException, IOException {
        myTests.log(Status.INFO,name.getMethodName()+":Test start");
        myTests.log(Status.INFO,"details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(imagePath+"\\"+System.currentTimeMillis())).build());
        Thread.sleep(5000);
        senderReceiver= new senderReceiverPage(driver);
        senderReceiver.sendToAnotherPerson();
        senderReceiver.fillAllForm();
        senderReceiver.addPicture();
        senderReceiver.checkSendAfterPayment();
        senderReceiver.sendBySms();
        Assert.assertEquals(senderReceiver.getColorText(),senderReceiver.whoToSendColorText());
        myTests.log(Status.PASS,"Test:"+name.getMethodName());

    }

    //BuyMe site - Step 2: Who to send the gift card
    //The test verifies that all information entered has been saved correctly
    @Test
    public void test09_verifyCorrectInformation(){
        myTests.log(Status.INFO,""+name.getMethodName()+":Test start");
        senderReceiver =new senderReceiverPage(driver);
        Assert.assertEquals(senderReceiver.getSender_Name(),senderReceiver.getGiftSenderName().getText());
        myTests.log(Status.INFO,"The sender's name has been saved correctly");
        Assert.assertEquals(senderReceiver.getReceiver_Name(),senderReceiver.getGiftReceiverName().getText());
        myTests.log(Status.INFO,"The receiver's name has been saved correctly");
        Assert.assertEquals(senderReceiver.getBlessing_Text(),senderReceiver.getGiftBlessingText().getText());
        myTests.log(Status.INFO,"The blessings were kept properly");
        myTests.log(Status.PASS,"Test:"+name.getMethodName());

    }

    //BuyMe site - Who to send the gift card --> How to send the gift card?
    //The test sends the gift by SMS.
    //Puts in the required details and goes to make a payment
    @Test
    public void test10_smsOption(){
        myTests.log(Status.INFO,""+name.getMethodName()+":Test start");
        sendBySms = new sendBySms(driver);
        sendBySms.sendGiftBySms();

    }

    //Take screenshot function
    private static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
        extent.flush();
    }

}
