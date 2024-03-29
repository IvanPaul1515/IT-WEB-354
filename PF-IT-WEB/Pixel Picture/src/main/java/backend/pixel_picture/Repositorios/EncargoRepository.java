package backend.pixel_picture.Repositorios;

import backend.pixel_picture.Entidades.EncargoFotografia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncargoRepository extends JpaRepository<EncargoFotografia, Long> {
}
