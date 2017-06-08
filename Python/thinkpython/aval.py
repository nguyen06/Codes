import math


def evaluate():
	while True:
		a = raw_input("what expression do you want?\n")

		if a == 'done':
			break
		else:
			print eval(a)
evaluate()