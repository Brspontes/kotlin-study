# Etapa de build
FROM jelastic/maven:3.9.5-openjdk-21 AS builder

# Define o diretório de trabalho
WORKDIR /app

# Copia todos os arquivos da aplicação para o diretório de trabalho no container
COPY . .

# Executa o build da aplicação usando Maven
RUN mvn clean install

# Etapa final, para a execução do jar
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho na etapa final
WORKDIR /app

# Copia o jar gerado na etapa de build para o container final
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta 8080 para a aplicação
EXPOSE 8080

# Define o comando de inicialização do container
ENTRYPOINT ["java", "-jar", "/app/app.jar"]