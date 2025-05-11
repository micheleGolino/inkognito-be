# inkognito-be

Backend API per app mobile stile Tinder, sviluppato con Quarkus.

## Contenuto del progetto

- **API REST reali**: gestione utenti, messaggi, match, preferenze, ecc.
- **API REST mock** (package `org.it.ms.inkognito.mock`):
  - `/mock/users` — utenti fittizi
  - `/mock/messages` — messaggi fittizi
  - `/mock/matches` — match fittizi
  - `/mock/login` — login mock (restituisce token)
  - `/mock/swipe` — simula swipe (like/dislike)
  - `/mock/feed` — feed utenti suggeriti
  - `/mock/upload-profile-image` — upload immagine profilo mock

Queste risorse permettono di sviluppare e testare l'app mobile senza database.

## Avvio in modalità sviluppo

Per avviare in modalità dev (hot reload):

```sh
./mvnw quarkus:dev
```

Dev UI disponibile su <http://localhost:8080/q/dev/>

## Build e packaging

Per creare il pacchetto (jar):

```sh
./mvnw package
```

Per creare un über-jar:

```sh
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

Esegui con:

```sh
java -jar target/*-runner.jar
```

## Build e run con Docker

Build dell'immagine:

```sh
docker build -t inkognito-be .
```

Esegui il container (porta 8085):

```sh
docker run -p 8085:8085 inkognito-be
```

## Esecuzione nativa (GraalVM)

```sh
./mvnw package -Dnative
```

Oppure in container:

```sh
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

## Altre risorse utili

- [Quarkus](https://quarkus.io/)
- [OpenAPI/Swagger UI](https://quarkus.io/guides/openapi-swaggerui)
- [Hibernate ORM Panache](https://quarkus.io/guides/hibernate-orm-panache)
- [PostgreSQL datasource](https://quarkus.io/guides/datasource)

---

Per dettagli su entità, servizi e mock, consulta il codice nei package `entities`, `services`, `mock`.
