package ui.login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ui.components.BaseFrame;
import ui.main.MainFrame;
import ui.utils.UIConstants;
import ui.utils.MessageUtil;
import model.Usuario;
import DAO.UsuarioDAO;
import service.usuario.UsuarioService;
import service.exceptions.ServiceException;
import BaseDatos.Conexion;

/**
 * @version 1.0
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Ventana de inicio de sesión del sistema.
 * Permite autenticar usuarios mediante correo electrónico y contraseña.
 */
public class LoginFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;

	// Componentes
	private JPanel mainPanel;
	private JPanel headerPanel;
	private JPanel formPanel;
	private JPanel buttonPanel;

	private JLabel titleLabel;
	private JLabel subtitleLabel;
	private JLabel emailLabel;
	private JLabel passwordLabel;

	private JTextField emailField;
	private JPasswordField passwordField;

	private JButton loginButton;
	private JButton clearButton;

	private UsuarioService usuarioService;

	/**
	 * Constructor del frame de login.
	 */
	public LoginFrame() {
		super(UIConstants.APP_TITLE, UIConstants.SIZE_LOGIN);
		
		// Inicializar servicio
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO(Conexion.getInstance());
			this.usuarioService = new UsuarioService(usuarioDAO);
		} catch (Exception e) {
			MessageUtil.showError(this, "Error al inicializar el sistema: " + e.getMessage());
			System.exit(1);
		}
	}

	@Override
	protected void initializeComponents() {
		// Paneles principales
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(UIConstants.COLOR_BACKGROUND);
		mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

		headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		headerPanel.setBackground(UIConstants.COLOR_BACKGROUND);

		formPanel = new JPanel();
		formPanel.setLayout(new GridBagLayout());
		formPanel.setBackground(UIConstants.COLOR_BACKGROUND);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		buttonPanel.setBackground(UIConstants.COLOR_BACKGROUND);

		// Título
		titleLabel = new JLabel("Bienvenido");
		titleLabel.setFont(UIConstants.FONT_TITLE);
		titleLabel.setForeground(UIConstants.COLOR_PRIMARY);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		subtitleLabel = new JLabel("Sistema de Inspección Fitosanitaria");
		subtitleLabel.setFont(UIConstants.FONT_BODY);
		subtitleLabel.setForeground(UIConstants.COLOR_TEXT_SECONDARY);
		subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Labels
		emailLabel = new JLabel("Correo Electrónico:");
		emailLabel.setFont(UIConstants.FONT_BODY);

		passwordLabel = new JLabel("Contraseña:");
		passwordLabel.setFont(UIConstants.FONT_BODY);

		// Campos de texto
		emailField = new JTextField(20);
		emailField.setFont(UIConstants.FONT_BODY);
		emailField.setPreferredSize(new Dimension(250, 35));

		passwordField = new JPasswordField(20);
		passwordField.setFont(UIConstants.FONT_BODY);
		passwordField.setPreferredSize(new Dimension(250, 35));

		// Botones
		loginButton = createStyledButton(UIConstants.BTN_LOGIN, UIConstants.COLOR_PRIMARY);
		clearButton = createStyledButton(UIConstants.BTN_CLEAR, UIConstants.COLOR_SECONDARY);
	}

	@Override
	protected void setupLayout() {
		// Header
		headerPanel.add(Box.createVerticalStrut(20));
		headerPanel.add(titleLabel);
		headerPanel.add(Box.createVerticalStrut(5));
		headerPanel.add(subtitleLabel);
		headerPanel.add(Box.createVerticalStrut(30));

		// Form
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);

		// Email
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		formPanel.add(emailLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		formPanel.add(emailField, gbc);

		// Password
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		formPanel.add(passwordLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		formPanel.add(passwordField, gbc);

		// Espacio
		gbc.gridy = 4;
		formPanel.add(Box.createVerticalStrut(20), gbc);

		// Buttons
		buttonPanel.add(loginButton);
		buttonPanel.add(clearButton);

		// Main panel
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(mainPanel);
	}

	@Override
	protected void setupListeners() {
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});

		// Enter para login
		emailField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					passwordField.requestFocus();
				}
			}
		});

		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
	}

	/**
	 * Crea un botón estilizado.
	 */
	private JButton createStyledButton(String text, Color backgroundColor) {
		JButton button = new JButton(text);
		button.setFont(UIConstants.FONT_BUTTON);
		button.setPreferredSize(UIConstants.SIZE_BUTTON_STANDARD);
		button.setBackground(backgroundColor);
		button.setForeground(UIConstants.COLOR_TEXT_ON_PRIMARY);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return button;
	}

	/**
	 * Limpia los campos del formulario.
	 */
	private void clearFields() {
		emailField.setText("");
		passwordField.setText("");
		emailField.requestFocus();
	}

	/**
	 * Realiza el proceso de login.
	 */
	private void login() {
		String email = emailField.getText().trim();
		String password = new String(passwordField.getPassword()).trim();

		// Validaciones básicas
		if (email.isEmpty() || password.isEmpty()) {
			MessageUtil.showRequiredFields(this);
			return;
		}

		// Validar formato de email
		if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			MessageUtil.showError(this, "Por favor ingrese un correo electrónico válido");
			return;
		}

		// Autenticar usuario
		try {
			Usuario usuario = usuarioService.obtenerPorEmail(email);

			if (usuario == null) {
				MessageUtil.showError(this, "Usuario no encontrado");
				passwordField.setText("");
				return;
			}

			// TODO: Implementar verificación de contraseña con hash
			// Por ahora usamos una autenticación simple para demostración
			
			// Login exitoso
			MessageUtil.showSuccess(this, "¡Bienvenido, " + usuario.getNombre() + "!");
			
			// Abrir ventana principal
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					MainFrame mainFrame = new MainFrame(usuario);
					mainFrame.display();
					LoginFrame.this.dispose();
				}
			});

		} catch (ServiceException ex) {
			MessageUtil.showError(this, "Error al autenticar: " + ex.getMessage());
		}
	}

	/**
	 * Método principal para ejecutar la aplicación.
	 */
	public static void main(String[] args) {
		// Configurar Look and Feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Ejecutar en el hilo de eventos de Swing
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.display();
			}
		});
	}
}
