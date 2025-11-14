# 🔧 SOLUCIONES PASO A PASO

**Versión:** 2.1  
**Fecha:** 13 de noviembre de 2025  

---

## 📋 ÍNDICE DE SOLUCIONES

1. ✅ Solucionar problema CRÍTICO: Overridable method call
2. ✅ Implementar Logger en lugar de printStackTrace()
3. ✅ Completar mapUsuario() en UsuarioDAO
4. ✅ Renombrar paquete BaseDatos → basedatos (opcional)
5. ✅ Modernizar switch a rule switch (Java 14+)

---

## SOLUCIÓN #1: PROBLEMA CRÍTICO - Sellar Conexion

**Archivo:** `src/BaseDatos/Conexion.java`

### Cambio:
```diff
- public class Conexion implements DBConnection {
+ public final class Conexion implements DBConnection {
```

**Justificación:**
- Previene subclasificación accidental
- Garantiza que getInstance() siempre devuelve la misma clase
- Elimina riesgo de override de getConnectionString() en constructor

**Línea a cambiar:** Línea 20

---

## SOLUCIÓN #2: Implementar Logger

**Archivo:** `src/BaseDatos/Conexion.java`

### Paso 1: Agregar importes

```diff
  package BaseDatos;
  
  import java.sql.Connection;
  import java.sql.DriverManager;
  import java.sql.SQLException;
+ import java.util.logging.Logger;
+ import java.util.logging.Level;
```

### Paso 2: Agregar Logger como atributo de clase

```diff
  public class Conexion implements DBConnection {

	private static Conexion instance;
	private Connection connection;
+	private static final Logger LOGGER = Logger.getLogger(Conexion.class.getName());
	
	// Parámetros de conexión Oracle
```

### Paso 3: Reemplazar printStackTrace() en constructor

**Ubicación: Línea 47 (ClassNotFoundException)**
```diff
  } catch (ClassNotFoundException e) {
  	System.err.println("Error: Driver de Oracle no encontrado");
-	e.printStackTrace();
+	LOGGER.log(Level.SEVERE, "Error: Driver de Oracle no encontrado", e);
  	throw new RuntimeException("Error: ojdbc.jar no está en el classpath.", e);
  }
```

**Ubicación: Línea 51 (SQLException en constructor)**
```diff
  } catch (SQLException e) {
  	System.err.println("Error conectando a la base de datos");
-	e.printStackTrace();
+	LOGGER.log(Level.SEVERE, "Error conectando a la base de datos Oracle", e);
  	throw new RuntimeException("Error conectando a la base de datos: " + e.getMessage(), e);
  }
```

### Paso 4: Reemplazar printStackTrace() en closeConnection()

**Ubicación: Línea 114**
```diff
  } catch (SQLException e) {
  	System.err.println("Error al cerrar la conexión: " + e.getMessage());
-	e.printStackTrace();
+	LOGGER.log(Level.WARNING, "Error al cerrar la conexión", e);
  }
```

---

## SOLUCIÓN #3: Completar mapUsuario() en UsuarioDAO

**Archivo:** `src/DAO/UsuarioDAO.java`

**Ubicación: Línea 143**

### Cambio completo:
```diff
- private Usuario mapUsuario(ResultSet rs) throws SQLException {
-	// No implementado
-	return new Usuario();
- }

+ private Usuario mapUsuario(ResultSet rs) throws SQLException {
+	Usuario usuario = new Usuario();
+	usuario.setId(rs.getString("ID_USUARIO"));
+	usuario.setEmail(rs.getString("EMAIL"));
+	usuario.setRol(rs.getString("ROL"));
+	usuario.setNombre(rs.getString("NOMBRE"));
+	usuario.setApellido(rs.getString("APELLIDO"));
+	usuario.setTelefono(rs.getString("TELEFONO"));
+	usuario.setFechaRegistro(rs.getString("FECHA_REGISTRO"));
+	return usuario;
+ }
```

**Nota:** Asegúrate de que Usuario tiene estos setters implementados.

---

## SOLUCIÓN #4: Renombrar Paquete (Opcional pero Recomendado)

### Paso 1: Crear nuevo paquete
```
Click derecho en src/
New → Package
Nombre: basedatos
```

### Paso 2: Mover archivos
```
Mover o copiar:
- Conexion.java
- DBConnection.java
- DBConnectionFactory.java
- AdministradorConnection.java
- ProductorConnection.java
- AsistenteTecnicoConnection.java
- PropietarioConnection.java

De: src/BaseDatos/
A:  src/basedatos/
```

### Paso 3: Actualizar referencias en los archivos

**En Conexion.java:**
```diff
- package BaseDatos;
+ package basedatos;
```

**En DBConnection.java:**
```diff
- package BaseDatos;
+ package basedatos;
```

**En DBConnectionFactory.java:**
```diff
- package BaseDatos;
+ package basedatos;
```

**En todos los DAOs (cambiar imports):**
```diff
- import BaseDatos.DBConnection;
+ import basedatos.DBConnection;
```

### Paso 4: Eliminar carpeta antigua
```
Eliminar: src/BaseDatos/
(Si usa eclipse, hacer "Move" automatiza esto)
```

