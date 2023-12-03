public class L12E3 {
    public static void displaycar(Car car1) {
        System.out.format("(%d)%nVin: %s%nColour: %s%nMileage: %d%nCondition: %s%nPrice: %d%n",
                car1.year, car1.vin, car1.colour, car1.mileage, car1.condition, car1.price);
        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) {
        Car car1 = new Car();
        displaycar(car1);

        car1.make = "Ferrari";
        car1.model = "F439";
        car1.year = 2009;
        car1.vin = "3GYVKNEFXAG625569";
        car1.price = 55125;
        car1.colour = "White";
        car1.mileage = 45336;
        car1.condition = "Good";
        displaycar(car1);
    }
}
