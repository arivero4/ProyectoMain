package service.negocio;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import DAO.InspeccionFitosanitariaDAO;
import model.InspeccionFitosanitaria;
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
 * Servicio para gestionar inspecciones fitosanitarias.
 * Incluye validaciones y calculos de indices de infestacion.
 */
public class InspeccionFitosanitariaService extends ServiceBase<InspeccionFitosanitaria, InspeccionFitosanitariaDAO> {

	public InspeccionFitosanitariaService(InspeccionFitosanitariaDAO dao) {
		super(dao);
	}

	public InspeccionFitosanitaria obtenerPorId(long id) throws ServiceException {
		try {
			validatePositive((double) id, "id");
			return dao.obtenerPorId(id);
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar inspeccion", e);
			throw new ServiceException("SEARCH_ERROR", "Error buscando inspeccion", e);
		}
	}

	public List<InspeccionFitosanitaria> obtenerTodos() throws ServiceException {
		try {
			return dao.obtenerTodos();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al obtener inspecciones", e);
			throw new ServiceException("GET_ALL_ERROR", "Error obteniendo inspecciones", e);
		}
	}

	public List<InspeccionFitosanitaria> obtenerPorEstado(String estado) throws ServiceException {
		try {
			validateNotEmpty(estado, "estado");
			return dao.obtenerPorEstado(estado);
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al obtener inspecciones por estado", e);
			throw new ServiceException("SEARCH_ERROR", "Error buscando inspecciones", e);
		}
	}

	public double calcularIndiceInfestacion(int plantasAfectadas, int plantasMuestreadas) 
			throws ValidationException, BusinessRuleException {
		
		if (plantasMuestreadas <= 0) {
			throw new ValidationException("plantasMuestreadas", "Debe ser mayor a cero");
		}
		
		if (plantasAfectadas < 0) {
			throw new ValidationException("plantasAfectadas", "No puede ser negativo");
		}
		
		if (plantasAfectadas > plantasMuestreadas) {
			throw new BusinessRuleException("IndiceInfestacion", 
				"Plantas afectadas no puede exceder plantas muestreadas");
		}
		
		return (double) plantasAfectadas / plantasMuestreadas * 100.0;
	}

	public String evaluarSeveridad(double indice) throws ValidationException {
		if (indice < 0 || indice > 100) {
			throw new ValidationException("indice", "Debe estar entre 0 y 100");
		}
		
		if (indice < 10) return "BAJA";
		if (indice < 30) return "MEDIA";
		if (indice < 60) return "ALTA";
		return "CRITICA";
	}
}
