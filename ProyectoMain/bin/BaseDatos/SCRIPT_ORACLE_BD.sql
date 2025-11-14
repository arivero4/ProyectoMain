-- =====================================================================
-- SISTEMA DE INSPECCIONES FITOSANITARIAS - SCRIPT ORACLE
-- VERSION 2.1
-- AUTORES: Isabella Vargas, Ricardo Viancha, Iswar Corrales, Andres Rivero
-- =====================================================================

-- Eliminación de tablas existentes (en orden inverso de dependencias)
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE RESULTADO_TECNICO';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE LOTE';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE PLAGA_CULTIVO';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE CULTIVO';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE PLAGA';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE INSPECCION_FITOSANITARIA';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE PREDIO';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE LUGAR_PRODUCCION';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE VEREDA';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE MUNICIPIO';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE DEPARTAMENTO';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE ASISTENTE_TECNICO';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE PRODUCTOR';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE PROPIETARIO';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/



-- =====================================================================
-- TABLA: DEPARTAMENTO
-- =====================================================================
CREATE TABLE DEPARTAMENTO
(
    id VARCHAR(10) CONSTRAINT dep_id_pk PRIMARY KEY,
    nombre VARCHAR(50) CONSTRAINT dep_nom_nn NOT NULL,
    codigo_dane VARCHAR(10) CONSTRAINT dep_cod_nn NOT NULL
);

-- =====================================================================
-- TABLA: MUNICIPIO
-- =====================================================================
CREATE TABLE MUNICIPIO
(
    id VARCHAR(10) CONSTRAINT mun_id_pk PRIMARY KEY,
    nombre VARCHAR(50) CONSTRAINT mun_nom_nn NOT NULL,
    codigo_dane VARCHAR(10) CONSTRAINT mun_cod_nn NOT NULL,
    id_departamento VARCHAR(10) CONSTRAINT mun_idmun_nn NOT NULL
    CONSTRAINT mun_iddpto_fk REFERENCES DEPARTAMENTO(id)
);

-- =====================================================================
-- TABLA: VEREDA
-- =====================================================================
CREATE TABLE VEREDA
(
    id VARCHAR(10) CONSTRAINT ver_id_pk PRIMARY KEY,
    nombre VARCHAR(50) CONSTRAINT ver_nom_nn NOT NULL,
    codigo_dane VARCHAR(10) CONSTRAINT ver_cod_nn NOT NULL,
    id_municipio VARCHAR(10) CONSTRAINT ver_idmun_nn NOT NULL
    CONSTRAINT ver_idmun_fk REFERENCES MUNICIPIO(id)
);

-- =====================================================================
-- TABLA: PROPIETARIO
-- =====================================================================
CREATE TABLE PROPIETARIO
(
    id VARCHAR(10) CONSTRAINT prop_id_pk PRIMARY KEY,
    numero_identificacion VARCHAR(20) CONSTRAINT prop_numid_nn NOT NULL,
    rol VARCHAR(20) CONSTRAINT prop_rol_nn NOT NULL,
    nombre VARCHAR(50) CONSTRAINT prop_nom_nn NOT NULL,
    telefono_contacto VARCHAR(20) CONSTRAINT prop_tel_nn NOT NULL,
    correo_electronico VARCHAR(50) CONSTRAINT prop_cor_nn NOT NULL
);

-- =====================================================================
-- TABLA: PRODUCTOR
-- =====================================================================
CREATE TABLE PRODUCTOR
(
    id VARCHAR(10) CONSTRAINT prod_id_pk PRIMARY KEY,
    numero_identificacion VARCHAR(20) CONSTRAINT prod_numid_nn NOT NULL,
    rol VARCHAR(20) CONSTRAINT prod_rol_nn NOT NULL,
    nombre VARCHAR(50) CONSTRAINT prod_nom_nn NOT NULL,
    telefono_contacto VARCHAR(20) CONSTRAINT prod_tel_nn NOT NULL,
    correo_electronico VARCHAR(50) CONSTRAINT prod_cor_nn NOT NULL
);

-- =====================================================================
-- TABLA: ASISTENTE_TECNICO
-- =====================================================================
CREATE TABLE ASISTENTE_TECNICO
(
    id VARCHAR(10) CONSTRAINT at_id_pk PRIMARY KEY,
    numero_identificacion VARCHAR(20) CONSTRAINT at_numid_nn NOT NULL,
    rol VARCHAR(20) CONSTRAINT at_rol_nn NOT NULL,
    nombre VARCHAR(50) CONSTRAINT at_nom_nn NOT NULL,
    telefono_contacto VARCHAR(20) CONSTRAINT at_tel_nn NOT NULL,
    correo_electronico VARCHAR(50) CONSTRAINT at_cor_nn NOT NULL,
    numero_tarjeta_profesional VARCHAR(15) CONSTRAINT at_ntp_nn NOT NULL
);

