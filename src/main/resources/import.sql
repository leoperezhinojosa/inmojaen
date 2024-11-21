-- Archivo de Datos predeterminados de la aplicación

-- Inserciones de roles:
INSERT INTO `rol` (`rol`) VALUES ('ADMIN');
INSERT INTO `rol` (`rol`) VALUES ('SELLER');
INSERT INTO `rol` (`rol`) VALUES ('BUYER');

-- Inserciones de usuario:
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`) VALUES ('admin', 'Administrador', 'leo@sincorreo.com', 'admin', 1, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 1, 0);
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`) VALUES ('vendedor', 'Vendedor', 'maria@sincorreo.com', 'vendedor', 2, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 1);
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`) VALUES ('cliente', 'Cliente', 'pepe@sincorreo.com', 'cliente', 3, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 0);
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`) VALUES ('desactivado', 'Desactivado', 'jose@sincorreo.com', 'desactivado', 3, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 0, 0, 0);

-- Inserciones de anuncios:
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `usuario_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`) VALUES ('Anuncio de prueba 1', 'Descripcion de anuncio de prueba 1', 150000, '2022-02-28', 2, 1, 0, 0, 2, 4, 300);
INSERT INTO `anuncio` (`titulo`, `descripcion`, `precio`, `fecha_publicacion`, `usuario_id`, `activo`, `reservado`, `vendido`, `banos`, `habitaciones`, `superficie`) VALUES ('Anuncio de prueba 2', 'Descripcion de anuncio de prueba 2', 250000, '2022-02-28', 2, 1, 0, 0, 1, 2, 110);