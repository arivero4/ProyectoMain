package service.usuario;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import DAO.ProductorDAO;
import model.Productor;
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
 * Servicio para gestionar productores agricolas.
 * Incluye validaciones especificas para datos de productores.
 */
public class ProductorService extends ServiceBase<Productor, ProductorDAO> {

	public ProductorService(ProductorDAO dao) {
		super(dao);
	}

	public Productor obtenerPorId(long id) throws ServiceException {
		try {
			validatePositive(id, "id");
			return dao.obtenerPorId(id);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar productor por ID", e);
			throw new ServiceException("SEARCH_ERROR", "Error buscando productor", e);
		}
	}

	public Productor obtenerPorCedula(String cedula) throws ServiceException {
		try {
			validateNotEmpty(cedula, "cedula");
			validateNumeric(cedula, "cedula");
			return dao.obtenerPorCedula(cedula);
		} catch (ValidationException e) {
			throw e;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar productor por cedula", e);
			throw new ServiceException("SEARCH_ERROR", "Error buscando productor", e);
		}
	}

	public List<Productor> obtenerTodos() throws ServiceException {
		try {
			return dao.obtenerTodos();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al obtener todos los productores", e);
			throw new ServiceException("GET_ALL_ERROR", "Error obteniendo productores", e);
		}
	}

	public Productor crear(Productor productor) throws ServiceException {
		try {
			validarProductor(productor);
			return dao.crear(productor);
		} catch (ValidationException e) {
			throw e;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al crear productor", e);
			throw new ServiceException("CREATE_ERROR", "Error creando productor", e);
		}
	}

	public Productor actualizar(Productor productor) throws ServiceException {
		try {
			validarProductor(productor);
			return dao.actualizar(productor);
		} catch (ValidationException e) {
			throw e;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al actualizar productor", e);
			throw new ServiceException("UPDATE_ERROR", "Error actualizando productor", e);
		}
	}

	private void validarProductor(Productor productor) throws ValidationException {
		validateNotNull(productor, "productor");
		validateNotEmpty(productor.getNumeroIdentificacion(), "numeroIdentificacion");
		validateNotEmpty(productor.getNombre(), "nombres");
		validateNotEmpty(productor.getApellidos(), "apellidos");
		
		if (productor.getCorreoElectronico() != null && !productor.getCorreoElectronico().trim().isEmpty()) {
			validateEmail(productor.getCorreoElectronico());
		}
	}
}
