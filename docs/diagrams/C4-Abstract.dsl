workspace "Taller2PSP" "Modelo C4 abstracto del sistema MAPE-K de gestion de contenidos" {

	model {
		usuario = person "Usuario final" "Consume contenido educativo desde un navegador web."
		carga = softwareSystem "Herramienta de carga" "Genera trafico HTTP para evaluar la autoadaptacion del sistema."

		sistema = softwareSystem "Sistema MAPE-K de gestion de contenidos" "Ajusta automaticamente el modo de presentacion segun la demanda." {
			web = container "Aplicacion web" "Servidor web en Javalin que expone los endpoints y ejecuta la adaptacion." "Java 17, Javalin"
		}

		usuario -> sistema "Consulta contenido adaptativo" "HTTP"
		carga -> sistema "Estresa el sistema para provocar cambios de modo" "HTTP"
	}

	views {
		systemContext sistema {
			include usuario
			include carga
			include sistema
			autolayout lr
		}

		container sistema {
			include usuario
			include carga
			include web
			autolayout lr
		}

		styles {
			element "Person" {
				shape Person
				background "#2A6F97"
				color "#FFFFFF"
			}

			element "Software System" {
				background "#0B3954"
				color "#FFFFFF"
			}

			element "Container" {
				background "#D9EAF4"
				color "#102A43"
			}
		}
	}
}