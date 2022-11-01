package com.portfolio.mvreyna.Controller;

import com.portfolio.mvreyna.DTO.dtoEducacion;
import com.portfolio.mvreyna.Entity.Educacion;
import com.portfolio.mvreyna.Security.Controller.Mensaje;
import com.portfolio.mvreyna.Service.SEducacion;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
// @CrossOrigin(origins="http://localhost:4200")
@CrossOrigin(origins="https://mvrfrontend.web.app")

public class CEducacion {
    @Autowired
    SEducacion sEducacion;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list= sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion>getById(@PathVariable("id") int id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion= sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoEdu){
       if (StringUtils.isBlank (dtoEdu.getNombreEdu())){
           return new ResponseEntity (new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
       }
       if (sEducacion.existsByNombreEdu(dtoEdu.getNombreEdu())){
           return new ResponseEntity(new Mensaje("Esa educación existe"), HttpStatus.BAD_REQUEST);
       }
       
       if (StringUtils.isBlank (dtoEdu.getDescripcionEdu())){
           return new ResponseEntity (new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
       }
       
       Educacion educacion = new Educacion(dtoEdu.getNombreEdu(),dtoEdu.getDescripcionEdu());
       sEducacion.save(educacion);
       
       return new ResponseEntity(new Mensaje("Educación creada"),HttpStatus.OK);
    }
  
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
         //Validaci+on si existe ID
       if(!sEducacion.existsById(id))
           return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
       
       sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Educación eliminada"), HttpStatus.OK);
    }
     
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoEdu){
        //Validaci+on si existe ID
       if(!sEducacion.existsById(id)){
           return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
       }
        //Compara las experiencia para no duplicar
       if(sEducacion.existsByNombreEdu(dtoEdu.getNombreEdu())&& sEducacion.getByNombreEdu(dtoEdu.getNombreEdu()).get().getId()!=id){
           return new ResponseEntity(new Mensaje("El nombre ya existe"),HttpStatus.BAD_REQUEST);
       }
        //No permite campos vacios
       if(StringUtils.isBlank(dtoEdu.getNombreEdu())){
           return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
       }
      Educacion educacion= sEducacion.getOne(id).get();
      
      educacion.setNombreEdu(dtoEdu.getNombreEdu());
      educacion.setDescripcionEdu(dtoEdu.getDescripcionEdu());
      
      sEducacion.save(educacion);
      return new ResponseEntity(new Mensaje("Educación actualizada"),HttpStatus.OK);
    }
}