import java.util.Random;
import java.util.Scanner;

public class trabalhopoo {
    // Luiz Miguel M. de Almeida, Luis Fernando de Oliveira Santos e Ryan Silva
    // Faria Almeida

    public static void tabela(String[][] TabelaPlayer) {
        char Organizacaodasletras = 'A';

        // Exibindo a matriz
        for (int i = 0; i < 14; i++) {
            System.out.print("                                       ");
            System.out.print(Organizacaodasletras);
            Organizacaodasletras++;
            for (int g = 0; g < 14; g++) {
                System.out.print(TabelaPlayer[i][g]);
            }
            System.out.println();
        }
        System.out.print("                                       ");
        System.out.println("0 1  2  3  4  5  6  7  8  9 10 11 12 13 14");

    }

    public static void PosicionamentoNavios(String[][] TabelaPlayer) {
        Random rand = new Random();
        int contPA = 0, contNT = 0, contContra = 0, contSub = 0; // Contagem dos quantidade de barcos
        int limitedosbarcos = 0;

        while (contPA != 1 || contNT != 2 || contContra != 3 || contSub != 4) {
            int linha = rand.nextInt(14);
            int coluna = rand.nextInt(14);
            int barcos = rand.nextInt(4); // 0-Porta Avioes (pode ter 1) (5 casas); 1-Navio Tanque (pode ter 2) (4
                                          // casas); 2-Contratorpedeiros (pode ter 3) (3 casas); 3-Submarino (pode ter
                                          // 4) (2 casas).
            int direcao = rand.nextInt(4); // direcao (esquerda=0, direita=1, cima=2 e baixo=3)

            switch (barcos) {
                case 0:
                    limitedosbarcos = 5;
                    if (contPA < 1) {
                        contPA += PosicionamentoDosBarcos(TabelaPlayer, coluna, linha, direcao, limitedosbarcos);
                    }
                    break;
                case 1:
                    limitedosbarcos = 4;
                    if (contNT < 2) {
                        contNT += PosicionamentoDosBarcos(TabelaPlayer, coluna, linha, direcao, limitedosbarcos);
                    }
                    break;
                case 2:
                    limitedosbarcos = 3;
                    if (contContra < 3) {
                        contContra += PosicionamentoDosBarcos(TabelaPlayer, coluna, linha, direcao, limitedosbarcos);
                    }
                    break;
                case 3:
                    limitedosbarcos = 2;
                    if (contSub < 4) {
                        contSub += PosicionamentoDosBarcos(TabelaPlayer, coluna, linha, direcao, limitedosbarcos);
                    }
                    break;
            }
        }
    }

