def ack(m,n):
	if m == 0:
		return n + 1
	elif n == 0 and m > 0:
		return ack(m-1,n)
	else:
		return ack(m-1,ack(m,n-1))

print ack(300,400)