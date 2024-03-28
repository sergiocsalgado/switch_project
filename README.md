# Project Management System - Grupo 5 - DevOps #
O objetivo deste assignment é implementar uma pipeline no Jenkins para o nosso projeto onde devem ser aplicados os <br>
seguintes requisitos:

- Código como infraestrutura: onde é possível reproduzir a nossa solução num sistema, clonando o nosso repositório <br>
e container imagens que serão carregadas no Docker Hub.


- Git/Bitbucket: utilização de um destes serviços para criação de tarefas, e issues para todos os elementos do grupo. <br>
Utilização de branches para desenvolver funcionalidades específicas da solução.


- Hosts:
  - A solução no jenkins é baseada em 2 hosts:
    - **Control** host: Um host com o Jenkins.
    - **Dind** host: Um host com o Docker in Docker a ser usado pelo Jenkins.
  - O _deployment_ é baseado em 4 hosts diferentes e são partilhados num só ficheiro docker compose
    - **Frontend** host: Para apresentação da aplicação.
    - **Backend** host: Para servidor da aplicação.
    - **DB** host: Para guardar dados da aplicação. No nosso caso foi usada a MariaDB.
    - **PHPMyAdmin** host: Para gerir e visualizar a base de dados.

## I - Configuração inicial do Jenkins

É criado um docker-compose.yml onde são definidos dois serviços:
- Docker-in-Docker (DIND)
- Jenkins-Blueocean (Blue Ocean)


**1 - blueocean Dockerfile:**

 ``` dockerfile
 FROM jenkins/jenkins:2.401.2
USER root
RUN apt-get update && apt-get install -y lsb-release
RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
  https://download.docker.com/linux/debian/gpg
RUN echo "deb [arch=$(dpkg --print-architecture) \
  signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
  https://download.docker.com/linux/debian \
  $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
RUN apt-get update && apt-get install -y docker-ce-cli
USER jenkins
RUN jenkins-plugin-cli --plugins "blueocean docker-workflow"
 ```

**2 - docker-compose file:**

 ``` yaml
 version: '3'

services:
  jenkins-docker:
    image: docker:dind
    container_name: jenkins-docker
    privileged: true
    networks:
      jenkins:
        aliases:
          - docker
    environment:
      - DOCKER_TLS_CERTDIR=/certs
    volumes:
      - jenkins-docker-certs:/certs/client
      - jenkins-data:/var/jenkins_home
    ports:
      - '2376:2376'

  jenkins-blueocean:
    build:
      context: ./
      dockerfile: Dockerfile
    image: myjenkins-blueocean:2.401.1-1
    restart: on-failure
    networks:
      - jenkins
    environment:
      - DOCKER_HOST=tcp://docker:2376
      - DOCKER_CERT_PATH=/certs/client
      - DOCKER_TLS_VERIFY=1
    volumes:
      - jenkins-data:/var/jenkins_home
      - jenkins-docker-certs:/certs/client:ro
    ports:
      - "8282:8080"
      - "50000:50000"

networks:
  jenkins:
    driver: bridge

volumes:
  jenkins-data:
    external: true
  jenkins-docker-certs:
    external: true
 ```

Este é um ficheiro de configuração do docker-compose usado para definir dois serviços: jenkins-docker e jenkins-blueocean. <br>
O serviço jenkins-docker é responsável por executar comandos Docker dentro de um container, enquanto o serviço <br>
jenkins-blueocean é usado para executar o Jenkins e fornecer uma interface web.

As portas 2376, 8282 e 50000 são mapeadas para permitir o acesso ao Docker, à interface web do Jenkins e à comunicação <br>
com agentes do Jenkins, respectivamente.


## II - Criação de Dockerfile para os hosts de backend e frontend do projeto

Para a publicação das imagens no docker hub, será necessário elaborar dois Dockerfiles independentes para definir<br>
as instruções necessárias, de forma a construir as imagens dos docker para os respetivos componentes, frontend e backend <br>
do projeto.

### 1 - Dokerfile (backend) 

