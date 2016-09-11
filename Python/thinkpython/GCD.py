"""
	the greatest common divisor (GCD) of a and b is the largest number that 
	divides both of them with no remainder. One way to find the GCD of two 
	number is based on the observation that if r is the remanider wheen a is 
	divided by b, then GCD(a,b) = GCD(b,r). As a base case, we can use GCD(a,0)= a
"""
def GCD(a,b):
	if a % b == 0:
		return b
	else:
		return GCD(b,a%b)
print GCD(12,90)
print GCD(12,60)