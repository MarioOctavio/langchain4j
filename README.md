Gemini AI Studio – Integração com Java Spring Boot

📌 Visão Geral

Este projeto demonstra como integrar o Gemini AI Studio (Google AI) com LangChain e Spring Boot para análise de métricas e logs em aplicações Java. 

Inclui:

- Exposição de métricas via Spring Boot Actuator

- Integração com Prometheus e Grafana para visualização, utilizando docker-compose

<br/>
🚀 Tecnologias Utilizadas
Java 17+

Spring Boot

LangChain4j

Gemini AI Studio

Prometheus

Grafana

Docker

Gradle

<br/>
✅ Pré-requisitos

Java 17 ou superior instalado

Docker instalado e em execução

Conta ativa no Gemini AI Studio com uma API Key válida

<br/>
⚙️ Instalação e Execução

1 - Clone o repositório:

``` bash
  git clone <repo-url>
```

<br/>
2 - Configure a API Key do Gemini:

Edite o arquivo src/main/resources/application.properties e adicione sua chave:

Código
gemini.api.key=SUA_API_KEY_AQUI


<br/>
3 - Execute a aplicação:

Inicie a classe principal LangChainGeminiApplication.java

<br/>
4 - Suba os serviços do Prometheus e Grafana:

Execute o comando abaixo na pasta que contém o docker-compose.yml (pode ser a raiz ou iac-empresas):

```bash
  docker-compose up -d
```
<br/>

🔗 Endpoints Úteis

Métricas (Spring Boot Actuator): http://localhost:8080/actuator/metrics

Prometheus: http://localhost:9090/targets

Grafana: http://localhost:3000

<br/>
📚 Referências

📘 Documentação oficial do LangChain4j: https://docs.langchain4j.dev/intro/

🎥 Vídeo introdutório sobre LangChain com Gemini AI Studio: https://www.youtube.com/watch?v=A5i7D7RAPA4&t

🔑 Gerar chave de API do Gemini AI Studio: https://aistudio.google.com/api-keys