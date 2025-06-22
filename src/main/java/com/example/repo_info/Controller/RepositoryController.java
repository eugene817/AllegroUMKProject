package com.example.repo_info.Controller;

import com.example.repo_info.Model.RepositoryDto;
import com.example.repo_info.Service.RepositoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repositories")
public class RepositoryController {
    private final RepositoryService service;

    public RepositoryController(RepositoryService service) {
        this.service = service;
    }

    @Operation(summary = "Get information of GitHub-repo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully found"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{owner}/{repo}")
    public RepositoryDto getRepo(
            @PathVariable String owner,
            @PathVariable("repo") String repo) {
        return service.getRepository(owner, repo);
    }
}
