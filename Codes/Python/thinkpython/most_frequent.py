"""
	write the most_frequent that takes a string and prints 
	the letters in decreasing order of frequency. 
"""

def most_frequent(s):

	d = make_histogram(s)		#build the dictionary

	"""
		how do we sort them in the decreasing order?

		we can sorted dictionary with reverse order
	"""
	for w in sorted(d, key = d.get, reverse=True):
		print w, d[w]


def make_histogram(s):
	"""
		build a dictionary contains the frequency of letters appear 
		in the string
	"""
	d = dict()
	for x in s:
		d[x] = d.get(x,0) + 1
	return d
if __name__ == "__main__":
	most_frequent("I am writing this detailed explanation.")