import sys
import os

class time(object):
	"""represent the time of day """
def print_time(time_obj):
	hour, minute, second = time_obj.hour, time_obj.minute, time_obj.second

	print ':'.join([str(hour), str(minute), str(second)])
def main():
	t = time()
	t.hour=10
	t.minute=30
	t.second = 23
	print_time(t)

if __name__ == '__main__':
	main()