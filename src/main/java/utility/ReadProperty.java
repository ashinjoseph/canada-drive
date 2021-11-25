package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

public class ReadProperty {

	private static final String configFileName = "config.properties";

	private static String file_seperator = File.separator;

	private static final Map<String, String> propertiesMap = new HashMap<String, String>();

	static {
		Properties config = new Properties();

		try {

			String configFilePath = "Configuration" + file_seperator
					+ configFileName;

			System.out.println(configFilePath);

			config.load(new FileInputStream(configFilePath));

			Set<Entry<Object, Object>> propertiesSet = config.entrySet();

			for (Entry<Object, Object> propertyEntry : propertiesSet) {
				propertiesMap.put((String) propertyEntry.getKey(),
						(String) propertyEntry.getValue());
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	public static String loadProperty(String propertyKey) {
		return propertiesMap.get(propertyKey);
	}

}
