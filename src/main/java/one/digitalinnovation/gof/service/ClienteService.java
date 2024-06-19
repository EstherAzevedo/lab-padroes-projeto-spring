package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.dto.ClienteDTO;
import java.util.List;

public interface ClienteService {

    List<ClienteDTO> buscarTodos();

    ClienteDTO buscarPorId(Long id);

    void inserir(ClienteDTO clienteDTO);

    void atualizar(Long id, ClienteDTO clienteDTO);

    void deletar(Long id);
}
