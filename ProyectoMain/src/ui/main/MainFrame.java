package ui.main;

import BaseDatos.Conexion;
import DAO.InspeccionFitosanitariaDAO;
import java.awt.*;
import javax.swing.*;
import model.Usuario;
import service.negocio.InspeccionFitosanitariaService;
import ui.components.BaseFrame;
import ui.forms.InspeccionFitosanitariaListPanelSimple;
import ui.utils.MessageUtil;
import ui.utils.UIConstants;

/**
 * @version 1.0
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Ventana principal del sistema.
 * Contiene el menú principal, toolbar y área de trabajo según el rol del usuario.
 */
public class MainFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;

	// Usuario autenticado
	private Usuario currentUser;
	
	// Servicios
	private InspeccionFitosanitariaService inspeccionService;

	// Componentes principales
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JPanel contentPanel;
	private JPanel statusBar;

	// Menús
	private JMenu menuInspecciones;
	private JMenu menuPredios;
	private JMenu menuUsuarios;
	private JMenu menuReportes;
	private JMenu menuSistema;

	// Labels de status bar
	private JLabel statusLabel;
	private JLabel userLabel;

	/**
	 * Constructor de la ventana principal.
	 * 
	 * @param user Usuario autenticado
	 */
	public MainFrame(Usuario user) {
		super(UIConstants.APP_TITLE + " - Principal", UIConstants.SIZE_MAIN);
		this.currentUser = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Inicializar servicios
		try {
			InspeccionFitosanitariaDAO inspeccionDAO = new InspeccionFitosanitariaDAO(Conexion.getInstance());
			this.inspeccionService = new InspeccionFitosanitariaService(inspeccionDAO);
		} catch (Exception e) {
			MessageUtil.showError(this, "Error al inicializar servicios: " + e.getMessage());
		}
	}

	@Override
	protected void initializeComponents() {
		// Menu Bar
		menuBar = new JMenuBar();
		menuBar.setBackground(UIConstants.COLOR_SURFACE);

		// Menús principales
		menuInspecciones = createMenu("Inspecciones", 'I');
		menuPredios = createMenu("Predios y Lotes", 'P');
		menuUsuarios = createMenu("Usuarios", 'U');
		menuReportes = createMenu("Reportes", 'R');
		menuSistema = createMenu("Sistema", 'S');

		// ToolBar
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBackground(UIConstants.COLOR_SURFACE);

		// Content Panel
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBackground(UIConstants.COLOR_BACKGROUND);

		// Status Bar
		statusBar = new JPanel();
		statusBar.setLayout(new BorderLayout());
		statusBar.setBackground(UIConstants.COLOR_SURFACE);
		statusBar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UIConstants.COLOR_BORDER));
		statusBar.setPreferredSize(new Dimension(0, 25));

		statusLabel = new JLabel("  Listo");
		statusLabel.setFont(UIConstants.FONT_CAPTION);

		userLabel = new JLabel("Usuario: " + currentUser.getNombre() + " (" + currentUser.getRol() + ")  ");
		userLabel.setFont(UIConstants.FONT_CAPTION);
		userLabel.setForeground(UIConstants.COLOR_TEXT_SECONDARY);
	}

	@Override
	protected void setupLayout() {
		// Configurar menús
		setupMenuInspecciones();
		setupMenuPredios();
		setupMenuUsuarios();
		setupMenuReportes();
		setupMenuSistema();

		menuBar.add(menuInspecciones);
		menuBar.add(menuPredios);
		menuBar.add(menuUsuarios);
		menuBar.add(menuReportes);
		menuBar.add(menuSistema);

		setJMenuBar(menuBar);

		// Configurar toolbar
		setupToolBar();

		// Status bar
		statusBar.add(statusLabel, BorderLayout.WEST);
		statusBar.add(userLabel, BorderLayout.EAST);

		// Panel de bienvenida inicial
		JPanel welcomePanel = createWelcomePanel();
		contentPanel.add(welcomePanel, BorderLayout.CENTER);

		// Layout principal
		add(toolBar, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
	}

	/**
	 * Crea el panel de bienvenida.
	 */
	private JPanel createWelcomePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(UIConstants.COLOR_BACKGROUND);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel welcomeLabel = new JLabel("Bienvenido al Sistema de Inspección Fitosanitaria");
		welcomeLabel.setFont(UIConstants.FONT_TITLE);
		welcomeLabel.setForeground(UIConstants.COLOR_PRIMARY);
		panel.add(welcomeLabel, gbc);

		gbc.gridy = 1;
		JLabel userNameLabel = new JLabel(currentUser.getNombre());
		userNameLabel.setFont(UIConstants.FONT_SUBTITLE);
		panel.add(userNameLabel, gbc);

		gbc.gridy = 2;
		JLabel roleLabel = new JLabel("Rol: " + currentUser.getRol());
		roleLabel.setFont(UIConstants.FONT_BODY);
		roleLabel.setForeground(UIConstants.COLOR_TEXT_SECONDARY);
		panel.add(roleLabel, gbc);

		gbc.gridy = 3;
		gbc.insets = new Insets(30, 10, 10, 10);
		JLabel instructionLabel = new JLabel("Seleccione una opción del menú para comenzar");
		instructionLabel.setFont(UIConstants.FONT_BODY);
		instructionLabel.setForeground(UIConstants.COLOR_TEXT_SECONDARY);
		panel.add(instructionLabel, gbc);

		return panel;
	}

	/**
	 * Configura el menú de inspecciones.
	 */
	private void setupMenuInspecciones() {
		JMenuItem nuevaInspeccion = createMenuItem("Nueva Inspección", 'N');
		JMenuItem listarInspecciones = createMenuItem("Listar Inspecciones", 'L');
		JMenuItem buscarInspeccion = createMenuItem("Buscar Inspección", 'B');

		menuInspecciones.add(nuevaInspeccion);
		menuInspecciones.add(listarInspecciones);
		menuInspecciones.addSeparator();
		menuInspecciones.add(buscarInspeccion);

		// Listeners
		nuevaInspeccion.addActionListener(e -> showNotImplemented());
		listarInspecciones.addActionListener(e -> mostrarListadoInspecciones());
		buscarInspeccion.addActionListener(e -> showNotImplemented());
	}

	/**
	 * Configura el menú de predios.
	 */
	private void setupMenuPredios() {
		JMenuItem gestionPredios = createMenuItem("Gestión de Predios", 'P');
		JMenuItem gestionLotes = createMenuItem("Gestión de Lotes", 'L');
		JMenuItem gestionCultivos = createMenuItem("Gestión de Cultivos", 'C');

		menuPredios.add(gestionPredios);
		menuPredios.add(gestionLotes);
		menuPredios.add(gestionCultivos);

		// TODO: Agregar listeners
		gestionPredios.addActionListener(e -> showNotImplemented());
		gestionLotes.addActionListener(e -> showNotImplemented());
		gestionCultivos.addActionListener(e -> showNotImplemented());
	}

	/**
	 * Configura el menú de usuarios.
	 */
	private void setupMenuUsuarios() {
		JMenuItem gestionProductores = createMenuItem("Gestión de Productores", 'P');
		JMenuItem gestionAsistentes = createMenuItem("Gestión de Asistentes Técnicos", 'A');
		JMenuItem gestionPropietarios = createMenuItem("Gestión de Propietarios", 'O');

		menuUsuarios.add(gestionProductores);
		menuUsuarios.add(gestionAsistentes);
		menuUsuarios.add(gestionPropietarios);

		// TODO: Agregar listeners
		gestionProductores.addActionListener(e -> showNotImplemented());
		gestionAsistentes.addActionListener(e -> showNotImplemented());
		gestionPropietarios.addActionListener(e -> showNotImplemented());
	}

	/**
	 * Configura el menú de reportes.
	 */
	private void setupMenuReportes() {
		JMenuItem reporteInspecciones = createMenuItem("Reporte de Inspecciones", 'I');
		JMenuItem reportePredios = createMenuItem("Reporte de Predios", 'P');
		JMenuItem reportePlagas = createMenuItem("Reporte de Plagas", 'L');
		JMenuItem exportarCSV = createMenuItem("Exportar a CSV", 'E');

		menuReportes.add(reporteInspecciones);
		menuReportes.add(reportePredios);
		menuReportes.add(reportePlagas);
		menuReportes.addSeparator();
		menuReportes.add(exportarCSV);

		// TODO: Agregar listeners
		reporteInspecciones.addActionListener(e -> showNotImplemented());
		reportePredios.addActionListener(e -> showNotImplemented());
		reportePlagas.addActionListener(e -> showNotImplemented());
		exportarCSV.addActionListener(e -> showNotImplemented());
	}

	/**
	 * Configura el menú del sistema.
	 */
	private void setupMenuSistema() {
		JMenuItem perfil = createMenuItem("Mi Perfil", 'P');
		JMenuItem configuracion = createMenuItem("Configuración", 'C');
		JMenuItem ayuda = createMenuItem("Ayuda", 'A');
		JMenuItem acercaDe = createMenuItem("Acerca de", 'D');
		JMenuItem cerrarSesion = createMenuItem("Cerrar Sesión", 'S');
		JMenuItem salir = createMenuItem("Salir", 'X');

		menuSistema.add(perfil);
		menuSistema.add(configuracion);
		menuSistema.addSeparator();
		menuSistema.add(ayuda);
		menuSistema.add(acercaDe);
		menuSistema.addSeparator();
		menuSistema.add(cerrarSesion);
		menuSistema.add(salir);

		// Listeners
		perfil.addActionListener(e -> showNotImplemented());
		configuracion.addActionListener(e -> showNotImplemented());
		ayuda.addActionListener(e -> showNotImplemented());
		acercaDe.addActionListener(e -> showAboutDialog());
		cerrarSesion.addActionListener(e -> logout());
		salir.addActionListener(e -> exitApplication());
	}

	/**
	 * Configura la barra de herramientas.
	 */
	private void setupToolBar() {
		JButton btnNewInspection = createToolBarButton("Nueva Inspección");
		JButton btnListInspections = createToolBarButton("Listar Inspecciones");
		JButton btnReports = createToolBarButton("Reportes");

		toolBar.add(btnNewInspection);
		toolBar.add(btnListInspections);
		toolBar.addSeparator();
		toolBar.add(btnReports);

		// Listeners
		btnNewInspection.addActionListener(e -> showNotImplemented());
		btnListInspections.addActionListener(e -> mostrarListadoInspecciones());
		btnReports.addActionListener(e -> showNotImplemented());
	}

	/**
	 * Crea un menú con mnemónico.
	 */
	private JMenu createMenu(String text, char mnemonic) {
		JMenu menu = new JMenu(text);
		menu.setMnemonic(mnemonic);
		menu.setFont(UIConstants.FONT_BODY);
		return menu;
	}

	/**
	 * Crea un item de menú con mnemónico.
	 */
	private JMenuItem createMenuItem(String text, char mnemonic) {
		JMenuItem item = new JMenuItem(text);
		item.setMnemonic(mnemonic);
		item.setFont(UIConstants.FONT_BODY);
		return item;
	}

	/**
	 * Crea un botón para la toolbar.
	 */
	private JButton createToolBarButton(String text) {
		JButton button = new JButton(text);
		button.setFont(UIConstants.FONT_BODY);
		button.setFocusPainted(false);
		return button;
	}

	/**
	 * Muestra el listado de inspecciones fitosanitarias.
	 */
	private void mostrarListadoInspecciones() {
		if (inspeccionService == null) {
			MessageUtil.showError(this, "El servicio de inspecciones no está disponible");
			return;
		}
		
		InspeccionFitosanitariaListPanelSimple panel = new InspeccionFitosanitariaListPanelSimple(this, inspeccionService);
		setContent(panel);
		updateStatus("Listado de Inspecciones Fitosanitarias");
	}

	/**
	 * Muestra mensaje de funcionalidad no implementada.
	 */
	private void showNotImplemented() {
		MessageUtil.showInfo(this, "Esta funcionalidad estará disponible próximamente", "En Desarrollo");
	}

	/**
	 * Muestra el diálogo "Acerca de".
	 */
	private void showAboutDialog() {
		String message = UIConstants.APP_NAME + "\n" +
						 UIConstants.APP_VERSION + "\n\n" +
						 "Sistema de gestión de inspecciones fitosanitarias\n" +
						 "para control y monitoreo de cultivos agrícolas.\n\n" +
						 "Desarrollado por:\n" +
						 "Isabella Vargas\n" +
						 "Ricardo Viancha\n" +
						 "Iswar Corrales\n" +
						 "Andres Rivero";
		
		MessageUtil.showInfo(this, message, "Acerca de");
	}

	/**
	 * Cierra la sesión del usuario.
	 */
	private void logout() {
		boolean confirm = MessageUtil.showConfirm(this, "¿Está seguro que desea cerrar sesión?", "Cerrar Sesión");
		if (confirm) {
			// TODO: Implementar cierre de sesión y retorno a LoginFrame
			MessageUtil.showInfo(this, "Sesión cerrada exitosamente");
			System.exit(0);
		}
	}

	/**
	 * Sale de la aplicación.
	 */
	private void exitApplication() {
		boolean confirm = MessageUtil.showConfirm(this, "¿Está seguro que desea salir de la aplicación?", "Salir");
		if (confirm) {
			System.exit(0);
		}
	}

	/**
	 * Actualiza el mensaje de la barra de estado.
	 */
	public void updateStatus(String message) {
		statusLabel.setText("  " + message);
	}

	/**
	 * Cambia el contenido del panel principal.
	 */
	public void setContent(JPanel panel) {
		contentPanel.removeAll();
		contentPanel.add(panel, BorderLayout.CENTER);
		contentPanel.revalidate();
		contentPanel.repaint();
	}
}
