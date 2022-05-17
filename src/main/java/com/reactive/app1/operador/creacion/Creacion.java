package com.reactive.app1.operador.creacion;

import com.reactive.app1.App1Application;
import com.reactive.app1.models.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Creacion {
    private final Logger logger = LoggerFactory.getLogger( Creacion.class  );

    public void justFrom() {
        Mono.just(new Persona(1, "asies", 29));
    }

    /**
     * Para expresar elementos vacios puedes usar
     * Mono.empty()
     * Flux.empty()
     */
    public void empty() {
        Mono.empty();
        Flux.empty();
    }

    /**
     * FLUX PERMITE RECORRER RANGOS Y APLICAR TRANSFORMACIONES A LOS ELEMENTOS DEL RANGO
     */
    public void range() {
        Flux.range(0, 3).doOnNext( i -> logger.info("i: " + i) )
                .subscribe();
    }

    public void repeatFlux () {
        List<Persona> personaList = new ArrayList<>();
        personaList.add( new Persona(1, "CRISTHIAN", 29) );
        personaList.add( new Persona(2, "VILLEGAS", 29) );
        personaList.add( new Persona(3, "GARCIA", 29) );

        Flux.fromIterable( personaList )
                .repeat(3)
                .subscribe( p -> logger.info( p.toString() ) );
    }

    public void repeatMono () {
        Mono.just( new Persona(1, "CRISTHIAN", 29) )
                .repeat( 3 )
                .subscribe( p -> logger.info( p.toString() ) );
    }

}
