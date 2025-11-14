package ui.components;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @version 1.0
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Clase base para todas las ventanas principales de la aplicación.
 * Proporciona funcionalidad común como centrado de ventana, configuración estándar, etc.
 */
public abstract class BaseFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que inicializa la ventana con título y dimensión.
	 * 
	 * @param title Título de la ventana
	 * @param size Dimensión de la ventana
	 */
	public BaseFrame(String title, Dimension size) {
		super(title);
		this.setSize(size);
		initializeFrame();
		initializeComponents();
		setupLayout();
		setupListeners();
	}

	/**
	 * Inicializa configuraciones básicas de la ventana.
	 */
	private void initializeFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar en pantalla
		setResizable(true);
	}

	/**
	 * Centra la ventana en la pantalla.
	 */
	protected void centerWindow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
		int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
	}

	/**
	 * Inicializa los componentes de la ventana.
	 * Este método debe ser implementado por las subclases.
	 */
	protected abstract void initializeComponents();

	/**
	 * Configura el layout de la ventana.
	 * Este método debe ser implementado por las subclases.
	 */
	protected abstract void setupLayout();

	/**
	 * Configura los listeners de la ventana.
	 * Este método puede ser sobrescrito por las subclases si es necesario.
	 */
	protected void setupListeners() {
		// Implementación por defecto vacía
		// Las subclases pueden sobrescribir si necesitan listeners
	}

	/**
	 * Muestra la ventana.
	 */
	public void display() {
		centerWindow();
		setVisible(true);
	}

	/**
	 * Cierra la ventana.
	 */
	public void close() {
		setVisible(false);
		dispose();
	}
}
