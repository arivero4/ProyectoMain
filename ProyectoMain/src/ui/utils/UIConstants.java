package ui.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * @version 1.0
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Constantes de interfaz de usuario.
 * Define colores, fuentes, dimensiones y configuraciones estándar para toda la aplicación.
 */
public final class UIConstants {

	// Colores principales
	public static final Color COLOR_PRIMARY = new Color(34, 139, 34);      // Verde forestal
	public static final Color COLOR_SECONDARY = new Color(46, 125, 50);    // Verde más oscuro
	public static final Color COLOR_ACCENT = new Color(76, 175, 80);       // Verde claro
	public static final Color COLOR_BACKGROUND = new Color(250, 250, 250); // Gris muy claro
	public static final Color COLOR_SURFACE = Color.WHITE;
	public static final Color COLOR_ERROR = new Color(211, 47, 47);        // Rojo
	public static final Color COLOR_WARNING = new Color(245, 124, 0);      // Naranja
	public static final Color COLOR_SUCCESS = new Color(56, 142, 60);      // Verde
	public static final Color COLOR_INFO = new Color(2, 136, 209);         // Azul
	
	// Colores de texto
	public static final Color COLOR_TEXT_PRIMARY = new Color(33, 33, 33);
	public static final Color COLOR_TEXT_SECONDARY = new Color(117, 117, 117);
	public static final Color COLOR_TEXT_DISABLED = new Color(189, 189, 189);
	public static final Color COLOR_TEXT_ON_PRIMARY = Color.WHITE;
	
	// Colores de bordes
	public static final Color COLOR_BORDER = new Color(224, 224, 224);
	public static final Color COLOR_BORDER_FOCUS = COLOR_PRIMARY;
	
	// Fuentes
	public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 24);
	public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.BOLD, 18);
	public static final Font FONT_HEADING = new Font("Segoe UI", Font.BOLD, 16);
	public static final Font FONT_SUBHEADING = new Font("Segoe UI", Font.BOLD, 14);
	public static final Font FONT_BODY = new Font("Segoe UI", Font.PLAIN, 13);
	public static final Font FONT_CAPTION = new Font("Segoe UI", Font.PLAIN, 11);
	public static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 13);
	
	// Dimensiones de ventanas
	public static final Dimension SIZE_LOGIN = new Dimension(400, 500);
	public static final Dimension SIZE_MAIN = new Dimension(1280, 720);
	public static final Dimension SIZE_DIALOG_SMALL = new Dimension(400, 300);
	public static final Dimension SIZE_DIALOG_MEDIUM = new Dimension(600, 400);
	public static final Dimension SIZE_DIALOG_LARGE = new Dimension(800, 600);
	public static final Dimension SIZE_FORM_SMALL = new Dimension(500, 400);
	public static final Dimension SIZE_FORM_MEDIUM = new Dimension(700, 500);
	public static final Dimension SIZE_FORM_LARGE = new Dimension(900, 650);
	
	// Dimensiones de componentes
	public static final Dimension SIZE_BUTTON_STANDARD = new Dimension(120, 35);
	public static final Dimension SIZE_BUTTON_LARGE = new Dimension(150, 40);
	public static final Dimension SIZE_BUTTON_SMALL = new Dimension(80, 30);
	public static final Dimension SIZE_TEXTFIELD = new Dimension(250, 30);
	public static final Dimension SIZE_TEXTAREA = new Dimension(300, 100);
	
	// Espaciado
	public static final int PADDING_SMALL = 5;
	public static final int PADDING_MEDIUM = 10;
	public static final int PADDING_LARGE = 20;
	public static final int GAP_SMALL = 5;
	public static final int GAP_MEDIUM = 10;
	public static final int GAP_LARGE = 15;
	
	// Bordes
	public static final int BORDER_RADIUS = 5;
	public static final int BORDER_THICKNESS = 1;
	
	// Textos de la aplicación
	public static final String APP_NAME = "Sistema de Inspección Fitosanitaria";
	public static final String APP_VERSION = "v2.1";
	public static final String APP_TITLE = APP_NAME + " - " + APP_VERSION;
	
	// Mensajes estándar
	public static final String MSG_ERROR_TITLE = "Error";
	public static final String MSG_WARNING_TITLE = "Advertencia";
	public static final String MSG_SUCCESS_TITLE = "Éxito";
	public static final String MSG_INFO_TITLE = "Información";
	public static final String MSG_CONFIRM_TITLE = "Confirmar";
	
	public static final String MSG_DELETE_CONFIRM = "¿Está seguro que desea eliminar este registro?";
	public static final String MSG_SAVE_SUCCESS = "Registro guardado exitosamente";
	public static final String MSG_UPDATE_SUCCESS = "Registro actualizado exitosamente";
	public static final String MSG_DELETE_SUCCESS = "Registro eliminado exitosamente";
	public static final String MSG_REQUIRED_FIELDS = "Por favor complete todos los campos obligatorios";
	public static final String MSG_INVALID_DATA = "Los datos ingresados no son válidos";
	
	// Etiquetas comunes
	public static final String LBL_ID = "ID:";
	public static final String LBL_NAME = "Nombre:";
	public static final String LBL_EMAIL = "Correo Electrónico:";
	public static final String LBL_PHONE = "Teléfono:";
	public static final String LBL_ADDRESS = "Dirección:";
	public static final String LBL_DATE = "Fecha:";
	public static final String LBL_STATUS = "Estado:";
	public static final String LBL_NOTES = "Notas:";
	public static final String LBL_REQUIRED = "*";
	
	// Botones comunes
	public static final String BTN_SAVE = "Guardar";
	public static final String BTN_CANCEL = "Cancelar";
	public static final String BTN_DELETE = "Eliminar";
	public static final String BTN_EDIT = "Editar";
	public static final String BTN_NEW = "Nuevo";
	public static final String BTN_SEARCH = "Buscar";
	public static final String BTN_CLEAR = "Limpiar";
	public static final String BTN_REFRESH = "Actualizar";
	public static final String BTN_CLOSE = "Cerrar";
	public static final String BTN_LOGIN = "Iniciar Sesión";
	public static final String BTN_LOGOUT = "Cerrar Sesión";
	public static final String BTN_EXPORT = "Exportar";
	public static final String BTN_IMPORT = "Importar";
	public static final String BTN_PRINT = "Imprimir";
	
	// Roles de usuario
	public static final String ROLE_ADMIN = "ADMINISTRADOR";
	public static final String ROLE_INSPECTOR = "INSPECTOR";
	public static final String ROLE_TECNICO = "TECNICO";
	public static final String ROLE_PRODUCTOR = "PRODUCTOR";
	public static final String ROLE_PROPIETARIO = "PROPIETARIO";
	
	/**
	 * Constructor privado para evitar instanciación.
	 */
	private UIConstants() {
		throw new AssertionError("No se puede instanciar la clase UIConstants");
	}
}
