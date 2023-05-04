import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ToyStore {
    private List<Toy> toys;
    private List<Toy> prizeToys;

    public ToyStore() {
        toys = new ArrayList<>();
        prizeToys = new ArrayList<>();
    }

    public void addToy(int id, String name, int quantity, int weight) {
        toys.add(new Toy(id, name, quantity, weight));
    }

    public void changeWeight(int id, int weight) {
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                toy.setWeight(weight);
                return;
            }
        }
        System.out.println("Игрушка не найдена");
    }

    public void drawPrizeToys(int count) {
        int totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight() * toy.getQuantity();
        }

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int randomNumber = random.nextInt(totalWeight);
            int currentWeight = 0;
            for (Toy toy : toys) {
                currentWeight += toy.getWeight() * toy.getQuantity();
                if (randomNumber < currentWeight) {
                    toy.setQuantity(toy.getQuantity() - 1);
                    prizeToys.add(new Toy(toy.getId(), toy.getName(), 1, toy.getWeight()));
                    totalWeight -= toy.getWeight();
                    break;
                }
            }
        }
    }

    public void getPrizeToy() {
        if (prizeToys.isEmpty()) {
            System.out.println("Призовых игрушек не осталось");
            return;
        }

        Toy prizeToy = prizeToys.remove(0);
        System.out.println("Вы выиграли " + prizeToy.getName() + "!");
        try {
            File file = new File("prizeToys.txt");
            FileWriter writer = new FileWriter(file, true);
            writer.write(prizeToy.getName() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

