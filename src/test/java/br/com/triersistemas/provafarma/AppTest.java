package br.com.triersistemas.provafarma;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {

    private static BigDecimal nota = BigDecimal.ZERO;

    @BeforeAll
    public static void antesTest() {
        System.out.println("Nota 1 - 0");
        nota = BigDecimal.ZERO;

        System.out.println("Nota 2 - 0.5");
        nota = nota.add(BigDecimal.valueOf(0.5));
    }

    @AfterAll
    public static void DepoisTest() {
        System.out.println("Total: " + nota);
    }

    @Test
    @Order(0)
    public void criarMatrizLongTest() {

        Map<Long, Boolean> check = new HashMap<>();
        var b = new Blocos();
        for (int x = 0; x < 5000; x++) {
            var m = b.criarMatrizLong();
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[i].length; j++) {
                    final long val = m[i][j];
                    assertTrue(val >= 0 || val <= 9);
                    if (Objects.isNull(check.get(val))) {
                        check.put(val, true);
                    }
                }
            }
        }
        for (long i = 0; i < 10; i++) {
            assertTrue(check.get(i));
        }
        System.out.println("Nota 3a - 0.5");
        nota = nota.add(BigDecimal.valueOf(0.5));
    }

    @Test
    @Order(1)
    public void criarMatrizCharTest() {

        Map<String, Boolean> check = new HashMap<>();
        var b = new Blocos();
        final char[] texto = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
        for (int x = 0; x < 9000; x++) {
            var m = b.criarMatrizChar();
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[i].length; j++) {
                    final char val = m[i][j];
                    if (Objects.isNull(check.get(val))) {
                        check.put(String.valueOf(val), true);
                    }
                }
            }
        }
        for (int i = 0; i < texto.length; i++) {
            assertTrue(check.get(String.valueOf(texto[i])));
        }
        System.out.println("Nota 3b - 2");
        nota = nota.add(BigDecimal.valueOf(2));
    }

    @Test
    @Order(2)
    public void classPerfumeTest() {
        var p1 = new Perfume("p1", BigDecimal.ONE, EnumTipoPerfume.EAU_DE_PARFUM);
        var p2 = new Perfume("p2", BigDecimal.TEN, EnumTipoPerfume.EAU_DE_TOILETTE);

        for (int i = 0; i < 9000; i++) {
            assertTrue(p1.getId() >= 10 || p1.getId() <= 99);
            assertEquals(p1.getNome(), "p1");
            assertEquals(p1.getValor(), BigDecimal.ONE);
            assertEquals(p1.getTipo(), EnumTipoPerfume.EAU_DE_PARFUM);

            assertTrue(p2.getId() >= 10 || p1.getId() <= 99);
            assertEquals(p2.getNome(), "p2");
            assertEquals(p2.getValor(), BigDecimal.TEN);
            assertEquals(p2.getTipo(), EnumTipoPerfume.EAU_DE_TOILETTE);
        }
        System.out.println("Nota 4 - 1");
        nota = nota.add(BigDecimal.valueOf(1));
    }

    @Test
    @Order(3)
    public void gerarTest() {

        final String[] nomes = {
                "Invictus", "Ferrari", "Entity Dulce Amor", "Lady Million", "La Vie Est Belle",
                "Pure XS", "Oso Woman", "Boss Bottled Night", "Pino Silvestre", "Alien Perfume"
        };

        Map<Long, Boolean> checkL = new HashMap<>();
        Map<String, Boolean> checkS = new HashMap<>();
        Map<BigDecimal, Boolean> checkB = new HashMap<>();
        Map<EnumTipoPerfume, Boolean> checkE = new HashMap<>();

        var g = new GeradorPerfume();
        for (int x = 0; x < 999999; x++) {
            var perfumes = g.gerar();
            assertEquals(6, perfumes.size());
            for (Perfume perfume : perfumes) {
                extracted(checkL, perfume.getId());
                extracted(checkS, perfume.getNome());
                extracted(checkB, perfume.getValor());
                extracted(checkE, perfume.getTipo());
            }
        }
        assertEquals(90, checkL.size());
        for (Map.Entry<Long, Boolean> item : checkL.entrySet()) {
            assertTrue(item.getKey() >= 10 && item.getKey() <= 99);
        }
        for (int i = 0; i < nomes.length; i++) {
            assertTrue(checkS.get(nomes[i]));
        }

        assertEquals(10000, checkB.size());
        for (Map.Entry<BigDecimal, Boolean> item : checkB.entrySet()) {
            final BigDecimal val = item.getKey();
            assertTrue(val.toString().length() <= 5);
            assertTrue(val.compareTo(BigDecimal.ZERO) >= 0 && val.compareTo(BigDecimal.valueOf(99.99)) <= 0);
        }

        assertEquals(2, checkE.size());
        for (Map.Entry<EnumTipoPerfume, Boolean> item : checkE.entrySet()) {
            assertTrue(EnumTipoPerfume.EAU_DE_PARFUM.equals(item.getKey()) || EnumTipoPerfume.EAU_DE_TOILETTE.equals(item.getKey()));
        }
        System.out.println("Nota 5 - 3");
        nota = nota.add(BigDecimal.valueOf(3));
    }

    private <T> void extracted(Map check, T val) {
        if (Objects.isNull(check.get(val))) {
            check.put(val, true);
        }
    }

    @Test
    @Order(4)
    public void carrinhoTest() {

        var p1 = new Perfume("p1", BigDecimal.ONE, EnumTipoPerfume.EAU_DE_PARFUM);
        var p2 = new Perfume("p2", BigDecimal.TEN, EnumTipoPerfume.EAU_DE_TOILETTE);

        final Carrinho car1 = new Carrinho();
        car1.addPerfume(p1, 5);
        car1.addPerfume(p2, 3);

        assertEquals(2, car1.getItens().size());
        assertEquals(BigDecimal.valueOf(5), car1.getItens().get(0).getValorTotal());
        assertEquals(BigDecimal.valueOf(30), car1.getItens().get(1).getValorTotal());
        assertEquals(BigDecimal.valueOf(35), car1.getValorTotal());

        car1.finalizar();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            car1.addPerfume(p2, 1);
        });
        assertEquals("Carrinho finalizado", exception.getMessage());

        final Carrinho car2 = new Carrinho();

        var perfumes = new GeradorPerfume().gerar();
        for (Perfume perfume : perfumes) {
            car2.addPerfume(perfume, 1);
        }
        car2.finalizar();

        assertEquals(6, car2.getItens().size());

        System.out.println("Nota 6 - 3");
        nota = nota.add(BigDecimal.valueOf(3));
    }
}
