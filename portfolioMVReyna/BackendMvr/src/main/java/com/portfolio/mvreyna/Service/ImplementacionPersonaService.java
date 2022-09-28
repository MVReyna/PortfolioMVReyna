
package com.portfolio.mvreyna.Service;

import com.portfolio.mvreyna.Entity.Persona;
import com.portfolio.mvreyna.Interface.IPersonaService;
import com.portfolio.mvreyna.Repository.IPersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionPersonaService implements IPersonaService{
    @Autowired IPersonaRepository ipersonaRepository;

    @Override
    public List<Persona> getPersona() {
       List<Persona> persona=ipersonaRepository.findAll();
       return persona;
    }

    @Override
    public void savePersona(Persona persona) {
        ipersonaRepository.save(persona);        
    }

    @Override
    public void deletePersona(Long id) {
        ipersonaRepository.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
        Persona persona= ipersonaRepository.findById(id).orElse(null);
        return persona;
    }
    
}
