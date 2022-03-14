package productpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	private ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Product Publisher service started");
		//Register Service
		ProductInterface productInterface = new ProductService();
		serviceRegistration = context.registerService(ProductInterface.class.getName(), productInterface, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Product Publisher service stopped");
		serviceRegistration.unregister();
	}

}
