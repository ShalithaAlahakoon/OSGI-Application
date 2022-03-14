package productconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import productpublisher.ProductInterface;

public class Activator implements BundleActivator {


	ServiceReference serviceReference;


	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		serviceReference = context.getServiceReference(ProductInterface.class.getName());
		@SuppressWarnings("unchecked")
		ProductInterface producInterface = (ProductInterface)context.getService(serviceReference);	
		displayMainMenu(producInterface);
	}


	public void stop(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		System.out.println("Good Bye!");
		context.ungetService(serviceReference);
	}
	
	public void displayMainMenu(ProductInterface productInterface) {
		
		int option;
		String suboption = "y";
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("----------OSGI super market----------");
		System.out.println("1  - Add Product");
		System.out.println("2  - Get all Product");
		System.out.println("3  - Search Product by ID");
		System.out.println("4  - Delete Product by ID");
		System.out.print("Choose an option : ");
		
		option = Integer.parseInt(sc.nextLine().trim());
		switch(option) {
			case 1:
				productInterface.insertProduct();
				
				while(suboption.equals("y")) {
					System.out.println("\n\nDo you want to Add Another Product (y/n)");
					suboption = sc.nextLine().trim();
		
					if(suboption.equals("y")) {
						productInterface.insertProduct();
					}
				}
				displayMainMenu(productInterface);
				break;
			case 2:
				productInterface.getallProduct();
				displayMainMenu(productInterface);
				break;
			case 3:
				productInterface.searchProduct();
				displayMainMenu(productInterface);
				break;
			case 4:
				productInterface.deleteProduct();
				displayMainMenu(productInterface);
				break;
			default:
				System.out.println("Incorrect Input... Try Again");
				displayMainMenu(productInterface);
		}
		
		
	}
}
