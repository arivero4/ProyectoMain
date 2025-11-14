package service.usuario;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import DAO.PropietarioDAO;
import model.Propietario;
import service.base.ServiceBase;
import service.exceptions.ServiceException;
import service.exceptions.ValidationException;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Servicio para gestionar propietarios de predios.
 * Incluye validaciones de documentos y datos personales.
 */
public class PropietarioService extends ServiceBase<Propietario, PropietarioDAO> {

	public PropietarioService(PropietarioDAO dao) {
		super(dao);
	}

	public Propietario obtenerPorId(long id) throws ServiceException {
		try {
			validatePositive((double) id, "id");
			return dao.obtenerPorId(id);
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar propietario", e);
			throw new ServiceException("SEARCH_ERROR", "Error buscando propietario", e);
		}
	}

	public List<Propietario> obtenerTodos() throws ServiceException {
		try {
			return dao.obtenerTodos();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al obtener propietarios", e);
			throw new ServiceException("GET_ALL_ERROR", "Error obteniendo propietarios", e);
		}
	}

	public long crear(Propietario propietario) throws ServiceException {
		try {
			validarPropietario(propietario);
			return dao.crear(propietario);
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al crear propietario", e);
			throw new ServiceException("CREATE_ERROR", "Error creando propietario", e);
		}
	}

	public int actualizar(Propietario propietario) throws ServiceException {
		try {
			validarPropietario(propietario);
			return dao.actualizar(propietario);
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al actualizar propietario", e);
			throw new ServiceException("UPDATE_ERROR", "Error actualizando propietario", e);
		}
	}

	private void validarPropietario(Propietario propietario) throws ValidationException {
		validateNotNull(propietario, "propietario");
		validateNotEmpty(propietario.getNumeroIdentificacion(), "numeroIdentificacion");
		validateNotEmpty(propietario.getNombre(), "nombre");
	}
}
