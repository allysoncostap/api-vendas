package com.allyson.rest.controller;


import com.allyson.domain.entity.Produto;
import com.allyson.domain.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("api/produto")
public class ProdutoController {
    @Autowired
    private ProdutosRepository produtos;

   @GetMapping("{id}")
    public Produto getById(@PathVariable Integer id){
       return produtos.findById(id)
               .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto nao encontrado"));
   }

@PostMapping
    @ResponseStatus(CREATED)
    public Produto save(@RequestBody Produto produto){
       return  produtos.save(produto);
}

@DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id ){
        produtos.findById(id).map(p -> {
            produtos.delete(p);
            return Void.TYPE;
        }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado"));


}
@PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Produto produto){
       produtos.findById(id).map( p -> {
          produto.setId((p.getId()));
          produtos.save(produto);
          return produto;
       }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
}
    @GetMapping
    public  List<Produto> busca(Produto prodFiltrado){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );
        Example example = Example.of(prodFiltrado, matcher);
        return produtos.findAll(example);
    }

    @GetMapping("/all")
    public List<Produto> buscarTodos(){
       return produtos.findAll();
    }

}
