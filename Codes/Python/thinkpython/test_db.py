import anydbm
db = anydbm.open('caption.db','c')
db['cleese.png'] = 'my photo'
print db['cleese.png']
for key in db:
	print key
db.close()