package com.example.repo_info;

import com.example.repo_info.Controller.RepositoryController;
import com.example.repo_info.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.repo_info.Model.RepositoryDto;
import com.example.repo_info.Service.GitHubClient;
import com.example.repo_info.Service.RepositoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

@WebMvcTest(controllers = RepositoryController.class)
class RepositoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GitHubClient gitHubClient;

    @MockBean
    private RepositoryService repositoryService;

    @Test
    @DisplayName("GET /repositories/{owner}/{repo} — успешно")
    void shouldReturnRepositoryDto() throws Exception {
        // подготовка DTO, который вернёт сервис
        RepositoryDto dto = new RepositoryDto(
                "octocat/Hello-World",
                "My first repo!",
                "https://github.com/octocat/Hello-World.git",
                42,
                Instant.parse("2011-01-26T19:01:12Z")
        );

        when(repositoryService.getRepository("octocat", "Hello-World"))
                .thenReturn(dto);

        mockMvc.perform(get("/repositories/octocat/Hello-World")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("octocat/Hello-World"))
                .andExpect(jsonPath("$.description").value("My first repo!"))
                .andExpect(jsonPath("$.cloneURL").value("https://github.com/octocat/Hello-World.git"))
                .andExpect(jsonPath("$.stars").value(42))
                .andExpect(jsonPath("$.createdAt").value("2011-01-26T19:01:12Z"));
    }

    @Test
    @DisplayName("GET /repositories/{owner}/{repo} — 404 если не найден")
    void shouldReturn404WhenNotFound() throws Exception {
        when(repositoryService.getRepository(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenThrow(new NotFoundException("Repo not found"));

        mockMvc.perform(get("/repositories/foo/bar"))
                .andExpect(status().isNotFound());
    }
}
