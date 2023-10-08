import java.util.*;
import java.util.concurrent.TimeUnit;
import static java.lang.System.out;
import java.lang.Character;

public class App {

    static void print(String s){
        System.out.println(s);
    }


    public static void printerApp() throws Exception{
        String creator = "Julius Ingeli";
        System.out.println("Printer app");
        System.out.println("App made by:" + creator);
        int number1 = 4, number2 = 5;
        System.out.println("Value of number 1 is: " + number1 + "\nValue of number 2 is: " + number2);
        int multiply, add, subtract;
        float division = number1/number2; 
        multiply = number1*number2;
        add = number1+number2;
        subtract = number1-number2;
        
        System.out.printf("Result of addition: %d\nResult of subtraction: %d\nResult of multiplication: %d\nResult of divison: %f\n",add,subtract,multiply,division);
        double n1 = 4, n2 = 5;
        System.out.println(n1/n2);
    }
    public static void comparerApp() throws Exception{
        Scanner myObj = new Scanner(System.in);
        int n1, n2, n3;
        System.out.printf("Enter first number:");
        n1 = myObj.nextInt();
        System.out.printf("Enter second number:");
        n2 = myObj.nextInt();
        if(n1 == n2){
            System.out.printf("Entered numbers are equal.\n");
        }
        else{
            String result = (n1 < n2) ? "First number is smaller than second number " : "First number is bigger than second number";
            System.out.println(result);
        }

        System.out.println("------SECOND COMPARISION------");

        System.out.printf("Enter first number:");
        n1 = myObj.nextInt();
        System.out.printf("Enter second number:");
        n2 = myObj.nextInt();
        System.out.printf("Enter third number:");
        n3 = myObj.nextInt();
        if(n1 == n2 && n2 == n3){
            System.out.println("A. All numbers are the same");
        }
        if(n1==n2 || n2>n3){
            if(n1==n2){
                System.out.println("B.First number and second number are the same");
            }
            if(n2>n3){
                System.out.println("B.Second number is greater than third number");
            }
        }
        if(n1>n2 && n1>n3){
            System.out.println("C.First number is greater than both second and third numbers");
        }
   
        if(n1<=n2){
            System.out.println("D.First number is equal or smaller than second number");
        }
        else if(n2>n3){
            System.out.println("D.Second number is greater than third number");
        }
        else{
            System.out.println("D. Fail");
        }
            
        
        if(n1==n2){
            System.out.println("E.First number and second number are equal.");
        }
        else if(n1==n3){    
            System.out.println("E.First number and third number are equal.");
            }
        else{
            System.out.println("E.Fail");
        }
        
        System.out.println("------THIRD COMPARISION------");
        String name1,name2,name3;
        System.out.printf("Enter first name:");
        name1 = myObj.nextLine();
        System.out.printf("Enter second name:");
        name2 = myObj.nextLine();
        System.out.printf("Enter third name:");
        name3 = myObj.nextLine();
        
        if(name1.equals(name2)){
            System.out.println("A. First name and second name are the same.");
        }
        if (!name1.equals(name2)){
            System.out.println("B. First and second name are not equal.");
        }
        if(name1.equals(name2)){
            System.out.println("C. First name and second name are the same.");
        }
        else if(name1.equals(name3)){
            System.out.println("C. First name and third name are the same.");
        }
        myObj.close();
    }
    public static void rng(int rngCalls, int min, int max, Random random)throws Exception{
        min++;    
        System.out.println("Random numbers: ");
        for(int i = 0; i<rngCalls;i++){
           int randN = random.nextInt(max)+min;
           System.out.println(randN);
        }

    }
    public static void calculator() throws Exception{
        System.out.println("Initializing calculator...");
        double n1,n2;
        char sign;
        Scanner obj = new Scanner(System.in);
        System.out.printf("Enter number:" );
        n1 = obj.nextDouble();
        System.out.printf("Enter number:" );
        n2 = obj.nextDouble();
        System.out.printf("Enter operation: + - * /:" );
        sign = obj.next().charAt(0);
        switch(sign){
            case '+':
                System.out.println("result:" + (n1+n2));
                break;
            case '-':
                System.out.println("result:" + (n1-n2));
                break; 
            case '*':
                System.out.println("result:" + (n1*n2));
                break; 
            case '/':
                System.out.println("result:" + (n1/n2));
                break; 
            default:
                System.out.println("Invalid operation.");
        }
        obj.close();
    }
    public static void lucky7(Random random) throws Exception {
        Scanner obj = new Scanner(System.in);
        System.out.printf("How much money do you want to put in?");
        int startercash = obj.nextInt(), cash=startercash;
        
        int[] numArr = {0,0,0};
        System.out.println("balance: " + cash + "$");
        while(cash>0){
            
            int seven = 0;
            for(int i = 0;i<3;i++){
                int randN = random.nextInt(10);
                numArr[i] = randN;
            }
            System.out.printf("%d %d %d\n",numArr[0],numArr[1],numArr[2]);
            for(int i = 0;i<3;i++){
                if(numArr[i]==7){
                    seven++;
                }
            }
            //TimeUnit.SECONDS.sleep(3);
            switch(seven){
                case 0:
                    cash--;                    
                    break;
                case 1:
                    cash+=3;
                    break;
                case 2:
                    cash+=5;

                    break;
                case 3:
                    cash+=10;
                    break;
            }
            System.out.println("balance: " + cash + "$");
            System.out.println("Play another round? Y/N");
            char choice = obj.next().charAt(0);
            Character.toLowerCase(choice);
            if(choice == 'y'){
                System.out.println("Continuing...");
            }
            else if(choice == 'n'){
                System.out.println("Stopping...");
                obj.close();
                break;
            }
            else{
                System.out.println("Invalid choice... continuing!");
            }
            
            
        }

        System.out.println("Final balance:" + cash + "$");

        if(cash==0){
            System.out.println("You lost all money.");
            obj.close();
            return;
        }
        else if(cash > startercash){
            System.out.println("You won " + (cash - startercash) +"$!");
            obj.close();
            return;
        }
        else if(cash < startercash){
            System.out.println("You lost " + (startercash - cash) +"$!");
            obj.close();
            return;
        }
        else if(cash == startercash){
            System.out.println("You made nothing.");
            obj.close();
            return;
        }
    obj.close();
    }

