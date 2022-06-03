public class decode {   
   
    static int opcode;
    static int R1;
    static int R2;
    static int immediate;
    static byte R1Val;
    static byte R2Val;



   public decode(){

   }

   public static void Decode(short instrct){

    opcode = (0b1111000000000000 & instrct) >>> 12;
    R1 = (0b0000111111000000 & instrct) >>> 6;
    R2 = (0b0000000000111111 & instrct) ;
    immediate = (0b0000000000111111 & instrct) ;
    R1Val = memory.Registers[R1]; 
    R2Val = memory.Registers[R2]; 
    System.out.println("Debugging ..");
   } 

}
