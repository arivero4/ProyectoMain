package service.negocio;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import DAO.PlagaDAO;
import model.Plaga;
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
 * Servicio para gestionar plagas.
 * Incluye validaciones y alertas epidemiologicas.
 */
public class PlagaService extends ServiceBase<Plaga, PlagaDAO> {

	public PlagaService(PlagaDAO dao) {
		super(dao);
	}

	public Plaga obtenerPorId(long id) throws ServiceException {
		try {
			validatePositive((double) id, "id");
			return dao.obtenerPorId(id);
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar plaga", e);
			throw new ServiceException("SEARCH_ERROR", "Error buscando plaga", e);
		}
	}

	public List<Plaga> obtenerTodos() throws ServiceException {
		try {
			return dao.obtenerTodos();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al obtener plagas", e);
			throw new ServiceException("GET_ALL_ERROR", "Error obteniendo plagas", e);
		}
	}

	public boolean requiereAlerta(String nivelSeveridad) throws ValidationException {
		validateNotEmpty(nivelSeveridad, "nivelSeveridad");
		
		String[] nivelesAlerta = {"ALTA", "CRITICA", "CUARENTENARIA"};
		validateInSet(nivelSeveridad, nivelesAlerta, "nivelSeveridad");
		
		return nivelSeveridad.equals("CRITICA") || nivelSeveridad.equals("CUARENTENARIA");
	}
}
