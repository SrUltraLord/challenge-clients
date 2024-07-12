# Microservicio Clientes

El presente proyecto contiene la gestión de clientes.

# Tecnologías y librerías utilizadas

- Redis
- Postgres
- Java 17
- Lombok
- JPA
- Spring boot 3.3.1

# Ejecución del proyecto

Para la base de datos se requiere crear una base con el nombre `clients`, el esquema necesario
para el funcionamiento de la aplicación se encuentra en el fichero `BaseDatos.sql`.

Para iniciar la aplicación es necesario ejecutar el siguiente comando:

```bash
$ ./gradlew bootRun
```

Para la ejecución de las pruebas unitarias es necesario correr el siguiente comando:

```bash
$ ./gradlew test
```

# Autor

- David Reyes

