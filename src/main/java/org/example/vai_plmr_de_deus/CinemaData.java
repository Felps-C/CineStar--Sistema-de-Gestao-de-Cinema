package org.example.vai_plmr_de_deus;

import java.util.ArrayList;
import java.util.List;

public class CinemaData {

    public static List<Filme> filmes = new ArrayList<>();

    static {
        filmes.add(new Filme(
                "Interestelar",
                "Christopher Nolan",
                "10 anos",
                "Ficção Científica",
                "2h49min",
                25.00,
                List.of("14:00", "18:00", "21:30")
        ));

        filmes.add(new Filme(
                "O Poderoso Chefão",
                "Francis Ford Coppola",
                "14 anos",
                "Drama",
                "2h55min",
                30.00,
                List.of("15:00", "19:00")
        ));

        filmes.add(new Filme(
                "Toy Story",
                "John Lasseter",
                "Livre",
                "Animação",
                "1h21min",
                20.00,
                List.of("13:00", "16:00", "18:30")
        ));
    }
}

