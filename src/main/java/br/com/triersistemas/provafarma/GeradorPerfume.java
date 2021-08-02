package br.com.triersistemas.provafarma;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;
import java.math.BigDecimal;

public class GeradorPerfume {
	
	public List<Perfume> gerar() {
		SplittableRandom drS = new SplittableRandom();
		List<Perfume> perfumes = new ArrayList<Perfume>();
		String[] nomes = {
				"Invictus", "Ferrari", "Entity Dulce Amor", "Lady Million", 
				"La Vie Est Belle", "Pure XS", "Oso Woman", 
				"Boss Bottled Night", "Pino Silvestre", "Alien Perfume"
		};
		EnumTipoPerfume[] tipos = {
				EnumTipoPerfume.EAU_DE_PARFUM, EnumTipoPerfume.EAU_DE_TOILETTE
		};
		for (int i = 0; i < 6; i++) {
			BigDecimal valor = BigDecimal.valueOf(drS.nextInt(0, 10000));
			valor = valor.divide(BigDecimal.valueOf(100));
			String nome = nomes[drS.nextInt(0, nomes.length)];
			EnumTipoPerfume tipo = tipos[drS.nextInt(0, tipos.length)];
			perfumes.add(new Perfume(nome, valor, tipo));
		}
		return perfumes;
	}
}