-- =====================================================================
-- TABLA: CULTIVO
-- =====================================================================
CREATE TABLE CULTIVO
(
    id VARCHAR(10) CONSTRAINT cul_id_pk PRIMARY KEY,
    nombre_variedad VARCHAR(50) CONSTRAINT cul_nomva_nn NOT NULL,
    nombre_cientifico VARCHAR(50) CONSTRAINT cul_nomcie_nn NOT NULL,
    nombre_comun VARCHAR(50) CONSTRAINT cul_nomcom_nn NOT NULL,
    descripcion VARCHAR(200) CONSTRAINT cul_desc_nn NOT NULL
);

-- =====================================================================
-- TABLA: PLAGA
-- =====================================================================
CREATE TABLE PLAGA
(
    id VARCHAR(10) CONSTRAINT pla_id_pk PRIMARY KEY,
    nombre_cientifico VARCHAR(50) CONSTRAINT pla_nomcie_nn NOT NULL,
    nombre_comun VARCHAR(50) CONSTRAINT pla_nomcom_nn NOT NULL,
    descripcion VARCHAR(200) CONSTRAINT pla_desc_nn NOT NULL
);

-- =====================================================================
-- TABLA: LUGAR_PRODUCCION
-- =====================================================================
CREATE TABLE LUGAR_PRODUCCION
(
    id VARCHAR(10) CONSTRAINT lp_id_pk PRIMARY KEY,
    codigo_ica VARCHAR(10) CONSTRAINT lp_cod_uk UNIQUE,
    nombre VARCHAR(150) CONSTRAINT lp_nom_nn NOT NULL,
    id_productor VARCHAR(10) CONSTRAINT lp_idprod_nn NOT NULL
        CONSTRAINT lp_idprod_fk REFERENCES PRODUCTOR(id),
    id_asistente_tecnico VARCHAR(10)
        CONSTRAINT lp_idas_fk REFERENCES ASISTENTE_TECNICO(id)
);

-- =====================================================================
-- TABLA: INSPECCION_FITOSANITARIA
-- =====================================================================
CREATE TABLE INSPECCION_FITOSANITARIA
(
    id VARCHAR(20) CONSTRAINT fito_id_pk PRIMARY KEY,
    fecha_inspeccion DATE CONSTRAINT fito_fch_nn NOT NULL,
    id_asistente_tecnico VARCHAR(10) CONSTRAINT fito_idas_nn NOT NULL
    CONSTRAINT fito_idas_fk REFERENCES ASISTENTE_TECNICO(id),
    id_lugar VARCHAR(10) CONSTRAINT fito_lugar_fk REFERENCES LUGAR_PRODUCCION(id)
);

-- =====================================================================
-- TABLA: PREDIO
-- =====================================================================
CREATE TABLE PREDIO
(
    id VARCHAR(20) CONSTRAINT pre_id_pk PRIMARY KEY,
    numero_predial VARCHAR(30) CONSTRAINT pre_numpri_nn NOT NULL,
    direccion VARCHAR(50) CONSTRAINT pre_dir_nn NOT NULL,
    coordenada_lat NUMBER(9,6) CONSTRAINT pre_lat_nn NOT NULL,
    coordenada_long NUMBER(9,6) CONSTRAINT pre_long_nn NOT NULL,
    area NUMBER(10, 2) CONSTRAINT pre_are_nn NOT NULL,
    id_propietario VARCHAR(10) CONSTRAINT pre_idprop_nn NOT NULL
    CONSTRAINT pre_idprop_fk REFERENCES PROPIETARIO(id),
    id_vereda VARCHAR(10) CONSTRAINT pre_idver_nn NOT NULL
    CONSTRAINT pre_idver_fk REFERENCES VEREDA(id),
    cod_lugar_produccion VARCHAR(10)
    CONSTRAINT pre_lp_fk REFERENCES LUGAR_PRODUCCION(id)
);

