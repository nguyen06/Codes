import math
def estimate_pi():

	k = 0
	term = (math.factorial(4*k)*(1103+26390*k))/(math.pow(math.factorial(k),4)*math.pow(396,4*k))
	sum = 0
	while term > 1e-15:
		term = (math.factorial(4*k)*(1103+26390*k))/(math.pow(math.factorial(k),4)*math.pow(396,4*k))
		k = k + 1
		#print term
		sum += term
	return sum
print 1/((2*math.sqrt(2)/9801)*estimate_pi())