import java.util.*;

public class Main {

    public static boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void help() {
        System.out.println("Kоманды: add, remove, edit, clear, quantity, goods, exit");
    }

    public static void main(String[] args) {

        Basket shoppingBasket = new BasketImplement();
        System.out.println("Введите операцию или 'help' для справки.");
        boolean i = true;
        while(i == true) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите команду: ");
            String enteringAcommand = scanner.next();

            String name;
            int quantity;

            switch (enteringAcommand.toLowerCase()) {
                case "add":
                    System.out.println("Введите название товара:");
                    name = scanner.next();
                    System.out.println("Введите количество товара:");
                    shoppingBasket.addProduct(name, scanner.nextInt());
                    continue;

                case "remove":
                    System.out.println("Введите товар:");
                    name = scanner.next();
                    int error = shoppingBasket.removeProduct(name);
                    if (error == 1){
                        System.out.println("успешно удалён");
                    }
                    if (error == 0){
                        System.out.println("В корзине нет такого товара.");
                    }
                    continue;

                case "edit":
                    System.out.println("Введите товар:");
                    name = scanner.next();
                    quantity = scanner.nextInt();
                    System.out.println("Введите количество:");
                    error = shoppingBasket.updateProductQuantity(name, quantity);
                    if (error == 1){
                        System.out.println("Товар успешно обновлён");
                    }
                    if (error == 0){
                        System.out.println("В корзине нет такого товара.");
                    }
                    continue;

                case "clear":
                    shoppingBasket.clear();
                    continue;

                case "quantity":
                    System.out.println("Введите наименование товара:");
                    try {
                        System.out.println(shoppingBasket.getProductQuantity(scanner.next()));
                    }
                    catch (Exception exception) {
                        System.out.println("В корзине нет такого товара.");
                    }
                    continue;

                case "goods":
                    System.out.println(shoppingBasket.getProducts());
                    continue;

                case "exit":
                    i = false;
                    break;

                case "help":
                    help();
                    continue;

                default:
                    System.out.println("Неверная команда! Введите 'hepl' для справки.");
                    continue;
            }
        }
    }
}