---

## SOLUCIÓN #5: Rule Switch (Opcional - Java 14+)

**Archivo:** `src/BaseDatos/DBConnectionFactory.java`

**Ubicación: Línea 30-42**

### Cambio:
```diff
  public static DBConnection getConnectionByRole(String role) {
      if (role == null || role.trim().isEmpty()) {
          throw new IllegalArgumentException("El rol no puede estar vacío");
      }

-     switch (role.toLowerCase().trim()) {
-     case "admin":
-         return AdministradorConnection.getInstance();
-     case "productor":
-         return ProductorConnection.getInstance();
-     case "asistente_tecnico":
-         return AsistenteTecnicoConnection.getInstance();
-     case "propietario":
-         return PropietarioConnection.getInstance();
-     default:
-         throw new IllegalArgumentException("Rol no válido: " + role + 
-             ". Roles válidos: admin, productor, asistente_tecnico, propietario");
-     }
+
+     return switch (role.toLowerCase().trim()) {
+         case "admin" -> AdministradorConnection.getInstance();
+         case "productor" -> ProductorConnection.getInstance();
+         case "asistente_tecnico" -> AsistenteTecnicoConnection.getInstance();
+         case "propietario" -> PropietarioConnection.getInstance();
+         default -> throw new IllegalArgumentException("Rol no válido: " + role + 
+             ". Roles válidos: admin, productor, asistente_tecnico, propietario");
+     };
  }
```

---

## SOLUCIÓN #6: Pattern Matching (Opcional - Java 16+)

**Archivo:** `src/DAO/DAOBase.java`

**Ubicación: Línea 242-254 (en setParameters())**

### Cambio:
```diff
  private void setParameters(PreparedStatement pstmt, Object[] params) throws SQLException {
      if (params == null) return;

      for (int i = 0; i < params.length; i++) {
          int index = i + 1; // JDBC usa índices de 1 a N
          Object param = params[i];

-         if (param instanceof String) {
+         if (param instanceof String s) {
-             pstmt.setString(index, (String) param);
-         } else if (param instanceof Integer) {
+             pstmt.setString(index, s);
+         } else if (param instanceof Integer i) {
-             pstmt.setInt(index, (Integer) param);
-         } else if (param instanceof Long) {
+             pstmt.setInt(index, i);
+         } else if (param instanceof Long l) {
-             pstmt.setLong(index, (Long) param);
-         } else if (param instanceof Double) {
+             pstmt.setLong(index, l);
+         } else if (param instanceof Double d) {
-             pstmt.setDouble(index, (Double) param);
-         } else if (param instanceof java.sql.Date) {
+             pstmt.setDouble(index, d);
+         } else if (param instanceof java.sql.Date sqlDate) {
-             pstmt.setDate(index, (java.sql.Date) param);
-         } else if (param instanceof java.sql.Timestamp) {
+             pstmt.setDate(index, sqlDate);
+         } else if (param instanceof java.sql.Timestamp ts) {
-             pstmt.setTimestamp(index, (java.sql.Timestamp) param);
-         } else if (param instanceof Boolean) {
+             pstmt.setTimestamp(index, ts);
+         } else if (param instanceof Boolean b) {
-             pstmt.setBoolean(index, (Boolean) param);
+             pstmt.setBoolean(index, b);
          } else {
              pstmt.setObject(index, param);
          }
      }
  }
```

---

## 🎯 ORDEN DE APLICACIÓN RECOMENDADO

### **FASE INMEDIATA (Hoy - 30 minutos)**
1. ✅ Solución #1: Sellar Conexion con `final`
2. ✅ Solución #2: Implementar Logger (reemplazar printStackTrace)
3. ✅ Solución #3: Completar mapUsuario()

### **FASE 1 (Próximos 30 minutos)**
4. ✅ Solución #4: Renombrar paquete BaseDatos → basedatos

### **FASE 2 (Si Java 14+)**
5. ✅ Solución #5: Modernizar switch a rule switch

### **FASE 3 (Si Java 16+)**
6. ✅ Solución #6: Pattern matching en DAOBase

---

## ✅ VALIDACIÓN DESPUÉS DE CAMBIOS

Después de aplicar cada solución:

```bash
# En Eclipse:
1. Project → Clean
2. Project → Build All
3. Verificar en Problems view
4. Ejecutar get_errors() para validar
```

---

## 📊 RESULTADO ESPERADO

Después de aplicar todas las soluciones INMEDIATAS:

```
ANTES:
├─ 1 CRÍTICO (Overridable call)
├─ 3 IMPORTANTES (Logger, mapUsuario, package)
├─ 9 WARNINGS (isinstance, etc)
└─ Total: 13 problemas

DESPUÉS (Solo soluciones inmediatas):
├─ 0 CRÍTICOS ✅
├─ 0 IMPORTANTES ✅
├─ 9 WARNINGS (si no aplicas Soluciones #5 y #6)
└─ Total: 9 problemas (reducido 69%)
```

---

**Documento de soluciones:** 13 de noviembre de 2025  
**Versión:** 2.1  
**Estado:** Listo para implementar
