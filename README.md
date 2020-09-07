#	Evaluación Práctica (ver. 2.0)

El siguiente bosquejo de diseño tiene como propósito establecer las condiciones medulares para que **Usted** pueda entender lo que se le solicita en la presente evaluación. Siga las indicaciones que se le indican a continuación.

0.  Requisitos

**Obligatorios**

- [x] MySQL v8.0.21
- [x] Java v11.0.8
- [x] JavaFX v14.0.2.1
- [x] IntelliJ IDEA

*Opcionales*

- [x] MySQLWorkbench
- [x] Jasper Report
- [x] MySQL Connector Driver
- [x] JCalendar v1.4

1.   Crear en MySQL un esquema de base de datos con el nombre de “motel”. En ese esquema se tienen que crear dos tablas. Una se tiene que llamar “habitacion” y la otra “historial”. Las siguientes figuras le servirán para orientarse en la creación del esquema y las dos tablas antes mencionadas.

Nota: Observe que existe una relación entre la tabla habitación e historial.

```sql
CREATE DATABASE  IF NOT EXISTS `motel`;
USE `motel`;

-- Table structure for table `habitacion`

DROP TABLE IF EXISTS `habitacion`;

CREATE TABLE `habitacion` (
  `idhabitacion` int NOT NULL,
  `huesped` varchar(45) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idhabitacion`)
)

-- Table structure for table `historial`

DROP TABLE IF EXISTS `historial`;

CREATE TABLE `historial` (
  `idhistorial` int NOT NULL AUTO_INCREMENT,
  `huesped` varchar(45) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `habitacion_id` int NOT NULL,
  PRIMARY KEY (`idhistorial`),
  KEY `FK_Habitacion_HIstorial_idx` (`habitacion_id`),
  CONSTRAINT `FK_Habitacion_HIstorial` FOREIGN KEY (`habitacion_id`) REFERENCES `habitacion` (`idhabitacion`)
) 
```

1. Insertar 14 registros en la tabla habitacion. Los datos puede introducirlos manualmente (o en la forma que Usted guste). Al insertar los registros deberá considerar lo siguiente:

   1. El campo **id** almacena el número de habitación. Por tanto, los valores válidos al hacer la captura deben ser en el rango 1...14.
   2. El campo **huesped** corresponde al nombre o alias del huésped. Durante la inserción manual de los 14 registros que se le han solicitado, en vez de capturar el nombre de una persona, deberá capturar “Habitación 1”, “Habitación 2”, “Habitación 3”, y así sucesivamente.
   3. El campo **fecha** corresponde a la fecha en que fue capturada la habitación. Sobre este campo, le sugiero que no le otorgue ningún valor durante la inserción, para que le sea asignada automáticamente la fecha y hora del sistema (tipo de campo timestamp).
2. Con el IDE IntelliJ IDEA crear una clase llamada **MotelDatos**. En esta clase Usted deberá escribir el código base que se muestra a continuación, implementar los métodos de la interface y agregar todos los que Usted crea pertinentes.

```java
package motel;
import java.sql.Connection;

interface OperacionSQL{
  Connection Conectar();
  boolean isOcupada(int id);
  boolean registrar();
  boolean desocupar();
  boolean registrarHistorial();
}

public class MotelDatos implements OperacionSQL{
  private Connection db;
  private int id;
  private String huesped;

  public void setId(int id){
    this.id = id;
  }

  public void setHuesped(String huesped){
    this.huesped = huesped;
  }
}
```

Descripción de los métodos de la interfaz OperacionSQL

| Método               | Descripción                                                  |
| -------------------- | ------------------------------------------------------------ |
| conectar( )           | Establece conexión a la base de datos                        |
| isOcupada(int id)    | Devuelve ***true*** si la habitación está ocupada y ***false*** en caso contrario |
| registrar( )          | Registra la habitación como ocupada. Desencadena la acción de guardar en el campo *huésped* de la tabla *habitación* el nombre o alias de la persona que la rentó. Además, en el botón de la habitación de la interfaz gráfica (véase Figura 2 y el botón de la habitación 10) se cambia el número de habitación por el nombre del *huésped*. |
| desocupar( )          | Libera la habitación. El botón de la interfaz recupera el texto original (número de habitación) quitando de este el nombre del *huésped*. |
| registrarHistorial( ) | Se guardan los datos de la habitación que se ha liberado en la tabla historial. |
| **NOTA:**            | Los métodos que se han descrito tienen el propósito de ofrecerle un punto de partida para el desarrollo de la solución. Siéntase en libertad de agregar las clases adicionales que Usted crea convenientes. |

