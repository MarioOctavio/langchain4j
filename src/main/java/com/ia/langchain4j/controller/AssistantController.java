package com.ia.langchain4j.controller;

import com.ia.langchain4j.service.AssistantAiService;
import com.ia.langchain4j.service.MetricService;
import dev.langchain4j.service.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assistant")
public class AssistantController {

    private final AssistantAiService assistantAiService;
    private final MetricService metricService;

    public AssistantController(AssistantAiService assistantAiService, MetricService metricService) {
        this.assistantAiService = assistantAiService;
        this.metricService = metricService;
    }

    @PostMapping
    public String askAssistant(@RequestBody(required = false) String userMessage) {
        String finalMessage = "";
        if(StringUtils.hasText(userMessage)){
            finalMessage =  userMessage.concat(metricService.fetchPrometheusMetrics());
        } else {
            finalMessage = metricService.fetchPrometheusMetrics();
        }

        Result<String> result = assistantAiService.handleRequest(finalMessage);
        return result.content();
    }

}