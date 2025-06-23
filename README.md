# AllegroUMKProject
> A Spring Boot service (“Repo-Info”) that returns details about a GitHub repository.


## 📖 Description

This REST API handles requests to `GET /repositories/{owner}/{repo}` and returns:

- **fullName**: the repository’s full name (`owner/repo`)
- **description**: repository description
- **cloneURL**: Git clone URL
- **stars**: number of stargazers
- **createdAt**: creation date in ISO 8601 format

**Example request:**
```bash
GET http://localhost:8080/repositories/octocat/Hello-World
```

**Example response**
```json
{
  "fullName":   "octocat/Hello-World",
  "description":"My first repo!",
  "cloneURL":   "https://github.com/octocat/Hello-World.git",
  "stars":      42,
  "createdAt":  "2011-01-26T19:01:12Z"
}
```

# 🚀 Quick Start

1. Build and run with Docker:
```shell
docker build -t repo-info:latest .
docker run --rm -p 8080:8080 repo-info:latest
```

2. Verify the service:
```shell
curl http://localhost:8080/repositories/octocat/Hello-World
```

# 📚 API Documentation
- Swagger UI:
```http
http://localhost:8080/docs
```
- OpenAPI JSON:
```http
http://localhost:8080/docs/api-docs
```

# 🧪 Load Testing with Vegeta
1. Create a targets.txt file:
```txt
# targets.txt
GET http://localhost:8080/repositories/octocat/Hello-World
```
2. Run the attack (20 RPS for 1 minute):
```shell
vegeta attack \
  -rate=20 \
  -duration=1m \
  -targets=targets.txt \
| tee results.bin
```
3. Generate a text report:
```shell
vegeta report \
  -type=text \
  -output=report.txt \
  results.bin
```


# 🔧 Possible Enhancements
- GitHub Authentication: use a Personal Access Token for higher rate limits
- Security: CORS, TLS/HTTPS, rate limiting (e.g. Bucket4j)
- Metrics & Monitoring: Prometheus, Grafana, Micrometer
- Resilience: Circuit Breaker, Retry (Resilience4j)
- Container Orchestration: Docker Compose, Kubernetes manifests
- CI/CD: GitHub Actions for build, test, and Docker image publishing
- Logging & Tracing: Sleuth/OpenTelemetry, centralized log aggregation

# ✅ Production Readiness
- Configuration via application.yml and environment variables
- Dockerfile and published Docker image
- Swagger UI and OpenAPI docs at /docs and /docs/api-docs
- Unit, MVC, and E2E tests
- Verified ability to handle ≥20 RPS with P95 < 5 ms

# Dziekuje za ciekawy project 😊