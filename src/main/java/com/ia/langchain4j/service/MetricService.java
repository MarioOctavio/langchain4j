package com.ia.langchain4j.service;

import com.ia.langchain4j.metrics.CustomMetrics;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Service
public class MetricService {

    private final RestTemplate restTemplate;

    public MetricService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchPrometheusMetrics() {
        StringBuilder finalMessage = new StringBuilder();

        for (Map.Entry<String, List<String>> entry : CustomMetrics.METRIC_CATEGORIES_PROMETHEUS.entrySet()) {
            String categoria = entry.getKey();
            List<String> metricas = entry.getValue();
            StringBuilder builder = new StringBuilder();

            builder.append("Categoria: ").append(categoria).append("\n");

            for (String metrica : metricas) {
                try {
                    URI uri = UriComponentsBuilder.fromHttpUrl(CustomMetrics.BASE_URL_PROMETHEUS)
                            .queryParam("query", metrica)
                            .build()
                            .encode()
                            .toUri();

                    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
                    builder.append("Métrica: ").append(metrica).append("\n");
                    builder.append(response.getBody()).append("\n\n");
                } catch (Exception e) {
                    builder.append("Erro ao consultar ").append(metrica).append(": ").append(e.getMessage()).append("\n\n");
                }
            }

            finalMessage.append(builder);
        }

        return finalMessage.toString();
    }
}
