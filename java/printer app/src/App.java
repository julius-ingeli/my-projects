import java.util.*;
import static java.lang.System.out;
import java.lang.Character;

public class App {
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

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        //printerApp();
        //comparerApp();
        //rng(550, 0, 6, random);
        //calculator();
        //lucky7(random);
        //ageApp(args);
        nameHangman(random);

       
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

    }
}
 */