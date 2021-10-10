package repository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	
	public HomePage(WebDriver navegador) {
		this.driver = navegador;
	}
	
	WebDriver driver;
	String modifiedXPath;
	
	
	public String getModifiedXPath() {
		return modifiedXPath;
	}

	public void setModifiedXPath(String number) {
		String xPath = String.format("//*[@id=\"homefeatured\"]/li[%s]/div/div[2]/div[1]/span", number);
		this.modifiedXPath = xPath;
	}

	By brandLogo = By.xpath("//*[@id=\"header_logo\"]/a/img");
	By searchBox = By.id("search_query_top");
	By homeFeatured = By.id("homefeatured");
	By listaElementos = By.tagName("li");
	By ventanaPopUp = By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2");
	By addToCartButton = By.linkText("Add to cart");
	By mensajeTituloVentana = By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/h2");
	By mensajeTituloCarrito = By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a");
	By botonContinueShopping = By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span");
	
	public WebElement continueShoppingElement() {
		return driver.findElement(botonContinueShopping);
	}
	
	public WebElement tituloCarritoElement() {
		return driver.findElement(mensajeTituloCarrito);
	}

	public WebElement tituloVentanaElement() {
		return driver.findElement(mensajeTituloVentana);
	}
	
	public WebElement addToCartElement(WebElement productoLista) {
		return productoLista.findElement(addToCartButton);
	}
	
	public WebElement productoAleatorioElement(String xPath) {
		By producto = By.xpath(xPath);
		return driver.findElement(producto);
	}
	
	public WebElement brandLogoElement() {
		return driver.findElement(brandLogo);
	}
	
	//Si quieres buscar una palabra
	public void searchBoxQuery(String wordToSearch) {
		driver.findElement(searchBox).sendKeys(wordToSearch);
	}
	
	public WebElement homeFeaturedElement() {
		return driver.findElement(homeFeatured);
	}
	
	public List<WebElement> listaElementosProducto() {
		WebElement contenedor = this.homeFeaturedElement();
		return contenedor.findElements(listaElementos);
	} 
	
	public WebElement ventanaPopUpElement() {
		return driver.findElement(ventanaPopUp);
	}
}
