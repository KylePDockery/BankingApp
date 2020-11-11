package com.banking_app;

import java.util.Scanner;
import java.text.NumberFormat;

public class Account {
    double balance;
    double previousTransaction;
    String customerName;
    String customerID;

    Account(String cname, String cid) {
        customerName = cname;
        customerID = cid;
    }

    private void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            previousTransaction = amount;
        }
    }

    private void withdraw(double amount) {
        if (amount > 0) {
            balance -= amount;
            previousTransaction = -amount;
        }
    }

    private void getPreviousTransaction() {
        if (previousTransaction > 0) {
            System.out.println("Deposited " + previousTransaction);
        } else if (previousTransaction < 0) {
            System.out.println("Withdrawn: " + Math.abs(previousTransaction));
        } else {
            System.out.println("No transaction occurred");
        }
    }


    private void calculateInterest(int years) {
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        double interestRate = .0185;
        double newBalance = (balance * interestRate * years) + balance;
        System.out.println("The current interest rate is " + (100 * interestRate) + "%");
        System.out.println("After " + years + " years, the balance will be: " + defaultFormat.format(newBalance));
    }

    private void menuText() {
        System.out.println("What would you like to do?\n");
        System.out.println("""
                A. Check your balance
                B. Make a deposit
                C. Make a withdrawal
                D. View previous transaction
                E. Calculate interest
                F. Exit""");
    }

    void showMenu() {
        char option;
        Scanner scanner = new Scanner(System.in);
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        System.out.println("Welcome, " + customerName + "!");
        System.out.println("You ID is: " + customerID + "\n");
        menuText();

        do {
            System.out.println("\n Enter an option: ");
            char option1 = scanner.next().charAt(0);
            option = Character.toUpperCase(option1);
            System.out.println();

            switch (option) {
                case 'A' -> {
                    System.out.println("==============\n Balance = " + defaultFormat.format(balance) +
                            "\n============== \n");
                    menuText();
                }
                case 'B' -> {
                    System.out.println("Enter an amount to deposit \n");
                    double amount = scanner.nextDouble();
                    deposit(amount);
                    System.out.println("You have deposited " + defaultFormat.format(amount) +
                            "\nYour new balance is " + defaultFormat.format(balance));
                    menuText();
                }
                case 'C' -> {
                    System.out.println("enter an amount to withdraw: \n");
                    double amount2 = scanner.nextDouble();
                    withdraw(amount2);
                    System.out.println("You have withdrawn " + defaultFormat.format(amount2) +
                            "\nYour new balance is " + defaultFormat.format(balance));
                    menuText();
                }
                case 'D' -> {
                    System.out.println("==================");
                    getPreviousTransaction();
                    System.out.println("==================\n");
                    menuText();
                }
                case 'E' -> {
                    System.out.println("Enter how many years of accrued interest: ");
                    int years = scanner.nextInt();
                    calculateInterest(years);
                    System.out.println("Your current balance is " + defaultFormat.format(balance));
                    menuText();
                }
                case 'F' -> System.out.println("===================");
                default -> System.out.println("Error: invalid option. Please enter another option: A, B, C, D, E, or F");
            }
        } while (option != 'F');
        System.out.println("Thank you for using Kyle's First Bank of Java!");
    }
}