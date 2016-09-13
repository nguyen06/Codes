"""
	the program test all the six-digit numbers and prints any numbers 
	that satisfy these requirements
"""
def is_six_digit(word):
	num = '0123456789'
	i = 0
	count = 0
	while i < len(word):
		if word[i] in num:
			count += 1
			if count == 6:
				return True
			i += 1
		else:
			i += 1
	return False
def find_six_digit():
	with open('six_digit.txt','r') as f:
		for line in f:
			for word in line.split():
				if is_six_digit(word):
					print word
find_six_digit()
#print is_six_digit('300,000')