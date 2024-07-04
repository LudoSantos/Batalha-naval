public class Conversor 
{
    int Numero;
    
    int converter ( char letra )
    {
       switch ( letra ) 
       {
        case 'A':
         Numero = 0;
        break;
        case 'B':
         Numero = 1;
        break;
        case 'C':
         Numero = 2;
        break;
        case 'D':
         Numero = 3;
        break;
        case 'E':
         Numero = 4;
        break;
        case 'F':
         Numero = 5;
        break; 
        case 'G':
         Numero = 6;
        break;
        case 'H':
         Numero = 7;
        break; 
        case 'I':
         Numero = 8; 
        break;
        case 'J':
         Numero = 9;
        break; 
        case 'K':
         Numero = 10;
        break; 
        case 'L':
         Numero = 11;
        break; 
        case 'M':
         Numero = 12;
        break; 
        case 'N':
         Numero = 13; 
        break;     
        default:
         Numero = -1;
        break;
       } 
        return Numero;
    }
}
