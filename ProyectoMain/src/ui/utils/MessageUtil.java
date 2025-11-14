package ui.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * @version 1.0
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Utilidad para mostrar mensajes de diálogo estandarizados.
 * Simplifica la creación de mensajes de error, advertencia, información y confirmación.
 */
public final class MessageUtil {

	/**
	 * Constructor privado para evitar instanciación.
	 */
	private MessageUtil() {
		throw new AssertionError("No se puede instanciar la clase MessageUtil");
	}

	/**
	 * Muestra un mensaje de error.
	 * 
	 * @param parent Componente padre
	 * @param message Mensaje a mostrar
	 */
	public static void showError(Component parent, String message) {
		JOptionPane.showMessageDialog(
			parent,
			message,
			UIConstants.MSG_ERROR_TITLE,
			JOptionPane.ERROR_MESSAGE
		);
	}

	/**
	 * Muestra un mensaje de error con título personalizado.
	 * 
	 * @param parent Componente padre
	 * @param message Mensaje a mostrar
	 * @param title Título del diálogo
	 */
	public static void showError(Component parent, String message, String title) {
		JOptionPane.showMessageDialog(
			parent,
			message,
			title,
			JOptionPane.ERROR_MESSAGE
		);
	}

	/**
	 * Muestra un mensaje de advertencia.
	 * 
	 * @param parent Componente padre
	 * @param message Mensaje a mostrar
	 */
	public static void showWarning(Component parent, String message) {
		JOptionPane.showMessageDialog(
			parent,
			message,
			UIConstants.MSG_WARNING_TITLE,
			JOptionPane.WARNING_MESSAGE
		);
	}

	/**
	 * Muestra un mensaje de advertencia con título personalizado.
	 * 
	 * @param parent Componente padre
	 * @param message Mensaje a mostrar
	 * @param title Título del diálogo
	 */
	public static void showWarning(Component parent, String message, String title) {
		JOptionPane.showMessageDialog(
			parent,
			message,
			title,
			JOptionPane.WARNING_MESSAGE
		);
	}

	/**
	 * Muestra un mensaje de información.
	 * 
	 * @param parent Componente padre
	 * @param message Mensaje a mostrar
	 */
	public static void showInfo(Component parent, String message) {
		JOptionPane.showMessageDialog(
			parent,
			message,
			UIConstants.MSG_INFO_TITLE,
			JOptionPane.INFORMATION_MESSAGE
		);
	}

	/**
	 * Muestra un mensaje de información con título personalizado.
	 * 
	 * @param parent Componente padre
	 * @param message Mensaje a mostrar
	 * @param title Título del diálogo
	 */
	public static void showInfo(Component parent, String message, String title) {
		JOptionPane.showMessageDialog(
			parent,
			message,
			title,
			JOptionPane.INFORMATION_MESSAGE
		);
	}

	/**
	 * Muestra un mensaje de éxito.
	 * 
	 * @param parent Componente padre
	 * @param message Mensaje a mostrar
	 */
	public static void showSuccess(Component parent, String message) {
		JOptionPane.showMessageDialog(
			parent,
			message,
			UIConstants.MSG_SUCCESS_TITLE,
			JOptionPane.INFORMATION_MESSAGE
		);
	}

	/**
	 * Muestra un diálogo de confirmación.
	 * 
	 * @param parent Componente padre
	 * @param message Mensaje a mostrar
	 * @return true si el usuario confirma, false en caso contrario
	 */
	public static boolean showConfirm(Component parent, String message) {
		int result = JOptionPane.showConfirmDialog(
			parent,
			message,
			UIConstants.MSG_CONFIRM_TITLE,
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);
		return result == JOptionPane.YES_OPTION;
	}

	/**
	 * Muestra un diálogo de confirmación con título personalizado.
	 * 
	 * @param parent Componente padre
	 * @param message Mensaje a mostrar
	 * @param title Título del diálogo
	 * @return true si el usuario confirma, false en caso contrario
	 */
	public static boolean showConfirm(Component parent, String message, String title) {
		int result = JOptionPane.showConfirmDialog(
			parent,
			message,
			title,
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);
		return result == JOptionPane.YES_OPTION;
	}

	/**
	 * Muestra un diálogo para confirmar eliminación.
	 * 
	 * @param parent Componente padre
	 * @return true si el usuario confirma, false en caso contrario
	 */
	public static boolean showDeleteConfirm(Component parent) {
		return showConfirm(parent, UIConstants.MSG_DELETE_CONFIRM);
	}

	/**
	 * Muestra un mensaje de éxito al guardar.
	 * 
	 * @param parent Componente padre
	 */
	public static void showSaveSuccess(Component parent) {
		showSuccess(parent, UIConstants.MSG_SAVE_SUCCESS);
	}

	/**
	 * Muestra un mensaje de éxito al actualizar.
	 * 
	 * @param parent Componente padre
	 */
	public static void showUpdateSuccess(Component parent) {
		showSuccess(parent, UIConstants.MSG_UPDATE_SUCCESS);
	}

	/**
	 * Muestra un mensaje de éxito al eliminar.
	 * 
	 * @param parent Componente padre
	 */
	public static void showDeleteSuccess(Component parent) {
		showSuccess(parent, UIConstants.MSG_DELETE_SUCCESS);
	}

	/**
	 * Muestra un mensaje de campos requeridos.
	 * 
	 * @param parent Componente padre
	 */
	public static void showRequiredFields(Component parent) {
		showWarning(parent, UIConstants.MSG_REQUIRED_FIELDS);
	}

	/**
	 * Muestra un mensaje de datos inválidos.
	 * 
	 * @param parent Componente padre
	 */
	public static void showInvalidData(Component parent) {
		showError(parent, UIConstants.MSG_INVALID_DATA);
	}
}
