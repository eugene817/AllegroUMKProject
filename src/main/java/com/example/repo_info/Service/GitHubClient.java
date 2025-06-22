package com.example.repo_info.Service;

import com.example.repo_info.Exception.NotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class GitHubClient {
    private final RestTemplate restTemplate;

    public GitHubClient(RestTemplateBuilder builder) {
        this.restTemplate = builder
                .rootUri("https://api.github.com")
                .build();
    }

    public JsonNode fetchRepo(String owner, String repo) {
        String uri = String.format("/repos/%s/%s", owner, repo);
        try {
            ResponseEntity<JsonNode> resp = restTemplate.getForEntity(uri, JsonNode.class);
            return resp.getBody();
        } catch (HttpClientErrorException.NotFound ex) {
            throw new NotFoundException("Repository not found");
        }
    }
}
