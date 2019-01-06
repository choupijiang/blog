package tech.choupijiang.blog.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.choupijiang.blog.model.Artical;
import tech.choupijiang.blog.service.ArticalService;


@RestController
public class ArticalController {

    private static final String ID = "{id:.+}";
    private static final String NAME = "{name:.+}";

    private static final Logger log = LoggerFactory.getLogger(ArticalController.class);
    private final ArticalService articalService;

    public ArticalController(ArticalService articalService){
        this.articalService = articalService;
    }

    @GetMapping(value = {"/", "/articals"})
    Flux<Artical> articals(){
        return articalService.findAllArticals();
    }

    @PostMapping("/articals")
    Mono<Void> create(@RequestBody Flux<Artical> articals){
        return articalService.createArtical(articals).then();
    }

    @GetMapping(value = "/articals/id=" + ID)
    Mono<Artical> articalById(@PathVariable String id){
        return articalService.findOneById(id);
    }

    @GetMapping(value = "/articals/name=" + NAME)
    Mono<Artical> articalByName(@PathVariable String name){
        return articalService.findOneByName(name);
    }

}
