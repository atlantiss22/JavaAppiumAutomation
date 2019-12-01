package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = '{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
            SEARCH_DEFAULT_EMPTY_RESULT_ELEMENT = "org.wikipedia:id/search_empty_image";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button!", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present!", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), searchLine, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(searchResultXpath), "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(searchResultXpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles() {

        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public List<WebElement> getAllResultsOfSearchOnFirstPage() {
        return this.waitForElementsPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Cannot find results of search", 15);
    }

    public void checkAllResultsWithText(List<WebElement> results, String expectedSubstring) {
        expectedSubstring = expectedSubstring.toLowerCase();
        for (int i = 0; i < results.size(); i++) {
            //Внутри результата есть заголовок и подзаголовок (или только заголовок) *или редирект
            //=> Ищем любой элемент, который может содержать искомое слово внутри результата
            List<WebElement> includeElements = results.get(i).findElements(By.xpath("//*"));

            boolean hasText = false;
            for (int j = 0; j < includeElements.size(); j++) {
                if (includeElements.get(j).getText().toLowerCase().contains(expectedSubstring)) {
                    hasText = true;
                }
            }
            Assert.assertTrue(
                    "One or more search results does not contain the search text",
                    hasText
            );
        }
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);
    }

    public void waitForDefaultEmptyResultsLabel() {
        this.waitForElementPresent(By.id(SEARCH_DEFAULT_EMPTY_RESULT_ELEMENT), "Cannot find image of empty search result", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not to find any results"
        );
    }
}
