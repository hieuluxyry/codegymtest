import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    DataAccessObjectFactory factory = new CSVDataAccessObjectFactory();
    CSVProductDAO productDAO = new CSVProductDAO();
    public static void main(String[] args) {
        DataAccessObjectFactory factory = new CSVDataAccessObjectFactory();
        ProductDAO productDAO = factory.getProductDAO();

        boolean exit = false;
        while (!exit) {
            System.out.println("========= Product Management System =========");
            System.out.println("1. Add a new product");
            System.out.println("2. Update a product ");
            System.out.println("3. Delete a product");
            System.out.println("4. View all products");
            System.out.println("5. View a product by ID");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = readIntInput(); // Sử dụng phương thức đọc số nguyên an toàn
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addNewProduct(productDAO);
                    break;
                case 2:
                    updateProduct(productDAO);
                    break;
                case 3:
                    deleteProduct(productDAO);
                    break;
                case 4:
                    displayAllProducts(productDAO);
                    break;
                case 5:
                    viewProductById(productDAO);
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        }

        scanner.close();
    }

    private static int readIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next(); // Consume invalid input
        }
        return scanner.nextInt();
    }

    private static void displayAllProducts(ProductDAO productDAO) {
        List<Product> products = productDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("========= All Products =========");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private static void viewProductById(ProductDAO productDAO) {
        System.out.print("Enter product ID: ");
        int id = readIntInput();
        Product product = productDAO.getProductById(id);
        if (product != null) {
            System.out.println("========= Product Details =========");
            System.out.println(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void addNewProduct(ProductDAO productDAO) {
        System.out.print("Enter product ID: ");
        int id = readIntInput();
        scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        Product newProduct = new Product(id, name, price);
        productDAO.addProduct(newProduct);
        System.out.println("Product added successfully.");
    }

    private static void updateProduct(ProductDAO productDAO) {
        System.out.print("Enter product ID to update: ");
        int id = readIntInput();
        Product productToUpdate = productDAO.getProductById(id);
        if (productToUpdate != null) {
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter new product name: ");
            String newName = scanner.nextLine().trim();
            System.out.print("Enter new product price: ");
            double newPrice = scanner.nextDouble();
            productToUpdate.setName(newName);
            productToUpdate.setPrice(newPrice);
            productDAO.updateProduct(productToUpdate);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void deleteProduct(ProductDAO productDAO) {
        System.out.print("Enter product ID to delete: ");
        int id = readIntInput();
        productDAO.deleteProduct(id);
        System.out.println("Product deleted successfully.");
    }
}

