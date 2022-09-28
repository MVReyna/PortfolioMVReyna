
package com.portfolio.mvreyna.Repository;

import com.portfolio.mvreyna.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository <Persona,Long> {
    
}
