public class decode {   
   

   public decode(){

   }

   public static void Decode(short instrct){

    int opcode = (0b1111000000000000 & instrct) >>> 12;
    int R1 = (0b0000111111000000 & instrct) >>> 6;
    int R2 = (0b0000000000111111 & instrct) ;
    int immediate = (0b0000000000111111 & instrct) ;
    byte R1Val = memory.Registers[R1]; 
    byte R2Val = memory.Registers[R2]; 

   } 

}
