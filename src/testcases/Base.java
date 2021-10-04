package testcases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	WebDriver navegador = new ChromeDriver();
	WebDriverWait espera = new WebDriverWait(navegador , 20);
	List<WebElement> listaDeProductos = new ArrayList<WebElement>();
	int cantidadLista;
	int numeroAlAzar;
	List<Integer> productosAlAzar = new ArrayList<Integer>();
	double totalProductos = 0;
	
	String mensajeDeVentana;
	String mensajeCarrito;
}
