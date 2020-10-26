public Logger<Integer> add(Logger<Integer> a, int b) {
    return a.map(x -> x + b); 
}

public Logger<Integer> sum(int n) {
    if (n==0) {
        return Logger.make(0);
    } else {
        return add(sum(n-1),n);
    }
}

public Logger<Integer> f(int n) {
    if (n == 1) {
        return Logger.make(1);
    } else if (n % 2 == 0) {
        return Logger.make(n).map(x -> x/2).flatMap(x -> f(x));
    } else {
        return Logger.make(n).map(x -> 3*x).map(x -> x+1).flatMap(x -> f(x));
    }
}
