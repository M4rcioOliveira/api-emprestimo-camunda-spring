
# Concessão de Empréstimo - Camunda/Spring

Este projeto foi desenvolvido como parte do meu aprendizado em Camunda e demonstra a implementação de um processo de concessão de empréstimo utilizando a plataforma BPMN (Business Process Model and Notation), em conjunto com o Spring Framework. O objetivo é simular um fluxo de aprovação de empréstimos, desde a solicitação inicial até a decisão final, aproveitando as funcionalidades de modelagem e execução de processos do Camunda. Este é um projeto de estudo para entender melhor a integração dessas tecnologias e os conceitos de BPMN.

## Diagrama do Processo
![diagrama bpmn](https://raw.githubusercontent.com/M4rcioOliveira/api-emprestimo-camunda-spring/refs/heads/master/src/main/resources/exemplos/loan_process.png)

O diagrama acima ilustra o fluxo do processo de concessão de empréstimo. As principais etapas e componentes são:
1. **Solicitação**: O processo é iniciado com a solicitação de um empréstimo, contendo informações como CPF, renda, valor e tipo de empréstimo.
2. **Validação**: A solicitação é submetida a diversas validações, como score de crédito, valor do empréstimo e tipo de empréstimo.
3. **Aprovação**: Caso a solicitação atenda aos critérios, ela é encaminhada para aprovação.
4. **Decisão Final**: Após a aprovação, a decisão final sobre a concessão do empréstimo é tomada.



-   **Delegates**: As regras de negócio específicas são implementadas em *[delegates](#estrutura-do-projeto)*, que são componentes do Camunda responsáveis por executar lógica personalizada dentro do fluxo do processo *(retângulos⚙️)*.
-   **Gateways**: Os ***exclusive gateways*** são utilizados para tomar decisões com base em condições definidas, direcionando o fluxo do processo para diferentes caminhos *(losangos ✖️)* .
-   **User Tasks**: As ***user tasks*** representam atividades que exigem a intervenção humana, como a aprovação de um empréstimo consignado *(retângulos 👤)*.

💡 *Baixe e use Camunda Modeler para abrir e explorar os modelos disponível na pasta *[resources](#estrutura-do-projeto).**

## Tecnologias Utilizadas

-   **Java 17**: ☕️
-   **Spring Framework**: 🌱
-   **Camunda 7**: 🅒
-   **Docker**: 🐳

## Como Executar

1. **Clone o Projeto:**
 ```bash  
  git clone https://github.com/M4rcioOliveira/api-emprestimo-camunda-spring.git
  ```
2. **Entre na pasta do projeto e build a aplicação:**
 ```bash  
  docker build -t loan .
  ```
3. **Execute a Aplicação:**
 ```bash  
  docker run -p 8080:8080 loan
  ```
4. **Faça uma requisição:**
 ```bash  
curl --location 'http://localhost:8080/v1/loan' \
--header 'Content-Type: application/json' \
--data '{
    "cpf": "40697903060", //81761679082 Informe um desses dois cpfs para ter score aprovado.
    "renda": 22122.00,
    "valor": 11000.00,
    "tipoEmprestimo": "CONSIGNADO" 
}'
 ```
💡 *[Swagger UI](http://localhost:8080/swagger-ui/index.html)*

5. **Acesse o cockpit para ver o processo em execução e clique sobre LOAN para ver as instâncias em execução (user e senha *admin*):**
 ```bash  
   http://localhost:8080/camunda/app/cockpit 
```
6. **Acesse TaskList para ver as tarefas pendentes de aprovação manual (*clique em Add a simple filter no painel esquerdo da tela*):**
 ```bash  
  http://localhost:8080/camunda/app/tasklist  
   ```
![tela tasklist - clique sobre uma tarefa no painel do meio e em seguida no painel direito escolha entre os selects se quer APROVAR ou REPROVAR SOLICITAÇÃO, finalize clicando em Complete.](https://raw.githubusercontent.com/M4rcioOliveira/api-emprestimo-camunda-spring/refs/heads/master/src/main/resources/exemplos/example-usertask.png)
💡 *Acompanhe os logs da aplicação no terminal.*

## Estrutura do Projeto

```plaintext  
loan/
├── src/
│   ├── main/
│       ├── java/
│       │   ├── com/
│       │       ├── github/
│       │           ├── m4rciooliveira/
│       │               ├── config/
│       │               │   └── CamundaConfig.java
│       │               ├── constants/
│       │               │   ├── MsgException.java
│       │               │   ├── ProcessName.java
│       │               │   └── VariableName.java
│       │               ├── controller/
│       │               │   ├── dto/
│       │               │   │   └── LoanRequestDTO.java
│       │               │   └── LoanController.java
│       │               ├── delegate/
│       │               │   ├── AprovaCreditoDelegate.java
│       │               │   ├── CalculaConcessaoCreditoDelegate.java
│       │               │   ├── ConsultaScoreCreditoDelegate.java
│       │               │   └── ReprovaCreditoDelegate.java
│       │               ├── domain/
│       │               │   ├── enums/
│       │               │   │   ├── StatusAprovacao.java
│       │               │   │   └── TipoEmprestimo.java
│       │               │   └── Aprovacao.java
│       │               ├── exception/
│       │               │   └── ConverterException.java
│       │               ├── util/
│       │               │   ├── ConverterUtil.java
│       │               │   └── FormatterUtil.java
│       │               ├── wrapper/
│       │               │   └── ConverterUtilWrapper.java
│       │               └── Application.java
│       ├── resources/
│           ├── exemplos/
│           │   ├── example-usertask.png
│           │   └── loan_process.png
│           ├── static/
│           │   ├── forms/
│           │       └── form_loan_process.form
│           ├── application.yaml
│           └── loan_process.bpmn
├── .gitignore
├── Dockerfile
├── README.md
└── pom.xml
```  

💡 *[DEMO - YouTube](https://youtu.be/DL4rhl7-77M)*

## 👨‍💻 Desenvolvido por

<p align="center">  
  <a href="https://github.com/M4rcioOliveira">  
    <img src="https://avatars.githubusercontent.com/u/85836687?s=150&v=4" width="120" alt="Marcio Oliveira">  
  </a>  
</p>  

<h3 align="center">  
  <a href="https://github.com/M4rcioOliveira">Marcio Oliveira</a>  
</h3>  

<p align="center">  
  <a href="https://www.linkedin.com/in/marcioco/" target="_blank">  
    <img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white">  
  </a>  
</p>  


##  

💡*Saiba mais sobre o camunda em [Get started with Camunda and the Spring Boot | docs.camunda.org](https://docs.camunda.org/get-started/spring-boot/)*