-- Archivo de Datos predeterminados de la aplicación

-- Inserciones de roles:
INSERT INTO `rol` (`rol`) VALUES ('ADMIN');
INSERT INTO `rol` (`rol`) VALUES ('SELLER');
INSERT INTO `rol` (`rol`) VALUES ('BUYER');

-- Inserciones de usuario:
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`) VALUES ('admin',      'Administrador',  'leo@sincorreo.com',      'admin',    1, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 1, 0);
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`) VALUES ('vendedor',   'Vendedor',       'maria@sincorreo.com',    'vendedor', 2, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 1);
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`) VALUES ('cliente',    'Cliente',        'pepe@sincorreo.com',     'cliente',  3, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 0);
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`) VALUES ('Juan',       'Perez',          'juan@sincorreo.com',     'juanp',    3, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 0);
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`) VALUES ('Sofia',      'Gomez',          'sofia@sincorreo.com',    'sofig',    2, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 1);
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`) VALUES ('Miguel',     'Rodriguez',      'miguel@sincorreo.com',   'miguelr',  3, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 0);

-- Inserciones de anuncios:
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `usuario_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`) VALUES ('Apartamento de lujo', 'Apartamento centrico con piscina', 450000, '2022-02-28', 2, 1, 0, 0, 2, 4, 300, 'Calle Nueva, Jaen');
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `usuario_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`) VALUES ('Piso de oficina', 'Piso en el centro de la ciudad con vista al mar', 250000, '2022-02-28', 2, 1, 0, 0, 1, 2, 110, 'Calle Principal, Jaen');
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `usuario_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`) VALUES ('Casa en la playa', 'Hermosa casa con vista al mar', 300000, '2024-11-22', 3, 1, 0, 0, 3, 5, 200, 'Paseo de la Estacion, Jaen'); 
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `usuario_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`) VALUES ('Apartamento centrico', 'Apartamento en el corazon de la ciudad', 120000, '2024-11-23', 3, 1, 0, 0, 1, 2, 80, 'Avenida Principal, Jaen');
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `usuario_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`) VALUES ('Casa en las afueras', 'Casa en las afueras de la ciudad', 240000, '2024-11-25', 3, 1, 0, 0, 1, 2, 80, 'Avenida Secundaria, Jaen');

-- Inserciones de mensajes:
INSERT INTO `mensaje` (`contenido`, `fecha`, `hora`, `leido`, `enviado`, `recibido`, `borrado`, `emisor_id`, `receptor_id`, `bloqueado`) VALUES ('Hola, ¿estas interesado en mi anuncio?',      CAST('2024-11-22' AS DATE), CAST('14:30:00' AS TIME), 0, 1, 1, 0, 2, 3, 0);
INSERT INTO `mensaje` (`contenido`, `fecha`, `hora`, `leido`, `enviado`, `recibido`, `borrado`, `emisor_id`, `receptor_id`, `bloqueado`) VALUES ('Si, me interesa. ¿Cuando podriamos hablar?',  CAST('2024-11-22' AS DATE), CAST('15:00:00' AS TIME), 0, 1, 1, 0, 3, 2, 0);
INSERT INTO `mensaje` (`contenido`, `fecha`, `hora`, `leido`, `enviado`, `recibido`, `borrado`, `emisor_id`, `receptor_id`, `bloqueado`) VALUES ('¿Que dia podrias venir a verlo?',             CAST('2024-11-22' AS DATE), CAST('15:30:00' AS TIME), 0, 1, 1, 0, 2, 3, 0);