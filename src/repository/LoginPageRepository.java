package repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageRepository {

	public LoginPageRepository(WebDriver navegador) {
		this.driver = navegador;
		PageFactory.initElements(driver, this);
	}
	
	WebDriver driver;

	@FindBy(xpath="//*[@id=\"cart_title\"]")
	WebElement cartTitle;
	
	@FindBy(xpath="//*[@id=\"total_price_container\"]")
	WebElement priceContainer;
	
	@FindBy(linkText="Proceed to checkout")
	WebElement proceedToCheckOut;

	public WebElement proceedToCheckoutElement() {
		return proceedToCheckOut;
	}
	public WebElement priceContainerElement() {
		return priceContainer;
	}
	
	public WebElement cartTitleElement() {
		return cartTitle;
	}
}
