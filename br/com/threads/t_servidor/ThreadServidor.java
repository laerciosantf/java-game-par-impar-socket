/*
* Classe responsável por executar
* o script do servidor e sua thread
*
* Autor: Laércio Filho
* Data: 04/12/2018
*
* */

package br.com.threads.t_servidor;

import br.com.functions.servidor.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ThreadServidor extends Thread {
    /* variáveis globais */
    private final int porta; /* recebe porta via classe Servidor */

    private int aux_pts_player;
    private int aux_pts_pc;

    public ThreadServidor(int porta) {
        this.porta = porta;
    }
    @Override
    public void run() {
        try { /* try principal */
            /* SCRIPT DO SERVIDOR */
            System.out.println(
                    "O servidor está carregando o jogo..."); /* sinaliza o início do
             carregamento do jogo no servidor */
            try {
                Thread.sleep(2 * 1000); /* 2 segundos de load */
            } catch (InterruptedException e) {}

            System.out.println(
                    "Carregamento concluído.\r\n"); /* sinaliza que o primeiro load
             foi concluído com sucesso */
            try {
                Thread.sleep(2 * 1000); /* 2 segundos de load */
            } catch (InterruptedException e) {}

            ServerSocket server_socket = new ServerSocket(
                    this.porta); /* define a porta que será usada
             pelo cliente para acessar o servidor */
            System.out.println(
                    "Servidor rodando na porta " + this.porta + "\n\r"); /* sinaliza que a porta
             foi definida com sucesso e o servidor está no ar */
            System.out.println(
                    "Aguardando conexão..."); /* sinaliza que o servidor está aguardando uma nova
             conexão de um cliente */
            /* **** finaliza o script de inicialização do jogo após a definição correta da porta **** */

            while(true) { /* while infinito */
                /* SCRIPT PARA RECEBER A CONEXÃO DO CLIENTE */
                Socket cliente_socket = server_socket.accept(); /* para o cliente acessar, o servidor
                 deve aceitar seu acesso */
                System.out.println(
                        "Nova conexão identificada.\n\r"); /* passou do accept.. */
                /* *** finaliza o script recebendo uma conexão do cliente *** */

                /* VARIÁVEIS LOCAIS */
                String nickname_escolhido = null; /* inicializa a variável que receberá o nickname
                 escolhido pelo player através do cliente */
                String msg_escolha_par_impar; /* inicializa a variável que receberá a msg
                 após a escolha da opção selecionada pelo player (1- par ou 2- ímpar) */
                int escolha_par_impar = -1; /* inicializa a variável que receberá a opção escolhida
                 pelo player (1- par, 2- ímpar ou 0- sair) */
                int opc_jogar_novamente = -1; /* inicializa a variável que receberá a opção para
                 jogar novamente ou não (1 ou 2) */

                /* VARIÁVEIS DE CONTROLE DE FLUXO */
                int flag_nickname = 0; /* pode ser 1 ou 0, 1- nickname válido e 0- nickname inválido */
                int flag_par_impar = -1; /* pode ser -1 ou 1, -1- opção de par ou ímpar não selecionada
                 e 1- opção de par ou ímpar selecionada */

                /* DECLARAÇÕES DE CLASSES AUXILIARES */
                Pontuacao pontos = new Pontuacao(); /* classe pontuação */

                /* MÉTODOS JAVA.UTIL. */
                /* método Scanner */
                Scanner s = new Scanner(
                        cliente_socket.getInputStream()); /* recebe tudo que for
                 digitado pelo cliente */

                do {
                    /* SCRIPT PARA CONTROLE DE FLUXO DE INFORMAÇÕES TROCADAS cliente -> servidor */
                    if (flag_nickname < 1) { /* checa se o nickname já foi informado..
                     se ainda não, entra aqui */
                        nickname_escolhido = s.nextLine(); /* recebe o nickname escolhido pelo player */
                        String msg_entrada_player = nickname_escolhido + " entrou no jogo.\n\r"; /* concatena o nickname
                         a uma String complementar */
                        System.out.println(msg_entrada_player); /* imprime msg de que o player x entrou no jogo.
                         essa msg é apresentada no servidor */
                        flag_nickname++; /* incrementa flag de controle */
                    } /* não deve entrar nesse if ^ após a primeira volta */
                    else if (flag_nickname == 1 && flag_par_impar < 1) { /* checa se a etapa de preenchimento do nickname foi realizada com sucesso
                     e se a opção de jogada já foi escolhida.. se ainda não, entra aqui */
                        escolha_par_impar = s.nextInt(); /* recebe opção de par ou ímpar selecionada pelo player */

                        VerificarEscolha ve = new VerificarEscolha(
                                escolha_par_impar, nickname_escolhido); /* envia por parâmetro a opção escolhida pelo cliente
                                 e o nickname do player para verificação (inicializa variáveis da classe VerificarEscolha */
                        msg_escolha_par_impar = ve.verificarParImpar(); /* armazena retorno da função verificarParImpar (msg_escolha) */
                        System.out.println(msg_escolha_par_impar); /* imprime o retorno armazenado na variável msg_escolha_par_impar */
                        flag_par_impar = 1; /* incrementa flag de controle */
                    } /* não deve entrar nesse if ^ após a segunda volta */
                    else {
                        if (flag_nickname == 1 && flag_par_impar == 1) { /* checa se as etapas de preenchimento do nickname e de seleção
                         da opção de jogada foram realizadas corretamente */
                            
                            /* JOGADA DO PLAYER */
                            int jogada_player = s.nextInt(); /* recebe um número de 0 a 5 escolhido pelo player (a verificação da jogada do player
                            é feita no cliente) */
                            System.out.println(nickname_escolhido + " jogou!"); /* sinaliza no servidor que o player realizou sua jogada */
                            try {
                                Thread.sleep(2 * 1000); /* 2 segundos de load*/
                            } catch (InterruptedException in) {}

                            /* JOGADA DO PC */
                            JogarPc jpc = new JogarPc(); /* declara um novo obj da classe JogarPc */
                            int jogada_pc = jpc.jogar(); /* recebe um número aleatório jogado pelo pc (0 a 5) */
                            System.out.println("PC jogo!\n\r"); /* sinaliza que o pc finalizou sua jogada */
                            try {
                                Thread.sleep(2 * 1000); /* 2 segundos de load */
                            } catch (InterruptedException in) {}

                            /* CÁLCULO DA JOGADA */
                            System.out.println("Calculando o resultado da jogada..."); /* sinaliza que o servidor iniciou o cálculo 
                            da jogada */
                            int soma_numeros_jogados = jogada_player + jogada_pc; /* soma os números jogados pelo player e pelo pc */
                            int resto = soma_numeros_jogados % 2; /* recebe o resto da divisão da soma dos números jogados por 2
                            (resultado deve ser igual a 0 ou 1) */
                            try {
                                Thread.sleep(2 * 1000); /* 2 segundos de load */
                            } catch (InterruptedException in) {}

                            System.out.println("Resultado calculado com sucesso.\n\r"); /* sinaliza que o cálculo da jogada foi
                            finalizado */
                            try {
                                Thread.sleep(2 * 1000); /* 2 segundos de load */
                            } catch (InterruptedException in) {}

                            /* DADOS DA JOGADA */
                            DadosPartida dj = new DadosPartida(
                                    jogada_player, jogada_pc, nickname_escolhido); /* declara um novo obj da classe DadosPartida */

                            dj.imprimirJogadaAtual(); /* chama o método imprimirJogadaAtual para imprimir os dados atualizados da rodada */
                            try {
                                Thread.sleep(2 * 1000); /* 2 segundos de load */
                            } catch (InterruptedException in) {}

                            /* RESULTADO GERAL DA PARTIDA */
                            ResultadoPartida rp = new ResultadoPartida(
                                    resto, escolha_par_impar, nickname_escolhido); /* declara um novo obj da classe ResultadoPartida */

                            rp.imprimirResultadoGeral(pontos); /* chama método imprimirResultadoGeral passando um obj do tipo Pontuacao por parâmetro */
                            this.aux_pts_player = pontos.ptsPlayer(); /* aux_pts_player recebe a quantidade de pontos do player */
                            this.aux_pts_pc = pontos.ptsPc(); /* aux_pts_pc recebe a quantidade de pontos do pc */

                            try {
                                Thread.sleep(2 * 1000); /* 2 segundos de load */
                            } catch (InterruptedException in) {}

                            /* PLACAR GERAL */
                            PlacarGeral pg = new PlacarGeral(
                                    nickname_escolhido, this.aux_pts_player, this.aux_pts_pc); /* declara um novo obj da classe PlacarGeral */

                            pg.placar(); /* chama o método para imprimir o placar geral */

                            /* JOGAR NOVAMENTE */
                            opc_jogar_novamente = s.nextInt(); /* recebe opção selecionada pelo player */
                            if (opc_jogar_novamente == 1) { /* opc_jogar_novamente = 1, jogar novamente.. */
                                flag_par_impar = -1; /* retorna variável de controle para o valor inicial */
                            }
                        }
                    }

                } while (escolha_par_impar != 0 || opc_jogar_novamente != 2); /* while é mantido até que
                 uma das condições seja satisfeita */

                System.out.println(nickname_escolhido + " saiu do jogo"); /* sinaliza que o cliente foi desconectado */
                cliente_socket.close(); /* encerra a conexão com o cliente */
            }
        } catch (IOException ex) {}
    }
}
