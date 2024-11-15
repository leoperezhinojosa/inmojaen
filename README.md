**Proyecto final 2ºDAM - IES Vírgen del Carmen**
-
Gestión Inmobiliaria: INMOJAÉN
- 

<u>Características de frontend y backend del programa:</u>
- Codificación:
    - Lenguajes utilizados: `Java`, `JavaScript`, `SQL`, `HTML5` y `CSS`
    - Framework utilizado: `Spring-Boot`  
    - Motor de plantillas (frontend): `Thymeleaf`
    - Sistema de seguridad para Registro: `Spring-Security`
  
- Herramientas de desarrollo:
  - Entorno de desarrollo (IDE): `Visual Studio Code` 
  - Plataforma de contenedores: `Docker`
  - Gestor de bases de datos: `Adminer`

Documentación de la APP de Gestión Inmobiliaria INMOJAÉN
-

_Descripción del Proyecto_
-

Este proyecto consiste en una aplicación web de Inmobiliaria. 

<u>Dispone de **3 tipos de usuario:**</u>
- **Usuario ADMIN (Administrador):** Gestionan todos los usuarios y anuncios de la aplicación. Pueden añadir nuevos usuarios de cualquier tipo, incluyendo éste, y realizar operaciones de CRUD de cualquier tipo de usuario o anuncio, excepto eliminar usuarios o anuncios con información sensible, que sólo podrán ser desactivados.
- **Usuario PREMIUM - SELLER (Perfil de ventas):** Es un perfil de **pago**. Los vendedores pueden poner anuncios de venta y/o alquiler de inmuebles. Se plantea poner un pago de una pequeña tasa por anuncio. Una vez creado, pueden crear, modificar, o desactivar cualquier anuncio. Pueden borrar cualquier anuncio excepto los que ya hayan sido reservados o vendidos. También dispone de todas las opciones del usuario gratuito.
- **Usuario FREE - BUYER (Perfil de buscador para alquiler o compra):** Es un perfil **gratuito**. Los compradores pueden ver los anuncios activos de la aplicación, y pueden realizar búsquedas especificas dependiendo de la ciudad, zona, tipo de vivienda, etc., que les interese. Pueden guardar los anuncios que deseen como favoritos, y aquellos que hayan visto quedarán marcados para no repetirlos si no quieren, y no confundirse de anuncio. 

_Estructura del Proyecto_
-

El proyecto está dividido en varios módulos:

- src/main/java: Contiene el código fuente de la aplicación.
- src/main/resources: Contiene los recursos estáticos de la aplicación.
- src/main/test: Contiene las pruebas unitarias.
- src/main/stacks: Contiene los archivos de configuración de la aplicación.
  
_**Tecnologías utilizadas**_

<u>_Backend_</u>

El backend de esta aplicación estará desarrollado en `Spring Boot`, framework desarrollado para el trabajo con Java como lenguaje de programación. Es un entorno de desarrollo gratuito y de código abierto. Cuenta con características clave que han incrementado su popularidad y su uso por parte de desarrolladores, de las cuales destacan: 
- Permitir crear todo tipo de aplicaciones en el lado del back-end de forma independiente.
- Facilitar el trabajo con herramientas como Tomcat, Jetty o Undertow de forma directa, sin necesidad de implementar archivos específicos.
- Simplificar las dependencias para mejorar la configuración final del proyecto que se desarrolla con Spring Boot.
- Ser un framework que se configura de manera simple, compatible con bibliotecas de terceros.
- Facilitar la creación de listas, controles de estado y mejorar la configuración externa para el desarrollo de aplicaciones.
- No hay que generar código para los aspectos controlados por Spring Boot y no hay requisitos para la configuración XML.

Spring Boot facilita así la creación de todo tipo de aplicaciones basadas en él de manera independiente con el mínimo esfuerzo por parte de los desarrolladores, siendo una tecnología que facilita que los desarrolladores se centren solo en la parte de programación, sin necesidad de preocuparse por aspectos como la arquitectura.

