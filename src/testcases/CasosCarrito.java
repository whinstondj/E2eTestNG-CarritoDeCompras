package testcases;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CasosCarrito extends Base {
	
	@BeforeSuite
	public void AbrirNavegador(ITestContext context) {
		navegador.get("http://automationpractice.com/index.php");
		navegador.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		Dimension driverSize = new Dimension(1170, 700);
		navegador.manage().window().setSize(driverSize);
		context.setAttribute("WebDriver", navegador);
	}
	
	@Test
	public void elegirArticulosAzarCarritoCompras() {
		Assert.assertEquals(navegador.getTitle(), "My Store");
		listaDeProductos = repoHomePage.listaElementosProducto();
		//System.out.println(listaDeProductos.size());
		
		if (listaDeProductos.size()<4) {
			cantidadLista = listaDeProductos.size();
		} else {
			cantidadLista = 3;
		}
		
		for (int i = 0; i<cantidadLista; i++) {
			numeroAlAzar = (int) (Math.random() * listaDeProductos.size() +1);
			if (productosAlAzar.contains(numeroAlAzar)) {
				i--;
			}else {
				productosAlAzar.add(numeroAlAzar);
			}
		}
		
		productosAlAzar.forEach(System.out::println);	
	}
	
	@Test(dependsOnMethods={"elegirArticulosAzarCarritoCompras"})
	public void anadirProductosAlCarrito() {
		for (int i=0; i < cantidadLista; i++) {			                        
			//String xPathElemento = String.format("//*[@id=\"homefeatured\"]/li[%s]/div/div[2]/div[1]/span", productosAlAzar.get(i));
			//String precioUnitarioDolar = navegador.findElement(By.xpath(xPathElemento)).getText();
			repoHomePage.setModifiedXPath(productosAlAzar.get(i).toString());
			String precioUnitarioDolar = repoHomePage.productoAleatorioElement(repoHomePage.getModifiedXPath()).getText();
			String precioUnitario = precioUnitarioDolar.replace("$", "");
			System.out.println(precioUnitario);
			Double precio = Double.valueOf(precioUnitario);
			System.out.println("Este es el valor unitario del producto: " + precio);
			totalProductos += precio;
			System.out.println("Este es el total de los valores: " + totalProductos);
			//listaDeProductos.get(productosAlAzar.get(i)-1).findElement(By.linkText("Add to cart")).click();
			repoHomePage.addToCartElement(listaDeProductos.get(productosAlAzar.get(i)-1)).click();
			
			if (i==0) {
				mensajeDeVentana = "There is 1 item in your cart.";
				mensajeCarrito = "Cart 1 Product";
			} else {
				mensajeDeVentana = String.format("There are %s items in your cart.", i+1);
				mensajeCarrito = String.format("Cart %s Products", i+1);
			}
			
			WebElement ventanaDeProducto = espera.until(ExpectedConditions.visibilityOf(repoHomePage.ventanaPopUpElement()));
			
			//Comparaciones
			Assert.assertEquals(repoHomePage.ventanaPopUpElement().getText(), "Product successfully added to your shopping cart");
			
			Assert.assertEquals(navegador.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/h2")).getText(), mensajeDeVentana);
			Assert.assertEquals(navegador.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a")).getText(), mensajeCarrito);
			
			navegador.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span")).click();
			Boolean ventanaDesaparecida = espera.until(ExpectedConditions.invisibilityOf(repoHomePage.ventanaPopUpElement()));
		}
	}
	
	@Test(dependsOnMethods={"anadirProductosAlCarrito"})
	public void verificarProductos () {
		//Verificaciones Finales del caso de Prueba
		Actions mouseActions = new Actions(navegador);
		WebElement cartOptions = navegador.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a"));
		mouseActions.moveToElement(cartOptions).perform();
		totalProductos += 2.00;
		
		//Codigo para crar dos decimales
		DecimalFormat df2 = new DecimalFormat("#.00");
		
		totalEnString = String.format("$%s", df2.format(totalProductos));
		totalEnString = totalEnString.replace(",", ".");
		
		//Assert.assertTrue(false);
		
		WebElement ventanaTotal = espera.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/div/div[2]/span[1]")));
		Assert.assertEquals(navegador.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/div/div[2]/span[1]")).getText(), totalEnString);
		
		WebElement botonCheckout = espera.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"button_order_cart\"]")));
		
		SoftAssert validaciones = new SoftAssert();
		
		//validaciones.assertTrue(false);
		validaciones.assertTrue(true);
		
		//validaciones.assertAll();
	}
	
	@Test(dependsOnMethods= {"verificarProductos"})
	public void verificarCheckOut() {
		navegador.findElement(By.xpath("//*[@id=\"button_order_cart\"]")).click();
		Assert.assertEquals(navegador.getTitle(), "Order - My Store");
		String primerTitulo = navegador.findElement(By.xpath("//*[@id=\"cart_title\"]")).getText();
		//SHOPPING-CART SUMMARY
		primerTitulo = primerTitulo.substring(0, 21);
		Assert.assertEquals(primerTitulo, "SHOPPING-CART SUMMARY");
		Assert.assertEquals(navegador.findElement(By.xpath("//*[@id=\"total_price_container\"]")).getText(), totalEnString);
		navegador.findElement(By.linkText("Proceed to checkout")).click();
		Assert.assertEquals(navegador.getTitle(), "Login - My Store");
	}
	
	@AfterSuite
	public void cerrarNavegador() {
		navegador.close();
	}

}
