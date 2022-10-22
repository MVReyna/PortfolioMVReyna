
package com.portfolio.mvreyna.Security.Repository;

import com.portfolio.mvreyna.Security.Entity.Rol;
import com.portfolio.mvreyna.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
