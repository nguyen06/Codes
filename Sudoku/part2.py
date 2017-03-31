list_variable = []
list_result = []
file_out = open('answer.txt','w')
def get_possitive_from_minisat_out_put():

	
	num_of_variable = 0
	num_of_clause = 0
	num = ''
	with open("minisol.txt") as f:
		f.readline()
		for lines in f:
			for ch in lines:
				if ch != " " and ch != "\n":
					num += ch
				
				else:
					#print(num)
					if int(num) > 0:
						#num_of_variable += 1
						list_variable.append(num)
						#print(num_of_variable)
						num = ''
					else:
						num = ''
	print(list_variable)
	
def convert_to_solve_puzzel():
	list_num = map(int,list_variable)
	for element in list_num:
		x = element -1
		i = x / 81

		if i == 0:
			i = 1
			j = x/9
			if j == 0:
				j=1
				k = x+1
			else:
				temp = j
				j = j +1
				k =  x-(temp *9) +1
		else:
			a = i
			i = i+1
			j = (x-(a*81))/9
			if j == 0:
				j = 1
				k = (x-(a*81)) +1

			else:
				b = j
				j = j +1
				k = x - (a*81) - (b*9) +1
		print(str(i) + " " + str(j) + " " + str(k))
		list_result.append(k)

	

if __name__ == '__main__':
	get_possitive_from_minisat_out_put()
	convert_to_solve_puzzel()
	count = 1
	for i in list_result:
		if count < 10:
			file_out.write(str(i) + " ")
			count += 1
		else:
			file_out.write("\n")
			file_out.write(str(i) + " ")
			count = 2
	file_out.write("\n")		