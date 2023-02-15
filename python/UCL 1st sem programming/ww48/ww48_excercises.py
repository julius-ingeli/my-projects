"""
Exercise 1: Write a simple program to simulate the operation of the
grep command on Unix. Ask the user to enter a regular expression and
count the number of lines that matched the regular expression:
$ python grep.py
Enter a regular expression: ^Author
mbox.txt had 1798 lines that matched ^Author
$ python grep.py
Enter a regular expression: ^Xmbox.
txt had 14368 lines that matched ^X-
$ python grep.py
Enter a regular expression: jah
mbox.txt had 4175 lines that matched java$
"""
import re
counter = 0
search = input("Search for expressions: ")
fhand = open("mbox.txt","r")
for line in fhand:
    line = line.rstrip()
    if re.search(search, line):
        counter += 1
print("Found ", str(counter), " results.")



"""
Exercise 2: Write a program to look for lines of the form:
New Revision: 39772
Extract the number from each of the lines using a regular expression
and the findall() method. Compute the average of the numbers and
print out the average as an integer.
Enter file:mbox.txt
38549
Enter file:mbox-short.txt
39756
"""
import re

filename = input("Enter file: ")
fhand = open(filename, 'r')

nums = []

for line in fhand:
    match = re.search("New\sRevision:\s(\d+)", line)
    if match:
        nums.append(int(match.group(1)))

print(sum(nums)/len(nums))