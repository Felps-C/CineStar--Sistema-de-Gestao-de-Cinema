package org.example.Cinema.Model;

import java.util.ArrayList;
import java.util.List;

public class Estoque {

    public static List<Produto> produtos = new ArrayList<>();

    static {
        produtos.add(new Produto("Pipoca", 50));
        produtos.add(new Produto("Refrigerante", 40));
        produtos.add(new Produto("Chocolate", 30));
        produtos.add(new Produto("Nachos", 20));
    }
}

