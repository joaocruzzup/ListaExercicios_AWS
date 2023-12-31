package org.example.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MetodosView {

    private Scanner sc;

    public MetodosView() {
        sc = new Scanner(System.in);
    }

    public Long lerIdValido() {
        try {
            Long id = sc.nextLong();
            sc.nextLine();
            return id;
        } catch (InputMismatchException e) {
            System.out.println("Erro: Digite um número inteiro válido. Tente novamente.");
            sc.nextLine();
            return null;
        }
    }

    public String lerDataValida() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String data = sc.nextLine();
        while (true) {
            try {
                LocalDate.parse(data, formatter);
                return data;
            } catch (Exception e) {
                System.out.println("Erro: Digite uma data válida no formato YYYY-MM-DD. Tente novamente.");
                data = sc.nextLine();
            }
        }
    }

    public String lerHoraValida() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String hora;
        while (true) {
            hora = sc.nextLine();
            try {
                LocalTime.parse(hora, formatter);
                return hora;
            } catch (Exception e) {
                System.out.println("Erro: Digite uma hora válida no formato HH:mm:ss. Tente novamente.");
            }
        }
    }


    public String lerCpfValido() {
        String cpf;
        while (true) {
            System.out.print("Digite o CPF (123.123.123-12): ");
            cpf = sc.nextLine();
            if (validarCpf(cpf)) {
                break;
            } else {
                System.out.println("Erro: Digite um CPF válido no formato 123.123.123-12. Tente novamente.");
            }
        }
        return cpf;
    }

    private boolean validarCpf(String cpf) {
        String regex = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
        return cpf.matches(regex);
    }
}
