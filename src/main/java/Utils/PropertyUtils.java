package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;



public class PropertyUtils {



	/** The property file. */
	static Properties propertyFile = new Properties();

	/** The file name. */
	String fileName;

	/** The value. */
	String value;

	/**
	 * Gets the property.
	 *
	 * @param property
	 *            the property
	 * @return the property
	 */
	public String getProperty(String property) {
		try {
			value = propertyFile.getProperty(property);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return value;
	}

	/**
	 * Sets the property.
	 *
	 * @param strKey
	 *            the str key
	 * @param strValue
	 *            the str value
	 * @throws Throwable
	 *             the throwable
	 */
	public void setProperty(String strKey, String strValue) throws Throwable {
		try {
			propertyFile.setProperty(strKey, strValue);
			propertyFile.store(new FileOutputStream(fileName), null);
		} catch (Exception exception) {
			exception.printStackTrace();

		}
	}
	
	public PropertyUtils(String fileName) {
		this.fileName = fileName;

		File propertyfile = new File(fileName);
		if (propertyfile.exists()) {
			FileInputStream fileInputStream = null;
			try {
				fileInputStream = new FileInputStream(propertyfile);
				if (fileInputStream != null) {
					propertyFile.load(fileInputStream);
					fileInputStream.close();
				}
			} catch (FileNotFoundException fileNotFoundException) {
				
			} catch (IOException iOException) {
				System.out
						.println("Error while Loading file in loading propetry file is ::" + iOException.getMessage());
			} catch (Exception exception) {
				

			} finally {
				IOUtils.closeQuietly(fileInputStream);

			}
		} else {
			
		}

	}

	public static void main(String[] args) {
		
		
		System.out.println("Property Value: "+propertyFile.getProperty("COLUMN_NAME"));
	}

}
