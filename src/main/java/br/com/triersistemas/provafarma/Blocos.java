package br.com.triersistemas.provafarma;

import java.util.SplittableRandom;

public class Blocos {
	
	public Long[][] criarMatrizLong() {
		SplittableRandom drS = new SplittableRandom();
		Long[][] matriz = new Long[16][16];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = drS.nextLong(0, 10);
			}
		}
		return matriz;
	}
	
	public char[][] criarMatrizChar() {
		char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		char[][] matriz = new char[16][16];
		SplittableRandom drS = new SplittableRandom();
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = letras[drS.nextInt(0, letras.length)];
			}
		}
		return matriz;
	}
}
