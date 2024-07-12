package com.ntt.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ManageClientDTO(
        @JsonProperty("Nombre")
        @Size(min = 2, max = 32)
        @Nonnull
        String name,

        @JsonProperty("Genero")
        @Pattern(regexp = "^(male|female)$")
        @Nonnull
        String gender,

        @JsonProperty("Edad")
        @Nonnull
        Integer age,

        @JsonProperty("Identificador")
        @Size(min = 10, max = 13)
        @Nonnull
        String identifier,

        @JsonProperty("Direccion")
        @Size(min = 2, max = 128)
        @Nonnull
        String address,

        @JsonProperty("Telefono")
        @Pattern(regexp = "^(\\+?\\d{1,3}[- ]?)?(\\(?\\d{3}\\)?[- ]?)?[\\d- ]{7,10}$")
        String phone,

        @JsonProperty("Clave")
        @Size(min = 8, max = 64)
        String password
) {
}
