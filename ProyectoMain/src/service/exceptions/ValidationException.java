package service.exceptions;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Excepción para errores de validación de datos.
 * Se lanza cuando los datos de entrada no cumplen las reglas de validación.
 * 
 * Ejemplos:
 * - Email inválido
 * - Documento duplicado
 * - Campos requeridos vacíos
 * - Formato de datos incorrecto
 */
public class ValidationException extends ServiceException {

	private static final long serialVersionUID = 1L;
	private String fieldName;

	/**
	 * Constructor básico.
	 * 
	 * @param message - Mensaje de validación
	 */
	public ValidationException(String message) {
		super("VALIDATION_ERROR", message);
	}

	/**
	 * Constructor con campo que falló.
	 * 
	 * @param fieldName - Nombre del campo que falló la validación
	 * @param message - Mensaje descriptivo del error
	 */
	public ValidationException(String fieldName, String message) {
		super("VALIDATION_ERROR", "Campo '" + fieldName + "': " + message);
		this.fieldName = fieldName;
	}

	/**
	 * Constructor con mensaje y causa.
	 * 
	 * @param message - Mensaje de error
	 * @param cause - Excepción original
	 */
	public ValidationException(String message, Throwable cause) {
		super("VALIDATION_ERROR", message, cause);
	}

	/**
	 * Constructor con campo, mensaje y causa.
	 * 
	 * @param fieldName - Campo que falló
	 * @param message - Mensaje
	 * @param cause - Causa original
	 */
	public ValidationException(String fieldName, String message, Throwable cause) {
		super("VALIDATION_ERROR", "Campo '" + fieldName + "': " + message, cause);
		this.fieldName = fieldName;
	}

	/**
	 * Obtiene el nombre del campo que falló la validación.
	 * 
	 * @return String - Nombre del campo
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Verifica si la excepción está asociada a un campo específico.
	 * 
	 * @return boolean - true si tiene campo asociado
	 */
	public boolean hasFieldName() {
		return fieldName != null && !fieldName.trim().isEmpty();
	}
}
