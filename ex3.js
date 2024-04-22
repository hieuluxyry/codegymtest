let sumOfListPrime = (x) => {
  let sum = 0;
  if(x < 2) return sum;
  sum = 2;
  for(let i = 3; i <= x; i += 2){
    if(isPrime(i)) sum += i;
  }
  return sum;
}

function isPrime(n){
  for(let i = 2; i <= Math.sqrt(n); i++){
    if(n % i === 0) return false;
  }
  return n > 1;
}
console.log(sumOfListPrime(17));