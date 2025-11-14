package ui.forms;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import model.InspeccionFitosanitaria;
import service.negocio.InspeccionFitosanitariaService;
import ui.components.BasePanel;
import ui.utils.MessageUtil;
import ui.utils.UIConstants;

/**
 * @version 1.0
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Panel para listar y gestionar inspecciones fitosanitarias.
 */
public class InspeccionFitosanitariaListPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	// Servicios
	private InspeccionFitosanitariaService inspeccionService;
	private JFrame parentFrame;

	// Componentes
	private JPanel toolbarPanel;
	private JPanel tablePanel;

	private JButton nuevaButton;
	private JButton editarButton;
	private JButton eliminarButton;
	private JButton buscarButton;
	private JButton actualizarButton;
	private JButton exportarButton;

	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;

	// Datos
	private List<InspeccionFitosanitaria> inspecciones;
	private InspeccionFitosanitaria selectedInspeccion;

	/**
	 * Constructor del panel.
	 */
	public InspeccionFitosanitariaListPanel(JFrame parent, InspeccionFitosanitariaService service) {
		super(new BorderLayout(10, 10));
		this.parentFrame = parent;
		this.inspeccionService = service;
		setBorder(new EmptyBorder(15, 15, 15, 15));
	}

	@Override
	protected void initializeComponents() {
		// Toolbar
		toolbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		toolbarPanel.setBackground(UIConstants.COLOR_SURFACE);

		nuevaButton = createToolbarButton("Nueva", UIConstants.COLOR_PRIMARY);
		editarButton = createToolbarButton("Editar", UIConstants.COLOR_ACCENT);
		eliminarButton = createToolbarButton("Eliminar", UIConstants.COLOR_ERROR);
		buscarButton = createToolbarButton("Buscar", UIConstants.COLOR_INFO);
		actualizarButton = createToolbarButton("Actualizar", UIConstants.COLOR_SECONDARY);
		exportarButton = createToolbarButton("Exportar", UIConstants.COLOR_WARNING);

		// Deshabilitar botones que requieren selección
		editarButton.setEnabled(false);
		eliminarButton.setEnabled(false);

		// Tabla
		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setBackground(UIConstants.COLOR_BACKGROUND);

		String[] columnNames = { "ID", "Fecha", "Estado", "Tipo", "Lugar Producción", "Observaciones" };
		tableModel = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Tabla no editable
			}
		};

		table = new JTable(tableModel);
		table.setFont(UIConstants.FONT_BODY);
		table.setRowHeight(25);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setFont(UIConstants.FONT_SUBHEADING);
		table.getTableHeader().setBackground(UIConstants.COLOR_PRIMARY);
		table.getTableHeader().setForeground(UIConstants.COLOR_TEXT_ON_PRIMARY);

		// Anchos de columna
		table.getColumnModel().getColumn(0).setPreferredWidth(80);  // ID
		table.getColumnModel().getColumn(1).setPreferredWidth(100); // Fecha
		table.getColumnModel().getColumn(2).setPreferredWidth(120); // Estado
		table.getColumnModel().getColumn(3).setPreferredWidth(120); // Tipo
		table.getColumnModel().getColumn(4).setPreferredWidth(180); // Lugar Producción
		table.getColumnModel().getColumn(5).setPreferredWidth(300); // Observaciones

		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createLineBorder(UIConstants.COLOR_BORDER));

		// Cargar datos iniciales
		loadInspecciones();
	}

	@Override
	protected void setupLayout() {
		// Toolbar
		toolbarPanel.add(nuevaButton);
		toolbarPanel.add(editarButton);
		toolbarPanel.add(eliminarButton);
		toolbarPanel.add(new JSeparator(SwingConstants.VERTICAL));
		toolbarPanel.add(buscarButton);
		toolbarPanel.add(actualizarButton);
		toolbarPanel.add(new JSeparator(SwingConstants.VERTICAL));
		toolbarPanel.add(exportarButton);

		// Panel de tabla
		JLabel titleLabel = new JLabel("Inspecciones Fitosanitarias");
		titleLabel.setFont(UIConstants.FONT_HEADING);
		titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(UIConstants.COLOR_BACKGROUND);
		headerPanel.add(titleLabel, BorderLayout.WEST);

		tablePanel.add(headerPanel, BorderLayout.NORTH);
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		// Layout principal
		add(toolbarPanel, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
	}

	@Override
	protected void setupListeners() {
		// Botones
		nuevaButton.addActionListener(e -> nuevaInspeccion());
		editarButton.addActionListener(e -> editarInspeccion());
		eliminarButton.addActionListener(e -> eliminarInspeccion());
		buscarButton.addActionListener(e -> buscarInspeccion());
		actualizarButton.addActionListener(e -> actualizarLista());
		exportarButton.addActionListener(e -> exportarDatos());

		// Selección en tabla
		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					selectedInspeccion = inspecciones.get(selectedRow);
					editarButton.setEnabled(true);
					eliminarButton.setEnabled(true);
				} else {
					selectedInspeccion = null;
					editarButton.setEnabled(false);
					eliminarButton.setEnabled(false);
				}
			}
		});

		// Doble click para editar
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = table.getSelectedRow();
					if (row >= 0) {
						editarInspeccion();
					}
				}
			}
		});
	}

	/**
	 * Crea un botón de toolbar estilizado.
	 */
	private JButton createToolbarButton(String text, Color background) {
		JButton button = new JButton(text);
		button.setFont(UIConstants.FONT_BUTTON);
		button.setPreferredSize(UIConstants.SIZE_BUTTON_STANDARD);
		button.setBackground(background);
		button.setForeground(UIConstants.COLOR_TEXT_ON_PRIMARY);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return button;
	}

	/**
	 * Carga la lista de inspecciones desde el servicio.
	 */
	private void loadInspecciones() {
		try {
			inspecciones = inspeccionService.obtenerTodos();
			updateTable();
		} catch (Exception ex) {
			MessageUtil.showError(this, "Error al cargar inspecciones: " + ex.getMessage());
			inspecciones = new java.util.ArrayList<>();
		}
	}

	/**
	 * Actualiza la tabla con los datos actuales.
	 */
	private void updateTable() {
		tableModel.setRowCount(0); // Limpiar tabla

		if (inspecciones == null || inspecciones.isEmpty()) {
			return;
		}

		for (InspeccionFitosanitaria insp : inspecciones) {
			Object[] row = new Object[6];
			row[0] = insp.getId();
			row[1] = insp.getFechaInspeccion() != null ? insp.getFechaInspeccion() : "";
			row[2] = insp.getEstado() != null ? insp.getEstado() : "N/A";
			row[3] = insp.getTipoInspeccion() != null ? insp.getTipoInspeccion() : "N/A";
			
			// Lugar de producción
			if (insp.getLugaresProduccion() != null) {
				row[4] = insp.getLugaresProduccion().getNombre();
			} else {
				row[4] = "N/A";
			}
			
			// Observaciones (truncadas si son muy largas)
			row[5] = insp.getObservaciones() != null && insp.getObservaciones().length() > 50
					? insp.getObservaciones().substring(0, 47) + "..."
					: insp.getObservaciones();
			tableModel.addRow(row);
		}
	}

	/**
	 * Abre el formulario para crear nueva inspección.
	 */
	private void nuevaInspeccion() {
		InspeccionFitosanitariaForm form = new InspeccionFitosanitariaForm(parentFrame, inspeccionService);
		form.display();

		if (form.isAccepted()) {
			actualizarLista();
		}
	}

	/**
	 * Abre el formulario para editar inspección seleccionada.
	 */
	private void editarInspeccion() {
		if (selectedInspeccion == null) {
			MessageUtil.showWarning(this, "Por favor seleccione una inspección para editar");
			return;
		}

		InspeccionFitosanitariaForm form = new InspeccionFitosanitariaForm(parentFrame, inspeccionService,
				selectedInspeccion);
		form.display();

		if (form.isAccepted()) {
			actualizarLista();
		}
	}

	/**
	 * Elimina la inspección seleccionada.
	 */
	private void eliminarInspeccion() {
		if (selectedInspeccion == null) {
			MessageUtil.showWarning(this, "Por favor seleccione una inspección para eliminar");
			return;
		}

		boolean confirm = MessageUtil.showDeleteConfirm(this);
		if (!confirm) {
			return;
		}

		try {
			// TODO: Implementar método eliminar en servicio
			// long id = Long.parseLong(selectedInspeccion.getId());
			// inspeccionService.eliminar(id);
			MessageUtil.showInfo(this, "Eliminación pendiente de implementar en servicio");
			MessageUtil.showDeleteSuccess(this);
			actualizarLista();
		} catch (Exception ex) {
			MessageUtil.showError(this, "Error al eliminar inspección: " + ex.getMessage());
		}
	}

	/**
	 * Abre el diálogo de búsqueda.
	 */
	private void buscarInspeccion() {
		// TODO: Implementar diálogo de búsqueda
		MessageUtil.showInfo(this, "Funcionalidad en desarrollo", "Buscar Inspección");
	}

	/**
	 * Actualiza la lista de inspecciones.
	 */
	private void actualizarLista() {
		loadInspecciones();
		table.clearSelection();
		selectedInspeccion = null;
		editarButton.setEnabled(false);
		eliminarButton.setEnabled(false);
	}

	/**
	 * Exporta los datos a CSV.
	 */
	private void exportarDatos() {
		// TODO: Implementar exportación a CSV usando ReportService
		MessageUtil.showInfo(this, "Funcionalidad en desarrollo", "Exportar Datos");
	}

	/**
	 * Obtiene la inspección seleccionada.
	 */
	public InspeccionFitosanitaria getSelectedInspeccion() {
		return selectedInspeccion;
	}
}
