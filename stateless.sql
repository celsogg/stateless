CREATE DATABASE  IF NOT EXISTS `stateless` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `stateless`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: stateless
-- ------------------------------------------------------
-- Server version	5.6.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `ID_ADMIN` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ADMIN` varchar(32) DEFAULT NULL,
  `PASSWORD_ADMIN` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`ID_ADMIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asignatura`
--

DROP TABLE IF EXISTS `asignatura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asignatura` (
  `ID_ASIGNATURA` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PLAN` int(11) DEFAULT NULL,
  `NOMBRE_ASIGNATURA` varchar(128) DEFAULT NULL,
  `SCT_ASIGNATURA` int(11) DEFAULT NULL,
  `NIVEL_ASIGNATURA` int(11) DEFAULT NULL,
  `CODIGO_ASIGNATURA` varchar(16) DEFAULT NULL,
  `HORAS_TEORIA` int(11) DEFAULT NULL,
  `HORAS_EJERCICIO` int(11) DEFAULT NULL,
  `HORAS_LABORATORIO` int(11) DEFAULT NULL,
  `RESUMEN_ASIGNATURA` varchar(1024) DEFAULT NULL,
  `ES_ANUAL` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`ID_ASIGNATURA`),
  KEY `FK_RELATIONSHIP_4` (`ID_PLAN`),
  CONSTRAINT `FK_RELATIONSHIP_4` FOREIGN KEY (`ID_PLAN`) REFERENCES `plan` (`ID_PLAN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1404 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignatura`
--

LOCK TABLES `asignatura` WRITE;
/*!40000 ALTER TABLE `asignatura` DISABLE KEYS */;
INSERT INTO `asignatura` VALUES (3,2,'CÁLCULO I PARA INGENIERÍA',7,1,'10101',6,2,0,'Asignatura teórica-práctica de iniciación al Cálculo para funciones reales de una variable real. Previamente presenta la estructura del conjunto fundamental R de los números reales como cuerpo ordenado completo, y luego el plano real cartesiano. Esta asignatura desarrolla los conceptos, propiedades y aplicaciones de los conceptos básicos del cálculo: límites, continuidad y derivada, mostrando recurrentemente aspectos de geometría analítica en el plano real.',0),(4,2,'ÁLGEBRA I PARA INGENIERÍA',7,1,'10102',6,2,0,'Asignatura teórico-práctica  que tiene como propósito describir los aspectos fundamentales de la matemática, especialmente en relación a las propiedades y aplicaciones de los diferentes tipos usuales de funciones y al estudio de ecuaciones algebraicas',0),(5,2,'FISICA I PARA INGENIERÍA',7,1,'10103',4,2,1,'En esta asignatura se presenta, a nivel fundamental, los conceptos básicos de la física en las temáticas de mecánica, ondas, fluidos y termodinámica.',0),(6,2,'TALLER DE DESARROLLO PERSONAL E INTERGRAL',3,1,'10104',2,0,2,'Asignatura orientada a la reflexión personal desde herramientas de la psicología, con la finalidad de posibilitar el desarrollo de habilidades de autoconciencia en los estudiantes, que permitan generar un proceso de adaptación a la vida universitaria, utilizando como recurso personal  la capacidad de observar sus procesos internos, conocer características de su  personalidad y el estilo de relacionarse consigo mismos, con lo otros y con el mundo exterior.\nDicho proceso se genera tanto  a nivel personal como en la relación y vinculación con los otros, lo cual abre la posibilidad de generar una vinculación significativa con los demás en este proceso de adaptación a una nueva etapa de la vida.',0),(7,2,'INTRODUCCIÓN A LA INGENIERÍA',2,1,'10125',0,0,2,'Asignatura teórico-práctica  que tiene como propósito describir los aspectos fundamentales de la Ingeniería, el ingeniero y su papel en el entorno laboral, social y medio ambiental.',0),(8,2,'MÉTODOS DE ESTUDIO',2,1,'10126',0,0,2,'Asignatura teórica-taller que tiene como propósito que los alumnos aprendan y comprendan los factores que explican el buen estudio, incluyendo la planificación de las actividades, el control del proceso intelectual y la evaluación de los resultados, a través de herramientas y metodologías de aprendizaje en el contexto de problemas matemáticos.',0),(9,2,'CALCULO II PARA INGENIERÍA',7,2,'10107',6,2,0,'Introduce el estudio del cálculo integral incluida la integral impropia. El  estudio de las series numéricas, las series de potencias, curvas en el espacio y diferenciación de funciones reales de varias variables.\nEntrega los elementos  del cálculo integral de funciones reales de una variable real y su aplicación a problemas de la ingeniería.\nEn su última parte, el curso entrega elementos de series de funciones, dando las bases para su aplicación al estudio de las series de Fourier. ',0),(10,2,'ALGEBRA II PARA INGENIERÍA',6,2,'10108',4,2,0,'Asignatura teórica-práctica que completa el desarrollo de los aspectos fundamentales del Álgebra, centrándose en los conceptos y resultados sobre vectores y la potente generalización a espacios vectoriales, la asociación con transformaciones (aplicaciones) lineales, y la incorporación de los productos interiores y los conceptos de valores propios y vectores propios.',0),(11,2,'FÍSICA II PARA INGENIERÍA',7,2,'10109',4,2,1,'Esta asignatura se presenta a nivel fundamental los conceptos básicos de la física en las temáticas de electricidad y magnetismo.',0),(12,2,'FUNDAMENTOS DE COMPUTACIÓN Y PROGRAMACIÓN',5,2,'10110',4,0,2,'Este curso tiene como objetivo acercar al estudiante de ingeniería a los computadores como herramientas de cómputo programables y versátiles, y como poderosas herramientas para la búsqueda y el manejo de información; se consideran también tanto la gran variedad de dispositivos accesorios existentes como la conectividad y accesibilidad a grandes fuentes de información. A continuación se enfrenta al alumno al problema de construir soluciones computacionales a problemas relativamente sencillos, proporcionándoles las herramientas básicas necesarias para poder construir programas computacionales. Finalmente, se muestra la utilidad de estas máquinas en la resolución de problemas numéricos con ayuda de software especializado de uso frecuente en ingeniería. ',0),(13,2,'QUIMICA GENERAL',5,2,'10111',4,2,0,'Asignatura teórico-práctica  que tiene como propósito entregar a los estudiantes de ingeniería los conocimientos básicos de química, que les permitan conocer como se compone la materia y sus propiedades fundamentales, para comprender los fenómenos químicos que se presentan en la naturaleza y que están relacionados con procesos productivos.',0),(14,2,'ELECTRICIDAD Y MAGNETISMO PARA INGENIERÍA',7,3,'10127',4,2,1,'En esta asignatura se presenta la rama de la física que concierne a los fenómenos eléctricos y magnéticos ya que las leyes de electricidad y magnetismo desempeñan un papel fundamental en la comprensión del funcionamiento de varios dispositivos. Por tanto la asignatura presenta inicialmente los fenómenos eléctricos y sus leyes fundamentales, para posteriormente continuar con los fenómenos magnéticos y sus leyes, haciendo énfasis en los conceptos fundamentales y aplicaciones. ',0),(15,2,'COMUNICACIÓN EFECTIVA',2,3,'10128',0,0,2,NULL,0),(16,2,'INGLÉS I',3,3,'10130',0,0,2,'Asignatura práctica que tiene como objetivo el desarrollo de habilidades lingüísticas y comunicativas en Inglés como lengua extranjera en lectura, escritura, audición y expresión oral, de acuerdo al nivel A1 (ALTE Breakthrough) según el Marco Común Europeo de Referencia para las Lenguas: Aprendizaje, Enseñanza y Evaluación.',0),(17,2,'ECUACIONES DIFERENCIALES PARA INGENIERÍA',6,3,'10122',4,2,0,NULL,0),(18,2,'MÉTODOS DE PROGRAMACIÓN',8,3,'13201',4,2,2,'Asignatura teórico-práctica que tiene como propósito introducir al estudiante de\nIngeniería Informática a la resolución de problemas de manejo de datos, entregando\nherramientas para modelarlos, representar y programar una solución apropiada,\ndesarrollada en base al paradigma de programación orientada a objetos (POO).',0),(19,2,'FUNDAMENTOS DE ECONOMÍA',5,3,'10116',4,2,0,'Asignatura teórico-práctica  que tiene como propósito describir los aspectos fundamentales de la Economía, y su aplicación en la Ingeniería, en sintonía con el entorno laboral, social y medio ambiental.',0),(20,2,'CALCULO III PARA INGENIERIA',6,4,'10129',4,2,0,NULL,0),(21,2,'ANÁLISIS ESTADÍSTICO PARA INGENIERÍA',5,4,'10115',4,2,0,'Curso introductorio que permite conocer y aplicar metodología estadística en el análisis de información, para apoyar al ingeniero  en la toma de decisiones utilizando probabilidades, cuando existe incertidumbre y variación',0),(22,2,'ESTRUCTURA DE COMPUTADORES ',6,4,'13278',4,0,2,'Asignatura teórico-práctica que introduce los elementos básicos y fundamentales sobre los cuales se construye el fenómeno de la computación. \nConceptos de representación y manejo de la información en sistemas digitales a nivel avanzado.\nElementos básicos que componen el hardware de un computador a nivel medio.\nModelo básico de computación de Von Neumann.',0),(23,2,'PARADIGMAS DE PROGRAMACIÓN',6,4,'13204',4,0,2,'Asignatura teórico-práctica que tiene como propósito formalizar las características de los\nparadigmas de programación que el estudiante ya conoce (imperativa-procedural y\norientada a objetos) y capacitarlo para enfrentar el desarrollo de software con otros tipos\nde paradigmas y orientaciones.',0),(24,2,'ANÁLISIS DE ALGORITMOS Y ESTRUCTURA DE DATOS',5,4,'13205',4,0,2,'Asignatura teórico-práctica que tiene como propósito resolver problemas usando\nalgoritmos y estructuras de datos adecuados a la situación. En particular, se espera que el\nalumno aprenda a aplicar los conceptos de tiempo de ejecución y complejidad algorítmica;\ndiseñar, implementar y modificar estructuras de datos de acuerdo a las necesidades del\nproblema; diseñar algoritmos elementales, iterativos y recursivos; recomendar de manera\nfundada una estructura de datos para un problema dado. Los conceptos vistos en cátedra\nse refuerzan con actividades de laboratorio, donde deberán implementar diversas\nestructuras de datos para problemas específicos. ',0),(25,2,'INGLÉS II',3,4,'10131',0,0,2,NULL,0),(26,2,'INGENIERÍA DE SISTEMAS',5,5,'13279',4,2,0,'Asignatura teórico-práctica que tiene como propósito introducir el pensamiento sistémico y crítico en el estudiante para que pueda realizar diseño de procesos de trabajo en organizaciones humanas. Además, el estudiante aprenderá a utilizar herramientas de ingeniería de sistemas para la modelización de problemas complejos y de procesos de trabajo. Con ellas, el estudiante  podrá dimensionar sistemáticamente los impactos de los resultados a los cuales llega con la abstracción. En el sentido de las habilidades, este curso hace que los estudiantes trabajen en equipo con proyectos acotados. En esos proyectos el estudiante usará técnicas y herramientas ayudan a mitigar los problemas derivados de la aplicación excesiva de los enfoques analítico-reduccionistas ampliamente utilizados en ingeniería. De esa forma, el curso pretende aportar en la dirección de la responsabilidad social y conciencia crítica de los estudiantes en el ejercicio de su profesión.',0),(27,2,'DISEÑO DE BASES DE DATOS',5,5,'13280',4,1,1,'La asignatura de base de datos proporciona conocimiento teórico y práctico en la aplicación de modelos de bases de datos relacionales, durante las etapas de diseño, administración, investigación de herramientas y gestión de bases de datos.',0),(28,2,'ORGANIZACIÓN DE COMPUTADORES',6,5,'13281',4,1,1,'Asignatura teórico-práctica que introduce los principios de la organización física y lógica de los computadores, exponiendo la capa bajo el nivel de programación de la aplicación y sistema operativo, y sobre los sistemas digitales. Los tópicos incluyen organización del procesador, la memoria, dispositivos de entrada/salida, buses y arquitecturas paralelas. ',0),(29,2,'FUNDAMENTOS DE INGENIERÍA DE SOFTWARE',5,5,'13209',4,2,0,'Asignatura teórica de carácter introductoria cuyo propósito es que el estudiante conozca los conceptos esenciales y consolidados de la Ingeniería de Software y la importancia de esta disciplina. Se proporciona un marco para comprender materias que son estudiadas en cursos más avanzados del área. Para conocer los conceptos del desarrollo de software, y las herramientas que la disciplina aporta para producir software en forma controlada, se presenta con detalle una metodología orientada a objetos (OO) en la que se distinguen diferentes y útiles artefactos que pueden ser generados en un proceso de desarrollo de software. Se muestra el uso de las principales técnicas para captura de requisitos, análisis OO, diseño OO y programación de modelos OO. Se presentan patrones de diseño OO, su programación y uso. Se revisa el apoyo de la tecnología Web para el desarrollo OO.',0),(30,2,'ALGORITMOS AVANZADOS',6,5,'13210',4,2,2,NULL,0),(31,2,'INGLÉS III',3,5,'10132',0,0,2,NULL,0),(32,2,'ALGORITMOS NUMÉRICOS',6,6,'13282',4,2,2,'Al término de la asignatura el alumno será capaz de:\n\n1. Determinar los tipos y fuentes de errores en cálculos numéricos.\n2. Encontrar aproximaciones numéricas a problemas con ecuaciones no-lineales.\n3. Resolver sistemas lineales de ecuaciones.\n4. Obtener productos de la integración en forma aproximada y encontrar ceros de funciones.\n5. Aproximar funciones y Resolver ecuaciones diferenciales.\n6. Modelar y resolver problemas en base a algoritmos probabilistas.\n7. Implementar los métodos anteriores usando programación científica.',0),(33,2,'SISTEMAS OPERATIVOS',7,6,'13212',4,2,2,NULL,0),(34,2,'PROCESAMIENTO DE LENGUAJES FORMALES',6,6,'13213',4,2,2,'Asignatura teórico-práctica que tiene como propósito entregar herramientas al estudiante para la resolución de problemas de representación, reconocimiento y traducción de lenguajes formales.',0),(35,2,'ANTROPOLOGÍA E INGENIERÍA',3,6,'13214',4,0,0,NULL,0),(36,2,'TALLER DE BASE DE DATOS',6,6,'13215',2,0,4,NULL,0),(37,2,'INGLÉS IV',3,6,'10133',0,0,2,NULL,0),(38,2,'INFERENCIA Y MODELOS ESTADÍSTICOS',6,7,'13283',4,2,0,NULL,0),(39,2,'REDES DE COMPUTADORES',7,7,'13217',4,2,2,NULL,0),(40,2,'LÓGICA Y TEORÍA DE LA COMPUTACIÓN',6,7,'13284',4,2,0,NULL,0),(41,2,'MACROECONOMÍA Y GLOBALIZACIÓN',4,7,'13219',4,0,0,NULL,0),(42,2,'MÉTODOS DE INGENIERÍA DE SOFTWARE',6,7,'13220',4,2,0,NULL,0),(43,2,'MÉTODOS DE OPTIMIZACIÓN',5,8,'13221',4,0,2,NULL,0),(44,2,'ANÁLISIS DE DATOS',6,8,'13222',4,0,2,NULL,0),(45,2,'SISTEMAS DE COMUNICACIÓN',6,8,'13223',4,0,2,NULL,0),(46,2,'FINANZAS Y CONTABILIDAD',5,8,'13224',4,0,2,NULL,0),(47,2,'ADMINISTRADOR DE PROYECTO DE SOFTWARE',8,8,'13225',4,2,2,NULL,0),(48,2,'INGENIERÍA Y SOCIEDAD',4,9,'13226',4,0,2,NULL,0),(49,2,'MODELACIÓN Y SIMULACIÓN',8,9,'13227',4,2,2,NULL,0),(50,2,'SISTEMAS DISTRIBUÍDOS',6,9,'13228',4,0,2,NULL,0),(51,2,'EVALUACIÓN DE PROYECTOS',6,9,'13229',2,0,4,NULL,0),(52,2,'TALLER DE INGENIERÍA DE SOFTWARE',6,9,'13030',2,0,4,NULL,0),(53,2,'TÓPICOS DE ESPECIALIDAD I',6,10,'13231',4,0,2,NULL,0),(54,2,'TÓPICOS DE ESPECIALIDAD II',6,10,'13232',4,0,2,NULL,0),(55,2,'TÓPICOS DE ESPECIALIDAD III',6,10,'13233',4,0,2,NULL,0),(56,2,'SEGURIDAD Y AUDITORÍA INFORMÁTICA',5,10,'13234',4,0,2,NULL,0),(57,2,'PROYECTO DE INGENIERÍA INFORMÁTICA',7,10,'13235',0,0,8,NULL,0),(58,2,'TÓPICOS DE ESPECIALIDAD IV',6,11,'13236',4,0,2,NULL,0),(59,2,'TÓPICOS DE ESPECIALIDAD V',6,11,'13237',4,0,2,NULL,0),(60,2,'TÓPICOS DE ESPECIALIDAD VI',6,11,'13238',4,0,2,NULL,0),(61,2,'DIRECCIÓN Y GESTIÓN DE EMPRESAS',6,11,'13239',2,0,4,NULL,0),(62,2,'SEMINARIO DE INFORMÁTICA',6,11,'13285',2,0,4,NULL,0),(63,2,'TRABAJO DE TITULACIÓN',30,12,'13286',0,0,2,NULL,0),(64,1,'CALCULO',NULL,1,'10001',4,4,0,'Al final del curso el alumno tendrá la capacidad de:\n 1.- Expresar analíticamente (en ecuaciones) una sentencia (texto).\n 2.- Formular, analizar y resolver problemas mediante el calculo diferencial y calculo integral.',1),(65,1,'ALGEBRA',NULL,1,'10002',6,2,0,'Al final del curso el alumno tendrá la capacidad de:\n 1.- Traducir los datos de un problema práctico a fórmulas algebraicas\n 2.- Analizar y resolver problemas prácticos usando herramientas algebraicas\n 3.- Generar algoritmos para la resolución de problemas básicos\n 4.- Identificar datos, recursos y variables de decisión',1),(66,1,'FISICA',NULL,1,'10003',4,2,2,' Al final del curso el alumno podrá:\n 1.- Conocer el avance científico a través de la historia.\n 2.- Expresar analíticamente un fenómeno de la Mecánica.\n 3.- Interpretar físicamente ecuaciones de la Mecánica.\n 4.- Identificar los distintos conceptos de la Mecánica en la vida diaria.\n 5.- Aplicar ecuaciones de Mecánica en la resolución de problemas.\n ',1),(67,1,'INTRODUCCION A LA QUIMICA',NULL,1,'10004',6,0,0,'Al final del curso el alumno podrá:\n * Valorar el avance de la ciencia y de la ciencia química en particular, a través de la historia.\n * Identificar los distintos conceptos de la química en la vida diaria.\n *Evaluar los alcances de las distintas leyes que rigen la química.\n *Identificar aspectos químicos presentes en los temas ecológicos e industriales.',0),(68,1,'FUNDAMENTOS DE PROGRAMACION',NULL,2,'13006',4,2,2,'Al término de la asignatura el alumno será capaz de :\n1. Aplicar estrategias para resolver problemas: algorítmicos y problemas no estructurados.\n2. Describir los procesos a través de los cuales se aprende a resolver los problemas algorítmicos y los problemas no estructurados.\n3. Diseñar y escribir programas, en los estilos de programación: funcional, imperativo e hibrido, utilizando objetos simples, compuestos y complejos.\n4. Evaluar el comportamiento de programas a partir de su código con distintos enfoques de programación.',0),(69,1,'CALCULO AVANZADO',NULL,3,'10007',4,4,0,' Al final del curso el alumno podrá:\n\n 1.- Expresar analíticamente una sentencia.\n 2.- Formular, analizar, y resolver problemas mediante el cálculo diferencial y el cálculo integral de varias variables.',0),(70,1,'ECUACIONES DIFERENCIALES',NULL,3,'10008',4,4,0,'Al final del curso el alumno tendrá la capacidad de:\n 1.- Formular modelos diferenciales simples de las Ciencias y la Ingeniería.\n 2.- Resolver problemas de valores iniciales y/o de frontera que involucran ecuaciones diferenciales clásicas de las ciencias y de la Ingeniería.',0),(71,1,'ELECTROMAGNETISMO Y FISICA MODERNA',NULL,3,'10010',4,2,2,'Al final del curso el alumno podrá:\nConocer el avance científico a través de la historia. \nExpresar analíticamente un fenómeno físico.\nInterpretar físicamente ecuaciones del Electromagnetismo y la Fís. Mod.\nIdentificar los distintos conceptos físicos en la vida diaria.\nAplicar las ecuaciones de la Física a la resolución de problemas.',0),(72,1,'LENGUAJES Y PARADIGMAS DE PROGRAMACION',NULL,3,'13007',4,2,2,'Al final del curso el alumno podrá:\n\n1. Identificar y explicar las características de los siguientes paradigmas de programación: imperativo, orientado a objetos, funcional, concurrente y lógico o relacional.\n2. Identificar el paradigma de un lenguaje y sus características.\n3. Analizar, predecir y diseñar comportamientos y estructuras de programas escritos en lenguajes de los paradigmas: imperativo, orientado a objetos, funcional y lógico.\n4. Diseñar y construir programas en los principales lenguajes pertenecientes a cada uno de los paradigmas del punto anterior.',0),(73,1,'PROBABILIDAD Y ESTADISTICA',NULL,4,'10009',4,2,0,'Al final del curso el alumno será capaz de:\n1. Elaborar información cuantitativa y cualitativa, generando  gráficos y tablas estadísticas adecuadas.\n2. Determinar modelos probabilísticos adecuados para describir matemáticamente “Experimentos Aleatorios” reales y aplicar correctamente los principales teoremas del cálculo de probabilidades.\n3. Modelar algunas características concretas de fenómenos naturales en términos de variables aleatorias continuas o discretas que siguen distribuciones conocidas.\n4. Definir la distribución de probabilidad de las principales estadísticas derivadas de muestras aleatorias simples de una población infinita.\n5. Reconocer los problemas de estimación de parámetros y de dócimas de hipótesis como problemas de decisión con riesgos medidos en términos de probabilidad.\n6. Resolver problemas de estimación de parámetros y de docimasia de hipótesis referentes a medias, proporciones, varianza, bondad de ajuste, asociación y regresión lineal.',0),(74,1,'MICROECONOMIA',NULL,4,'10013',4,0,0,'Comprender el comportamiento de los agentes microeconómicos. Enfrentar con fundamentos teóricos los hechos económicos que ocurren habitualmente en nuestra sociedad e internacionalmente. ',0),(75,1,'TOPICOS DE MATEMATICAS I - INFORMATICA',NULL,4,'13008',4,2,0,'Al final del curso el alumno podrá:\n 1.- Modelar diferentes tipos de problemas que se presentan en Ingeniería.\n 2.- Resolver numéricamente diversos problemas de Ingeniería.',0),(76,1,'ESTRUCTURA DE DATOS Y ALGORITMOS',NULL,4,'13009',4,2,2,'Al final del curso el alumno podrá: identificar estructuras de datos aplicables a problemas simples y clásicos, incluyendo el análisis de su desempeño. Además, desarrollará una visión general de la aplicación de algoritmos, independiente del lenguaje de programación.',0),(77,1,'ESTRUCTURAS DISCRETAS',NULL,4,'13010',4,2,0,'Al final del curso el alumno podrá:\n1. Aplicar los fundamentos lógicos a los métodos de prueba.\n2. Resolver  problemas combinatorios.\n3. Calcular complejidad de algoritmos.\n4. Aplicar el Algebra de Boole al diseño de los sistemas combinatorios.\n5. Diseñar sistemas secuenciales.',0),(78,1,'MACROECONOMIA',NULL,5,'10014',4,0,0,'Al final del curso el alumno podrá:\n1. Identificar las variables macroeconómicos que afectan la vida económica de una nación y del mundo entero.\n2. Conocer las herramientas de que disponen las autoridades económicas para enfrentar el crecimiento de una economía, identificar los factores que afectan el empleo y la inflación.\n3. Determinar como afectan los cambios económicos de un país a las economías del resto de mundo.\n4. Conocer la información diaria disponible de la economía nacional como internacional en publicaciones especializadas como son el Diario Financiero, Estrategia,  Wall Street Journal y Financial Times.',0),(79,1,'AUTOMATAS Y LENGUAJES FORMALES',NULL,5,'13011',4,2,0,'Al final del curso el alumno podrá:\n1. Identificar tipos de lenguajes formales y la complejidad asociada a la resolución de problemas de representación, reconocimiento, traducción.\n2. Describir lenguajes mediante lenguajes formales de descripción (expresiones regulares y gramáticas).\n3. Decidir el modelo de máquina aplicable al problema de reconocimiento de lenguajes. Combinar y optimizar autómatas finitos.\n4. Implementar algoritmos correspondientes a los modelos de máquinas ',0),(80,1,'FUNDAMENTOS DE PROCESOS PRODUCTIVOS',NULL,5,'13012',4,2,0,'Al final del curso el alumno deberá ser capaz de:\n\na) Reconocer las variables de mayor incidencia en los procesos productivos\nb) Identificar  procesos productivos y representarlos mediante diagramas de flujo de procesos.\nc) Identificar los equipos fundamentales utilizados en procesos productivos y describir diagramas de procesos productivos.\nd) Reconocer mecanismos de  transferencia de calor en procesos productivos\ne) Reconocer modelos matemáticos asociado a la transformación de la materia prima en producto final.',0),(81,1,'ALGORITMOS AVANZADOS',NULL,5,'13013',4,2,2,'Al final del curso el alumno podrá:\n1. Diseñar algoritmos que resuelvan problemas particulares.\n2. Evaluar el comportamiento de algoritmos para resolver un problema dado.\n3. Evaluar complejidad de problemas. \n4. Decidir entre diferentes algoritmos el más adecuado para resolver un problema dado.',0),(82,1,'ORGANIZACION DE COMPUTADORES',NULL,5,'13014',4,2,2,'Al final del curso el alumno podrá:\n1. Conocer la estructura y organización lógica de un computador.  \n2. Conocer y evaluar comparativamente distintos procesadores.  \n3. Programación en lenguaje de “ensamble\".',0),(83,1,'FINANZAS',NULL,6,'10012',4,0,2,'Al finalizar el curso el estudiante será capaz de:\n1. Conocer los fundamentos que sustentan la teoría Financiera.\n2. Aplicar diversas técnicas para analizar y emitir diagnósticos de los estados financieros de empresas.\n3. Aplicar las principales técnicas de valuación a la resolución de problemas inherentes a la administración financiera.',0),(84,1,'LOGICA Y TEORIA DE COMPUTACION',NULL,6,'13015',4,2,0,'Al finalizar el curso el estudiante será capaz de:\n1. Reconocer la sintaxis de distintos sistemas lógicos \n2. Aplicar estrategias de cálculos a distintos sistemas lógicos\n3. Especificar y resolver problemas en distintos sistemas lógicos\n4. Reconocer los límites  de computabilidad y complejidad de los formalismos lógico-computacionales.\n5. Identificar sistemas lógicos para representar cómputo distribuido y paralelo.',0),(85,1,'COMPILADORES',NULL,6,'13016',4,2,2,'Al término del curso el estudiante podrá:\n1.- Diseñar y construir traductores de lenguajes de programación, especialmente compiladores.\n2.- Aplicar conceptos y técnicas de diseño y construcción de procesadores de lenguajes al diseño de software en general, por ejemplo: migración de datos, programas, ingeniería reversa de software.',0),(86,1,'COMUNICACION DE DATOS',NULL,6,'13017',4,2,2,'Al final del curso el alumno podrá:\n1. Identificar y representar señales análogas y digitales en el tiempo y la frecuencia.\n2. Describir sistemas y algoritmos de codificación de datos.\n3. Describir y evaluar el comportamiento de canales de comunicación tanto análogos como digitales.\n4. Identificar y describir diferentes sistemas de transmisión.',0),(87,1,'SISTEMAS OPERATIVOS',NULL,6,'13018',4,2,2,'Al final del curso el alumno podrá:\n\n1. Reconocer las características asociadas al funcionamiento de los sistemas operativos.\n2. Utilizar eficientemente  los recursos de un sistema computacional a través del conocimiento de la estructura de su sistema operativo.  \n3. Diseñar y construir sistemas de administración  de recursos basados en la Teoría de Sistemas Operativos.',0),(88,1,'METODOS DE OPTIMIZACION',NULL,7,'13019',4,0,2,'Al final del curso el alumno podrá:\n1. Reconocer problemas de optimización.\n2. Modelar matemáticamente problemas de optimización.\n3. Analizar y determinar la factibilidad de la resolución computacional  de diferentes problemas de optimización. \n4. Resolver diferentes problemas de optimización de acuerdo a su complejidad.\n',0),(89,1,'COMPUTACION PARALELA',NULL,7,'13020',4,0,2,'Al final del curso el alumno podrá:\n1. Identificar los elementos de arquitecturas de máquinas paralelas \n2. Describir y analizar los principios generales de la computación paralela.\n3. Utilizar técnicas de  programación paralela.',0),(90,1,'INGENIERIA DE SISTEMAS',NULL,7,'13021',4,2,2,'Al final del curso el alumno podrá:\n1. Identificar las diferencias entre el enfoque científico reduccionista y el enfoque sistémico.\n2. Aplicar estrategias para resolver problemas no estructurados en el ámbito organizacional para diseñar sus procesos.\n3. Negociar con los involucrados en la situación problema, los modelos formales propuestos.\n4. Reconocer el rol de las Tecnologías de la Información y de la Comunicación como subordinadas a la estrategia de la organización.',0),(91,1,'REDES DE COMUNICACION',NULL,7,'13022',4,2,2,'Al final del curso el alumno podrá:\n1. Comprender los conceptos y la terminología de las redes de comunicación.\n2. Identificar elementos necesarios para el funcionamiento de un sistema de redes.\n3. Conocer y evaluar tecnologías de redes actuales.\n4. Evaluar los sistemas de comunicación en base a criterios técnicos y  requerimientos de las aplicaciones.',0),(92,1,'FUNDAMENTOS DE BASE DE DATOS',NULL,7,'13023',4,2,0,'Al final del curso el alumno podrá:\n1. Identificar los conceptos de los sistemas de Bases de Datos.\n2. Diseñar Bases de Datos.\n3. Utilizar técnicas de construcción de Bases de Datos\n4. Evaluar desempeño de Sistemas de Bases de Datos. \n',0),(93,1,'ANTROPOLOGIA E INGENIERIA',NULL,8,'10015',4,0,0,'Al final del curso el alumno podrá:\n 1.- Definir la cuestión central de la antropología filosófica.\n 2.- Sostener una visión filosófica del hombre\n 3-  Comprender el sentido y la importancia de la técnica en la vida humana.',0),(94,1,'FUNDAMENTOS DE INGENIERIA DE SOFTWARE',NULL,8,'13024',4,2,0,'Al término del curso el estudiante deberá ser capaz de:\n\nExplicar los conceptos fundamentales de la ingeniería de software.\nDescribir y aplicar técnicas de modelamiento de software. \nDiseñar un sistema de software siguiendo los enfoques y las prácticas de la ingeniería de software, en especial de la ingeniería de software orientada a objetos.',0),(95,1,'CONTROL Y SIMULACION',NULL,8,'13025',4,2,2,'Al final del curso el alumno podrá:\n1. Comprender los fundamentos de la teoría de sistemas lineales.\n2. Utilizar técnicas de modelación de sistemas dinámicos lineales y no lineales.\n3. Analizar, diseñar y evaluar estrategias básicas de control automático.\n4. Conceptualizar, modelar y simular distintos procesos de automatización.\n5. Manejar herramientas computacionales de simulación y de automatización.',0),(96,1,'SISTEMAS DISTRIBUIDOS',NULL,8,'13026',4,0,2,'Al término del curso, el estudiante entenderá la finalidad y características teóricas deseables de los Sistemas Distribuidos (SD), además de plataformas y soluciones tecnológicas basadas en éstos. ',0),(97,1,'PROYECTOS DE BASE DE DATOS',NULL,8,'13027',2,0,4,'Al final del curso el alumno podrá:\n1. Aplicar Bases de Datos en sistemas de información.\n2. Construir una aplicación que utilice Bases de Datos.\n3. Evaluar la capacidad y desempeño de una Base de Datos.\n',0),(98,1,'EVALUACION DE PROYECTOS',NULL,9,'10017',2,0,4,'Al final del curso el alumno podrá:\n1. Ser capaz de desarrollar y aplicar distintas metodologías y conceptos de evaluación de proyectos, generando las competencias que permitan apoyar la toma de decisiones.  ',0),(99,1,'PROYECTOS DE INGENIERIA DE SOFTWARE',NULL,9,'13028',2,0,4,'Al finalizar el curso el estudiante será capaz de:\n1. Desarrollar un sistema de software aplicando con rigor una metodología de desarrollo de software en todas sus fases. \n2. Aplicar principios de calidad a todo el proceso de desarrollo de software.\n3. Identificar problemas en el desarrollo de software de gran escala. ',0),(100,1,'COMPORTAMIENTO HUMANO EN EL TRABAJO - INFORMATICO',NULL,9,'13029',6,0,0,'*Proporcionar al alumno de ingeniería los conocimientos que le permitan una futura capacitación en su lugar de trabajo.\n\n*Al término del curso, el alumno de ingeniería estará preparado para adquirir una visión clara y fundamentada de las posibilidades de una participación activa en el ámbito del trabajo: implementación de proyectos nuevos, negociaciones, capacitación, gestión estratégica etc.',0),(101,1,'INTERFAZ HUMANO COMPUTADOR',NULL,9,'13030',4,0,2,'Al final del curso el alumno podrá:\n1. Conocer los alcances de la disciplina IHC\n2. Describir los conceptos claves de las disciplinas que aportan a la IHC\n3. Describir formalmente la actividad del usuario y del sistema\n4. Diseñar interfaces de usuario interactivas\n5. Aplicar principios y estándares en el diseño de una interfaz de usuario\n6. Implantar interfaces de usuario\n7. Evaluar la interacción humano computador\n8. Consolidar los conocimientos en aplicaciones reales',0),(102,1,'ELECTIVO PROFESIONAL I',NULL,9,'13031',4,0,2,NULL,0),(103,1,'ADMINISTRACION DE EMPRESAS',NULL,10,'10018',4,0,0,'Al finalizar el curso el alumno podrá:\n\n1. Comprender la dinámica de la empresa como un sistema inscrito en un sistema mayor y las influencias que éste ejerce sobre ella.\n2. Identificar y describir el rol de los empresarios y administradores en la empresa\n3. Reconocer y describir el funcionamiento de las áreas o funciones de una empresa (funciones de Comercialización, Producción, Finanzas y Recursos Humanos.\n4. Reconocer la naturaleza específica del problema administrativo.\n5. Analizar y comprender las 4 fases del proceso administrativo: planificación, organización, dirección y control.\n6. Analizar y comprender el proceso de toma de decisiones.',0),(104,1,'PROYECTO DE INGENIERIA INFORMATICA',NULL,10,'13032',0,0,8,'Al final del curso el alumno podrá: \n\nPreparar, formular, evaluar y presentar un proyecto en el ámbito de la Ingeniería Informática, utilizando los elementos teóricos y prácticos adquiridos durante la carrera. Para ello, el alumno deberá establecer adecuadamente los aspectos técnicos, económicos, operacionales, legales y de mercado y sus interrelaciones, en un proyecto que deberá integrar las diversas áreas de conocimientos obtenidos durante su carrera.\n\nPresentar y defender propuestas tecnológicas, en lo posible, originales, que resuelvan necesidades relevantes de personas, grupos sociales u organizaciones y, cuya ejecución, pueda constituirse como una actividad empresarial de negocio, económicamente sustentable.\n\nEvaluar proyectos ya formulados bajo un enfoque de emprendimiento conducente a la generación de productos y servicios informáticos que satisfagan demandas determinadas, en lo posible, no tradicionales.',0),(105,1,'ELECTIVO PROFESIONAL II',NULL,10,'13033',4,0,2,NULL,0),(106,1,'ELECTIVO PROFESIONAL III',NULL,10,'13034',4,0,2,NULL,0),(107,1,'ELECTIVO PROFESIONAL IV',NULL,10,'13035',4,0,2,NULL,0),(108,1,'DIRECCION Y GESTION DE EMPRESAS',NULL,11,'13036',2,0,4,'Comprender los Conceptos y herramientas fundamentales de la Administración de Operaciones.\nManejar y Aplicar las técnicas y procedimientos de Administración de Operaciones, para la solución de problemas de gestión y la toma de decisiones en empresas de productos químicos o de servicios.\nIdentificar y evaluar los factores claves y la interdependencia de estos factores en la gestión efectiva de los sistemas operativos en empresas de productos químicos y de servicios.',0),(109,1,'ELECTIVO PROFESIONAL V',NULL,11,'13037',4,0,2,NULL,0),(110,1,'ELECTIVO PROFESIONAL VI',NULL,11,'13038',4,0,2,NULL,0),(111,1,'SEMINARIO DE TITULACION',NULL,11,'13039',0,0,4,'Al finalizar la asignatura, el alumno deberá haber definido su proyecto de Trabajo de Título y logrado que éste sea aprobado por el Departamento.',0),(112,1,'TRABAJO DE TITULACION',NULL,12,'13040',0,0,16,NULL,0),(113,3,'CÁLCULO APLICADO',NULL,1,'10051',6,2,0,'Al final del curso, el alumno habrá logrado desarrollar las habilidades operacionales y capacidades de empleo de ellas y sus fundamentos, en la formación de modelos matemáticos, la determinación de soluciones a situaciones inéditas, en un estilo lógico sistemático y coherente.',1),(114,3,'MATEMÁTICAS GENERAL',NULL,1,'10052',6,2,0,'Al final del curso el alumno tendrá la capacidad de:\n 1.- Traducir el enunciado de un problema práctico a una expresión algebraica\n 2.- Utilizar las herramientas algebraicas para analizar y resolver problemas prácticos\n 3.- Identificar datos, recursos y variables de decisión',1),(115,3,'INTRODUCCIÓN A LA QUÍMICA',NULL,1,'10054',6,0,0,'Al final del curso el alumno podrá:\n * Valorar el avance de la ciencia y de la ciencia química en particular, a través de la historia.\n * Identificar los distintos conceptos de la química en la vida diaria.\n *Evaluar los alcances de las distintas leyes que rigen la química.\n *Identificar aspectos químicos presentes en los temas ecológicos e industriales.    \n ',0),(116,3,'FISICA GENERAL - INFORMÁTICA',NULL,1,'13052',6,2,2,' Al final del curso el podrá:\nIdentificar los principales hitos en el desarrollo de la Física Clásica.\nIdentificar los distintos fenómenos de la Mecánica en la vida diaria y en la industria.\nEvaluar los distintos conceptos de mecánica de fluidos en sistemas estáticos y dinámicos.\nExplicar los mecanismos de transferencia del calor, las formas de cuantificarlo, y las consecuencias en los materiales.\nEvaluar el Primer principio de la Termodinámica en sistemas observados en la vida diaria y en aplicaciones industriales.\nEvaluar el Segundo Principio de la Termodinámica en sistemas observados en la vida diaria y en aplicaciones industriales. ',1),(117,3,'INTRODUCCION A LA PROGRAMACIÓN',NULL,2,'13051',4,2,2,'Al final del curso el alumno podrá:\n1. Aplicar estrategias para resolver problemas: algorítmicos  y  problemas no estructurados.\n2. Describir los procesos a través de los cuales se aprende a resolver los problemas algorítmicos  y  los problemas no estructurados.\n3. Diseñar y escribir programas, en los estilos de programación: funcional, imperativo e híbrido, utilizando objetos simples, compuestos y complejos.\n4. Evaluar el comportamiento de programas a partir de su código con distintos enfoques de programación.',0),(118,3,'MATEMÁTICAS APLICADAS',NULL,3,'10057',4,2,0,' Al final del curso el alumno tendrá la capacidad de:\n \nResolver ecuaciones en forma aproximada.\nHallar el valor numérico aproximado de una integral.\nEncontrar solución aproximada de ecuaciones diferenciales.\nOrganizar y analizar información estadística.\nCalcular y aplicar medidas estadísticas.\n      -      Asociar modelos de probabilidad a fenómenos reales. ',0),(119,3,'MÉTODOS GRÁFICOS COMPUTACIONALES',NULL,3,'10058',2,0,4,'Al final del curso el alumno podrá:\n \nResolver problemas mediante geometría descriptiva\nRepresentar cuerpos en proyección diédrica\nRepresentar e interpretar dibujos en perspectivas isométricas\nRepresentaciones convencionales:  Interpretación de cortes, de roscas e hilos.\n-    Interpretación de planos.',0),(120,3,'INTRODUCCIÓN A LA ECONOMÍA',NULL,3,'10060',4,2,0,'Este curso pretende entregar las bases de la teoría económica, para que así el alumno pueda interpretar y comprender los aspectos centrales de acontecer económico e internacional, y aplicar los conceptos e instrumentos teóricos y prácticos entregados, para analizar y evaluar situaciones específicas de la realidad.',0),(121,3,'LENGUAJES DE PROGRAMACIÓN',NULL,3,'13053',4,0,2,'Al final del curso el alumno podrá:\n1. Identificar y explicar las características de los  siguientes paradigmas de programación: imperativo, orientado a objetos, funcional, concurrente y lógico o relacional. \n2. Identificar el paradigma de un lenguaje y sus características\n3. Analizar, predecir y diseñar comportamientos y estructuras de programas escritos en lenguajes de los paradigmas: imperativo, orientado a objetos, funcional y lógico.\n4. Diseñar y construir programas en los principales lenguajes pertenecientes a cada uno de los paradigmas del punto anterior. ',0),(122,3,'TALLER DE DESARROLLO PERSONAL',NULL,4,'10061',4,0,0,'El taller, como Metodología Activa centrada en el alumno, pretende proporcionar a éste los medios adecuados para autoevaluarse en su proceso de desarrollo como Persona, su auto-conocimiento, su capacidad para relacionarse, sus valores, sus sentimientos y emociones, a través de experiencias de aprendizaje estructurales, que promueven en los alumnos una actitud abierta y espontánea.',0),(123,3,'ADMINISTRACION',NULL,4,'10062',4,0,0,'Al finalizar el curso el alumno podrá:\n\n1. Comprender la dinámica de la empresa como un sistema inscrito en un sistema mayor y las influencias que éste ejerce sobre ella.\n2. Identificar y describir el rol de los empresarios y administradores en la empresa\n3. Reconocer y describir el funcionamiento de las áreas o funciones de una empresa (funciones de Comercialización, Producción, Finanzas y Recursos Humanos.\n4. Reconocer la naturaleza específica del problema administrativo.\n5. Analizar y comprender las 4 fases del proceso administrativo: planificación, organización, dirección y control.\n6. Analizar y comprender el proceso de toma de decisiones.',0),(124,3,'TEORÍA DE SISTEMAS',NULL,4,'13054',4,2,0,'Al final del curso el alumno podrá:\n1. Utilizar las técnicas, conceptos y métodos de sistemas para concebir, estructurar y resolver problemas en sistemas complejos y de actividad humana.  Emplear el enfoque de sistemas como una herramienta epistemológica (de aprendizaje).',0),(125,3,'ALGORITMOS Y ESTRUCTURAS DE DATOS I',NULL,4,'13055',4,0,2,'Al final del curso el alumno podrá:\n1. Conocer y manejar estructuras de control, características básicas y avanzadas de los lenguajes de programación.  Entender y aplicar los conceptos de Tiempo de Ejecución y Orden de un algoritmo.  Entender y clasificar los distintos tipos de problemas P, NP, NP completo.\n2. Conocer la especificación de TDA para árboles y grafos, Ser capaz de definir operaciones, conocer y manipular mediante algoritmos las diferentes estructuras para representar dichos grafos.  Conocer los principales algoritmos para recorrer y manipular árboles y grafos.\n3. Manejar el concepto de recursividad y ser capaz de diseñar algoritmos recursivos de propósito general.\n4. conocer diferentes aplicaciones de los árboles binarios, árboles AVL y árboles en general.\n5. Conocer la especificación del TDA grafo y ser capaz de definir nuevas operaciones sobre los mismos.  Conocer las dos representaciones, estática y dinámica, para TDA. Conocer, aplicar y modificar los algoritmos de recorrido de TDA.',0),(126,3,'SISTEMAS DIGITALES',NULL,4,'13056',4,0,2,'Al final del curso el alumno podrá: \nAl término del curso el estudiante estará en condiciones de reconocer y/o especificar funcionalidad digital de componentes, aplicar métodos y tecnologías para el diseño y desarrollo de circuitos combinacionales y secuenciales de diversa complejidad. Además podrá conceptualizar, modelar, diseñar, implementar, analizar y evaluar distintas interfaces digitales, para enfrentar la integración de dichos circuitos con distintas plataformas de hardware computacional utilizados en la especialidad.',0),(127,3,'ESTRUCTURA DE COMPUTADORES',NULL,5,'13057',4,0,2,'Al final del curso el alumno podrá:\n1. Conocer la estructura y organización lógica de un computador.  \n2. Conocer y evaluar comparativamente distintos procesadores.  \n3. Programación en lenguaje de “ensamble\".',0),(128,3,'BASES DE DATOS',NULL,5,'13059',4,0,2,'Al final del curso el alumno podrá:\n1. Conocer y manipular un DBMS\n2. Diseñar e implementar extractores de información de una BD, utilizando consultas, funciones y procedimientos almacenados.\n3. Generar modelos abstractos (E-R) de datos e implementarlos.',0),(129,3,'PROCESAMIENTO DE LENGUAJES FORMALES',NULL,5,'13060',4,0,2,'Al término del curso el alumno debera ser capaz de:\nDescribir los problemas de descripcion y reconocimiento de lenguajes mediante las\nherramientas de descripción finita y máquinas de reconocimiento.\nIdentificar, diseñar e implementar soluciones de software a problemas de procesamiento\nde palabras y reconocimiento de lenguajes regulares y lenguajes libres de contexto\ndeterministas.',0),(130,3,'ALGORITMOS Y ESTRUCTURAS DE DATOS II',NULL,5,'13061',4,0,2,'Al final del curso el alumno podrá:\n\nDesarrollar en el alumno la capacidad de enfrentar problemas algorítmicos del ámbito de la Ingeniería Informática sabiendo identificar su grado de dificultad en su resolución computacional. \n1.   Demostrar conocimiento general sobre el diseño de algoritmos avanzados.\n2.   Demostrar conocimientos sobre la teoría de complejidad computacional. \n3.   Saber identificar la naturaleza combinatoria de problemas en informática.\n4.   Demostrar conocimiento para crear y evaluar modelos existentes que  representen de manera adecuada problemas de la Ingeniería Informática. \n5.  Comprensión de la importancia del uso de modelos para representar problemas de Ingeniería Informática.',0),(131,3,'CONTABILIDAD Y COSTO',NULL,5,'13071',4,2,0,'Al final del curso el alumno podrá:\n\n1. Comprender la naturaleza de los componentes en la estructura contable y de costos de las empresas.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             \n\n2. Entender el procedimiento para formular y analizar los estados contables usuales en toda empresas y su uso como herramienta en el proceso de toma de',0),(132,3,'EPISTEMOLOGÍA',NULL,6,'10067',4,0,0,'El alumno será capaz de:\n\n1. Reflexionar críticamente sobre la naturaleza, los objetivos, los métodos y \n    valor de la ciencia.\n\n2. Percibir el valor de la verdad de los enunciados científicos, y la \n    Importancia de los resultados de la aplicación del saber científico.\n\n3. Reflexionar sobre la ciencia como parte del proceso general de la \n    Existencia humana cuyo sentido puede ayudar a esclarecer.',0),(133,3,'INGENIERÍA DE SOFTWARE',NULL,6,'13062',4,0,2,'El estudiante debe ser capaz de aplicar una metodología, estándar o norma de desarrollo de software a problemas informáticos de diversos ámbitos del quehacer empresarial privado o publico, para ello deberá cuestionarse el entorno del problema a solucionar y el ámbito que lo rodea, además de reconocer las diferentes estructuras administrativas y de gestión asociada al desarrollo.',0),(134,3,'SISTEMAS OPERATIVOS',NULL,6,'13063',4,0,2,'Al final del curso el alumno podrá:\n1. Al término del curso estará en condiciones de:  Utilizar eficientemente los recursos de un sistema computacional a través de un conocimiento cabal de la estructura de su sistema operativo.  Seleccionar el Sistema Operativo más adecuado para una instalación dada.  Diseñar y construir sistemas de administración de recursos basados en la Teoría de Sistemas Operativos.',0),(135,3,'REDES COMPUTACIONALES',NULL,6,'13064',4,0,2,'Al final del curso el alumno podrá:\n1. Identificar y relacionar conceptos fundamentos de la teoría de comunicación de datos y redes, a fin de aplicarlos a la formulación y resolución de problemas de diseño e implementación de enlaces de comunicación Intranet/externet, en el ámbito de las redes de computación e informática.\n2. Desarrollar habilidades que contribuyan al manejo de estándares y tecnologías de redes, enlace físico y protocolos de transmisión/recepción, extendiendo su aplicación en el ámbito del diagnóstico de fallas, soporte y mantención funcional de interacciones WEB, esquemas de comunicaciones internet y redes más complejas.',0),(136,3,'ELECTIVO PROFESIONAL I',NULL,6,'13065',4,0,2,NULL,0),(137,3,'TALLER DE SISTEMAS DISTRIBUIDOS',NULL,7,'13066',2,0,4,'Al final del curso, el alumno:\nSerá capaz de comprender la naturaleza de los sistemas distribuidos, así como el paradigma que los envuelve.\nSerá capaz de identificar, analizar y traducir un conjunto de requerimientos de software en un sistemas distribuidos.\nDispondrá de un conjunto de elementos teóricos y prácticos para desenvolverse en nuevas tecnologías de la información orientadas al trabajo con sistemas distribuidos.',0),(138,3,'ELECTIVO PROFESIONAL II',NULL,7,'13067',4,0,2,NULL,0),(139,3,'ELECTIVO PROFESIONAL III',NULL,7,'13068',4,0,2,NULL,0),(140,3,'SEMINARIO DE PROYECTO DE TÍTULO',NULL,7,'13069',2,0,2,'Al final del curso el alumno podrá:\nEste es el curso terminal de la carrera, junto con Proyecto de Título II.  El objetivo es que el alumno prepare la propuesta de trabajo de título, de modo que con ella aprobada pueda iniciar su trabajo conducente a elaborar la memoria de titulación.\n\nSe espera en primer lugar que el alumno conozca, en forma general, los distintos ámbitos de la ingeniería informática para así poder formular una propuesta acorde con problemas y desarrollos actuales.  El alumno debe luego elaborar una propuesta articulada, conociendo de los reglamentos y las formalidades de esta.  Para ello tiene que aprender a realizar investigación sobre un tema, hacer búsquedas bibliográficas, resumir y extraer la información relevante desde memorias y artículos en general.  Debe ser capaz de generar una propuesta de solución propia, sugiriendo metodologías para su desarrollo y el desglose todas las actividades relevantes.',0),(141,3,'TALLER DE MANEJO DE DESARROLLO DE RELACIONES INTERPERSONALES',NULL,8,'10064',4,0,0,'El Taller es una instancia de aprendizaje cuya especificidad radica en la modalidad denominada “aprender haciendo”, por lo cual el aprendizaje es fundamentalmente experiencial, personal y subjetivo. Esto significa que la responsabilidad del proceso de aprender recae esencialmente en el asunto y requiere de la participación activa y comprometida de éste.  Al profesor cabe guiar dicho proceso, creando un marco para su conducción mediante la reflexión y discusión activa de los miembros integrantes del taller.',0),(142,3,'PROYECTO DE TÍTULO',NULL,8,'13070',0,0,16,NULL,0),(143,4,'CÁLCULO I PARA INGENIERÍA',7,1,'10101',6,2,0,'Asignatura teórica-práctica de iniciación al Cálculo para funciones reales de una variable real. Previamente presenta la estructura del conjunto fundamental R de los números reales como cuerpo ordenado completo, y luego el plano real cartesiano. Esta asignatura desarrolla los conceptos, propiedades y aplicaciones de los conceptos básicos del cálculo: límites, continuidad y derivada, mostrando recurrentemente aspectos de geometría analítica en el plano real.',0),(144,4,'ÁLGEBRA I PARA INGENIERÍA',7,1,'10102',6,2,0,'Asignatura teórico-práctica  que tiene como propósito describir los aspectos fundamentales de la matemática, especialmente en relación a las propiedades y aplicaciones de los diferentes tipos usuales de funciones y al estudio de ecuaciones algebraicas',0),(145,4,'FÍSICA I PARA INGENIERÍA',7,1,'10103',4,2,1,'En esta asignatura se presenta, a nivel fundamental, los conceptos básicos de la física en las temáticas de mecánica, ondas, fluidos y termodinámica.',0),(146,4,'TALLER DE DESARROLLO PERSONAL E INTEGRAL',3,1,'10104',2,0,2,'Asignatura orientada a la reflexión personal desde herramientas de la psicología, con la finalidad de posibilitar el desarrollo de habilidades de autoconciencia en los estudiantes, que permitan generar un proceso de adaptación a la vida universitaria, utilizando como recurso personal  la capacidad de observar sus procesos internos, conocer características de su  personalidad y el estilo de relacionarse consigo mismos, con lo otros y con el mundo exterior.\n\nDicho proceso se genera tanto  a nivel personal como en la relación y vinculación con los otros, lo cual abre la posibilidad de generar una vinculación significativa con los demás en este proceso de adaptación a una nueva etapa de la vida.',0),(147,4,'INTRODUCCIÓN A LA INGENIERÍA',2,1,'10125',0,0,2,'Asignatura teórico-práctica  que tiene como propósito describir los aspectos fundamentales de la Ingeniería, el ingeniero y su papel en el entorno laboral, social y medio ambiental.',0),(148,4,'MÉTODOS DE ESTUDIO',2,1,'10126',0,0,2,'Asignatura teórica-taller que tiene como propósito que los alumnos aprendan y comprendan los factores que explican el buen estudio, incluyendo la planificación de las actividades, el control del proceso intelectual y la evaluación de los resultados, a través de herramientas y metodologías de aprendizaje en el contexto de problemas matemáticos.',0),(149,4,'CÁLCULO II PARA INGENIERÍA',7,2,'10107',6,2,0,'Introduce el estudio del cálculo integral incluida la integral impropia. El  estudio de las series numéricas, las series de potencias, curvas en el espacio y diferenciación de funciones reales de varias variables.\n Entrega los elementos  del cálculo integral de funciones reales de una variable real y su aplicación a problemas de la ingeniería.\nEn su última parte, el curso entrega elementos de series de funciones, dando las bases para su aplicación al estudio de las series de Fourier. ',0),(150,4,'ÁLGEBRA II PARA INGENIERÍA',6,2,'10108',4,2,0,'Asignatura teórica-práctica que completa el desarrollo de los aspectos fundamentales del Álgebra, centrándose en los conceptos y resultados sobre vectores y la potente generalización a espacios vectoriales, la asociación con transformaciones (aplicaciones) lineales, y la incorporación de los productos interiores y los conceptos de valores propios y vectores propios.',0),(151,4,'FÍSICA II PARA INGENIERÍA',7,2,'10109',4,2,1,'Esta asignatura se presenta a nivel fundamental los conceptos básicos de la física en las temáticas de electricidad y magnetismo.',0),(152,4,'FUNDAMENTOS DE COMPUTACIÓN Y PROGRAMACIÓN',5,2,'10110',4,0,2,'Este curso tiene como objetivo acercar al estudiante de ingeniería a los computadores como herramientas de cómputo programables y versátiles, y como poderosas herramientas para la búsqueda y el manejo de información; se consideran también tanto la gran variedad de dispositivos accesorios existentes como la conectividad y accesibilidad a grandes fuentes de información. A continuación se enfrenta al alumno al problema de construir soluciones computacionales a problemas relativamente sencillos, proporcionándoles las herramientas básicas necesarias para poder construir programas computacionales. Finalmente, se muestra la utilidad de estas máquinas en la resolución de problemas numéricos con ayuda de software especializado de uso frecuente en ingeniería. ',0),(153,4,'QUÍMICA GENERAL',5,2,'10111',4,2,0,'Asignatura teórico-práctica  que tiene como propósito entregar a los estudiantes de ingeniería los conocimientos básicos de química, que les permitan conocer como se compone la materia y sus propiedades fundamentales, para comprender los fenómenos químicos que se presentan en la naturaleza y que están relacionados con procesos productivos.',0),(154,4,'ELECTRICIDAD Y MAGNETISMO PARA INGENIERÍA',7,3,'10127',4,2,1,'En esta asignatura se presenta la rama de la física que concierne a los fenómenos eléctricos y magnéticos ya que las leyes de electricidad y magnetismo desempeñan un papel fundamental en la comprensión del funcionamiento de varios dispositivos. Por tanto la asignatura presenta inicialmente los fenómenos eléctricos y sus leyes fundamentales, para posteriormente continuar con los fenómenos magnéticos y sus leyes, haciendo énfasis en los conceptos fundamentales y aplicaciones. ',0),(155,4,'COMUNICACIÓN EFECTIVA',2,3,'10128',0,0,2,NULL,0),(156,4,'INGLÉS I',3,3,'10130',0,0,2,'Asignatura práctica que tiene como objetivo el desarrollo de habilidades lingüísticas y comunicativas en Inglés como lengua extranjera en lectura, escritura, audición y expresión oral, de acuerdo al nivel A1 (ALTE Breakthrough) según el Marco Común Europeo de Referencia para las Lenguas: Aprendizaje, Enseñanza y Evaluación.',0),(157,4,'ECUACIONES DIFERENCIALES Y MÉTODOS NUMÉRICOS PARA INGENIERÍA',6,3,'10123',4,2,0,NULL,0),(158,4,'MÉTODOS DE PROGRAMACIÓN',8,3,'13201',4,2,2,'Asignatura teórico-práctica que tiene como propósito introducir al estudiante de\nIngeniería Informática a la resolución de problemas de manejo de datos, entregando\nherramientas para modelarlos, representar y programar una solución apropiada,\ndesarrollada en base al paradigma de programación orientada a objetos (POO).',0),(159,4,'ANÁLISIS ESTADÍSTICO PARA INGENIERÍA',5,3,'10115',4,2,0,'Curso introductorio que permite conocer y aplicar metodología estadística en el análisis de información, para apoyar al ingeniero  en la toma de decisiones utilizando probabilidades, cuando existe incertidumbre y variación',0),(160,4,'INGENIERÍA DE SISTEMAS',5,4,'13252',4,2,0,'Asignatura teórico-práctica que tiene como propósito introducir el pensamiento sistémico y crítico en el estudiante para que pueda realizar diseño de procesos de trabajo en organizaciones humanas. Además, el estudiante aprenderá a utilizar herramientas de ingeniería de sistemas para la modelización de problemas complejos y de procesos de trabajo. Con ellas, el estudiante  podrá dimensionar sistemáticamente los impactos de los resultados a los cuales llega con la abstracción. En el sentido de las habilidades, este curso hace que los estudiantes trabajen en equipo con proyectos acotados. En esos proyectos el estudiante usará técnicas y herramientas ayudan a mitigar los problemas derivados de la aplicación excesiva de los enfoques analítico-reduccionistas ampliamente utilizados en ingeniería. De esa forma, el curso pretende aportar en la dirección de la responsabilidad social y conciencia crítica de los estudiantes en el ejercicio de su profesión.',0),(161,4,'FUNDAMENTOS DE ECONOMÍA',5,4,'10116',4,2,0,'Asignatura teórico-práctica  que tiene como propósito describir los aspectos fundamentales de la Economía, y su aplicación en la Ingeniería, en sintonía con el entorno laboral, social y medio ambiental.',0),(162,4,'ESTRUCTURA DE COMPUTADORES',6,4,'13273',4,2,1,'Asignatura teórico-práctica que introduce los elementos básicos y fundamentales sobre los cuales se construye el fenómeno de la computación. \nConceptos de representación y manejo de la información en sistemas digitales a nivel avanzado.\nElementos básicos que componen el hardware de un computador a nivel medio.\nModelo básico de computación de Von Neumann.',0),(163,4,'PARADIGMAS DE PROGRAMACIÓN',6,4,'13204',4,0,2,'Asignatura teórico-práctica que tiene como propósito formalizar las características de los\nparadigmas de programación que el estudiante ya conoce (imperativa-procedural y\norientada a objetos) y capacitarlo para enfrentar el desarrollo de software con otros tipos\nde paradigmas y orientaciones.',0),(164,4,'ANÁLISIS DE ALGORITMOS Y ESTRUCTURA DE DATOS',5,4,'13205',4,0,2,'Asignatura teórico-práctica que tiene como propósito resolver problemas usando\nalgoritmos y estructuras de datos adecuados a la situación. En particular, se espera que el\nalumno aprenda a aplicar los conceptos de tiempo de ejecución y complejidad algorítmica;\ndiseñar, implementar y modificar estructuras de datos de acuerdo a las necesidades del\nproblema; diseñar algoritmos elementales, iterativos y recursivos; recomendar de manera\nfundada una estructura de datos para un problema dado. Los conceptos vistos en cátedra\nse refuerzan con actividades de laboratorio, donde deberán implementar diversas\nestructuras de datos para problemas específicos. ',0),(165,4,'INGLÉS II',3,4,'10131',0,0,2,NULL,0),(166,4,'EVALUACIÓN Y GESTIÓN DE PROYECTOS',5,5,'13256',4,0,2,'Asignatura teórico-práctica que tiene como objetivo preparar al alumno con las competencias y las herramientas adecuadas para evaluar y llevar a cabo la gestión de un proyecto de tecnologías de la información. ',0),(167,4,'DISEÑO DE BASE DE DATOS',5,5,'13274',4,1,1,NULL,0),(168,4,'ORGANIZACIÓN DE COMPUTADORES',6,5,'13275',4,1,1,NULL,0),(169,4,'FUNDAMENTOS DE INGENIERÍA DE SOFTWARE',5,5,'13209',4,2,0,'Asignatura teórica de carácter introductoria cuyo propósito es que el estudiante conozca los conceptos esenciales y consolidados de la Ingeniería de Software y la importancia de esta disciplina. Se proporciona un marco para comprender materias que son estudiadas en cursos más avanzados del área. Para conocer los conceptos del desarrollo de software, y las herramientas que la disciplina aporta para producir software en forma controlada, se presenta con detalle una metodología orientada a objetos (OO) en la que se distinguen diferentes y útiles artefactos que pueden ser generados en un proceso de desarrollo de software. Se muestra el uso de las principales técnicas para captura de requisitos, análisis OO, diseño OO y programación de modelos OO. Se presentan patrones de diseño OO, su programación y uso. Se revisa el apoyo de la tecnología Web para el desarrollo OO.',0),(170,4,'INFORMÁTICA Y SOCIEDAD',4,5,'13260',4,0,2,'El  propósito de esta asignatura es desarrollar la capacidad de analizar críticamente el efecto de la tecnología informática en las prácticas de la sociedad. Además, introduce al estudiante en la gestión del conocimiento asociada a las TIC.\nEl curso trata la temática de la “Sociedad del Conocimiento”. Para ello se identifican las etapas del proceso de creación y difusión del conocimiento en las organizaciones. En particular, esta asignatura motiva a que los estudiantes trabajen en equipo con proyectos acotados y relacionados con la gestión del conocimiento que se utiliza en el desarrollo de aplicaciones informáticas.\nDesde el área CTS (Ciencia Tecnología y Sociedad) el objetivo es que los estudiantes estén alertas a las dificultades y oportunidades del uso de la TIC en la sociedad. Los alumnos(as) descubren cuáles son los riesgos al asumir el paradigma dominante de la tecnología. Este modelo se presenta “neutro” y sin conexión con el impacto ético, político, psicológico, social y medio ambiental.\nSe describir',0),(171,4,'SISTEMAS DE INFORMACIÓN',4,5,'13261',4,0,2,'La asignatura considera la entrega de los conceptos y técnicas que permiten comprender la estructura y funcionamiento de una organización y cómo los flujos de información deben ser definidos y administrados para apoyar al logro de los objetivos organizacionales.  ',0),(172,4,'INGLÉS III',3,5,'10132',0,0,2,NULL,0),(173,4,'SISTEMAS OPERATIVOS',7,6,'13212',4,2,2,NULL,0),(174,4,'TALLER DE BASES DE DATOS',6,6,'13215',2,0,4,NULL,0),(175,4,'ADMINISTRACIÓN Y GESTIÓN INFORMÁTICA',5,6,'13262',4,0,2,NULL,0),(176,4,'TÉCNICAS DE INGENIERÍA DE SOFTWARE',5,6,'13265',4,0,2,NULL,0),(177,4,'REDES COMPUTACIONALES',6,6,'13266',4,2,2,NULL,0),(178,4,'INGLÉS IV',3,6,'10233',0,0,2,NULL,0),(179,4,'PROYECTO DE INGENIERÍA DE SOFTWARE',7,7,'13267',2,0,6,NULL,0),(180,4,'TÓPICOS DE ESPECIALIDAD I',6,7,'13268',4,0,2,NULL,0),(181,4,'TÓPICOS DE ESPECIALIDAD II',6,7,'13269',4,0,2,NULL,0),(182,4,'TÓPICOS DE ESPECIALIDAD III',6,7,'13270',4,0,2,NULL,0),(183,4,'SEMINARIO DE COMPUTACIÓN E INFORMÁTICA',2,7,'13276',2,0,4,NULL,0),(184,4,'TRABAJO DE TITULACIÓN',30,8,'13277',0,0,2,NULL,0);
/*!40000 ALTER TABLE `asignatura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asignatura_habilidad`
--

DROP TABLE IF EXISTS `asignatura_habilidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asignatura_habilidad` (
  `ID_ASIGNATURA` int(11) DEFAULT NULL,
  `ID_HABILIDAD` int(11) DEFAULT NULL,
  KEY `FK_RELATIONSHIP_10` (`ID_HABILIDAD`),
  KEY `FK_RELATIONSHIP_11` (`ID_ASIGNATURA`),
  CONSTRAINT `FK_RELATIONSHIP_10` FOREIGN KEY (`ID_HABILIDAD`) REFERENCES `habilidad` (`ID_HABILIDAD`),
  CONSTRAINT `FK_RELATIONSHIP_11` FOREIGN KEY (`ID_ASIGNATURA`) REFERENCES `asignatura` (`ID_ASIGNATURA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignatura_habilidad`
--

LOCK TABLES `asignatura_habilidad` WRITE;
/*!40000 ALTER TABLE `asignatura_habilidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `asignatura_habilidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asignatura_intermedia`
--

DROP TABLE IF EXISTS `asignatura_intermedia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asignatura_intermedia` (
  `ID_ASIGNATURA` int(11) DEFAULT NULL,
  `ASI_ID_ASIGNATURA` int(11) DEFAULT NULL,
  KEY `FK_RELATIONSHIP_6` (`ID_ASIGNATURA`),
  KEY `FK_RELATIONSHIP_7` (`ASI_ID_ASIGNATURA`),
  CONSTRAINT `FK_RELATIONSHIP_6` FOREIGN KEY (`ID_ASIGNATURA`) REFERENCES `asignatura` (`ID_ASIGNATURA`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_RELATIONSHIP_7` FOREIGN KEY (`ASI_ID_ASIGNATURA`) REFERENCES `asignatura` (`ID_ASIGNATURA`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignatura_intermedia`
--

LOCK TABLES `asignatura_intermedia` WRITE;
/*!40000 ALTER TABLE `asignatura_intermedia` DISABLE KEYS */;
INSERT INTO `asignatura_intermedia` VALUES (3,NULL),(4,NULL),(5,NULL),(6,NULL),(7,NULL),(8,NULL),(9,3),(10,4),(11,5),(12,4),(13,NULL),(14,11),(14,9),(15,6),(16,8),(17,9),(17,10),(18,12),(19,3),(20,9),(21,9),(22,10),(22,14),(23,18),(24,18),(25,16),(26,7),(26,21),(27,23),(28,22),(29,23),(30,23),(30,24),(31,25),(32,17),(32,20),(33,28),(34,24),(35,26),(35,15),(36,27),(36,29),(37,31),(38,21),(39,28),(40,34),(41,19),(41,35),(42,29),(43,20),(43,30),(44,38),(45,39),(46,19),(47,42),(48,41),(49,32),(50,45),(51,46),(52,36),(52,47),(53,50),(53,51),(53,52),(54,48),(54,49),(54,51),(55,50),(55,51),(55,52),(56,45),(57,51),(57,52),(58,53),(58,56),(59,54),(59,55),(60,54),(60,55),(61,46),(61,48),(62,57),(63,62),(64,NULL),(65,NULL),(66,NULL),(67,NULL),(68,NULL),(69,64),(70,64),(70,65),(71,64),(71,66),(72,68),(73,64),(74,64),(75,70),(75,68),(76,72),(77,70),(78,74),(79,72),(80,67),(80,71),(81,76),(82,71),(82,77),(83,78),(84,79),(85,79),(86,73),(86,82),(87,81),(87,82),(88,81),(89,81),(89,82),(90,78),(90,80),(91,86),(92,84),(93,84),(93,85),(93,86),(93,87),(94,90),(94,92),(95,90),(96,87),(96,91),(97,92),(98,83),(99,94),(99,97),(100,93),(101,93),(101,94),(102,94),(102,95),(102,96),(102,97),(103,83),(104,98),(104,99),(104,101),(105,102),(106,102),(107,102),(108,103),(109,89),(109,96),(110,89),(110,96),(111,102),(112,111),(113,NULL),(114,NULL),(115,NULL),(116,NULL),(117,NULL),(118,113),(119,117),(120,113),(121,117),(122,113),(122,114),(122,115),(122,116),(122,117),(123,120),(124,120),(124,117),(125,121),(126,114),(127,126),(128,125),(129,121),(130,125),(131,120),(132,118),(132,119),(132,120),(132,121),(133,128),(134,127),(135,127),(136,128),(137,133),(137,135),(138,133),(139,124),(139,135),(140,127),(140,128),(140,129),(140,130),(141,122),(142,140),(143,NULL),(144,NULL),(145,NULL),(146,NULL),(147,NULL),(148,NULL),(149,143),(150,144),(151,145),(152,144),(153,NULL),(154,149),(154,151),(155,146),(156,148),(157,149),(157,152),(158,152),(159,149),(160,147),(160,159),(161,143),(162,150),(162,154),(163,158),(164,158),(165,156),(166,161),(167,163),(168,162),(169,163),(170,155),(171,160),(172,165),(173,168),(174,167),(174,169),(175,166),(176,169),(177,168),(178,172),(179,175),(179,174),(179,176),(180,173),(180,174),(180,175),(180,176),(180,177),(180,178),(181,173),(181,174),(181,175),(181,176),(181,177),(181,178),(182,173),(182,174),(182,175),(182,176),(182,177),(182,178),(183,173),(183,174),(184,183);
/*!40000 ALTER TABLE `asignatura_intermedia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrera`
--

DROP TABLE IF EXISTS `carrera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carrera` (
  `ID_CARRERA` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE_CARRERA` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`ID_CARRERA`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrera`
--

LOCK TABLES `carrera` WRITE;
/*!40000 ALTER TABLE `carrera` DISABLE KEYS */;
INSERT INTO `carrera` VALUES (1,'Ingeniería civil informática'),(2,'Ingeniería de ejecución en computación e informática');
/*!40000 ALTER TABLE `carrera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habilidad`
--

DROP TABLE IF EXISTS `habilidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `habilidad` (
  `ID_HABILIDAD` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION_HABILIDAD` varchar(1024) DEFAULT NULL,
  `GRADO_HABILIDAD` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_HABILIDAD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habilidad`
--

LOCK TABLES `habilidad` WRITE;
/*!40000 ALTER TABLE `habilidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `habilidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nombres`
--

DROP TABLE IF EXISTS `nombres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nombres` (
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nombres`
--

LOCK TABLES `nombres` WRITE;
/*!40000 ALTER TABLE `nombres` DISABLE KEYS */;
/*!40000 ALTER TABLE `nombres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan` (
  `ID_PLAN` int(11) NOT NULL AUTO_INCREMENT,
  `ID_CARRERA` int(11) DEFAULT NULL,
  `NOMBRE_PLAN` varchar(128) DEFAULT NULL,
  `ANIO_PLAN` int(11) DEFAULT NULL,
  `CODIGO_PLAN` varchar(32) DEFAULT NULL,
  `VISIBLE_PLAN` tinyint(4) DEFAULT '0',
  `VERSION_PLAN` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`ID_PLAN`),
  KEY `FK_RELATIONSHIP_9` (`ID_CARRERA`),
  CONSTRAINT `FK_RELATIONSHIP_9` FOREIGN KEY (`ID_CARRERA`) REFERENCES `carrera` (`ID_CARRERA`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1,1,'Civil plan 2001',2001,'1863',1,NULL),(2,1,'Civil plan 2012',2012,'1363',1,NULL),(3,2,'Ejecucion plan 2001',2001,'1853',1,NULL),(4,2,'Ejecucion plan 2012',2012,'1353',1,NULL);
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profesor` (
  `ID_PROFESOR` int(11) NOT NULL AUTO_INCREMENT,
  `USUARIO_PROFESOR` varchar(32) DEFAULT NULL,
  `NOMBRE_PROFESOR` varchar(32) DEFAULT NULL,
  `APELLIDO_PROFESOR` varchar(32) DEFAULT NULL,
  `CONTRASENA_PROFESOR` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`ID_PROFESOR`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (1,'Consuelo.Ramirez','Consuelo','Ramírez Santibáñez','123'),(2,'Maria.Parodi','María Carolina','Parodi Davila','123'),(3,'Monica.Villanueva','Mónica','Villanueva Ilufi','123'),(4,'Erika.Rosas','Erika','Rosas Olivo','123'),(5,'Rodrigo.Pizarro','Rodrigo','Pizarro Guzmán','123'),(6,'Jacqueline.Kohler','Jacqueline','Kohler Casasempere','123'),(7,'Max.Chacon','Max','Chacón Pacheco','123'),(8,'Miguel.Fuentes','Miguel','Fuente Villalobos','123'),(9,'Fernando.Rannou','Fernando','Rannou Fuentes','123'),(10,'Mauricio.Marin','Mauricio','Marín Caihuan','123'),(11,'Manuel.Manriquez','Manuel','Manriquez López','123'),(12,'Bruno.Jerardino','Bruno','Jerardino Wiesenborn','123'),(13,'Rosa.Muñoz','Rosa','Muñoz Calanchie','123');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor_asignatura`
--

DROP TABLE IF EXISTS `profesor_asignatura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profesor_asignatura` (
  `ID_PROFESOR` int(11) DEFAULT NULL,
  `ID_ASIGNATURA` int(11) DEFAULT NULL,
  KEY `FK_RELATIONSHIP_12` (`ID_PROFESOR`),
  KEY `FK_RELATIONSHIP_15` (`ID_ASIGNATURA`),
  CONSTRAINT `FK_RELATIONSHIP_12` FOREIGN KEY (`ID_PROFESOR`) REFERENCES `profesor` (`ID_PROFESOR`),
  CONSTRAINT `FK_RELATIONSHIP_15` FOREIGN KEY (`ID_ASIGNATURA`) REFERENCES `asignatura` (`ID_ASIGNATURA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor_asignatura`
--

LOCK TABLES `profesor_asignatura` WRITE;
/*!40000 ALTER TABLE `profesor_asignatura` DISABLE KEYS */;
/*!40000 ALTER TABLE `profesor_asignatura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programa_asignatura`
--

DROP TABLE IF EXISTS `programa_asignatura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `programa_asignatura` (
  `ID_PROGRAMA` int(11) NOT NULL AUTO_INCREMENT,
  `ID_ASIGNATURA` int(11) DEFAULT NULL,
  `ANIO_PROGRAMA` int(11) DEFAULT NULL,
  `SEMESTRE_PROGRAMA` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_PROGRAMA`),
  KEY `FK_RELATIONSHIP_16` (`ID_ASIGNATURA`),
  CONSTRAINT `FK_RELATIONSHIP_16` FOREIGN KEY (`ID_ASIGNATURA`) REFERENCES `asignatura` (`ID_ASIGNATURA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programa_asignatura`
--

LOCK TABLES `programa_asignatura` WRITE;
/*!40000 ALTER TABLE `programa_asignatura` DISABLE KEYS */;
/*!40000 ALTER TABLE `programa_asignatura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('SEQ_GEN',0);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `APELLIDO` varchar(128) DEFAULT NULL,
  `NOMBRE` varchar(128) DEFAULT NULL,
  `ROL` varchar(20) DEFAULT NULL,
  `UID` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Carcamo','Miguel','alumno','1010'),(2,'sor','profe','profesor','1001');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-16 11:39:26