-- =====================================================================
-- TABLA: PLAGA_CULTIVO (Relación M:M)
-- =====================================================================
CREATE TABLE PLAGA_CULTIVO
(
    id VARCHAR(20) CONSTRAINT plcul_id_pk PRIMARY KEY,
    id_cultivo VARCHAR(10) CONSTRAINT plcul_cul_nn NOT NULL
    CONSTRAINT plcul_cul_fk REFERENCES CULTIVO(id),
    id_plaga VARCHAR(10)
    CONSTRAINT plcul_pla_fk REFERENCES PLAGA(id)
);

-- =====================================================================
-- TABLA: LOTE
-- =====================================================================
CREATE TABLE LOTE
(
    id VARCHAR(20) CONSTRAINT lot_id_pk PRIMARY KEY,
    area NUMBER(10, 2) CONSTRAINT lot_are_nn NOT NULL,
    fecha_siembra DATE ,
    fecha_eliminacion DATE,
    id_lugar VARCHAR(10) CONSTRAINT lot_lp_nn NOT NULL
    CONSTRAINT lot_lp_fk REFERENCES LUGAR_PRODUCCION(id),
    id_inspeccion VARCHAR(20)
    CONSTRAINT lot_ins_fk REFERENCES INSPECCION_FITOSANITARIA(id)
);

-- =====================================================================
-- TABLA: CULTIVO_LOTE (Relación entre CULTIVO y LOTE)
-- =====================================================================
CREATE TABLE CULTIVO_LOTE
(
    id_cultivo VARCHAR(10) CONSTRAINT cullot_cul_nn NOT NULL
    CONSTRAINT cullot_cul_fk REFERENCES CULTIVO(id),
    id_lote VARCHAR(20) CONSTRAINT cullot_lot_nn NOT NULL
    CONSTRAINT cullot_lot_fk REFERENCES LOTE(id),
    CONSTRAINT cullot_pk PRIMARY KEY (id_cultivo, id_lote)
);

-- =====================================================================
-- TABLA: RESULTADO_TECNICO
-- =====================================================================
CREATE TABLE RESULTADO_TECNICO
(
    id VARCHAR(20) CONSTRAINT rt_id_pk PRIMARY KEY,
    plantas_evaluadas NUMBER(10,0) CONSTRAINT rt_pe_nn NOT NULL,
    plantas_afectadas NUMBER(10,0) CONSTRAINT rt_pa_nn NOT NULL,
    observaciones VARCHAR(200),
    id_inspeccion VARCHAR(20) CONSTRAINT rt_codins_nn NOT NULL
    CONSTRAINT rt_codins_fk REFERENCES INSPECCION_FITOSANITARIA(id),
    id_cultivo VARCHAR(10)
    CONSTRAINT rt_cul_fk REFERENCES CULTIVO(id),
    id_plaga VARCHAR(10)
    CONSTRAINT rt_pla_fk REFERENCES PLAGA(id)
);

-- =====================================================================
-- ÍNDICES PARA OPTIMIZACIÓN DE CONSULTAS
-- =====================================================================

-- Índices para DEPARTAMENTO
CREATE INDEX idx_departamento_codigo ON DEPARTAMENTO(codigo_dane);

-- Índices para MUNICIPIO
CREATE INDEX idx_municipio_codigo ON MUNICIPIO(codigo_dane);
CREATE INDEX idx_municipio_departamento ON MUNICIPIO(id_departamento);

-- Índices para VEREDA
CREATE INDEX idx_vereda_codigo ON VEREDA(codigo_dane);
CREATE INDEX idx_vereda_municipio ON VEREDA(id_municipio);

-- Índices para PROPIETARIO
CREATE INDEX idx_propietario_id ON PROPIETARIO(numero_identificacion);
CREATE INDEX idx_propietario_correo ON PROPIETARIO(correo_electronico);

-- Índices para PRODUCTOR
CREATE INDEX idx_productor_id ON PRODUCTOR(numero_identificacion);
CREATE INDEX idx_productor_correo ON PRODUCTOR(correo_electronico);

-- Índices para ASISTENTE_TECNICO
CREATE INDEX idx_asistente_id ON ASISTENTE_TECNICO(numero_identificacion);
CREATE INDEX idx_asistente_tarjeta ON ASISTENTE_TECNICO(numero_tarjeta_profesional);

-- Índices para LUGAR_PRODUCCION
CREATE INDEX idx_lugar_codigo_ica ON LUGAR_PRODUCCION(codigo_ica);
CREATE INDEX idx_lugar_productor ON LUGAR_PRODUCCION(id_productor);
CREATE INDEX idx_lugar_asistente ON LUGAR_PRODUCCION(id_asistente_tecnico);

