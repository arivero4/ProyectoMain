package service.base;

import java.util.logging.Logger;
import DAO.DAOBase;
import service.exceptions.ValidationException;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Clase base generica para todos los servicios.
 * Proporciona validaciones comunes y acceso al Logger.
 */
public abstract class ServiceBase<T, D extends DAOBase<T>> {
    protected D dao;
    protected static final Logger LOGGER = Logger.getLogger(ServiceBase.class.getName());

    public ServiceBase(D dao) {
        if (dao == null) throw new IllegalArgumentException("DAO cannot be null");
        this.dao = dao;
    }

    protected String getServiceName() {
        return this.getClass().getSimpleName();
    }

    public D getDao() {
        return dao;
    }

    protected void validateNotEmpty(String value, String fieldName) throws ValidationException {
        if (value == null || value.trim().isEmpty())
            throw new ValidationException(fieldName, "Cannot be empty");
    }

    protected void validateNotNull(Object value, String fieldName) throws ValidationException {
        if (value == null)
            throw new ValidationException(fieldName, "Cannot be null");
    }

    protected void validateEmail(String email) throws ValidationException {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
            throw new ValidationException("email", "Invalid email format");
    }

    protected void validatePositive(double value, String fieldName) throws ValidationException {
        if (value <= 0)
            throw new ValidationException(fieldName, "Must be positive");
    }

    protected void validateMinLength(String value, int min, String fieldName) throws ValidationException {
        if (value == null || value.length() < min)
            throw new ValidationException(fieldName, "Min length: " + min);
    }

    protected void validateMaxLength(String value, int max, String fieldName) throws ValidationException {
        if (value != null && value.length() > max)
            throw new ValidationException(fieldName, "Max length: " + max);
    }

    protected void validateNumeric(String value, String fieldName) throws ValidationException {
        if (value == null || !value.matches("\\d+"))
            throw new ValidationException(fieldName, "Must be numeric");
    }

    protected void validateInSet(String value, String[] validValues, String fieldName) throws ValidationException {
        if (value == null) {
            throw new ValidationException(fieldName, "Cannot be null");
        }
        for (String valid : validValues) {
            if (valid.equals(value)) {
                return;
            }
        }
        throw new ValidationException(fieldName, "Invalid value. Allowed: " + String.join(", ", validValues));
    }

    @Override
    public String toString() {
        return "ServiceBase [" + getServiceName() + "]";
    }
}
