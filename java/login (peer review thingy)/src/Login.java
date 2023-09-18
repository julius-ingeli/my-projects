import java.util.Scanner;

public class Login {

    //Function to for easy string printing
    //I know, I am THAT lazy
    public static void print(String s){
        System.out.println(s);
    }

    public static String generateEmail(String fname, String lname, String domain){ //email generating method
        String email; //initializing the return var
        fname = fname.toLowerCase(); //lowercasing the first name
        lname = lname.toLowerCase(); //lowercasing the last name    
        email = fname + "." + lname + "@" + domain; //result string
        return email; //returning the resulting email
    }
    public static String generateUsername(String fname, String lname){
        String usrname, pt1, pt2; //initializing return var, part 1 and part 2 of the username 
        fname = fname.toLowerCase(); //lower casing the first name
        lname = lname.toLowerCase(); //lower casing the last name
        if(fname.length()>4){ //if first name is longer than 4 
            pt1 = fname.substring(0, 4); //using substrings to extract the first 4 characters
        }
        else{   //if it is not long enough, just take the whole name
            pt1 = fname;    //I know it is not necessary, but I did it anyways
        }
        if(lname.length()>4){   //same condition for the last name
            pt2 = lname.substring(lname.length()-4, lname.length()); //same thing, but other way around
                                                                     //using lname.length() to get last name length, first argument is length subtracted by four and then using the second argument
                                                                     //is the total name length
        }
        else{   //same as in the first name
            pt2 = lname;
        }
        usrname = pt1+pt2;  //combing the result
        return usrname; //returning the resulting username
    }

    public static void main(String[] args) throws Exception { //declaring main function
        String fname, lname, domain, email, usrname; //initializing variables
        Scanner obj = new Scanner(System.in); //initializing a scanner object
        print("Enter first name:");
        fname = obj.nextLine(); //inputting the first name
        print("Enter last name: ");
        lname = obj.nextLine(); //inputting the last name
        print("Enter domain:");
        domain = obj.nextLine(); //inputting the domain name
        if(fname.length() < 1 || lname.length() < 1){ //checking if user actually entered anything into the first/last name prompts
            print("Error! First or Last name is missing!\nExiting program...");
            obj.close(); //closing the Scanner object
            return; //exiting the program
        }
        email = generateEmail(fname, lname, domain); //calling generateEmail method
        usrname = generateUsername(fname, lname);    //calling generateUsername method
        print(email); //printing the email
        print(usrname); //printing the username
        obj.close(); //closing the Scanner object
    }
}
