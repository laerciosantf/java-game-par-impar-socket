/*
* Classe responsável pela execução da thread do servidor
* através do método servidor()
*
* */

package br.com.main;

import br.com.threads.t_servidor.ThreadServidor;

public class Servidor {

    void servidor() { /* executa thread do servidor */
        int porta = 4400;
        ThreadServidor ts = new ThreadServidor(porta);
        ts.start();
    }

    public static void main(String[] args) {
        Servidor serv = new Servidor();
        serv.servidor();
    }
}
