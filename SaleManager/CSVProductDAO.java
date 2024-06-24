//đọc ghi fie csv
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVProductDAO implements ProductDAO {

    private static final String CSV_FILE_PATH = "products.csv";
    private static final String CSV_SEPARATOR = ",";

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(CSV_SEPARATOR);
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                products.add(new Product(id, name, (int) price));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProductById(int id) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(CSV_SEPARATOR);
                int productId = Integer.parseInt(data[0]);
                if (productId == id) {
                    String name = data[1];
                    double price = Double.parseDouble(data[2]);
                    return new Product(id, name, (int) price);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addProduct(Product product) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            bw.write(product.getId() + CSV_SEPARATOR + product.getName() + CSV_SEPARATOR + product.getPrice());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        List<Product> products = getAllProducts();
        boolean found = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                products.set(i, product);
                found = true;
                break;
            }
        }
        if (found) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
                for (Product p : products) {
                    bw.write(p.getId() + CSV_SEPARATOR + p.getName() + CSV_SEPARATOR + p.getPrice());
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    @Override
    public void deleteProduct(int id) {
        List<Product> products = getAllProducts();
        boolean found = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                found = true;
                break;
            }
        }
        if (found) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
                for (Product p : products) {
                    bw.write(p.getId() + CSV_SEPARATOR + p.getName() + CSV_SEPARATOR + p.getPrice());
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Product not found.");
        }
    }
}

