package service.negocio;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import DAO.LoteDAO;
import model.Lote;
import service.base.ServiceBase;
import service.exceptions.BusinessRuleException;
import service.exceptions.ServiceException;
import service.exceptions.ValidationException;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Servicio para gestionar lotes agricolas.
 * Incluye validaciones de areas y ubicacion.
 */
public class LoteService extends ServiceBase<Lote, LoteDAO> {

	public LoteService(LoteDAO dao) {
		super(dao);
	}

	public Lote obtenerPorId(long id) throws ServiceException {
		try {
			validatePositive((double) id, "id");
			return dao.obtenerPorId(id);
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar lote", e);
			throw new ServiceException("SEARCH_ERROR", "Error buscando lote", e);
		}
	}

	public List<Lote> obtenerTodos() throws ServiceException {
		try {
			return dao.obtenerTodos();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al obtener lotes", e);
			throw new ServiceException("GET_ALL_ERROR", "Error obteniendo lotes", e);
		}
	}

	public void validarAreaLote(Lote lote, double areaMaximaPredio) 
			throws ValidationException, BusinessRuleException {
		
		validateNotNull(lote, "lote");
		validatePositive((double) lote.getArea(), "area");
		
		if (lote.getArea() > areaMaximaPredio) {
			throw new BusinessRuleException("AreaLote", 
				"El area del lote (" + lote.getArea() + " ha) excede el area del predio (" + 
				areaMaximaPredio + " ha)");
		}
		
		LOGGER.fine("Area del lote validada correctamente: " + lote.getArea() + " ha");
	}
}
