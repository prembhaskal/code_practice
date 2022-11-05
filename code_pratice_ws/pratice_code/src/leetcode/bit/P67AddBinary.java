package leetcode.bit;

public class P67AddBinary {

    public static void main(String[] args) {
        var sol = new P67AddBinary();
        var ans = sol.addBinary("11", "1");
        System.out.printf("output:%s\n", ans);
    }

    public String addBinary(String a, String b) {
        var ar = a.toCharArray();
        var br = b.toCharArray();

        StringBuilder sb = new StringBuilder();
        var ai = ar.length - 1;
        var bi = br.length - 1;
        int carry = 0;
        for (;ai >= 0 || bi >=0;ai--, bi--) {
            char ac, bc;
            ac = '0';
            bc = '0';
            if (ai >= 0) {
                ac = ar[ai];
            }
            if (bi >= 0) {
                bc = br[bi];
            }
            int sum = 0;
            sum = sum + ac - '0';
            sum = sum + bc - '0';
            sum = sum + carry;
            sb.append((char)(sum%2+'0'));
            carry = sum/2;
        }

        if (carry > 0) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }


}