Las características utilizadas en este proyecto son:

- **Spring MVC:** Módulo utilizado para el desarrollo de aplicaciones web y servicios Restful de forma óptima y fácil. Sigue el patrón de diseño Modelo-Vista-Controlador, es un patrón de arquitectura de software que, utilizando 3 componentes (Vistas, Models y Controladores) separa la lógica de la aplicación de la lógica de la vista en una aplicación.
- **Spring Data JPA:** API de persistencia desarrollada para la plataforma Java EE que se incluye en el estándar EJB3. Esta API trata de unificar la forma en que funcionan las utilidades que proveen un mapeo objeto-relacional.
- **Spring Boot Devtools:** Módulo de herramientas de desarrollo Spring Boot. Incluye múltiples funciones útiles para desarrolladores, mejorando la experiencia de desarrollo: almacenamiento en caché de recursos estáticos, reinicios automáticos, recarga en vivo, configuraciones globales y ejecución remota de aplicaciones.
- **Spring Security:** Es un marco de autenticación, centrado en proporcionar autenticación y autorización a las aplicaciones Java, proporcionando control de acceso potente y altamente personalizable . Es el estándar de facto para proteger las aplicaciones basadas en Spring.

El backend de la aplicación se encarga de exponer una API REST que permite realizar las siguientes operaciones: 
- Registro de usuarios con nombre de usuario único
- Inicio de sesión con seguridad encriptada
- Muestra de anuncios en general 
- Búsqueda de anuncios según los parámetros que se desee (precio, localización, nº de habitaciones, nº de baños, etc.)
- Marcar/desmarcar anuncios como 'favoritos' o 'no deseados' según el interés del perfíl de comprador
- Operaciones CRUD de sus propios anuncios para el perfíl de vendedor
- Operaciones CRUD de usuarios para el perfíl de administrador
- Operaciones CRUD de anuncios para el perfíl de administrador

_<u>Frontend</u>_

El frontend de esta aplicación estará desarrollado utilizando el motor de plantillas `Thymeleaf`, tecnología que permite la creación de plantillas de forma elegante y con un código bien formateado. Se adapta muy bien para trabajar en la capa **vista** del **MVC** de **aplicaciones web,** proporcionando una manera simple y fácil de introducir las plantillas HTML con el código Java en el lado del servidor. Lo consigue mediante:

- **Sintaxis amigable:** Tiene una sintaxis fácil de leer, similar a HTML.
- **Expresiones:** Permite utilizar expresiones en las plantillas para acceder a los datos y poder manipularlos. Éstas son similares a otras expresiones utilizadas en otros motores de plantillas, haciendo fácil trabajar con éstas.
- **Integración con Spring:** Su diseño está pensado para trabajar con el framework de Spring.
- **Procesamiento del lado del servidor:** Thymeleaf se ejecuta en el lado del servidor, es decir, accede alos datos y realiza operaciones antes de enviar la respuesta al cliente. Así se genera de forma dinámica el contenido de las páginas en función de los datos y la lógica de negocio.
- **Características adicionales:** Hacen que el desarrollo de aplicaciones web sea más productivo y eficaz: Son la Internacionalización, la manipulación de URL, la validación de formularios, la iteración de listas y la condicionalización de contenido.

El frontend de la aplicación se conecta con el backend mediante una API REST para las operaciones de búsqueda de anuncios, reserva de inmuebles, guardado de anuncios nuevos, etc.

Base de Datos
-

- **MySQL:** Sistema de gestión de base de datos relacional. Utiliza el lenguaje SQL.

El proyecto usa una base de datos MySQL que integra las tablas:

