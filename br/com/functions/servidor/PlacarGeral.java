/*
* Classe auxiliar responsável por imprimir
* no console do servidor o placar geral do
* jogo.
*
* Autor: Laércio Filho
* Data: 04/12/2017
*
* */

package br.com.functions.servidor;

public class PlacarGeral {
    /* variáveis globais */
    private String nickname;
    private int pontos_player;
    private int pontos_pc;

    public PlacarGeral( /* constructor para inicializar a variáveis globais */
            String nickname, int pontos_player, int pontos_pc) {

        this.nickname = nickname;
        this.pontos_player = pontos_player;
        this.pontos_pc = pontos_pc;
    }
    public void placar() { /* imprime o placar geral no console do servidor */
        System.out.println("*** Placar ***");
        /* pontuação player */
        if (this.pontos_player == 0 || this.pontos_player > 1) {
            System.out.println(this.nickname + " = " + this.pontos_player + " pontos"); /* plural */
        }
        else {
            System.out.println(this.nickname + " = " + this.pontos_player + " ponto"); /* singular */
        }
        /* pontuação pc */
        if (this.pontos_pc == 0 || this.pontos_pc > 1)  {
            System.out.println("PC = " + this.pontos_pc + " pontos\n\r"); /* plural */
        }
        else {
            System.out.println("PC = " + this.pontos_pc + " ponto\n\r"); /* singular */
        }
    }
}
