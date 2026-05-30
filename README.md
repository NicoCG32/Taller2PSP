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

---

## Estructura de carpetas

```
Taller2PSP/
├─ pom.xml
├─ README.md
├─ ref/
│  └─ Taller.pdf
└─ src/
	└─ main/
		└─ java/
			├─ app/
			│  └─ Main.java
			├─ knowledge/
			│  ├─ AdaptationConfig.java
			│  ├─ KnowledgeBase.java
			│  └─ SystemState.java
			├─ mape/
			│  ├─ Analyzer.java
			│  ├─ Executor.java
			│  ├─ Monitor.java
			│  └─ Planner.java
			├─ model/
			│  └─ Content.java
			├─ service/
			│  └─ ContentService.java
			└─ web/
				├─ ContentController.java
				└─ WebServer.java
```

_Proyecto académico — enfoque en patrones de software y arquitectura de sistemas autoadaptativos._