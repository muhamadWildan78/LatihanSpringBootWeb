package com.tabeldata.bootcamp.web.springbootweb.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
    public class Category {

        private Integer category_id;
        private Integer department_id;

        @NotEmpty(message = "ga boleh kosong")
        @Length(min= 4,message = "panjang minimal 4")
        private String name;

        @NotEmpty
        @Length(min= 4,message = "panjang minimal 4")
        private String description;
    }
