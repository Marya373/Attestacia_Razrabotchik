import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
        if (count <= 0) {
            System.out.println("Неверное количество игрушек");
            return;
        }

        int totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight() * toy.getQuantity();
        }

        if (totalWeight == 0) {
            System.out.println("Нет доступных игрушек");
            return;
        }

        if (count * 10 > totalWeight) {
            System.out.println("Недостаточно игрушек для розыгрыша");
            return;
        }

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int prizeToyWeight = 0;
            while (prizeToyWeight == 0) {
                int index = random.nextInt(toys.size());
                Toy toy = toys.get(index);
                if (toy.getQuantity() > 0) {
                    prizeToyWeight = toy.getWeight();
                    toy.setQuantity(toy.getQuantity() - 1);
                    prizeToys.add(toy);
                    toys.remove(index);
                }
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter("prizeToys.txt", true))) {
            for (Toy toy : prizeToys) {
                writer.println(toy.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

    public void getPrizeToy() {
        if (prizeToys.isEmpty()) {
            System.out.println("Нет призовых игрушек");
            return;
        }
        Toy toy = prizeToys.get(0);
        System.out.println("Вы получили призовую игрушку: " + toy.getName());
        prizeToys.remove(toy);
    }
}
