/*
* Classe auxiliar responsável pela jogada
* randômica do pc
*
* Autor: Laércio Filho
* Data: 04/12/2017
*
* */

package br.com.functions.servidor;

import java.util.Random;

public class JogarPc {
    public int jogar() { /* inicializa a jogada do pc */
        Random rand = new Random();
        int n_jogado_pc = rand.nextInt(6); /* gera um número
        aleatório de 0 a 5 para a jogada do pc */

        return n_jogado_pc; /* retorna o resultado do número aleatório */
    }
}
