package service.negocio;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import DAO.ResultadoTecnicoDAO;
import model.ResultadoTecnico;
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
 * Servicio para gestionar resultados tecnicos de inspecciones.
 * Incluye generacion de recomendaciones y medidas.
 */
public class ResultadoTecnicoService extends ServiceBase<ResultadoTecnico, ResultadoTecnicoDAO> {

	public ResultadoTecnicoService(ResultadoTecnicoDAO dao) {
		super(dao);
	}

	public ResultadoTecnico obtenerPorId(long id) throws ServiceException {
		try {
			validatePositive((double) id, "id");
			return dao.obtenerPorId(id);
		} catch (ValidationException e) {
			throw new ServiceException("VALIDATION_ERROR", e.getMessage(), e);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al buscar resultado tecnico", e);
			throw new ServiceException("SEARCH_ERROR", "Error buscando resultado", e);
		}
	}

	public List<ResultadoTecnico> obtenerTodos() throws ServiceException {
		try {
			return dao.obtenerTodos();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al obtener resultados tecnicos", e);
			throw new ServiceException("GET_ALL_ERROR", "Error obteniendo resultados", e);
		}
	}

	public String generarRecomendacion(String nivelSeveridad, String tipoCultivo) 
			throws ValidationException {
		
		validateNotEmpty(nivelSeveridad, "nivelSeveridad");
		validateNotEmpty(tipoCultivo, "tipoCultivo");
		
		StringBuilder recomendacion = new StringBuilder();
		
		switch (nivelSeveridad.toUpperCase()) {
			case "BAJA":
				recomendacion.append("Monitoreo preventivo. ");
				recomendacion.append("Revision semanal del cultivo de ").append(tipoCultivo).append(". ");
				break;
			case "MEDIA":
				recomendacion.append("Control moderado. ");
				recomendacion.append("Aplicar tratamiento preventivo en ").append(tipoCultivo).append(". ");
				break;
			case "ALTA":
				recomendacion.append("ATENCION: Control inmediato. ");
				recomendacion.append("Aplicar tratamiento curativo urgente. ");
				break;
			case "CRITICA":
				recomendacion.append("ALERTA MAXIMA: Aislamiento de area afectada. ");
				recomendacion.append("Posible erradicacion del cultivo. ");
				recomendacion.append("Notificar al ICA inmediatamente. ");
				break;
			default:
				recomendacion.append("Evaluacion tecnica requerida. ");
		}
		
		return recomendacion.toString();
	}
}
