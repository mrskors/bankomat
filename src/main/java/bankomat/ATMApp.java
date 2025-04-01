package bankomat;

import bankomat.generator.AccountNumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ATMApp {
    private static final Logger logger = LoggerFactory.getLogger(ATMApp.class);

    public static void main(String[] args) {
        Bank bank = new Bank();
        ATM atm = new ATM(bank);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        // 1. Stwórz przykładowe konto i dodaj do banku
        Person person = new Person("Jan", "Kowalski", "123456");
        Card card = new Card("0000");
        Account account = new Account(AccountNumberGenerator.generateAccountNumber(), person, card);
        bank.addAccount(account.getNumber(), account);

        while (flag) {
            printMainMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    if (atm.authenticate(card, "0000")) {
                        flag = false;
                        logger.info("Użytkownik zalogował się do systemu");
                    }
                }
                case "2" -> {
                    System.out.println("Do zobaczenia!");
                    System.exit(0);
                }
                default -> System.out.println("Nieprawidłowy wybór.");
            }
        }

        while (atm.isAuthenticated()) {
            printTransactionMenu();

            String action = scanner.nextLine();
            logger.info("Użytkownik wybrał opcję: {}", action);

            switch (action) {
                case "1":
                    System.out.println(atm.checkBalance());
                    break;
                case "2":
                    atm.deposit(scanner.nextDouble());
                    scanner.nextLine();
                    break;
                case "3":
                    atm.withdraw(scanner.nextDouble());
                    scanner.nextLine();
                    break;
                case "4":
                    atm.showTransactionHistory();
                    break;
                case "5":
                    // showTransactionHistoryByType for DEPOSIT and WITHDRAW
                    atm.showTransactionHistoryByType(TransactionType.DEPOSIT);
                    atm.showTransactionHistoryByType(TransactionType.WITHDRAW);
                    break;
                case "6":
                    atm.changePin(scanner.nextLine());
                    break;
                case "7":
                    atm.logout();
                    System.out.println("Wylogowano.");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór.");

            }
        }
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("=== BANKOMAT ===");
        System.out.println("1. Zaloguj się");
        System.out.println("2. Zakończ");
    }

    private static void printTransactionMenu() {
        System.out.println("=== MENU OPERACJI ===");
        System.out.println("1. Sprawdź saldo");
        System.out.println("2. Wpłać środki");
        System.out.println("3. Wypłać środki");
        System.out.println("4. Historia transakcji");
        System.out.println("5. Historia tylko wpłat i wypłat");
        System.out.println("6. Zmień PIN");
        System.out.println("7. Wyloguj");
    }
}
