package com.reactive.app1.transformacion;

import com.reactive.app1.models.Persona;
import com.reactive.app1.operador.creacion.Creacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Transformacion {
    private final Logger logger = LoggerFactory.getLogger( Creacion.class  );

    public void map() {
        List<Persona> personaList = new ArrayList<>();
        personaList.add( new Persona(1, "CRISTHIAN", 29) );
        personaList.add( new Persona(2, "VILLEGAS", 29) );
        personaList.add( new Persona(3, "GARCIA", 29) );

        /**Flux.fromIterable(personaList)
                .map( p -> {
                    p.setEdad( p.getEdad() + 10 );
                    return p;
                } ).subscribe( p -> logger.info( p.toString() ) ); */

        Flux<Integer> fx = Flux.range(0, 10);
        fx.map( x -> x * 2 ).subscribe( x -> logger.info( "X = " + x ) );
    }

    public void flapMap() {
        List<Persona> personaList = new ArrayList<>();
        personaList.add( new Persona(1, "CRISTHIAN", 29) );
        personaList.add( new Persona(2, "VILLEGAS", 29) );
        personaList.add( new Persona(3, "GARCIA", 29) );

        /**
         * FUNCIONA IGUAL QUE EL MAP, PERO ESTE PIDE QUE SE RETORNE UN NUEVO FLUJO DE INFORMACION
         *
         * EL MAP HACE UN WRAP DEL OBJETO, EL FLATMAP LITERALMENTE LO APLANA
         */

        Flux.fromIterable(personaList)
                .flatMap( p -> {
                    p.setEdad( p.getEdad() + 10 );
                    return Mono.just(p);
                } ).subscribe( p -> logger.info( p.toString() ) );
    }

    public void groupBy() {
        List<Persona> personaList = new ArrayList<>();
        personaList.add( new Persona(1, "CRISTHIAN", 29) );
        personaList.add( new Persona(1, "VILLEGAS", 29) );
        personaList.add( new Persona(3, "GARCIA", 29) );

        Flux.fromIterable(personaList)
                .groupBy(Persona::getIdPersona)
                .flatMap(idFlux -> idFlux.collectList())
                .subscribe( p -> logger.info( p.toString() ) );
    }

}
