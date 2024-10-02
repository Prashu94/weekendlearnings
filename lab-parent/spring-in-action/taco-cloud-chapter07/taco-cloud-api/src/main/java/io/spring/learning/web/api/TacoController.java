package io.spring.learning.web.api;

import io.spring.learning.data.TacoRepository;
import io.spring.learning.tacos.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class TacoController {
    private TacoRepository tacoRepository;

    public TacoController(TacoRepository tacoRepository){
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(page).getContent();
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }

    @GetMapping("/{id}")
    public Optional<Taco> tacoById(@PathVariable("id") Long id) {
        return tacoRepository.findById(id);
    }
}
