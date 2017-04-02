
import os

class element:
	def __init__(self, row, column, value):
		self.row= row;
		self.column = column
		self.value = value

sudoku_list_element = []
list_contain_at_list_one = []
list_element_to_CNF_file = [] # initialize 
list_at_most_one_number = [] #
list_at_most_one_number_column =[]
list_at_most_one_number_column_in_three_by_three = []
list_at_most_one_number_column_in_three_by_three_part_two =[]

excepted_list =[".","?","*","_","0","\n"]


temp_file = open("temFile.txt",'w')
CNF_file = open("cnf_format.cnf", 'w')

"""
	read the puzzle input and convert to (i,j,k) format

	i: the line number
	j: the column number
	k: the value at (i,j)

	only read when the value of at (i,j) is not ('0','?','.','*')
"""
def getInput():
	
	line_num = 0
	with open("test1.txt") as f:
		for line in f:
			line_num += 1
			column_num = 0
			for ch in line:
				column_num += 1
				if ch not in excepted_list:
					
					tempObject = element(line_num, column_num,ch)
					#print("hell0")
					sudoku_list_element.append(tempObject)
				if ch  == "\n":
					column_num = 0
				
					

#just for testing
def test(mylist):
	#getInput()

	for e in mylist:
		print(str(e.row) + "" + str(e.column) + "" + str(e.value))


"""
	now we conver these (i,j,k) into the Conjunctive Normal Form (CNF)
	using the formular given in class

	(i,j,k) -> 81*(i-1) + 9*(j-1) +1

	the result will be write to CNF file
"""
def convert_to_CNF(list_element):
	line_num = 1
	for element in list_element:
		if int(element.row) == line_num:
			CNF_temp_element = 81*(int(element.row) -1) + 9*(int(element.column) -1) + (int(element.value) -1) +1
			temp_file.write(str(CNF_temp_element) + " 0" + "\n")
		else:
			line_num += 1
			#temp_file.write("0" + "\n")
	#temp_file.write("0" +"\n") #write to the end of line


def contain_at_least_one_number():
	#f = open("at_leat_one.txt",'w')
	list_at_list_one_number = []
	for i in range(1,10):
		for j in range(1,10):
			for k in range(1,10):
				tempObject = element(i,j,k) #fa
				list_contain_at_list_one.append(tempObject)

def conver_contain_at_least_one_number_to_CNF(list_element):
	line_num = 1
	column_num = 1
	for element in list_element:
		
		if int(element.row) == line_num and int(element.column) == column_num:
			CNF_temp_element = 81*(int(element.row) -1) + 9*(int(element.column) -1) + (int(element.value) -1) +1
			temp_file.write(str(CNF_temp_element) + " ")
		elif int(element.row) == line_num and int(element.column)  != column_num:
			
			temp_file.write("0" + "\n")
			CNF_temp_element = 81*(int(element.row) -1) + 9*(int(element.column) -1) + (int(element.value) -1) +1
			temp_file.write(str(CNF_temp_element) + " ")
			column_num += 1
			
		else:
			
			temp_file.write("0" + "\n")
			#print(str(element.row) + " " + str(element.column) +" "+ str(element.value))
			CNF_temp_element = 81*(int(element.row) -1) + 9*(int(element.column) -1) + (int(element.value) -1) +1
			temp_file.write(str(CNF_temp_element) + " ")
			line_num += 1
			column_num = 1

	temp_file.write("0" + "\n") #write to the end of line

def contain_at_most_one_in_row():

	for i in range(1,10):
		for k in range(1,10):
			for j in range(1,9):
				for l in range((j+1),10):
						tempObject01 = element(i,j,k)
						tempObject02 = element(i,l,k)
						list_at_most_one_number.append(tempObject01)
						list_at_most_one_number.append(tempObject02)

def covert_list_contain_at_most_one_to_CNF(list_most_one):
	i = 1
	k = 1
	count = 1
	for element in list_most_one:
		if count < 3:
			CNF_temp_element = -(81*(int(element.row) -1) + 9*(int(element.column) -1) + (int(element.value) -1) +1)
			#print(str(CNF_temp_element) + " ")
			temp_file.write(str(CNF_temp_element) + " ")
			count += 1
		else:
			#print("\n")
			temp_file.write("0" + "\n")
			count = 2
			CNF_temp_element = -(81*(int(element.row) -1) + 9*(int(element.column) -1) + (int(element.value) -1) +1)
			temp_file.write(str(CNF_temp_element) + " ")
			#print(str(CNF_temp_element) + " ")
			
			
	temp_file.write("0" + "\n")

def appear_at_most_one_in_column():
	for j in range(1,10):
		for k in range(1,10):
			for i in range(1,9):
				for l in range((i+1),10):
						tempObject01 = element(i,j,k)
						tempObject02 = element(l,j,k)
						list_at_most_one_number_column.append(tempObject01)
						list_at_most_one_number_column.append(tempObject02)

def covert_list_contain_at_most_one_in_column_to_CNF(list_most_one):
	i = 1
	k = 1
	count = 1
	for element in list_most_one:
		if count < 3:
			CNF_temp_element = -(81*(int(element.row) -1) + 9*(int(element.column) -1) + (int(element.value) -1) +1)
			#print(str(CNF_temp_element) + " ")
			temp_file.write(str(CNF_temp_element) + " ")
			count += 1
		else:
			#print("\n")
			temp_file.write("0" + "\n")
			count = 2
			CNF_temp_element = -(81*(int(element.row) -1) + 9*(int(element.column) -1) + (int(element.value) -1) +1)
			temp_file.write(str(CNF_temp_element) + " ")
			#print(str(CNF_temp_element) + " ")
	temp_file.write("0" + "\n")

