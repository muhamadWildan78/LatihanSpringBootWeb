package com.tabeldata.bootcamp.web.springbootweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Example {
    private String id;
    @NotNull(message = "gak boleh null")
    @NotEmpty(message = "gak boleh kosong")
    private String message;
    private String other;
    @Min(value = 0, message = "gak boleh minus")
    private BigDecimal salary;
    @NotNull(message = "gak boleh null")
    @JsonProperty(value = "is_active")
    private Boolean isActive;
    private LocalDateTime transactionDatetime;
    private LocalDate transactionDate;
    private String extra;
}
