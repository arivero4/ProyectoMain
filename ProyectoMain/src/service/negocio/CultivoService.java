package service.negocio;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import DAO.CultivoDAO;
import model.Cultivo;
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
 * Servicio para gestionar cultivos.
 * Incluye validaciones de areas y tipos de cultivo.
 */
public class CultivoService extends ServiceBase<Cultivo, CultivoDAO> {

	public CultivoService(CultivoDAO dao) {
		super(dao);
	}

	public Cultivo obtenerPorId(long id) throws ServiceException {
		try {
			validatePositive((double) id, "id");
			return dao.obtenerPorId(id);
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar cultivo", e);
			throw new ServiceException("SEARCH_ERROR", "Error buscando cultivo", e);
		}
	}

	public List<Cultivo> obtenerTodos() throws ServiceException {
		try {
			return dao.obtenerTodos();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al obtener cultivos", e);
			throw new ServiceException("GET_ALL_ERROR", "Error obteniendo cultivos", e);
		}
	}

	public void validarAreaCultivo(double areaCultivada, double areaMaximaLote) throws ValidationException {
		if (areaCultivada <= 0) {
			throw new ValidationException("areaCultivada", "Debe ser mayor a cero");
		}
		
		if (areaCultivada > areaMaximaLote) {
			throw new ValidationException("areaCultivada", 
				"No puede exceder el area del lote (" + areaMaximaLote + " ha)");
		}
	}
}
