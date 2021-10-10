package repository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class HomePageRepository {
	
	
	public HomePageRepository(WebDriver navegador) {
		this.driver = navegador;
		PageFactory.initElements(driver, this);
	}
	
	WebDriver driver;
	String modifiedXPath;
	
	@FindBy(id="homefeatured")
	WebElement homeFeatured;
	
	@FindBys({@FindBy(tagName="li")})
	List<WebElement> listaElementos;
	
	@FindBy(xpath= "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")
	WebElement ventanaPopUp;
	
	@FindBy(linkText="Add to cart")
	WebElement addToCartButton;
	
	@FindBy(xpath = "//*[@id=\"header_logo\"]/a/img")
	WebElement brandLogo;
	
	@FindBy(id="search_query_top")
	WebElement searchBox;
	
	@FindBy(xpath="//*[@id=\"layer_cart\"]/div[1]/div[2]/h2")
	WebElement mensajeTituloVentana;
	
	@FindBy(xpath="//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a")
	WebElement mensajeTituloCarrito;
	
	@FindBy(xpath="//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span")
	WebElement botonContinueShopping;
	
	@FindBy(xpath="//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a")
	WebElement cartOptions;
	
	@FindBy(xpath="//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/div/div[2]/span[1]")
	WebElement ventanaTotal;
	
	
	@FindBy(xpath="//*[@id=\"button_order_cart\"]")
	WebElement botonCheckOut;
	
	
	public WebElement homeFeaturedElement() {
		return homeFeatured;
	}

	public WebElement addToCartElement(WebElement productoLista) {
		PageFactory.initElements(new DefaultElementLocatorFactory(productoLista), this);
		return addToCartButton;
	}

	
	public List<WebElement> listaElementosProducto() {
		PageFactory.initElements(new DefaultElementLocatorFactory(this.homeFeaturedElement()), this);
		return listaElementos;
	} 

	public WebElement ventanaPopUpElement() {
		return ventanaPopUp;
	}

	
	public String getModifiedXPath() {
		return modifiedXPath;
	}
	
	public void setModifiedXPath(String number) {
		String xPath = String.format("//*[@id=\"homefeatured\"]/li[%s]/div/div[2]/div[1]/span", number);
		this.modifiedXPath = xPath;
	}
	
	public WebElement botonCheckOutElement() {
		return botonCheckOut;
	}
	
	public WebElement ventanaTotalElement() {
		return ventanaTotal;
	}
	
	public WebElement cartOptionsElements() {
		return cartOptions;
	}
	
	public WebElement continueShoppingElement() {
		return botonContinueShopping;
	}
	
	public WebElement tituloCarritoElement() {
		return mensajeTituloCarrito;
	}

	public WebElement tituloVentanaElement() {
		return mensajeTituloVentana;
	}
		
	public WebElement productoAleatorioElement(String xPath) {		
		By producto = By.xpath(xPath);
		return driver.findElement(producto);
	}
	
	public WebElement brandLogoElement() {
		return brandLogo;
	}
	
	//Si quieres buscar una palabra
	public void searchBoxQuery(String wordToSearch) {
		searchBox.sendKeys(wordToSearch);
	}
		
}
