package isolette;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Isolette {

	public static void main(String[] args) throws FileNotFoundException {
		final String INPUT_FILE = "C:\\Users\\mraks\\Dropbox\\CSE564_Project\\DesignAndFiles\\InputFile.txt";
		final String INFANT_TEMP = "C:\\Users\\mraks\\Dropbox\\CSE564_Project\\DesignAndFiles\\infantTemeratures.txt";

		Nurse nurse = new Nurse();
		File inFile = new File(INPUT_FILE);
		Scanner sc = new Scanner(inFile);
		nurse.setControl(sc.nextBoolean());
		nurse.setLdTemp(sc.nextInt());
		nurse.setUdTemp(sc.nextInt());
		nurse.setLaTemp(sc.nextInt());
		nurse.setUaTemp(sc.nextInt());
		sc.close();	

		Infant infant = new Infant();
		File file = new File(INFANT_TEMP);
		Scanner scan = new Scanner(file);
		
		TemperatureSensor tempSensor = new TemperatureSensor();
		OperatorInterface operatorInterface = new OperatorInterface();
		Thermostat thermostat = new Thermostat();
		HeatSource heatSource = new HeatSource();

		if(nurse.isControl()) {
			while(scan.hasNextLine()) {
				infant.setInfantTemp((scan.nextInt()));
				operatorInterface.validateInput(nurse);
				thermostat.setCurrentTemp(tempSensor.getCurrentTemp(infant.getInfantTemp(),heatSource));
				//System.out.println(heatSource.isHeatControl());
				thermostat.setStatus(tempSensor, operatorInterface);
				heatSource.setHeatControl(thermostat.regulator(nurse.getLdTemp(),nurse.getUdTemp()));
				//System.out.println(heatSource.isHeatControl());
				thermostat.monitor(nurse.getLaTemp(), nurse.getUaTemp());
				operatorInterface.writeOutputFile(thermostat);

				//testing
				System.out.println(nurse.toString()); 
				System.out.println(infant.toString());
				System.out.println(tempSensor.toString());
				System.out.println(heatSource.toString());
				System.out.println(thermostat.toString());
				System.out.println(operatorInterface.toString());
				System.out.println("-------------------------------------------");
			}
			scan.close();
		}
		else {
			System.out.println("Isolette is OFF!");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			Date date = new Date();
			try {

				FileWriter fileWriter = new FileWriter("C:\\Users\\mraks\\Dropbox\\CSE564_Project\\DesignAndFiles\\IsoletteOutput.txt", true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				bufferedWriter.write("--------------------------------------------------------");
				bufferedWriter.newLine();
				bufferedWriter.write("Isolette is OFF");
				bufferedWriter.newLine();
				bufferedWriter.write("Timestamp: \t\t\t "+formatter.format(date));
				bufferedWriter.newLine();
				bufferedWriter.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}


}
