stateless
=========

configurar el servidor

	en netbeans la pestaña services, clic derecho add server

		elegir glassfish, poner un nombre, siguiente
		
		aceptar los terminos de la licencia y descargar (102 MB) version 4.1, cuando termine la descarga, next

		dejar todo por defecto y finalizar

	Crear en mysql la base de datos stateless y el usuario stateles pass stateless con los privilegios sobre la base de datos creada

	en la misma pestaña, pero en Databases, clic derecho, new connection...

		Driver: Mysql

			siguiente

		Database: stateless
		User Name: stateless
		
		Password: stateless

		si todo está bien se puede presionar Test Connection y dirá Connectiom Succeded

		Copiar la JDBC URL (servirá después)

		siguiente, siguiente, finish

	descargar el connector mysql http://dev.mysql.com/downloads/connector/j/, extraer el .jar contenido y pegarlo a la ruta del servidor creado (carpeta lib), ej:
		C:\Users\Celso\GlassFish_Server\glassfish\lib\

	echar a andar el servidor creado (start) o reiniciar, y acceder a la consola de administración (clic derecho, view domain admin console)

		apretar en resources -> JDBC Connection Pools, new...

			Pool Name: statelessPool
			Resource Type: javax.sql.DataSource
			driver: Mysql

				siguiente

			En additional Properties

				password: stateless
				databaseName: stateless
				user: stateless

				agregar propiedades:

				url: jdbc:mysql://localhost:3306/stateless?zeroDateTimeBehavior=convertToNull
				URL: jdbc:mysql://localhost:3306/stateless?zeroDateTimeBehavior=convertToNull

			si todo está bien, al entrar al pool creado y apretar el boton ping dirá: Ping Succeded

		apretar en resources -> JDBC Resources, new...

			JNDI Name: StatelessJNDI
			Pool Name: statelessPool

			ok

	Probar el proyecto dándole (al enterprise(el triangulito)) build with dependencies y luego run
        si los proyecto tienen errores darle build with dependencies a cada uno para que maven descargue las dependencias