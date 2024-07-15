
# Sistema de Agendamento de Horário simples

Um Sistema de Agendamento de Horário, onde você consegue cadastrar um cliente, agendar um horário para esse cliente e visualizar a hora, dia e o para cliente foi reservado. 
Esse projeto foi feito para disciplina de `Programação Orientada a Objeto em JAVA`


## Pré-requisitos
Certifique-se de ter as seguintes ferramentas instaladas em seu sistema:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) 11 ou superior
- [Maven](https://maven.apache.org/install.html) 
- [Connector-mysql](https://mvnrepository.com/artifact/mysql/mysql-connector-java)

## Configuração do Ambiente
1. **Verifique a instalação do JDK**
Certifique-se de que o JDK está instalado corretamente e que o `JAVA_HOME` está configurado:
```sh
java -version
```

2. **Clone o Repositório**
Clone o repositório do projeto para o seu ambiente local:

```sh
git clone https://github.com/usuario/nome-do-projeto.git
cd nome-do-projeto
```
## Compilação e Excução
Se você estiver utilizando um compilador que não precise excutar o projeto java pela linha de comando, apenas click em **RUN** ou atalho de compilação para excutar o arquivo `main.java`.

**Compilar e executar o MAVEN no terminal**
- Compile o projeto
```sh
mvn clean install
```
- Execute o Projeto
Após a compilação, execute o seguinte comando para iniciar o projeto:

```sh
mvn exec:java -Dexec.mainClass="com.seu.pacote.MainClass"
```


## Licença
This project is under the GPL-3.0 License. Take a look at the [LICENSE](https://choosealicense.com/licenses/mit/) file for more details.