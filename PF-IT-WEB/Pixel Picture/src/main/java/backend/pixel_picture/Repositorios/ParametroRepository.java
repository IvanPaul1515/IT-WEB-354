package backend.pixel_picture.Repositorios;

import backend.pixel_picture.entidades.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Integer> {
}
