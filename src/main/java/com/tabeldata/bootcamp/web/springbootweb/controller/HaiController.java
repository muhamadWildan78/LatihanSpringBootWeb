package com.tabeldata.bootcamp.web.springbootweb.controller;

import com.tabeldata.bootcamp.web.springbootweb.dao.CategoryDao;
import com.tabeldata.bootcamp.web.springbootweb.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
    @RequestMapping("/api")
    public class HaiController {

        @Autowired
        private CategoryDao dao;

        @GetMapping("/list")
        public List<Category> list() {
            return dao.list();
        }

        @GetMapping("/findById/{id}")
         public ResponseEntity<?> findById(@PathVariable Integer id) {
            try {
                Category data = this.dao.findById(id);
                return ResponseEntity.ok(data);
            } catch (EmptyResultDataAccessException erdae) {
                return ResponseEntity.noContent().build();
            }
        }

        @GetMapping(value = "/category/show")
        public Category showData(
                @RequestParam(name = "ctg") Integer category,
                @RequestParam(name = "dpt") Integer department,
                @RequestParam(name = "nme") String name,
                @RequestParam(name = "dsc") String description){
            Category data = new Category();
            data.setCategory_id(category);
            data.setDepartment_id(department);
            data.setName(name);
            data.setDescription(description);
            return data;
        }

        @PostMapping(value = "/input")
        public ResponseEntity<Map<String, Object>>
        insertData(@RequestBody @Valid Category data, BindingResult result) {
            Map<String, Object> hasil = new HashMap<>();
            hasil.put("id", dao.insertCategory(data));
            hasil.put("status", "simpan Berhasil");
            return ResponseEntity.ok(hasil);
        }
//            try {
//                this.dao.insertCategory(data);
//                return ResponseEntity.ok().build();
//            } catch (DuplicateKeyException dke) {
//                dke.printStackTrace();
//                return ResponseEntity.badRequest()
//                        .body("Duplicate data");
//            }catch (DataAccessException dea){
//                dea.printStackTrace();
//                return ResponseEntity.internalServerError()
//                        .body("database gak konek atau sql salah");
//            }
//            catch (Exception ex){
//                ex.printStackTrace();
//                return ResponseEntity.internalServerError()
//                        .body("Gak tau errornya apa! check sendiri");
//            }

        @PostMapping("/update")
        public ResponseEntity<Map<String, Object>>
        updateCategory(@RequestBody Category cate){
            Map<String, Object> hasil = new HashMap<>();
            dao.updateCategory(cate);
            hasil.put( "id", 0);
            hasil.put("status", "update Berhasil");
            return ResponseEntity.ok(hasil);
        }

        @DeleteMapping("/category/{kodeid}")
        public ResponseEntity<?> delete(@PathVariable String kodeid) {
            this.dao.deleteCategory(kodeid);
            return ResponseEntity.ok().build();
        }
    }