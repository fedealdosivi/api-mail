package apimail.Repository;

import apimail.Model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    List<Mensaje> findByRemitente(String remitente);

    @Query("SELECT m FROM Mensaje m JOIN m.destinatarios d WHERE d = :email")
    List<Mensaje> findByDestinatario(@Param("email") String email);
}
