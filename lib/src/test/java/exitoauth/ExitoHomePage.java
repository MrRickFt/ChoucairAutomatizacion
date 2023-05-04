package exitoauth;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExitoHomePage extends BasePage {

	private final String URL = "https://www.exito.com/";
	private final String CART_URL = "https://www.exito.com/checkout-io#/";

	@FindBy(id = "category-menu")
	private WebElement categoriaMenu;

	@FindBy(id = "undefined-nivel2-Tecnología")
	private WebElement tecnologiaCategoria;

	@FindBy(id = "Categorías-nivel3-Computadores portátiles")
	private WebElement portatilesSubCategoria;

	@FindBy(className = "css-1j9dihh-control")
	private WebElement ciudadDropdown;

	@FindBy(className = "css-h54wua-menu")
	private List<WebElement> ciudadList;

	@FindBy(className = "css-1j9dihh-control")
	private List<WebElement> almacenList;

	@FindBy(className = "exito-geolocation-3-x-primaryButtonEnable")
	private WebElement confirmarButton;

	@FindBy(css = "#gallery-layout-container article:not([aria-hidden='true'])")
	private List<WebElement> listaDeProductos;

	public ExitoHomePage(WebDriver driver) {
		super(driver);
	}

	public void navigateToHomePage() throws InterruptedException {
		driver.get(URL);
		WebDriverWait wait = new WebDriverWait(driver, 25);
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}

	public void selectMarketCategory() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(categoriaMenu)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("undefined-nivel2-Tecnología"))).click();
		System.out.println("Categoría seleccionada");
	}

	public void selectLaptopSubCategory() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Categorías-nivel3-Computadores portátiles")))
				.click();
		
		System.out.println("subcategoría seleccionada");
	}

	public void selectCity(int index) {
		wait.until(ExpectedConditions.visibilityOf(ciudadDropdown)).click();
		wait.until(ExpectedConditions.visibilityOf(ciudadList.get(index))).click();
	}

	public void selectStore(int index) throws InterruptedException {
		List<WebElement> elementos = driver.findElements(By.className("css-1j9dihh-control"));
		System.out.println("Número de elementos encontrados: " + elementos.size());
		if (elementos.size() > 0) {
			WebElement elemento1 = elementos.get(0); // El primer elemento
			elemento1.click();
		} else {
			System.out.println("No se encontraron elementos con la clase css-1j9dihh-control.");
		}

		// Listado de almacenes disponibles
		List<WebElement> almacenList = new ArrayList<WebElement>();
		almacenList = driver.findElements(By.className("css-h54wua-menu"));

		for (WebElement element : almacenList) {
			System.out.print(element.getText());

		}

		Thread.sleep(3000);
		// Seleccionar el 0 elemento de la lista "almacenes"
		WebElement primerAlmacen = almacenList.get(0);
		primerAlmacen.click();
	}

	public void confirmLocation() {
		wait.until(ExpectedConditions.visibilityOf(confirmarButton)).click();
	}

	public List<WebElement> getVisibleProducts() {
		return listaDeProductos;
	}

	public void addRandomProductsToCart(int numberOfProducts) throws InterruptedException {
		List<WebElement> productosSeleccionados = new ArrayList<>();
		Random rand = new Random();
		while (productosSeleccionados.size() < numberOfProducts) {
			int index = rand.nextInt(listaDeProductos.size());
			WebElement producto = listaDeProductos.get(index);
			if (!productosSeleccionados.contains(producto)) {
				productosSeleccionados.add(producto);
			}
		}

		for (WebElement producto : productosSeleccionados) {
			int cantidad = rand.nextInt(10) + 1;
			System.out.println("Producto seleccionado: " + producto.getText() + " - Cantidad: " + cantidad);

			// Obtener el botón "Agregar" del producto
			WebElement buttonAgregar = producto.findElement(By.cssSelector(
					".vtex-button.bw1.ba.fw5.v-mid.relative.pa0.lh-solid.br2.min-h-regular.t-action.bg-action-primary.b--action-primary.c-on-action-primary.hover-bg-action-primary.hover-b--action-primary.hover-c-on-action-primary.pointer.w-100"));

			// Mover el mouse al botón "Agregar" y hacer click
			Actions actions = new Actions(driver);
			actions.moveToElement(buttonAgregar).perform(); // Solo mover el mouse sin hacer click
			Thread.sleep(1000); // Esperar un segundo para que se cargue el botón y el producto
			actions.click().build().perform(); // Hacer click en el botón
			Thread.sleep(3000);
			// Esperar a que el botón "Agregar más" y el stepper aparezcan y hacer click la
			// cantidad de veces elegida al azar
			WebElement buttonAgregarMas = wait
					.until(ExpectedConditions.elementToBeClickable(producto.findElement(By.cssSelector(
							".exito-vtex-components-4-x-buttonActions.exito-vtex-components-4-x-productSummaryAddToCar.product-summary-add-to-car-plus"))));
			WebElement stepperContainer = producto
					.findElement(By.cssSelector(".exito-vtex-components-4-x-stepperContainerQty"));
			int productosAgregados = 1; // Inicializar en 1, ya que ya se agregó una unidad del producto
			while (productosAgregados < cantidad) {
				wait.until(ExpectedConditions.elementToBeClickable(buttonAgregarMas)).click();
				productosAgregados++;
				wait.until(ExpectedConditions.textToBePresentInElement(stepperContainer,
						Integer.toString(productosAgregados)));
			}

			// Validar que la cantidad seleccionada sea correcta
			String cantidadSeleccionada = stepperContainer.getText();
			if (!cantidadSeleccionada.equals(cantidad + ".00 u")) {
				System.out.println("Error al seleccionar la cantidad para el producto " + producto.getText()
						+ ". Cantidad seleccionada: " + cantidadSeleccionada);
			}

			Thread.sleep(3000);
		}
	}

	public void verifyProductsToCart() throws InterruptedException {
		// Abrir la página
		driver.get(CART_URL);

		// Esperar a que se cargue la página

		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector(".exito-checkout-io-0-x-itemCartContent")));

		// Encuentra todos los elementos que tengan la estructura indicada
		List<WebElement> productos = driver.findElements(By.cssSelector("div[data-molecule-product-detail='true']"));

		// Recorre todos los elementos encontrados e imprime sus datos, incluyendo la
		// cantidad
		for (WebElement producto : productos) {
			String marca = producto
					.findElement(By.cssSelector("div[data-molecule-product-detail-brand-name='true'] span")).getText();
			String nombre = producto.findElement(By.cssSelector("div[data-molecule-product-detail-name='true'] span"))
					.getText();
			String precio = producto.findElement(By.cssSelector(
					"div[data-molecule-product-detail-prices='true'] div[data-molecule-product-detail-price-best-price='true'] span"))
					.getText();
			String cantidad = producto
					.findElement(By.cssSelector("div[data-molecule-product-detail-quantity='true'] span")).getText(); // Nueva
																														// línea

			System.out.println("Marca: " + marca);
			System.out.println("Nombre: " + nombre);
			System.out.println("Cantidad: " + cantidad); // Nueva línea
			System.out.println("Precio: " + precio);
		}

		// Encontrar el elemento que contiene el total y extraer su valor
		WebElement totalElement = driver.findElement(
				By.cssSelector(".exito-checkout-io-0-x-summaryTotal [data-molecule-summary-item-value='true']"));
		String total = totalElement.getText();

		// Mostrar el total
		System.out.println("#########################");
		System.out.println("Total general: " + total);
		System.out.println("#########################");
		Thread.sleep(5000);
	}

}
