package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ConfigReader;

public abstract class BasePage {

	protected final WebDriver driver;
	protected final WebDriverWait wait;
	protected final Actions actions;
	protected final JavascriptExecutor js;

	protected BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));

		this.actions = new Actions(driver);
		this.js = (JavascriptExecutor) driver;
	}

	/*---------------------------------------
	Element Helpers
	---------------------------------------*/

	/**
	 * Returns a single WebElement.
	 */
	protected WebElement getElement(final By locator) {
		return driver.findElement(locator);
	}

	/**
	 * Returns list of matching elements.
	 */
	protected List<WebElement> getElements(final By locator) {
		return driver.findElements(locator);
	}

	/*---------------------------------------
	Wait Methods
	---------------------------------------*/

	/**
	 * Waits until an element becomes visible.
	 *
	 * @param locator element locator
	 * @return visible WebElement
	 */

	protected WebElement waitForVisibility(final By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Waits until an element becomes clickable.
	 */
	protected WebElement waitForClickability(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * Waits until an element is present in DOM.
	 */
	protected WebElement waitForPresence(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * Waits until an element disappears.
	 */
	protected boolean waitForInvisibility(By locator) {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	protected boolean waitForUrlContains(String partialUrl) {
		return wait.until(ExpectedConditions.urlContains(partialUrl));
	}

	protected boolean waitForTitleContains(String title) {
		return wait.until(ExpectedConditions.titleContains(title));
	}

	protected boolean waitForTextToBe(By locator, String expectedText) {
		return wait.until(ExpectedConditions.textToBe(locator, expectedText));
	}

	/*---------------------------------------
	Element Actions
	---------------------------------------*/

	/**
	 * Clicks on an element after waiting for it to become clickable.
	 *
	 * @param locator element locator
	 */
	protected void click(final By locator) {
		waitForClickability(locator).click();
	}

	/**
	 * Enters text into an input field.
	 *
	 * @param locator element locator
	 * @param text    text to enter
	 */
	protected void type(final By locator, final String text) {
		waitForVisibility(locator).sendKeys(text);
	}

	/**
	 * Clears an input field before entering text.
	 *
	 * @param locator element locator
	 * @param text    text to enter
	 */
	protected void clearAndType(final By locator, final String text) {
		WebElement element = waitForVisibility(locator);
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * Returns the visible text of an element.
	 *
	 * @param locator element locator
	 * @return element text
	 */
	protected String getText(final By locator) {
		return waitForVisibility(locator).getText();
	}

	/**
	 * Checks whether an element is displayed.
	 *
	 * @param locator element locator
	 * @return true if displayed
	 */
	protected boolean isDisplayed(final By locator) {
		final WebElement element = findVisibleElement(locator);
		return element != null && element.isDisplayed();
	}

	/**
	 * Checks whether an element is enabled.
	 */
	protected boolean isEnabled(final By locator) {
		final WebElement element = findVisibleElement(locator);
		return element != null && element.isEnabled();
	}

	/**
	 * Checks whether an element is selected.
	 */
	protected boolean isSelected(final By locator) {
		final WebElement element = findVisibleElement(locator);
		return element != null && element.isSelected();
	}

	/*
	 * Instead of duplicating the same try-catch in three above methods
	 * (isDisplayed(), isEnabled(), isSelected()), we can centralize it with a
	 * helper:
	 */

	protected WebElement findVisibleElement(final By locator) {
		try {
			return waitForVisibility(locator);
		} catch (TimeoutException | NoSuchElementException e) {
			return null;
		}
	}

	/*---------------------------------------
	// these methods wrap common browser level operations (Browser Actions)
	---------------------------------------*/

	/**
	 * Returns the current page title.
	 *
	 * @return page title
	 */
	protected String getPageTitle() {
		return driver.getTitle();
	}

	/**
	 * Returns the current URL.
	 *
	 * @return current URL
	 */
	protected String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Refreshes the current page.
	 */
	protected void refreshPage() {
		driver.navigate().refresh();
	}

	/**
	 * Navigates to the previous page.
	 */
	public void navigateBack() {
		driver.navigate().back();
	}

	/**
	 * Navigates to the next page.
	 */
	protected void navigateForward() {
		driver.navigate().forward();
	}

	/*---------------------------------------
	JavaScript Actions (these methods are useful when Selenium native interactions are not sufficient)
	---------------------------------------*/
	/**
	 * Clicks an element using JavaScript.
	 *
	 * @param locator element locator
	 */
	protected void jsClick(final By locator) {
		js.executeScript("arguments[0].click();", waitForVisibility(locator));
	}

	/**
	 * Scrolls an element into view.
	 *
	 * @param locator element locator
	 */
	protected void scrollIntoView(final By locator) {
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", waitForVisibility(locator));
	}

	protected void scrollToTop() {
		js.executeScript("window.scrollTo(0,0)");
	}

	protected void scrollToBottom() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/*---------------------------------------
	Mouse Actions
	---------------------------------------*/

	protected void hover(final By locator) {
		actions.moveToElement(waitForVisibility(locator)).perform();
	}

	protected void doubleClick(final By locator) {
		actions.doubleClick(waitForClickability(locator)).perform();
	}

	protected void rightClick(final By locator) {
		actions.contextClick(waitForClickability(locator)).perform();
	}

	/*---------------------------------------
	Select Class (Dropdown Actions)
	---------------------------------------*/

	protected void selectByVisibleText(final By locator, final String text) {

		new Select(waitForVisibility(locator)).selectByVisibleText(text);
	}

	protected void selectByValue(final By locator, final String value) {

		new Select(waitForVisibility(locator)).selectByValue(value);
	}

	protected void selectByIndex(final By locator, final int index) {

		new Select(waitForVisibility(locator)).selectByIndex(index);
	}

	protected String getSelectedOption(final By locator) {

		return new Select(waitForVisibility(locator)).getFirstSelectedOption().getText();
	}

	/*---------------------------------------
	Utility methods
	---------------------------------------*/

	protected String getAttribute(final By locator, final String attribute) {

		return waitForVisibility(locator).getAttribute(attribute);
	}

	protected String getCssValue(final By locator, final String property) {

		return waitForVisibility(locator).getCssValue(property);
	}

}