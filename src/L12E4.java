import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class L12E4 {

    public static ArrayList<Car> loadFile(String filename) {
        List<String> carDetails;
        ArrayList<Car> carList = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            String line = in.readLine();
            while (line != null) {
                carDetails = Arrays.asList(line.split(","));
                Car newCar = new Car();
                newCar.make = carDetails.get(0);
                newCar.model = carDetails.get(1);
                newCar.year = Integer.parseInt(carDetails.get(2));
                newCar.vin = carDetails.get(3);
                newCar.price = Integer.parseInt(carDetails.get(4));
                newCar.colour = carDetails.get(5);
                newCar.mileage = Integer.parseInt(carDetails.get(6));
                newCar.condition = carDetails.get(7);

                carList.add(newCar);
                line = in.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error occurred reading file: " + e.toString());
        }
        return carList;
    }

    public static void displayCar(Car car) {
        System.out.format("%s %s (%d)%n", car.make, car.model, car.year);
        System.out.format("Vin: %s%n", car.vin);
        System.out.format("Colour: %s%n", car.colour);
        System.out.format("Mileage: %d%n", car.mileage);
        System.out.format("Condition: %s%n", car.condition);
        System.out.format("Price: %d%n", car.price);
        System.out.println("------------------------------------------");
    }

    public static void main(String[] args) {
        ArrayList<Car> carList = loadFile("src/carSales.txt");
        for (Car car : carList) {
            displayCar(car);
        }
    }

}
