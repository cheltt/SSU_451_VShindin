import java.util.*;

class Main {
    public static void main( String[] args)
    {
        boolean h = true;
        while(h == true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(" ");
            System.out.println("Для выхода введите 'q' или для продолжения введите номер задания (1 или 2):");
            char theNuberOfTheTask = scanner.findInLine(".").charAt(0);
            switch (theNuberOfTheTask) {
                case '1':
                    firstTask(scanner);
                    break;
                case '2':
                    secondTask(scanner);
                    break;
                case 'q':
                    h = false;
                    break;
                default:
                    System.out.println("Вы ввели неверное значение.");
            }
        }
    }

    public static int functionFirstYear ()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите год начала:");
        Integer firstYear = scanner.nextInt();
        if ( firstYear > 0) {
            return firstYear;
        }
        else
        {
            return -1;
        }
    }

    public static int functionLastYear ()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите год конца :");
        Integer lastYear = scanner.nextInt();
        if ( lastYear > 0) {
            return lastYear;
        }
        else
        {
            return -1;
        }
    }

    public static int firstTask(Scanner scanner)
    {
        System.out.println("Введите год :");
        Integer year = scanner.nextInt();
        if (year >= 0)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.DAY_OF_MONTH, 13);
            for (int i = 0; i <= 11; i++)
            {
                calendar.set(Calendar.MONTH, i);
                if (calendar.get(Calendar.DAY_OF_WEEK) == 6)
                {
                    Locale locale = new Locale("RU");
                    System.out.println(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, locale));
                }
            }
        }
        else
        {
            System.out.println("Введённый год меньше нашей эры.");
            switch (year)
            {
                case -1:
                    firstTask(scanner);
                    return 0;
                default:
                    firstTask(scanner);
                    return 0;
            }
        }
        return 0;
    }

    public static int secondTask(Scanner scanner)
    {
        Integer firstYear = functionFirstYear ();
        if (firstYear == -1)
        {
            System.out.println("Год начала диапазона меньше начала нашей эры.");
            switch (firstYear)
            {
                case -1:
                    secondTask(scanner);
                    break;
                default:
                    secondTask(scanner);
            }
        }
        Integer lastYear = functionLastYear ();
        if (lastYear == -1)
        {
            System.out.println("Год конца диапазона меньше начала нашей эры.");
            switch (lastYear)
            {
                case -1:
                    secondTask(scanner);
                    return 0;
                default:
                    secondTask(scanner);
                    return 0;
            }
        }
        if (lastYear < firstYear)
        {
            System.out.println("Год конца диапазона меньше начала диапазона.");
            switch (lastYear)
            {
                case -1:
                    secondTask(scanner);
                    return 0;
                default:
                    secondTask(scanner);
                    return 0;
            }
        }

        System.out.println("Введите номер месяца:");
        Integer month = scanner.nextInt();
        if ((month >= 1) && (month <= 12))
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, month-1);
            calendar.set(Calendar.DAY_OF_MONTH, 13);
            int counter = 0;
            for (int i = firstYear; i <= lastYear; i++)
            {
                calendar.set(Calendar.YEAR, i);
                if (calendar.get(Calendar.DAY_OF_WEEK) == 6)
                {
                    System.out.println(calendar.get(Calendar.YEAR));
                    counter++;
                }
            }
            System.out.println("Количество найденныех лет, где пятница 13-го попадается в указанном месяце:" + counter);
        }
        else
        {
            System.out.println("Номер месяца должен быть от 1 до 12.");
            switch (month)
            {
                case -1:
                    secondTask(scanner);
                    break;
                default:
                    secondTask(scanner);
            }
        }
        return 0;
    }
}