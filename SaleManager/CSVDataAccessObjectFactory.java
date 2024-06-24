// Triển khai của DataAccessObjectFactory để tạo ra CSVProductDAO.
public class CSVDataAccessObjectFactory implements DataAccessObjectFactory {

    @Override
    public ProductDAO getProductDAO() {
        return new CSVProductDAO();
    }
}

