package com.tabeldata.bootcamp.web.springbootweb.controller;
import com.tabeldata.bootcamp.web.springbootweb.model.Example;
import com.tabeldata.bootcamp.web.springbootweb.dao.ExampleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/example/api")
public class HaloController {

    @Autowired
    private ExampleDao dao;

    @GetMapping("/list")
    public List<Example> list() {
        return dao.list();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            Example data = this.dao.findById(id);
            return ResponseEntity.ok(data);
        } catch (EmptyResultDataAccessException erdae) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/show")
    public Example showData(
            @RequestParam(name = "msg", required = true) String message,
            @RequestParam(name = "oth", required = false) String other,
            BigDecimal salary,
            Boolean active) {
        Example data = new Example();
        data.setMessage(message);
        data.setOther(other);
        data.setSalary(salary);
        data.setIsActive(active);
        data.setTransactionDate(LocalDate.now());
        data.setTransactionDatetime(LocalDateTime.now());
        return data;
    }
    @PostMapping(value = "/input")
    public ResponseEntity<?> inputData(@RequestBody @Valid Example data) {
        try {
            this.dao.insert(data);
            return ResponseEntity.ok().build();
        } catch (DuplicateKeyException dke) {
            dke.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("Duplicate data");
        }catch (DataAccessException dea){
            dea.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("database gak konek atau sql salah");
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Gak tau errornya apa! check sendiri");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        this.dao.delete(id);
        return ResponseEntity.ok().build();
    }

}