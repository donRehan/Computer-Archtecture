public class memory{

public static short[] Imemory;
public static byte[] Dmemory;
public static byte[] Registers;

public memory(){
    Imemory = new short[1024];
    Dmemory = new byte[2048];
    Registers = new byte[66];
    //Setting the pc initially to 0
    Registers[65] = 0;
}

}