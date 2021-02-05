import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AssignmentFlipkart
{
    WebDriver driver=null;
    @BeforeTest
    public void setupTest()
    {
        String projectPath=System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",projectPath+"/src/main/resources/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.myntra.com/");
    }

    @Test
    public void test()
    {
        clickingOnCategoryJeans();
        clickingCheckboxBrand();
        clickingPriceCheckbox();
        clickingColorCheckbox();
        clickingOnProduct();
        int priceOfProduct=clickingOnJeansSize();
        clickingOnAddToBagButton(priceOfProduct);

    }

    public void clickingOnCategoryJeans()
    {
        try
        {
            JavascriptExecutor js=(JavascriptExecutor) driver;
            WebElement elementJeans=driver.findElement(By.xpath("//div[@class='text-banner-container']//following::h4[text()='Categories To Bag']"));
            js.executeScript("arguments[0].scrollIntoView();",elementJeans);
            List<WebElement> elements=driver.findElements(By.xpath("//img[@class='image-image undefined image-hand']"));
            elements.get(2).click();
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }
    public void clickingCheckboxBrand()
    {
        try
        {
            driver.findElement(By.xpath("//input[@value='Roadster']/parent::label")).click();
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }

    public void clickingPriceCheckbox()
    {
        try
        {
            WebDriverWait wait=new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='price-list']/li/label")));
            List<WebElement> elementPrices=driver.findElements(By.xpath("//ul[@class='price-list']/li/label"));
            elementPrices.get(0).click();
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }

    public void clickingColorCheckbox()
    {
        try
        {
            driver.findElement(By.xpath("//span[@data-colorhex='green']/parent::label")).click();
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }

    public void clickingOnProduct()
    {
        try
        {
            List<WebElement> elementProduct=driver.findElements(By.xpath("//a[@target='_blank']"));
            elementProduct.get(0).click();
            Set<String> windows=driver.getWindowHandles();
            Iterator<String> it=windows.iterator();
            String parentId=it.next();
            String childId=it.next();
            driver.switchTo().window(childId);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }


    }
    public int clickingOnJeansSize()
    {
        int productPrice=0;
        try
        {
            List<WebElement> elementJeansSize=driver.findElements(By.xpath("//button[@class='size-buttons-size-button size-buttons-size-button-default']"));
            elementJeansSize.get(2).click();
            String productJeansPrice=driver.findElement(By.xpath("//span[@class='pdp-price']/strong")).getText().replace("Rs","");
            System.out.println(productJeansPrice);
            String parts[]=productJeansPrice.split("\\.");
            System.out.println("hi="+parts[1]);
            String gettingJeansPrice=parts[1];
            productPrice=Integer.parseInt(gettingJeansPrice.trim());
            System.out.println("hi1="+productPrice);

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return productPrice;
    }
    public void clickingOnAddToBagButton(int productValue)
    {
        try
        {
            driver.findElement(By.xpath("//div[@class='pdp-description-container']/div[3]/div/div[1]")).click();
            Thread.sleep(3000);
            List<WebElement> elementBag=driver.findElements(By.xpath("//div[@id='sizeButtonsContainer']/following::div/div/a"));
            elementBag.get(0).click();
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }
}