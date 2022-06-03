public class runner{

    //pipeline registers
    static byte jtfetedpc;
    static short jtfetinst;
    static int jtfetOpcode;
    static int jtfetR1;
    static int jtfetR2;
    static int jtfetimmediate;
    static byte jtfetR1Val;
    static byte jtfetR2Val;
    static int max;
    static int oldopcode;
    static int oldR1;
    static int oldR2;
    static int oldimmediate;
    static byte oldR1Val;
    static byte oldR2Val;
    static short oldinst;
    static byte oldpc;



public runner()
{

}


public static void main(String[] args)
{

    //insert program in parser class
    memory Memory = new memory();
    parser Parser = new parser();
    fetch Fetcher = new fetch();
    decode Decoder = new decode();
    execute Executer = new execute();
    runner Runner = new runner();
    parser.parse();
    int cycle = 1;
    byte pc = memory.Registers[65];
    max = parser.iCounter;
    max += 2; //for the last two rows in instructions 
/*
    memory.Dmemory[1] = 2;
    memory.Dmemory[4] = 10;
  */  while(pc < max)
    {
    System.out.println("Clock cycle = "+ cycle);


    System.out.println("pc = "+ pc);

   jtfetinst = fetch.Fetch();
   jtfetedpc = (byte)  (pc) ;

    System.out.println("Fetch instruction number = "+ (pc ));

    if( cycle >= 2){
    decode.Decode(oldinst);
    jtfetOpcode = decode.opcode;
    jtfetR1 = decode.R1;
    jtfetR2 = decode.R2;
    jtfetimmediate = decode.immediate;
    jtfetR1Val = decode.R1Val;
    jtfetR2Val = decode.R2Val;
    System.out.println("Decode instruction number = "+  (pc - 1));
    }

    if(cycle >= 3)
    {
    System.out.println("Execute instruction number = "+  (pc - 2));
    execute.Execute( (byte) oldopcode, (byte) oldR1Val, (byte) oldR2Val, oldR1, oldR2, oldimmediate);
    System.out.println("Status Register = "+  (memory.Registers[64]));
    }    

    
    System.out.println("==================================================================");

    oldinst = jtfetinst; oldpc = jtfetedpc;
    oldopcode = jtfetOpcode; oldR1 = jtfetR1; oldR2 = jtfetR2;
    oldimmediate = jtfetimmediate; oldR1Val = jtfetR1Val; oldR2Val = jtfetR2Val;

    pc = memory.Registers[65];
    cycle++;
    }

    //printing all the registers values
    System.out.println("Registers Values");
    System.out.print("[");
    for(int i = 0; i < memory.Registers.length ; i++)
    {
        System.out.print(memory.Registers[i]+ " , ");
    }
    System.out.println("]");

    System.out.println("==================================================================");

    System.out.println("Instruction memory Values");
    System.out.print("[");
    for(int i = 0; i < memory.Imemory.length ; i++)
    {
        System.out.print(memory.Imemory[i]+ " , ");
    }
    System.out.println("]");
    
    
    System.out.println("==================================================================");

    System.out.println("Data memory Values");
    System.out.print("[");
    for(int i = 0; i < memory.Dmemory.length ; i++)
    {
        System.out.print(memory.Dmemory[i]+ " , ");
    }
    System.out.println("]");
}

}