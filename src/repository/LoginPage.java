package repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public LoginPage(WebDriver navegador) {
		this.driver = navegador;
	}
	
	WebDriver driver;

	By cartTitle = By.xpath("//*[@id=\"cart_title\"]");
	By priceContainer = By.xpath("//*[@id=\"total_price_container\"]");
	By proceedToCheckOut = By.linkText("Proceed to checkout");

	public WebElement proceedToCheckoutElement() {
		return driver.findElement(proceedToCheckOut);
	}
	public WebElement priceContainerElement() {
		return driver.findElement(priceContainer);
	}
	
	public WebElement cartTitleElement() {
		return driver.findElement(By.xpath("//*[@id=\"cart_title\"]"));
	}
}
