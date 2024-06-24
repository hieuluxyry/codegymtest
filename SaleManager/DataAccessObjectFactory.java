//Interface của Abstract Factory để tạo ra các DAO
public interface DataAccessObjectFactory {
    ProductDAO getProductDAO();
}
