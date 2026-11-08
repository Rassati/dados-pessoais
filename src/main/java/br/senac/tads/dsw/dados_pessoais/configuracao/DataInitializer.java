package br.senac.tads.dsw.dados_pessoais.configuracao;

import br.senac.tads.dsw.dados_pessoais.entidade.ConhecimentoEntity;
import br.senac.tads.dsw.dados_pessoais.entidade.PessoaEntity;
import br.senac.tads.dsw.dados_pessoais.entidade.RoleEntity;
import br.senac.tads.dsw.dados_pessoais.entidade.UsuarioSistemaEntity;
import br.senac.tads.dsw.dados_pessoais.repositorio.ConhecimentoRepository;
import br.senac.tads.dsw.dados_pessoais.repositorio.PessoaRepository;
import br.senac.tads.dsw.dados_pessoais.repositorio.RoleRepository;
import br.senac.tads.dsw.dados_pessoais.repositorio.UsuarioSistemaRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PessoaRepository pessoaRepository;
    private final ConhecimentoRepository conhecimentoRepository;
    private final UsuarioSistemaRepository usuarioSistemaRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(PessoaRepository pessoaRepository,
                           ConhecimentoRepository conhecimentoRepository,
                           UsuarioSistemaRepository usuarioSistemaRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.pessoaRepository = pessoaRepository;
        this.conhecimentoRepository = conhecimentoRepository;
        this.usuarioSistemaRepository = usuarioSistemaRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<String> conhecimentos = List.of("Java", "JavaScript", "SQL", "HTML", "CSS", "Docker", "Angular", "React");
        Map<String, ConhecimentoEntity> mapConhecimentos = new HashMap<>();

        if (conhecimentoRepository.count() == 0) {
            for (var c : conhecimentos) {
                ConhecimentoEntity cEnt = new ConhecimentoEntity();
                cEnt.setNome(c);
                mapConhecimentos.put(c, cEnt);
            }
            conhecimentoRepository.saveAll(mapConhecimentos.values());
        } else {
            mapConhecimentos = conhecimentoRepository.findAll().stream()
                .collect(Collectors.toMap(ConhecimentoEntity::getNome, Function.identity()));
        }

        if (pessoaRepository.count() == 0) {
            PessoaEntity p1 = new PessoaEntity();
            p1.setUsername("jpinkman");
            p1.setNome("Jesse Pinkman");
            p1.setEmail("jpinkman@email.com");
            p1.setTelefone("(11) 99000-9999");
            p1.setDataNascimento(LocalDate.parse("2000-10-20"));
            p1.setSenha("Abcd%12345");
            p1.getConhecimentos().add(mapConhecimentos.get("Java"));
            p1.getConhecimentos().add(mapConhecimentos.get("SQL"));
            p1.getConhecimentos().add(mapConhecimentos.get("Docker"));
            pessoaRepository.save(p1);

            PessoaEntity p2 = new PessoaEntity();
            p2.setUsername("Homemferro");
            p2.setNome("Tony Stark");
            p2.setEmail("ironman@email.com");
            p2.setTelefone("(21) 99888-2222");
            p2.setDataNascimento(LocalDate.parse("1999-05-10"));
            p2.setSenha("Abcd%12345");
            p2.getConhecimentos().add(mapConhecimentos.get("Angular"));
            p2.getConhecimentos().add(mapConhecimentos.get("JavaScript"));
            p2.getConhecimentos().add(mapConhecimentos.get("HTML"));
            p2.getConhecimentos().add(mapConhecimentos.get("CSS"));
            pessoaRepository.save(p2);

            PessoaEntity p3 = new PessoaEntity();
            p3.setUsername("Eleven");
            p3.setNome("Eleven Hopper");
            p3.setEmail("eleven@email.com");
            p3.setTelefone("(11) 9999-9323");
            p3.setDataNascimento(LocalDate.parse("2001-02-23"));
            p3.setSenha("Abcd%12345");
            p3.getConhecimentos().add(mapConhecimentos.get("React"));
            p3.getConhecimentos().add(mapConhecimentos.get("JavaScript"));
            p3.getConhecimentos().add(mapConhecimentos.get("HTML"));
            pessoaRepository.save(p3);
        }

        if (usuarioSistemaRepository.count() == 0) {
            RoleEntity roleAdmin = obterOuCriarRole("ROLE_ADMIN");
            RoleEntity roleGerente = obterOuCriarRole("ROLE_GERENTE");
            RoleEntity roleUser = obterOuCriarRole("ROLE_USER");

            UsuarioSistemaEntity admin = new UsuarioSistemaEntity();
            admin.setUsername("admin");
            admin.setSenha(passwordEncoder.encode("Abcd%12345"));
            admin.getRoles().addAll(List.of(roleAdmin, roleGerente, roleUser));

            UsuarioSistemaEntity user1 = new UsuarioSistemaEntity();
            user1.setUsername("user1");
            user1.setSenha(passwordEncoder.encode("Abcd%12345"));
            user1.getRoles().addAll(List.of(roleGerente, roleUser));

            UsuarioSistemaEntity user2 = new UsuarioSistemaEntity();
            user2.setUsername("user2");
            user2.setSenha(passwordEncoder.encode("Abcd%12345"));
            user2.getRoles().add(roleUser);

            usuarioSistemaRepository.saveAll(List.of(admin, user1, user2));
        }
    }

    private RoleEntity obterOuCriarRole(String nome) {
        return roleRepository.findByNome(nome).orElseGet(() -> {
            RoleEntity role = new RoleEntity();
            role.setNome(nome);
            return roleRepository.save(role);
        });
    }
}
