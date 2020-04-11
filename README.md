## Finding Trudeau
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/90a7cc1b72d34f8cabcb58ee502d4ff5)](https://www.codacy.com/manual/makbn/finding_trudeau?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=makbn/finding_trudeau&amp;utm_campaign=Badge_Grade)

Finding Trudeau (FT) is a simple application for crawling `CNN` and `Twitter` looking for any news and tweets about *Justin Trudeau*. 
Plus, you can generate **`wordcloud`** from most important topics and keywords from Mr. Trudeau's tweets!


[![example wordcloud output](https://github.com/makbn/finding_trudeau/raw/master/wcexample.png)](https://twitter.com/JustinTrudeau)

 
A Running instance of this application is accessible from [findingtrudea.sakku.cloud](https://findingtrudea.sakku.cloud) or:

 * [Endpoints](#endpoints)
 * [Build from source code](#how-to-build-the-application)
 * [Components](#components)
 * [Technologies](#technologies)
 * [License](#license)
   
   
## Endpoints
### Fetching Posts

Fetching Posts from supported providers, `CNN` and `Twitter`:

```sh
curl -X GET \
  'http://localhost:8080/post?limitation=30&type=CNN' \
  -H 'cache-control: no-cache'
```

Below table describes available params:

| Parameter      |   Type                    | required | default             | describe |
| ---------------| ------------------------- | :------: | ------------------- | -------------------------------------- |
| limitation     | `int`                     | NO       | 25                  | maximum number of returned             |
| type           | `CNN`, `TWITTER`, or `ALL`| NO       | `ALL`               | filter returned values by provider     |
| from           | `date`                    | NO       | A year ago          | oldest result date                     |
| to             | `date`                    | NO       | Current time        | newest result date                     |

Sample output:

> ```$xslt
> {
>   "error": false,
>    "message": "Ok",
>    "result": {
>        "posts": {
>            "TWITTER": [
>                {
>                    "id": "1246990711519612930",
>                    "content": "Shirley Douglas was a tremendous talent, a tireless advocate, and a fearless activist who never stopped fighting for what she believed in. Her passing is a true loss for our country, and I’m sending my condolences to @RealKiefer and their entire family during this difficult time.",
>                    "link": "https://twitter.com/JustinTrudeau/status/1246990711519612930",
>                    "publishDate": 1586573820184,
>                    "title": "Shirley Douglas was a tremendous ta..."
>                }
>            ]
>        }
>    },
>    "time": 1586575527076
> }
> ```

---

To generate `wordcloud` from Mr. Trudeau's tweets:

```sh
curl -X GET \
    'http://localhost:8081/post/twitter/wordcloud'
```

---
To see the applications health:

```sh
curl -X GET \
    'http://localhost:8081/healthcheck'
```

## How to build the application

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/findingtrudeau-1.0-SNAPSHOT.jar server config.yml`
    * Docker image of the application is available on hub: `docker run -d makbn/finding_trudeau:latest` 
3. To check that your application is running enter url `http://localhost:8080`

* In order to develop this project you need to install `lombok` plugin on your editor.
* Docker Image `latest` tag is built from `master` branch with every change on this branch.

## Technologies
 * **Java 8 SE**: FT is powered by lovely Java version 8 and maven
 * **Dropwizard**: This application is based on `Dropwizard` framework.
 * **HK2**: For dependency injection
 
 And Thanks to:
 
 * [Jefferson Henrique](https://github.com/Jefferson-Henrique) for [GetOldTweets-java](https://github.com/Jefferson-Henrique/GetOldTweets-java)
 * [stopwords-iso](https://github.com/stopwords-iso) for En stopwords
 * [Kenny Cason](https://github.com/kennycason) for [kumo](https://github.com/kennycason/kumo)


## License

**GNU General Public License v3.0**

> Permissions of this strong copyleft license are conditioned on making available complete source code of licensed works and modifications, which include larger works using a licensed work, under the same license. Copyright and license notices must be preserved. Contributors provide an express grant of patent rights.
