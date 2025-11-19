import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Expense {
    private int expenseId;
    private double amount;
    private String category;
    private String description;

    public Expense(int expenseId, double amount, String category, String description) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Expense ID: " + expenseId +
                ", Amount: " + amount +
                ", Category: " + category +
                ", Description: " + description;
    }
}

class ExpenseManager {
    private ArrayList<Expense> expenses;
    private int nextId;

    public ExpenseManager() {
        expenses = new ArrayList<>();
        nextId = 1;
    }

    public void addExpense(double amount, String category, String description) {
        Expense newExpense = new Expense(nextId, amount, category, description);
        expenses.add(newExpense);
        System.out.println("Expense added with ID: " + nextId);
        nextId++;
    }

    public void deleteExpense(int expenseId) {
        boolean removed = expenses.removeIf(exp -> exp.getExpenseId() == expenseId);
        if (removed) {
            System.out.println("Expense deleted successfully.");
        } else {
            System.out.println("Expense ID not found.");
        }
    }

    public void viewAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        for (Expense exp : expenses) {
            System.out.println(exp);
        }
    }

    public void searchByCategory(String category) {
        boolean found = false;
        for (Expense exp : expenses) {
            if (exp.getCategory().equalsIgnoreCase(category)) {
                System.out.println(exp);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expenses found for category: " + category);
        }
    }

    public double getTotalExpense() {
        double total = 0;
        for (Expense exp : expenses) {
            total += exp.getAmount();
        }
        return total;
    }
}

public class ExpenseTracker {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Expense Tracker Menu =====");
            System.out.println("1. Add Expense");
            System.out.println("2. Delete Expense");
            System.out.println("3. View All Expenses");
            System.out.println("4. Search by Category");
            System.out.println("5. View Total Expense");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        try {
                            System.out.print("Enter amount: ");
                            double amount = sc.nextDouble();
                            sc.nextLine();

                            System.out.print("Enter category: ");
                            String category = sc.nextLine();

                            System.out.print("Enter description: ");
                            String description = sc.nextLine();

                            manager.addExpense(amount, category, description);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid amount!");
                            sc.nextLine();
                        }
                        break;

                    case 2:
                        System.out.print("Enter Expense ID to delete: ");
                        int id = sc.nextInt();
                        manager.deleteExpense(id);
                        break;

                    case 3:
                        manager.viewAllExpenses();
                        break;

                    case 4:
                        System.out.print("Enter category: ");
                        String cat = sc.nextLine();
                        manager.searchByCategory(cat);
                        break;

                    case 5:
                        System.out.println("Total Expense: " + manager.getTotalExpense());
                        break;

                    case 6:
                        System.out.println("Exiting...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                sc.nextLine();
            }
        }
    }
}

