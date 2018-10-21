
import java.io.IOException;
import java.util.Scanner;

public class Principal {

    final static char MAX = 'O';    //humano    minmax=1
    final static char MIN = 'X';    //cpu       minmax=-1 e se empate minmax=0

    static int posicao;

    public static void main(String[] args) throws IOException {
        Jogo jogo = new Jogo();
        System.out.println("Começo do Jogo");
        // Enquanto o jogo n�o estiver acabado
        while (!jogo.verificaFinal()) {
            jogo.exibeEstado();

            getMovimentoHumano(jogo);
            jogo.jogaETroca('O', posicao);        //efetua a jogada e troca jogador

            // Se o jogo acabar depois de efetuar nova jogada, entao finaliza
            if (jogo.verificaFinal()) {
                break;
            }

            float maximo = Integer.MAX_VALUE;
            Jogo proxima_jogada = null;                       //proximo movimento

            for (Jogo filho : jogo.Filhos()) {       // percorre a lista de filhos do estado atual
                int mm = 0;

                mm = filho.minimax(filho, 'O');     //recebe minmax do filho

                if (mm <= maximo) {                //se minmax maior que maximo, atualiza maximo e proximo movimento
                    maximo = mm;
                    proxima_jogada = filho;
                }
            }

            jogo = proxima_jogada;
        }

        jogo.exibeEstado();              //exibe estado final

        char vencedor = jogo.getGanhador();
        System.out.println("Quantidade no gerados " + jogo.qtd_no);
        encerramento(vencedor);

    }

    public static void getMovimentoHumano(Jogo j) {
        Scanner s = new Scanner(System.in);

        System.out.println("Vez do humano");
        System.out.println("Selecione o estado que deseja preencher");

        System.out.println("1 │ 2 │ 3");
        System.out.println("───────");
        System.out.println("4 │ 5 │ 6");
        System.out.println("───────");
        System.out.println("7 │ 8 │ 9");

        int p = s.nextInt();

        if (p > 0 && p < 10 && j.estado[p - 1] == ' ') {
            posicao = p;
        } else {
            System.out.println("Posição inválida, tente de novo");
            j.exibeEstado();
            getMovimentoHumano(j);
        }
    }

    private static void encerramento(char vencedor) {
        switch (vencedor) {
            case 'X':
                System.out.println("CPU GANHOU");
                break;
            case 'E':
                System.out.println("EMPATE");
                break;
            case 'O':
                System.out.println("HUMANO GANHOU");            //nunca sera exibida
                break;
            default:
                break;
        }

        System.out.println("\n\nFIM DO JOGO, pressione uma tecla para continuar");
    }

}