    public static int PosicionamentoDosBarcos(String[][] Tabela, int coluna, int linha, int direcao,
            int limitedosbarcos) {
        int cont = 0;

        if (direcao == 0) {
            if (coluna >= limitedosbarcos - 1) {
                for (int i = linha - 1; i <= linha + 1; i++) {
                    for (int g = coluna + 1; g <= coluna - limitedosbarcos; g--) {
                        if (g >= 0 && g < 14 && i >= 0 && i < 14) {
                            if (Tabela[i][g] == "(0)") {
                                return 0;
                            }
                            if (Tabela[i][g] == "( )") {
                                cont++;
                            }
                        }
                    }
                }
                if (cont != 0) {
                    for (int i = 0; i < limitedosbarcos; i++) {
                        Tabela[linha][coluna - i] = "(0)";
                    }
                    return 1;
                }
            }
        } else if (direcao == 1) {
            if (coluna <= 14 - limitedosbarcos) {
                for (int i = linha - 1; i <= linha + 1; i++) {
                    for (int g = coluna - 1; g <= coluna + limitedosbarcos; g++) {
                        if (g >= 0 && g < 14 && i >= 0 && i < 14) {
                            if (Tabela[i][g] == "(0)") {
                                return 0;
                            }
                            if (Tabela[i][g] == "( )") {
                                cont++;
                            }
                        }
                    }
                }
                if (cont != 0) {
                    for (int i = 0; i < limitedosbarcos; i++) {
                        Tabela[linha][coluna + i] = "(0)";
                    }
                    return 1;
                }
            }
        } else if (direcao == 2) {
            if (linha >= limitedosbarcos - 1) {
                for (int i = linha + 1; i <= linha - limitedosbarcos; i--) {
                    for (int g = coluna - 1; g <= coluna + 1; g++) {
                        if (g >= 0 && g < 14 && i >= 0 && i < 14) {
                            if (Tabela[i][g] == "(0)") {
                                return 0;
                            }
                            if (Tabela[i][g] == "( )") {
                                cont++;
                            }
                        }
                    }
                }
                if (cont != 0) {
                    for (int i = 0; i < limitedosbarcos; i++) {
                        Tabela[linha - i][coluna] = "(0)";
                    }
                    return 1;
                }
            }
        } else if (direcao == 3) {
            if (linha <= 14 - limitedosbarcos) {
                for (int i = linha - 1; i <= linha + limitedosbarcos; i++) {
                    for (int g = coluna - 1; g <= coluna + 1; g++) {
                        if (g >= 0 && g < 14 && i >= 0 && i < 14) {
                            if (Tabela[i][g] == "(0)") {
                                return 0;
                            }
                            if (Tabela[i][g] == "( )") {
                                cont++;
                            }
                        }
                    }
                }
                if (cont != 0) {
                    for (int i = 0; i < limitedosbarcos; i++) {
                        Tabela[linha + i][coluna] = "(0)";
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    public static int TiroDoPlayer(String[][] TabelaCPU, String[][] TabelaVisual, int atingidos, Conversor conversao) {
        int horizontal = 0, linhaVertical = 0, i = 1, numero = 0;
        char vertical;
        Scanner scan = new Scanner(System.in), scaninho = new Scanner(System.in);
        String teste;
        System.out.println();
        while (i == 1) {

            System.out.println("Digite a linha vertical da tabela ( letra maiúscula ): ");
            vertical = scan.next().charAt(0);
            linhaVertical = conversao.converter(vertical);

            if (linhaVertical > 13 || linhaVertical < 0) {
                System.out.println("Resposta inválida.");
            } else {
                i = 0;
            }
        }
        i = 1;
        while (i == 1) {
            System.out.println("Digite a linha horizontal da tabela ( número ): ");
            teste = scaninho.nextLine();
            try {
                numero = Integer.parseInt(teste);
                if (numero > 14 || numero < 1) {
                    System.out.println("Resposta inválida.");
                } else {
                    horizontal = numero - 1;
                    i = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Resposta inválida.");
            }
        }

        while (TabelaVisual[linhaVertical][horizontal] == "(*)" || TabelaVisual[linhaVertical][horizontal] == "(X)") {
            System.out.println("Essas cordenadas estão indisponíveis");
            i = 1;
            while (i == 1) {

                System.out.println("Digite a linha vertical da tabela ( letra maiúscula ): ");
                vertical = scan.next().charAt(0);
                linhaVertical = conversao.converter(vertical);

                if (linhaVertical > 13 || linhaVertical < 0) {
                    System.out.println("Resposta inválida.");
                } else {
                    i = 0;
                }
            }
            i = 1;
            while (i == 1) {
                System.out.println("Digite a linha horizontal da tabela ( número ): ");
                teste = scaninho.nextLine();
                try {
                    numero = Integer.parseInt(teste);
                    if (numero > 14 || numero < 1) {
                        System.out.println("Resposta inválida.");
                    } else {
                        horizontal = numero - 1;
                        i = 0;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Resposta inválida.");
                }
            }
        }

        if (TabelaCPU[linhaVertical][horizontal] == "(0)") {
            TabelaVisual[linhaVertical][horizontal] = "(*)";
            TabelaCPU[linhaVertical][horizontal] = "(*)";
            System.out.println("ACERTOU O ALVO!!");
            atingidos++;

            int contQuantidadedeBarcos = 0, contQuantidadedeAcertos = 0;
            contQuantidadedeBarcos = contBarcos(TabelaCPU, horizontal, linhaVertical);
            contQuantidadedeAcertos = contAcertos(TabelaVisual, horizontal, linhaVertical);

            if (contQuantidadedeBarcos == contQuantidadedeAcertos) {
                int marcador = 1;
                CobrindoDeAgua(TabelaVisual, TabelaCPU, contQuantidadedeBarcos, horizontal, linhaVertical);
                System.out.println("UM BARCO FOI AFUNDADO!!");
            }
        } else {
            TabelaVisual[linhaVertical][horizontal] = "(X)";
            TabelaCPU[linhaVertical][horizontal] = "(X)";
            System.out.println("ERROU O ALVO!!");
        }
        return atingidos;
    }

    public static void CobrindoDeAgua(String[][] TabelaVisual, String[][] TabelaCPU, int contQuantidadedeBarcos,
            int horizontal, int linhaVertical) {
        int i = 0;

        if (linhaVertical - 1 >= 0) {
            i = 0;
            if (TabelaVisual[linhaVertical - 1][horizontal] == "(*)") {
                while (TabelaVisual[linhaVertical - i][horizontal] != "( )"
                        && TabelaVisual[linhaVertical - i][horizontal] != "(X)") {
                    if (linhaVertical - i >= 0) {
                        if (horizontal + 1 <= 13) {
                            TabelaVisual[linhaVertical - i][horizontal + 1] = "(X)";
                            TabelaCPU[linhaVertical - i][horizontal + 1] = "(X)";
                        }
                        if (horizontal - 1 >= 0) {
                            TabelaVisual[linhaVertical - i][horizontal - 1] = "(X)";
                            TabelaCPU[linhaVertical - i][horizontal - 1] = "(X)";
                        }
                        i++;
                    }
                    if (linhaVertical - i < 0)
                        break;
                }
                if (linhaVertical - i >= 0) {

                    if (horizontal > 0 && horizontal < 13) {
                        TabelaVisual[linhaVertical - i][horizontal + 1] = "(X)";
                        TabelaVisual[linhaVertical - i][horizontal - 1] = "(X)";
                        TabelaVisual[linhaVertical - i][horizontal] = "(X)";
                        TabelaCPU[linhaVertical - i][horizontal + 1] = "(X)";
                        TabelaCPU[linhaVertical - i][horizontal - 1] = "(X)";
                        TabelaCPU[linhaVertical - i][horizontal] = "(X)";

                    }
                    if (horizontal == 13) {
                        TabelaVisual[linhaVertical - i][horizontal - 1] = "(X)";
                        TabelaVisual[linhaVertical - i][horizontal] = "(X)";
                        TabelaCPU[linhaVertical - i][horizontal - 1] = "(X)";
                        TabelaCPU[linhaVertical - i][horizontal] = "(X)";

                    }
                    if (horizontal == 0) {
                        TabelaVisual[linhaVertical - i][horizontal + 1] = "(X)";
                        TabelaVisual[linhaVertical - i][horizontal] = "(X)";
                        TabelaCPU[linhaVertical - i][horizontal + 1] = "(X)";
                        TabelaCPU[linhaVertical - i][horizontal] = "(X)";

                    }

                }
            }
            if (linhaVertical + 1 <= 13) {
                if (TabelaVisual[linhaVertical - 1][horizontal] == "( )"
                        && TabelaVisual[linhaVertical + 1][horizontal] == "(*)") {
                    if (linhaVertical - 1 >= 0) {

                        if (horizontal > 0 && horizontal < 13) {
                            TabelaVisual[linhaVertical - 1][horizontal + 1] = "(X)";
                            TabelaVisual[linhaVertical - 1][horizontal - 1] = "(X)";
                            TabelaVisual[linhaVertical - 1][horizontal] = "(X)";
                            TabelaCPU[linhaVertical - 1][horizontal + 1] = "(X)";
                            TabelaCPU[linhaVertical - 1][horizontal - 1] = "(X)";
                            TabelaCPU[linhaVertical - 1][horizontal] = "(X)";

                        }
                        if (horizontal == 13) {
                            TabelaVisual[linhaVertical - 1][horizontal - 1] = "(X)";
                            TabelaVisual[linhaVertical - 1][horizontal] = "(X)";
                            TabelaCPU[linhaVertical - 1][horizontal - 1] = "(X)";
                            TabelaCPU[linhaVertical - 1][horizontal] = "(X)";

                        }
                        if (horizontal == 0) {
                            TabelaVisual[linhaVertical - 1][horizontal + 1] = "(X)";
                            TabelaVisual[linhaVertical - 1][horizontal] = "(X)";
                            TabelaCPU[linhaVertical - 1][horizontal + 1] = "(X)";
                            TabelaCPU[linhaVertical - 1][horizontal] = "(X)";

                        }

                    }
                }
            }
        }

        if (linhaVertical + 1 <= 13) {
            i = 0;
            if (TabelaVisual[linhaVertical + 1][horizontal] == "(*)") {
                while (TabelaVisual[linhaVertical + i][horizontal] != "( )"
                        && TabelaVisual[linhaVertical + i][horizontal] != "(X)") {
                    if (linhaVertical + i <= 13) {
                        if (horizontal + 1 <= 13) {
                            TabelaVisual[linhaVertical + i][horizontal + 1] = "(X)";
                            TabelaCPU[linhaVertical + i][horizontal + 1] = "(X)";

                        }
                        if (horizontal - 1 >= 0) {
                            TabelaVisual[linhaVertical + i][horizontal - 1] = "(X)";
                            TabelaCPU[linhaVertical + i][horizontal - 1] = "(X)";

                        }
                        i++;
                    }
                    if (linhaVertical + i > 13)
                        break;
                }
                if (linhaVertical + i <= 13) {
                    if (TabelaVisual[linhaVertical + 1][horizontal] == "( )"
                            && TabelaVisual[linhaVertical - 1][horizontal] == "(*)") {
                        if (horizontal > 0 && horizontal < 13) {
                            TabelaVisual[linhaVertical + i][horizontal + 1] = "(X)";
                            TabelaVisual[linhaVertical + i][horizontal - 1] = "(X)";
                            TabelaVisual[linhaVertical + i][horizontal] = "(X)";
                            TabelaCPU[linhaVertical + i][horizontal + 1] = "(X)";
                            TabelaCPU[linhaVertical + i][horizontal - 1] = "(X)";
                            TabelaCPU[linhaVertical + i][horizontal] = "(X)";

                        }
                        if (horizontal == 13) {
                            TabelaVisual[linhaVertical + i][horizontal - 1] = "(X)";
                            TabelaVisual[linhaVertical + i][horizontal] = "(X)";

                            TabelaCPU[linhaVertical + i][horizontal - 1] = "(X)";
                            TabelaCPU[linhaVertical + i][horizontal] = "(X)";

                        }
                        if (horizontal == 0) {
                            TabelaVisual[linhaVertical + i][horizontal + 1] = "(X)";
                            TabelaVisual[linhaVertical + i][horizontal] = "(X)";
                            TabelaCPU[linhaVertical + i][horizontal + 1] = "(X)";
                            TabelaCPU[linhaVertical + i][horizontal] = "(X)";

                        }
                    }
                }
            }
            if (linhaVertical - 1 >= 0) {
                if (linhaVertical + 1 <= 13) {
                    if (horizontal > 0 && horizontal < 13) {
                        TabelaVisual[linhaVertical + 1][horizontal + 1] = "(X)";
                        TabelaVisual[linhaVertical + 1][horizontal - 1] = "(X)";
                        TabelaVisual[linhaVertical + 1][horizontal] = "(X)";
                        TabelaCPU[linhaVertical + 1][horizontal + 1] = "(X)";
                        TabelaCPU[linhaVertical + 1][horizontal - 1] = "(X)";
                        TabelaCPU[linhaVertical + 1][horizontal] = "(X)";

                    }
                    if (horizontal == 13) {
                        TabelaVisual[linhaVertical + 1][horizontal - 1] = "(X)";
                        TabelaVisual[linhaVertical + 1][horizontal] = "(X)";

                        TabelaCPU[linhaVertical + 1][horizontal - 1] = "(X)";
                        TabelaCPU[linhaVertical + 1][horizontal] = "(X)";

                    }
                    if (horizontal == 0) {
                        TabelaVisual[linhaVertical + 1][horizontal + 1] = "(X)";
                        TabelaVisual[linhaVertical + 1][horizontal] = "(X)";
                        TabelaCPU[linhaVertical + 1][horizontal + 1] = "(X)";
                        TabelaCPU[linhaVertical + 1][horizontal] = "(X)";

                    }

                }
            }
        }

        if (horizontal - 1 >= 0) {
            i = 0;
            if (TabelaVisual[linhaVertical][horizontal - 1] == "(*)") {
                while (TabelaVisual[linhaVertical][horizontal - i] != "( )"
                        && TabelaVisual[linhaVertical][horizontal - i] != "(X)") {
                    if (horizontal - i >= 0) {
                        if (linhaVertical + 1 <= 13) {
                            TabelaVisual[linhaVertical + 1][horizontal - i] = "(X)";
                            TabelaCPU[linhaVertical + 1][horizontal - i] = "(X)";
                        }
                        if (linhaVertical - 1 >= 0) {
                            TabelaVisual[linhaVertical - 1][horizontal - i] = "(X)";
                            TabelaCPU[linhaVertical - 1][horizontal - i] = "(X)";
                        }
                        i++;
                    }
                    if (horizontal - i < 0)
                        break;
                }
                if (horizontal - i >= 0) {
                    if (linhaVertical > 0 && linhaVertical < 13) {
                        TabelaVisual[linhaVertical + 1][horizontal - i] = "(X)";
                        TabelaVisual[linhaVertical - 1][horizontal - i] = "(X)";
                        TabelaVisual[linhaVertical][horizontal - i] = "(X)";
                        TabelaCPU[linhaVertical + 1][horizontal - i] = "(X)";
                        TabelaCPU[linhaVertical - 1][horizontal - i] = "(X)";
                        TabelaCPU[linhaVertical][horizontal - i] = "(X)";

                    }
                    if (linhaVertical == 13) {
                        TabelaVisual[linhaVertical - 1][horizontal - i] = "(X)";
                        TabelaVisual[linhaVertical][horizontal - i] = "(X)";
                        TabelaCPU[linhaVertical - 1][horizontal - i] = "(X)";
                        TabelaCPU[linhaVertical][horizontal - i] = "(X)";

                    }
                    if (linhaVertical == 0) {
                        TabelaVisual[linhaVertical + 1][horizontal - i] = "(X)";
                        TabelaVisual[linhaVertical][horizontal - i] = "(X)";
                        TabelaCPU[linhaVertical + 1][horizontal - i] = "(X)";
                        TabelaCPU[linhaVertical][horizontal - i] = "(X)";

                    }

                }
            }
            if (horizontal + 1 <= 13) {
                if (TabelaVisual[linhaVertical][horizontal - 1] == "( )"
                        && TabelaVisual[linhaVertical][horizontal + 1] == "(*)")
                    if (horizontal - 1 >= 0) {
                        if (linhaVertical > 0 && linhaVertical < 13) {
                            TabelaVisual[linhaVertical + 1][horizontal - 1] = "(X)";
                            TabelaVisual[linhaVertical - 1][horizontal - 1] = "(X)";
                            TabelaVisual[linhaVertical][horizontal - 1] = "(X)";
                            TabelaCPU[linhaVertical + 1][horizontal - 1] = "(X)";
                            TabelaCPU[linhaVertical - 1][horizontal - 1] = "(X)";
                            TabelaCPU[linhaVertical][horizontal - 1] = "(X)";

                        }
                        if (linhaVertical == 13) {
                            TabelaVisual[linhaVertical - 1][horizontal - 1] = "(X)";
                            TabelaVisual[linhaVertical][horizontal - 1] = "(X)";
                            TabelaCPU[linhaVertical - 1][horizontal - 1] = "(X)";
                            TabelaCPU[linhaVertical][horizontal - 1] = "(X)";

                        }
                        if (linhaVertical == 0) {
                            TabelaVisual[linhaVertical + 1][horizontal - 1] = "(X)";
                            TabelaVisual[linhaVertical][horizontal - 1] = "(X)";
                            TabelaCPU[linhaVertical + 1][horizontal - 1] = "(X)";
                            TabelaCPU[linhaVertical][horizontal - 1] = "(X)";

                        }

                    }
            }
        }

        if (horizontal + 1 <= 13) {
            i = 0;
            if (TabelaVisual[linhaVertical][horizontal + 1] == "(*)") {
                while (TabelaVisual[linhaVertical][horizontal + i] != "( )"
                        && TabelaVisual[linhaVertical][horizontal + i] != "(X)") {
                    if (horizontal + i <= 13) {
                        if (linhaVertical + 1 <= 13) {
                            TabelaVisual[linhaVertical + 1][horizontal + i] = "(X)";
                            TabelaCPU[linhaVertical + 1][horizontal + i] = "(X)";
                        }
                        if (linhaVertical - 1 >= 0) {
                            TabelaVisual[linhaVertical - 1][horizontal + i] = "(X)";
                            TabelaCPU[linhaVertical - 1][horizontal + i] = "(X)";
                        }
                        i++;
                    }
                    if (horizontal + i > 13)
                        break;
                }
                if (horizontal + i <= 13) {
                    if (linhaVertical > 0 && linhaVertical < 13) {
                        TabelaVisual[linhaVertical + 1][horizontal + i] = "(X)";
                        TabelaVisual[linhaVertical - 1][horizontal + i] = "(X)";
                        TabelaVisual[linhaVertical][horizontal + i] = "(X)";
                        TabelaCPU[linhaVertical + 1][horizontal + i] = "(X)";
                        TabelaCPU[linhaVertical - 1][horizontal + i] = "(X)";
                        TabelaCPU[linhaVertical][horizontal + i] = "(X)";

                    }
                    if (linhaVertical == 13) {
                        TabelaVisual[linhaVertical - 1][horizontal + i] = "(X)";
                        TabelaVisual[linhaVertical][horizontal + i] = "(X)";
                        TabelaCPU[linhaVertical - 1][horizontal + i] = "(X)";
                        TabelaCPU[linhaVertical][horizontal + i] = "(X)";
                    }
                    if (linhaVertical == 0) {
                        TabelaVisual[linhaVertical + 1][horizontal + i] = "(X)";
                        TabelaVisual[linhaVertical][horizontal + i] = "(X)";
                        TabelaCPU[linhaVertical + 1][horizontal + i] = "(X)";
                        TabelaCPU[linhaVertical][horizontal + i] = "(X)";

                    }
                }

            }
            if (horizontal - 1 >= 0) {
                if (TabelaVisual[linhaVertical][horizontal + 1] == "( )"
                        && TabelaVisual[linhaVertical][horizontal - 1] == "(*)") {
                    if (horizontal + 1 <= 13) {
                        if (linhaVertical > 0 && linhaVertical < 13) {
                            TabelaVisual[linhaVertical + 1][horizontal + 1] = "(X)";
                            TabelaVisual[linhaVertical - 1][horizontal + 1] = "(X)";
                            TabelaVisual[linhaVertical][horizontal + 1] = "(X)";
                            TabelaCPU[linhaVertical + 1][horizontal + 1] = "(X)";
                            TabelaCPU[linhaVertical - 1][horizontal + 1] = "(X)";
                            TabelaCPU[linhaVertical][horizontal + 1] = "(X)";

                        }
                        if (linhaVertical == 13) {
                            TabelaVisual[linhaVertical - 1][horizontal + 1] = "(X)";
                            TabelaVisual[linhaVertical][horizontal + 1] = "(X)";
                            TabelaCPU[linhaVertical - 1][horizontal + 1] = "(X)";
                            TabelaCPU[linhaVertical][horizontal + 1] = "(X)";
                        }
                        if (linhaVertical == 0) {
                            TabelaVisual[linhaVertical + 1][horizontal + 1] = "(X)";
                            TabelaVisual[linhaVertical][horizontal + 1] = "(X)";
                            TabelaCPU[linhaVertical + 1][horizontal + 1] = "(X)";
                            TabelaCPU[linhaVertical][horizontal + 1] = "(X)";

                        }
                    }
                }

            }
        }

    }

    public static int contAcertos(String[][] TabelaVisual, int horizontal, int linhaVertical) {
        int cont = 0;

        if (linhaVertical - 1 >= 0) {
            if (TabelaVisual[linhaVertical - 1][horizontal] == "(*)") {
                int i = 0;
                while (TabelaVisual[linhaVertical - i][horizontal] != "( )"
                        && TabelaVisual[linhaVertical - i][horizontal] != "(X)"
                        && TabelaVisual[linhaVertical - i][horizontal] != "(0)") {
                    if (linhaVertical - i >= 0) {
                        cont++;
                        i++;
                    }
                    if (linhaVertical - i < 0)
                        break;
                }
            }
        }
        if (linhaVertical + 1 <= 13) {
            if (TabelaVisual[linhaVertical + 1][horizontal] == "(*)") {
                int i = 0;
                while (TabelaVisual[linhaVertical + i][horizontal] != "( )"
                        && TabelaVisual[linhaVertical + i][horizontal] != "(X)"
                        && TabelaVisual[linhaVertical + i][horizontal] != "(0)") {
                    if (linhaVertical + i <= 13) {
                        cont++;
                        i++;
                    }
                    if (linhaVertical + i > 13)
                        break;
                }
            }
        }
        if (horizontal - 1 >= 0) {
            if (TabelaVisual[linhaVertical][horizontal - 1] == "(*)") {
                int i = 0;
                while (TabelaVisual[linhaVertical][horizontal - i] != "( )"
                        && TabelaVisual[linhaVertical][horizontal - i] != "(X)"
                        && TabelaVisual[linhaVertical][horizontal - i] != "(0)") {
                    if (horizontal - i >= 0) {
                        cont++;
                        i++;
                    }
                    if (horizontal - i < 0)
                        break;
                }
            }
        }
        if (horizontal + 1 <= 13) {
            if (TabelaVisual[linhaVertical][horizontal + 1] == "(*)") {
                int i = 0;
                while (TabelaVisual[linhaVertical][horizontal + i] != "( )"
                        && TabelaVisual[linhaVertical][horizontal + i] != "(X)"
                        && TabelaVisual[linhaVertical][horizontal + i] != "(0)") {
                    if (horizontal + i <= 13) {
                        cont++;
                        i++;
                    }
                    if (horizontal + i > 13)
                        break;
                }
            }
        }
        return cont;
    }

    public static int contBarcos(String[][] TabelaCPU, int horizontal, int linhaVertical) {
        int cont = 0;
        if (linhaVertical - 1 >= 0) {
            if (TabelaCPU[linhaVertical - 1][horizontal] == "(0)"
                    || TabelaCPU[linhaVertical - 1][horizontal] == "(*)") {
                int i = 0;
                while (TabelaCPU[linhaVertical - i][horizontal] != "( )"
                        && TabelaCPU[linhaVertical - i][horizontal] != "(X)") {
                    if (linhaVertical - i >= 0) {
                        cont++;
                        i++;
                    }
                    if (linhaVertical - i < 0)
                        break;
                }
            }
        }
        if (linhaVertical + 1 <= 13) {
            if (TabelaCPU[linhaVertical + 1][horizontal] == "(0)"
                    || TabelaCPU[linhaVertical + 1][horizontal] == "(*)") {
                int i = 0;
                while (TabelaCPU[linhaVertical + i][horizontal] != "( )"
                        && TabelaCPU[linhaVertical + i][horizontal] != "(X)") {
                    if (linhaVertical + i <= 13) {
                        cont++;
                        i++;
                    }
                    if (linhaVertical + i > 13)
                        break;
                }
            }
        }
        if (horizontal - 1 >= 0) {
            if (TabelaCPU[linhaVertical][horizontal - 1] == "(0)"
                    || TabelaCPU[linhaVertical][horizontal - 1] == "(*)") {
                int i = 0;
                while (TabelaCPU[linhaVertical][horizontal - i] != "( )"
                        && TabelaCPU[linhaVertical][horizontal - i] != "(X)") {
                    if (horizontal - i >= 0) {
                        cont++;
                        i++;
                    }
                    if (horizontal - i < 0)
                        break;
                }
            }
        }
        if (horizontal + 1 <= 13) {
            if (TabelaCPU[linhaVertical][horizontal + 1] == "(0)"
                    || TabelaCPU[linhaVertical][horizontal + 1] == "(*)") {
                int i = 0;
                while (TabelaCPU[linhaVertical][horizontal + i] != "( )"
                        && TabelaCPU[linhaVertical][horizontal + i] != "(X)") {
                    if (horizontal + i <= 13) {
                        cont++;
                        i++;
                    }
                    if (horizontal + i > 13)
                        break;
                }
            }
        }
        return cont;
    }

    public static int TiroDaCPU(String[][] TabelaPlayer, int atingidos, int linha, int coluna) {
        System.out.println();
        System.out.print("Vez do Adversário");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        if (TabelaPlayer[linha][coluna] == "(0)") {
            TabelaPlayer[linha][coluna] = "(*)";
            atingidos++;
            System.out.println("O adversário acertou um barco ");

            int contQuantidadedeBarcos = 0, contQuantidadedeAcertos = 0;
            contQuantidadedeBarcos = contBarcos(TabelaPlayer, coluna, linha);
            contQuantidadedeAcertos = contAcertos(TabelaPlayer, coluna, linha);

            if (contQuantidadedeBarcos == contQuantidadedeAcertos) {
                int marcador = 0;
                CobrindoDeAgua(TabelaPlayer, TabelaPlayer, contQuantidadedeBarcos, coluna, linha);
                System.out.println("UM BARCO FOI AFUNDADO!!");
            }
        } else {
            TabelaPlayer[linha][coluna] = "(X)";
            System.out.println("\nO adversário não acertou nenhum barco");
        }

        return atingidos;
    }

    public static int TiroHard(String[][] TabelaPlayer, int linha2, int coluna2) {
        int comecou = 0;
        System.out.println();
        System.out.print("Vez do Adversário");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        if (TabelaPlayer[linha2][coluna2] == "(0)") {
            TabelaPlayer[linha2][coluna2] = "(*)";
            System.out.println("O adversário acertou um barco ");

            int contQuantidadedeBarcos = 0, contQuantidadedeAcertos = 0;
            contQuantidadedeBarcos = contBarcos(TabelaPlayer, coluna2, linha2);
            contQuantidadedeAcertos = contAcertos(TabelaPlayer, coluna2, linha2);

            if (contQuantidadedeBarcos == contQuantidadedeAcertos) {
                int marcador = 0;
                CobrindoDeAgua(TabelaPlayer, TabelaPlayer, contQuantidadedeBarcos, coluna2, linha2);
                System.out.println("UM BARCO FOI AFUNDADO!!");
            }

            comecou = 2;
        }
        if (TabelaPlayer[linha2][coluna2] == "( )") {
            TabelaPlayer[linha2][coluna2] = "(X)";
            System.out.println("O adversário não acertou nenhum barco");
            comecou = 3;
        }
        return comecou;
    }

    public static void inicio() {
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println(
                "      #####       ###   ##########  ###      ###       ###   ###    ###        ##     ##    ###  ##     ##    ###      ###");
        System.out.println(
                "     ##  ###    ## ##     ###     ## ##     ###       ###   ###   ## ##       ####   ##   ## ##  ##    ##   ## ##     ###");
        System.out.println(
                "    #####      ##  ##    ###     ##  ##    ###       #########   ##  ##  ##  ## ##  ##   ##  ##  ##   ##   ##  ##    ###");
        System.out.println(
                "   ##   ###  ########   ###    ########   ###       ###   ###  ########     ##   ####  ########  ##  ##  ########   ###");
        System.out.println(
                "  #######   ##     ##  ###    ##     ##  ########  ###   ###  ##     ##    ##    ###  ##     ##   ###   ##     ##  ########");
        System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                                                  Aperte Enter para começar");
        System.out.println(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print("                                                             ");
        scan.nextLine();

    }

    public static void FalaInicial() {
        String var = ("-Capitão Kirk a bordo!\n Recebemos relatos de atividade submarina hostil em nossas águas...\n Estamos em uma missão crítica para proteger nossa pátria e garantir\n a segurança de nossos mares.\n Precisamos de sua expertise estratégica para nos ajudar a localizar e neutralizar esses submarinos inimigos.\n Está pronto para se juntar a mim nesta Batalha Naval?");
        for (int i = 0; i < var.length(); i++) {
            System.out.print(var.charAt(i));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n");
    }

    public static void FalaFinal(int VitoriaPlayer, int VitoriaCPU) {
        if (VitoriaPlayer == 30) {
            String var = ("-Excelente trabalho, marinheiro!\n Graças à sua destreza e determinação, conseguimos repelir os submarinos inimigos e manter nossas águas seguras.\n Você provou ser um valioso aliado nesta Batalha Naval. Mas lembre-se, nosso trabalho aqui nunca termina.\n Estarei pronto para chamar por sua ajuda novamente, se precisarmos defender nossa nação mais uma vez");
            for (int i = 0; i < var.length(); i++) {
                System.out.print(var.charAt(i));
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\n");
        } else if (VitoriaCPU == 30) {
            String var = ("-Maldição! Nossos inimigos escaparam mais uma vez!\n Você chamou isso de estratégia? Foi uma desgraça completa!\n Nossa nação confiou em nós para proteger suas águas e falhamos miseravelmente!\n Você acha que isso é um jogo? Isso é uma batalha pela segurança de nosso povo!\n Precisamos estar alertas, precisamos estar focados, e você... você estava completamente perdido!\n Volte para o treinamento básico, e não se atreva a voltar aqui até que esteja pronto para lutar de verdade!");
            for (int i = 0; i < var.length(); i++) {
                System.out.print(var.charAt(i));
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\n");
        }
    }

    public static void main(String args[]) {

        int resposta = 1;
        while (resposta != 2) {
            Random randinho = new Random();
            Conversor conversao = new Conversor();
            String TabelaPlayer[][] = new String[14][14], teste;
            String TabelaCPU[][] = new String[14][14];
            String TabelaVisual[][] = new String[14][14];
            int VitoriaPlayer = 0, VitoriaCPU = 0, dificuldade = 0, VitoriaCPU2 = 0, comecou = 0;
            int linha2 = 0, coluna2 = 0, lado, i = 1, comprovante = 0, ladinho = 0;
            int LinhaI = 0, ColunaI = 0, j = 1;
            Scanner oi = new Scanner(System.in);

            for (int k = 0; k < 14; k++) {
                for (int g = 0; g < 14; g++) {
                    TabelaPlayer[k][g] = "( )";
                    TabelaCPU[k][g] = "( )";
                    TabelaVisual[k][g] = "( )";
                }
            }
            inicio();
            // Chamando o método tabela com a matriz preenchida
            PosicionamentoNavios(TabelaPlayer);
            PosicionamentoNavios(TabelaCPU);
            System.out.println();
            FalaInicial();
            while (j == 1) {
                System.out.print("                                       ");
                System.out.print("Selecione a dificuldade: fácil(1) | normal(2): ");
                teste = oi.nextLine();
                try {
                    int numero = Integer.parseInt(teste);
                    dificuldade = numero;
                    if (dificuldade == 1 || dificuldade == 2) {
                        j = 0;
                    } else {
                        System.out.print("                                       ");
                        System.out.println("Não é um número inteiro valido.");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("                                       ");
                    System.out.println("Não é um número inteiro valido.");
                }
            }
            while (dificuldade != 1 && dificuldade != 2) {
                System.out.print("                                               ");
                System.out.print("Esse modo de jogo é inválido, selecione a dificuldade fácil(1) ou normal(2): ");
                dificuldade = oi.nextInt();
            }

            System.out.println();
            System.out.print("                                       ");
            System.out.println("-Tabela Player-");
            tabela(TabelaPlayer);
            System.out.println();
            System.out.print("                                       ");
            System.out.println("-Campo Adversário- ");
            tabela(TabelaVisual);
            System.out.println();
            if (dificuldade == 1) {
                while (VitoriaPlayer != 30 || VitoriaCPU != 30) {
                    Random rand = new Random();
                    int linha = rand.nextInt(14);
                    int coluna = rand.nextInt(14);
                    while (TabelaPlayer[linha][coluna] == "(X)" || TabelaPlayer[linha][coluna] == "(*)") {
                        linha = rand.nextInt(14);
                        coluna = rand.nextInt(14);
                    }
                    VitoriaPlayer = TiroDoPlayer(TabelaCPU, TabelaVisual, VitoriaPlayer, conversao);
                    VitoriaCPU = TiroDaCPU(TabelaPlayer, VitoriaCPU, linha, coluna);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("                                       ");
                    System.out.println("-Tabela Player-");
                    tabela(TabelaPlayer);
                    System.out.println();
                    System.out.print("                                       ");
                    System.out.println("-Campo Adversário- ");
                    tabela(TabelaVisual);
                    System.out.println();
                }
                FalaFinal(VitoriaPlayer, VitoriaCPU);
                Scanner respostaFinal = new Scanner(System.in);
                i = 0;
                while (i == 0) {
                    System.out.println("Deseja Jogar novamente? Sim(1) | Não(2)");
                    teste = respostaFinal.nextLine();
                    try {
                        int numero = Integer.parseInt(teste);
                        if (numero == 2) {
                            resposta = numero;
                            i = 1;
                        } else {
                            if (numero == 1) {
                                resposta = numero;
                                i = 1;
                            } else {
                                System.out.println("Resposta Invalida");
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Resposta Invalida.");
                    }
                }
            } else if (dificuldade == 2) {
                while (VitoriaPlayer != 30 || VitoriaCPU != 30) {
                    Random rand = new Random();
                    int linha = rand.nextInt(14);
                    int coluna = rand.nextInt(14);
                    while (TabelaPlayer[linha][coluna] == "(X)" || TabelaPlayer[linha][coluna] == "(*)") {
                        linha = rand.nextInt(14);
                        coluna = rand.nextInt(14);
                    }
                    System.out.println();
                    VitoriaPlayer = TiroDoPlayer(TabelaCPU, TabelaVisual, VitoriaPlayer, conversao);
                    if ( VitoriaPlayer == 30 )
                        break;
                    if (comecou == 0) {
                        VitoriaCPU2 = TiroDaCPU(TabelaPlayer, VitoriaCPU, linha, coluna);
                        if (VitoriaCPU2 > VitoriaCPU) {
                            VitoriaCPU = VitoriaCPU2;
                            comecou = 1;
                            linha2 = linha;
                            coluna2 = coluna;
                            LinhaI = linha;
                            ColunaI = coluna;
                            i = 1;
                            // 0 = cima, 1 = direita, 2 = baixo, 3 = esquerda
                            while (i == 1) {
                                comprovante = 0;
                                lado = randinho.nextInt(4);
                                switch (lado) {
                                    case 0:
                                        linha2 = linha2 - 1;
                                        break;
                                    case 1:
                                        coluna2 = coluna2 + 1;
                                        break;
                                    case 2:
                                        linha2 = linha2 + 1;
                                        break;
                                    case 3:
                                        coluna2 = coluna2 - 1;
                                        break;
                                }
                                if (lado == 0 && linha2 == -1) {
                                    linha2 = linha2 + 1;
                                    comprovante = 1;
                                }
                                if (lado == 2 && linha2 == 14) {
                                    linha2 = linha2 - 1;
                                    comprovante = 1;
                                }
                                if (coluna2 == -1 && lado == 3) {
                                    coluna2 = coluna2 + 1;
                                    comprovante = 1;
                                }
                                if (coluna2 == 14 && lado == 1) {
                                    coluna2 = coluna2 - 1;
                                    comprovante = 1;
                                }
                                if (TabelaPlayer[linha2][coluna2] == "(X)") {
                                    comprovante = 1;
                                }
                                if (comprovante == 0) {
                                    i = 2;
                                    ladinho = lado;
                                }
                            }
                        }
                    } else {
                        if (comecou == 2) {
                            switch (ladinho) {
                                case 0:
                                    if (linha2 != 0) {
                                        linha2 = linha2 - 1;
                                    } else {
                                        comecou = 4;
                                    }
                                    break;
                                case 1:
                                    if (coluna2 != 13) {
                                        coluna2 = coluna2 + 1;
                                    } else {
                                        comecou = 4;
                                    }
                                    break;
                                case 2:
                                    if (linha2 != 13) {
                                        linha2 = linha2 + 1;
                                    } else {
                                        comecou = 4;
                                    }
                                    break;
                                case 3:
                                    if (coluna2 != 0) {
                                        coluna2 = coluna2 - 1;
                                    } else {
                                        comecou = 4;
                                    }
                                    break;
                            }
                            if (TabelaPlayer[linha2][coluna2] == "(X)") {
                                comecou = 4;
                            }
                            if (TabelaPlayer[linha2][coluna2] == "( )") {
                                comecou = 4;
                            }
                            if (comecou != 4) {
                                comecou = TiroHard(TabelaPlayer, linha2, coluna2);
                                if (comecou == 2) {
                                    VitoriaCPU++;
                                }
                                if (comecou == 3) {
                                    comecou = 5;
                                }
                            }
                        }
                        if (comecou == 4 || comecou == 5) {
                            if (comecou == 5) {
                                comecou = 4;
                            } else {
                                if (comecou == 4) {
                                    switch (ladinho) {
                                        case 0:
                                            if (LinhaI != 13) {
                                                LinhaI = LinhaI + 1;
                                            } else {
                                                VitoriaCPU = TiroDaCPU(TabelaPlayer, VitoriaCPU, linha, coluna);
                                                comecou = 0;
                                            }
                                            break;
                                        case 1:
                                            if (ColunaI != 0) {
                                                ColunaI = ColunaI - 1;
                                            } else {
                                                VitoriaCPU = TiroDaCPU(TabelaPlayer, VitoriaCPU, linha, coluna);
                                                comecou = 0;
                                            }
                                            break;
                                        case 2:
                                            if (LinhaI != 0) {
                                                LinhaI = LinhaI - 1;
                                            } else {
                                                VitoriaCPU = TiroDaCPU(TabelaPlayer, VitoriaCPU, linha, coluna);
                                                comecou = 0;
                                            }
                                            break;
                                        case 3:
                                            if (ColunaI != 13) {
                                                ColunaI = ColunaI + 1;
                                            } else {
                                                VitoriaCPU = TiroDaCPU(TabelaPlayer, VitoriaCPU, linha, coluna);
                                                comecou = 0;
                                            }
                                            break;
                                    }
                                    if (comecou != 0) {
                                        if (TabelaPlayer[LinhaI][ColunaI] == "(X)"
                                                || TabelaPlayer[LinhaI][ColunaI] == "( )") {
                                            VitoriaCPU2 = TiroDaCPU(TabelaPlayer, VitoriaCPU, linha, coluna);
                                            if (VitoriaCPU2 > VitoriaCPU) {
                                                VitoriaCPU = VitoriaCPU2;
                                                comecou = 6;
                                                linha2 = linha;
                                                coluna2 = coluna;
                                                LinhaI = linha;
                                                ColunaI = coluna;
                                                i = 1;
                                                while (i == 1) {
                                                    comprovante = 0;
                                                    lado = randinho.nextInt(4);
                                                    switch (lado) {
                                                        case 0:
                                                            linha2 = linha2 - 1;
                                                            break;
                                                        case 1:
                                                            coluna2 = coluna2 + 1;
                                                            break;
                                                        case 2:
                                                            linha2 = linha2 + 1;
                                                            break;
                                                        case 3:
                                                            coluna2 = coluna2 - 1;
                                                            break;
                                                    }
                                                    if (lado == 0 && linha2 == -1) {
                                                        linha2 = linha2 + 1;
                                                        comprovante = 1;
                                                    }
                                                    if (lado == 2 && linha2 == 14) {
                                                        linha2 = linha2 - 1;
                                                        comprovante = 1;
                                                    }
                                                    if (coluna2 == -1 && lado == 3) {
                                                        coluna2 = coluna2 + 1;
                                                        comprovante = 1;
                                                    }
                                                    if (coluna2 == 14 && lado == 1) {
                                                        coluna2 = coluna2 - 1;
                                                        comprovante = 1;
                                                    }
                                                    if (TabelaPlayer[linha2][coluna2] == "(X)") {
                                                        comprovante = 1;
                                                    }
                                                    if (comprovante == 0) {
                                                        i = 2;
                                                        ladinho = lado;
                                                    }
                                                }
                                            }
                                        } else {
                                            comecou = TiroHard(TabelaPlayer, LinhaI, ColunaI);
                                            if (comecou == 2) {
                                                VitoriaCPU++;
                                                comecou = 4;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (comecou == 3) {
                            switch (ladinho) {
                                case 0:
                                    linha2 = linha2 + 1;
                                    break;
                                case 1:
                                    coluna2 = coluna2 - 1;
                                    break;
                                case 2:
                                    linha2 = linha2 - 1;
                                    break;
                                case 3:
                                    coluna2 = coluna2 + 1;
                                    break;
                            }
                            i = 1;
                            while (i == 1) {
                                comprovante = 0;
                                lado = randinho.nextInt(4);
                                switch (lado) {
                                    case 0:
                                        linha2 = linha2 - 1;
                                        break;
                                    case 1:
                                        coluna2 = coluna2 + 1;
                                        break;
                                    case 2:
                                        linha2 = linha2 + 1;
                                        break;
                                    case 3:
                                        coluna2 = coluna2 - 1;
                                        break;
                                }
                                if (lado == 0 && linha2 == -1) {
                                    linha2 = linha2 + 1;
                                    comprovante = 1;
                                }
                                if (lado == 2 && linha2 == 14) {
                                    linha2 = linha2 - 1;
                                    comprovante = 1;
                                }
                                if (coluna2 == -1 && lado == 3) {
                                    coluna2 = coluna2 + 1;
                                    comprovante = 1;
                                }
                                if (coluna2 == 14 && lado == 1) {
                                    coluna2 = coluna2 - 1;
                                    comprovante = 1;
                                }
                                if (TabelaPlayer[linha2][coluna2] == "(X)") {
                                    comprovante = 1;
                                    switch (lado) {
                                        case 0:
                                            linha2 = linha2 + 1;
                                            break;
                                        case 1:
                                            coluna2 = coluna2 - 1;
                                            break;
                                        case 2:
                                            linha2 = linha2 - 1;
                                            break;
                                        case 3:
                                            coluna2 = coluna2 + 1;
                                            break;
                                    }
                                }
                                if (comprovante == 0) {
                                    i = 2;
                                    ladinho = lado;
                                }
                            }
                            comecou = TiroHard(TabelaPlayer, linha2, coluna2);
                            if (comecou == 2) {
                                VitoriaCPU++;
                            }
                        }
                        if (comecou == 1 || comecou == 6) {
                            if (comecou == 6) {
                                comecou = 1;
                            } else {
                                comecou = TiroHard(TabelaPlayer, linha2, coluna2);
                                if (comecou == 2) {
                                    VitoriaCPU++;
                                }
                            }
                        }
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("                                       ");
                    System.out.println("-Tabela Player-");
                    tabela(TabelaPlayer);
                    System.out.println();
                    System.out.print("                                       ");
                    System.out.println("-Campo Adversário- ");
                    tabela(TabelaVisual);
                    System.out.println();
                }
                FalaFinal(VitoriaPlayer, VitoriaCPU);
                Scanner respostaFinal = new Scanner(System.in);
                i = 0;
                while (i == 0) {
                    try {
                        System.out.println("Deseja Jogar novamente? Sim(1) | Não(2)");
                        teste = respostaFinal.nextLine();
                        int numero = Integer.parseInt(teste);
                        if (numero == 1) {
                            resposta = numero;
                            i = 1;
                        } else {
                            if (numero == 2) {
                                resposta = numero;
                                i = 1;
                            } else {
                                System.out.println("Resposta Invalida");
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Resposta Invalida");
                    }
                }
            }
        }
    }
}