package pl.lotto;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
interface MongoTestContainer {

    String DOCKER_IMAGE_NAME = "mongo:4.0.10";
    @Container
    MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse(DOCKER_IMAGE_NAME));

    @DynamicPropertySource
    private static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
}