    public static void ageApp(String[] args) throws Exception{
        int age = 18;
        if (age > 0 && age < 18){
            System.out.println("You are underage.");
            if (age >= 15){
                System.out.println("You can drive a moped.");
            }
            if (age == 18){
                System.out.println("You can drive a car.");
            }
        }
        else if(age >= 65){
            System.out.println("You are retired.");
        }
        else{
            System.out.println("You are an adult."); 
        }
        if(age % 10 == 0){
            System.out.println("Anniversary Party!");
        }
        else if(age == 100){
            System.out.printf("Congratulations!\nCongratulations!\nCongratulations!\n");
        }
        else if(age > 40 && age < 50){
            System.out.println("Happy mid life!");
        }
        System.out.println("Press space to exit.");
    }
    public static void nameHangman(Random random) throws Exception{
        String[] names = {"Jane","Emma","Paul","Joseph","John","Oscar","Bob","Boris"};
        int namesArrLen = names.length;
        Scanner obj = new Scanner(System.in);
        //System.out.println("DEBUG:"+namesArrLen);
        int randIdx = random.nextInt(namesArrLen);
        //System.out.println("DEBUG:"+randIdx);
        String guessName = names[randIdx];
        int nameLen = guessName.length();
        char[] empty;
        empty = new char[nameLen];
        for(int i = 0; i<nameLen;i++){
            empty[i] = '_';
        }
        int guesses = 3;
        System.out.println("Guess the name!");
        while(guesses>0){
            System.out.println(empty);
            String inputString = obj.nextLine();
            if(inputString.equals(guessName)){
                System.out.println("You win!");
                obj.close();
                return;
            }
            else{
                guesses--;
                if(guesses==0){
                    System.out.println("You lose! The name was: " + guessName);
                    obj.close();
                    return;
                }
                else{
                    while(true){
                        randIdx = random.nextInt(nameLen-1);
                        if(empty[randIdx]=='_'){
                            empty[randIdx] = guessName.charAt(randIdx);
                            break;
                        }
                        

                    }

                }
            }
        }
        obj.close();
    }

