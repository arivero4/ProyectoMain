package ui.components;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;
import ui.utils.UIConstants;

/**
 * @version 1.0
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Clase base para todos los diálogos de la aplicación.
 * Proporciona funcionalidad común para diálogos modales y no modales.
 */
public abstract class BaseDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	protected boolean accepted = false;

	/**
	 * Constructor que inicializa el diálogo.
	 * 
	 * @param parent Ventana padre
	 * @param title Título del diálogo
	 * @param modal Si el diálogo es modal o no
	 * @param size Dimensión del diálogo
	 */
	public BaseDialog(JFrame parent, String title, boolean modal, Dimension size) {
		super(parent, title, modal);
		this.setSize(size);
		initializeDialog();
		initializeComponents();
		setupLayout();
		setupListeners();
	}

	/**
	 * Constructor para diálogos modales con tamaño predeterminado.
	 * 
	 * @param parent Ventana padre
	 * @param title Título del diálogo
	 */
	public BaseDialog(JFrame parent, String title) {
		this(parent, title, true, UIConstants.SIZE_DIALOG_MEDIUM);
	}

	/**
	 * Inicializa configuraciones básicas del diálogo.
	 */
	private void initializeDialog() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(getParent());
		setResizable(false);
	}

	/**
	 * Centra el diálogo respecto a su ventana padre o en la pantalla.
	 */
	protected void centerDialog() {
		if (getParent() != null) {
			setLocationRelativeTo(getParent());
		} else {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (screenSize.width - getWidth()) / 2;
			int y = (screenSize.height - getHeight()) / 2;
			setLocation(x, y);
		}
	}

	/**
	 * Inicializa los componentes del diálogo.
	 * Este método debe ser implementado por las subclases.
	 */
	protected abstract void initializeComponents();

	/**
	 * Configura el layout del diálogo.
	 * Este método debe ser implementado por las subclases.
	 */
	protected abstract void setupLayout();

	/**
	 * Configura los listeners del diálogo.
	 * Este método puede ser sobrescrito por las subclases si es necesario.
	 */
	protected void setupListeners() {
		// Implementación por defecto vacía
	}

	/**
	 * Muestra el diálogo.
	 */
	public void display() {
		centerDialog();
		setVisible(true);
	}

	/**
	 * Acepta el diálogo y lo cierra.
	 */
	protected void acceptDialog() {
		accepted = true;
		dispose();
	}

	/**
	 * Cancela el diálogo y lo cierra.
	 */
	protected void cancelDialog() {
		accepted = false;
		dispose();
	}

	/**
	 * Verifica si el diálogo fue aceptado.
	 * 
	 * @return true si fue aceptado, false en caso contrario
	 */
	public boolean isAccepted() {
		return accepted;
	}
}
