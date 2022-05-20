package com.reactive.app1.combinacion;

import com.reactive.app1.models.Persona;
import com.reactive.app1.models.Venta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Combinacion {
    private static final Logger log = LoggerFactory.getLogger(Combinacion.class);

    public void merge() {
        List<Persona> personas = new ArrayList<>();
        personas.add( new Persona(1, "CRISTHIAN", 10) );
        personas.add( new Persona(2, "VILLEGAS", 29) );
        personas.add( new Persona(3, "GARCIA", 18) );

        List<Persona> personas2 = new ArrayList<>();
        personas2.add( new Persona(4, "ANGELA", 10) );
        personas2.add( new Persona(5, "BALLADO", 29) );
        personas2.add( new Persona(6, "LAURIANI", 18) );

        List<Venta> ventas = new ArrayList<>();
        ventas.add( new Venta(1, LocalDateTime.now()) );
        ventas.add( new Venta(2, LocalDateTime.now()) );
        ventas.add( new Venta(3, LocalDateTime.now()) );
        ventas.add( new Venta(4, LocalDateTime.now()) );

        Flux<Persona> fx1 = Flux.fromIterable( personas );
        Flux<Persona> fx2 = Flux.fromIterable( personas2 );
        Flux<Venta> fx3 = Flux.fromIterable( ventas );

        Flux.merge(fx1, fx2, fx3).subscribe(
                p -> log.info( p.toString() )
        );
    }

    public void zip() {
        List<Persona> personas = new ArrayList<>();
        personas.add( new Persona(1, "CRISTHIAN", 10) );
        personas.add( new Persona(2, "VILLEGAS", 29) );
        personas.add( new Persona(3, "GARCIA", 18) );

        List<Persona> personas2 = new ArrayList<>();
        personas2.add( new Persona(4, "ANGELA", 10) );
        personas2.add( new Persona(5, "BALLADO", 29) );
        personas2.add( new Persona(6, "LAURIANI", 18) );

        List<Venta> ventas = new ArrayList<>();
        ventas.add( new Venta(1, LocalDateTime.now()) );
        ventas.add( new Venta(2, LocalDateTime.now()) );
        ventas.add( new Venta(3, LocalDateTime.now()) );
        ventas.add( new Venta(4, LocalDateTime.now()) );

        Flux<Persona> fx1 = Flux.fromIterable( personas );
        Flux<Persona> fx2 = Flux.fromIterable( personas2 );
        Flux<Venta> fx3 = Flux.fromIterable( ventas );

        Flux.zip(fx1, fx2, (p1, p2) -> {
            return String.format("Flux1: %s, Flux2: %s", p1, p2);
        }).subscribe( x -> log.info( x ) );

        Flux.zip(fx1, fx2).subscribe( x -> log.info( x.toString() ) );
    }

    public void zipWith() {
        List<Persona> personas = new ArrayList<>();
        personas.add( new Persona(1, "CRISTHIAN", 10) );
        personas.add( new Persona(2, "VILLEGAS", 29) );
        personas.add( new Persona(3, "GARCIA", 18) );

        List<Persona> personas2 = new ArrayList<>();
        personas2.add( new Persona(4, "ANGELA", 10) );
        personas2.add( new Persona(5, "BALLADO", 29) );
        personas2.add( new Persona(6, "LAURIANI", 18) );

        List<Venta> ventas = new ArrayList<>();
        ventas.add( new Venta(1, LocalDateTime.now()) );
        ventas.add( new Venta(2, LocalDateTime.now()) );
        ventas.add( new Venta(3, LocalDateTime.now()) );
        ventas.add( new Venta(4, LocalDateTime.now()) );

        Flux<Persona> fx1 = Flux.fromIterable( personas );
        Flux<Persona> fx2 = Flux.fromIterable( personas2 );
        Flux<Venta> fx3 = Flux.fromIterable( ventas );

        //fx1.zipWith(fx2, (p1, p2) -> {
        //    return String.format("Flux1: %s, Flux2: %s", p1, p2);
        //}).subscribe(x -> log.info(x.toString()));

        fx1.zipWith(fx3, (p1, v1) -> {
            return String.format("Flux1: %s, Flux2: %s", p1, v1);
        }).subscribe(x -> log.info(x.toString()));
    }
}
