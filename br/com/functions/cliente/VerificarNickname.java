/*
* Classe criada para verificar
*
* Autor: Laércio Filho
* Data: 11/12/2017
*
* */

package br.com.functions.cliente;

import java.util.Scanner; /* para ler o que é escrito no console */

public class VerificarNickname {
    /* VARIÁVEIS GLOBAIS */
    private String nickname;

    public VerificarNickname(String nick) {
        this.nickname = nick;
    }
    /* função para verificar a string digitada pelo player */
    public String verificar() {

        do {
            Scanner t = new Scanner(System.in);

            if (this.nickname.length() > 0 && this.nickname.length() <= 3) { /* o primeiro requisito para
             o nickname ser considerado válido é ser diferente de vazio e o número de caracteres de ser
             maior que 3 */
                System.out.println("Seu nickname deve ter mais de 3 caracteres. " +
                        "Aguarde para digitar novamente.\n\r"); /* caso não atenda ao requisito acima,
                         esta mensagem deve ser apresentada ao cliente */

                try {
                    Thread.sleep(2 * 1000); /* load de 2 segundo para possibilitar ao jogador
                     jogar novamente */
                } catch (InterruptedException in) {}

                System.out.print("Digite seu nickname novamente: "); /* assim esta mensagem é apresentada no
                 console do cliente */
                this.nickname = t.nextLine(); /* captura o que for digitado no cliente */
            }

            if (this.nickname.length() == 0) { /* se o requisito da quantidade de caracteres for igual a
             nulo */
                System.out.println("Seu nickname não pode ser vazio. " +
                        "Aguarde para digitar novamente.\n\r"); /* então esta msg deverá ser apresentada no
                         console do cliente */

                try {
                    Thread.sleep(2 * 1000); /* load de 2 segundo para possibilitar ao jogador
                     jogar novamente */
                } catch (InterruptedException in) {}

                System.out.print("Digite seu nickname novamente: "); /* assim esta mensagem é apresentada no
                 console do cliente */
                this.nickname = t.nextLine(); /* captura o que for digitado no cliente */
            }

        } while (this.nickname.length() <= 3); /* deixamos esse processo dentro de um loop até que os requi-
         sitos sejam atendidos de forma adequada */

        return this.nickname; /* com o nickname ok, a função retorna a string digitada.
         essa string pode ser tanto do nickname digitado pelo jogador na thread do cliente,
         ou, se o campo for preenchido de forma inadequada, então o novo nickname digitado
         será retornado */
    }
}