    public static void furnitureApp() throws Exception{
        String [] furniture = {"Table","Chair","Shelf","Sofa"};
        out.println("---First print---");
        for(int i = 0; i< furniture.length; i++){
            out.println(furniture[i]);
        }
        out.println("---Second print---");
        for(int i = 0;i<2;i++){
            out.println(furniture[i]);
        }
        out.println("---Third print---");
        for(int i = 0;i<furniture.length;i++){
            if(furniture[i].compareTo("Sofa")==0){
                out.println(furniture[i]);
            }
        }
    }
    public static void diceThrow(Random random)throws Exception{
        int [] thrownNumbers = new int[5];
        for(int i=0;i<thrownNumbers.length;i++){
            thrownNumbers[i] = random.nextInt(6)+1;
        }
        out.printf("Random numbers:");
        for(int i = 0; i<thrownNumbers.length;i++){
            out.printf(" %d ",thrownNumbers[i]);
        }
        int sum = 0;
        out.printf("\n");
        for(int i =0;i<thrownNumbers.length;i++){
            sum+=thrownNumbers[i];
        }
        out.printf("Sum of the random numbers: %d",sum);
    }

    public static void diceThrow2(Random random)throws Exception{
        int [] thrownNumbers = new int[5];
        for(int i=0;i<thrownNumbers.length;i++){
            int randN = random.nextInt(20)+1;
            boolean match = false;
            for(int j=0;j<thrownNumbers.length;j++){
                if(thrownNumbers[j]==randN){
                    match = true;
                    i--;
                    break;
                }
            }
            if(match == false){
                thrownNumbers[i] = randN;
            }
        }
        for(int i = 0;i<thrownNumbers.length;i++){
            out.printf(" %d ",thrownNumbers[i]);
        }
        out.printf("\n");
    }


    public static void memGame(Random random) throws Exception{
        print("Try to remember the following numbers:");
        int[] arr = new int [5];
        for(int i = 0;i<5;i++){
            arr[i] = random.nextInt(5)+1;
        }

        for(int i = 0;i<5;i++){
            out.printf("%d ", arr[i]);
        }
        out.printf("\n ");

        TimeUnit.SECONDS.sleep(4); //sleep

        try {                              //cls or clear
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int [] inarr = new int [5];
        Scanner obj = new Scanner(System.in);
        for(int i = 0; i<5;i++){
            out.printf("Type number %d\n", i+1);
            int in = obj.nextInt();
            inarr[i] = in;
        }
        print("Your numbers were:");
        for(int i = 0;i<5;i++){
            out.printf("%d ", inarr[i]);
        }
        out.printf("\n");
        print("The given numbers were:");
        for(int i = 0;i<5;i++){
            out.printf("%d ", arr[i]);
        }
        out.printf("\n");
        int rights = 0;
        for(int i =0; i<5;i++){
            if(inarr[i]==arr[i]){
                rights++;
            }
        }
        out.printf("You were correct %d times.\n", rights);
        obj.close();
    }

    public static void arrTest(Random random) throws Exception{
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i = 0; i<10; i++){
            arr.add(random.nextInt(10)+2);
        }

        arr.remove(4);

        for(int i = 0; i<10;i++){
            out.printf("%d ", arr.get(i));
        }

    }
    public static void arrDemo() throws Exception{
        Scanner obj = new Scanner(System.in); //scanner obj - input
        int rows; //number of rows
        char ch = '*'; //character to fill the string
        print("How many rows?"); 
        rows = obj.nextInt(); // input rows
        String s = new String(new char[rows]).replace('\0',' ');
        StringBuilder sb = new StringBuilder(s);
        for(int i = rows-1; i>-1;i--){
             sb.setCharAt(i,ch);
             TimeUnit.SECONDS.sleep(1);
             out.println(sb);
        }
        obj.close();
    }
 
    public static void printDemo()throws Exception{
        int n = 69;
        double pi = 3.1415926;
        System.out.println("println Hello");
        System.out.print("print Hello\n");
        System.out.print("print Hello\n");
        System.out.println("(println):The value is " + n);
        System.out.printf("(printf) The value is %d and the pi value is %f", n, pi);

    }
    static void printStuff(){
        print("********************");
        print("* Method Exercises *");
        print("********************");
    }
    static int areacalc(int w, int h){
        return w*h;
    }

    static ArrayList<String> sortStr(ArrayList<String>s){
        Collections.sort(s);
        return s;
    }

    static void multiply1000(int n){System.out.printf("%d",n*1000);}
    
