"""
	The code is to find three consecutive double letters.
"""
def is_triple_double(word):
	i = 0
	count = 0
	while i < len(word) -1:
		if word[i] == word[i+1]:
			count += 1
			if count == 3:
				return True
			i += 2
		else:
			count = 0
			i += 1
	return False

def find_triple_double():
	fin = open("words.txt")
	for line in fin:
		word = line.strip()
		if is_triple_double(word):
			print word

print 'three consecutive double letters'
find_triple_double()
print ''