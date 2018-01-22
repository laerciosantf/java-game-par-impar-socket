/*
* Classe para controlar a opção selecionada
* pelo player no menu principal
*
* Autor: Laércio Filho
* Data: 11/12/2017
*
* */

package br.com.functions.cliente;

import java.util.Scanner;

public class MenuPrincipal {
    private int opc_escolhida = -1; /* condição para manter o loop na função menu, o valor
     da variável opc_escolha deve ser sempre diferente de 0 */
    private String msg_escolha = "Escolha uma das opções abaixo:"; /* o valor da variável msg_escolha
     deve ser alterado caso a opção escolhida pelo jogador seja diferente de uma das opções abaixo */

    public int menu() {

        do {
            System.out.println(this.msg_escolha); /* imprime no console o valor de msg_escolha definido
             antes de entrar no do */
            System.out.println("1- Par"); /* 1 para par */
            System.out.println("2- Ímpar"); /* 2 para ímpar */
            System.out.println("0- Sair\n\r"); /* 0 para sair */

            System.out.print("Digite uma opção: ");
            Scanner t = new Scanner(System.in); /* cria-se um obj t do tipo Scanner */
            this.opc_escolhida = t.nextInt();

            switch (this.opc_escolhida) {
                case 1:
                    return this.opc_escolhida; /* retorna 1 */
                case 2:
                    return this.opc_escolhida; /* retorna 2 */
                case 0:
                    return this.opc_escolhida; /* retorna 0 */
                default:
                    System.out.println("Opção inválida. Aguarde para escolher novamente.\n\r");
                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException e) {}
                    this.msg_escolha = "Escolha novamente uma das opções abaixo:"; /* msg
                    é alterada caso o player erre a opção */
                    break;
            }

        } while (this.opc_escolhida > 2);
        return 0;
    }
}
