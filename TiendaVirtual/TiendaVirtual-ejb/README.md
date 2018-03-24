* Es necesario que cuando descaguen el proyecto adicionalmente creen la base de datos.

<p align="center">
  <img src="./resources/DB_creation.png" alt="Size Limit example"
       width="806" height="1192">
</p>

* Modelo de dominio de la aplicación.

<p align="center">
  <img src="./resources/domain_model.png" alt="Size Limit example"
       width="938" height="796">
</p>

* Para poder implementar interceptores a nivel de módulo es necesario crear un archivo Entity Java Bean (EJB) llamado Standard Deployment.
  * A nivel de módulo, en NetBeans, darle "new" >> EJB >> Others >> Entity EJB >> Stardard Deployment.
  * Se creará un archivo llamado "ejb-jar.xml" para el modulo EJB o uno llamado "web.xml" para el módulo WAR.
  * Este archivo .xml consta de dos (2) partes: La primera sección muestra quién es el interceptor y la segunda muestra en dónde se aplica.
