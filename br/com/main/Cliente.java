/*
* Classe responsável por executar a thread do cliente
* através do método cliente()
*
* Autor: Laércio Filho
* Data: 14/12/2017
*
* */

package br.com.main;

import br.com.threads.t_cliente.ThreadCliente;

public class Cliente { /* executa thread do cliente */

    void cliente() {
        int porta = 4400;
        ThreadCliente tc = new ThreadCliente(porta);
        tc.start();
    }

    public static void main(String[] args) {
        Cliente cli = new Cliente();
        cli.cliente();
    }
}
