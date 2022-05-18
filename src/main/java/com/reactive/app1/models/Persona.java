package com.reactive.app1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    private Integer idPersona;
    private String nombre;
    private Integer edad;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return Objects.equals(getIdPersona(), persona.getIdPersona()) && Objects.equals(getNombre(), persona.getNombre()) && Objects.equals(getEdad(), persona.getEdad());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPersona(), getNombre(), getEdad());
    }
}
