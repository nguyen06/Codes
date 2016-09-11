def sed(filename):
	try:
		fin = open(filename)
		fout = open('out.txt', 'w')
		for line in fin:
			fout.write(line)
		fin.close()
		fout.close()
	except:
		print "something went wrong"
sed('words.txt')