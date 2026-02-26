# Principios SOLID

SOLID es un conjunto de cinco principios de diseño de software orientados a la programación orientada a objetos, aunque sus ideas pueden aplicarse de forma equivalente a otros paradigmas. Estos principios permiten que nuestro código sea más legible, fácil de mantener y escalable. SOLID es un acrónimo donde cada letra representa un principio específico: S (Single Responsibility), O (Open/Closed), L (Liskov Substitution), I (Interface Segregation) y D (Dependency Inversion).

## S - Single Responsibility Principle (Principio de Responsabilidad Única)
Este principio establece que las clases, métodos, módulos, etc., deben tener una única razón para cambiar, es decir, cumplir una sola función o responsabilidad. Se aplica, por ejemplo, cuando una clase tiene muchos métodos o cuando un método realiza varias tareas muy distintas. Escribir código de esta manera puede provocar que al cambiar una funcionalidad se afecten otras sin querer. Seguir este principio ayuda a encontrar errores más fácilmente, evita que los cambios rompan otras partes del programa y hace que las pruebas unitarias sean más sencillas de realizar.

## O - Open/Closed Principle (Principio de Abierto/Cerrado)
Este principio establece que el software debe estar abierto a la extensión pero cerrado a la modificación. Es decir, si se quiere agregar una nueva funcionalidad, no es necesario cambiar el código que ya funciona; basta con añadir código que se integre al existente. Por ejemplo, si una clase tiene un método con muchas condiciones para validar datos, esto puede generar estructuras complejas y difíciles de mantener. Para solucionarlo, se pueden crear nuevas clases que manejen cada validación por separado. Aplicar este principio ayuda a proteger el código que ya funciona y facilita que el software sea más escalable.

## L - Liskov Substitution Principle (Principio de Sustitución de Liskov)
Este principio dice que las subclases o implementaciones deben poder reemplazar a sus clases base o interfaces sin cambiar el comportamiento del programa. Si una clase sigue un contrato, no debe romperlo, de manera que al sustituirla por otra, por ejemplo un servicio de prueba, el sistema siga funcionando igual. Se rompe cuando la implementación devuelve tipos distintos a los esperados, faltan métodos o propiedades, o al cambiar la clase el flujo de la aplicación se rompe. Cumplirlo hace que el código sea más confiable y fácil de probar.

## I - Interface Segregation Principle (Principio de Segregación de Interfaces)
Este principio dice que las clases, módulos o componentes no deben depender de interfaces o contratos que no usan, es decir, no deben estar obligadas a implementar métodos o funcionalidades que no van a usar o que no tienen nada que ver con su contexto. Es mejor tener varias interfaces pequeñas y específicas que una interfaz muy grande. Se rompe cuando se tienen interfaces con muchos métodos que las clases terminan dejando vacíos, por ejemplo. Cumplirlo hace que el código sea más limpio, claro y fácil de mantener porque elimina código innecesario.

## D - Dependency Inversion Principle (Principio de Inversión de Dependencias)
Este principio establece que las clases, métodos o módulos no deben depender directamente de otras clases específicas. Se aplica, por ejemplo, cuando una clase crea otra dentro de sí misma, lo que hace que estén muy ligadas. Seguir este principio ayuda a que sea más fácil reemplazar una clase por otra, probar el código y modificarlo sin romper lo que ya funciona.
