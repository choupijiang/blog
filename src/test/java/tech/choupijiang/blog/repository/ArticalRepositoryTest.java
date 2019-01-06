package tech.choupijiang.blog.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoOperator;
import reactor.test.StepVerifier;
import tech.choupijiang.blog.model.Artical;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest (excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class ArticalRepositoryTest {

    @Autowired
    ArticalRepository repository;

    @Autowired
    MongoOperations operations;


//    @Before
//    public void setUp(){
//        operations.dropCollection(Artical.class);
//        operations.insert(new Artical("1", "China Baidu Rise ...", "Robin li saied ...", new Date()));
//        operations.insert(new Artical("2", "China Alibaba  Double 11  ...", "China Biggest E-commence ..", new Date()));
//        operations.findAll(Artical.class).forEach(artical -> {
//            System.out.println("!!!" + artical);
//        });
//    }

    @Test
    public void findByIdShouldWork(){
        Mono<Artical> articalMono = repository.findById("1");

        StepVerifier.create(articalMono).expectNextMatches(
                results -> {
                    assertThat(results.getId()).isEqualTo("1");
                    assertThat(results.getName()).isEqualTo("China Baidu Rise ...");
                    return true;
                }
        ).verifyComplete();

    }

    @Test
    public void findByNameContainsShouldWOrk(){
        Mono<Artical> articalMono = repository.findByNameContains("Alibaba");
        StepVerifier.create(articalMono).expectNextMatches(
                results -> {
                    assertThat(results.getId()).isEqualTo("2");
                    assertThat(results.getName()).isEqualTo("China Alibaba  Double 11  ...");
                    return true;
                }
        ).verifyComplete();
    }



}
