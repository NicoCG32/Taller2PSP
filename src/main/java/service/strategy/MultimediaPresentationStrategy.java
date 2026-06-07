package service.strategy;

import model.Content;
import model.ContentResource;

/**
 * Estrategia que muestra la version completa del contenido educativo.
 */
public class MultimediaPresentationStrategy implements ContentPresentationStrategy {

    /**
     * Renderiza titulo, descripcion, imagen y recurso multimedia.
     */
    @Override
    public String render(Content content) {
        return """
                <!doctype html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>%s - Modo multimedia</title>
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
                            max-width: 1040px;
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
                            color: #47636f;
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
                            max-width: 680px;
                            margin: 12px 0 0;
                            color: #52616b;
                            font-size: 17px;
                            line-height: 1.55;
                        }

                        .mode {
                            flex: 0 0 auto;
                            border: 1px solid #27856f;
                            border-radius: 8px;
                            color: #12624f;
                            background: #e4f4ee;
                            padding: 8px 12px;
                            font-size: 13px;
                            font-weight: 700;
                        }

                        .grid {
                            display: grid;
                            grid-template-columns: minmax(0, 1.25fr) minmax(280px, 0.75fr);
                            gap: 18px;
                            margin-top: 24px;
                        }

                        .panel {
                            border: 1px solid #d8dee6;
                            border-radius: 8px;
                            background: #ffffff;
                            padding: 20px;
                        }

                        .panel-title {
                            margin: 0 0 12px;
                            font-size: 18px;
                        }

                        .text-block {
                            margin: 0;
                            line-height: 1.65;
                        }

                        .visual-block {
                            display: grid;
                            place-items: center;
                            min-height: 210px;
                            border: 1px solid #cfd8dc;
                            border-radius: 8px;
                            background: linear-gradient(135deg, #e7f2ed, #f5eddc);
                            color: #31444c;
                            text-align: center;
                            padding: 18px;
                            font-weight: 700;
                        }

                        .visual-block img {
                            max-width: 100%%;
                            height: auto;
                            border-radius: 6px;
                        }

                        .video-frame video {
                            display: block;
                            width: 100%%;
                            aspect-ratio: 16 / 9;
                            border: 1px solid #cfd8dc;
                            border-radius: 8px;
                            background: #111827;
                        }

                        .media-grid {
                            display: grid;
                            grid-template-columns: repeat(auto-fit, minmax(160px, 1fr));
                            gap: 12px;
                            margin-top: 16px;
                        }

                        .media-card {
                            border: 1px solid #d8dee6;
                            border-radius: 8px;
                            background: #ffffff;
                            padding: 10px;
                        }

                        .media-card img,
                        .media-card video {
                            display: block;
                            width: 100%%;
                            border-radius: 6px;
                            background: #f7faf9;
                        }

                        .media-card video {
                            aspect-ratio: 16 / 9;
                            background: #111827;
                        }

                        .media-card strong {
                            display: block;
                            margin: 8px 0 4px;
                            font-size: 14px;
                        }

                        .media-card span {
                            color: #52616b;
                            font-size: 13px;
                            line-height: 1.45;
                        }

                        .resource-list {
                            display: grid;
                            gap: 12px;
                            margin-top: 18px;
                        }

                        .resource {
                            border-left: 4px solid #27856f;
                            background: #f7faf9;
                            padding: 12px 14px;
                        }

                        .resource strong {
                            display: block;
                            margin-bottom: 4px;
                        }

                        @media (max-width: 760px) {
                            .header {
                                display: block;
                            }

                            .mode {
                                display: inline-block;
                                margin-top: 16px;
                            }

                            .grid {
                                grid-template-columns: 1fr;
                            }
                        }
                    </style>
                </head>
                <body>
                    <main class="page">
                        <header class="header">
                            <div>
                                <p class="eyebrow">Sistema autoadaptativo MAPE-K</p>
                                <h1>%s</h1>
                                <p class="lead">Bienvenido al curso. En condiciones normales se entrega el material completo, incluyendo texto, imagen, video y recurso complementario.</p>
                            </div>
                            <span class="mode">Modo MULTIMEDIA</span>
                        </header>

                        <section class="grid">
                            <article class="panel">
                                <h2 class="panel-title">Contenido principal</h2>
                                <p class="text-block">%s</p>
                            </article>

                            <aside class="panel">
                                <h2 class="panel-title">Material multimedia</h2>
                                <div class="visual-block">
                                    <img src="%s" alt="%s">
                                </div>
                                <div class="resource-list">
                                    <div class="resource">
                                        <strong>%s</strong>
                                        <div class="video-frame">
                                            <video controls preload="metadata" poster="%s">
                                                <source src="%s" type="video/mp4">
                                                Tu navegador no puede reproducir este video.
                                            </video>
                                        </div>
                                    </div>
                                    <div class="resource">
                                        <strong>Recurso complementario</strong>
                                        <span>%s</span>
                                    </div>
                                </div>
                            </aside>
                        </section>

                        <section class="panel" style="margin-top:18px;">
                            <h2 class="panel-title">Imágenes complementarias</h2>
                            <div class="media-grid">
                                %s
                            </div>
                        </section>

                        <section class="panel" style="margin-top:18px;">
                            <h2 class="panel-title">Videos complementarios</h2>
                            <div class="media-grid">
                                %s
                            </div>
                        </section>
                    </main>
                </body>
                </html>
                """.formatted(
                content.getTitle(),
                content.getTitle(),
                content.getDescription(),
                content.getImagePath(),
                content.getImageDescription(),
                content.getMainVideo().getTitle(),
                content.getImagePath(),
                content.getVideoPath(),
                content.getResourceLink(),
                renderImageCards(content),
                renderVideoCards(content)
        );
    }

    /**
     * Renderiza todas las imagenes complementarias en modo multimedia.
     */
    private String renderImageCards(Content content) {
        StringBuilder html = new StringBuilder();
        for (ContentResource image : content.getImages()) {
            html.append("""
                    <article class="media-card">
                        <img src="%s" alt="%s">
                        <strong>%s</strong>
                        <span>%s</span>
                    </article>
                    """.formatted(image.getPath(), image.getDescription(), image.getTitle(), image.getDescription()));
        }
        return html.toString();
    }

    /**
     * Renderiza todos los videos complementarios en modo multimedia.
     */
    private String renderVideoCards(Content content) {
        StringBuilder html = new StringBuilder();
        for (ContentResource video : content.getVideos()) {
            html.append("""
                    <article class="media-card">
                        <video controls preload="metadata" poster="%s">
                            <source src="%s" type="video/mp4">
                            Tu navegador no puede reproducir este video.
                        </video>
                        <strong>%s</strong>
                        <span>%s</span>
                    </article>
                    """.formatted(content.getImagePath(), video.getPath(), video.getTitle(), video.getDescription()));
        }
        return html.toString();
    }
}