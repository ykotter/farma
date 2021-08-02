package br.com.triersistemas.provafarma;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.SplittableRandom;

@Getter
public class ItemCarrinho {

    private Long id;
    private Perfume perfume;
    private Integer qtd;
    private BigDecimal valorTotal;

    public ItemCarrinho(final Perfume p, final Integer qtd) {
        this.id = new SplittableRandom().nextLong(100, 1000);
        this.perfume = p;
        this.qtd = qtd;
        this.valorTotal = p.getValor().multiply(BigDecimal.valueOf(qtd));
    }
}