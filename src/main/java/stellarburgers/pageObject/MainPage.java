package stellarburgers.pageObject;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class MainPage {

    @FindBy(how = How.XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']/a")
    private SelenideElement logo;

    @FindBy(how = How.XPATH, using = ".//nav/a[@href='/account']")
    private SelenideElement personalAccountButton;

    @FindBy(how = How.XPATH, using = ".//li[1]/a[@href='/']")
    private SelenideElement constructorButton;

    @Step("Click on personal account button in header")
    public void clickPersonalAccountButton() {
        personalAccountButton.click();
    }

    @Step("Check that URL is login in account page")
    public void checkURLIsLoginPage() {
        webdriver().shouldHave(url(LoginPage.URL));
    }

    @Step("Check that URL is profile in account page")
    public void checkURLIsProfilePage() {
        webdriver().shouldHave(url(PersonalAccount.URL));
    }

    @Step("Check that URL is main page")
    public void checkURlIsMainPage() {
        webdriver().shouldHave(url(ConstructorPage.URL));
    }

    public void checkButtonHasHrefToAccount() {
        personalAccountButton.shouldHave(Condition.href("/account"));
    }

    @Step("Click on constructor button")
    public void clickConstructorButton() {
        constructorButton.click();
    }

    @Step("Click on logo")
    public void clickLogo() {
        logo.click();
    }

}
