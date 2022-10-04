package io.github.gabriellmelo10.localizacao.domain.repository;

import io.github.gabriellmelo10.localizacao.domain.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

    //busca pelo nome like ordenado
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    List<Cidade> findByNomeLikeOrdenado(String nome, Sort sort);

    //busca pelo nome like paginado
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    Page<Cidade> findByNomeLikePaginado(String nome, Pageable pageable);

    List<Cidade> findByNome(String nome);

    List<Cidade> findByNomeStartingWith(String nome);

    List<Cidade> findByNomeEndingWith(String nome);

    List<Cidade> findByNomeContaining(String nome);

    List<Cidade> findByHabitantes(Long habitantes);

    List<Cidade> findByHabitantesLessThan(Long habitantes);

    List<Cidade> findByHabitantesGreaterThan(Long habitantes);

    List<Cidade> findByHabitantesLessThanEqual(Long habitantes);

    List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);
}
