# SmartGarage QA Automation (Java, JUnit 5, Selenium, REST Assured)

End‑to‑end and API test suite for the **SmartGarage** application. The project mixes **web UI (Selenium 4)** and **REST API (REST Assured 5)** tests, organized with **JUnit 5** and reported with **Allure 2**. Tests are consistently tagged so you can run only API, only UI, or everything in one command.

---

## Tech stack

- Java 17, Maven
- JUnit 5 (Jupiter) with `@Tag`
- Selenium 4 (UI/System tests)
- REST Assured 5 (API/Integration tests)
- Allure 2 (reporting) — Maven plugin + CLI
- Gson, Lombok, Faker
- (Optional) MariaDB JDBC for DB utilities

> Allure Maven & JUnit 5 are configured in the POM, including the Allure plugin and Surefire provider. Allure results are written under `target/allure-results` (see `src/test/resources/allure.properties`).

---

## Project layout

```
src/
  main/
    java/
      com.smartgarage.api/           # API client layer (ServicesApi, CustomerApi, VehicleApi, ClientCarsApi, …)
      com.smartgarage.pages/         # Page Objects (AdminPanelPage, ServiceOverviewPage, sections/, …)
      com.testframework/             # Core: DriverManager, enums (BrowserType, BrowserMode), Base* classes, RequestSpecFactory
    resources/
  test/
    java/
      api/                           # API tests (e.g., users, services, vehicles, clientCars)
      web/                           # UI tests (pages/entities/services)
    resources/
      config.properties              # Environment & browser settings
      allure.properties              # Allure output directory
```

Key base classes:
- `com.testframework.core.BaseApiTest` — common REST Assured setup & Allure filter.
- `com.testframework.core.BaseWebTest` — WebDriver lifecycle and page initialization.
- `com.testframework.core.BaseApiService` & `RequestSpecFactory` — base URI and Basic Auth from config.

---

## Run the SmartGarage app locally (Docker)

⚠️ **Prerequisite:** You need to have [Docker](https://docs.docker.com/get-docker/) installed and running on your machine.
This repo includes the application source so you can spin it up in Docker and then run the tests against it.
### Folder layout (place these at the **repo root**)
```
/app                 # SmartGarage app (Docker build context)
/database            # MariaDB init & Dockerfile (build context)
/setup-docker/
   docker-compose.yml
```
> The provided `docker-compose.yml` (under `setup-docker/`) builds the two services using relative paths `../app` and `../database`, so keep this structure exactly.

### Start the stack
```bash
# Build & run
cd setup-docker
docker compose build
docker compose up -d
# go back to project root to run the tests
cd ..
```

- App: http://localhost:8081
- MariaDB: localhost:3307 (container port 3306 is mapped to host 3307)

Check status and logs:
```bash

docker compose ps
docker compose logs -f app
docker compose logs -f mariadb
```

Stop & clean:
```bash

docker compose down           # stop
docker compose down -v        # stop + remove DB volume (fresh database)
```

## Test tagging and how to select tests

This suite uses **JUnit 5 `@Tag`** to partition tests:

- **API tests** → `@Tag("integration")` (plus fine‑grained tags like `users-api`, `services-api`)
- **UI (Selenium) tests** → `@Tag("system")`

Run subsets with Maven Surefire’s **groups** / **excludedGroups** (JUnit Platform provider):

```bash
# Run ONLY API tests
mvn clean test -Dgroups=integration

# Run ONLY UI (Selenium) tests
mvn clean test -Dgroups=system

# Run both API + UI
mvn clean test -Dgroups=integration,system

# Run by a more specific tag (e.g., only Users API)
mvn clean test -Dgroups=users-api

# Exclude a tag (e.g., skip UI)
mvn clean test -DexcludedGroups=system

# Combine include/exclude
mvn clean test -Dgroups=integration -DexcludedGroups=services-api
```

> Maven Surefire’s JUnit Platform provider maps JUnit 5 **tags** to the **`groups`** and **`excludedGroups`** CLI properties, and also supports tag expressions. See the official docs.

---

## Running with Allure reports

1) **Install Allure CLI** (one‑time):

- macOS (Homebrew):
  ```bash
  brew install allure
  allure --version
  ```

- Other OS options: see an Allure installation guide. (https://allurereport.org/docs/install/)

1) **Execute tests and open the report**:

```bash
# Run ALL tests + serve Allure report
mvn clean test allure:serve

# Only API tests (integration) + Allure
mvn clean test -Dgroups=integration allure:serve

# Only UI tests (system) + Allure
mvn clean test -Dgroups=system allure:serve

# Generate static report (no temp server)
mvn clean test allure:report
```
Allure Maven plugin basics and goals are documented here.

> Allure results are generated to `target/allure-results`, and `allure:serve` spins up a temporary web server with the HTML report.

### Allure annotations you’ll see in code
- `@Epic`, `@Feature`, (optionally `@Story`, `@Severity`, etc.) to organize the report’s **Behaviors** tree.

---

## Configuration

Edit `src/test/resources/config.properties` for environment and browser setup:

```properties
smartGarageUrl=http://localhost:8081/
adminUsername=felix_jackson
adminPassword=password123%D
browserType=CHROME        # CHROME | FIREFOX | EDGE
browserMode=NORMAL # NORMAL | HEADLESS
defaultTimeoutSeconds=10
```

- **API base URL & credentials** are read by `RequestSpecFactory` and applied to every API request (Basic auth, JSON content type).
- **Browser** is configured by `DriverManager` using `browserType` and `browserMode` (e.g., headless CI runs).

> Java 17 and the Allure + Surefire + Compiler plugins are pinned in the POM.

---

## Common commands

```bash
# Smoke everything locally
mvn clean test

# Just API (integration)
mvn clean test -Dgroups=integration

# Just UI (system)
mvn clean test -Dgroups=system

# API + UI + open Allure
mvn clean test -Dgroups=integration,system allure:serve

# Regenerate a static Allure report after a run
mvn allure:report
```

---

## Troubleshooting

- **No tests run / 0 tests found**: verify your tag filters (e.g., did you pass `-Dgroups` that excludes all?).
- **Allure report doesn’t open**: ensure the **Allure CLI** is installed and on PATH; check that `target/allure-results` exists after a test run.
- **WebDriver errors**: confirm `browserType`, `browserMode`, and that a compatible browser is installed on the machine/agent.
- **Wrong base URL / 401**: update `smartGarageUrl` and credentials in `config.properties` (used by `RequestSpecFactory`).

---

## License

Internal training project. See the repository for details.
