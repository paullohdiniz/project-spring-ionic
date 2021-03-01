package br.com.project.springionic.services;

import br.com.project.springionic.controller.domain.Categoria;
import br.com.project.springionic.controller.domain.Cliente;
import br.com.project.springionic.dto.CategoriaDTO;
import br.com.project.springionic.repository.CategoriaRepository;
import br.com.project.springionic.services.exception.DataIntegrityException;
import br.com.project.springionic.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        //return obj.orElse(null);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Nao Encontado"));
    }

    public List<CategoriaDTO> findAll(){
        List<Categoria> categoriaList = categoriaRepository.findAll();

        return categoriaList.stream()
                .map(cat ->
                        new CategoriaDTO(cat.getId(),cat.getNome())).collect(Collectors.toList());
    }
    public Categoria insert(final Categoria categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }
    public Categoria update(final Categoria categoria){
        Categoria newCategoria = findById(categoria.getId());
        udpateData(newCategoria, categoria);
        return categoriaRepository.save(newCategoria);
    }

    private void udpateData(Categoria newCategoria, Categoria categoria) {
        newCategoria.setNome(categoria.getNome());
    }

    public void delete(final Integer id){
        ///Tem tratamento de exceçao
        findById(id);
        try{
            categoriaRepository.deleteById(id);
        }
        catch (DataIntegrityException ex){
            throw new DataIntegrityException("Nao e possivel excluir categoria do produto.");
        }

    }
    public Page<CategoriaDTO> findAllPerPage(Integer page, Integer linePerPage, String orderBy, String direction){
        Pageable pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest)
                .map(cat -> new CategoriaDTO(cat.getId(),cat.getNome()));
    }

    public Categoria fromCategoria(CategoriaDTO categoriaDTO){
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome(), null);
    }
}
