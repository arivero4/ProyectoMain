package service.utilidad;

import java.util.regex.Pattern;
import service.exceptions.ValidationException;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Servicio de validaciones transversales.
 * Proporciona validaciones comunes para todo el sistema.
 */
public class ValidationService {

	private static final Pattern EMAIL_PATTERN = 
		Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
	
	private static final Pattern PHONE_PATTERN = 
		Pattern.compile("^[0-9]{7,10}$");
	
	private static final Pattern CEDULA_PATTERN = 
		Pattern.compile("^[0-9]{6,10}$");

	public boolean isValidEmail(String email) {
		return email != null && EMAIL_PATTERN.matcher(email).matches();
	}

	public boolean isValidPhone(String phone) {
		return phone != null && PHONE_PATTERN.matcher(phone).matches();
	}

	public boolean isValidCedula(String cedula) {
		return cedula != null && CEDULA_PATTERN.matcher(cedula).matches();
	}

	public void validateEmail(String email) throws ValidationException {
		if (!isValidEmail(email)) {
			throw new ValidationException("email", "Formato de email invalido");
		}
	}

	public void validatePhone(String phone) throws ValidationException {
		if (!isValidPhone(phone)) {
			throw new ValidationException("telefono", "Debe contener entre 7 y 10 digitos");
		}
	}

	public void validateCedula(String cedula) throws ValidationException {
		if (!isValidCedula(cedula)) {
			throw new ValidationException("cedula", "Debe contener entre 6 y 10 digitos");
		}
	}

	public void validateRange(double value, double min, double max, String fieldName) 
			throws ValidationException {
		if (value < min || value > max) {
			throw new ValidationException(fieldName, 
				"Debe estar entre " + min + " y " + max);
		}
	}

	public void validatePositiveInteger(int value, String fieldName) 
			throws ValidationException {
		if (value <= 0) {
			throw new ValidationException(fieldName, "Debe ser un numero entero positivo");
		}
	}
}
