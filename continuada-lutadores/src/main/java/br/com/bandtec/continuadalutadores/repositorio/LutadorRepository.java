package br.com.bandtec.continuadalutadores.repositorio;

import br.com.bandtec.continuadalutadores.dominio.Lutador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LutadorRepository extends JpaRepository<Lutador, Integer> {

    List<Lutador> findAllByOrderByForcaGolpeDesc();

    Integer countAllByVivoTrue();

    List<Lutador> findAllByVivoFalse();
}
