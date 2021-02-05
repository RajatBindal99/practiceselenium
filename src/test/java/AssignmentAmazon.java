import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AssignmentAmazon
{
    WebDriver driver=null;
    String a;

    @BeforeTest
    public void setupTest()
    {
        try
        {
            String projectPath=System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver",projectPath+"/src/main/resources/chromedriver.exe");
            driver=new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get("https://www.amazon.in/");
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }

    @Test
    public void test()
    {
        searchProduct();
        verifyProductsVisibility();
        clickOnCheckboxBrand();
        clickOnCheckBoxPrice();
        a=clickOnDiscount();
        verifyingPrice();
        addToCart();
        verifySubTotal();
    }



    /**
     *
     */
    public void searchProduct()
    {
        try
        {
            driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Laptop");
            driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }

    public void verifyProductsVisibility()
    {
        try
        {
            List<WebElement> productLaptops=driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
            for(WebElement productLaptop:productLaptops)
            {
                if(productLaptop.getText().contains("Laptop"))
                {
                    System.out.println("All Products Are Laptop");
                }
                else
                {
                    System.out.println("Not Laptop");
                }
            }
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }

    public void clickOnCheckboxBrand()
    {
        try
        {
            driver.findElement(By.xpath("//li[@id='p_89/HP']/span/a/div/label/i")).click();
            List<WebElement> productHpLaptops=driver.findElements(By.xpath("//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']"));
            for(WebElement productHpLaptop:productHpLaptops)
            {
                if(productHpLaptop.getText().contains("HP"))
                {
                    System.out.println("All products Are Of Hp");
                }
                else
                {
                    System.out.println("Not Of Hp");
                }
            }
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }

    public void clickOnCheckBoxPrice()
    {
        try
        {
            driver.findElement(By.xpath("//div[@id='priceRefinements']/ul/li[4]/span/a")).click();
            List<WebElement> productPrice=driver.findElements(By.xpath("//span[@class='a-price-whole']"));
            for(WebElement productPriceComparison:productPrice)
            {
                String productHpPrice=productPriceComparison.getText().replace(",","");
                int ProductHpIntegerPrice=Integer.parseInt(productHpPrice);
                if(ProductHpIntegerPrice<50000)
                {
                    System.out.println("All are under 50K");
                }
                else
                {
                    System.out.println("Not under 50K");
                }
            }
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }

    public String clickOnDiscount()
    {
        String productPriceSameOrNot="";
        //String productModelComparison="";
        try
        {
            WebElement elementDiscount=driver.findElement(By.xpath("//span[text()='Discount']"));
            JavascriptExecutor js=(JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", elementDiscount);
            Thread.sleep(4000);
            driver.findElement(By.xpath("//ul[@aria-labelledby='p_n_pct-off-with-tax-title']/li/span/a")).click();

            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='a-price-whole']")));
            List<WebElement> productPriceSame=driver.findElements(By.xpath("//span[@class='a-price-whole']"));
            productPriceSameOrNot=productPriceSame.get(0).getText().replace(",","");

            List<WebElement> productName=driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
            String modelName=productName.get(0).getText();
            productName.get(0).click();
            Set<String> windows=driver.getWindowHandles();
            Iterator<String> it=windows.iterator();
            String parentId=it.next();
            String childId=it.next();
            driver.switchTo().window(childId);
            String productModelComparison=driver.findElement(By.xpath("//div[@id='titleSection']/h1/span")).getText();
            System.out.println(productModelComparison);
            if(modelName.equalsIgnoreCase(productModelComparison))
            {
                System.out.println("Same product model");
            }
            else
            {
                System.out.println("Not same product model");
            }



            System.out.println("bye");
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return productPriceSameOrNot;
    }

    public void verifyingPrice()
    {
        try
        {
            System.out.println("A is="+a);
            int finalProductPrice=Integer.parseInt(a);
            String productPriceComparison2=driver.findElement(By.xpath("//span[@class='a-size-medium a-color-price priceBlockBuyingPriceString']")).getText().replace("₹","").replace(",","");
            System.out.println("productPrice"+productPriceComparison2);
            String parts[]=productPriceComparison2.split("\\.");
            System.out.println(parts[0]);
            String b=parts[0];
            int finalProductComparison=Integer.parseInt(b.trim());
            System.out.println("b="+b);

            if(finalProductPrice==finalProductComparison)
            {
                System.out.println("same price");
            }
            else
            {
                System.out.println("not same price");
            }
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }
    public void addToCart()
    {
        //driver.findElement(By.xpath("//span[@class='a-button a-spacing-small a-button-primary a-button-icon']")).click();
        driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@aria-labelledby='attach-sidesheet-view-cart-button-announce']")));
        driver.findElement(By.xpath("//input[@aria-labelledby='attach-sidesheet-view-cart-button-announce']")).click();
        String quantity=driver.findElement(By.xpath("//span[@class='a-dropdown-prompt']")).getText();
        System.out.println("quantity="+quantity);
        int quantityOfProduct=Integer.parseInt(quantity);
        if(quantityOfProduct!=0)
        {
            System.out.println("Product Added");
        }
        else
        {
            System.out.println("not added");
        }
    }
    public void verifySubTotal()
    {
        try
        {
            String total=driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']")).getText().replace(",","");
            System.out.println("total=="+total);
            String parts[]=total.split("\\.");
            String totalSplit=parts[0];
            int totalComparison=Integer.parseInt(totalSplit.trim());
            System.out.println("total="+totalComparison);

            /*String sub=driver.findElement(By.xpath("//div[@class='a-row a-spacing-mini sc-subtotal sc-subtotal-activecart sc-java-remote-feature']/span[2]/span")).getText();
            System.out.println("sub="+sub);*/

            List<WebElement> elements=driver.findElements(By.xpath("//span[@class='a-color-price sc-price-container a-text-bold']"));
            String secondElement=elements.get(1).getText().replace(",","");
            String array[]=secondElement.split("\\.");
            String secondElementSplit=array[0];
            int secondElementComparison=Integer.parseInt(secondElementSplit.trim());
            System.out.println("secondElement="+secondElementComparison);

            if(totalComparison==secondElementComparison)
            {
                System.out.println("same SUB TOTAL");
            }
            else
            {
                System.out.println("not same SUB TOTAL");
            }
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }
    @AfterTest
    public void exit()
    {
        driver.quit();
    }

}
