package ui.forms;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import model.InspeccionFitosanitaria;
import service.exceptions.ServiceException;
import service.negocio.InspeccionFitosanitariaService;
import ui.components.BasePanel;
import ui.utils.MessageUtil;
import ui.utils.UIConstants;

/**
 * @version 1.0
 * Panel simplificado para listar inspecciones fitosanitarias.
 */
public class InspeccionFitosanitariaListPanelSimple extends BasePanel {

	private static final long serialVersionUID = 1L;

	private final InspeccionFitosanitariaService inspeccionService;
	private final JFrame parentFrame;

	private JPanel toolbarPanel;
	private JButton nuevaButton;
	private JButton editarButton;
	private JButton eliminarButton;
	private JButton actualizarButton;

	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;

	private List<InspeccionFitosanitaria> inspecciones;
	private InspeccionFitosanitaria selectedInspeccion;

	public InspeccionFitosanitariaListPanelSimple(JFrame parent, InspeccionFitosanitariaService service) {
		super(new BorderLayout(10, 10));
		this.parentFrame = parent;
		this.inspeccionService = service;
		setBorder(new EmptyBorder(15, 15, 15, 15));
	}

	@Override
	protected void initializeComponents() {
		toolbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		toolbarPanel.setBackground(UIConstants.COLOR_SURFACE);

		nuevaButton = createToolbarButton("Nueva", UIConstants.COLOR_PRIMARY);
		editarButton = createToolbarButton("Editar", UIConstants.COLOR_ACCENT);
		eliminarButton = createToolbarButton("Eliminar", UIConstants.COLOR_ERROR);
		actualizarButton = createToolbarButton("Actualizar", UIConstants.COLOR_SECONDARY);

		editarButton.setEnabled(false);
		eliminarButton.setEnabled(false);

		String[] columnNames = { "ID", "Fecha Inspección" };
		tableModel = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.setFont(UIConstants.FONT_BODY);
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setFont(UIConstants.FONT_SUBHEADING);
		table.getTableHeader().setBackground(UIConstants.COLOR_PRIMARY);
		table.getTableHeader().setForeground(UIConstants.COLOR_TEXT_ON_PRIMARY);

		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);

		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createLineBorder(UIConstants.COLOR_BORDER));

		loadInspecciones();
	}

	@Override
	protected void setupLayout() {
		toolbarPanel.add(nuevaButton);
		toolbarPanel.add(editarButton);
		toolbarPanel.add(eliminarButton);
		toolbarPanel.add(new JSeparator(SwingConstants.VERTICAL));
		toolbarPanel.add(actualizarButton);

		JLabel titleLabel = new JLabel("Inspecciones Fitosanitarias");
		titleLabel.setFont(UIConstants.FONT_HEADING);
		titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(UIConstants.COLOR_BACKGROUND);
		headerPanel.add(titleLabel, BorderLayout.WEST);

		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.setBackground(UIConstants.COLOR_BACKGROUND);
		tablePanel.add(headerPanel, BorderLayout.NORTH);
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		add(toolbarPanel, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
	}

	@Override
	protected void setupListeners() {
		nuevaButton.addActionListener(e -> nuevaInspeccion());
		editarButton.addActionListener(e -> editarInspeccion());
		eliminarButton.addActionListener(e -> eliminarInspeccion());
		actualizarButton.addActionListener(e -> actualizarLista());

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

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && table.getSelectedRow() >= 0) {
					editarInspeccion();
				}
			}
		});
	}

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

	private void loadInspecciones() {
		try {
			inspecciones = inspeccionService.obtenerTodos();
			updateTable();
		} catch (ServiceException ex) {
			MessageUtil.showError(this, "Error al cargar inspecciones: " + ex.getMessage());
			inspecciones = new java.util.ArrayList<>();
		}
	}

	private void updateTable() {
		tableModel.setRowCount(0);

		if (inspecciones == null || inspecciones.isEmpty()) {
			return;
		}

		for (InspeccionFitosanitaria insp : inspecciones) {
			Object[] row = new Object[2];
			row[0] = insp.getId();
			row[1] = insp.getFechaInspeccion();
			tableModel.addRow(row);
		}
	}

	private void nuevaInspeccion() {
		InspeccionFitosanitariaFormSimple form = new InspeccionFitosanitariaFormSimple(parentFrame, inspeccionService);
		form.display();

		if (form.isAccepted()) {
			actualizarLista();
		}
	}

	private void editarInspeccion() {
		if (selectedInspeccion == null) {
			MessageUtil.showWarning(this, "Seleccione una inspección para editar");
			return;
		}

		InspeccionFitosanitariaFormSimple form = new InspeccionFitosanitariaFormSimple(parentFrame, inspeccionService,
				selectedInspeccion);
		form.display();

		if (form.isAccepted()) {
			actualizarLista();
		}
	}

	private void eliminarInspeccion() {
		if (selectedInspeccion == null) {
			MessageUtil.showWarning(this, "Seleccione una inspección para eliminar");
			return;
		}

		boolean confirm = MessageUtil.showDeleteConfirm(this);
		if (!confirm) {
			return;
		}

		try {
			MessageUtil.showInfo(this, "Eliminación pendiente de implementar en servicio");
			actualizarLista();
		} catch (Exception ex) {
			MessageUtil.showError(this, "Error al eliminar: " + ex.getMessage());
		}
	}

	private void actualizarLista() {
		loadInspecciones();
		table.clearSelection();
		selectedInspeccion = null;
		editarButton.setEnabled(false);
		eliminarButton.setEnabled(false);
	}
}
