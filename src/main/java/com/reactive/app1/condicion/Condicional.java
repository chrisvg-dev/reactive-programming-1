package com.reactive.app1.condicion;

import com.reactive.app1.combinacion.Combinacion;
import com.reactive.app1.models.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Condicional {
    private static final Logger log = LoggerFactory.getLogger(Combinacion.class);

    public void defaultIsEmpty (){
        Mono.just( new Persona(1, "CRISTHIAN", 29) )
        //Mono.empty()
        //Flux.empty()
                .defaultIfEmpty( new Persona(0, "", 0))
                .subscribe( x -> log.info( x.toString() ) );
    }

    public void takeUntil (){
        List<Persona> personas = new ArrayList<>();
        personas.add( new Persona(1, "CRISTHIAN", 10) );
        personas.add( new Persona(2, "VILLEGAS", 29) );
        personas.add( new Persona(3, "GARCIA", 18) );

        // TOMA LOS ELEMENTOS QUE NO CUMPLAN CON LA CONDICION
        Flux.fromIterable( personas ).takeUntil( persona -> persona.getEdad() > 10 ).subscribe(
                persona -> log.info(persona.toString())
        );
    }

    public void timeOut (){
        List<Persona> personas = new ArrayList<>();
        personas.add( new Persona(1, "CRISTHIAN", 10) );
        personas.add( new Persona(2, "VILLEGAS", 29) );
        personas.add( new Persona(3, "GARCIA", 18) );
        personas.add( new Persona(3, "GARCIA", 9) );

        // TOMA LOS ELEMENTOS QUE CUMPLAN CON LA CONDICION
        Flux.fromIterable( personas )
                .delayElements(Duration.ofSeconds(3)) // HACE UNA PAUSA EN EL FLUJO DE INFORMACION
                .timeout( Duration.ofSeconds(2) ) // SI EL RETRASO EXCEDE EL TIEMPO DE ESPERA, SE CIERRA EL FLUJO
                .subscribe(persona -> log.info(persona.toString()));
    }


}
