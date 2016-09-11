import math
#a =raw_input('square root of what?\n')

def new_ton(a):
	a = float(a)
	x = a / 2
	i = 0
	y=0
	while i < 10:
		y = (x + a/x) /2
		x = y
		i +=1
	return y
def using_math(a):
	return math.sqrt(a)

def print_out():
	print 'number \t New Ton \t Math Lib \t Difference'
	
	for i in range(1,10):
		n = new_ton(i)
		m = using_math(i)
		d = abs(n-m)

		print '{}\t{:<12} \t {:<12} \t {:<12}'.format(i,n,m,d)
print_out()