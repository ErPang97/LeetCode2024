class Solution {
    public int romanToInt(String s) {

        HashMap<Character, Integer> romanNumKey = new HashMap<>();
        romanNumKey.put('I', 1);
        romanNumKey.put('V', 5);
        romanNumKey.put('X', 10);
        romanNumKey.put('L', 50);
        romanNumKey.put('C', 100);
        romanNumKey.put('D', 500);
        romanNumKey.put('M', 1000);

        int val = 0;
        char[] sArr = s.toCharArray();
        for(int i = 0; i < sArr.length; i++){
            char c = sArr[i];
            val += romanNumKey.get(c);
            if(i > 0){

                if((c == 'V' || c == 'X') && sArr[i-1] == 'I'){
                    val -= 2;
                } else if((c == 'L' || c == 'C') && sArr[i-1] == 'X'){
                    val -= 20;
                } else if((c == 'D' || c == 'M') && sArr[i-1] == 'C'){
                    val -= 200;
                }

            }
        }

        return val;
    }
}