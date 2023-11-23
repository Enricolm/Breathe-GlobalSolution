package com.example.breathe.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.breathe.models.Bairro;
import com.example.breathe.models.Cidade;
import com.example.breathe.models.Diagnostico;
import com.example.breathe.models.Doenca;
import com.example.breathe.models.Endereco;
import com.example.breathe.models.Estado;
import com.example.breathe.models.Logradouro;
import com.example.breathe.models.Usuario;
import com.example.breathe.repository.BairroRepository;
import com.example.breathe.repository.CidadeRepository;
import com.example.breathe.repository.DiagnosticoRepository;
import com.example.breathe.repository.DoencaRepository;
import com.example.breathe.repository.EnderecoRepository;
import com.example.breathe.repository.EstadoRepository;
import com.example.breathe.repository.LogradouroRepository;
import com.example.breathe.repository.UsuarioRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    DoencaRepository doencaRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    BairroRepository bairroRepository;

    @Autowired
    LogradouroRepository logradouroRepository;

    @Autowired 
    EnderecoRepository enderecoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    DiagnosticoRepository diagnosticoRepository;


    @Override
    public void run(String... args) throws Exception {

        Doenca d1 = new Doenca(1L, "Sinusite", "Faz espirrar", "Rinossoro");
        Doenca d2 = new Doenca(2L, "Asma", "Perder ar", "Usar bombinha");
        doencaRepository.saveAll(List.of(d1, d2));

        Estado e1 = new Estado(1L, "São Paulo", "SP");
        Estado e2 = new Estado(2L, "Rio de Janeiro", "RJ");
        estadoRepository.saveAll(List.of(e1, e2));

        Cidade c1 = new Cidade(1L, 21,"Niteroi", 121, e2);
        Cidade c2 = new Cidade(2L, 11,"Guarulhos", 123, e1);
        cidadeRepository.saveAll(List.of(c1, c2));
        

        Bairro b1 = new Bairro (1L, "Pimentas", "Sul", c2);
        Bairro b2 = new Bairro (2L, "Vila Nossa Senhora de Fátima", "Sul", c2);
        bairroRepository.saveAll(List.of(b1, b2));

        Logradouro l1 = new Logradouro(1L, "57600-580","Rua Angelo", 132, b2);
        Logradouro l2 = new Logradouro(2L, "38405-004","Rua Esmeralda", 122, b2);
        logradouroRepository.saveAll(List.of(l1, l2));

        Endereco end1 = new Endereco(1L, "Mercado", "12/10/2004", "20/12/2015", l1);
        Endereco end2 = new Endereco(2L, "Praça", "02/07/2014", "12/03/2018", l2);
        enderecoRepository.saveAll(List.of(end1, end2));

        Usuario u1 = new Usuario(1L, "fernando@gmail.com", "0k03N]rE!t:p", end1);
        Usuario u2 = new Usuario(2L, "cesar@gmail.com", "yR35z8_nx;J7", end2);
        usuarioRepository.saveAll(List.of(u1, u2));

        Diagnostico dia1 = new Diagnostico(1L, d1, u1);
        Diagnostico dia2 = new Diagnostico(2L, d2, u2);
        diagnosticoRepository.saveAll(List.of(dia1, dia2));
        
        


    }



}
