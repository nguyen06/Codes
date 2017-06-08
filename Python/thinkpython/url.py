import urllib

zipcode = raw_input("what is the zip code?")

url = 'http://uszip.com/zip/' + zipcode
conn = urllib.urlopen(url)

for line in conn.fp:
    line = line.strip()
    if 'Total population' in line:
        print line
    if 'Asian' in line: 
        print line
    if 'White' in line: 
        print line

conn.close()