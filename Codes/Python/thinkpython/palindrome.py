def first_word(word):
	return word[0]
def last_word(word):
	return word[-1]
def midle(word):
	return word[1:-1]

def is_palendrom(word):
	if len(word) <=1:
		return True
	if first_word(word) != last_word(word):
		return False
	else:
		return is_palendrom(midle(word))
print is_palendrom("allen")
print is_palendrom("atta")
print is_palendrom("redivider")