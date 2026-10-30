class Solution:
    """
    P:
    - Given:
        - temperatures -> of list of int's
            - represents the daily temperatures
    - Want:
        - return answer - a list[int]
            - answer[i] is the number of days 
            you have to wait after the i-th day to
            get a warmer temperature
            - if there is no future day where this is possible
                - answer[i] = 0
    E:
    - The problem isn't too difficult to understand, the examples are clear
        (high_temp (j) - low_temp_index (i) = # days
        and i < j)
    D:
    - come back after thinking about approach, not sure if any DS is too necessary atm
    - a Stack?
    A:
    - the most obvious choice would be a Brute Force algorithm:
        - result = [0] * len(temperatures)
        - for i in [0, len(temperatures) exclusive]:  
            - low_temp = temperature at i
            - for j in [i, len(temperatures) exclusive]:
                - high_temp = temperature at j
                - if high_temp > low_temp:
                    - result[i] = j - i
                    - break
        - return result
    - this isn't too difficult but it be O(n**2) worse case time complexity,
    how do we do better?
    - could we use a DS like a stack? What if we stored in said Stack,
    (temperature, index) tuples?
        - the principle idea would be that at every index i , we'd check the
        top of the stack to see if the temperature at i is greater than the item at
        the top of the stack. If so pop that item, and store in the result list,
        at the index of the item that was removed, the difference between i and that 
        last item
    C:  
    """
    def dailyTemperatures(self, temperatures: list[int]) -> list[int]:
        result = [0] * len(temperatures)
        stack = [] # stores (index, temperature) tuples
        for i in range(len(temperatures)): # last item always 0 so can ignore
            current = temperatures[i]
            while len(stack) != 0: # keep popping off items until the top no longer less
                top = stack.pop()
                if current > top[1]:
                    result[top[0]] = i - top[0]
                else:
                    stack.append(top) # re-add to top if not 
                    break
            stack.append((i, current))
        return result


        return result
