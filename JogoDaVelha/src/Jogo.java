
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Jogo {

    public char[] estado = new char[9];
    public char jogador;
    public static int qtd_no = 0;

    //Funcoes de Jogo
    public Jogo() {
        for (int i = 0; i < estado.length; i++) {
            estado[i] = ' ';
        }
        jogador = 'O';              //MAX
    }

    public Jogo(char[] e, char j) {
        estado = e;
        jogador = j;
    }

    public void jogaETroca(char atual, int jogada) {     // efetua a jogada e troca o jogador

        char[] novo = jogar(atual, jogada);             //novo estado com a jogada atual

        if (novo != null) {                             //se estado novo nao for vazio, entao atualiza estado
            estado = novo;
        }

        if (atual == 'O') {
            jogador = 'X';
        } else {
            jogador = 'O';
        }
    }

    public char[] jogar(char atual, int posição) {
        if (estado[posição - 1] == ' ') {           //se estado vazio na posição de entrada

            char[] novo = new char[9];              //novo estado

            for (int i = 0; i < 9; i++) {
                novo[i] = estado[i];                //copia valores de estado antigo para novo estado
            }

            novo[posição - 1] = atual;              //adiciona jogada a novo estado

            return novo;
        } else {
            return null;
        }
    }

    List<Jogo> Filhos() {
        List<Jogo> f = new ArrayList<Jogo>();

        char prox_jogador;

        if (jogador == 'O') {
            prox_jogador = 'X';
        } else {
            prox_jogador = 'O';
        }

        for (int i = 1; i <= 9; i++) {                              //percorre os Filhos 
            char[] e = geraEstado(jogador, i);                      //gera novo estado 
            if (e != null) {
                f.add(new Jogo(e, prox_jogador));                   //adiciona estado a lista de Filhos
            }
        }
        return f;
    }

    public char[] geraEstado(char j, int p) {               // gera novo estado com caracter do jogador na posição informada        
        if (estado[p - 1] == ' ') {                         //se posição livre em estado atual
            char[] novo = new char[9];                      //cria novo estado

            for (int i = 0; i < 9; i++) {                   //copia estado atual para estado novo
                novo[i] = estado[i];
            }

            novo[p - 1] = j;                                //preenche novo estado com a jogada

            qtd_no++;
            return novo;
        } else {
            return null;
        }
    }

    public int minimax(Jogo j, char atual) {

        if (j.verificaFinal()) {                      // se j é um estado final, retorna minmax de j
            char ganhador = j.getGanhador();

            if (ganhador == 'O') {
                return 1;                           //humano
            } else {
                if (ganhador == 'X') {
                    return -1;                      //CPU
                } else {
                    return 0;                       //empate
                }
            }
        }

        if (atual == 'O') {                         //se atual for MAX(Humano)

            int menor = Integer.MIN_VALUE;          // recebendo o menor valor possivel

            for (Jogo filho : j.Filhos()) {         //percorre filhos e procura menor valor de minmax
                int mm = minimax(filho, 'X');
                menor = Integer.max(menor, mm);
            }
            return menor;
        } else {                                      //se atual for MIN(CPU)
            int maior = Integer.MAX_VALUE;          //recebe maior valor possivel

            for (Jogo filho : j.Filhos()) {         //percorre filhos e procura maior valor de minmax
                int v = minimax(filho, 'O');
                maior = Integer.min(maior, v);
            }
            return maior;
        }
    }

    //Funções referentes ao estado
    public void exibeEstado() {
        System.out.println("Estado atual");

        System.out.println(estado[0] + " │ " + estado[1] + " │ " + estado[2]);
        System.out.println("───────");
        System.out.println(estado[3] + " │ " + estado[4] + " │ " + estado[5]);
        System.out.println("───────");
        System.out.println(estado[6] + " │ " + estado[7] + " │ " + estado[8]);

        System.out.println("\n\n");
    }

    boolean verificaFinal() {
        char resultado = getGanhador();
        return resultado != 0;              //se nao tem ganhador, nao retorna
    }

    char getGanhador() {               //verifica se alguma das linhas obtidas em completas() tem vencedor
        for (char[] linha : completas()) {
            if (linha[0] == linha[1] && linha[1] == linha[2]) {
                if (linha[0] == 'O') {
                    return 'O';
                } else if (linha[0] == 'X') {
                    return 'X';
                }
            }
        }
        if (estadoCheio()) {
            return 'E';
        }
        return 0;
    }

    public ArrayList<char[]> completas() {                  //cria um conjunto com todas as possiveis linhas, colunas e diagonais completas
        ArrayList<char[]> conjunto = new ArrayList<>();

        for (int i = 0; i < 3; i++) {   //checa linhas horizontais
            char[] linha = new char[3];
            linha[0] = estado[i * 3];
            linha[1] = estado[i * 3 + 1];
            linha[2] = estado[i * 3 + 2];
            conjunto.add(linha);
        }

        for (int i = 0; i < 3; i++) {   //checa linhas verticais
            char[] coluna = new char[3];
            coluna[0] = estado[i];
            coluna[1] = estado[i + 3];
            coluna[2] = estado[i + 6];
            conjunto.add(coluna);
        }

        char[] diagonal1 = {estado[0], estado[4], estado[8]};   //checa diagonais
        char[] diagonal2 = {estado[2], estado[4], estado[6]};

        conjunto.add(diagonal1);
        conjunto.add(diagonal2);

        return conjunto;
    }

    boolean estadoCheio() {
        for (int i = 0; i < estado.length; i++) {
            if (estado[i] == ' ') {
                return false;
            }
        }
        return true;
    }

}
