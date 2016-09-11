"""
	An anagram is direct word switch or word play, the result of rearrangeing 
	the letters of a word or phrase to produce a new word or phrase, using
	all the orginal letters exactly once

	write a program that read a word list from a file and prints all the sets 
	of words that are anagrams
"""

def make_historam(filename):
	"""
		read from the file and read the line, read the word, put them in the 
		dictionary
	"""
	d = dict()
	sort = list()
	fin = open(filename)
	for line in fin:
		word = line.strip().lower()
		
		order = order_letters(word)
		
		if order not in d:
			d[order] = [word]	#the value is the list
		else:
			d[order].append(word)
	return d

def order_letters(s):
	"""
		order letters in the word
	"""
	l = list(s)
	sort = sorted(l)
	t = ''.join(sort)
	return t
def print_anagram(d):
	"""
		go through the dictionary to find the list has more than 2 values, 
		then print it
	"""
	# print the largest set of anagram first, followed by the second largest
	l = []
	for key in d:
		if len(d[key]) > 2:
			l.append((len(d[key]),d[key]))
	l.sort(reverse = True)
	for element in l:
		print element
if __name__ == "__main__":
	d = make_historam('words.txt')
	print_anagram(d)