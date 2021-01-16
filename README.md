# LandingJobsStreamProcessor

Streams processor for landing jobs offers that transforms the landing job offer into a format for my job offer application aggregator.

The landing jobs job JSON object has the following fields:

```
[
    {
        "id": 1,
        "city": "East Terry",
        "company_id": 1,
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
    },
    {
        "id": 2,
        "city": "Port Timothyland",
        "company_id": 1,
        "country_code": "ML",
        "country_name": "Mali",
        "currency_code": "EUR",
        "expires_at": "2015-02-11",
        "nice_to_have": "Amet nemo quia temporibus officiis vero et. Accusamus eum itaque vel facere. Totam neque distinctio recusandae qui. Nulla fuga fugiat quis praesentium temporibus.",
        "perks": "Quibusdam adipisci aspernatur aut amet dignissimos nihil asperiores. Aut vel repudiandae. Provident dolore sed ullam. Non corporis fugiat voluptatem sunt ex ea. Et aut quaerat porro maxime voluptatem odit.",
        "published_at": "2015-02-11T13:17:01.621Z",
        "reward": 900,
        "remote": false,
        "relocation_paid": false,
        "role_description": "---\n- Neque nobis ut enim porro autem. Voluptatem nulla et quia. Tenetur enim fugiat quo\n  praesentium eos amet aliquam. Sit facilis tempore quidem quas explicabo ipsam sed.\n  Ab voluptas id illum molestiae.\n- A assumenda nisi doloribus ab. Dolorum omnis qui modi possimus quod saepe rerum.\n  Animi et ea consequatur.\n- Ut corrupti unde qui. Quia a eius veniam occaecati eum libero dicta. Qui et laudantium.\n  Animi blanditiis et facilis dolores consequatur impedit consequuntur.\n",
        "salary_low": null,
        "salary_high": null,
        "successful?": false,
        "title": "Dynamic Infrastructure Liason",
        "work_from_home": false,
        "created_at": "2015-02-11T13:17:01.621Z",
        "updated_at": "2015-02-12T19:01:56.042Z",
        "type": "Part-time",
        "tags": [
            "Lisp",
            "LPC",
            "xBase"
        ]
    },
    {
        "id": 3,
        "city": "Botsfordborough",
        "company_id": 1,
        "country_code": "ML",
        "country_name": "Mali",
        "currency_code": "EUR",
        "expires_at": "2015-04-01",
        "nice_to_have": "Excepturi ratione debitis nulla. Quia aut occaecati repellendus voluptas dolorem animi placeat. Rerum et consequatur dolores cum soluta est molestias. Non nihil nostrum ut pariatur.",
        "perks": "Blanditiis vel ea suscipit et quae. Eveniet magnam pariatur ullam rem ut. Aliquid qui quo voluptatum culpa. Aut eos labore. Et omnis autem assumenda ipsam repudiandae dolor.",
        "published_at": "2015-02-11T00:28:01.683Z",
        "reward": 600,
        "remote": false,
        "relocation_paid": false,
        "role_description": "---\n- A aut itaque magni ut. Voluptas quos qui ullam mollitia animi nam. Et qui voluptatibus.\n  Rem dolor numquam veniam impedit tempore.\n- Iusto eius ut. Fugit et non. Cum vero mollitia placeat quae quia. Aut dignissimos\n  aspernatur. Tempora recusandae aut voluptatem voluptatem.\n- Odio eius est. Ea harum quia nam occaecati alias quia cumque. Nemo in quia eos aliquid\n  quos quisquam quo. Corporis a eveniet ab enim.\n",
        "salary_low": null,
        "salary_high": null,
        "successful?": false,
        "title": "Corporate Infrastructure Liason",
        "work_from_home": false,
        "created_at": "2015-02-11T00:28:01.683Z",
        "updated_at": "2015-02-12T19:01:56.042Z",
        "type": "Freelance",
        "tags": [
            "Go",
            "JScript",
            "Slate"
        ]
    }
]
```

The request to get more jobs is: https://landing.jobs/api/v1/jobs

##### Build

```
mvn package
docker build -t processor/landing-jobs-processor .
```

##### Run
```
docker run processor/landing-jobs-processor
    or
docker-compose up 
```