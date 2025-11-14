package service.exceptions;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Excepción base para toda la capa de servicios.
 * Proporciona un punto centralizado para manejo de errores en servicios.
 * 
 * Todos los servicios deben lanzar o encapsular excepciones en esta clase.
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	private final String errorCode;
	private Object[] parameters;

	/**
	 * Constructor básico con mensaje.
	 * 
	 * @param message - Mensaje de error descriptivo
	 */
	public ServiceException(String message) {
		super(message);
		this.errorCode = "UNKNOWN_ERROR";
	}

	/**
	 * Constructor con mensaje y causa raíz.
	 * 
	 * @param message - Mensaje de error
	 * @param cause - Excepción original que causó el error
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		this.errorCode = "UNKNOWN_ERROR";
	}

	/**
	 * Constructor con código de error y mensaje.
	 * 
	 * @param errorCode - Código identificador del error
	 * @param message - Mensaje de error descriptivo
	 */
	public ServiceException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * Constructor completo con código, mensaje y causa.
	 * 
	 * @param errorCode - Código identificador del error
	 * @param message - Mensaje de error
	 * @param cause - Excepción original
	 */
	public ServiceException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	/**
	 * Constructor con parámetros para mensajes dinámicos.
	 * 
	 * @param errorCode - Código del error
	 * @param message - Mensaje (puede contener placeholders {})
	 * @param parameters - Parámetros para reemplazar en el mensaje
	 */
	public ServiceException(String errorCode, String message, Object[] parameters) {
		super(message);
		this.errorCode = errorCode;
		this.parameters = parameters;
	}

	/**
	 * Obtiene el código de error.
	 * 
	 * @return String - Código identificador del error
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Obtiene los parámetros del error.
	 * 
	 * @return Object[] - Array de parámetros
	 */
	public Object[] getParameters() {
		return parameters;
	}

	/**
	 * Obtiene el mensaje formateado con parámetros.
	 * 
	 * @return String - Mensaje completo con parámetros reemplazados
	 */
	@Override
	public String getMessage() {
		String message = super.getMessage();
		if (parameters != null && parameters.length > 0) {
			for (int i = 0; i < parameters.length; i++) {
				message = message.replace("{" + i + "}", String.valueOf(parameters[i]));
			}
		}
		return "[" + errorCode + "] " + message;
	}

	/**
	 * Retorna una representación en String de la excepción.
	 * 
	 * @return String - Representación completa
	 */
	@Override
	public String toString() {
		return "ServiceException [errorCode=" + errorCode + ", message=" + getMessage() + "]";
	}
}
