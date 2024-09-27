-- Archivo de Datos predeterminados de la aplicación

-- Inserciones de roles:
INSERT INTO `rol` (`rol`) VALUES ('ADMIN');
INSERT INTO `rol` (`rol`) VALUES ('SELLER');
INSERT INTO `rol` (`rol`) VALUES ('BUYER');

-- Inserciones de usuario:
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`) VALUES ('admin', 'Administrador', 'leo@sincorreo.com', 'admin', 1, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 1, 0);
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`) VALUES ('vendedor', 'Vendedor', 'maria@sincorreo.com', 'vendedor', 2, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 1);
INSERT INTO `usuario` (`nombre`, `apellidos`, `email`, `username`, `rol_id`, `password`, `enabled`, `inalterable`, `premium`) VALUES ('cliente', 'Cliente', 'pepe@sincorreo.com', 'cliente', 3, '$2a$10$JdTDI22BygL/kxl4jR4zGeeLAhU2xafTlZrtfWki/4xm5TXg5qc.q', 1, 0, 0);