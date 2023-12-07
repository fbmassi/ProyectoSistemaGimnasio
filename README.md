# Gimnasio "Supertlon" - Sistema de Gestión Integral

Este repositorio contiene el código fuente y la documentación del sistema de gestión integral desarrollado para la cadena de gimnasios "Supertlon". El sistema permite la administración eficiente de sedes, clases, profesores, artículos y usuarios.

## Relevamiento y Requerimientos

El equipo de analistas ha realizado un relevamiento detallado para la confección del sistema, considerando los siguientes aspectos:

- **Niveles de Socios y Sedes**: Los socios se clasifican en niveles ("Black", "Oro", "Platinum") y cada sede tiene un nivel mínimo de aceptación. No puede haber más de una sede en el mismo barrio.

- **Asignación de Clases**: Se establece un proceso para asignar clases, considerando la disponibilidad de profesores, lugares y la capacidad de las sedes. Se detallan restricciones para profesores y alumnos.

- **Tipos de Clases y Artículos**: Se definen diferentes tipos de clases y artículos. Algunas clases pueden ser presenciadas en línea y almacenadas en una base de datos de streaming.

- **Desgaste/Amortización de Artículos**: Todos los artículos tienen una forma de desgaste/amortización, y se deben mantener suficientes existencias para afrontar cada clase. Se dispara una alerta en caso de falta de stock.

- **Estados de Clases y Rentabilidad**: Las clases pueden tener estados como "AGENDADA", "CONFIRMADA" y "FINALIZADA". La rentabilidad se calcula considerando costos de profesores, emplazamiento y membresías de alumnos.

## Perfiles de Usuario

El sistema admite tres perfiles de usuario:

- **Soporte Técnico**: Puede crear sedes, usuarios, tipos de clases y artículos.

- **Administrativo**: Encargado de gestionar sedes, clases, artículos y clientes. Puede monitorear el estado de los artículos y las clases almacenadas en el sistema de streaming.

- **Cliente**: Puede reservar lugares para clases presenciales o virtuales, según corresponda.
