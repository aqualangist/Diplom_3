package stellarburgers.pageObject;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class PersonalAccount {

    public static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    @FindBy(how = How.CLASS_NAME, using = "Account_button__14Yp3")
    private SelenideElement logoutButton;

    @Step("Click logout button")
    public void clickLogoutButton() {
        logoutButton.click();
    }

    @Step("Check that URL is login page")
    public void checkURLIsLoginPage() {
        webdriver().shouldHave(url(LoginPage.URL));
    }

}
