package tech.choupijiang.blog.service;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import org.springframework.data.mongodb.core.MongoOperations;
import reactor.core.publisher.Mono;
import tech.choupijiang.blog.model.Artical;
import tech.choupijiang.blog.repository.ArticalRepository;

import java.util.Date;


@Service
public class ArticalService {

    private final ArticalRepository articalRepository;

    public ArticalService(ArticalRepository articalRepository){
        this.articalRepository = articalRepository;
    }

    public Flux<Artical> findAllArticals(){
        return articalRepository.findAll().log("find all artical");
    }


    public Mono<Void> createArtical(Flux<Artical> articals){
         return articals.flatMap(artical -> {
             return articalRepository.save(artical).log("create blog");
         }).then();
    }

    public Mono<Artical> findOneByName(String name){
        return articalRepository.findByNameContains(name).log("find one artical what name contains " + name);
    }


    public Mono<Artical> findOneById(String id){
        return articalRepository.findById(id);
    }



    @Bean
    CommandLineRunner init(MongoOperations operations){
        return (args)  -> {
            operations.dropCollection(Artical.class);
            operations.insert(new Artical("1", "China Baidu Rise ...", "Robin li saied ...", new Date()));
            operations.insert(new Artical("2", "China Alibaba  Double 11  ...", "China Biggest E-commence ..", new Date()));
            operations.findAll(Artical.class).forEach(artical -> {
                System.out.println("!!!" + artical);
            });
        };
    }


}
