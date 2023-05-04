import java.util.Scanner;

public class Programm {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();
        toyStore.addToy(1, "Слон", 10, 20);
        toyStore.addToy(2, "Кукла", 8, 30);
        toyStore.addToy(3, "Машинка", 12, 50);
        toyStore.addToy(4, "Конструктор", 15, 10);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Розыгрыш игрушек");
            System.out.println("2. Получить игрушку");
            System.out.println("3. Добавить игрушку");
            System.out.println("4. Изменить игрушку");
            System.out.println("5. Выход");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Введите количество призовых игрушек для розыгрыша: ");
                    int count = scanner.nextInt();
                    toyStore.drawPrizeToys(count);
                    System.out.println(count + " розыгрыш призовых игрушек");
                    break;
                case 2:
                    toyStore.getPrizeToy();
                    break;
                case 3:
                    System.out.println("Введите id игрушки:");
                    int id = scanner.nextInt();
                    System.out.println("Введите имя игрушки:");
                    String name = scanner.next();
                    System.out.println("Введите количество :");
                    int quantity = scanner.nextInt();
                    System.out.println("Введите вес:");
                    int weight = scanner.nextInt();
                    toyStore.addToy(id, name, quantity, weight);
                    System.out.println("Игрушка добавлена");
                    break;
                case 4:
                    System.out.println("Введите id игрушки:");
                    id = scanner.nextInt();
                    System.out.println("Введите вес:");
                    weight = scanner.nextInt();
                    toyStore.changeWeight(id, weight);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }
}
