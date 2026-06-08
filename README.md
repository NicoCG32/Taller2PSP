# Taller PSP — Sistema MAPE‑K para gestión de contenidos

**Docente:**
Daniel San Martín Santibáñez

**Curso:**
Patrones de Software y Programación - Universidad Católica del Norte

**Integrantes:**
- Pablo Guzmán (Ingeniería Civil en Computación e Informática)
- Rodrigo Cortés (Ingeniería Civil en Computación e Informática)

## Descripción del proyecto

Implementación de un sistema MAPE‑K (Monitor, Analyzer, Planner, Executor y Knowledge) para la adaptación dinámica de contenidos en una aplicación web.

Este proyecto organiza los componentes en capas: módulos de monitorización, análisis, planificación y ejecución, junto con una base de conocimiento y un servicio web para exponer y gestionar contenidos de forma adaptable.

## Compilación y ejecución

Para compilar el proyecto:

```bash
mvn clean compile
```

Para ejecutar la aplicación:

```bash
mvn exec:java
```

Una vez iniciada, la interfaz puede visualizarse en:

```text
http://localhost:7000/
```

La ruta recomendada para acceder directamente al contenido es:

```text
http://localhost:7000/content
```

### Pruebas rápidas (curl)

Una vez iniciado el servidor, puedes probar los endpoints básicos con `curl`:

```bash
# Obtener la vista adaptada (ejecuta el ciclo MAPE-K)
curl -i http://localhost:7000/content

# Consultar estado actual (JSON)
curl -s http://localhost:7000/status | jq

# Obtener metadatos de recursos multimedia
curl -s http://localhost:7000/resources | jq

# Reiniciar la simulación
curl -i http://localhost:7000/reset
```

---

## Documentación técnica con Doxygen

El proyecto incluye una configuración de Doxygen en:

```text
docs/doxygen/Doxyfile
```

Para generar la documentación técnica del código:

```bash
doxygen docs/doxygen/Doxyfile
```

La documentación HTML se genera en:

```text
docs/doxygen/generated/html/index.html
```

En PowerShell, puede abrirse con:

```powershell
Start-Process .\docs\doxygen\generated\html\index.html
```

Es necesario tener Doxygen instalado y disponible en el `PATH` del sistema.

---

## Estructura de carpetas

```
Taller2PSP/
├─ config.properties
├─ docs/
│  ├─ diagrams/
│  └─ doxygen/
│     └─ Doxyfile
├─ pom.xml
├─ README.md
├─ ref/
│  └─ Taller.pdf
└─ src/
	└─ main/
		├─ java/
		│  ├─ app/
		│  │  └─ Main.java
		│  ├─ knowledge/
		│  │  ├─ AdaptationConfig.java
		│  │  ├─ KnowledgeBase.java
		│  │  └─ SystemState.java
		│  ├─ mape/
		│  │  ├─ Analyzer.java
		│  │  ├─ Executor.java
		│  │  ├─ MapeCycleFacade.java
		│  │  ├─ Monitor.java
		│  │  └─ Planner.java
		│  ├─ model/
		│  │  ├─ Content.java
		│  │  ├─ ContentResource.java
		│  │  ├─ DemandLevel.java
		│  │  └─ PresentationMode.java
		│  ├─ service/
		│  │  ├─ ContentService.java
		│  │  └─ strategy/
		│  │     ├─ ContentPresentationStrategy.java
		│  │     ├─ MultimediaPresentationStrategy.java
		│  │     ├─ RestrictedPresentationStrategy.java
		│  │     └─ TextPresentationStrategy.java
		│  └─ web/
		│     ├─ ContentController.java
		│     └─ WebServer.java
		└─ resources/
			├─ images/
			└─ videos/
				
```

## Descripción por paquetes

Breve guía de qué contiene cada paquete en `src/main/java` y cómo se relaciona con los demás:

- `app`:
	- Contiene `Main.java` — punto de entrada y orquestador de instancias. Crea y conecta `KnowledgeBase`, componentes MAPE-K, `ContentService` y `WebServer`.
	- Relación: inicia toda la aplicación y ensambla las dependencias.

- `knowledge`:
	- Contiene `KnowledgeBase`, `SystemState` y `AdaptationConfig`.
	- Responsabilidad: almacenar estado dinámico (contador de solicitudes y modo activo) y parámetros de adaptación (umbrales). Es accesible desde Monitor, Analyzer y Executor.

- `mape`:
	- Componentes `Monitor`, `Analyzer`, `Planner`, `Executor` y la fachada `MapeCycleFacade`.
	- Responsabilidad: implementar el ciclo MAPE-K; `Monitor` registra solicitudes en `KnowledgeBase`, `Analyzer` clasifica la demanda, `Planner` decide el `PresentationMode` y `Executor` aplica la adaptación en `KnowledgeBase`.
	- Relación: orquestado por `MapeCycleFacade`, invocado por `ContentController` en cada petición a `/content`.

- `model`:
	- Entidades de dominio: `Content`, `ContentResource`, `DemandLevel`, `PresentationMode`.
	- Responsabilidad: representar el contenido educativo y recursos multimedia; `Content` compone varios `ContentResource`.
	- Relación: consumido por `ContentService` y las estrategias de presentación.

- `service`:
	- `ContentService` y el paquete `service.strategy` con `ContentPresentationStrategy` e implementaciones (`MultimediaPresentationStrategy`, `RestrictedPresentationStrategy`, `TextPresentationStrategy`).
	- Responsabilidad: obtener el contenido, seleccionar la estrategia adecuada según `PresentationMode` y renderizar la salida HTML.
	- Relación: `ContentController` invoca `ContentService` para producir la respuesta al usuario.

- `web`:
	- `WebServer` y `ContentController`.
	- Responsabilidad: exponer el servidor HTTP (Javalin), registrar rutas y traducir solicitudes HTTP en llamadas a las capas del sistema.
	- Relación: punto de entrada HTTP — `ContentController` llama a `MapeCycleFacade` y `ContentService`.

- `resources` (carpeta de proyecto `resources/`):
	- Contiene imágenes y videos servidos de forma estática por `WebServer`.
	- Relación: referenciados por `ContentResource.path` y consumidos por las vistas generadas por las estrategias.

---



_Proyecto académico — enfoque en patrones de software y arquitectura de sistemas autoadaptativos._