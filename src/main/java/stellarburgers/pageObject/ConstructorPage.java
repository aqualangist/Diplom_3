package stellarburgers.pageObject;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import com.codeborne.selenide.Condition;

public class ConstructorPage {

    final static public String URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement saucesAnchor;

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']/..")
    private SelenideElement saucesTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunsAnchor;

    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']/..")
    private SelenideElement bunsTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingsAnchor;

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']/..")
    private SelenideElement fillingsTab;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;

    @Step("Click on Sauces")
    public void clickSauces() {
        saucesAnchor.click();
    }

    @Step("Check Sauces tab is opened")
    public void checkSaucesTabIsOpened() {
        saucesTab.shouldHave(Condition.cssClass("tab_tab_type_current__2BEPc"));
    }

    @Step("Click on Buns")
    public void clickBuns() {
        bunsAnchor.click();
    }

    @Step("Check Buns tab is opened")
    public void checkBunsTabIsOpened() {
        bunsTab.shouldHave(Condition.cssClass("tab_tab_type_current__2BEPc"));
    }

    @Step("Click on Fillings")
    public void clickFillings() {
        fillingsAnchor.click();
    }

    @Step("Check Fillings tab is opened")
    public void checkFillingsTabIsOpened() {
        fillingsTab.shouldHave(Condition.cssClass("tab_tab_type_current__2BEPc"));
    }

    @Step("Click login button on main page")
    public void clickLoginButton() {
        loginButton.click();
    }

}
