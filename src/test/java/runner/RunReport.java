package runner;

import reporting.ReportGenerator;

public class RunReport {

    public static void main(String[] args) {
//    	System.out.println("PATH = ");
//    	System.out.println(System.getenv("Path"));
    
        ReportGenerator.generateAllureReport();

    }

}