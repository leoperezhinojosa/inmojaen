-- Archivo de Datos predeterminados de la aplicación

-- Inserciones de roles:
INSERT INTO `rol` (`rol`) VALUES ('ADMIN');
INSERT INTO `rol` (`rol`) VALUES ('SELLER');
INSERT INTO `rol` (`rol`) VALUES ('BUYER');

-- Inserciones de usuario:
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`) VALUES ('admin',      'Administrador',  'leo@sincorreo.com',      'admin',    1, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 1, 0);
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`, `telefono`) VALUES ('vendedor',   'Vendedor',       'maria@sincorreo.com',    'vendedor', 2, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 1, '987654654');
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`, `telefono`) VALUES ('cliente',    'Cliente',        'pepe@sincorreo.com',     'cliente',  3, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 0, '879654456');
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`, `telefono`) VALUES ('Juan',       'Perez',          'juan@sincorreo.com',     'juanp',    3, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 0, '213132132');
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`, `telefono`) VALUES ('Sofia',      'Gomez',          'sofia@sincorreo.com',    'sofig',    2, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 1, '123456789');
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`, `telefono`) VALUES ('Miguel',     'Rodriguez',      'miguel@sincorreo.com',   'miguelr',  3, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 0, '654546545');

-- Inserciones de anuncios:
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `usuario_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Apartamento de lujo', 'Apartamento centrico con piscina', 450000, '2022-02-28', 2, 1, 0, 0, 2, 4, 300, 'Calle Nueva, Jaen', 0);
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `usuario_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Piso de oficina', 'Piso en el centro de la ciudad con vista al mar', 250000, '2022-02-28', 2, 1, 0, 0, 1, 2, 110, 'Calle Principal, Jaen', 0);
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `usuario_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Casa en la playa', 'Hermosa casa con vista al mar', 300000, '2024-11-22', 3, 1, 0, 0, 3, 5, 200, 'Paseo de la Estacion, Jaen', 0); 
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `usuario_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Apartamento centrico', 'Apartamento en el corazon de la ciudad', 120000, '2024-11-23', 3, 1, 0, 0, 1, 2, 80, 'Avenida Principal, Jaen', 0);
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `usuario_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Casa en las afueras', 'Casa en las afueras de la ciudad', 240000, '2024-11-25', 3, 1, 0, 0, 1, 2, 80, 'Avenida Secundaria, Jaen', 0);

-- Inserciones de mensajes:
INSERT INTO `mensaje` (`contenido`, `fecha`, `hora`, `leido`, `enviado`, `recibido`, `borrado`, `emisor_id`, `receptor_id`, `bloqueado`) VALUES ('Hola, ¿estas interesado en mi anuncio?',      CAST('2024-11-22' AS DATE), CAST('14:30:00' AS TIME), 0, 1, 1, 0, 2, 3, 0);
INSERT INTO `mensaje` (`contenido`, `fecha`, `hora`, `leido`, `enviado`, `recibido`, `borrado`, `emisor_id`, `receptor_id`, `bloqueado`) VALUES ('Si, me interesa. ¿Cuando podriamos hablar?',  CAST('2024-11-22' AS DATE), CAST('15:00:00' AS TIME), 0, 1, 1, 0, 3, 2, 0);
INSERT INTO `mensaje` (`contenido`, `fecha`, `hora`, `leido`, `enviado`, `recibido`, `borrado`, `emisor_id`, `receptor_id`, `bloqueado`) VALUES ('¿Que dia podrias venir a verlo?',             CAST('2024-11-22' AS DATE), CAST('15:30:00' AS TIME), 0, 1, 1, 0, 2, 3, 0);

-- Inserciones de mensajes enlazados a anuncios:
INSERT INTO `anuncio_mensajes` (`anuncio_id`, `mensajes_id`) VALUES (1, 1);
INSERT INTO `anuncio_mensajes` (`anuncio_id`, `mensajes_id`) VALUES (1, 2);

-- ToDo: Añadir el resto de mensajes enlazados a anuncios.
-- Inserciones de mensajes por emisor:
/* INSERT INTO `usuario_mensajes_by_emisor` (`usuario_id`, `mensaje_id`) VALUES (2, 1);
INSERT INTO `usuario_mensajes_by_emisor` (`usuario_id`, `mensaje_id`) VALUES (3, 2);
INSERT INTO `usuario_mensajes_by_emisor` (`usuario_id`, `mensaje_id`) VALUES (2, 3); */

-- Inserciones de mensajes por receptor:
/* INSERT INTO `usuario_mensajes_by_receptor` (`usuario_id`, `mensaje_id`) VALUES (3, 1);
INSERT INTO `usuario_mensajes_by_receptor` (`usuario_id`, `mensaje_id`) VALUES (2, 2);
INSERT INTO `usuario_mensajes_by_receptor` (`usuario_id`, `mensaje_id`) VALUES (3, 3); */

-- Inserciones de anuncios en venta:
INSERT INTO `usuario_anuncios_en_venta` (`anuncios_en_venta_id`, `usuario_id`) VALUES (1, 2);
INSERT INTO `usuario_anuncios_en_venta` (`anuncios_en_venta_id`, `usuario_id`) VALUES (2, 2);
INSERT INTO `usuario_anuncios_en_venta` (`anuncios_en_venta_id`, `usuario_id`) VALUES (3, 2);
INSERT INTO `usuario_anuncios_en_venta` (`anuncios_en_venta_id`, `usuario_id`) VALUES (4, 2);
INSERT INTO `usuario_anuncios_en_venta` (`anuncios_en_venta_id`, `usuario_id`) VALUES (5, 5);

-- Inserciones de anuncios favoritos:
INSERT INTO `usuario_anuncios_favoritos` (`anuncios_favoritos_id`, `usuario_id`) VALUES (1, 3);
INSERT INTO `usuario_anuncios_favoritos` (`anuncios_favoritos_id`, `usuario_id`) VALUES (2, 3);
INSERT INTO `usuario_anuncios_favoritos` (`anuncios_favoritos_id`, `usuario_id`) VALUES (3, 3);
/* INSERT INTO `usuario_anuncios_favoritos` (`anuncios_favoritos_id`, `usuario_id`) VALUES (4, 4);
INSERT INTO `usuario_anuncios_favoritos` (`anuncios_favoritos_id`, `usuario_id`) VALUES (5, 6); */

-- Inserciones de fotos:
INSERT INTO `foto` (`url`, `activo`, `principal`, `descripcion`) VALUES ('https://png.pngtree.com/background/20230708/original/pngtree-luxurious-modern-apartment-interior-in-3d-renderings-picture-image_4176419.jpg', 1, 1, 'salon');
INSERT INTO `foto` (`url`, `activo`, `principal`, `descripcion`) VALUES ('https://assets.easybroker.com/property_images/2937148/46671193/EB-KG7148.png?version=1651178992', 1, 0, 'terraza');
INSERT INTO `foto` (`url`, `activo`, `principal`, `descripcion`) VALUES ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTepbMAMK6DY8jjZnKgqk45IFHs8-DGmzAURA&s', 1, 1, 'porche');
INSERT INTO `foto` (`url`, `activo`, `principal`, `descripcion`) VALUES ('https://content.arquitecturaydiseno.es/medio/2022/03/24/casa-playa-malibu-cindy-crawford_48a698b2_2000x1333.jpg', 1, 0, 'salones');
INSERT INTO `foto` (`url`, `activo`, `principal`, `descripcion`) VALUES ('https://www.65ymas.com/uploads/s1/26/18/54/co-mo-mantener-tu-casa-de-la-playa-en-perfecto-estado-durante-el-invierno.jpeg', 1, 0, 'exterior');

-- Inserciones de fotos en anuncios:
INSERT INTO `anuncio_fotos` (`anuncio_id`, `fotos_id`) VALUES (1, 1);
INSERT INTO `anuncio_fotos` (`anuncio_id`, `fotos_id`) VALUES (1, 2);
INSERT INTO `anuncio_fotos` (`anuncio_id`, `fotos_id`) VALUES (3, 3);
INSERT INTO `anuncio_fotos` (`anuncio_id`, `fotos_id`) VALUES (3, 4);
INSERT INTO `anuncio_fotos` (`anuncio_id`, `fotos_id`) VALUES (3, 5);