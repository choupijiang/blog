package tech.choupijiang.blog.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import tech.choupijiang.blog.model.Artical;


public interface ArticalRepository extends ReactiveCrudRepository<Artical, String> {
    Mono<Artical> findByNameContains(String name);

    Mono<Artical> save(Artical artical);

    Mono<Artical> findById(String id);

}
