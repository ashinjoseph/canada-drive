package common;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtendManager {
    private static ExtentReports extent;
    
    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            extent = new ExtentReports(filePath, true);
            
            extent
                .addSystemInfo("Host Name", "Canada Drives")
                .addSystemInfo("Environment", "QA");
        }
        
        return extent;
    }
    public static ExtentReports getReporterInstance()
    {
    	return extent;
    }
}