Foi criado um Dockerfile _(devops\backend\Dockerfile)_ que cria uma imagem de um Docker container de forma a construir o 
backend da nossa _web apllication_ e, em seguida, criar um container Tomcat para implementar a mesma.

Pré-requisitos com alterações necessárias no servidor:

- **application.properties** <br>

  - É definida a porta em que o servidor será executado, neste caso, a porta 8080.
  - Especifica a classe do driver JDBC que será usada para comunicar com a base de dados MariaDB.
  - Define o URL de conexão com a base de dados MariaDB. Indica o nome do host (database), a porta (3306) e o nome <br>
  da base de dados (group5DB).
  - Define o username para autenticação na base de dados.
  - Define a password para autenticação na base de dados.
  - A opção create-drop indica que o Hibernate vai criar as tabelas na base de dados quando a aplicação for iniciada <br>
  e irá descartá-las quando a aplicação for encerrada.

``` java
spring.jpa.hibernate.ddl-auto=update
``` 


- **ServletInitializer** <br>

  - A classe ServletInitializer faz parte de uma aplicação Spring Boot e estende a classe SpringBootServletInitializer. <br>
  Substitui o método configure, que é usado para configurar a aplicação quando implantada em um container de servlet. <br>
  Ao especificar a classe SwitchApplication como a fonte no método configure, o container de servlet pode inicializar <br>
  e configurar corretamente a aplicação Spring Boot. Isso permite que esta seja implantada como um arquivo .war, <br>
  aproveitando os recursos do container de servlet beneficiando dos recursos fornecidos pelo Spring Boot.

``` java
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SwitchApplication.class);
    }
}
``` 

**Stage 1: Build the backend**

``` dockerfile
# Base image
FROM maven:3.9.1 as builder

# Set the working directory within the container image
WORKDIR /app

# Copy the source code to the container
COPY ./ ./

# Build the project
RUN mvn package -DskipTests

# Create the final container
FROM tomcat:9.0.54-jdk11

# Remove the default Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file to Tomcat webapps directory
COPY --from=builder /app/target/switch2022project-g5-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose port to access the server
EXPOSE 8080

# Command to run the app
CMD ["catalina.sh", "run"]
```

**Visão geral do Dockerfile:**

O Dockerfile consiste nos seguintes passos:

**Stage 1: *Build* do Backend**
* Importa a imagem base utilizada. Esta será maven: 3.9.1, que contém o Maven instalado.
* Define do diretório inicial dentro do container (usado como ponto de partida para clonar o reposotório git).
* Copia todo o código-fonte (incluindo o arquivo Dockerfile) para o diretório de trabalho dentro do container.
* Para compilar e comprimir a aplicação num ficheiro .war .<br>
Utiliza o parâmetro _-DskipTests_ para saltar a execução dos testes durante a sua construção.

**Stage 2: *Build* do container final**
* Importa a imagem base utilizada. Esta será tomcat: 9.0.54, que contém o Java 11 instalado.
* Remove a aplicação web padrão do Tomcat existente no diretório indicado. <br>
É utilizado para limpar o diretório antes de copiar o novo WAR para lá.
* Copia o ficheiro .war do build do backend criado na **stage 1** para o diretório indicado. O nome do ficheiro .war é <br>
alterado para ROOT.war, para que a aplicação seja implementada na raiz do Tomcat.
* Disponibiliza a porta 8080, que é a porta padrão usada pelo Tomcat para aceitar pedidos HTTP.
* Define o comando CMD para executar o scripts que iniciam o servidor Tomcat quando o container for iniciado.


Em resumo, este Dockerfile cria um container que copia o projeto, compila e comprime o código usando o Maven e, <br>
em seguida, implementa a aplicação no servidor Tomcat. O container criado pode ser executado para iniciar o backend.

### 2 - Dockerfile (frontend)

