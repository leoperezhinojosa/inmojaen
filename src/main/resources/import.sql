-- Archivo de Datos predeterminados de la aplicacion

-- Inserciones de roles:
INSERT INTO `rol` (`rol`) VALUES ('ADMIN');
INSERT INTO `rol` (`rol`) VALUES ('USER');
INSERT INTO `rol` (`rol`) VALUES ('TEMPORAL');

-- Inserciones de usuario:
INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`, `telefono`) VALUES (1, 'admin',             'Administrador',  'leo@sincorreo.com',      'admin',    1, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 1, 0, '900123456');
INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`, `telefono`) VALUES (2, 'Santiago',          'Rodenas',        'santi@sincorreo.com',    'santi',    2, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 1, '987654654');
INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`, `telefono`) VALUES (3, 'Juan Gualberto',    'Fernandez',      'juangu@sincorreo.com',   'juangu',   2, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 0, '879654456');
INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`, `telefono`) VALUES (4  ,'Juan Jose',        'Serrano',        'juanjo@sincorreo.com',   'juanjo',   2, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 0, '213132132');
INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`, `telefono`) VALUES (5,  'Luis',             'Molina',         'luis@sincorreo.com',     'luis',     2, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 1, '123456789');
INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`, `telefono`) VALUES (6,  'Miguel',           'Rodriguez',      'miguel@sincorreo.com',   'miguel',   2, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 0, '654546545');

-- Inserciones de anuncios:
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `vendedor_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Apartamento de lujo',     'Apartamento centrico con piscina',                 450000, '2022-02-28', 2, 1, 0, 0, 2, 4, 300, 'Calle Nueva, Jaen', 0);
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `vendedor_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Piso de oficina',         'Piso en el centro de la ciudad con vista al mar',  250000, '2022-02-28', 2, 1, 0, 0, 1, 2, 110, 'Calle Principal, Jaen', 0);
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `vendedor_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Casa en la playa',        'Hermosa casa con vista al mar',                    300000, '2024-11-22', 3, 1, 0, 0, 3, 5, 200, 'Paseo de la Estacion, Jaen', 0); 
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `vendedor_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Apartamento centrico',    'Apartamento en el corazon de la ciudad',           120000, '2024-11-23', 3, 1, 0, 0, 1, 2, 60,  'Avenida De Juan Jimenez, Jaen', 0);
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `vendedor_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Piso a reformar',         'Piso a reformar con muchas posibilidades',         250000, '2024-11-25', 4, 1, 0, 0, 2, 2, 70,  'Avenida de Granada, Jaen', 0);
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `vendedor_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Chalet en urbanizacion',  'Estupendo chalet en urbanizacion con jardin',      210000, '2024-11-25', 5, 1, 0, 0, 3, 4, 180, 'Avenida de Andalucia, Jaen', 0);
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `vendedor_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Atico con vistas',        'Atico con inmmejorables vistas al monte',          140000, '2024-11-25', 5, 1, 0, 0, 2, 3, 120, 'Calle San Roque, Jaen', 0);
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `vendedor_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Piso obra nueva',         'Piso en urbanizacion de nueva construccion',       340000, '2024-11-25', 6, 1, 0, 0, 1, 3, 90,  'Calle Obispo Ceballos, Jaen', 0);
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `vendedor_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Atico duplex',            'Atico Duplex muy cerca de la estacion',            270000, '2024-11-25', 6, 1, 0, 0, 2, 3, 120, 'Avenida Ejerl montel, Jaen', 0);
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `vendedor_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`, `direccion`, `visto`) VALUES ('Casa en las afueras',     'Casa en las afueras de la ciudad',                 230000, '2024-11-25', 3, 1, 0, 0, 3, 4, 300, 'Calle Pescaderia, Jaen', 0);

-- Inserciones de imagenes:
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto1.jpg', 1, 1, 'salon');
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto2.jpg', 1, 0, 'terraza');
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto3.jpg', 1, 1, 'porche');
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto4.jpg', 1, 0, 'salones');
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto5.jpg', 1, 0, 'exterior');
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto6.jpg', 1, 0, 'salon');
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto7.jpg', 1, 0, 'exterior');
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto8.jpg', 1, 0, 'fachada');
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto9.jpg', 1, 0, 'cocina');
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto10.jpg', 1, 0, 'salon');
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto11.jpg', 1, 0, 'cocina');
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto12.jpg', 1, 0, 'salon');
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto13.jpg', 1, 0, 'exterior');
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto14.jpg', 1, 0, 'exterior');
INSERT INTO `imagen` (`url`, `activo`, `principal`, `descripcion`) VALUES ('uploads/foto15.jpg', 1, 0, 'interior');

-- Inserciones de anuncios favoritos:
INSERT INTO `favorito` (`anuncio_id`, `usuario_id`) VALUES (1, 2);
INSERT INTO `favorito` (`anuncio_id`, `usuario_id`) VALUES (2, 3);
INSERT INTO `favorito` (`anuncio_id`, `usuario_id`) VALUES (3, 3);
INSERT INTO `favorito` (`anuncio_id`, `usuario_id`) VALUES (3, 4);
INSERT INTO `favorito` (`anuncio_id`, `usuario_id`) VALUES (3, 5);
INSERT INTO `favorito` (`anuncio_id`, `usuario_id`) VALUES (6, 5);
INSERT INTO `favorito` (`anuncio_id`, `usuario_id`) VALUES (7, 3);
INSERT INTO `favorito` (`anuncio_id`, `usuario_id`) VALUES (7, 4);
INSERT INTO `favorito` (`anuncio_id`, `usuario_id`) VALUES (8, 5);
INSERT INTO `favorito` (`anuncio_id`, `usuario_id`) VALUES (2, 6);

-- Inserciones de imagenes en anuncios:
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (1, 1);
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (1, 2);
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (2, 3);
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (2, 4);
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (3, 5);
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (4, 6);
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (5, 7);
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (5, 8);
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (6, 9);
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (7, 10);
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (7, 11);
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (8, 12);
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (8, 13);
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (9, 14);
INSERT INTO `anuncio_imagenes` (`anuncio_id`, `imagenes_id`) VALUES (10, 15);