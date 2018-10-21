# JogoDaVelhaMiniMax
Trabalho da disciplina de Inteligência Artificial - UFU 2017.2
Implementação do jogo da velha usando algoritmo MiniMax. 

-> Configuração inicial:
1) Humano = MAX ‘O’
2) CPU = MIN ‘X’
3) Humano sempre inicia o jogo
4) Medida de desempenho:
I) 1 se humano ganhar
II) -1 se CPU ganhar
III) 0 se empate

-> Enquanto não encontra um estado final, faz as seguintes ações:
1) getMovimentoHumano – obtém no teclado a posição que o humano deseja jogar
2) jogaETroca – faz a jogada com a posição obtida em 1 e troca jogador para X
3) vetificaFinal – verifica se estado novo com jogada de 2 é final
   I) se getGanhador retorna valor, tem ganhador
     (a) verifica se alguma linha do estado atual é igual a lista de estados completos
4) Filhos – gera lista de filhos do estado atual do jogo
    I) geraEstado – gera novo estado com jogador e posicao
5) for – percorre todos os filhos na lita gerada em 4
    I) minmax – calcula valor de minmax de um filho do Humano (MAX)
     (a) verifica se final, se sim retorna medida de desempenho
     (b) se jogador atual for MAX, percorre filhos e acha menor minmax
     (c) se jogador atual for MIN, percorre filhos e acha maior minmax
   II) guarda como próxima jogada o filho que contem maior valor minmax
6) jogo recebe próxima jogada (do CPU)

-> Quando encontra estado final exibe a quantidade de nos que foram gerados e quem foi o ganhador 
