package ui.forms;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.InspeccionFitosanitaria;
import model.LugarProduccion;
import service.negocio.InspeccionFitosanitariaService;
import ui.components.BaseDialog;
import ui.utils.MessageUtil;
import ui.utils.UIConstants;

/**
 * @version 1.0
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Formulario para crear y editar inspecciones fitosanitarias.
 */
public class InspeccionFitosanitariaForm extends BaseDialog {

	private static final long serialVersionUID = 1L;

	// Modo del formulario
	private boolean isEditMode = false;
	private InspeccionFitosanitaria inspeccion;

	// Servicios
	@SuppressWarnings("unused")
	private InspeccionFitosanitariaService inspeccionService;

	// Componentes del formulario
	private JPanel mainPanel;
	private JPanel formPanel;
	private JPanel buttonPanel;

	// Campos de datos básicos
	private JTextField idField;
	private JTextField fechaField;
	private JComboBox<String> estadoCombo;
	private JComboBox<String> tipoInspeccionCombo;

	// Campos de observaciones y recomendaciones
	private JTextArea observacionesArea;
	private JTextArea recomendacionesArea;
	private JScrollPane observacionesScroll;
	private JScrollPane recomendacionesScroll;

	// Campos de lugar de producción
	private JTextField lugarIdField;
	private JButton seleccionarLugarButton;
	private JLabel lugarInfoLabel;
	private LugarProduccion lugarSeleccionado;

	// Botones
	private JButton guardarButton;
	private JButton cancelarButton;

	/**
	 * Constructor para crear nueva inspección.
	 */
	public InspeccionFitosanitariaForm(JFrame parent, InspeccionFitosanitariaService service) {
		super(parent, "Nueva Inspección Fitosanitaria", true, UIConstants.SIZE_FORM_LARGE);
		this.inspeccionService = service;
		this.isEditMode = false;
		this.inspeccion = new InspeccionFitosanitaria();
	}

	/**
	 * Constructor para editar inspección existente.
	 */
	public InspeccionFitosanitariaForm(JFrame parent, InspeccionFitosanitariaService service,
			InspeccionFitosanitaria inspeccion) {
		super(parent, "Editar Inspección Fitosanitaria", true, UIConstants.SIZE_FORM_LARGE);
		this.inspeccionService = service;
		this.isEditMode = true;
		this.inspeccion = inspeccion;
	}

	@Override
	protected void initializeComponents() {
		// Paneles principales
		mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		mainPanel.setBackground(UIConstants.COLOR_BACKGROUND);

		formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(UIConstants.COLOR_BACKGROUND);

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		buttonPanel.setBackground(UIConstants.COLOR_BACKGROUND);

		// Campos básicos
		idField = new JTextField(20);
		idField.setEditable(false);
		idField.setBackground(UIConstants.COLOR_BORDER);

		fechaField = new JTextField(20);
		fechaField.setFont(UIConstants.FONT_BODY);
		fechaField.setPreferredSize(new Dimension(250, 30));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		fechaField.setText(sdf.format(new Date()));

		// ComboBox Estado
		String[] estados = { "Pendiente", "En Proceso", "Completada", "Cancelada" };
		estadoCombo = new JComboBox<>(estados);
		estadoCombo.setFont(UIConstants.FONT_BODY);
		estadoCombo.setPreferredSize(new Dimension(250, 30));

		// ComboBox Tipo de Inspección
		String[] tipos = { "Rutinaria", "Emergencia", "Seguimiento" };
		tipoInspeccionCombo = new JComboBox<>(tipos);
		tipoInspeccionCombo.setFont(UIConstants.FONT_BODY);
		tipoInspeccionCombo.setPreferredSize(new Dimension(250, 30));

		// TextAreas
		observacionesArea = new JTextArea(5, 30);
		observacionesArea.setFont(UIConstants.FONT_BODY);
		observacionesArea.setLineWrap(true);
		observacionesArea.setWrapStyleWord(true);
		observacionesScroll = new JScrollPane(observacionesArea);
		observacionesScroll.setBorder(BorderFactory.createLineBorder(UIConstants.COLOR_BORDER));

		recomendacionesArea = new JTextArea(5, 30);
		recomendacionesArea.setFont(UIConstants.FONT_BODY);
		recomendacionesArea.setLineWrap(true);
		recomendacionesArea.setWrapStyleWord(true);
		recomendacionesScroll = new JScrollPane(recomendacionesArea);
		recomendacionesScroll.setBorder(BorderFactory.createLineBorder(UIConstants.COLOR_BORDER));

		// Campos de lugar de producción
		lugarIdField = new JTextField(10);
		lugarIdField.setEditable(false);
		lugarIdField.setBackground(UIConstants.COLOR_BORDER);

		seleccionarLugarButton = new JButton("Seleccionar Lugar");
		seleccionarLugarButton.setFont(UIConstants.FONT_BODY);

		lugarInfoLabel = new JLabel("No se ha seleccionado ningún lugar de producción");
		lugarInfoLabel.setFont(UIConstants.FONT_CAPTION);
		lugarInfoLabel.setForeground(UIConstants.COLOR_TEXT_SECONDARY);

		// Botones
		guardarButton = new JButton(UIConstants.BTN_SAVE);
		guardarButton.setFont(UIConstants.FONT_BUTTON);
		guardarButton.setPreferredSize(UIConstants.SIZE_BUTTON_STANDARD);
		guardarButton.setBackground(UIConstants.COLOR_PRIMARY);
		guardarButton.setForeground(UIConstants.COLOR_TEXT_ON_PRIMARY);

		cancelarButton = new JButton(UIConstants.BTN_CANCEL);
		cancelarButton.setFont(UIConstants.FONT_BUTTON);
		cancelarButton.setPreferredSize(UIConstants.SIZE_BUTTON_STANDARD);

		// Cargar datos si es modo edición
		if (isEditMode && inspeccion != null) {
			loadInspeccionData();
		}
	}

