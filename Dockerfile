# Estágio 1: Build da aplicação
FROM ubuntu:latest AS build

# Instalar OpenJDK 17 e Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Definir diretório de trabalho
WORKDIR /app

# Copiar arquivos do projeto
COPY . .

# Compilar o projeto e gerar o JAR
RUN mvn clean install -DskipTests

# Estágio 2: Imagem final otimizada
FROM openjdk:17-jdk-slim

# Expor a porta que o Render irá usar
EXPOSE 8080

# Copiar o JAR compilado do estágio de build
COPY --from=build /app/target/meuapp-0.0.1-SNAPSHOT.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
