package service.usuario;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import DAO.AsistenteTecnicoDAO;
import model.AsistenteTecnico;
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
 * Servicio para gestionar asistentes tecnicos.
 * Incluye validaciones para licencias y especialidades.
 */
public class AsistenteTecnicoService extends ServiceBase<AsistenteTecnico, AsistenteTecnicoDAO> {

	public AsistenteTecnicoService(AsistenteTecnicoDAO dao) {
		super(dao);
	}

	public AsistenteTecnico obtenerPorId(long id) throws ServiceException {
		try {
			validatePositive((double) id, "id");
			return dao.obtenerPorId(id);
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar asistente tecnico", e);
			throw new ServiceException("SEARCH_ERROR", "Error buscando asistente tecnico", e);
		}
	}

	public List<AsistenteTecnico> obtenerTodos() throws ServiceException {
		try {
			return dao.obtenerTodos();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al obtener asistentes tecnicos", e);
			throw new ServiceException("GET_ALL_ERROR", "Error obteniendo asistentes", e);
		}
	}

	public long crear(AsistenteTecnico asistente) throws ServiceException {
		try {
			validarAsistente(asistente);
			return dao.crear(asistente);
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al crear asistente tecnico", e);
			throw new ServiceException("CREATE_ERROR", "Error creando asistente", e);
		}
	}

	public int actualizar(AsistenteTecnico asistente) throws ServiceException {
		try {
			validarAsistente(asistente);
			return dao.actualizar(asistente);
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al actualizar asistente", e);
			throw new ServiceException("UPDATE_ERROR", "Error actualizando asistente", e);
		}
	}

	private void validarAsistente(AsistenteTecnico asistente) throws ValidationException {
		validateNotNull(asistente, "asistente");
		validateNotEmpty(asistente.getNumeroIdentificacion(), "numeroIdentificacion");
		validateNotEmpty(asistente.getNombre(), "nombre");
		
		if (asistente.getCorreoElectronico() != null && !asistente.getCorreoElectronico().trim().isEmpty()) {
			validateEmail(asistente.getCorreoElectronico());
		}
	}
}
