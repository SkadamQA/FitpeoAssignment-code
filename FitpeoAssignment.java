package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.time.Duration;

public class FitpeoAssignment {
    // Declare WebDriver and JavaScriptExecutor variables
    private WebDriver driver;
    private JavascriptExecutor js;

    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }
    // Cleanup WebDriver
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    public void updateTextFieldAndVerify(int desiredValue) throws InterruptedException {
        WebElement textField = driver.findElement(By.xpath("//input[@type='number' and contains(@class,'MuiInputBase-input')]"));
        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));

        // Clear and set the desired value in the text field
        textField.clear();
        // With the help of sendkeys slider is set to max value
//        textField.sendKeys(String.valueOf(desiredValue));

        Thread.sleep(2000);

        // With the help of Javascript i am able to fill desired value in textfield but value is not updating
        js.executeScript(
                "arguments[0].value = arguments[1]; " +
                        "arguments[0].dispatchEvent(new Event('input')); " +
                        "arguments[0].dispatchEvent(new Event('change')); " +
                        "arguments[0].dispatchEvent(new Event('blur'));",
                textField, desiredValue
        );


        // Validate that the slider value matches the desired value
        String sliderValue = slider.getAttribute("value");
        System.out.println("Slider Value: " + sliderValue);
        if (Integer.parseInt(sliderValue) == desiredValue) {
            System.out.println("Test Passed: Slider value matches desired value!");
        } else {
            System.out.println("Test Failed: Slider value does not match!");
        }
    }
    // Set slider value action class and verify
    public void setSliderValueAndVerify(int desiredValue, int min, int max) throws InterruptedException {

        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));

        // Calculate the slider offset
        int sliderWidth = slider.getSize().getWidth();
        int xOffset = (int) (((desiredValue - min) / (double) (max - min)) * sliderWidth);

        // Use Actions class to drag the slider to the desired position
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(xOffset - (sliderWidth / 2), 0)
                .release()
                .perform();
        // Get the current value of the slider
        int currentValue = Integer.parseInt(slider.getAttribute("value"));
        // Loop until the slider value matches the desired value
        while (currentValue != desiredValue) {
            // If the current value is greater than the desired value, press the down arrow key
            if (currentValue > desiredValue) {
                slider.sendKeys(Keys.ARROW_DOWN);

            } else if (currentValue < desiredValue) {
                slider.sendKeys(Keys.ARROW_UP);
            }
            currentValue = Integer.parseInt(slider.getAttribute("value"));
        }
        String sliderValue = slider.getAttribute("value");
        System.out.println("Slider Value after adjustment: " + sliderValue);
        if (Integer.parseInt(sliderValue) == desiredValue) {
            System.out.println("Test Passed: Slider set to desired value!");
        } else {
            System.out.println("Test Failed: Slider not set correctly!");
        }
        Thread.sleep(3000);
    }
    // Navigate to Revenue Calculator page
    public void navigateToRevenueCalculator() {
        driver.get("https://www.fitpeo.com");
        WebElement revenueCalculatorCTA= driver.findElement(By.xpath("//div[@class='satoshi MuiBox-root css-5ty6tm' and contains(text(),'Revenue Calculator')]"));
        revenueCalculatorCTA.click();
        System.out.println("Navigated to Revenue Calculator page");
    }

    // Scroll to slider
    public void scrollToSlider() throws InterruptedException {
        js.executeScript("window.scrollBy(0, 400);");
        Thread.sleep(2000);
    }

    public void scrollToProductCart() throws InterruptedException {
        js.executeScript("window.scrollBy(0, 800);");
        Thread.sleep(2000);
    }
    public void selectCheckBoxOfProduct091() throws InterruptedException {
        WebElement CPT091=driver.findElement(By.xpath("//p[contains(text(), 'CPT-99091')]/following::input[@type='checkbox'][1]"));
        // Scroll to the Product
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",CPT091);
        CPT091.click();
        System.out.println("CPT-99091 Selected");
        Assert.assertTrue(CPT091.isSelected());
        Thread.sleep(2000);
    }
    public void selectCheckBoxOfProduct453() throws InterruptedException {
        WebElement CPT453=driver.findElement(By.xpath("//p[contains(text(), 'CPT-99453')]/following::input[@type='checkbox'][1]"));
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",CPT453);
        CPT453.click();
        System.out.println("CPT-99453 Selected");
        Assert.assertTrue(CPT453.isSelected());
        Thread.sleep(2000);
    }
    public void selectCheckBoxOfProduct454() throws InterruptedException {
        WebElement CPT454=driver.findElement(By.xpath("//p[contains(text(), 'CPT-99454')]/following::input[@type='checkbox'][1]"));
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",CPT454);
        CPT454.click();
        System.out.println("CPT-99454 Selected");
        Assert.assertTrue(CPT454.isSelected());
        Thread.sleep(2000);
    }

    public void selectCheckBoxOfProduct474() throws InterruptedException {
        WebElement CPT474=driver.findElement(By.xpath("//p[contains(text(), 'CPT-99474')]/following::input[@type='checkbox'][1]"));
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",CPT474);
        CPT474.click();
        System.out.println("CPT-99474 Selected");
        Assert.assertTrue(CPT474.isSelected());
        Thread.sleep(2000);
    }

    public void ValidateTotalRecurringReimbursement(String val){
        WebElement TotalValue=driver.findElement(By.xpath("//p[contains(@class,'css-1xroguk') and contains(text(),'Total Recurring Reimbursement')]"));
        String Value=TotalValue.getText();
        String[] parts=Value.split(":");
        String amount = parts[1].trim();
        System.out.println(amount);
        Assert.assertEquals(amount,val);
    }





    public static void main(String[] args) throws InterruptedException {
        FitpeoAssignment test=new FitpeoAssignment();
        try {
            // Setup WebDriver
            test.setup();

            // Navigate to Revenue Calculator page
            test.navigateToRevenueCalculator();

            // Scroll to slider and update text field
            test.scrollToSlider();

            // Set slider value and verify
            System.out.println("Running Test: Set Slider Value and Verify");
            test.setSliderValueAndVerify(820, 0, 2000);

            // Update text field and verify
            System.out.println("Running Test: Update Text Field and Verify Slider");
            test.updateTextFieldAndVerify(560);

            test.scrollToProductCart();
            // Click of Checkbox of Respective Product
            test.selectCheckBoxOfProduct091();
            test.selectCheckBoxOfProduct453();
            test.selectCheckBoxOfProduct454();
            test.selectCheckBoxOfProduct474();
            // Validate Total Recurring Reimbursement for all Patients Per Month
            test.ValidateTotalRecurringReimbursement("$110700");




        } finally {
            // Tear down WebDriver
            test.tearDown();
        }
    }


    }

