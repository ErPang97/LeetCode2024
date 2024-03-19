/**
 * @param {number[]} arr
 * @param {Function} fn
 * @return {number[]}
 */
var map = function(arr, fn) {
    let newArray = [];
    for(var i = 0; i < arr.length; i++){
        newArray.push(fn(arr[i], i));
    }
    return newArray;
};