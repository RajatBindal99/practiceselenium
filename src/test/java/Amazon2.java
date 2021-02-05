import com.sun.deploy.cache.BaseLocalApplicationProperties;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Amazon2 extends Amazon
{
    @Test
    public static void test()
    {
        amazonTest();
    }
    public static void amazonTest()
    {
        driver.get("https://www.amazon.in/");
        driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("laptop");
        driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();

    }
}
