package stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellarburgers.pageObject.ConstructorPage;

import static com.codeborne.selenide.Selenide.open;

public class ConstructorTest {

    @Test
    @DisplayName("Buns section is opened when opening main page")
    public void checkBunsTabIsOpenedWhenOpeningMainPageTest() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.checkBunsTabIsOpened();
    }

    @Test
    @DisplayName("List scrolls to Sauces section when clicking on Sauces")
    public void checkSaucesAnchorOpensSaucesSectionTest() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);

        constructorPage.clickSauces();
        constructorPage.checkSaucesTabIsOpened();
    }

    @Test
    @DisplayName("List scrolls to Fillings section when clicking on Fillings")
    public void checkFillingsAnchorOpensFillingsSectionTest() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);

        constructorPage.clickFillings();
        constructorPage.checkFillingsTabIsOpened();
    }

}
