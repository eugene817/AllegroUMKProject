# AllegroUMKProject

# Co mozna dodac
- logowanie z GitHub zeby mozna bylo wysylac wiecej requestow
- Security (CORS, TLS, Rate Limiting)
- Statystyki (graphana, prometheus)
- Moze byc dodac testy

## For production
- Fill the CI
- Docker-compose/k8s integration
- If github login, .env or github secrets
- Security 100%
- Statistics 100%

# V stands for Vegeta:
- Atack
```shell
vegeta attack \
  -rate=20 \
  -duration=1m \
  -targets=targets.txt \
| tee results.bin
```

- Stats:
```shell
vegeta report \
  -type=text \
  -output=report.txt \
  results.bin
```

# Run in docker container
```shell
docker build -t repo-info:latest .
docker run --rm -p 8080:8080 repo-info:latest
```

# Docs:
```http
localhost:8080/docs
```
## or in json
```http
localhost:8080/docs/api-docs
```

## Dziekuje za ciekawy projekt i za zajecia :)

---

Create a simple REST service that will return details of the given GitHub repository. Details
should include:
- full name of the repository
- description of the repository
- git clone URL
- number of stargazers
- date of creation (ISO format)
  The API of the service should look as follows:
  GET /repositories/{owner}/{repository-name}
  {
  "fullName": "...",
  "description": "...",
  "cloneURL": "...",
  "stars": 0,
  "createdAt": "..."
  }
  GitHub API reference can be found at: https://docs.github.com/en/rest?apiVersion=2022-11-28
  Non-functional requirements:
- should be able to serve 20 requests per second (as in: no obvious bottlenecks)
- set of end-to-end tests that can be run using some build tool (Gradle or Maven preferred)
- good design and quality of code
- almost ready to deploy on production (if additional work is needed, please write what it
  is)
  The GitHub API imposes a rate limit, so consider using a cache to reach the desired
  performance. This could be an in-memory solution or a custom implementation using a
  database. To simulate heavy endpoint traffic and conduct performance tests you can use tools
  such as k6, Vegeta, ab, etc.
  It is OK to make tradeoffs or to simplify the solution as long as you leave a note describing
  your thought process.