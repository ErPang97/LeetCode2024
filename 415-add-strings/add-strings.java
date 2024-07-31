class Solution {
    public String addStrings(String num1, String num2) {
        // P:
        // E: 11 + 123 = (0 + 1)*100 + (1+2)*10 + (3+1)*1
        // D: none
        // A: 
        // 1. iterate over both num1 and num2 from length to 0 
        // a carry over value carry = n1 digit + n2 digit + prev_carry / 10 (integer division)
        // meaning from ones place, to tens place, etc. 
        // while (i >= 0 || j >= 0) 
        // idea here is that once we stop adding from one, doesn't mean can't stop 
        // using other string
        // n1 = num1.charAt(i) - '0', gives distance between the number chars :O
        // again conditionals, so that n1, n2 only used when numbers still needed from either num1 or num2, otherwise set to 0
        // append to string builder sum%10
        // decrement i and j
        // after while loop, append carry if not 0, and return a reverse to 
        // the StringBuilder



        int maxLength = Math.max(num1.length(), num2.length());
        while(num1.length() != maxLength){
            num1 = "0" + num1;
        }
        while(num2.length() != maxLength){
            num2 = "0" + num2;
        }
        
        int carry = 0;
        StringBuilder sb = new StringBuilder();

        for(int i = maxLength - 1; i >= 0; i--){
            int power = maxLength -1 - i;
            int sum  = (
                (num1.charAt(i) - '0')
                + (num2.charAt(i) - '0')
                ) + carry;
            carry = sum / 10;
            sb.append(sum  % 10);

        }

        if(carry != 0){
            sb.append(carry);
        }

        return sb.reverse().toString();

    }
}