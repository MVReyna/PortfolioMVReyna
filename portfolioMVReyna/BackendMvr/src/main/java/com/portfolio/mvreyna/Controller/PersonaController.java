
package com.portfolio.mvreyna.Controller;

import com.portfolio.mvreyna.DTO.dtoPersona;
import com.portfolio.mvreyna.Entity.Persona;
import com.portfolio.mvreyna.Security.Controller.Mensaje;
import com.portfolio.mvreyna.Service.ImplementacionPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
// @CrossOrigin(origins="http://localhost:4200")
@CrossOrigin(origins="https://mvrfrontend.web.app")
public class PersonaController {
   @Autowired
    ImplementacionPersonaService sPersona;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list= sPersona.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona>getById(@PathVariable("id") int id){
        if(!sPersona.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.BAD_REQUEST);
        }
        
        Persona persona= sPersona.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    /*@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtoEdu){
       if (StringUtils.isBlank (dtoEdu.getNombre())){
           return new ResponseEntity (new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
       }
       if(sPersona.existsByNombre(dtoPersona.getNombre())){
           return new ResponseEntity(new Mensaje("Esa educación existe"), HttpStatus.BAD_REQUEST);
       }
       
       Persona persona = new Persona(dtoPersona.getNombre(),dtoPersona.getDescripcion());
       sPersona.save(persona);
       
       return new ResponseEntity(new Mensaje("Persona creada"),HttpStatus.OK);
    }*/
  
    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
         //Validaci+on si existe ID
       if(!sPersona.existsById(id))
           return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
       
       sPersona.delete(id);
        return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);
    }*/
     
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtoPersona){
        //Validaci+on si existe ID
       if(!sPersona.existsById(id)){
           return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
       }
        //Compara las experiencia para no duplicar
       if(sPersona.existsByNombre(dtoPersona.getNombre())&& sPersona.getByNombre(dtoPersona.getNombre()).get().getId()!=id){
           return new ResponseEntity(new Mensaje("El nombre ya existe"),HttpStatus.BAD_REQUEST);
       }
        //No permite campos vacios
       if(StringUtils.isBlank(dtoPersona.getNombre())){
           return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
       }
      Persona persona= sPersona.getOne(id).get();
      
      persona.setNombre(dtoPersona.getNombre());
      persona.setDescripcion(dtoPersona.getDescripcion());
      
      sPersona.save(persona);
      return new ResponseEntity(new Mensaje("Persona actualizada"),HttpStatus.OK);
    }
}
