package service.strategy;

import model.Content;
import model.ContentResource;

/**
 * Estrategia que conserva texto e imagen, pero desactiva recursos multimedia pesados.
 */
public class RestrictedPresentationStrategy implements ContentPresentationStrategy {

    /**
     * Renderiza una version intermedia del contenido para demanda media.
     */
    @Override
    public String render(Content content) {
        return """
                <!doctype html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>%s - Modo restringido</title>
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
                            color: #6b5b2f;
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
                            border: 1px solid #b58a2a;
                            border-radius: 8px;
                            color: #6b4d11;
                            background: #fff4d7;
                            padding: 8px 12px;
                            font-size: 13px;
                            font-weight: 700;
                        }

                        .grid {
                            display: grid;
                            grid-template-columns: minmax(0, 1fr) minmax(280px, 0.9fr);
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

                        .image-frame {
                            border: 1px solid #cfd8dc;
                            border-radius: 8px;
                            background: #f7faf9;
                            padding: 12px;
                        }

                        .image-frame img {
                            display: block;
                            max-width: 100%%;
                            height: auto;
                            border-radius: 6px;
                        }

                        .image-list {
                            display: grid;
                            grid-template-columns: repeat(auto-fit, minmax(140px, 1fr));
                            gap: 10px;
                            margin-top: 14px;
                        }

                        .image-list article {
                            border: 1px solid #d8dee6;
                            border-radius: 8px;
                            background: #ffffff;
                            padding: 8px;
                        }

                        .image-list img {
                            display: block;
                            width: 100%%;
                            border-radius: 6px;
                        }

                        .image-list strong {
                            display: block;
                            margin-top: 8px;
                            font-size: 13px;
                        }

                        .disabled-resource {
                            margin-top: 16px;
                            border-left: 4px solid #b58a2a;
                            background: #fff9ed;
                            padding: 12px 14px;
                        }

                        .disabled-resource strong {
                            display: block;
                            margin-bottom: 4px;
                        }

                        code {
                            color: #6b4d11;
                            font-family: Consolas, Monaco, monospace;
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
                                <p class="eyebrow">Ahorro de recursos activo</p>
                                <h1>%s</h1>
                                <p class="lead">La demanda del sistema aumentó. Se conserva el texto y la imagen principal, pero los recursos multimedia pesados quedan desactivados.</p>
                            </div>
                            <span class="mode">Modo RESTRICTED</span>
                        </header>

                        <section class="grid">
                            <article class="panel">
                                <h2 class="panel-title">Contenido disponible</h2>
                                <p class="text-block">%s</p>
                            </article>

                            <aside class="panel">
                                <h2 class="panel-title">Imagen disponible</h2>
                                <div class="image-frame">
                                    <img src="%s" alt="%s">
                                </div>
                                <div class="image-list">
                                    %s
                                </div>
                                <div class="disabled-resource">
                                    <strong>Video desactivado temporalmente</strong>
                                    <span>%s y %d videos complementarios no se cargan en este modo para reducir consumo de recursos.</span>
                                </div>
                            </aside>
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
                renderImageCards(content),
                content.getVideoDescription(),
                content.getVideos().size()
        );
    }

    /**
     * Renderiza imagenes complementarias, conservando desactivados los videos.
     */
    private String renderImageCards(Content content) {
        StringBuilder html = new StringBuilder();
        for (ContentResource image : content.getImages()) {
            html.append("""
                    <article>
                        <img src="%s" alt="%s">
                        <strong>%s</strong>
                    </article>
                    """.formatted(image.getPath(), image.getDescription(), image.getTitle()));
        }
        return html.toString();
    }
}