``` dockerfile
# Base image
FROM node:18.15.0 as frontend-build

# Set the working directory within the container image
WORKDIR /app

# Copy all web application files to container image
COPY ./web_application/ ./

# Install dependencies
RUN npm ci

# Build
RUN npm run build

# Expose port to access the server
EXPOSE 3000

# Command to run the app
CMD [ "npx", "serve", "build" ]
```

Este Dockerfile (situado em _devops\frontend\Dockerfile_) vai criar uma imagem de um Docker container que irá <br>
permitir construir o frontend da nossa web application.

É composto pelo seguinte conjunto de instruções:

* Importa a imagem base utilizada (node: 18.15.0), que contém o Node.js instalado.
* O diretório "/app" é usado como ponto de partida para clonar o nosso repositório git.
* Copia todos os arquivos da aplicação web (localizados no diretório web_application) para o diretório de trabalho dentro do container.
* O comando npm ci para instalar as dependências do projeto.
* O comando npm run build para criar a versão otimizada e compilada do frontend.
* É disponibilizada a porta 3000, que é a porta na qual o frontend será executado e acessível externamente.
* O comando CMD para executar o pacote "serve" e disponibilizar o conteúdo da pasta "build" gerada anteriormente. <br>
O comando npx serve build inicia um servidor HTTP para disponibilizar os ficheiros da aplicação.


Em resumo, este Dockerfile cria um container que vai copiar a web_application, instala as dependências e cria uma versão <br> 
compilada da nossa aplicação. Em seguida, inicia um servidor HTTP para disponibilizar a aplicação na porta 3000. <br>
O container criado pode ser executado para iniciar o frontend.


## III - Configuração Jenkins Online

Instalar todos os **Plugins** necessários: Jenkins > manage Jenkins > plugins > available plugins

- Bitbucket Plugin
- Pipeline
- Blue Ocean
- Maven Integration plugin
- Docker
- Cobertura Plugin
- JaCoCo
- Javadoc Plugin
- Pipeline: GitHub Groovy Libraries

## IV - Criação do Jenkinsfile
Para a criação deste Jenkinsfile terá que ser alterado o ficheiro pom.xml de forma a gerar ficheiros .war <br>
durante o _build_ do projeto, adicionando o seguinte:

```xml
<packaging>war</packaging>
```

**Jenkinsfile:**

O Jenkinsfile é um arquivo de configuração para a implementação de pipelines de _Continuous Integration and Continuous Delivery_ (CI/CD) usando o Jenkins.<br>
É definido todo o fluxo de trabalho desde a construção e testes até à implementação de um projeto de software. <br>
Assim, é possível descrever em forma de script a execução das etapas necessárias para _build_ e _deploy_ do projeto.


Pré-requisitos com instalação necessária:

- Docker

``` dockerfile
pipeline {
    agent any

    stages {        
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.9.0-eclipse-temurin-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Building...'
                sh 'mvn -DskipTests clean package'
            }
        }

        stage('Test') {
            agent {
                docker {
                    image 'maven:3.9.0-eclipse-temurin-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Testing...'
                sh 'mvn test'
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Publish Jacoco Report') {
            steps {
                echo 'Publishing Jacoco Report...'
                jacoco(execPattern: '**/target/jacoco.exec')
            }
            post {
                always {
                    jacoco(execPattern: '**/target/jacoco.exec')
                    jacoco(classPattern: '**/target/classes')
                    jacoco(sourcePattern: 'src/main/java')
                    publishCoverage(adapters: [
                        jacocoAdapter('*/target/site/jacoco/*.xml')
                    ])
                }
            }
        }

        stage('Generate Javadocs') {
            agent {
                docker {
                    image 'maven:3.9.0-eclipse-temurin-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Generating Javadocs...'
                script {
                    try {
                        sh 'mvn javadoc:javadoc'
                    } catch (Exception e) {
                        echo 'Error generating Javadocs, but the build will continue'
                    }
                }
            }
        }

        stage('Publish Javadoc') {

            steps {
                echo 'Publishing Javadoc...'
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'target/site/apidocs',
                    reportFiles: 'index.html',
                    reportName: 'Javadoc'
                ])
            }
        }

        stage('Archive') {
            steps {
                echo 'Archiving...'
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
                archiveArtifacts artifacts: 'target/site/apidocs/**', fingerprint: true
                archiveArtifacts artifacts: 'target/surefire-reports/**', fingerprint: true
                archiveArtifacts artifacts: 'docs/**', fingerprint: true
            }
        }

        stage('Publish Image') {
            steps {
                echo 'Building and publishing Docker image...'
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerHubID') {
                        def frontendImage = docker.build("wolffer22/frontend:${env.BUILD_ID}", "-f ./devops/frontend/Dockerfile .")
                        def backendImage = docker.build("wolffer22/backend:${env.BUILD_ID}", "-f ./devops/backend/Dockerfile .")

                        frontendImage.push()
                        frontendImage.push("latest")

                        backendImage.push()
                        backendImage.push("latest")
                    }
                }
            }
        }
    }
}

```


