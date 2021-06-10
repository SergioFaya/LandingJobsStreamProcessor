# LandingJobsStreamProcessor

Streams processor for landing jobs offers that transforms the landing job offer into a format for my job offer application aggregator.

The landing jobs job JSON object input has the following format:

```
    {
        "id": 1,
        "city": "East Terry",
        "company": "Empresa",
        "country_code": "ML",
        "country_name": "Mali",
        "currency_code": "EUR",
        "expires_at": "2015-05-21",
        "nice_to_have": "Esse veniam vitae. Dolore ipsa sed quam modi quis quidem qui. Culpa facilis illum non. Doloribus est eum sit.",
        "perks": "Officiis aut fugiat temporibus consequatur perspiciatis sint cumque. Natus veritatis ullam rem nihil voluptas. Assumenda pariatur ullam temporibus. Est ut nisi qui voluptates ab provident. In eligendi praesentium ipsa asperiores similique nisi.",
        "published_at": "2015-02-09T18:34:30.270Z",
        "reward": 500,
        "remote": false,
        "relocation_paid": false,
        "role_description": "---\n- Autem nemo quia recusandae harum consequatur eos. Recusandae iure quia modi qui.\n  Repellendus voluptatem dicta illum voluptas impedit deleniti. Enim ipsa ducimus\n  itaque incidunt voluptatem cum. Molestiae saepe nemo qui dolor.\n- Fugit inventore quia. Laboriosam quam ut sint veritatis ut. Similique debitis tempore\n  nulla modi quisquam quae. Vel omnis numquam impedit voluptate dolorem laborum necessitatibus.\n- Numquam reiciendis et id vitae. Molestiae placeat rerum iure. Illum sunt est vel\n  eum. Consequatur sint ut qui voluptas quisquam reiciendis quibusdam.\n",
        "salary_low": null,
        "salary_high": null,
        "successful?": false,
        "title": "Regional Creative Specialist",
        "work_from_home": false,
        "created_at": "2015-02-09T18:34:30.270Z",
        "updated_at": "2015-02-12T19:01:56.042Z",
        "type": "Contract",
        "tags": [
            "Arduino",
            "Oberon"
        ]
    }
```

The output is:

```
{
  "salary": {
    "high": 0,
    "low": 0,
    "currency": null
  },
  "description": "---\n- A aut itaque magni ut. Voluptas quos qui ullam mollitia animi nam. Et qui voluptatibus.\n  Rem dolor numquam veniam impedit tempore.\n- Iusto eius ut. Fugit et non. Cum vero mollitia placeat quae quia. Aut dignissimos\n  aspernatur. Tempora recusandae aut voluptatem voluptatem.\n- Odio eius est. Ea harum quia nam occaecati alias quia cumque. Nemo in quia eos aliquid\n  quos quisquam quo. Corporis a eveniet ab enim.\n",
  "company": "Empresa",
  "location": {
    "country": "Mali",
    "city": "Botsfordborough"
  },
  "title": "Corporate Infrastructure Liason",
  "remote": false,
  "tags": [
    "Go",
    "JScript",
    "Slate"
  ],
  "url_link": "",
  "has_salary": false,
  "published_at": "2015-02-11T00:28:01.683Z"
}
```

The request to get more jobs is: https://landing.jobs/api/v1/jobs

##### Build

```
mvn package
docker build -t sergiofaya/landing-jobs-processor .
```

##### Run
```
docker run sergiofaya/landing-jobs-processor
    or
docker-compose up 
```