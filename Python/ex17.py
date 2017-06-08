from sys import argv
from os.path import exists

script, from_file, to_file = argv

print ("copy from %s to %s " % (from_file, to_file))

in_file = open(from_file)
in_data = in_file.read();

print ("the in_file is %d bytes long " % len(in_data))

print ("does the output file exists? %r " % exists(to_file))

input()

out_file = open(to_file, 'w')
out_file.write(in_data)

out_file.close()
in_file.close()