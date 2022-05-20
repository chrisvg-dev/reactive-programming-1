package com.reactive.app1.error;

import com.reactive.app1.models.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ErrorOp {
    private static final Logger log = LoggerFactory.getLogger(ErrorOp.class);

    public void retry() {
        List<Persona> personas = new ArrayList<>();
        personas.add( new Persona(1, "CRISTHIAN", 10) );
        personas.add( new Persona(2, "VILLEGAS", 29) );
        personas.add( new Persona(3, "GARCIA", 18) );

        Flux.fromIterable(personas)
                .concatWith( Flux.error(
                        new RuntimeException("ERROR AAAAAA")
                ) )
                .retry(1)
                .doOnNext( x -> log.info( x.toString() ) )
                .subscribe();
    }

    public void errorReturn() {
        List<Persona> personas = new ArrayList<>();
        personas.add( new Persona(1, "CRISTHIAN", 10) );
        personas.add( new Persona(2, "VILLEGAS", 29) );
        personas.add( new Persona(3, "GARCIA", 18) );

        Flux.fromIterable(personas)
                .concatWith(
                        Flux.error(
                                new RuntimeException("aaaa un error")
                        )
                )
                .onErrorReturn( new Persona(0, "ASIES", 99) )
                .subscribe( x -> log.info( x.toString() ) );
    }

    public void errorResume() {
        List<Persona> personas = new ArrayList<>();
        personas.add( new Persona(1, "CRISTHIAN", 10) );
        personas.add( new Persona(2, "VILLEGAS", 29) );
        personas.add( new Persona(3, "GARCIA", 18) );

        Flux.fromIterable(personas)
                .concatWith(
                        Flux.error(
                                new RuntimeException("aaaa un error")
                        )
                )
                .onErrorResume( e -> Mono.just( new Persona(0, "ERRR", 0) ) )
                .subscribe( x -> log.info( x.toString() ) );
    }

    public void errorMap() {
        List<Persona> personas = new ArrayList<>();
        personas.add( new Persona(1, "CRISTHIAN", 10) );
        personas.add( new Persona(2, "VILLEGAS", 29) );
        personas.add( new Persona(3, "GARCIA", 18) );

        Flux.fromIterable(personas)
                .concatWith(
                        Flux.error(
                                new RuntimeException("aaaa un error")
                        )
                )
                .onErrorMap( e -> new InterruptedException(e.getMessage()) )
                .subscribe( x -> log.info( x.toString() ) );
    }
}
