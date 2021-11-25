package functions;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import screens.BuyVehicle;

public class BuyVehicleFromInventory extends BuyVehicle {
	WebDriver driver;

	public BuyVehicleFromInventory(WebDriver driver) {
		super(driver);
	}

	public void buyVehicle(Map<Object, Object> map) throws Exception {

		try {
			if (map.containsKey("Make") && map.containsKey("Model")
					&& map.containsKey("SelectionID")) {
				String make = (String) map.get("Make");
				String model = (String) map.get("Model");
				String selectionID = (String) map.get("SelectionID");
				if (make != "") {
					SelectMake(make);
				}
				if (model != "") {

					SelectModel(model);
				}
				if (selectionID != "") {

					SelectVehicle(make,model,selectionID);
				}
			}
			ClickGetStarted();

			if (map.containsKey("Address")) {
				if ((String) map.get("Address") != "") {
					String address = (String) map.get("Address");
					 SelectAddress(address);
				}
			}
			if (map.containsKey("Warranty")) {
				if ((String) map.get("Warranty") != "") {
					String warranty = (String) map.get("Warranty");
					SelectWarranty(warranty);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyBuyVehicle(Map<Object, Object> map) throws Exception {

		try {
			String make="";
			String model ="";
			if (map.containsKey("Make") && map.containsKey("Model")) {
				 make = (String) map.get("Make");
				 model = (String) map.get("Model");
				 }
			String vehicleDetail=make+" "+model;
			verifyVehicleDetails(vehicleDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
			}
		}



