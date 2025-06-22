package com.example.repo_info;

import com.example.repo_info.Model.RepositoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class E2ERepositoryTests {
    @LocalServerPort
    int port;
    @Autowired
    TestRestTemplate rest;

    @Test
    void realGithubRepo() {
        ResponseEntity<RepositoryDto> resp = rest.getForEntity(
                "http://localhost:" + port + "/repositories/octocat/Hello-World",
                RepositoryDto.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody().getFullName()).isEqualTo("octocat/Hello-World");
    }
}