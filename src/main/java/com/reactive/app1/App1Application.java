package com.reactive.app1;

import com.reactive.app1.models.Persona;
import com.reactive.app1.operador.creacion.Creacion;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class App1Application implements CommandLineRunner {
	private final Logger logger = LoggerFactory.getLogger( App1Application.class  );

	public static void main(String[] args) {
		SpringApplication.run(App1Application.class, args);
	}

	public void reactor () {
		Mono.just( new Persona(1, "Mito", 29))
				.doOnNext( p -> logger.info( "[REACTOR] Persona " + p.toString() ) )
				.subscribe( p -> logger.info( "[REACTOR] Persona " + p.toString() ) );
	}

	public void rxjava2() {
		Observable.just(new Persona(1, "Mito", 29))
				.doOnNext( p -> logger.info( "[REACTOR] Persona " + p.toString() ) )
				.subscribe( p -> logger.info( "[RXJava2] Persona " + p.toString() ) );
	}

	public void mono() {
		Mono.just( new Persona(1, "Mito", 29) ).subscribe(p -> logger.info(p.toString()));
	}

	public void flux() {
		List<Persona> personaList = new ArrayList<>();
		personaList.add( new Persona(1, "CRISTHIAN", 29) );
		personaList.add( new Persona(2, "VILLEGAS", 29) );
		personaList.add( new Persona(3, "GARCIA", 29) );

		Flux.fromIterable( personaList ).subscribe( p -> logger.info( p.toString() ) );
	}

	public void fluxMono () {
		List<Persona> personaList = new ArrayList<>();
		personaList.add( new Persona(1, "CRISTHIAN", 29) );
		personaList.add( new Persona(2, "VILLEGAS", 29) );
		personaList.add( new Persona(3, "GARCIA", 29) );

		Flux<Persona> fx = Flux.fromIterable( personaList );
		fx.collectList().subscribe( lista -> logger.info( lista.toString() ) );
	}

	@Override
	public void run(String... args) throws Exception {
		//reactor();
		//rxjava2();

		//mono();
		//flux();
		//fluxMono();

		Creacion app = new Creacion();
		app.repeatMono();
	}
}