def appear_at_most_one_in_three_by_three_part_one():
	
	for k in range(1,10):
		for a in range(0,3):
			for b in range(0,3):
				for u in range(1,4):
					for v in range(1,3):
						for w in range(v+1,4):
								tempObject01 = element((3*a + u),(3*b +v),k)
								tempObject02 = element((3*a+u),(3*b+w),k)
								list_at_most_one_number_column_in_three_by_three.append(tempObject01)
								list_at_most_one_number_column_in_three_by_three.append(tempObject02)

def convert_appear_at_most_one_in_three_by_three_part_one_to_CNF(list_most_one):

	i = 1
	k = 1
	count = 1
	for element in list_most_one:
		if count < 3:
			CNF_temp_element = -(81*(int(element.row) -1) + 9*(int(element.column) -1) + (int(element.value) -1) +1)
			#print(str(CNF_temp_element) + " ")
			temp_file.write(str(CNF_temp_element) + " ")
			count += 1
		else:
			#print("\n")
			temp_file.write("0" + "\n")
			count = 2
			CNF_temp_element = -(81*(int(element.row) -1) + 9*(int(element.column) -1) + (int(element.value) -1) +1)
			temp_file.write(str(CNF_temp_element) + " ")
			#print(str(CNF_temp_element) + " ")
	temp_file.write("0" + "\n")

def appear_at_most_one_in_three_by_three_part_two():
	
	for k in range(1,10):
		for a in range(0,3):
			for b in range(0,3):
				for u in range(1,3):
					for v in range(1,4):
						for w in range(u+1,4):
							for t in range(1,4):
								tempObject01 = element(3*a +u,3*b +v,k)
								tempObject02 = element(3*a+w,3*b+t,k)
								list_at_most_one_number_column_in_three_by_three_part_two.append(tempObject01)
								list_at_most_one_number_column_in_three_by_three_part_two.append(tempObject02)

def convert_appear_at_most_one_in_three_by_three_part_two_to_CNF(list_most_one):

	i = 1
	k = 1
	count = 1
	for element in list_most_one:
		if count < 3:
			CNF_temp_element = -(81*(int(element.row) -1) + 9*(int(element.column) -1) + (int(element.value) -1) +1)
			#print(str(CNF_temp_element) + " ")
			temp_file.write(str(CNF_temp_element) + " ")
			count += 1
		else:
			#print("\n")
			temp_file.write("0" + "\n")
			count = 2
			CNF_temp_element = -(81*(int(element.row) -1) + 9*(int(element.column) -1) + (int(element.value) -1) +1)
			temp_file.write(str(CNF_temp_element) + " ")
			#print(str(CNF_temp_element) + " ")
	temp_file.write("0" + "\n")

"""
	At this poit, we convert the puzzel and all rule to CNF format
	However, we need to count number of variable and clauses base one the 
	file that we have just created
"""
def count_variables_and_clauses():
	list_variable = []
	num_of_variable = 0
	num_of_clause = 0
	num = ''
	with open("temFile.txt") as f:
		for lines in f:
			for ch in lines:
				if ch != " " and ch != "\n":
					num += ch
				elif ch == '\n':
					num_of_clause +=1
					num = " "
				else:
					#print(num)
					if int(num) > 0:
						#num_of_variable += 1
						list_variable.append(num)
						#print(num_of_variable)
						num = ''
					else:
						num = ''
	print(len(list_variable))
	#print(str(num_of_variable) + " " + str(num_of_clause) )
	"""
		Now we know number of variable and number of clauses, 
		we are able to write to the new CNF file with the header
	"""	
	f.close()		
	CNF_file.write("p cnf" + " " +str(len(list_variable)) + " " + str(num_of_clause) + "\n")

def copy_old_file_to_new_file():
	with open("temFile.txt") as f:
		lines = f.readlines()
	for l in lines:
		CNF_file.write(l)

if __name__ == '__main__':

		#get the input from the puzzel file
			
		getInput()
		convert_to_CNF(sudoku_list_element)
		test(sudoku_list_element)
		
		contain_at_least_one_number()

		test(list_contain_at_list_one)
		conver_contain_at_least_one_number_to_CNF(list_contain_at_list_one)
		
		contain_at_most_one_in_row()
		test(list_at_most_one_number)
		covert_list_contain_at_most_one_to_CNF(list_at_most_one_number)
		
		appear_at_most_one_in_column()
		covert_list_contain_at_most_one_in_column_to_CNF(list_at_most_one_number_column)
		test(list_at_most_one_number_column)
		
		appear_at_most_one_in_three_by_three_part_one();
		convert_appear_at_most_one_in_three_by_three_part_one_to_CNF(list_at_most_one_number_column_in_three_by_three)
		test(list_at_most_one_number_column_in_three_by_three)
		
		appear_at_most_one_in_three_by_three_part_two()
		test(list_at_most_one_number_column_in_three_by_three_part_two)
		convert_appear_at_most_one_in_three_by_three_part_two_to_CNF(list_at_most_one_number_column_in_three_by_three_part_two)
		
		#close the file
		temp_file.close()

		count_variables_and_clauses()
		copy_old_file_to_new_file()



		#os.system('minisat cnf_format.cnf outputFile.cnf')
		