    static void calculate(int n1, int n2, String s){
        Scanner numin = new Scanner(System.in);
        switch (s){
            case "sum":
                System.out.printf("The result is %d.",n1+n2);
                break;
            case "subtraction ":
                System.out.printf("The result is %d.",n1-n2);
                break;
            case "multiplication":
                System.out.printf("The result is %d.",n1*n2);
                break;
            default:
                System.out.printf("Wrong choice.",n1+n2);
                break;
                
        }
    }
        
    
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        Scanner choice = new Scanner(System.in);
        print("Select which app to use:\n1. Printer app\n2. Comparer App\n3. RNG\n4. Calculator\n5. Lucky 7\n6. Age app\n7. Name hangman\n8. Furniture app\n9. Dice thrower\n");
        print("10. Dice thrower 2\n11. Memory game\n12. Multiplication\n13. print\n14. area\n15. sorter\n16. calculator");
        print("DEMOS:\n17. Print demo\n18. Array demo\n19. Array test");
        while(true){
            int c = choice.nextInt();
            switch(c){
                case 1:
                    printerApp();
                    choice.close();
                    System.exit(0);
                case 2:
                    comparerApp();
                    choice.close();
                    System.exit(0);
                case 3:
                    rng(550, 0, 6, random);
                    choice.close();
                    System.exit(0);
                case 4:
                    calculator();
                    choice.close();
                    System.exit(0);
                case 5:
                    lucky7(random);
                    choice.close();
                    System.exit(0);
                case 6:
                    ageApp(args);
                    choice.close();
                    System.exit(0);
                case 7:
                    nameHangman(random);
                    choice.close();
                    System.exit(0);
                case 8:
                    furnitureApp();
                    choice.close();
                    System.exit(0);
                case 9:
                    diceThrow(random);
                    choice.close();
                    System.exit(0);
                case 10:
                    int dbg = 500;
                    while(dbg>0){
                    diceThrow2(random);
                    dbg--;
                    }
                    choice.close();
                    System.exit(0);
                case 11:
                    memGame(random);
                    choice.close();
                    System.exit(0);
                case 12:
                    print("Input number for multiplication:");
                    Scanner n = new Scanner(System.in);
                    int in = n.nextInt();                    
                    multiply1000(in);
                    n.close();
                    choice.close();
                    System.exit(0);
                case 13:
                    printStuff();
                    choice.close();
                    System.exit(0);
                case 14:
                    Scanner obj = new Scanner(System.in);
                    print("Input width:");
                    int w = obj.nextInt();
                    print("Input height");
                    int h = obj.nextInt();
                    int a = areacalc(w, h);
                    if(a>50){
                        System.out.printf("Area is %d. Huge area.\n",a);
                    }
                    else{
                        System.out.printf("Area is %d. Standard area.\n",a);
                    }
                    choice.close();
                    System.exit(0);
                case 15:
                    Scanner inp = new Scanner(System.in);
                    print("Word count:");
                    int wc = inp.nextInt();
                    inp.nextLine();//consuming the \n enter
                    ArrayList<String> wordList = new ArrayList<String>();
                    for(int i = 0; i<wc;i++){
                        out.printf("Input word:");
                        String word = inp.nextLine();
                        wordList.add(word);
                    }
                    sortStr(wordList);
                    print("Sorted list:");
                    for(int i = 0; i<wordList.size();i++){
                        print(wordList.get(i));
                    }
                    choice.close();
                    inp.close();
                    System.exit(0);
                case 16:
                    Scanner calcin = new Scanner(System.in);
                    Scanner opin = new Scanner(System.in);
                    int num1, num2;
                    String operator;
                    print("input number:");
                    num1 = calcin.nextInt();
                    print("input number:");
                    num2 = calcin.nextInt();
                    print("select operation (sum, subtraction, multiplication):");
                    operator = opin.nextLine();
                    calculate(num1,num2,operator);
                    choice.close();calcin.close();opin.close();                    
                    System.exit(0);
                
                case 17:
                    printDemo();
                    choice.close();
                    System.exit(0);
                case 18:
                    arrDemo();
                    choice.close();
                    System.exit(0);
                
                case 19:
                    arrTest(random);
                    choice.close();
                    System.exit(0);
                
                
                default:
                    out.printf("Choice %d is invalid, try again\n.", c);
            }
        }
        
    }
    
}


/*
import static java.lang.System.out;
import java.util.*;
public class AskName{
    static void print(String s){
        System.out.println(s);
    }
    public static void main(String[]args){
        Scanner obj = new Scanner(System.in);

    }
}


    static void printNumbers(){
        for(int i =10;i<41;i++){
            System.out.println(i);
        }
    }

 */