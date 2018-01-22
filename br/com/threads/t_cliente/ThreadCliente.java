/*
* Classe com o script de execução da thread do cliente
*
* Autor: Laércio Filho
* Data: 15/12/2017
* 
* */

package br.com.threads.t_cliente;

import br.com.functions.cliente.MenuPrincipal;
import br.com.functions.cliente.VerificarNickname;
import br.com.functions.cliente.JogarPlayer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ThreadCliente extends Thread {
    private final int porta;

    public ThreadCliente(int porta) {
        this.porta = porta;
    }
    @Override
    public void run() {
        try {
            /* LOAD DE ABERTURA DO JOGO E CONFIGURAÇÃO DO CLIENTE */
            System.out.println("Aguarde enquando o jogo é carregado...");
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException in) {}

            System.out.println("Jogo carregado com sucesso.\n\r");
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException in) {}
            /* fim do load de abertura */

            System.out.println("***** Bem Vindo ao Jogo do Par ou Ímpar *****\n\r"); /* msg de boas vindas */
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException in) {}

            Socket cliente_sock = new Socket("127.0.0.1", this.porta); /* define o ip e a porta que o cliente
             se conectará */

            Scanner t = new Scanner(System.in);
            PrintStream saida = new PrintStream(cliente_sock.getOutputStream()); /* ler tudo que é escrito no cliente
             e é enviado ao servidor */

            /* DEFINIÇÃO E VERIFICAÇÃO DO NICKNAME */
            System.out.print("Digite seu nickname: ");
            String nickname = t.nextLine(); /* ler o nickname digitado no cliente */

            VerificarNickname cn = new VerificarNickname(nickname); /* nova instância de um obj do tipo VerificarNickname */
            String nick_verificado = cn.verificar(); /* a string nick_verificado armazena a string retornada pela função
             verificar(), presente na classe VerificarNickname */
            System.out.println("Nickname válido!"); /* se o nickname estiver ok, está msg deve ser apresentada no console do
             cliente */
            saida.println(nick_verificado); /* envia via socket o nickname escolhido pelo cliente para o servidor */

            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException in) {}
            /* FIM DA DEFINIÇÃO E VERIFICAÇÃO DO NICKNAME */

            /* INÍCIO DA RODADA */
            int opc_jogar_nov; /* aqui deve ser armazenado um valor entre 1 e 2 (1- sim e 2- não) para jogar novamente */
            int n_rodada = 0; /* contador de rodadas */

            do {
                /* SCRIPT DA RODADA */
                n_rodada++; /* incrementa contador de rodadas */
                System.out.println("\n\r*** RODADA " + n_rodada + " ***\n\r"); /* sinaliza em qual rodada o jogo está */

                try {
                    Thread.sleep(2 * 1000); /* load de 2 segundos para carregar o menu principal */
                } catch (InterruptedException in) {}

                MenuPrincipal mp = new MenuPrincipal(); /* declara uma nova instância de um obj do tipo MenuPrincipal */
                int escolha_menu = mp.menu(); /* a variável escolha_menu deve armazenar um valor inteiro retornado através
                 da função menu(), presente na classe MenuPrincipal */
                if (escolha_menu > 1) { /* se for 2 */
                    System.out.println("Você escolheu ímpar.\n\r");
                }
                else if (escolha_menu == 1) { /* se for 1 */
                    System.out.println("Você escolheu par.\n\r");
                }
                else { /* se for 0 */
                    System.out.println("Você escolheu fechar o jogo.\n\r");
                    try {
                        Thread.sleep(1 * 1000); /* 1 segundo */
                    } catch (InterruptedException in) {}
                    System.out.println("Aguarde enquanto finalizamos todos os módulos...");
                    try {
                        Thread.sleep(3 * 1000); /* 3 segundo */
                    } catch (InterruptedException in) {}
                    System.out.println("Jogo finalizado com sucesso.\n\r");
                    System.exit(0); /* finaliza o jogo */
                }
                saida.println(escolha_menu); /* escreve a opção escolhida para o servidor */

                /* jogada do player */
                JogarPlayer jpl = new JogarPlayer(); /* obj jpl da classe JogarPlayer */
                int jogada_player = jpl.jogar(); /* executa método jogar da classe JogarPlayer e recebe o valor retornado na 
                 função jogar() */
                System.out.println("Você jogou " + jogada_player); /* imprime no console o valor jogado pelo jogador */
                saida.println(jogada_player); /* envia o valor jogado para o servidor */

                try {
                    Thread.sleep(12 * 1000); /* load de 12 segundos para que o cálculo da jogada seja realizado antes
                     do jogador decidir se vai jogar novamente ou não */
                } catch (InterruptedException in) {}

                /* menu jogar novamente */
                System.out.println("Você deseja jogar novamente?");
                System.out.println("1- Sim"); /* escolhendo sim uma nova rodada será inicializada */
                System.out.println("2- Não\n\r"); /* escolhendo não o jogo é finalizado */
                System.out.print("Digite uma opção: ");
                opc_jogar_nov = t.nextInt(); /* captura a opção escolhida pelo jogador */

                if (opc_jogar_nov == 2) { /* se opc_jogar_nov for igual a 2, então as msgs
                 abaixo deverão ser apresentadas no console do cliente */
                    System.out.println("Você escolheu não jogar novamente.\n\r");
                    try {
                        Thread.sleep(2 * 1000); /* load de 2 segundos */
                    } catch (InterruptedException in) {}
                    System.out.println("Aguarde enquanto finalizamos todos os módulos...");
                    try {
                        Thread.sleep(2 * 1000); /* mais um load de 2 segundos */
                    } catch (InterruptedException in) {}
                    System.out.println("Jogo finalizado com sucesso.");
                    try {
                        Thread.sleep(2 * 1000); /* mais load de 2 segundos */
                    } catch (InterruptedException in) {}

                    saida.println(opc_jogar_nov); /* envia a opção 2 para o servidor e o servidor
                     registra a saída do player */
                }

                saida.println(opc_jogar_nov); /* escreve 1 para o servidor (jogar novamente) */

            } while (opc_jogar_nov != 2); /* condição para encerrar o do */
            /* o do é encerrado quando 2 é escolhido pelo player no menu de jogar novamente */

            saida.close(); /* encerra o socket do servidor */
            t.close(); /* encerra a captura de informações através do teclado */
            cliente_sock.close(); /* encerra o socket do cliente */
        }
        catch (IOException ex) {}
    }
}
