package br.com.triersistemas.provafarma;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

@Getter
public class Carrinho {

    private Long id;
    private List<ItemCarrinho> itens;
    private EnumStatusCarrinho status;

    public Carrinho() {
        this.itens = new ArrayList<>();
        this.id = new SplittableRandom().nextLong(100, 1000);
        this.status = EnumStatusCarrinho.ANDAMENTO;
    }

    public void addPerfume(Perfume p, Integer qtd) {
        if (EnumStatusCarrinho.FINALIZADO.equals(this.status)) {
            throw new RuntimeException("Carrinho finalizado");
        }
        this.itens.add(new ItemCarrinho(p, qtd));
    }

    public void finalizar() {
        this.status = EnumStatusCarrinho.FINALIZADO;
    }

    public BigDecimal getValorTotal() {
        return this.itens.stream().map(ItemCarrinho::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public Long getId() {
    	return id;
    }
}