- `usuario`: Almacena la información sobre todos los usuarios registrados en la aplicación, de cualquiera de los 3 tipos disponibles.
- `anuncio`: Almacena la información sobre los anuncios que se crean en la aplicación, pertenecientes al perfil de vendedor.
- `foto`: Almacena la información sobre las fotografías insertadas en cada anuncio.
- `mensaje`: Almacena los mensajes enviados entre los perfiles de vendedor y comprador.
- `rol`: Almacena los tipos de rol de la aplicación. Además de los 3 tipos, se plantea un 4º tipo de rol: **invitado** (perfil no registrado).


_Casos de Uso_
-


**Administrador (ADMIN):**

1. **Gestión de usuarios:** Creación, lectura, modificación y borrado/desactivado de cualquier usuario registrado.
2. **Gestión de anuncios:** Creación, lectura, modificación y borrado/desactivado de cualquier anuncio publicado.
   
**Usuario PREMIUM - SELLER (Perfil de ventas):**

1. **Gestionar perfil de venta:** Creación, lectura, modificación y borrado/desactivado del perfil del usuario registrado.
2. **Publicar anuncios nuevos:** Publicación de anuncio con todas sus características asociadas.
3. **Gestionar anuncios publicados:** Creación, lectura, modificación y borrado/desactivado de cualquier anuncio del usuario registrado.
4. **Enviar mensajes a compradores:** Envío de mensaje privado al usuario que se haya puesto en contacto con el usuario registrado.
5. **Aceptar reserva de venta/alquiler:** Responde a la petición de reserva de un usuario **Comprador**. Cambia el anuncio a estado **RESERVADO**.
6. **Rechazar reserva de venta/alquiler:** Responde a la petición de reserva de un usuario **Comprador**. Envía un **mensaje de aviso de reserva rechazada.**
7. **Finalizar reserva de venta/alquiler:** Finaliza el anuncio **mostrando el mensaje 'VENDIDO'. Los anuncios finalizados** no pueden ser eliminados totalmente, **sólo pueden ser desactivados.**

**Usuario FREE - BUYER (Perfil de buscador para alquiler o compra):**

1. **Gestionar su información:** Creación, lectura, modificación y borrado/desactivado del perfil del usuario registrado y de sus anuncios guardados como favoritos.
2. **Buscar anuncios:** Búsqueda de anuncios de forma global o por parámetros.
3. **Gestionar anuncios favoritos:** Activar o desactivar sus anuncios según al usuario le interese o no guardarlos.
4. **Enviar mensajes a vendedores:** Envío de mensaje privado al usuario que se haya puesto en contacto con el usuario registrado.
5. **Solicitar reserva a compradores:** Envío de solicitud al vendedor pertinente para que no haga negocios con otro usuario y se reserve al comprador solicitante. **Este estado puede revertirse.**

**Usuario sin registrar:**

1. **Registro:** Registrar un usuario nuevo, bien como **Usuario PREMIUM - SELLER (Perfil de ventas)** o como **Usuario FREE - BUYER (Perfil de buscador para alquiler o compra).**
2. **Login:** Inicio de sesión del usuario para cargar el tipo de perfíl que posea.

Descripción
-

1. **Creación de anuncio**
    - _Descripción:_ El usuario vendedor puede crear un anuncio que aparecerá en la aplicación y mostrará todos los datos que el vendedor registre: Ubicación, tipo de operación (venta o alquiler), precio, tipo de vivienda, nº de habitaciones, nº de baños, etc.
    - _Implementación:_ Se desarrolla un endpoint en el backend que acepte una vista de formulario que, una vez comprobada (parámetros obligatorios) registre el nuevo anuncio en la aplicación.
2. **Busqueda de anuncios**
    - _Descripción:_ El usuario comprador puede realizar búsquedas de viviendas utilizando diferentes criterios como ciudad, tipo de operación (venta o alquiler), precio aproximado, nº de habitaciones, nº de baños, etc.
    - _Implementación:_ Se desarrolla un endpoint en el backend que acepte los parámetros buscados y consulte la base de datos, recuperando los anuncios que coincidan con los criterios que se busquen.
