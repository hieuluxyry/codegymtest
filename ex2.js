const insertNumberToArray = (arr, value, index) => {
  if(index < 0 || index > arr.length) return arr;
  else{
    let newArr = [...arr.slice(0, index), value, ...arr.slice(index)];
    return newArr;
  }
}
console.log(insertNumberToArray([1, 2, 3, 4, 5, 7, 8], 110, 5));