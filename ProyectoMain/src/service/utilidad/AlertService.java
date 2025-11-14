package service.utilidad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Servicio para gestionar alertas del sistema.
 * Permite crear, consultar y gestionar alertas epidemiologicas.
 */
public class AlertService {

	private static final Logger LOGGER = Logger.getLogger(AlertService.class.getName());
	private final List<Alerta> alertasActivas = new ArrayList<>();

	public void crearAlerta(String tipo, String descripcion, String nivelSeveridad, long entidadAfectada) {
		Alerta alerta = new Alerta(tipo, descripcion, nivelSeveridad, entidadAfectada);
		alertasActivas.add(alerta);
		LOGGER.warning("ALERTA CREADA: " + tipo + " - " + descripcion);
	}

	public List<Alerta> obtenerAlertasActivas() {
		return new ArrayList<>(alertasActivas);
	}

	public List<Alerta> obtenerAlertasCriticas() {
		List<Alerta> criticas = new ArrayList<>();
		for (Alerta alerta : alertasActivas) {
			if ("CRITICA".equals(alerta.getNivelSeveridad())) {
				criticas.add(alerta);
			}
		}
		return criticas;
	}

	public void cerrarAlerta(long idAlerta) {
		alertasActivas.removeIf(a -> a.getId() == idAlerta);
		LOGGER.info("Alerta cerrada: " + idAlerta);
	}

	public int contarAlertasPorTipo(String tipo) {
		int contador = 0;
		for (Alerta alerta : alertasActivas) {
			if (tipo.equals(alerta.getTipo())) {
				contador++;
			}
		}
		return contador;
	}

	// Clase interna para representar alertas
	public static class Alerta {
		private static long contadorId = 1;
		private final long id;
		private final String tipo;
		private final String descripcion;
		private final String nivelSeveridad;
		private final long entidadAfectada;
		private final Date fechaCreacion;

		public Alerta(String tipo, String descripcion, String nivelSeveridad, long entidadAfectada) {
			this.id = contadorId++;
			this.tipo = tipo;
			this.descripcion = descripcion;
			this.nivelSeveridad = nivelSeveridad;
			this.entidadAfectada = entidadAfectada;
			this.fechaCreacion = new Date();
		}

		public long getId() { return id; }
		public String getTipo() { return tipo; }
		public String getDescripcion() { return descripcion; }
		public String getNivelSeveridad() { return nivelSeveridad; }
		public long getEntidadAfectada() { return entidadAfectada; }
		public Date getFechaCreacion() { return fechaCreacion; }

		@Override
		public String toString() {
			return String.format("[%s] %s - %s (Severidad: %s)", 
				fechaCreacion, tipo, descripcion, nivelSeveridad);
		}
	}
}
