/*
* Classe auxiliar para imprimir o resultado
* geral do jogo através da função
* imprimirResultadoGeral()
*
* Há funções com o retorno da pontuação do
* player e a pontuação do pc
*
* Autor: Laércio Filho
* Data: 04/12/2017
*
* */

package br.com.functions.servidor;

public class ResultadoPartida {
    private int restoDivisao;
    private int escolhaPlayer;
    private String nickname;
    private int pontosPlayer;
    private int pontosPc;

    /* constructor */
    public ResultadoPartida(
            int restoDivisao, int escolhaPlayer, String nickname) {
        this.restoDivisao = restoDivisao;
        this.escolhaPlayer = escolhaPlayer;
        this.nickname = nickname;
    }
    /* imprimir resultado geral do jogo */
    public void imprimirResultadoGeral(Pontuacao pontos) {

       if (this.restoDivisao > 0) { /* condição se restoDivisao = 1 */
           System.out.println(
                   "O resultado final da partida foi ÍMPAR!");
           if (this.escolhaPlayer > 1) { /* se player escolher 2, então vence, senão... */
               System.out.println(
                       "Parabéns, " + this.nickname + ", você ganhou!\n\r");

               pontos.darPtsPlayer();
           }
           else { /* perde... */
               System.out.println(
                       "Sinto muito, " + this.nickname + ", você perdeu...\n\r");

               pontos.darPtsPc();
           }
       }
       else { /* se restoDivisao = 0 */
           System.out.println(
                   "O resultado final da partida foi PAR!");
           if (this.escolhaPlayer < 2) { /* se player escolher 1, então vence, senão... */
               System.out.println(
                       "Parabéns, " + this.nickname + ", você ganhou!\n\r");

               pontos.darPtsPlayer();
           }
           else { /* perde */
               System.out.println(
                       "Sinto muito, " + this.nickname + ", você perdeu...\n\r");

               pontos.darPtsPc();
           }
       }
    }
}
