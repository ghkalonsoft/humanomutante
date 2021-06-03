# Software para validar si un humano es mutante
El software recibe como parámetro un array de String, que contiene las secuencias de ADN, y valida a través de estas secuencias si un humano es o no mutante, 
con base en los requerimientos iniciales. 

# Ejecucuón en ambiente local
Se debe inicialmente clonar y/o descargar el proyecto del repositorio, preferiblemente imortarlo en el IDE Spring ToolSuite4 que fue donde se desarrolló,
se actualizar los paquetes configurados en el archivo POM.XML. La base de datos realizada en PostgeSql está configurada en el archivo application.properties.
El tomcat embebido en el IDE, realiza el despliegue en el puerto 8080.

# Ejecución en el VPS(Nube)
El consumo de los servicios(/mutant y stats), se realiza a través de las siguientes URLs:
1. Para la validación de la cadena por método POST: 
http://162.241.90.231:8080/humanoMutante/mutant/ 

 1.1 Cadena de array para mutantes: {
     "dna":["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
 }
 
 1.2 Cadena de array para no mutante:  {
     "dna":["ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"]
 }
 
 2. Para la generación del reporte se realiza a través de una petición GET: 
 http://162.241.90.231:8080/humanoMutante/stats/
