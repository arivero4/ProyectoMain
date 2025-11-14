package service.exceptions;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Excepción para violaciones de reglas de negocio.
 * Se lanza cuando una operación intenta violar las reglas de negocio del dominio.
 * 
 * Ejemplos:
 * - Rotación de cultivos inválida
 * - Área de lote superior a predio
 * - Inspección duplicada para mismo lote
 * - Cambio de estado no permitido
 */
public class BusinessRuleException extends ServiceException {

	private static final long serialVersionUID = 1L;
	private String ruleName;
	private String affectedEntity;

	/**
	 * Constructor básico.
	 * 
	 * @param message - Descripción de la violación de regla
	 */
	public BusinessRuleException(String message) {
		super("BUSINESS_RULE_VIOLATION", message);
	}

	/**
	 * Constructor con nombre de regla.
	 * 
	 * @param ruleName - Nombre de la regla que fue violada
	 * @param message - Mensaje descriptivo
	 */
	public BusinessRuleException(String ruleName, String message) {
		super("BUSINESS_RULE_VIOLATION", "Regla '" + ruleName + "' violada: " + message);
		this.ruleName = ruleName;
	}

	/**
	 * Constructor con regla y entidad afectada.
	 * 
	 * @param ruleName - Nombre de la regla violada
	 * @param affectedEntity - Tipo de entidad afectada
	 * @param message - Mensaje descriptivo
	 */
	public BusinessRuleException(String ruleName, String affectedEntity, String message) {
		super("BUSINESS_RULE_VIOLATION", 
			"Regla '" + ruleName + "' violada en " + affectedEntity + ": " + message);
		this.ruleName = ruleName;
		this.affectedEntity = affectedEntity;
	}

	/**
	 * Constructor con causa raíz.
	 * 
	 * @param ruleName - Nombre de la regla
	 * @param message - Mensaje
	 * @param cause - Excepción original
	 */
	public BusinessRuleException(String ruleName, String message, Throwable cause) {
		super("BUSINESS_RULE_VIOLATION", "Regla '" + ruleName + "' violada: " + message, cause);
		this.ruleName = ruleName;
	}

	/**
	 * Obtiene el nombre de la regla de negocio violada.
	 * 
	 * @return String - Nombre de la regla
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * Obtiene la entidad afectada por la violación.
	 * 
	 * @return String - Tipo de entidad
	 */
	public String getAffectedEntity() {
		return affectedEntity;
	}

	/**
	 * Verifica si la excepción tiene una regla asociada.
	 * 
	 * @return boolean - true si tiene nombre de regla
	 */
	public boolean hasRuleName() {
		return ruleName != null && !ruleName.trim().isEmpty();
	}

	/**
	 * Verifica si la excepción tiene entidad asociada.
	 * 
	 * @return boolean - true si tiene entidad asociada
	 */
	public boolean hasAffectedEntity() {
		return affectedEntity != null && !affectedEntity.trim().isEmpty();
	}
}
