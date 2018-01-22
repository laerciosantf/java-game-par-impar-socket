/*
* Classe auxiliar para gerenciar as pontuações
* dos jogadores
*
* Autor: Laércio Filho
* Data: 04/12/2017
*
* */

package br.com.functions.servidor;

public class Pontuacao {
    /* variáveis globais */
    private int pts_player;
    private int pts_pc;

    public Pontuacao() { /* constructor para
    receber as pontuações dos jogadores */
    }
    /* incrementa a variável pts_player */
    public void darPtsPlayer() {
        this.pts_player++;
    }
    /* incrementa a variável pts_pc */
    public void darPtsPc() {
        this.pts_pc++;
    }
    /* retorna pontuação do player */
    public int ptsPlayer() {
        return this.pts_player;
    }
    /* retorna pontuação do pc */
    public int ptsPc() {
        return this.pts_pc;
    }
}
