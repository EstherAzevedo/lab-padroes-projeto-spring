package one.digitalinnovation.gof.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import one.digitalinnovation.gof.dto.ClienteDTO;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.service.ClienteService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> buscarTodos() {
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
        return clientes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return convertToDto(cliente);
    }

    @Override
    public void inserir(ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        cliente.setId(id);
        clienteRepository.save(cliente);
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO convertToDto(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setEndereco(cliente.getEndereco());
        return clienteDTO;
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setEndereco(clienteDTO.getEndereco());
        return cliente;
    }
}
