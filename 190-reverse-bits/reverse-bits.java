public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        // P: we're given an unsigned int, and we're reversing its bits
        // and returning the resulting integer after reversing the bits
        // E: In given examples
        // D: NA
        // A: idea - sucessively cut the unsigned int in half
        // until we reach a 2-bit int, then return the reverse of the 2-bit 
        // (recursion)
        String binaryRep = Integer.toBinaryString(n);
        StringBuilder toThirtyTwoBits = new StringBuilder();
        while(toThirtyTwoBits.length() < 32 - binaryRep.length()){
            toThirtyTwoBits.append("0");
        }
        toThirtyTwoBits.append(binaryRep);
        binaryRep = toThirtyTwoBits.toString();
        String reverseString = reverseString(binaryRep);
        return Integer.parseUnsignedInt(reverseString, 2);
    }

    public String reverseString(String string){
        String binaryRep = string;
        int midPoint = binaryRep.length()/2;
        StringBuilder reverseString = new StringBuilder();
        String left = binaryRep.substring(0, midPoint);
        String right = binaryRep.substring(midPoint, binaryRep.length());
        if(binaryRep.length() == 1){
            reverseString.append(binaryRep);
        } else if(binaryRep.length() == 2){
            reverseString.append(right);
            reverseString.append(left);
        } else{
            String leftBinary = 
                reverseString(left);
            String rightBinary = 
                reverseString(right);
            reverseString.append(rightBinary);
            reverseString.append(leftBinary);
        }
        return reverseString.toString();
    }

}