**Visão geral do Pipeline:**

O pipeline consiste nas seguintes etapas (_stages_):

- **Build:** Nesta etapa, o código-fonte é compilado usando o agente Maven. <br>
  A opção _-DskipTests_ é usada para saltar a execução dos testes durante o processo de compilação.

- **Test:** O projeto é testado nesta etapa utilizando o comando _mvn test_. Os resultados dos testes são registados e <br>
  publicados como relatórios de teste JUnit. Os testes realizados nesta etapa são unitários e de integração.

- **Publicar Relatório Jacoco:** Esta etapa publica o relatório de cobertura de código do JaCoCo. <br>
  O arquivo de execução do JaCoCo é guardado e processado para gerar relatórios de cobertura.

- **Gerar Javadocs:** Javadocs são gerados para o projeto utilizando o comando Maven _javadoc:javadoc_.

- **Publicar Javadoc:** Os Javadocs gerados são publicados como artefactos HTML. O relatório do Javadoc é armazenado no <br>
  diretório target/site/apidocs.

- **Arquivar:** Esta etapa arquiva vários artefactos, incluindo o ficheiro .war gerado, os Javadocs, os relatórios de teste <br>
  do Surefire e quaisquer ficheiros de documentação.

- **Publicar Imagem:** A etapa final constrói e publica imagens Docker para os componentes frontend e backend do projeto.<br>
  As imagens são marcadas (_tag_) com o ID de compilação do Jenkins e com a _tag_ "latest" e enviadas para um registo Docker <br>
(neste caso, Docker Hub).


Para usar este Jenkinsfile, é preciso criar um _job_ de pipeline no Jenkins e configurá-lo para usar este ficheiro.

- Abrir o painel do Jenkins e criar um novo _job_ de pipeline.

- Na configuração do pipeline, selecionar "Pipeline script from SCM" como a definição de pipeline.

