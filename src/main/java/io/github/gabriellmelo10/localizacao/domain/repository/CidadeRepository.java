package io.github.gabriellmelo10.localizacao.domain.repository;

import io.github.gabriellmelo10.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
