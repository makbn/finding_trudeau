## Finding Trudeau
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/90a7cc1b72d34f8cabcb58ee502d4ff5)](https://www.codacy.com/manual/makbn/finding_trudeau?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=makbn/finding_trudeau&amp;utm_campaign=Badge_Grade)

Finding Trudeau is a simple application for crawling `CNN` and `Twitter` looking for any news and tweets about *Justin Trudeau*. A Running instance of this application is accessible from [findingtrudea.sakku.cloud](https://findingtrudea.sakku.cloud) or:

 * [Endpoints](#endpoints)
 * [Build from source code](#how-to-build-the-application)
 * [Components](#components)
 * [Development](#development)
 * [Technologies](#technologies)
   

How to build the application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/findingtrudeau-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

## Endpoints
### Fetching Posts

Fetching Posts from supported providers, `CNN` and `Twitter`:

```sh
curl -X GET \
  'http://localhost:8080/post?limitation=30&type=CNN' \
  -H 'cache-control: no-cache'
```

To see your applications health:

```sh
curl -X GET \
    'http://localhost:8081/healthcheck'
```
