/*
* Classe auxiliar responsável por imprimir
* os número jogados pelo player e pelo PC
* na rodada presente
*
* */

package br.com.functions.servidor;

public class DadosPartida {
    private int numeroJogadoPlayer;
    private int numeroJogadoPc;
    private String nicknamePlayer;

    /* constructor */
    public DadosPartida(int numeroJogadoPlayer, int numeroJogadoPc, String nicknamePlayer) {
        this.numeroJogadoPlayer = numeroJogadoPlayer;
        this.numeroJogadoPc = numeroJogadoPc;
        this.nicknamePlayer = nicknamePlayer;
    }
    /* imprime números jogados na partida atual */
    public void imprimirJogadaAtual() {
        System.out.println("*** Dados da Jogada ***");
        System.out.println(this.nicknamePlayer + " jogou o número " + this.numeroJogadoPlayer + ".");
        System.out.println("PC jogou o número " + this.numeroJogadoPc + "\n\r");
    }
}
