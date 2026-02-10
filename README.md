# Task Manager (Java, Spring Boot)
[![Actions Status](https://github.com/vvvilkha/java-project-99/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/vvvilkha/java-project-99/actions)
[![Java CI](https://sonarcloud.io/api/project_badges/measure?project=vvvilkha_java-project-99&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=vvvilkha_java-project-99)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=vvvilkha_java-project-99&metric=bugs)](https://sonarcloud.io/summary/new_code?id=vvvilkha_java-project-99)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=vvvilkha_java-project-99&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=vvvilkha_java-project-99)

## Описание

**Task Manager** — учебный REST API-сервис для управления задачами.
Проект демонстрирует работу с пользователями, задачами, статусами и метками, а также использует Spring Security для аутентификации.

Продакшен-версия: <https://task-management-pw5o.onrender.com>

## Требования

- Java 21+
- Gradle (можно использовать wrapper)

## Как запустить локально

```bash
cd app
./gradlew bootRun
```

После старта приложение будет доступно на `http://localhost:8080`.

## Полезные команды

```bash
  cd app
./gradlew test        # запуск тестов
./gradlew check       # полная проверка (включая линтер)
./gradlew build       # сборка проекта
```
