import math

def is_generator(p, g):
    p_minus_one = p - 1
    prime_factors = prime_factors_list(p_minus_one)

    for q in prime_factors:
        r = p_minus_one // q
        gr = pow(g, r, p)
        if gr == 1:
            return False

    return True

def prime_factors_list(n):
    factors = []
    while n % 2 == 0:
        factors.append(2)
        n //= 2
    for i in range(3, int(math.sqrt(n))+1, 2):
        while n % i == 0:
            factors.append(i)
            n //= i
    if n > 2:
        factors.append(n)
    return factors

myp = 1000000007

for i in range(2,1000):
	if is_generator(myp,i):
		print(i)


