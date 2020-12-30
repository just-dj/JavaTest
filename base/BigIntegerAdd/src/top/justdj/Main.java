package top.justdj;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        System.out.println(BigDecimal.valueOf(1234, 2));
        System.out.println(BigDecimal.valueOf(1, 2));

        while(true){
            Scanner in = new Scanner(System.in);
            System.out.println("---------------------------------------------------------");
            System.out.printf("输入第一个数字：");
            String str1 = in.next();
            System.out.printf("输入第二个数字：");
            String str2 = in.next();
            System.out.println("两个数字和为：" + bigAdd(str1,str2));;
        }
        
    }
    
    public static String bigAdd(String a,String b){
        if(a==null || b == null){
            throw new NullPointerException();
        }
        final String REGEX = "^(0|[1-9][0-9]*|-[1-9][0-9]*)$";
        if (!(Pattern.matches(REGEX,a) && Pattern.matches(REGEX,b)))
            throw  new InputMismatchException("输入不合法");
        
        boolean af = true,bf =true;
        if(a.charAt(0)=='-'){
            af = false;
            a = a.substring(1);
        }
        if(b.charAt(0)=='-'){
            bf = false;
            b = b.substring(1);
        }
        int maxsize = a.length()>b.length()?a.length()+2:b.length()+2;
        int[] m = new int[maxsize];
        int[] n = new int[maxsize];
        m[0] = af?0:9;
        n[0] = bf?0:9;
        for (int i = 0; i < a.length(); i++) {
            m[maxsize-a.length()+i] = Integer.parseInt(""+a.charAt(i));
        }
        for (int i = 0; i < b.length(); i++) {
            n[maxsize-b.length()+i] = Integer.parseInt(""+b.charAt(i));
        }
        int[] result = add(buma(m),buma(n));
        return intToString(buma(result));
    }
    public static String intToString(int[] a){
        StringBuffer s = new StringBuffer();
        if(a[0]==9){ s.append('-'); }
        int tag = 0;
        for (int i = 1; i < a.length; i++) {
            if(a[i]==0){ tag++; }
            else{ break; }
        }
        for (int j = tag+1; j < a.length; j++) {
            s.append(a[j]);
        }
        if(s.length()==0){
            return "0";
        }
        return s.toString();
    }
    public static int[] buma(int[] a) {
        if (a[0] == 9) {
            for (int i = 1; i < a.length; i++) {
                a[i] = 9 - a[i];
            }
            int[] tmp  = new int[a.length];
            tmp[a.length-1] = 1;
            return add(a, tmp);
        }else{
            return a;
        }
    }
    private static int[] add(int[] a, int[] b) {
        if(a.length<b.length){
            return null;
        }
        int size = a.length;
        int t = 0;//进位
        for (int i = 0; i < size; i++) {
            int k = a[size - i - 1] + b[size - i - 1];
            k+= t;//加进位
            if (k >= 10) {
                t = 1;
            }else{
                t = 0;
            }
            k %= 10;
            a[size - i - 1] = k;
        }
        return a;
    }
}
