package br.com.project.screenmatch.controller;

import br.com.project.screenmatch.domain.dto.SerieDTO;
import br.com.project.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping("/{id}")
    public SerieDTO getId(@PathVariable Long id) {
        return service.getId(id);
    }

    @GetMapping
    public List<SerieDTO> getSeries() {
    return service.getAllSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> getTop5Series() {
        return service.getTop5Series();
    }
}

/*
@RestController - Anotação que indica que a classe é um controller do tipo Rest.

@GetMapping(value = "api/albuns") - Os métodos de nosso controller são métodos normais, com este atributo podemos indicar que este método responde a requisições usando o verbo Get na URL api/albuns. Também existem os outros, como PutMapping, PostMapping e DeleteMapping.

@GetMapping(value = "api/albuns/{id}") - Podemos criar outros métodos, para outras URLs, no caso podemos criar um outra URL que recebe um id, e usar isso como parâmetro, assim se a seguinte URL for acessada: /api/Albuns/4 nosso método será executando recebendo 4 como parâmetro.

@GetMapping(value = "api/albuns/nome/{nomeParam}") - Podemos usar também para criarmos URLs mais específicas, aqui criarmos a URL /api/Albuns/nome/, que recebe um parâmetro. Para transformar partes da URL em parâmetro devemos criar entre { } e usar o mesmo nome em um dos parâmetros do método.

@PathVariable - Indica que a variável a seguir é um parâmetro de URL, ou seja, virá dos valores marcados entre chaves nas notações de Mapping.
 */