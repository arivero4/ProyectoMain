package ui.components;

import java.awt.LayoutManager;
import javax.swing.JPanel;
import ui.utils.UIConstants;

/**
 * @version 1.0
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Clase base para todos los paneles de la aplicación.
 * Proporciona funcionalidad común y estandariza la creación de paneles.
 */
public abstract class BasePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que inicializa el panel con un layout específico.
	 * 
	 * @param layout Layout manager a utilizar
	 */
	public BasePanel(LayoutManager layout) {
		super(layout);
		setBackground(UIConstants.COLOR_BACKGROUND);
		initializeComponents();
		setupLayout();
		setupListeners();
	}

	/**
	 * Constructor por defecto sin layout específico.
	 */
	public BasePanel() {
		this(null);
	}

	/**
	 * Inicializa los componentes del panel.
	 * Este método debe ser implementado por las subclases.
	 */
	protected abstract void initializeComponents();

	/**
	 * Configura el layout del panel.
	 * Este método debe ser implementado por las subclases.
	 */
	protected abstract void setupLayout();

	/**
	 * Configura los listeners del panel.
	 * Este método puede ser sobrescrito por las subclases si es necesario.
	 */
	protected void setupListeners() {
		// Implementación por defecto vacía
		// Las subclases pueden sobrescribir si necesitan listeners
	}

	/**
	 * Limpia todos los campos del panel.
	 * Este método puede ser sobrescrito por las subclases.
	 */
	public void clearFields() {
		// Implementación por defecto vacía
		// Las subclases pueden sobrescribir para limpiar sus campos específicos
	}

	/**
	 * Valida los datos del panel.
	 * Este método puede ser sobrescrito por las subclases.
	 * 
	 * @return true si los datos son válidos, false en caso contrario
	 */
	public boolean validateData() {
		// Implementación por defecto
		return true;
	}
}
