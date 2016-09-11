import string
"""
	the code is to encryption that involves "rotating" each letter in a
	word by n place.
"""
def rotate_letter(letter, n):
	if letter.isupper():
		start = ord('A')
	elif letter.islower():
		start = ord('a')
	else:
		return rot_letter # THIS CASE THE NUMBER

	num = ord(letter) - start
	let = (num + n)%26 + start
	return chr(let)
def rotate_word(word,n):
	rot = ''
	for letter in word:
		rot += rotate_letter(letter,n)
	return rot
if __name__== '__main__':
	print rotate_word('cheer',7)
	print rotate_word('thinh',7)
	print rotate_word('melon',7)