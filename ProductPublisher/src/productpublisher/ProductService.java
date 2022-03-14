package productpublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import shopdb.Database;
import shopdb.DatabaseImpl;

public class ProductService implements ProductInterface{
	
	private Connection connection = null;
	private Statement statement = null;
	private Database database;
	private ResultSet resultSet;
	
	Scanner sc = new Scanner(System.in);
	
	public ProductService() {
		super();
		database = new DatabaseImpl();
		connection = database.getDatabaseConnection();
	}

	//Add Product into the system
	@Override
	public void insertProduct() {
		// TODO Auto-generated method stub
     Product product = new Product();
		
		System.out.println("Enter Product Name : ");
		product.setName(sc.nextLine().trim());

		System.out.println("Enter Product Price : ");
		product.setPrice(sc.nextLine().trim());
		
		
		
		String query = "INSERT INTO product(name,price) "
				+ "VALUES('"+ product.getName() +"', '" +product.getPrice()+ "')";
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Product successfully inserted ...");
		} catch (SQLException exc) {
			System.out.println("Error with insert Product");
			System.out.println(exc.getMessage());
		}
		
	}



	//Get Product Details from the system
	@Override
	public void searchProduct() {
		
		int id;
		
		System.out.println("Enter id : ");
		id = (sc.nextInt());
		
		String query = "SELECT * FROM product WHERE id = '"+ id +"'";
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {  
		    	  System.out.printf("%20d %20s %20s \n",resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("price"));		    	
		      }		    	

		} catch (SQLException exc) {
			System.out.println("Error with get Product by id");
			System.out.println(exc.getMessage());
		}
	}

	//Get All Product Details from the system
	@Override
	public void getallProduct() {
		// TODO Auto-generated method stub
		Product product = new Product();
		List<Product> productList = new ArrayList<>();
		String query = "SELECT * FROM Product";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			System.out.printf("%20s %20s %20s \n","ID","Name","Price");  
	      
		      while (resultSet.next()) {  
		    	  System.out.printf("%20d %20s %20s \n",resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("price"));  
		      }
		} catch (SQLException exc) {
			System.out.println("Error with get all Products");
			System.out.println(exc.getMessage());
		}
	}

	//Delete Product Detail from the system
	@Override
	public void deleteProduct() {
		// TODO Auto-generated method stub
		int id;
		
		System.out.println("Enter Product ID : ");
		id = (sc.nextInt());
		
		
		try {			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM product WHERE id = '"+ id +"'"); 
		    	  System.out.printf("Successfully Deleted!");

		} catch (SQLException exc) {
			System.out.println("Error with get Product by Product ID");
			System.out.println(exc.getMessage());
		}
	}
		
}

