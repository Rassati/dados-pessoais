package br.senac.tads.dsw.dados_pessoais.repositorio;

import br.senac.tads.dsw.dados_pessoais.entidade.UsuarioSistemaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioSistemaRepository extends JpaRepository<UsuarioSistemaEntity, Integer> {
    Optional<UsuarioSistemaEntity> findByUsername(String username);
}
