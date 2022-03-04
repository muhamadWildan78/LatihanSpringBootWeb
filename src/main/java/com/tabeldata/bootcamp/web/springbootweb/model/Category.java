package com.tabeldata.bootcamp.web.springbootweb.model;

import lombok.Data;

    @Data
    public class Category {
        private Integer category_id;
        private Integer department_id;
        private String name;
        private String description;
    }
