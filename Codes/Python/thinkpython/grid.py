def print_beam():
	print ("+ - - - -",end=" ")
	
def print_plus():
	print("+")

def print_ver():
	print ("|         ",end="")

def do_twice(f):
	f()
	f()
def print_one_ver():
	print ("|")

def print_beams():
	do_twice(print_beam)
	print_plus()

def print_ver_line():
	do_twice(print_ver)
	print_one_ver()

def do_four():
	do_twice(print_ver_line)
	do_twice(print_ver_line)


def print_half():
	print_beams();
	do_four()

def print_full():
	do_twice(print_half)
	print_beams()

print_full()

# write function that draw a similar grid with four rows and four columns
print("print a grid with 4 rows and 4 columns")
def print_four(f):
	do_twice(f)
	do_twice(f)
def print_four_beams():
	print_four(print_beam)
	print_plus()
def print_four_ver():
	print_four(print_ver)
	print_one_ver()

def print_four_rows():
	print_four(print_four_ver)

def print_haft():
	print_four_beams()
	print_four_rows()
def print_four_full():
	print_four(print_haft)
	print_four_beams()
print_four_full()