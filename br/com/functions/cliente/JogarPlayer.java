package br.com.functions.cliente;

import java.util.Scanner;

public class JogarPlayer {

    public int jogar() {
        int numero_jogado_player;

        System.out.print("Digite um número de 0 à 5: ");
        Scanner t = new Scanner(System.in);
        numero_jogado_player = t.nextInt(); /* captura o valor jogado pelo player */
        System.out.print("\n\r"); /* "enter" */

        if (numero_jogado_player < 0 || numero_jogado_player > 5) { /* condição de controle */
            /* se o valor jogado pelo player for menor que 0 ou maior que 5, então entra aqui */
            System.out.println("Número inválido. Aguarde para jogar novamente.\n\r");
            try {
                Thread.sleep(2000); /* load de 2 segundos antes de possibilitar ao
                 player realizar uma nova jogada */
            } catch (InterruptedException in) {}
            jogar(); /* inicializa a função novamente */
        }

        return numero_jogado_player; /* retorna valor jogado pelo player */
    }
}
