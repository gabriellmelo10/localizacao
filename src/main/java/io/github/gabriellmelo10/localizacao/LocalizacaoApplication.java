package io.github.gabriellmelo10.localizacao;

import io.github.gabriellmelo10.localizacao.domain.entity.Cidade;
import io.github.gabriellmelo10.localizacao.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {
		listarCidadesPorQuantidadeHabitantes();
	}

	void listarCidadesPorQuantidadeHabitantes(){
		cidadeRepository.findByHabitantesLessThanAndNomeLike(8000001L, "Fo%").forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}
}
