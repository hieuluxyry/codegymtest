let arr = [];
function findAvg(arr){
    let length = arr.length; 
    sum = 0;
    arr.forEach((value)=>{
        sum += value;
    })
    return sum/length;
}
console.log(findAvg(arr));