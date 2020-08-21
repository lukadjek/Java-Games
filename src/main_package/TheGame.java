package main_package;

import java.util.Arrays;
import java.util.HashMap;

public class TheGame {

	private HashMap<String, InterfacePanel> serviceMap;

	public TheGame() {
		serviceMap = new HashMap<String, InterfacePanel>();
		addServices();
	}

	private void addServices() {
		serviceMap.put("Dice Rolling Game", new DiceGame());
		serviceMap.put("Day of the Week Game", new DayOfTheWeekGame());
	}

	public Object[] getServiceNames() {
		// System.out.println(serviceMap.keySet());
		System.out.println(Arrays.toString(serviceMap.keySet().toArray())); // or like above
		// System.out.println("This method returns: " + serviceMap.keySet().toArray());
		return serviceMap.keySet().toArray();
	}

	public Object getServiceJob(Object selectedService) {
		System.out.println("This method returns: " + serviceMap.get(selectedService));
		return serviceMap.get(selectedService);
	}

}
