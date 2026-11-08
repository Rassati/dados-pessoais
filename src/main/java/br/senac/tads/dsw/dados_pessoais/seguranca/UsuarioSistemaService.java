package br.senac.tads.dsw.dados_pessoais.seguranca;

import br.senac.tads.dsw.dados_pessoais.entidade.RoleEntity;
import br.senac.tads.dsw.dados_pessoais.entidade.UsuarioSistemaEntity;
import br.senac.tads.dsw.dados_pessoais.repositorio.UsuarioSistemaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioSistemaService implements UserDetailsService {

    private final UsuarioSistemaRepository usuarioSistemaRepository;

    public UsuarioSistemaService(UsuarioSistemaRepository usuarioSistemaRepository) {
        this.usuarioSistemaRepository = usuarioSistemaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioSistemaEntity entity = usuarioSistemaRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado: " + username));

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : entity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getNome()));
        }

        return new UsuarioSistema(entity.getUsername(), entity.getSenha(), authorities);
    }
}
