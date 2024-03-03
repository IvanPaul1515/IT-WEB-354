## Funcionamiento

1. **Simulación de Sensores:**
   - Se simulan dos sensores en instancias diferentes, cada uno monitoreando la temperatura y la humedad de una ubicación específica.
   - Los sensores generan datos cada 1 minuto en formato JSON que representan la temperatura y la humedad en un momento dado.
   - Estos datos son enviados al servicio Apache ActiveMQ para su procesamiento y almacenamiento.

2. **Recepción de Datos:**
   - Un programa en el sistema central se suscribe a los datos enviados por los sensores a través de Apache ActiveMQ.
   - Los datos recibidos son procesados y luego enviados a través de WebSocket a la página web para su visualización en tiempo real para ver ser visualizados en graficos.

3. **Visualización de Datos:**
   - La página web utiliza Chart.js para crear gráficos que muestran la evolución de la temperatura y la humedad de cada sensor.
   - Los gráficos se actualizan dinámicamente a medida que llegan nuevos datos, permitiendo observar los cambios en tiempo real.

## Tecnologías Utilizadas

- Apache ActiveMQ: Para la mensajería y el intercambio de datos entre los sensores y el sistema central.
- WebSocket: Para la comunicación bidireccional en tiempo real entre el servidor y la página web.
- Chart.js: Para la generación de gráficos interactivos que muestran los datos de temperatura y humedad.
- Docker: Para la gestión de contenedores y la ejecución del proyecto en entornos de desarrollo y producción.

El proyecto se ejecuta mediante Docker Compose.
El acceso a los gráficos se realiza a través del puerto 8080.
