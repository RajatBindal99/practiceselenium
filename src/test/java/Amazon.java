import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Amazon
{
    static WebDriver driver=null;
    @BeforeTest
    public static void openingAmazon()
    {
        String projectPath=System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",projectPath+"/src/main/resources/chromedriver.exe");
        driver=new ChromeDriver();
    }
    @AfterTest
    public static void closeBrowser()
    {
        driver.quit();

    }
}
