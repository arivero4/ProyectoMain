package ui.forms;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.InspeccionFitosanitaria;
import service.negocio.InspeccionFitosanitariaService;
import ui.components.BaseDialog;
import ui.utils.MessageUtil;
import ui.utils.UIConstants;

/**
 * @version 1.0
 * Formulario simplificado para crear y editar inspecciones fitosanitarias.
 * Trabaja con el modelo actual que solo tiene ID y fechaInspeccion.
 */
public class InspeccionFitosanitariaFormSimple extends BaseDialog {

	private static final long serialVersionUID = 1L;

	private boolean isEditMode = false;
	private InspeccionFitosanitaria inspeccion;
	private InspeccionFitosanitariaService inspeccionService;

	// Componentes
	private JPanel mainPanel;
	private JPanel formPanel;
	private JPanel buttonPanel;

	private JTextField idField;
	private JTextField fechaField;
	private JButton guardarButton;
	private JButton cancelarButton;

	public InspeccionFitosanitariaFormSimple(JFrame parent, InspeccionFitosanitariaService service) {
		super(parent, "Nueva Inspección Fitosanitaria", true, UIConstants.SIZE_FORM_SMALL);
		this.inspeccionService = service;
		this.isEditMode = false;
		this.inspeccion = new InspeccionFitosanitaria();
	}

	public InspeccionFitosanitariaFormSimple(JFrame parent, InspeccionFitosanitariaService service,
			InspeccionFitosanitaria inspeccion) {
		super(parent, "Editar Inspección Fitosanitaria", true, UIConstants.SIZE_FORM_SMALL);
		this.inspeccionService = service;
		this.isEditMode = true;
		this.inspeccion = inspeccion;
	}

	@Override
	protected void initializeComponents() {
		mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		mainPanel.setBackground(UIConstants.COLOR_BACKGROUND);

		formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(UIConstants.COLOR_BACKGROUND);

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		buttonPanel.setBackground(UIConstants.COLOR_BACKGROUND);

		idField = new JTextField(20);
		idField.setEditable(false);
		idField.setBackground(UIConstants.COLOR_BORDER);
		idField.setFont(UIConstants.FONT_BODY);

		fechaField = new JTextField(20);
		fechaField.setFont(UIConstants.FONT_BODY);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		fechaField.setText(sdf.format(new Date()));

		guardarButton = new JButton(UIConstants.BTN_SAVE);
		guardarButton.setFont(UIConstants.FONT_BUTTON);
		guardarButton.setPreferredSize(UIConstants.SIZE_BUTTON_STANDARD);
		guardarButton.setBackground(UIConstants.COLOR_PRIMARY);
		guardarButton.setForeground(UIConstants.COLOR_TEXT_ON_PRIMARY);
		guardarButton.setFocusPainted(false);

		cancelarButton = new JButton(UIConstants.BTN_CANCEL);
		cancelarButton.setFont(UIConstants.FONT_BUTTON);
		cancelarButton.setPreferredSize(UIConstants.SIZE_BUTTON_STANDARD);

		if (isEditMode && inspeccion != null) {
			loadInspeccionData();
		}
	}

	@Override
	protected void setupLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);

		int row = 0;

		if (isEditMode) {
			gbc.gridx = 0;
			gbc.gridy = row;
			gbc.anchor = GridBagConstraints.WEST;
			JLabel idLabel = new JLabel("ID:");
			idLabel.setFont(UIConstants.FONT_BODY);
			formPanel.add(idLabel, gbc);

			gbc.gridx = 1;
			formPanel.add(idField, gbc);
			row++;
		}

		gbc.gridx = 0;
		gbc.gridy = row;
		JLabel fechaLabel = new JLabel("Fecha Inspección:" + UIConstants.LBL_REQUIRED);
		fechaLabel.setFont(UIConstants.FONT_BODY);
		formPanel.add(fechaLabel, gbc);

		gbc.gridx = 1;
		formPanel.add(fechaField, gbc);

		buttonPanel.add(guardarButton);
		buttonPanel.add(cancelarButton);

		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(mainPanel);
	}

	@Override
	protected void setupListeners() {
		guardarButton.addActionListener(e -> guardarInspeccion());
		cancelarButton.addActionListener(e -> cancelDialog());
	}

	private void loadInspeccionData() {
		if (inspeccion == null)
			return;

		idField.setText(inspeccion.getId());

		if (inspeccion.getFechaInspeccion() != null) {
			fechaField.setText(inspeccion.getFechaInspeccion());
		}
	}

	private boolean validarDatos() {
		if (fechaField.getText().trim().isEmpty()) {
			MessageUtil.showWarning(this, "Por favor ingrese la fecha de inspección");
			fechaField.requestFocus();
			return false;
		}
		return true;
	}

	private void guardarInspeccion() {
		if (!validarDatos()) {
			return;
		}

		try {
			inspeccion.setFechaInspeccion(fechaField.getText().trim());

			if (isEditMode) {
				MessageUtil.showInfo(this, "Actualización pendiente de implementar en servicio");
			} else {
				MessageUtil.showInfo(this, "Creación pendiente de implementar en servicio");
			}

			acceptDialog();

		} catch (Exception ex) {
			MessageUtil.showError(this, "Error al guardar inspección: " + ex.getMessage());
		}
	}

	public InspeccionFitosanitaria getInspeccion() {
		return inspeccion;
	}
}
