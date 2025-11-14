package service.utilidad;

import BaseDatos.Conexion;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Servicio para generar reportes del sistema.
 * Exporta datos a formato CSV y genera reportes de inspecciones.
 */
public class ReportService {

	private static final Logger LOGGER = Logger.getLogger(ReportService.class.getName());
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");

	public String generarReporteInspecciones(long idLote, String rutaDestino) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String nombreArchivo = "reporte_inspecciones_" + dateFormat.format(new Date()) + ".csv";
		String rutaCompleta = rutaDestino + "\\" + nombreArchivo;
		
		try {
			conn = Conexion.getInstance().getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT i.id_inspeccion, i.fecha, p.nombre_plaga, " +
				"i.plantas_afectadas, i.plantas_muestreadas " +
				"FROM inspeccion_fitosanitaria i " +
				"INNER JOIN plaga p ON i.id_plaga = p.id_plaga " +
				"WHERE i.id_lote = " + idLote + " ORDER BY i.fecha DESC";
			
			rs = stmt.executeQuery(sql);
			
			try (FileWriter writer = new FileWriter(rutaCompleta)) {
				writer.write("ID,Fecha,Plaga,Plantas Afectadas,Plantas Muestreadas,Indice %\n");
				
				while (rs.next()) {
					int afectadas = rs.getInt("plantas_afectadas");
					int muestreadas = rs.getInt("plantas_muestreadas");
					double indice = (double) afectadas / muestreadas * 100.0;
					
					writer.write(String.format("%d,%s,%s,%d,%d,%.2f\n",
						rs.getLong("id_inspeccion"),
						rs.getDate("fecha"),
						rs.getString("nombre_plaga"),
						afectadas,
						muestreadas,
						indice
					));
				}
			}
			
			LOGGER.info("Reporte generado: " + rutaCompleta);
			return rutaCompleta;
			
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Error generando reporte", e);
			return null;
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "Error cerrando recursos", e);
			}
		}
	}

	public String generarReporteCultivos(String rutaDestino) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String nombreArchivo = "reporte_cultivos_" + dateFormat.format(new Date()) + ".csv";
		String rutaCompleta = rutaDestino + "\\" + nombreArchivo;
		
		try {
			conn = Conexion.getInstance().getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT c.id_cultivo, c.nombre_cultivo, c.variedad, " +
				"SUM(l.area) as area_total FROM cultivo c " +
				"INNER JOIN lote l ON c.id_lote = l.id_lote " +
				"GROUP BY c.id_cultivo, c.nombre_cultivo, c.variedad";
			
			rs = stmt.executeQuery(sql);
			
			try (FileWriter writer = new FileWriter(rutaCompleta)) {
				writer.write("ID,Cultivo,Variedad,Area Total (ha)\n");
				
				while (rs.next()) {
					writer.write(String.format("%d,%s,%s,%.2f\n",
						rs.getLong("id_cultivo"),
						rs.getString("nombre_cultivo"),
						rs.getString("variedad"),
						rs.getDouble("area_total")
					));
				}
			}
			
			LOGGER.info("Reporte generado: " + rutaCompleta);
			return rutaCompleta;
			
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Error generando reporte", e);
			return null;
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "Error cerrando recursos", e);
			}
		}
	}
}
