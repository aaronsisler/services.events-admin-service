# services.events-admin-service

## Task List

https://github.com/users/aaronsisler/projects/9/views/1

## Running Locally

### Docker

Start the Docker containers

```bash
docker compose -f ./docker-compose.local.yml up -d
```

Stop the Docker containers

```bash
docker compose -f ./docker-compose.local.yml down
```

List out the tables created

**Note** There is an alias assumed if using the `awslocal` command below. The alias assumes you have set the following:

```
awslocal=aws --endpoint-url http://localhost:8000
```

```bash
awslocal dynamodb list-tables
```

List out data in a table

```bash
awslocal dynamodb scan --table-name SERVICES_EVENTS_ADMIN_LOCAL
```

### IntelliJ

Place the below in the Environment Variables

```bash
MICRONAUT_ENVIRONMENTS=local
```

                            <typeMapping>DateTime=LocalDateTime</typeMapping>

<!--                            <dateLibrary>custom</dateLibrary>-->

                        </configOptions>
                        <typeMappings>
                            <typeMapping>LocalDateTime=LocalDateTime</typeMapping>
                            <!--                            <typeMapping>OffsetDateTime=LocalDateTime</typeMapping>-->
                            <!--                            <typeMapping>Date=LocalDate</typeMapping>-->
                            <!--                            <typeMapping>Time=LocalTime</typeMapping>-->
                        </typeMappings>
                        <importMappings>
                            <importMapping>LocalDateTime=LocalDateTime</importMapping>
                            <!--                            <importMapping>java.time.OffsetDateTime=java.time.LocalDateTime</importMapping>-->
                            <!--                            <importMapping>java.time.LocalDate=java.time.LocalDate</importMapping>-->
                            <!--                            <importMapping>java.time.LocalTime=java.time.LocalTime</importMapping>-->
                        </importMappings>