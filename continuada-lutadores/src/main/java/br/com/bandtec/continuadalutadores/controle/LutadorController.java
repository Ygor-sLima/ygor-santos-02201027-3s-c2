package br.com.bandtec.continuadalutadores.controle;

import br.com.bandtec.continuadalutadores.dominio.Golpe;
import br.com.bandtec.continuadalutadores.dominio.Lutador;
import br.com.bandtec.continuadalutadores.repositorio.LutadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lutadores")
public class LutadorController {

    @Autowired
    private LutadorRepository repository;

    @GetMapping
    public ResponseEntity getLutadores() {
        return ResponseEntity.status(200).body(repository.findAllByOrderByForcaGolpeDesc());
    }

    @PostMapping
    public ResponseEntity createLutador(@RequestBody @Valid Lutador novoLutador) {
        repository.save(novoLutador);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/contagem-vivos")
    public ResponseEntity countVivos() {
        return ResponseEntity.status(200).body(repository.countAllByVivoTrue());
    }

    @PostMapping("/{id}/concentrar")
    public ResponseEntity concentrarById(@PathVariable int id) {
        if (repository.existsById(id)) {
            Lutador lutador = repository.findById(id).get();
            if(lutador.getConcentracoesRealizadas() >= 3) {
                return ResponseEntity.status(400).body("Lutador já se concentrou 3 vezes!");
            }else {
                lutador.setConcentracoesRealizadas(lutador.getConcentracoesRealizadas()+1);
                lutador.setVida(lutador.getVida()*1.15);
                repository.save(lutador);
                return ResponseEntity.status(200).build();
            }
        }else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/golpe")
    public ResponseEntity fazerGolpe(@RequestBody @Valid Golpe golpe) {
        if (repository.existsById(golpe.getIdLutadorApanha()) &&
                repository.existsById(golpe.getIdLutadorBate())) {
            Lutador atacante = repository.findById(golpe.getIdLutadorBate()).get();
            Lutador golpeado = repository.findById(golpe.getIdLutadorApanha()).get();

            if(!atacante.isVivo() || !golpeado.isVivo()) {
                return ResponseEntity.status(400).body("Ambos os lutadores devem estar vivos!");
            }

            golpeado.setVida(golpeado.getVida() - atacante.getForcaGolpe());
            if (golpeado.getVida() <= 0) {
                golpeado.setVida(0.0);
                golpeado.setVivo(false);
                repository.save(golpeado);
            }

            List<Lutador> lista = new ArrayList<>();
            lista.add(atacante);
            lista.add(golpeado);
            return ResponseEntity.status(201).body(lista);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/mortos")
    public ResponseEntity getMortos() {
        return ResponseEntity.status(200).body(repository.findAllByVivoFalse());
    }
}
