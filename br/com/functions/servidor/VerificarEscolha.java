/*
* Classe auxiliar responsável por verificar no servidor
* qual opção foi escolhida pelo cliente (1- par ou 2- ímpar)
*
* Autor: Laércio Filho
* Data: 04/12/2017
*
* */

package br.com.functions.servidor;

public class VerificarEscolha {
    /* VARIÁVEIS GLOBAIS */
    private int escolha;
    private String nickname;

    public VerificarEscolha(int escolha, String nickname) { /* constructor auxiliar
     para inicializar as variáveis globais */
        this.escolha = escolha;
        this.nickname = nickname;
    }
    public String verificarParImpar() { /* verifica opção escolhida pelo cliente */
        String msg_escolha; /* variável auxiliar para receber msg
         concatenada (nickname + complemento) */
        
        if (this.escolha > 1) { /* se opção for igual a 2, então.. */
            msg_escolha = this.nickname + " escolheu ímpar e o PC par.\n\r"; /* concatena
             seleção de ímpar para o cliente e par para o pc */
        }
        else { /* senão.. no caso opção igual a 1 */
            msg_escolha = this.nickname + " escolheu par e o PC ímpar.\n\r"; /* concatena
             seleção de par para o cliente e ímpar para o pc */
        }
        return msg_escolha; /* retorna msg concatenada */
    }
}
