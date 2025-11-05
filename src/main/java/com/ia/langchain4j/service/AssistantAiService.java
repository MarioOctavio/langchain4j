package com.ia.langchain4j.service;

import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AssistantAiService {

    @SystemMessage("""
          Avalie as métricas abaixo coletadas via Spring Boot Actuator de uma aplicação Java em produção.
        
          Instruções da análise:

          Seja objetiva e concisa.

          Relate apenas métricas com anomalias, gargalos ou riscos de desempenho/estabilidade.

          Ignore métricas vazias, normais ou dentro dos padrões aceitáveis.

          Se aplicável, indique melhorias práticas ou alertas críticos recomendados.

          Cite boas práticas somente se forem diretamente relevantes.

          Não inclua redundâncias, explicações genéricas ou informações desnecessárias.
        
          Formato de resposta sugerido:
          Categoria: <nome>
              - Problema: <descrição curta>
              - Impacto: <impacto resumido>
              - Ação sugerida: <recomendação prática>
        """)
    Result<String> handleRequest(@UserMessage String userMessage);
}