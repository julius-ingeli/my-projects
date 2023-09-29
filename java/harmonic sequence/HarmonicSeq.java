public class HarmonicSeq {
    public static String seq(int n){
        double sum = 0, divisor = 1,quotient = 1;
        String resultStr;
        while(sum<=n){
            Double result = quotient/divisor;
            sum+=result;
            divisor++;
            //System.out.println(sum + " "+ divisor);
        }
        divisor--;
        resultStr = "Divisor: " + divisor + " Sum : " + sum;
        return resultStr;
    }
    public static void main(String[] args) {
        int a;
        if (args.length>0){
            try{
                a = Integer.parseInt(args[0]);
                String res = seq(a);
                System.out.print(res);
            } catch(NumberFormatException e){
                System.err.println("Argument "+args[0]+" must be numeric.");
                System.exit(1);
            }
        }
        else{
            System.err.println("Error: No arguments given!");
            System.exit(1);
        }

        
    }
}
