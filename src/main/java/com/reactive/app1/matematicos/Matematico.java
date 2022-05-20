package com.reactive.app1.matematicos;

import com.reactive.app1.models.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Matematico {
    public static final Logger log = LoggerFactory.getLogger(Matematico.class);

    public void average() {
        List<Persona> personas = new ArrayList<>();
        personas.add( new Persona(1, "CRISTHIAN", 10) );
        personas.add( new Persona(2, "VILLEGAS", 29) );
        personas.add( new Persona(3, "GARCIA", 18) );
        personas.add( new Persona(3, "GARCIA", 9) );

        Flux.fromIterable( personas )
                .collect(Collectors.averagingInt(Persona::getEdad))
                .subscribe( x -> log.info(x.toString()) );
    }

    public void count() {
        List<Persona> personas = new ArrayList<>();
        personas.add( new Persona(1, "CRISTHIAN", 10) );
        personas.add( new Persona(2, "VILLEGAS", 29) );
        personas.add( new Persona(3, "GARCIA", 18) );
        personas.add( new Persona(3, "GARCIA", 9) );

        Flux.fromIterable( personas )
                .count()
                .subscribe( x -> log.info(x.toString()) );
    }

    public void min() {
        List<Persona> personas = new ArrayList<>();
        personas.add( new Persona(1, "CRISTHIAN", 10) );
        personas.add( new Persona(2, "VILLEGAS", 29) );
        personas.add( new Persona(3, "GARCIA", 18) );
        personas.add( new Persona(3, "GARCIA", 9) );

        Flux.fromIterable( personas )
                .collect(Collectors.minBy( Comparator.comparing(Persona::getEdad) ))
                .subscribe( x -> log.info(x.get().toString()) );
    }

    public void sum() {
        List<Persona> personas = new ArrayList<>();
        personas.add( new Persona(1, "CRISTHIAN", 10) );
        personas.add( new Persona(2, "VILLEGAS", 29) );
        personas.add( new Persona(3, "GARCIA", 18) );
        personas.add( new Persona(3, "GARCIA", 9) );

        Flux.fromIterable( personas )
                .collect(Collectors.summingInt(Persona::getEdad) )
                .subscribe( x -> log.info(x.toString()) );
    }
}
