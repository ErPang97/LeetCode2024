/**
 * @param {number[]} arr
 * @param {Function} fn
 * @return {number[]}
 */
var filter = function(arr, fn) {
    var filtered = []; // var has scope across the function body
                       // while let is only within the immediate opening/closing blocks
    for (var i = 0; i < arr.length; i++) {
        if (fn(arr[i], i))
            filtered.push(arr[i])
    }
    return filtered;
};