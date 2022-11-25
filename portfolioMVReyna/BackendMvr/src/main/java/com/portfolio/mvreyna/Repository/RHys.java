
package com.portfolio.mvreyna.Repository;

import com.portfolio.mvreyna.Entity.hys;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RHys extends JpaRepository<hys, Integer>{
    Optional<hys> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
    public boolean existsByPorcentaje(String Porcentaje);
}
