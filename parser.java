import java.io.*;

public class parser{

   //Register 64 Statuts register
   //Register 65 PC


    public static int iCounter = 0;

    public static short signedtoUnsign(String number)
    {
      String result = "";
      short out = 0;
      if(number.charAt(0) == '1'){
        //negative number
        for(int i = 0 ; i < number.length() ; i++)
        {
          if(number.charAt(i) == '1')
          {
            result = result + '0';
          }
          else{
            result = result + '1';
          }
          out = Short.parseShort(result,2);
          out *= -1;
          out--;
        }}
      else{
        //positive
          out = Short.parseShort(number,2);
      }
      return out;
    }

public parser(){

}

   public static String getOpcode(String Opcode){

    switch(Opcode){
        case "ADD":
            return "0000";
        case "SUB":
           return "0001";
        case "MUL":
           return "0010";
         case "LDI":
           return "0011";          
         case "BEQZ":
           return "0100";          
         case "AND":
           return "0101";
         case "OR":
           return "0110";
          case "JR":
           return "0111";
          case "SLC":
           return "1000";          
         case "SRC":
           return "1001";          
         case "LB":
           return "1010";
         case "SB":
           return "1011";
    }

    return null;
   } 

   public static String getRegister(String regName){
   int temp =  Integer.parseInt(regName.substring(1)) -1;
   String tmp = Integer.toBinaryString(temp);
   
   String out = "";

   if(temp == 0 || temp == 1)
   {
    out = "00000" + tmp;
   }
   else if(temp > 1 && temp < 4)
   {
    out = "0000" + tmp;
   }
   else if(temp > 3 && temp < 9)
   {
    out = "000" + tmp;
   }
   else if(temp > 8 && temp < 17)
   {
    out = "00" + tmp;
   }
   else if(temp > 16 && temp < 33)
   {
    out = "0" + tmp;
   }
   else if(temp > 32)
   {
    out =  tmp;
   }
   return out;
   } 

   public static String getPadding(String regName){
    int temp =  Integer.parseInt(regName) -1;
    String tmp = Integer.toBinaryString(temp);
    
    String out = "";
 
    if(temp == 0 || temp == 1)
    {
     out = "00000" + tmp;
    }
    else if(temp > 1 && temp < 4)
    {
     out = "0000" + tmp;
    }
    else if(temp > 3 && temp < 9)
    {
     out = "000" + tmp;
    }
    else if(temp > 8 && temp < 17)
    {
     out = "00" + tmp;
    }
    else if(temp > 16 && temp < 33)
    {
     out = "0" + tmp;
    }
    else if(temp > 32)
    {
     out =  tmp;
    }
    return out;
    } 
 

    public static void parse(){
    //Universal path ==> Insert the path here
    String path = "program.txt"; 
    try{
    String line = "";
    FileReader fr = new FileReader(path);
    BufferedReader br = new BufferedReader(fr);
    while((line = br.readLine()) != null){
        String[] currentLine = line.split(" ");
        String result = "";
        for(int i = 0; i< currentLine.length ; i++)
        {
           if(i==0){
               result = result + getOpcode(currentLine[i]);
           } 
           if(i==1){
               result = result + getRegister(currentLine[i]);
           } 
           if(i==2){
            try{
                result = result + getPadding(currentLine[i]);
            }
            catch(Exception e){
                result = result + getRegister(currentLine[i]);
            }
               
           } 
        }

        short res = signedtoUnsign(result);

        memory.Imemory[iCounter] = res;
        iCounter++;

    }

    br.close();

    }
    catch(IOException fr){
        System.out.println("File not found, Please check the path again");
    }
    }


    public static void main(String[] args)
    {
        memory trash = new memory();
        parser test = new parser();
        parse();
    }
}