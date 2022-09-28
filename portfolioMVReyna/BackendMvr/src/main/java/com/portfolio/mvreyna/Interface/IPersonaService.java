
package com.portfolio.mvreyna.Interface;

import com.portfolio.mvreyna.Entity.Persona;
import java.util.List;


public interface IPersonaService {
   //Traer una lista de personas
    public List<Persona> getPersona();
    
    //Guardar un objeto persona
    public void savePersona(Persona persona);
    
    //Eliminar una persona por ID
    public void deletePersona(Long id);
    
    //Buscar una persona
    public Persona findPersona(Long id);
    
}
