# JavaEE
Ejemplos de Java Web EE desarrollados durante el curso Java Web EE.

Este proyecto se desarrollo usando NetBeans 8.2 & Java EE.

## Anotaciones

* Las anotaciones se colocan encima de lo que queremos anotar. El orden en el que yo defino mis anotaciones (@) no altera el resultado.
* La anotación `@Entity` hace que se reconozca la clase como una entidad.
* La anotación `@Id` hace que se reconozca determinado atributo como la llave primaria.
* La anotación `@Table(name="inserte_nombre")` permite asignar un nombre especifíco a la clase en el header de la DB.
* La notación `@Column(nullable=false, length = #)` permite modificar a lo largo de **atributos normales**.
  * En este ejemplo, el parámetro `length = #` permite delimitar el largo de este atributo.
  * El parámetro `nullable=false` indica que acá no se aceptan nulos, siempre tiene que haber algo. Sin embargo esta notación no realiza validaciones, sencillamente muestra cómo se relaciona el modelo de dominio con la DB. Para hacer validaciones, es decir que el cliente no ingrese un nulo, se requiere de otros frameworks.
* La notación `@JoinColumn` permite modifical a lo largo de **relaciones**.
* La anotación `@Temporal(TemporalType.DATE)` permite controlar qué tan especifíca se quiere una fecha.
  * Con .DATE sólo da la fecha (dd/mm/yyyy).
  * Con .TIME sólo da la hora.
  * Con .TIMESTAMP da la fecha y la hora.
* La notación `@Remove` indica a los EJB en estado statefull en qué método debe destruir el Bean. Se colocan en la interfaz. Para ver un ejemplo mirar [AdministracionOrdenLocal.java](https://github.com/dfzunigah/JavaEE/blob/master/TiendaVirtual/TiendaVirtual-ejb/src/java/logica/AdministracionOrdenLocal.java).
* Utilizando un EntityManager como moderador entre el modelo de dominio y el modelo relacional, es posible crear Queries a través de notaciones.
  * El tipo de Query implementado en este caso es @NamedQuery. Estos se componen básicamente de dos (2) partes: Un nombre y la Query que es escrita en JPQL (lo que lo hace portable de los motores de bases de datos).
  * El nombre debe ser **único** entre todas las queries de todo el programa.
  * Para ver un ejemplo de su definición, ver [Producto.java](https://github.com/dfzunigah/JavaEE/blob/master/TiendaVirtual/TiendaVirtual-ejb/src/java/entidades/Producto.java) o [Comprador.java](https://github.com/dfzunigah/JavaEE/blob/master/TiendaVirtual/TiendaVirtual-ejb/src/java/entidades/Comprador.java).
  * Para ver la implementación de las queries, ver los métodos consultarCompradores() o consultarProductos() en [AdministracionPersistenciaJPA.java](https://github.com/dfzunigah/JavaEE/blob/master/TiendaVirtual/TiendaVirtual-ejb/src/java/logica/AdministracionPersistenciaJPA.java).

## Relaciones
* El modelo relacional no maneja herencia, cosa que sí maneja el modelo de dominio. Por eso existen tres alternativas:
  * Esquema .SINGLE_TABLE: Sólo crea la clase padre a la cual le agrega un atributo discriminador y los atributos de sus hijos.
  * Esquema .TABLE_PER_CLASS que crea una clase para el padre y una para cada hijo en donde se repiten los atributos del padre.
  * Esquema .JOINED en donde crea una clase padre y una clase para cada hijo únicamente con sus atributos propios.
  
Para elegir qué tipo de esquema se quiere utilizar se implementa la notación a nivel de clase `@Inheritance(strategy = InheritanceType.JOINED)`.

En el caso de los esquemas .SINGLETABLE y .JOINED se crea automaticamente un atributo de discriminación que indica qué tipo de hijo es, que por defecto se llama DTYPE. Nosotros podemos cambiar ese default a través de la notación `@DiscriminatorColumn(name="TIPO_PERSONA", discriminatorType=DiscriminatorType.STRING, length=1)` en la cual indicamos que este atributo discriminador se va a llamar "TIPO_PERSONA", va a ser de tipo String y sólo va a tener una longitud de 1 (c-v). En nuestro caso "c" indica "Comprador" y "v" indica "Vendedor".

* Siempre que exista una relación bidireccional, siempre hay que establecer quién es el dueño de la relación.
  * Si esa relación es muchos a 1 `@ManyToOne`, el dueño de la relación es el lado muchos (Many).
  * Cuando la relación es muchos a muchos `@ManyToMany`, uno elige quién es el dueño de la relación. En cualquier caso una de las entidades debe llevar la etiqueta de dueño.
  * Por defecto todas las relaciones son opcionales, es decir, no se necesita colocar nada además del tipo de relación, por ejemplo `@ManyToOne` es una relación opcional. Si se quiere hacer que se vuelvan obligatorias entonces se utiliza la siguiente anotación `@Relationship(optional=false)`
  
## Cositas varias

* Lo ideal es que todas las entidades sean serializables, es decir que implementen la interfaz Serializable, de modo que se puedan guardar en disco secundario y de esta manera se pueda desplegar la aplicación en otras máquinas.
  * En caso de no hacer serializables las entidades igual el proyecto funciona pero a nivel local.
* La estrategia para que un campo sea incremental secuencial es:

```sh
 @GeneratedValue(strategy=GenerationType.IDENTITY)
```
Otras opciones son .SEQUENCE, .TABLE y .AUTO. En este caso se está usando una DB de Java-Derby; sin embargo en otras DBs, para hacer que el campo sea incremental se requiere SEQUENCE, no .IDENTITY. Entonces funcionaría bien en SQLServer pero no en Oracle.

El .TABLE es portable entre cualquier DB pero puede ofrecer problemas de desempeño pues le toca ir a buscar los elementos cada vez.

El .AUTO selecciona alguno de los otros tres atributos en base al proveedor de persistencia, es necesario mirar la documentación de cada uno.
