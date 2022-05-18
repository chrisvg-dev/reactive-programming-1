package com.reactive.app1.filtrado;

import com.reactive.app1.models.Persona;
import com.reactive.app1.operador.creacion.Creacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Filtrado {
    private final Logger logger = LoggerFactory.getLogger( Filtrado.class  );

    public void filter(){
        List<Persona> personaList = new ArrayList<>();
        personaList.add( new Persona(1, "CRISTHIAN", 10) );
        personaList.add( new Persona(2, "VILLEGAS", 29) );
        personaList.add( new Persona(3, "GARCIA", 18) );

        Flux.fromIterable( personaList )
                .filter( p -> p.getEdad() > 18 )
                .subscribe( p -> logger.info( p.toString() ) );
    }

    /**
     * DISTINC EVITA LOS DUPLICADOS
     */
    public void distinc(){
        List<Persona> personaList = new ArrayList<>();
        personaList.add( new Persona(1, "CRISTHIAN", 10) );
        personaList.add( new Persona(1, "VILLEGAS", 29) );
        personaList.add( new Persona(3, "GARCIA", 18) );

        Flux.fromIterable( personaList )
                .distinct( Persona::getIdPersona )  // hay que pasarle un parametro
                .subscribe( p -> logger.info( p.toString() ) );
    }

    /**
     * TAKE PERMITE TOMAR N CANTIDAD DE ELEMENTOS DEPENDIENDO DEL PARAMETRO PROPORCIONADO
     * COMENZANDO DESDE EL INICIO
     */
    public void take(){
        List<Persona> personaList = new ArrayList<>();
        personaList.add( new Persona(1, "CRISTHIAN", 10) );
        personaList.add( new Persona(1, "VILLEGAS", 29) );
        personaList.add( new Persona(3, "GARCIA", 18) );

        Flux.fromIterable( personaList )
                .take( 2 )  // hay que pasarle un parametro
                .subscribe( p -> logger.info( p.toString() ) );
    }

    /**
     * TAKE PERMITE TOMAR N CANTIDAD DE ELEMENTOS DEPENDIENDO DEL PARAMETRO PROPORCIONADO
     * COMENZANDO DESDE EL FINAL
     */
    public void takeLast(){
        List<Persona> personaList = new ArrayList<>();
        personaList.add( new Persona(1, "CRISTHIAN", 10) );
        personaList.add( new Persona(1, "VILLEGAS", 29) );
        personaList.add( new Persona(3, "GARCIA", 18) );

        Flux.fromIterable( personaList )
                .takeLast(1)  // hay que pasarle un parametro
                .subscribe( p -> logger.info( p.toString() ) );
    }

    /**
     * skip permite omitir n cantidad de elementos de la lista
     */
    public void skip(){
        List<Persona> personaList = new ArrayList<>();
        personaList.add( new Persona(1, "CRISTHIAN", 10) );
        personaList.add( new Persona(1, "VILLEGAS", 29) );
        personaList.add( new Persona(3, "GARCIA", 18) );

        Flux.fromIterable( personaList )
                .skip(1)  // hay que pasarle un parametro
                .subscribe( p -> logger.info( p.toString() ) );
    }
    /**
     * skip permite omitir n cantidad de elementos de la lista desde el final
     */
    public void skipLast(){
        List<Persona> personaList = new ArrayList<>();
        personaList.add( new Persona(1, "CRISTHIAN", 10) );
        personaList.add( new Persona(1, "VILLEGAS", 29) );
        personaList.add( new Persona(3, "GARCIA", 18) );

        Flux.fromIterable( personaList )
                .skipLast(1)  // hay que pasarle un parametro
                .subscribe( p -> logger.info( p.toString() ) );
    }

}
