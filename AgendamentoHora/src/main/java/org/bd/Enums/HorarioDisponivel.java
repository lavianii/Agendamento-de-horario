package org.bd.Enums;

import java.util.Scanner;

public enum HorarioDisponivel {
    INTERVALO_1(1, "08:00:00"),
    INTERVALO_2(2, "08:30:00"),
    INTERVALO_3(3, "09:00:00"),
    INTERVALO_4(4, "09:30:00"),
    INTERVALO_5(5, "10:00:00"),
    INTERVALO_6(6, "10:30:00"),
    INTERVALO_7(7, "11:00:00"),
    INTERVALO_8(8, "11:30:00"),
    INTERVALO_9(9, "12:00:00"),
    INTERVALO_10(10, "12:30:00"),
    INTERVALO_11(11, "13:00:00"),
    INTERVALO_12(12, "13:30:00"),
    INTERVALO_13(13, "14:00:00"),
    INTERVALO_14(14, "14:30:00"),
    INTERVALO_15(15, "15:00:00"),
    INTERVALO_16(16, "15:30:00"),
    INTERVALO_17(17, "16:00:00"),
    INTERVALO_18(18, "16:30:00"),
    INTERVALO_19(19, "17:00:00"),
    INTERVALO_20(20, "17:30:00");

    private final int numero;
    private final String hora;

    HorarioDisponivel(int numero, String hora) {
        this.numero = numero;
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }

    public int getNumero() {
        return numero;
    }

    public static int obterHora() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número do horário desejado: ");
        int numeroEscolhido = scanner.nextInt();

        try {
            String horaEscolhida = obterHoraPorNumero(numeroEscolhido);
            System.out.println("Você escolheu o horário: " + horaEscolhida);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return numeroEscolhido;
    }

    public static String obterHoraPorNumero(int numero) {
        for (HorarioDisponivel horario : HorarioDisponivel.values()) {
            if (horario.getNumero() == numero) {
                return horario.getHora();
            }
        }
        throw new IllegalArgumentException("Número inválido: " + numero);
    }
}
