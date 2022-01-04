package Contest;

public class BitOper {
    public static void main(String[] args) {
        int a = 11;//1011
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(-12));
        System.out.println(~a);//  Complement inverting bit and adding 1. Thus 01011 --> 10100 + 00001 = 10101 // 11100
        System.out.println(Integer.toBinaryString(~a));
        System.out.println((a & (~a)));
        System.out.println(a+(a & (~a)));
        System.out.println(a-(a & (~a)));
    }
}