-- Índices para INSPECCION_FITOSANITARIA
CREATE INDEX idx_inspeccion_codigo ON INSPECCION_FITOSANITARIA(codigo_ica);
CREATE INDEX idx_inspeccion_asistente ON INSPECCION_FITOSANITARIA(id_asistente_tecnico);
CREATE INDEX idx_inspeccion_fecha ON INSPECCION_FITOSANITARIA(fecha_inspeccion);

-- Índices para PREDIO
CREATE INDEX idx_predio_codigo ON PREDIO(codigo_ica);
CREATE INDEX idx_predio_propietario ON PREDIO(id_propietario);
CREATE INDEX idx_predio_vereda ON PREDIO(id_vereda);

-- Índices para LOTE
CREATE INDEX idx_lote_id ON LOTE(id);
CREATE INDEX idx_lote_lugar ON LOTE(cod_ica_lp);
CREATE INDEX idx_lote_fecha_siembra ON LOTE(fecha_siembra);

-- Índices para CULTIVO_LOTE
CREATE INDEX idx_cultivo_lote ON CULTIVO_LOTE(id_lote);

-- Índices para RESULTADO_TECNICO
CREATE INDEX idx_resultado_id ON RESULTADO_TECNICO(id);
CREATE INDEX idx_resultado_inspeccion ON RESULTADO_TECNICO(codigo_ica_inspeccion);
CREATE INDEX idx_resultado_cultivo ON RESULTADO_TECNICO(id_cultivo);
CREATE INDEX idx_resultado_plaga ON RESULTADO_TECNICO(id_plaga);

-- =====================================================================
-- SECUENCIAS PARA GENERACIÓN AUTOMÁTICA DE IDs (Opcional)
-- =====================================================================

CREATE SEQUENCE seq_departamento_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_municipio_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_vereda_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_propietario_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_productor_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_asistente_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_cultivo_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_plaga_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_lugar_produccion_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_inspeccion_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_predio_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_lote_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_resultado_id START WITH 1 INCREMENT BY 1;

-- =====================================================================
-- COMMIT Y FINALIZACIÓN
-- =====================================================================
COMMIT;

-- =====================================================================
-- DESCRIPCIÓN DE TABLAS Y RELACIONES
-- =====================================================================

