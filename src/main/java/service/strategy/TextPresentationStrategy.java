package service.strategy;

import model.Content;

/**
 * Estrategia que muestra una version textual liviana durante alta demanda.
 */
public class TextPresentationStrategy implements ContentPresentationStrategy {

    /**
     * Renderiza solo el resumen y un aviso de desactivacion temporal de multimedia.
     */
    @Override
    public String render(Content content) {
        return """
                <!doctype html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>%s - Modo texto</title>
                    <style>
                        * {
                            box-sizing: border-box;
                        }

                        body {
                            margin: 0;
                            background: #f4f6f8;
                            color: #1f2933;
                            font-family: Arial, Helvetica, sans-serif;
                        }

                        .page {
                            max-width: 880px;
                            margin: 0 auto;
                            padding: 32px 20px;
                        }

                        .header {
                            display: flex;
                            justify-content: space-between;
                            gap: 20px;
                            align-items: flex-start;
                            border-bottom: 1px solid #d8dee6;
                            padding-bottom: 20px;
                        }

                        .eyebrow {
                            margin: 0 0 8px;
                            color: #8a2f2f;
                            font-size: 13px;
                            font-weight: 700;
                            text-transform: uppercase;
                        }

                        h1 {
                            margin: 0;
                            font-size: 32px;
                            line-height: 1.15;
                        }

                        .lead {
                            max-width: 640px;
                            margin: 12px 0 0;
                            color: #52616b;
                            font-size: 17px;
                            line-height: 1.55;
                        }

                        .mode {
                            flex: 0 0 auto;
                            border: 1px solid #b44c4c;
                            border-radius: 8px;
                            color: #7c2525;
                            background: #fde8e8;
                            padding: 8px 12px;
                            font-size: 13px;
                            font-weight: 700;
                        }

                        .panel {
                            border: 1px solid #d8dee6;
                            border-radius: 8px;
                            background: #ffffff;
                            margin-top: 24px;
                            padding: 20px;
                        }

                        .panel-title {
                            margin: 0 0 12px;
                            font-size: 18px;
                        }

                        .summary {
                            margin: 0;
                            font-size: 18px;
                            line-height: 1.65;
                        }

                        .notice {
                            border-left: 4px solid #b44c4c;
                            background: #fff4f4;
                            margin-top: 18px;
                            padding: 14px 16px;
                            line-height: 1.55;
                        }

                        .disabled-list {
                            display: grid;
                            gap: 10px;
                            margin: 18px 0 0;
                            padding: 0;
                            list-style: none;
                        }

                        .disabled-list li {
                            border: 1px solid #e5e7eb;
                            border-radius: 8px;
                            background: #f9fafb;
                            padding: 12px 14px;
                        }

                        @media (max-width: 680px) {
                            .header {
                                display: block;
                            }

                            .mode {
                                display: inline-block;
                                margin-top: 16px;
                            }
                        }
                    </style>
                </head>
                <body>
                    <main class="page">
                        <header class="header">
                            <div>
                                <p class="eyebrow">Alta demanda detectada</p>
                                <h1>%s</h1>
                                <p class="lead">El sistema entrega una versión textual mínima para priorizar estabilidad y reducir consumo de recursos.</p>
                            </div>
                            <span class="mode">Modo TEXT</span>
                        </header>

                        <section class="panel">
                            <h2 class="panel-title">Contenido resumido</h2>
                            <p class="summary">%s</p>

                            <div class="notice">
                                <strong>Adaptación aplicada:</strong>
                                Las imágenes, videos y enlaces multimedia fueron desactivados temporalmente debido a alta demanda.
                            </div>

                            <ul class="disabled-list" aria-label="Recursos desactivados">
                                <li>Imagen principal no cargada: %s</li>
                                <li>Video principal no cargado: %s</li>
                                <li>Imágenes complementarias no cargadas: %d</li>
                                <li>Videos complementarios no cargados: %d</li>
                                <li>Recurso complementario no cargado: %s</li>
                            </ul>
                        </section>
                    </main>
                </body>
                </html>
                """.formatted(
                content.getTitle(),
                content.getTitle(),
                content.getSummary(),
                content.getImageDescription(),
                content.getVideoDescription(),
                content.getImages().size(),
                content.getVideos().size(),
                content.getResourceLink()
        );
    }
}