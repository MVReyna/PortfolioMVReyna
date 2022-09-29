
package com.portfolio.mvreyna.Security.Repository;

import com.portfolio.mvreyna.Security.Entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByNombreUsusario(String nombreUsusario);
    
    boolean existsByNombreUsusario(String nombreUsuario);
    boolean existsByEmail(String email);
}