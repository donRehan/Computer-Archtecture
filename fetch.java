public class fetch{

public fetch()
{

}

public static short Fetch()
{
        short temp =  memory.Imemory[memory.Registers[65]];
        memory.Registers[65]++;
        return temp;
}

}