- Definir a URL do repositório como o local onde está guardado este Jenkinsfile e as respetivas credenciais. <br>
  Using PTA token (https://stackoverflow.com/questions/61105368/how-to-use-github-personal-access-token-in-jenkins):
```
  https://<access token>@github.com/<userName>/<repository>.git
```

- Guardar a configuração do pipeline e executar o _job_.

## V - Criação do ficheiro docker-compose.yml para _deploy_ do projeto

Pré-requisitos:

- Criação de ficheiro .env.sample:
```
DB_ROOT_PASSWORD: example
DB_USER: sa
DB_PASSWORD: sa
DB_DATABASE: group5DB
```

Deste ficheiro cria-se uma cópia .env e guarda-se na raiz do projeto. <br>
Serve para ocultar ou proteger informações confidenciais, como senhas. <br>
O ficheiro .env é normalmente utilizado para armazenar variáveis de ambiente para uma aplicação.

**docker-compose:**

```yaml
 version: '3.1'

 #Setup Network
 networks:
   g5NetWork:

 #Setup Volumes (default)
 volumes:
   mariadb:
     driver: local

 services:
   database:
     image: mariadb
     restart: always
     volumes:
       - ./data:/var/lib/mysql
     environment:
       MARIADB_ROOT_PASSWORD: ${DB_ROOT_PASSWORD}
       MARIADB_USER: ${DB_USER}
       MARIADB_PASSWORD: ${DB_PASSWORD}
       MARIADB_DATABASE: ${DB_DATABASE}
     networks:
       - g5NetWork
     ports:
       - '3306:3306'

   phpmyadmin:
     image: phpmyadmin
     restart: always
     environment:
       PMA_HOST: database
     networks:
       - g5NetWork
     ports:
       - '8081:80'

   frontend:
     image: 'wolffer22/frontend:latest'
     networks:
       - g5NetWork
     ports:
       - '3000:3000'

   backend:
     image: 'wolffer22/backend:latest'
     environment:
       - SPRING_DATASOURCE_URL=jdbc:mariadb://database:3306/group5DB
       - SPRING_DATASOURCE_DRIVER-CLASS-NAME=org.mariadb.jdbc.Driver
     networks:
       - g5NetWork
     ports:
       - '8080:8080'
     depends_on:
       - database
```

**Visão geral do docker-compose:**

### **Database:** 

Neste serviço cria-se uma imagem de base de dados MariaBD.
* ***image:*** especifica-se que é uma imagem oficial __mariaDB__.
* ***restart:*** Quando o container parar é reiniciado pelo Docker.
* ***volumes:*** É mapeado o diretório _./data_ do host para o diretório _/var/lib/mysql_ dentro do <br>
  container onde são armazenados os dados.
* ***environment:*** As variáveis de ambiente são passadas para o container e configuram a mariaDB.
* ***networks:*** Determina que o container está conectado à rede __g5NetWork__.
* ***ports:*** É mapeada a porta 3306 (3306 antes dos ":") do host até à porta 3306 (3306 depois dos ":") dentro do
  container criado pelo docker-compose.



### **Phpmyadmin:** 

PhpMyAdmin é uma ferramenta de gestão de base de dados MySQL baseada na web. .<br>
Esta ferramenta fornece uma interface gráfica intuitiva para gerir bases de dados, tabelas, colunas, índices, consultas <br>
SQL e outros recursos relacionados ao MySQL.<br>
Com o PhpMyAdmin, os utilizadores podem executar tarefas comuns de gestão de base de dados, como criar bases de dados, <br>
criar tabelas, inserir e editar registos, executar consultas SQL e gerir permissões de utilizadores.<br>
É uma ferramenta amplamente utilizada por developers, administradores de base de dados e outros profissionais que trabalham <br>
com o MySQL, pois simplifica a gestão e a manutenção de bases de dados através de uma interface amigável.<br>
O PhpMyAdmin é um software de código aberto e pode ser instalado em servidores web que suportam PHP e MySQL.<br>

Estes foram os passos que seguimos para configurar o serviço PhpMyAdmin através do docker-compose.<br>
* ***phpmyadmin:***  Definir o nome do serviço PhpMyAdmin no Docker Compose.
* ***image: phpmyadmin*** Especificar a imagem Docker a ser usada para o serviço PhpMyAdmin. Neste caso, está usando <br>
a imagem "phpmyadmin", que é uma imagem oficial do PhpMyAdmin disponível no Docker Hub.<br>
* ***restart: always*** Definir a política de reinicialização para o serviço. Neste caso, o serviço será sempre <br>
reiniciado automaticamente se parar ou falhar.<br>
* ***environment:*** Aqui, definimos as variáveis de ambiente para o serviço.<br>
* ***- PMA_HOST = database*** Esta linha define uma variável de ambiente chamada "PMA_HOST" e atribui o valor "database".<br>
Esta variável é usada pelo PhpMyAdmin para determinar o host da base de dados MySQL ao qual se vai conectar.<br>
* ***networks:*** Aqui, especificamos as redes às quais o serviço PhpMyAdmin deve ser conectado.<br>
* ***- g5NetWork*** Especificação da rede chamada "g5NetWork", que já foi definida anteriormente no arquivo Docker Compose.<br>
* ***ports:*** Configuração do mapeamento da porta para o serviço.<br>
* ***- '8081:80'*** Por último, mapeamos a porta 8081 do host para a porta 80 dentro do contentor do PhpMyAdmin.<br>
  Significa que podemos aceder ao PhpMyAdmin no nosso browser a partir do link "http://localhost:8081" ou através do <br>
endereço IP da máquina onde o Docker está a ser executado, seguido por ":8081".<br>

### **Frontend:** 

* _**image**_: O serviço do frontned está a  usar a imagem Docker 'wolffer22/frontend' com a tag 'latest'. Essa imagem contém <br>
as dependências e o código necessários para o serviço de frontend.

* _**networks**_: O serviço do frontend está ligado a uma rede chamada 'g5NetWork'. Essa rede permite a comunicação entre <br>
containers dentro dessa mesma rede.

* _**ports**_: O serviço do frontend está a mapear a porta 3000 do container para a porta 3000 do localhost. Isso significa <br>
que o frontend estará acessível na porta 3000 do localhost.

Esta configuração permite que o serviço frontend seja acedido num browser ou por qualquer cliente que se ligue no <br>
localhost na porta 3000.

### **Backend:** 

* ***image:*** O serviço do backend está a  usar a imagem Docker 'wolffer22/backend' com a tag 'latest'. Essa imagem 
contém as dependências e o código necessários para o serviço de backend.
* ***environment:*** O environment especifica as variáveis que serão definidas para o serviço do backend. Neste caso, as duas 
variáveis estão definidas:
* _**SPRING_DATASOURCE_URL:**_ Esta variável define a URL para a base de dados MariaDB ao qual o serviço do backend 
se vai conectar. O URL 'jdbc:mariadb://database:3306/group5DB', onde 'database' é o nome do host do container MariaDB, 
'3306' é a porta padrão do MySQL/MariaDB e 'group5DB' é o nome da base de dados.
* _**SPRING_DATASOURCE_DRIVER-CLASS-NAME:**_  Esta variável define o nome do driver para o driver MariaDB. O 
'org.mariadb.jdbc.Driver' indica o uso do driver JDBC do MariaDB.
* ***networks:*** O serviço do backend está ligado a uma rede chamada 'g5NetWork'. Essa rede permite a comunicação entre 
containers dentro da mesma rede.
* ***ports:*** O serviço do backend está a mapear a porta 8080 do container para a porta 8080 da máquina host. Isto 
significa que o serviço de backend estará acessível na porta 8080 da máquina host.
* ***depends_on:*** O serviço de backend depende de outro serviço chamado 'database'. Isso implica que o serviço 'database' 
precisa ser iniciado antes do serviço de backend.

Esta configuração define um serviço backend usando uma imagem Docker específica, define environment para o serviço se 
conectar a uma base de dados MariaDB, especifica a rede e mapeamentos de portas, e estabelece a dependência em relação 
a outro serviço.

## VI - Conclusão

Este trabalho focou-se na implementação de uma pipeline Jenkins no nosso projeto que envolve uma aplicação web. <br>
A configuração inicial do Jenkins envolveu a definição de dois serviços Docker-in-Docker (DIND) e Jenkins-Blueocean <br>
usando um arquivo docker-compose.yml. Isso permitiu que os comandos do Docker fossem executados dentro de um container <br>
e forneceu uma interface web para o Jenkins. <br>

Foram criados dois Dockerfiles para os componentes de frontend e backend do projeto. O Dockerfile para o backend cria <br>
um container e arquiva a aplicação num ficheiro .war . O Dockerfile para o front-end cria um container e uma versão <br>
otimizada e compilada do front-end.<br>

A configuração do Jenkins inclui a instalação dos plugins necessários e a configuração de um Jenkinsfile. O Jenkinsfile <br>
define as etapas do pipeline, incluindo o _build_ e os testes do projeto, o envio das imagens do Docker para o Docker <br>
Hub e a implantação da aplicação nos respectivos hosts.<br>

Assim garante-se a entrega consistente e confiável da aplicação Web.