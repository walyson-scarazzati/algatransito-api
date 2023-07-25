# Sites que ajudam a criar o documento readme.md

*  https://www.makeareadme.com/
*  https://readme.so/ 

<h1 align="center">Algatransito-api REST API</h1>

## 📜 Descrição
REST API para gestão de multas de carros management, implementado para imagem Docker no Heroku com base CI/CD com Jenkins. No entanto, apenas a implantação do Heroku foi disponibilizada ao público: **https://erp-server-prod-profile.herokuapp.com**. Principalmente devido às condições de preços. Abaixo você pode encontrar exemplos de credenciais para fazer login. Todos os endpoints disponíveis estão incluídos na documentação do No entanto, apenas a implantação do Heroku foi disponibilizada ao público: **https://erp-server-prod-profile.herokuapp.com**. Principalmente devido às condições de preços. Abaixo você pode encontrar exemplos de credenciais para fazer login. Todos os endpoints disponíveis estão incluídos na documentação do OpenApi..
```
{
    "username" : "user",
    "password" : "password"
}
```

## 🛠 Stack Tecnológica
- Java 17
- Maven
- Spring Boot 3.1.0
- Hibernate
- Spring Data
- Flyway
- MySQL 8
- Lombok
- JSON Web Token
- JUnit
- Mockito
- Heroku
- Docker
- Jenkins
- OpenApi
- Postman
- Devtools

## ✅ Requesitos para instalação
- Maven
- JDK 17+
- Spring Tool Suite 4.19

## 🏃‍♂️ Instalação
- Perfil de desenvolvimento:
  - Run the application:
  ```
  mvn clean -Pdev spring-boot:run
  ```
  
- Perfil de produção (para uso local):
  - Add properties to data source connection in **application-prod.properties** file
  - Run the application:
  ```
  mvn clean -Pprod spring-boot:run
  ```
  
## 🎮 Documentação OpenApi

Remoto: https://erp-server-prod-profile.herokuapp.com/swagger-ui/index.html

Local: http://localhost:8080/swagger-ui/index.html

## 🎯 Recursos futuros
- Integração JWT com arquitetura Spring Security (para manipulação de autoridades)
- Página de registro com confirmação por e-mail