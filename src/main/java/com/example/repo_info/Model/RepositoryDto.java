package com.example.repo_info.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryDto {
    private String fullName;
    private String description;
    private String cloneURL;
    private int stars;
    private Instant createdAt;
}