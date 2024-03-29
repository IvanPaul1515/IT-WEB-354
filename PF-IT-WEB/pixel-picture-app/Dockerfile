# Usa una imagen base ligera de Node.js
FROM node:18

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /usr/src/app

# Copia los archivos de la aplicación compilada (desde la carpeta "build") al directorio de trabajo del contenedor
COPY . /usr/src/app

RUN npm install
# Instala un servidor HTTP para servir la aplicación
RUN npm install -g serve

# Expone el puerto 3000 para que la aplicación sea accesible desde fuera del contenedor
EXPOSE 3000

# Define el comando para iniciar el servidor HTTP y servir la aplicación
CMD ["serve", "-s", "build", "-l", "3000"]
