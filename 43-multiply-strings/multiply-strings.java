class Solution {
    /**
    P:
    - given strings, which are integers rep. as string
        - num1
        - num2
    - we want to return a string which is their product
    E:
    - examples make sense
    D:  
    - none maybe
    A:
    - a maybe brute force idea is to use the index in the string,
    as to know how many 0's we need to add to that number when considering
    that digit for multiplication
    - multiplication would then just be iterating through both strings
    and multiplying every digit in num1, and num2, then sum it all together.
    C:
     */
    public String multiply(String num1, String num2) {
        // when one number is 0, ofc. result is 0
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        // we know that max res length-wise is always the length of the 
        // two numbers added together
        int[] res = new int[num1.length() + num2.length()];

        // reverse the strings, for easier processing 
        // (iterating from lowest index, or lowest place, to highest)
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();

        // iterate through both string nums
        for (int i1 = 0; i1 < num1.length(); i1++) {
            for (int i2 = 0; i2 < num2.length(); i2++) {

                // determine difference between char at a certain index to 0, and multiply the values
                int digit = (num1.charAt(i1) - '0') * (num2.charAt(i2) - '0');
                // add the two together 
                res[i1 + i2] += digit;

                // in the event of a carry over, account for it!
                res[i1 + i2 + 1] += res[i1 + i2] / 10;
                res[i1 + i2] %= 10;
            }
        }

        // build result
        StringBuilder result = new StringBuilder();
        int i = res.length - 1;

        // iterate in reverse the res array and skip leading 0's
        while (i >= 0 && res[i] == 0) {
            i--;
        }

        // afterwards, build string up
        while (i >= 0) {
            result.append(res[i--]);
        }
        return result.toString();
    }
}