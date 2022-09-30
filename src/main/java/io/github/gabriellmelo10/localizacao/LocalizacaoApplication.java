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
		//listarCidadesPorNomeIniciandoCom();
		//listarCidadesPorNomeTerminandoCom();
		listarCidadesPorNomeContendo();
	}

	@Transactional
	void salvarCidade(){
		var cidade = new Cidade(1L, "São Paulo", 12396372l);
		cidadeRepository.save(cidade);
	}

	void listarCidades(){
		cidadeRepository.findAll().forEach(System.out::println);
	}

	void listarCidadesPorNome(){
		cidadeRepository.findByNome("Porto Velho").forEach(System.out::println);
	}

	void listarCidadesPorNomeIniciandoCom(){
		cidadeRepository.findByNomeStartingWith("Porto").forEach(System.out::println);
	}

	void listarCidadesPorNomeTerminandoCom(){
		cidadeRepository.findByNomeEndingWith("a").forEach(System.out::println);
	}

	void listarCidadesPorNomeContendo(){
		cidadeRepository.findByNomeContaining("a").forEach(System.out::println);
	}

	void listarCidadesPorHabitantes(){
		cidadeRepository.findByHabitantes(78787900L).forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}
}