/*
TABLA DEPARTAMENTO:
- id (VARCHAR 10) - PK - Identificador único del departamento
- nombre (VARCHAR 50) - NOT NULL - Nombre del departamento
- codigo_dane (VARCHAR 10) - Código DANE del departamento

TABLA MUNICIPIO:
- id (VARCHAR 10) - PK - Identificador único del municipio
- nombre (VARCHAR 50) - NOT NULL - Nombre del municipio
- codigo_dane (VARCHAR 10) - Código DANE del municipio
- id_departamento (VARCHAR 10) - NOT NULL - FK a DEPARTAMENTO

TABLA VEREDA:
- id (VARCHAR 10) - PK - Identificador único de la vereda
- nombre (VARCHAR 50) - NOT NULL - Nombre de la vereda
- codigo_dane (VARCHAR 10) - Código DANE de la vereda
- id_municipio (VARCHAR 10) - NOT NULL - FK a MUNICIPIO

TABLA PROPIETARIO:
- id (VARCHAR 10) - PK - Identificador único del propietario
- numero_identificacion (VARCHAR 20) - NOT NULL - Cédula o número de identificación
- rol (VARCHAR 20) - NOT NULL - Rol del usuario (propietario)
- nombre (VARCHAR 50) - NOT NULL - Nombre completo
- telefono_contacto (VARCHAR 20) - Teléfono de contacto
- correo_electronico (VARCHAR 50) - NOT NULL - Correo electrónico único

TABLA PRODUCTOR:
- id (VARCHAR 10) - PK - Identificador único del productor
- numero_identificacion (VARCHAR 20) - NOT NULL - Cédula o número de identificación
- rol (VARCHAR 20) - NOT NULL - Rol del usuario (productor)
- nombre (VARCHAR 50) - NOT NULL - Nombre completo
- telefono_contacto (VARCHAR 20) - Teléfono de contacto
- correo_electronico (VARCHAR 50) - NOT NULL - Correo electrónico único

TABLA ASISTENTE_TECNICO:
- id (VARCHAR 10) - PK - Identificador único del asistente técnico
- numero_identificacion (VARCHAR 20) - NOT NULL - Cédula o número de identificación
- rol (VARCHAR 20) - NOT NULL - Rol del usuario (asistente técnico)
- nombre (VARCHAR 50) - NOT NULL - Nombre completo
- telefono_contacto (VARCHAR 20) - Teléfono de contacto
- correo_electronico (VARCHAR 50) - NOT NULL - Correo electrónico único
- numero_tarjeta_profesional (VARCHAR 15) - NOT NULL - Tarjeta profesional del técnico

TABLA CULTIVO:
- id (VARCHAR 10) - PK - Identificador único del cultivo
- nombre_variedad (VARCHAR 50) - NOT NULL - Nombre de la variedad cultivada
- nombre_cientifico (VARCHAR 50) - NOT NULL - Nombre científico de la planta
- nombre_comun (VARCHAR 50) - NOT NULL - Nombre común de la planta
- descripcion (VARCHAR 200) - NOT NULL - Descripción del cultivo

TABLA PLAGA:
- id (VARCHAR 10) - PK - Identificador único de la plaga
- nombre_cientifico (VARCHAR 50) - NOT NULL - Nombre científico de la plaga
- nombre_comun (VARCHAR 50) - NOT NULL - Nombre común de la plaga
- descripcion (VARCHAR 200) - NOT NULL - Descripción de la plaga

TABLA LUGAR_PRODUCCION:
- codigo_ica (VARCHAR 10) - PK - Código ICA único del lugar de producción
- nombre (VARCHAR 150) - Nombre del lugar de producción
- id_productor (VARCHAR 10) - NOT NULL - FK a PRODUCTOR
- id_asistente_tecnico (VARCHAR 10) - FK a ASISTENTE_TECNICO (opcional)

TABLA INSPECCION_FITOSANITARIA:
- codigo_ica (VARCHAR 20) - PK - Código único de la inspección
- fecha_inspeccion (DATE) - NOT NULL - Fecha en que se realizó la inspección
- id_asistente_tecnico (VARCHAR 10) - NOT NULL - FK a ASISTENTE_TECNICO
- codigo_ica_lugar (VARCHAR 10) - FK a LUGAR_PRODUCCION

TABLA PREDIO:
- codigo_ica (VARCHAR 20) - PK - Código único del predio
- numero_predial (VARCHAR 30) - Número predial catastral
- direccion (VARCHAR 50) - NOT NULL - Dirección del predio
- coordenada_lat (NUMBER 9,6) - NOT NULL - Latitud del predio
- coordenada_long (NUMBER 9,6) - NOT NULL - Longitud del predio
- area (NUMBER 10,2) - NOT NULL - Área del predio en hectáreas
- id_propietario (VARCHAR 10) - NOT NULL - FK a PROPIETARIO
- id_vereda (VARCHAR 10) - NOT NULL - FK a VEREDA
- cod_lugar_produccion (VARCHAR 10) - FK a LUGAR_PRODUCCION

TABLA PLAGA_CULTIVO:
- id (VARCHAR 20) - PK - Identificador único de la relación
- id_cultivo (VARCHAR 10) - NOT NULL - FK a CULTIVO
- id_plaga (VARCHAR 10) - FK a PLAGA

TABLA LOTE:
- id (VARCHAR 20) - PK - Identificador único del lote
- area (NUMBER 10,2) - NOT NULL - Área del lote
- fecha_siembra (DATE) - Fecha de siembra
- fecha_eliminacion (DATE) - Fecha de eliminación del cultivo
- cod_ica_lp (VARCHAR 10) - NOT NULL - FK a LUGAR_PRODUCCION
- cod_ica_inspeccion (VARCHAR 20) - FK a INSPECCION_FITOSANITARIA

TABLA CULTIVO_LOTE:
- id_cultivo (VARCHAR 10) - NOT NULL - FK a CULTIVO (PK compuesta)
- id_lote (VARCHAR 20) - NOT NULL - FK a LOTE (PK compuesta)
Permite la relación M:M entre CULTIVO y LOTE

TABLA RESULTADO_TECNICO:
- id (VARCHAR 20) - PK - Identificador único del resultado
- plantas_evaluadas (NUMBER 10,0) - NOT NULL - Total de plantas evaluadas
- plantas_afectadas (NUMBER 10,0) - NOT NULL - Plantas afectadas por plagas
- observaciones (VARCHAR 200) - NOT NULL - Observaciones técnicas
- codigo_ica_inspeccion (VARCHAR 20) - NOT NULL - FK a INSPECCION_FITOSANITARIA
- id_cultivo (VARCHAR 10) - FK a CULTIVO
- id_plaga (VARCHAR 10) - FK a PLAGA
*/

-- =====================================================================
-- FIN DEL SCRIPT
-- =====================================================================
