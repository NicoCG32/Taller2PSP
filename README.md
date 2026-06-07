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

_Proyecto académico — enfoque en patrones de software y arquitectura de sistemas autoadaptativos._