	@Override
	protected void setupLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);

		int row = 0;

		// ID (solo en modo edición)
		if (isEditMode) {
			gbc.gridx = 0;
			gbc.gridy = row;
			gbc.anchor = GridBagConstraints.WEST;
			formPanel.add(new JLabel("ID:"), gbc);

			gbc.gridx = 1;
			formPanel.add(idField, gbc);
			row++;
		}

		// Fecha
		gbc.gridx = 0;
		gbc.gridy = row;
		formPanel.add(new JLabel("Fecha:" + UIConstants.LBL_REQUIRED), gbc);

		gbc.gridx = 1;
		formPanel.add(fechaField, gbc);
		row++;

		// Estado
		gbc.gridx = 0;
		gbc.gridy = row;
		formPanel.add(new JLabel("Estado:" + UIConstants.LBL_REQUIRED), gbc);

		gbc.gridx = 1;
		formPanel.add(estadoCombo, gbc);
		row++;

		// Tipo de inspección
		gbc.gridx = 0;
		gbc.gridy = row;
		formPanel.add(new JLabel("Tipo de Inspección:" + UIConstants.LBL_REQUIRED), gbc);

		gbc.gridx = 1;
		formPanel.add(tipoInspeccionCombo, gbc);
		row++;

		// Separador
		gbc.gridx = 0;
		gbc.gridy = row;
		gbc.gridwidth = 2;
		JSeparator separator1 = new JSeparator();
		separator1.setPreferredSize(new Dimension(0, 10));
		formPanel.add(separator1, gbc);
		row++;

		// Panel de lugar de producción
		JPanel lugarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		lugarPanel.setBackground(UIConstants.COLOR_BACKGROUND);
		lugarPanel.add(new JLabel("Lugar Producción ID:"));
		lugarPanel.add(lugarIdField);
		lugarPanel.add(seleccionarLugarButton);

		gbc.gridx = 0;
		gbc.gridy = row;
		gbc.gridwidth = 2;
		formPanel.add(lugarPanel, gbc);
		row++;

		gbc.gridx = 0;
		gbc.gridy = row;
		formPanel.add(lugarInfoLabel, gbc);
		row++;

		// Separador
		gbc.gridx = 0;
		gbc.gridy = row;
		JSeparator separator2 = new JSeparator();
		separator2.setPreferredSize(new Dimension(0, 10));
		formPanel.add(separator2, gbc);
		row++;

		// Observaciones
		gbc.gridx = 0;
		gbc.gridy = row;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		formPanel.add(new JLabel("Observaciones:"), gbc);

		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.5;
		formPanel.add(observacionesScroll, gbc);
		row++;

		// Recomendaciones
		gbc.gridx = 0;
		gbc.gridy = row;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;
		gbc.weighty = 0;
		formPanel.add(new JLabel("Recomendaciones:"), gbc);

		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.5;
		formPanel.add(recomendacionesScroll, gbc);

		// Botones
		buttonPanel.add(guardarButton);
		buttonPanel.add(cancelarButton);

		// Layout principal
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(mainPanel);
	}

	@Override
	protected void setupListeners() {
		// Botón guardar
		guardarButton.addActionListener(e -> guardarInspeccion());

		// Botón cancelar
		cancelarButton.addActionListener(e -> cancelDialog());

		// Botón seleccionar lugar
		seleccionarLugarButton.addActionListener(e -> seleccionarLugar());
	}

	/**
	 * Carga los datos de la inspección en modo edición.
	 */
	private void loadInspeccionData() {
		if (inspeccion == null)
			return;

		idField.setText(inspeccion.getId());

		if (inspeccion.getFechaInspeccion() != null) {
			fechaField.setText(inspeccion.getFechaInspeccion());
		}

		if (inspeccion.getEstado() != null) {
			estadoCombo.setSelectedItem(inspeccion.getEstado());
		}

		if (inspeccion.getTipoInspeccion() != null) {
			tipoInspeccionCombo.setSelectedItem(inspeccion.getTipoInspeccion());
		}

		if (inspeccion.getLugaresProduccion() != null) {
			lugarSeleccionado = inspeccion.getLugaresProduccion();
			lugarIdField.setText(lugarSeleccionado.getId());
			lugarInfoLabel.setText("Lugar: " + lugarSeleccionado.getNombre());
			lugarInfoLabel.setForeground(UIConstants.COLOR_SUCCESS);
		}

		if (inspeccion.getObservaciones() != null) {
			observacionesArea.setText(inspeccion.getObservaciones());
		}

		if (inspeccion.getRecomendaciones() != null) {
			recomendacionesArea.setText(inspeccion.getRecomendaciones());
		}
	}

	/**
	 * Valida los datos del formulario.
	 */
	private boolean validarDatos() {
		// Validar estado
		if (estadoCombo.getSelectedItem() == null) {
			MessageUtil.showWarning(this, "Por favor seleccione un estado");
			estadoCombo.requestFocus();
			return false;
		}

		// Validar tipo de inspección
		if (tipoInspeccionCombo.getSelectedItem() == null) {
			MessageUtil.showWarning(this, "Por favor seleccione un tipo de inspección");
			tipoInspeccionCombo.requestFocus();
			return false;
		}

		// Validar lugar seleccionado
		if (lugarSeleccionado == null && lugarIdField.getText().trim().isEmpty()) {
			MessageUtil.showWarning(this, "Por favor seleccione un lugar de producción");
			seleccionarLugarButton.requestFocus();
			return false;
		}

		return true;
	}

	/**
	 * Guarda la inspección.
	 */
	private void guardarInspeccion() {
		if (!validarDatos()) {
			return;
		}

		try {
			// Preparar datos
			inspeccion.setFechaInspeccion(fechaField.getText().trim());
			inspeccion.setEstado((String) estadoCombo.getSelectedItem());
			inspeccion.setTipoInspeccion((String) tipoInspeccionCombo.getSelectedItem());
			inspeccion.setObservaciones(observacionesArea.getText().trim());
			inspeccion.setRecomendaciones(recomendacionesArea.getText().trim());

			// Asociar lugar de producción
			if (lugarSeleccionado != null) {
				inspeccion.setLugarProduccion(lugarSeleccionado);
			}

			// Guardar o actualizar
			if (isEditMode) {
				// Actualizar inspección existente
				// int result = inspeccionService.actualizar(inspeccion);
				MessageUtil.showUpdateSuccess(this);
			} else {
				// Crear nueva inspección
				// long id = inspeccionService.crear(inspeccion);
				// inspeccion.setId(String.valueOf(id));
				MessageUtil.showSaveSuccess(this);
			}

			acceptDialog();

		} catch (Exception ex) {
			MessageUtil.showError(this, "Error al guardar la inspección: " + ex.getMessage());
		}
	}

	/**
	 * Abre el diálogo de selección de lugar de producción.
	 */
	private void seleccionarLugar() {
		// TODO: Implementar diálogo de selección de lugar
		MessageUtil.showInfo(this, "Funcionalidad en desarrollo", "Seleccionar Lugar de Producción");

		// Simulación temporal
		lugarIdField.setText("1");
		lugarInfoLabel.setText("Lugar: Ejemplo Lugar - Predio: Ejemplo - 10.5 ha");
		lugarInfoLabel.setForeground(UIConstants.COLOR_SUCCESS);
	}

	/**
	 * Obtiene la inspección editada/creada.
	 */
	public InspeccionFitosanitaria getInspeccion() {
		return inspeccion;
	}
}
