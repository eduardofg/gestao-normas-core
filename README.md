# Back-end - Gestão de Normas

 Exemplo de um serviço em Java que serve de back-end para um serviço de Gestão de Normas

## Framework utilizado:

 Spring 2.4.0
 
 Java 11
 
 Lombok
 
## Arquitetura

  Se utilizou como base os conceitos da arquitetura hexagonal, no qual se considerou a persistência em banco 
  
  parte da infraestrutura (ports & adapters), com modelagem diferente do modelo de domínio. 

## Utilização:

  Instale o Java OpenSDK versão 11: https://jdk.java.net/11/
  
  Aplique o Lombok na sua IDE: https://projectlombok.org/download
  
  Utilize o Maven para fazer o build do projeto: mvn clean install
  
  Rode a aplicação através da execução com SpringBoot: java -jar target/gestao-normas-core-0.0.1-SNAPSHOT.jar

  Para acessar o Swagger, utilize o seguinte endereço: http://localhost:8080/swagger-ui.html
  
  