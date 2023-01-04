# api-crud-usuario-consumindo-api-cep
Projeto didático para demonstar mais um meio de criar uma API com springRest, SpringData, DTO, PostgreSQL e Maven.

# Pré-requisitos

O maven deve está instalado, caso não esteja segue o link [maven](https://dicasdejava.com.br/como-instalar-o-maven-no-windows/)

Instalar e configurar o java, caso não esteja segue o link [java](https://medium.com/beelabacademy/configurando-vari%C3%A1veis-de-ambiente-java-home-e-maven-home-no-windows-e-unix-d9461f783c26)

Instalar o bd postgresql, caso não esteja segue o link [postresql](https://www.postgresql.org/)

![Estrutura do Projeto](https://github.com/thiago-jv/api-crud-usuario-consumindo-api-cep/blob/main/EstruturaProjeto.png)


# Tecnologias utilizadas e outros

 
 1- Java 8 [Sobre](https://www.java.com/pt-BR/download/help/java8_pt-br.html)
 
 2- SpringBoot 2.1.1.RELEASE [Sobre](https://docs.spring.io/spring-boot/docs/current/reference/html/)
 
 3- SpringData [Sobre](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference) 
 
 4- API REST [Sobre](https://www.redhat.com/pt-br/topics/api/what-is-a-rest-api)
 
 5- DTO [Sobre](https://qastack.com.br/software/171457/what-is-the-point-of-using-dto-data-transfer-objects)
  
 6- Maven 3.6.3 [Sobre](https://www.dclick.com.br/2010/09/15/o-que-e-o-maven-e-seus-primeiros-passos-com-a-ferramenta/)
 
 7- PostgreSQL [Sobre](https://www.postgresql.org/)

 
 # Proceso para rodar o projeto
```
1- git int na sua pasta que irá baixar o projeto, caso não tenha criado o repositorio local.
2- git clone https://github.com/thiago-jv/api-crud-usuario-consumindo-api-cep.git
3- criar o banco de dados conforme imagens abaixo
```

```
5- Entrar dentro da pasta do projeto
6- mvn dependency:resolve
7- mvn dependency:tree
8- mvn package
9- mvn spring-boot:run
```
![POST](https://github.com/thiago-jv/api-crud-usuario-consumindo-api-cep/blob/main/postman.png)
