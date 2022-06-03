public class execute{

    public execute(){

    }

    public static void Execute(byte opCode, int val1 , int val2, int R1 , int R2, int immediate)
    {
        switch(opCode)
        {
            case 0:
            add(opCode, val1 , val2, R1 , R2, immediate);
            break;
            case 1:
            sub(opCode, val1 , val2, R1 , R2, immediate);
            break;
            case 2:
            mul(opCode, val1 , val2, R1 , R2, immediate);
            break;
            case 3:
            ldi(opCode, val1 , val2,R1 , R2, immediate);
            break;
            case 4:
            beqz(opCode, val1 , val2, R1 , R2, immediate);
            break;
            case 5:
            and(opCode, val1 , val2, R1 , R2, immediate);
            break;
            case 6:
            or(opCode, val1 , val2,R1 , R2, immediate);
            break;
            case 7:
            jr(opCode, val1 , val2, R1 , R2, immediate);
            break;
            case 8:
            slc(opCode, val1 , val2, R1 , R2, immediate);
            break;
            case 9:
            src(opCode, val1 , val2, R1 , R2, immediate);
            break;
            case 10:
            lb(opCode, val1 , val2, R1 , R2, immediate);
            break;
            case 11:
            sb(opCode, val1 , val2, R1 , R2,immediate);
            break;
        }


    }

   public static void sReg(int res, byte opCode, int A, int B)
   {

       String SREG = Integer.toBinaryString(memory.Registers[64]);
       if(SREG.length() != 8)
       {
           int tmp = SREG.length();
           for(int i = 0; i < tmp ; i++)
           {
            SREG = 0 + SREG;
           }
       }
       if(opCode == 0)
       {    
            int op1 = Byte.toUnsignedInt( (byte) A);
            int op2 = Byte.toUnsignedInt( (byte) B);

            int condition = op1 + op2;             
            int Case = condition & 0b00000000000000000000000100000000;

            if(Case == 512)
            {
            SREG = SREG.substring(0,3) + "1" + SREG.substring(4);
            }
            else
            {
            
            SREG = SREG.substring(0,3) + "0" + SREG.substring(4);
            }
            if(A >= 0 && B >= 0){
                if(A+B <0){
            SREG = SREG.substring(0,4) + "1" + SREG.substring(5);
                }
                else{
            SREG = SREG.substring(0,4) + "0" + SREG.substring(5);
                }
            }
            else{
                if(A+B >= 0){
            SREG = SREG.substring(0,4) + "1" + SREG.substring(5);
                }
            }
       }
       if(opCode == 1)
       {
        if(A >= 0 && B < 0)
        {
            if(res < 0)
            {          
               SREG = SREG.substring(0,4) + "1" + SREG.substring(5);
            }
            else {
               SREG = SREG.substring(0,4) + "0" + SREG.substring(5);
            }
        }
        else if (A < 0 && B >= 0)
        {
            if(res > 0)
            {          
               SREG = SREG.substring(0,4) + "1" + SREG.substring(5);
            }
            else {
               SREG = SREG.substring(0,4) + "0" + SREG.substring(5);
            }          
        }
       }
       if((opCode == 0) || (opCode == 1) || (opCode == 2) || opCode == 5 || (opCode == 6) || (opCode == 8) || (opCode == 9))
       {
           if(res < 0)
           {
            SREG = SREG.substring(0,5) + "1" + SREG.substring(6);
           }
           else{
            SREG = SREG.substring(0,5) + "0" + SREG.substring(6);
           }
           if(res == 0)
           {
            SREG = SREG.substring(0,7) + "1";
           }
           else
           {
            SREG = SREG.substring(0,7) + "0";
           }
       }
       if((opCode == 0) || (opCode == 1))
       {
          int n = Character.getNumericValue(SREG.charAt(5));
          int v = Character.getNumericValue(SREG.charAt(4));
          int s = n ^ v;
          SREG = SREG.substring(0,6) + s + SREG.substring(7);
       }

        byte result = (byte) Byte.parseByte(SREG,2);
        memory.Registers[64] = result;

   }

   public static void add(byte opCode, int val1 , int val2, int R1 , int R2, int immediate){
       System.out.print("Register R"+ R1 + " old Value: " + val1);
       int res1 =  (val1 + val2); 
       byte res = (byte) res1;
       memory.Registers[R1] = res;
       System.out.println("Register R"+ R1 + " new Value: " + res);
   }

   public static void sub (byte opCode, int val1 , int val2, int R1 , int R2, int immediate){
       System.out.print("Register R"+ R1 + " old Value: " + val1);
       byte res =(byte)( val1 - val2); 
       memory.Registers[R1] = res;
       System.out.println("Register R"+R1 + " new Value: " + res);
   }

   public static void mul (byte opCode, int val1 , int val2, int R1 , int R2, int immediate){
       System.out.print("Register R"+ R1 + " old Value: " + val1);
       byte res =(byte) (val1 * val2); 
       memory.Registers[R1] = res;
       System.out.println("Register R"+ R1 + " new Value: " + res);
   }

   public static void ldi (byte opCode, int val1 , int val2, int R1 , int R2, int immediate){
       System.out.print("Register R"+ R1 + " old Value: " + val1);
       memory.Registers[R1] = (byte) (immediate+1);
       System.out.println("Register R"+ R1 + " new Value: " + (immediate+1) );
   }

   public static void beqz (byte opCode, int val1 , int val2, int R1 , int R2, int immediate){
       System.out.print("PC old Value: " + memory.Registers[65]);
       if(val1 == 0){
            memory.Registers[65]++;
            memory.Registers[65] += (immediate+1);
       }
       System.out.println("PC new Value: " + memory.Registers[65]);
   }

   public static void and (byte opCode, int val1 , int val2, int R1 , int R2, int immediate){
       System.out.print("Register R"+ R1 + " old Value: " + val1);
       byte res = (byte) (val1 & val2); 
       memory.Registers[R1] = res;
       System.out.println("Register R"+ R1 + " new Value: " + res);
   }

   public static void or (byte opCode, int val1 , int val2, int R1 , int R2, int immediate){
       System.out.print("Register R"+ R1 + " old Value: " + val1);
       byte res = (byte) (val1 | val2); 
       memory.Registers[R1] = res;
       System.out.println("Register R"+ R1 + " new Value: " + res);
   }

   public static void jr (byte opCode, int val1 , int val2, int R1 , int R2, int immediate){
       String temp1 = Integer.toBinaryString(val1);
       System.out.print("PC old Value: " + memory.Registers[65]);
       String temp2 = Integer.toBinaryString(val2);
       String temp3 = temp1 + temp2;
       byte decimal = (byte) Byte.parseByte(temp3,2);
       memory.Registers[65] = decimal;
       System.out.println("PC new Value: " + memory.Registers[65]);
   }

   public static void slc(byte opCode, int val1 , int val2, int R1 , int R2, int immediate){
       byte temp =  (byte) val1;
       System.out.print("Register R"+ R1 + " old Value: " + val1);
       byte res =  (byte) ((temp << (immediate +1)) | (temp >>> (8-(immediate+1))));
       memory.Registers[R1] = res;
       System.out.println("Register R"+ R1 + " new Value: " + res);
   }

    public static void src(byte opCode, int val1 , int val2, int R1 , int R2, int immediate){
       byte temp = (byte) val1;
       System.out.print("Register R"+ R1 + " old Value: " + val1);
       byte res =  (byte) ((temp >>> immediate) | (temp << (8-immediate)));
       memory.Registers[R1] = res;
       System.out.println("Register R"+ R1 + " new Value: " + res);
   }
//modified
   public static void lb(byte opCode, int val1 , int val2, int R1 , int R2, int immediate){
       System.out.print("Register R"+ R1 + " old Value: " + val1);
       memory.Registers[R1] = memory.Dmemory[immediate];
       System.out.println("Register R"+ R1 + " new Value: " + memory.Dmemory[immediate]);
   }

   public static void sb(byte opCode, int val1 , int val2, int R1 , int R2, int immediate){
       System.out.print("memory location "+ immediate + " old Value: " + memory.Dmemory[immediate]);
        memory.Dmemory[immediate] = (byte) val1;
       System.out.println("memory location "+ immediate + " new Value: " + val1);
   }



}
