class Solution {
    /**
    P:
        - given a string, we're just trying to find how many possible ways
        we can decode said string, given our mapping of
        "1" -> "A"
        "2" -> "B"
        ...
        "25" -> "Y"
        "26" -> "Z"
    E:

    D:

    A:
        - lets see if we can break this into a DP problem:
        - given a substring that ends onf the first index 1, the simplest case:
            - we know that there is only one possibility 
            - or it could be invalid, if it is equal to 0
        - now onto the next substring that ends on index 2:
            - we know that there could be 2 ways of valid encodings:
                - the encoding of the substring ending on index 1, and the substring that starts on 1 and ends on 2
                - or the encoding of the whole substring itself
            - or again it could be 0 if there is a 0, and the 2 digit
            encoding isn't valid, ie: 30, 40, 50, 01-09 etc.
        - now for given a substring that starts from the beginning and ends on index k of the full string
        the maximum
            - the number of encodings that are valid up to the substring k:
                given 
                


    C:
     */
    public int numDecodings(String s) {

        // map to store the max number of possible ways to decode
        // given a substring that starts at the end index, and goes
        // to index i
        Map<Integer, Integer> dp = new HashMap<>();

        // map the length of the string to 1, as an empty string
        // should return 1, as a base case
        dp.put(s.length(), 1);

        // loop through the string in reverse order
        for (int i = s.length()-1; i >= 0; i--) {
            // a decodable string cant be 0
            if (s.charAt(i) == '0') { 
                dp.put(i, 0);
            }
            else { 
            // it can however, be any digit 1-9, if single digit
                dp.put(i, dp.get(i+1));
            }   

            // checking the now double digit condition
            // if i + 1 is still valid, then we check if the first 
            // char i is 1, if so, then any number for the second digit works
            // however, if it starts with 2, then the following digit must be
            // 0123456 as 27 and up is not a valid encoding
            if (i + 1 < s.length() && (s.charAt(i) == '1'
            || s.charAt(i) == '2' && "0123456".contains(""+s.charAt(i+1)))) {
                dp.put(i, dp.get(i) + dp.get(i+2));
            }
        }
        return dp.get(0);
    }
}