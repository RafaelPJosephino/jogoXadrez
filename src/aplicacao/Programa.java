package aplicacao;

import jogoTabuleiro.TabuleiroException;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        List<PecaXadrez> pecasCapturadas = new ArrayList<>();

        while (!partidaXadrez.getCheckMate()) {
            try {
                UI.limparTela();
                UI.mostrarPartida(partidaXadrez, pecasCapturadas);

                System.out.println();
                System.out.print("Peca Origem: ");
                PosicaoXadrez origem = UI.LerPosicaoXadrez(scanner);

                boolean[][] posicaoPossiveis = partidaXadrez.movimentosPossiveis(origem);
                UI.limparTela();
                UI.mostraTabuleiroPosiveis(partidaXadrez.getPecas(), posicaoPossiveis);
                System.out.println();
                System.out.print("Peca Destino: ");
                PosicaoXadrez destino = UI.LerPosicaoXadrez(scanner);

                PecaXadrez pecaCapturada = partidaXadrez.movimentacaoXadrez(origem, destino);
                if (pecaCapturada != null) {
                    pecasCapturadas.add(pecaCapturada);
                }

                if (partidaXadrez.getPromocao() != null) {
                    System.out.print("Entre com a peca a ser promovida, Bispo(B) Cavalo(C) Dama(D) Torre(T): ");
                    String tipo = scanner.nextLine().toUpperCase();
                    while (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("D") && !tipo.equals("T")) {
                        System.out.println("Peca invalida! entre novamente uma peca, Bispo(B) Cavalo(C) Dama(D) Torre(T): ");
                        tipo = scanner.nextLine().toUpperCase();
                    }

                    partidaXadrez.colocarPecaPromovida(tipo);
                }

            } catch (XadrezException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }

        }
        UI.limparTela();
        UI.mostrarPartida(partidaXadrez, pecasCapturadas);


    }

}
