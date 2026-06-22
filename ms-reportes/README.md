# ms-reportes — Microservicio de Reportes

Microservicio encargado de **generar y almacenar reportes** a partir de eventos recibidos mediante Apache Kafka. Se integra con `ms-ventas` y `ms-inventario` a través de topics Kafka.

## Tecnologías

- Java 17
- Spring Boot 4.0.6
- Spring Kafka (consumer)
- Spring Data JPA
- MySQL
- Lombok

## Requisitos previos

- Java 17+
- Maven 3.8+
- MySQL corriendo en `localhost:3306`
- Apache Kafka corriendo en `localhost:9092`

## Levantar Kafka con Docker (rápido)

```bash
docker run -d --name kafka \
  -p 9092:9092 \
  -e KAFKA_CFG_NODE_ID=0 \
  -e KAFKA_CFG_PROCESS_ROLES=controller,broker \
  -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093 \
  -e KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
  -e KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT \
  -e KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=0@localhost:9093 \
  -e KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER \
  bitnami/kafka:latest
```

## Configuración

Edita `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/reportes_db
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD
spring.kafka.bootstrap-servers=localhost:9092
```

## Ejecutar

```bash
mvn spring-boot:run
```

El servicio inicia en el puerto **8083**.

## Topics Kafka que escucha

| Topic | Descripción |
|---|---|
| `ventas-events` | Eventos de nuevas ventas |
| `inventario-events` | Movimientos de inventario |

## Endpoints REST

| Método | URL | Descripción |
|---|---|---|
| GET | `/api/reportes` | Listar todos los reportes |
| GET | `/api/reportes/{id}` | Buscar reporte por ID |
| GET | `/api/reportes/tipo/{tipo}` | Filtrar por tipo (VENTA, INVENTARIO) |
| DELETE | `/api/reportes/{id}` | Eliminar un reporte |

## Patrón de diseño aplicado

- **Observer / Event-Driven**: el `ReporteConsumer` reacciona a eventos publicados en Kafka sin acoplamiento directo con los otros microservicios.
- **Repository Pattern**: acceso a datos desacoplado mediante `ReporteRepository`.
- **Service Layer**: lógica de negocio separada del controller y el consumer.