3. **Gestión de mensajería**
    - _Descripción:_ Cada usuario vendedor puede interactuar con otro usuario comprador (y viceversa) de forma privada mediante un sistema de mensajería de uno a uno dentro de la aplicación. Los mensajes aparecerán de forma ordenada tal y como se vayan enviando de uno al otro. Se desarrolla un sistema de mensajes de forma que señale los del usuario registrado a la derecha y los del usuario que haya enviado el mensaje a la izquierda (El diseño previo es similar al de Apps como la conocida _WhatsApp_). Asimismo, en la pantalla anterior se verá el listado de mensajes de todos los usuarios con quien se tenga comunicación.
    - _Implementación:_ Se utilizará un método para imprimir los mensajes por fecha en orden ascendente. Asimismo, según sea el usuario registrado, se imprimirán los mensajes en la posición derecha o izquierda.
4. **Administración de usuarios**
    - _Descripción:_ Cada administrador del sistema puede leer, añadir, editar y eliminar información sobre cada usuario (**excepciones:** Los mensajes sólo se pueden leer y desactivar, y los usuarios no pueden eliminarse, sólo desactivarse).
    - _Implementación:_ Se implementan endpoints en el backend para permitir a los administradores realizar las operaciones CRUD al conjunto de usuarios, con las excepciones señaladas en el punto anterior.
5. **Administración de anuncios**
    - _Descripción:_ Cada administrador del sistema puede leer, añadir, editar y eliminar información sobre cada anuncio (**excepciones:** Los anuncios con reservas en cualquier estado no pueden eliminarse, sólo desactivarse).
    - _Implementación:_ Se implementan endpoints en el backend para permitir a los administradores realizar las operaciones CRUD conjunto de anuncios, con las excepciones señaladas en el punto anterior.
6. **Registro**
    - _Descripción:_ Cualquier usuario debe registrarse en la aplicación proporcionando información básica: nombre, dirección de correo electrónico y contraseña serán los parámetros mínimos para hacerlo. **Debe escoger** si será un tipo de usuario **vendedor** (SELLER - de pago) o **comprador** (BUYER - gratuito). **El registro de tipo Administrador (ADMIN) no está contemplado aquí.**
    - _Implementación:_ Se desarrolla un endpoint en el backend que acepte una vista de formulario que, una vez comprobada (parámetros obligatorios) registre el nuevo usuario en la aplicación.
7. **Autenticación y Autorización**
    - _Descripción:_ El sistema proporciona mecanismos de autenticación y autorización que protegen los endpoints y con ellos los recursos, para asegurar que sólo los usuarios registrados y autorizados pueden acceder a ellos.
    - _Implementación:_ Uso de Spring Security para gestionar la autenticación y autorización de cada usuario, usando a su vez tokens de acceso JWT (JSON Web Tokens) para autenticar las solicitudes.

Configuración
-

La configuración de esta aplicación se encuentra situada en el archivo `application.properties`, situado en la carpeta `src/main/resources`. En este archivo se pueden configurar las propiedades de la aplicación (Puerto del servidor, URL de la base de datos, tipo de guardado de datos, codificación, etc.).

Ejecución del programa
-

Para ejecutar la aplicación, se señalan 2 formas:
    
- A: Usar el comando `mvn spring-boot:run` desde la raíz del proyecto.
- B: Desde el IDE **Visual Studio Code,** con la extensión **Spring Boot Dashboard,** ejecutar el comando `Run`.

En ambas formas, la aplicación del proyecto estará disponible en `http://localhost:8080`.

Autor
-

Este proyecto será realizado por el Alumno: **Leo Pérez Hinojosa**

Fecha de realización
-

Agosto - Diciembre de 2024

_**¡Gracias por su atención!**_ 
-

Esperamos que el programa sea de su agrado.