# beuta

## Run

To run unit tests
```shell
sbt test
```

To run integration tests
```shell
sbt it:test
```

To run the project through sbt
```shell
sbt run
```

## Build fat jar 

The project uses  [sbt-assembly](https://github.com/sbt/sbt-assembly) to create a "fat" jar
```
sbt assembly
```

The plugin is configured explicitly to run unit and integration tests as part of the `assembly` task. The generated jar is `target/scala-3.2.0/beuta.jar`

The fat jar can be run locally using
```
java -jar target/scala-3.2.0/beuta.jar
```

## Docker

[Dockerfile](Dockerfile) contains Docker [multi-stage](https://docs.docker.com/develop/develop-images/multistage-build/) instructions to assembly a lightweight image that only contains the final jar.
Intermediate steps are used to fetch sbt and build the jar. The final image is based on `jre-slim-buster`.

Assuming `docker` is up and running, the image can be built using
```
docker build -t <image tag> --build-arg JAVA_VERSION=11 --build-arg SBT_VERSION=1.7.1 -f ./Dockerfile .
```

and can later be run using
```
docker run -p 8080:8080 <image tag>
```

For portability, the project is setup to rely directly on [Dockerfile](Dockerfile) instead of using sbt plugins like [sbt-docker](https://github.com/marcuslonnberg/sbt-docker).
In the future, we might explore [sbt-native-image](https://github.com/scalameta/sbt-native-image)

To run docker on MAC OS X, the following can be helpful https://github.com/docker/buildx/issues/426#issuecomment-723208580

## Check

To check that the project is running fine (either when running it through `sbt run` or through docker), assuming `curl` is available, use
```
curl http://localhost:8080/status

// {"status":"ok"}
```