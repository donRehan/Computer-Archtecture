public class execute{

    public execute(){

    }

    public static void Execute(byte opCode, int A , int B)
    {
        switch(opCode)
        {
            case 0:
            add((byte)A, (byte)B);
            break;
            case 1:
            sub((byte)A, (byte)B);
            break;
            case 2:
            mul((byte)A, (byte)B);
            break;
            case 3:
            ldi(A, (byte)B);
            break;
            case 4:
            beqz((byte)A, (byte)B);
            break;
            case 5:
            and((byte)A, (byte)B);
            break;
            case 6:
            or((byte)A, (byte)B);
            break;
            case 7:
            jr((byte)A, (byte)B);
            break;
            case 8:
            slc((byte)A, (byte)B);
            break;
            case 9:
            src((byte)A, (byte)B);
            break;
            case 10:
            lb(A, (byte)B);
            break;
            case 11:
            sb(A, (byte)B);
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
            if(A+B > 255)
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

   }

   public static void add (byte A, byte B){
       byte temp1 = memory.Registers[A-1];
       System.out.print("Register R"+ A + " old Value: " + temp1);
       byte temp2 = memory.Registers[B-1];
       byte res = (byte) (temp1 + temp2); 
       memory.Registers[A-1] = res;
       System.out.println("Register R"+ A + " new Value: " + res);
   }

   public static void sub (byte A, byte B){
       byte temp1 = memory.Registers[A-1];
       System.out.print("Register R"+ A + " old Value: " + temp1);
       byte temp2 = memory.Registers[B-1];
       byte res =(byte)( temp1 - temp2); 
       memory.Registers[A-1] = res;
       System.out.println("Register R"+ A + " new Value: " + res);
   }

   public static void mul (byte A, byte B){
       byte temp1 = memory.Registers[A-1];
       System.out.print("Register R"+ A + " old Value: " + temp1);
       byte temp2 = memory.Registers[B-1];
       byte res =(byte) (temp1 * temp2); 
       memory.Registers[A-1] = res;
       System.out.println("Register R"+ A + " new Value: " + res);
   }

   public static void ldi (int A, byte B){
       System.out.print("Register R"+ A + " old Value: " + memory.Registers[A-1]);
       memory.Registers[A-1] = B;
       System.out.println("Register R"+ A + " new Value: " + B);
   }

   public static void beqz (byte A, byte B){
       byte temp1 = memory.Registers[A-1];
       System.out.print("PC old Value: " + memory.Registers[65]);
       if(temp1 == 0){
            memory.Registers[65]++;
            memory.Registers[65] += B;
       }
       System.out.println("PC new Value: " + memory.Registers[65]);
   }

   public static void and (byte A, byte B){
       byte temp1 = memory.Registers[A-1];
       System.out.print("Register R"+ A + " old Value: " + memory.Registers[A-1]);
       byte temp2 = memory.Registers[B-1];
       byte res = (byte) (temp1 & temp2); 
       memory.Registers[A-1] = res;
       System.out.println("Register R"+ A + " new Value: " + memory.Registers[A-1]);
   }

   public static void or (byte A, byte B){
       byte temp1 = memory.Registers[A-1];
       System.out.print("Register R"+ A + " old Value: " + memory.Registers[A-1]);
       byte temp2 = memory.Registers[B-1];
       byte res = (byte) (temp1 | temp2); 
       memory.Registers[A-1] = res;
       System.out.println("Register R"+ A + " new Value: " + memory.Registers[A-1]);
   }

   public static void jr (byte A, byte B){
       String temp1 = Integer.toBinaryString( (int) memory.Registers[A-1]);
       System.out.print("PC old Value: " + memory.Registers[65]);
       String temp2 = Integer.toBinaryString( (int) memory.Registers[B-1]);
       String temp3 = temp1 + temp2;
       byte decimal = (byte) Integer.parseInt(temp3,2);
       memory.Registers[65] = decimal;
       System.out.println("PC new Value: " + memory.Registers[65]);
   }

   public static void slc(byte A,byte B){
       byte temp =   memory.Registers[A-1];
       System.out.print("Register R"+ A + " old Value: " + memory.Registers[A-1]);
       byte res =  (byte) ((temp << B) | (temp >>> (8-B)));
       memory.Registers[A-1] = res;
       System.out.println("Register R"+ A + " new Value: " + memory.Registers[A-1]);
   }

    public static void src(byte A, byte B){
       byte temp = memory.Registers[A-1];
       System.out.print("Register R"+ A + " old Value: " + memory.Registers[A-1]);
       byte res =  (byte) ((temp >>> B) | (temp << (8-B)));
       memory.Registers[A-1] = res;
       System.out.println("Register R"+ A + " new Value: " + memory.Registers[A-1]);
   }

   public static void lb(int A, byte B){
       System.out.print("Register R"+ A + " old Value: " + memory.Registers[A-1]);
       memory.Registers[A-1] = memory.Dmemory[B];
       System.out.println("Register R"+ A + " new Value: " + memory.Registers[A-1]);
   }

   public static void sb(int A, byte B){
       System.out.print("memory location "+ B + " old Value: " + memory.Dmemory[B]);
        memory.Dmemory[B] = memory.Registers[A-1];
       System.out.println("memory location "+ B + " new Value: " + memory.Dmemory[B]);
   }



}