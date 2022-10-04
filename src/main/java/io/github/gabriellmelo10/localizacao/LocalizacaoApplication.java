package io.github.gabriellmelo10.localizacao;

import io.github.gabriellmelo10.localizacao.domain.entity.Cidade;
import io.github.gabriellmelo10.localizacao.domain.repository.CidadeRepository;
import io.github.gabriellmelo10.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeService cidadeService;

	@Override
	public void run(String... args) throws Exception {
		var cidade = new Cidade(null, "porto alegre", null);
		cidadeService
				.filtroDinamico(cidade)
				.forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}
}
