package com.example.repo_info.Service;

import com.example.repo_info.Model.RepositoryDto;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RepositoryService {
    private final GitHubClient client;

    public RepositoryService(GitHubClient client) {
        this.client = client;
    }

    @Cacheable(value = "repos", key = "#owner + '/' + #repo")
    public RepositoryDto getRepository(String owner, String repo) {
        JsonNode node = client.fetchRepo(owner, repo);
        return new RepositoryDto(
                node.get("full_name").asText(),
                node.path("description").asText(null),
                node.get("clone_url").asText(),
                node.get("stargazers_count").asInt(),
                Instant.parse(node.get("created_at").asText())
        );
    }
}
