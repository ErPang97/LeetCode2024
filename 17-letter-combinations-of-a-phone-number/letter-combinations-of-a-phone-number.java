class Solution {
    public List<String> letterCombinations(String digits) {
        // P
        /**
         * Given a string which contains ints 2-9 inclusive, 
         * we want to find all the possible letter combos 
         * this number can be represent, using the fact that:
         * if its 2: a,b or c
         *        3: d,e, f
         *        4: g,h,i
         *        5: j, k, l
         *        6: m, n, o
         *        7: p, q,r ,s
         *        8: t, u, v
         *        9: w, x, y, z 
         */
        // E:
        /**
         * Pretty straightforward with the first example, 2, 3:
         * Since we start with 2, our letter combos must start with:
         * a, b or c, and since it ends with 3, we must end with: d, e, or f
         * giving the following:
         * "ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"
         */
        // D:
        /**
         * Arrays, don't think need anything else, though maybe a map isn't 
         * a bad idea either, to map the nums to possible values
         */
        // A:
        /**
         * 
         * init an ArrayList of Strings repping possible combos
         * populate hashMap Integer to String of possible letters
         * depthFirstComboGeneration(digits, combos, 0, currentCobmo)
         *
         * return combos;
         * public void depthFirstComboGeneration(digits, combos, int index, String currentCombo):
         *     if (index >= len(digits)):
         *         add currentCombo to combos list
         *         return;
         *     currentNumber == digits[i]
         *     String[] currentLetters = hashMap.get(currentNumber).toCharArray()
         *     for i in range(len(currentLetters)):
         *         copyCurrentCombo = currentCombo + currentLetter[i]
         *         depthFirstComboGeneration(digits, combos, index+1, copyCurrentCombo)
         * 
         */
        // C:

        List<String> combos = new ArrayList<>();
        if (digits.length() == 0) return combos;

        HashMap<String, String> digitToLetters = new HashMap<>();
        digitToLetters.put("2", "abc");
        digitToLetters.put("3", "def");
        digitToLetters.put("4", "ghi");
        digitToLetters.put("5", "jkl");
        digitToLetters.put("6", "mno");
        digitToLetters.put("7", "pqrs");
        digitToLetters.put("8", "tuv");
        digitToLetters.put("9", "wxyz");

        depthFirstComboGeneration(digits, combos, 0, "", digitToLetters);
        return combos;
    }

    public void depthFirstComboGeneration(String digits, List<String> combos, int index, String currentCombo, HashMap<String, String> digitToLetters){
        if (index >= digits.length()){
            combos.add(currentCombo);
            return;
        }
        String currentNumber = ""+digits.charAt(index);
        String currentLetters = digitToLetters.get(currentNumber);
        for (char c: currentLetters.toCharArray()) {
            String copyCurrent = currentCombo + c;
            depthFirstComboGeneration(digits, combos, index+1, copyCurrent, digitToLetters);
        }

    }
}