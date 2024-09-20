class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // P:
            /**
            - n = piles of bananas (piles.length)
            - guards return in h hours
            - Koko decides k, her banana eating speed per hour
            - at each hour, she chooses some pile to eat k bananas from
            - or if less than k, the whole pile
            - wants to finish all bananas before guard returns
            - return min. k s.t. that all bananas are eaten within h hours
            - ** piles.length always at most h **
            --- OBS 1: anytime h = k, the min k is the 
                max pile[i]
            --- OBS 2: pile order doesn't matter, so 
                the option of sorting the pile makes sense
            --- OBS 3: if h == total bananas, then for sure, the min k is 1
                and any value greater for h, guarantees this as well
             */
        // E:
        // D:
        // A:
            /**
            - binary search over solution space (the possible values of k):
            which is 1 <= k <= maxValue
            - [1, ..., maxValue]
            - k = midpoint
            - validate k by calculating Math.ceil(sum(piles[i]/k))
            - if k is valid at midpoint, we can check the leftSpace 
            by setting right = k, and if not, set left = k + 1
            - return the k that is valid
             */
        // C:

        int n = piles.length;
        int totalBananas = 0;
        int largestPile = 0;
        for(int i = 0; i < piles.length; i++){
            totalBananas += piles[i];
            if(piles[i] > largestPile){
                largestPile = piles[i];
            }
        }

        int k = 0;

        int left = 0;
        int right = largestPile;
        while(left <= right) {
            int mid = (left+right)/2;
            int hoursNeeded = 0;
            for(int i = 0; i < piles.length; i++) {
                hoursNeeded += Math.ceil((double)piles[i]/mid);
            }
            if(hoursNeeded <= h) { 
                // this works so check lower half
                right = mid - 1; 
                k = mid;
            } else {
                left = mid + 1;
            }
        }

        return k;
    }
}