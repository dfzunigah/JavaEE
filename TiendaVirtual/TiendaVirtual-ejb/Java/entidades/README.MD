* Las anotaciones se colocan encima de lo que queremos anotar. El orden en el que yo defino mis anotaciones (@) no altera el resultado.

* La anotación `@Entity` hace que se reconozca la clase como una entidad.

* Siempre que exista una relación bidireccional, siempre hay que establecer quién es el dueño de la relación. Si esa relación es muchos a 1 (ManyToOne), el dueño de la relación es el lado muchos.

* La estrategia para que un campo sea incremental secuencial es:

```sh
 @GeneratedValue(strategy=GenerationType.IDENTITY)
```
Otras opciones son .SEQUENCE, .TABLE y .AUTO. En este caso se está usando una DB de Java-Derby; sin embargo en otras DBs, para hacer que el campo sea incremental se requiere SEQUENCE, no .IDENTITY. Entonces funcionaría bien en SQLServer pero no en Oracle.

El .TABLE es portable entre cualquier DB pero puede ofrecer problemas de desempeño pues le toca ir a buscar los elementos cada vez.

El .AUTO selecciona alguno de los otros tres atributos en base al proveedor de persistencia, es necesario mirar la documentación de cada uno.
