package io.github.gabriellmelo10.localizacao.service;

import io.github.gabriellmelo10.localizacao.domain.entity.Cidade;
import io.github.gabriellmelo10.localizacao.domain.repository.CidadeRepository;
import static io.github.gabriellmelo10.localizacao.domain.repository.specs.CidadeSpecs.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Transactional
    public void salvarCidade(){
        var cidade = new Cidade(1L, "São Paulo", 12396372l);
        cidadeRepository.save(cidade);
    }

    public void listarCidadesPorQuantidadeHabitantes(){
        cidadeRepository
                .findByHabitantesLessThanAndNomeLike(8000001L, "Fo%")
                .forEach(System.out::println);
    }

    public void findByHabitantesLessThan(Long habitantes){
        cidadeRepository
                .findByHabitantesLessThan(8000000L)
                .forEach(System.out::println);
    }

    public void findByHabitantesGreaterThan(Long habitantes){
        cidadeRepository
                .findByHabitantesLessThan(8000000L)
                .forEach(System.out::println);
    }

    public void findByHabitantesLessThanEqual(Long habitantes){
        cidadeRepository
                .findByHabitantesLessThan(8000000L)
                .forEach(System.out::println);
    }

    public void listarCidades(){
        cidadeRepository
                .findAll()
                .forEach(System.out::println);
    }

    public void findByNomeLikeOrdenado(){
        cidadeRepository
                .findByNomeLikeOrdenado("%a%", Sort.by("habitantes"))
                .forEach(System.out::println);
    }

    public void findByNomeLikePaginado(){
        Pageable pageable = PageRequest.of(1, 2);
        cidadeRepository
                .findByNomeLikePaginado("%%%%", pageable)
                .forEach(System.out::println);
    }

    public void listarCidadesPorNomeSQL(){
        cidadeRepository
                .findByNomeSqlNativo("São Paulo")
                .forEach(System.out::println);
    }

    public void listarCidadesPorNomeSQLProjection(){
        cidadeRepository
                .findByNomeSqlNativoProjection("São Paulo")
                .stream().map(cidadeProjeciton -> new Cidade(cidadeProjeciton.getId(), cidadeProjeciton.getNome(), null))
                .forEach(System.out::println);
    }

    public void listarCidadesPorNome(){
        cidadeRepository
                .findByNome("Porto Velho")
                .forEach(System.out::println);
    }

     public void listarCidadesPorNomeIniciandoCom(){
        cidadeRepository
                .findByNomeStartingWith("Porto")
                .forEach(System.out::println);
    }

    public void listarCidadesPorNomeTerminandoCom(){
        cidadeRepository
                .findByNomeEndingWith("a")
                .forEach(System.out::println);
    }

    public void listarCidadesPorNomeContendo(){
        cidadeRepository
                .findByNomeContaining("a")
                .forEach(System.out::println);
    }

    public void listarCidadesPorHabitantes(){
        cidadeRepository
                .findByHabitantes(78787900L)
                .forEach(System.out::println);
    }

    public List<Cidade> filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase();
        Example<Cidade> example = Example.of(cidade, matcher);
        return cidadeRepository.findAll(example);
    }

    public void listarCidadesBySpec(){
        cidadeRepository
                .findAll(nomeEqual("São Paulo").and(habitantesGreaterThan(1000L)))
                .forEach(System.out::println);
    }

    public void listarCidadesSpecsFiltroDinamico(Cidade filtro){
        Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());

        //select * from cidade where 1 = 1

        if(filtro.getId() != null){
            specs = specs.and( idEqual(filtro.getId()) );
        }

        if(StringUtils.hasText(filtro.getNome())){
            specs = specs.and( nomeLike(filtro.getNome()) );
        }

        if(filtro.getHabitantes() != null){
            specs = specs.and( habitantesGreaterThan(filtro.getHabitantes()) );
        }

        cidadeRepository
                .findAll(specs)
                .forEach(System.out::println);
    }
}
