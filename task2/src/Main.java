import java.util.*;

public class Main {

    public static boolean wasteSpace(char ch) {
        if (ch == ' ') {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean openBracket(char ch) {
        if (ch == '(') {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean closeBracket(char ch) {
        if (ch == ')') {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean oper(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^') {
            return true;
        }
        else {
            return false;
        }
    }

    public static int priority(char operation) {
        switch (operation) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    public static void calculateOperands(LinkedList<Double> digits, char operator) {

        double operand2 = digits.removeLast();
        double operand1 = digits.removeLast();
        double result;

        switch (operator) {
            case '+':
                result = operand1 + operand2;
                digits.add(result);
                break;
            case '-':
                result = operand1 - operand2;
                digits.add(result);
                break;
            case '*':
                result = operand1 * operand2;
                digits.add(result);
                break;
            case '/':
                result = operand1 / operand2;
                digits.add(result);
                break;
            case '^':
                digits.add(Math.pow(operand1, operand2));
                break;
        }
    }

    public static double calculateOperandsForOpenBracket(double operand2, double operand1 , char operator1) {

      //  double operand2 = digits1.removeLast();
      //  double operand1 = digits1.removeLast();
        double result;

        switch (operator1) {
            case '+':
                result = operand1 + operand2;
              //  digits1.add(result);
                return result;
            case '-':
                result = operand1 - operand2;
            //    digits1.add(result);
                return result;
            case '*':
                result = operand1 * operand2;
           //     digits1.add(result);
                return result;
            case '/':
                result = operand1 / operand2;
            //    digits1.add(result);
                return result;
            case '^':
              return (Math.pow(operand1, operand2));
        }
        return 0;
    }

    public static double functionOpenBracket(String str, int i, LinkedList<Integer> errorsOfCalculator,
                              LinkedList<Integer> indexI) {

        ArrayList<Double> digits1 = new ArrayList<Double>();
        ArrayList<Character> operators1 = new ArrayList<Character>();

       boolean check = true;
       digits1.add(0.0); // добавляется в список чисел, чтобы первое число было 0
       for (; i < str.length(); i++) {
           char ch = str.charAt(i);

           if (openBracket(ch)) {
               if (digits1.get(digits1.size() - 1) == 0 && operators1.isEmpty()){
                   operators1.add('+');
               }
               digits1.add(functionOpenBracket(str, i+1, errorsOfCalculator, indexI));
               i = indexI.getLast();
               if (!errorsOfCalculator.isEmpty()) {
                   System.out.println("Ошибка в парсинге строки.");
                   return 0;
               }
               continue;
           } else {
               if(closeBracket(ch) == true){
                   indexI.add(i);
                   break;
               }
               // Ошибки стоящих рядом операторов
               if (i > 1) {
                   if (oper(ch)){
                       if (oper(str.charAt(i-1))){
                           System.out.println(" Ошибка! Два оператора подряд.");
                           errorsOfCalculator.add(1);
                           return 0;
                       }
                   }
               }
               // если что-то кроме допустимого
               if (!wasteSpace(ch) && !openBracket(ch) && !closeBracket(ch) && !oper(ch) && !Character.isDigit(ch)) {
                   check = false;
                   break;
               }

               if (wasteSpace(ch))
                   continue;

               if (oper(ch)) {
                   while (!operators1.isEmpty() && priority(operators1.get(operators1.size() - 1)) >= priority(ch)) {
                       // сравнивается приоритет с конца, если у предыдущего больше, то выполняется
                       // например, 4 * 2 + 3. выполнится умножение
                       digits1.add(calculateOperandsForOpenBracket(digits1.remove(digits1.size() - 1),
                               digits1.remove(digits1.size() - 1), operators1.remove(operators1.size() - 1)));
                   }
                   operators1.add(ch); // добавляется оператор в список
               } else {
                   String operand = "";
                   int counterOfPoints = 0;
                   while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.')) {
                       // К первому нулю нужно прибавить число, если оно положительное
                       if (digits1.get(0) == 0 && digits1.get(digits1.size() - 1) == 0 && operators1.isEmpty()){
                           operators1.add('+');
                       }
                       if (str.charAt(i) == '.'){
                           counterOfPoints++;
                       }
                       if (counterOfPoints > 1){
                           System.out.println("Ошибка в парсинге строки.");
                           errorsOfCalculator.add(1);
                           return 0;
                       }
                       operand += str.charAt(i++); // получается чисто
                   }
                   i--;
                   digits1.add(Double.parseDouble(operand)); // добавляется чисто в список чисел
               }
           }
       }

       if (check) {
           while (!operators1.isEmpty()) {
               // начиная с последнего элемента выполняются операции пока не будет operators.isEmpty())
               digits1.add(calculateOperandsForOpenBracket(digits1.remove(digits1.size() - 1),
                       digits1.remove(digits1.size() - 1), operators1.remove(operators1.size() - 1)));
             // bad!!!  calculateOperandsForOpenBracket(digits1, operators1.removeLast());
           }
           // возвращается разультат
           return digits1.get(0);
       } else {
           System.out.println("Ошибка в парсинге строки.");
           errorsOfCalculator.add(1);
           return 0;
       }
   }

    public static int calculator(String str) {

        int errors = 0;
        LinkedList<Double> digits = new LinkedList<Double>();
        LinkedList<Character> operators = new LinkedList<Character>();
        LinkedList<Integer> errorsOfCalculator = new LinkedList<Integer>();
        LinkedList<Integer> indexI = new LinkedList<Integer>();

        //  решается проблема первого отрицательного числа
        digits.add(0.0); // добавляется в список чисел, чтобы первое число было 0
        boolean check = true; // проверка на ошибки
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // Ошибки стоящих рядом операторов
            if (i > 1) {
                if (oper(ch)){
                    if (oper(str.charAt(i-1))){
                        System.out.println(" Ошибка! Два оператора подряд.");
                        return 0;
                    }
                }
            }
            // если что-то кроме допустимого
            if (!wasteSpace(ch) && !openBracket(ch) && !closeBracket(ch) && !oper(ch) && !Character.isDigit(ch)) {
                check = false;
                break;
            }

            if (wasteSpace(ch))
                continue;

            if (openBracket(ch)) {
                if (digits.getLast() == 0 && operators.isEmpty()){
                    operators.add('+');
                }
                digits.add(functionOpenBracket(str, i+1, errorsOfCalculator, indexI));
                i = indexI.getLast();
                if (!errorsOfCalculator.isEmpty()) {
                    System.out.println("Ошибка в парсинге строки.");
                    return 0;
                }
                continue;
            }

            if (oper(ch)) {
                while (!operators.isEmpty() && priority(operators.getLast()) >= priority(ch)) {
                    // сравнивается приоритет с конца, если у предыдущего больше, то выполняется
                    // например, 4 * 2 + 3. выполнится умножение
                    calculateOperands(digits, operators.removeLast());
                }
                operators.add(ch); // добавляется оператор в список
            } else {
                String operand = "";
                int counterOfPoints = 0;
                while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.')) {
                    // К первому нулю нужно прибавить число, если оно положительное
                    if (digits.getFirst() == 0 && digits.getLast() == 0 && operators.isEmpty()){
                        operators.add('+');
                    }
                    if (str.charAt(i) == '.'){
                        counterOfPoints++;
                    }
                    if (counterOfPoints > 1){
                        System.out.println("Ошибка в парсинге строки.");
                        return 0;
                    }
                    operand += str.charAt(i++); // получается чисто
                }
                i--;
                digits.add(Double.parseDouble(operand)); // добавляется чисто в список чисел
            }
        }

        if (check) {
            while (!operators.isEmpty()) {
                // начиная с последнего элемента выполняются операции пока не будет operators.isEmpty())
                calculateOperands(digits, operators.removeLast());
            }
            System.out.println("result = " + digits.get(0));
            return 0;
        } else {
            System.out.println("Ошибка в парсинге строки.");
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("************************");
            System.out.println("Консольный калькулятор");
            System.out.println("Введите выражение, которое необходимо посчитать. Для выхода введите :q");
            String inStr = scanner.nextLine();
            if (inStr.equals(":q")) {
                break;
            }
            calculator(inStr);
        }
    }
}