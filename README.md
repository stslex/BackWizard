# BackWizard

BackWizard is a modular Ktor server application written in Kotlin. The project is structured as a multi‑module Gradle build and provides authentication, user management and admin utilities. The server is packaged as a Docker container and is deployed via GitHub Actions.

## Project Structure

- `app` – main application module containing the `Application.kt` entry point and Ktor plugins setup.
- `core:core` – common utilities such as configuration helpers, response helpers and JWT utilities.
- `core:database` – database plugin using Exposed ORM and PostgreSQL.
- `feature:auth` – registration, login and token refresh logic.
- `feature:user` – endpoints and domain logic for user data.
- `feature:admin` – admin utilities like resetting the user table.

Modules are declared in `settings.gradle.kts` and loaded using Koin for dependency injection.

## Getting Started

1. **Configuration** – supply `app/src/main/resources/application.conf` with database and JWT settings. The repository excludes this file from version control. Tests verify that all required properties exist.
2. **Build** – run `./gradlew build` to compile and test the project.
3. **Docker** – the `Dockerfile` creates a fat jar and exposes port `8080`.
4. **Deployment** – GitHub Actions (`.github/workflows`) build, push and deploy Docker images.

## Further Reading

- [OpenAPI Specification](documentation/documentation.yaml)
- `core` and `feature` modules for detailed implementation of each service.

