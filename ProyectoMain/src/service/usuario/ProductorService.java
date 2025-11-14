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

	public Productor obtenerPorNumeroIdentificacion(String numeroIdentificacion) throws ServiceException {
		try {
			validateNotEmpty(numeroIdentificacion, "numeroIdentificacion");
			validateNumeric(numeroIdentificacion, "numeroIdentificacion");
			
			// Buscar todos los productores y filtrar por número de identificación
			List<Productor> productores = dao.obtenerTodos();
			for (Productor p : productores) {
				if (p.getNumeroIdentificacion() != null && p.getNumeroIdentificacion().equals(numeroIdentificacion)) {
					return p;
				}
			}
			return null;
		} catch (ValidationException e) {
			throw e;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar productor por número de identificación", e);
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
			long id = dao.crear(productor);
			productor.setId(String.valueOf(id));
			return productor;
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
			dao.actualizar(productor);
			return productor;
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
		validateNotEmpty(productor.getNombre(), "nombre");
		
		if (productor.getCorreoElectronico() != null && !productor.getCorreoElectronico().trim().isEmpty()) {
			validateEmail(productor.getCorreoElectronico());
		}
	}
}
