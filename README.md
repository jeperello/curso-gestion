# CURSO-GESTION

Aplicacion basada en el Framework: Spring Boot v2.1.3.RELEASE.

### Tecnologias utilizadas
- Lenguaje de programacion java con openjdk 10.0.2 
- Mysql-Connector base de datos.
- Maven manejador de dependencias. 


### Objetivo 
Se desea implementar un sistema de gestión para un instituto educativo con las siguientes premisas:

El instituto dicta diferentes cursos a los cuales concurren alumnos. Los cursos son dictados por profesores.
- Los alumnos pueden tomar varios cursos.
- Los profesores pueden dictar varios cursos.
- Los datos de los alumnos son: tipo y número de documento, nombre y apellido, fecha de nacimiento, domicilio actual, sexo y teléfono.
- Los datos de los docentes son : tipo y número de documento, nombre y apellido, fecha de nacimiento, domicilio actual, sexo y teléfono. Además se desea almacenar la lista de capacitaciones que ha realizado el docente y los títulos que posee.
- Para los cursos la información necesaria es: nombre del curso, duración en horas, breve descripción del contenido y nota de aprobación.
- El sistema debe implementar altas, modificaciones y borrado para cada una de las entidades que se creen.

Se deben implementar las siguientes consultas:
- Obtener la lista de todos los alumnos de la institución.
- Obtener la lista de todos los alumnos de un curso determinado.
- Obtener la lista de cursos a los cuales concurre un alumno determinado.
- Obtener la lista de todos los profesores de la institución.
- Obtener la lista de cursos que dicta un profesor determinado.
- Obtener la lista de alumnos aprobados dada una materia determinada.
- Obtener la lista de cursos aprobados de un alumno determinado.

### API ENDPOINTS

## Profesores: /api/teachers
**Obtener la lista de todos los profesores:** 
```
curl -X GET "http://localhost:8080/api/teachers" -H "accept: */*"
```

**Otener profesor por id:**
```
curl -X GET "http://localhost:8080/api/teachers/1" -H "accept: */*"
```

**Crear un profesor:**
```
curl -v -d '{ "name":"Jorge", "lastName":"Perello"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/teachers
```

**Eliminar un profesor:**
```
curl -X DELETE "http://localhost:8080/api/teachers/2" -H "accept: */*"
```

**Modificar un profesor:**
```
curl -X PUT "http://localhost:8080/api/teachers" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"address\": \"calle sin nombre 123\", \"birthday\": \"1985-03-25\", \"documentNumber\": 32555555, \"documentType\": \"DNI\", \"id\": 3, \"lastName\": \"Jorge\", \"name\": \"Perello\", \"phoneNumber\": 2664714160, \"sex\": \"male\"}"
```

**Obtener la lista de los titulos de un profesor:**
```
curl -X GET "http://localhost:8080/api/teachers/4/titles" -H "accept: */*"
```

**Obtener la lista de los cursos de un profesor:**
```
curl -X GET "http://localhost:8080/api/teachers/7/courses" -H "accept: */*"
```

## Titulos y capacitaciones docentes: /api/titles
**Obtener la lista de todos los titulos:** 
```
curl -X GET "http://localhost:8080/api/titles" -H "accept: */*"
```

**Crear un nuevo titulo:**
```
curl -v -d '{ "name":"Desarrollador de Software"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/titles
```

**Eliminar titulo por id:**
```
curl -X DELETE "http://localhost:8080/api/titles/2" -H "accept: */*"
```

**Relacionar titulo a un profesor:** ejemplo: title id 3, teacher id 2
```
curl -X POST "http://localhost:8080/api/titles/3" -H "accept: */*" -H "Content-Type: application/json" -d "2"
```


## Cursos: /api/courses

**Obtener la lista de todos los cursos:** 

```
curl -X GET "http://localhost:8080/api/courses" -H "accept: */*"
```

**Obtener curso por id:** 

```
curl -X GET "http://localhost:8080/api/courses/1" -H "accept: */*"
```

**Crear nuevo curso:** 

```
curl -X POST "http://localhost:8080/api/courses" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"approveGrade\": 8, \"description\": \"Capacitacion docente\", \"duration\": 36, \"name\": \"Introduccion web\"}"
```

**Eliminar curso por id:**

```
curl -X DELETE "http://localhost:8080/api/courses/2" -H "accept: */*"
```

**Modificar curso.:** 

```
curl -X PUT "http://localhost:8080/api/courses" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"approveGrade\": 7, \"description\": \"Curso de capacitacion integral\", \"duration\": 20, \"id\": 3, \"name\": \"Curso de capacitacion integral\"}"
```

**Obtener la lista de estudiantes de un curso determinado:**

```
curl -X GET "http://localhost:8080/api/courses/8/students" -H "accept: */*"
```

**Obtener la lista de estudiantes aprovados de un curso determinado:**

```
curl -X GET "http://localhost:8080/api/courses/8/approved" -H "accept: */*"
```

**Agregar profesor a un curso:**
```
curl -X POST "http://localhost:8080/api/courses/1" -H "accept: */*" -H "Content-Type: application/json" -d "2"
```

## Estudiantes: /api/students
**Obtener la lista de todos los estudiantes:** 
```
curl -X GET "http://localhost:8080/api/students" -H "accept: */*"
```

**Otener un estudiante por id:**
```
curl -X GET "http://localhost:8080/api/students/1" -H "accept: */*"
```

**Crear estudiante:**
```
curl -X POST "http://localhost:8080/api/students" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"address\": \"Calle nueva 123\", \"birthday\": \"1985-03-22\", \"documentNumber\": 54445520, \"documentType\": \"DNI\", \"lastName\": \"Perello\", \"name\": \"Jorge\", \"phoneNumber\": 2664714150, \"sex\": \"M\"}"
```

**Eliminar un estudiante:**
```
curl -X DELETE "http://localhost:8080/api/students/1" -H "accept: */*"
```

**Modificar un estudiante:**
```
curl -X PUT "http://localhost:8080/api/students" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"address\": \"Nueva Calle 123\", \"birthday\": \"2019-03-02\", \"documentNumber\": 59854444, \"documentType\": \"DNI\", \"id\": 1, \"lastName\": \"Alberto\", \"name\": \"Jhon\", \"phoneNumber\": 15521515, \"sex\": \"M\"}"
```

**Obtener la lista de los cursos de un estudiante:**
```
curl -X GET "http://localhost:8080/api/students/8/courses" -H "accept: */*"
```

**Obtener la lista de los cursos aprovados de un estudiante:**
```
curl -X GET "http://localhost:8080/api/students/8/approved" -H "accept: */*"
```
