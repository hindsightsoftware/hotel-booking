package outside.ui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BookingPage extends Page {

    @FindBy(how = How.ID, using = "firstname")
    private WebElement txtFirstname;

    @FindBy(how = How.ID, using = "lastname")
    private WebElement txtLastname;

    @FindBy(how = How.ID, using = "totalprice")
    private WebElement txtPrice;

    @FindBy(how = How.ID, using = "depositpaid")
    private WebElement txtDeposit;

    @FindBy(how = How.ID, using = "additionalneeds")
    private WebElement txtAdditional;

    @FindBy(how = How.ID, using = "checkin")
    private WebElement txtCheckin;

    @FindBy(how = How.ID, using = "checkout")
    private WebElement txtCheckout;

    @FindBy(how = How.CSS, using = ".addButton")
    private WebElement btnSubmit;

    @FindBy(how = How.CSS, using = "#bookings .row")
    private List<WebElement> listRows;

    @FindBy(how = How.CSS, using = ".glyphicon-remove")
    private List<WebElement> btnDeleteList;

    @FindBy(how = How.CSS, using = ".glyphicon-edit")
    private List<WebElement> btnEditList;

    @FindBy(how = How.CSS, using = ".editfirstname")
    private WebElement txtEditFirstname;

    @FindBy(how = How.CSS, using = ".editlastname")
    private WebElement txtEditLastname;

    @FindBy(how = How.CSS, using = ".edittotalprice")
    private WebElement txtEditPrice;

    @FindBy(how = How.CSS, using = ".editdepositpaid")
    private WebElement txtEditDeposit;

    @FindBy(how = How.CSS, using = ".editadditionalneeds")
    private WebElement textEditAdditonalNeeds;

    @FindBy(how = How.CSS, using = ".editcheckin")
    private WebElement txtEditCheckin;

    @FindBy(how = How.CSS, using = ".editcheckout")
    private WebElement txtEditCheckout;

    @FindBy(how = How.CSS, using = ".glyphicon-ok")
    private WebElement btnConfirmEdit;

    private WebDriverWait wait;

    public BookingPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 10);
    }

    public void setFirstname(String entry){
        txtFirstname.sendKeys(entry);
    }

    public void setLastname(String entry) {
        txtLastname.sendKeys(entry);
    }

    public void setPrice(String entry) {
        txtPrice.sendKeys(entry);
    }

    public void setDeposit(String entry) {
        txtDeposit.sendKeys(entry);
    }

    public void setAdditional(String entry) {
        txtAdditional.sendKeys(entry);
    }

    public void setCheckin(String entry) {
        txtCheckin.sendKeys(entry);
    }

    public void setCheckout(String entry) {
        txtCheckout.sendKeys(entry);
    }

    public void clickSubmit() {
        btnSubmit.click();
    }

    public List<WebElement> getRows() throws InterruptedException {
        return listRows;
    }

    public void waitForRowCount(int count) {
        wait.until((ExpectedCondition<Boolean>) driver -> {
            int elementCount = driver.findElements(By.cssSelector(".glyphicon-edit")).size();
            return elementCount == count;
        });
    }

    public void clickDeleteBooking(int index) {
        btnDeleteList.get(index).click();
    }

    public void clickEditBooking(int index) {
        btnEditList.get(index).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".editfirstname")));
    }

    public void setEditFirstname(String editFirstname) {
        txtEditFirstname.clear();
        txtEditFirstname.sendKeys(editFirstname);
    }

    public void setEditLastname(String editLastname) {
        txtEditLastname.clear();
        txtEditLastname.sendKeys(editLastname);
    }

    public void setEditPrice(String editPrice) {
        txtEditPrice.clear();
        txtEditPrice.sendKeys(editPrice);
    }

    public void setEditDeposit(String editDeposit) {
        txtEditDeposit.clear();
        txtEditDeposit.sendKeys(editDeposit);
    }

    public void setEditAdditional(String editAdditional) {
        textEditAdditonalNeeds.clear();
        textEditAdditonalNeeds.sendKeys(editAdditional);
    }

    public void setEditCheckin(String editCheckin) {
        txtEditCheckin.clear();
        txtEditCheckin.sendKeys(editCheckin);
    }

    public void setEditCheckout(String editCheckout) {
        txtEditCheckout.clear();
        txtEditCheckout.sendKeys(editCheckout);
    }

    public void clickEditSubmit() {
        btnConfirmEdit.click();
    }
}
