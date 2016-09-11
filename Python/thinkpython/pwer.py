"""
	a is a power of b if it is divisible by b and a/b is a power of b
"""
def is_power(a,b):
	if a % b != 0:
		return False
	if a/b == 1:
		return True
	else:
		return is_power(a/b,b)

print is_power(125,5)