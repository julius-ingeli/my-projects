"""
Exercise 1: Download a copy of the file www.py4e.com/code3/words.txt. Write a program that reads the words in words.txt and stores them as keys in a dictionary. It doesn’t matter what the values are. Then you can use the in operator as a fast way to check whether a string is in the dictionary.
"""
dict = {}
openup = open("words.txt", "r")
for word in openup:
    word.split()
    dict = {word : "0"}
    print(dict)


"""
Exercise 2: Write a program that categorizes each mail message by
which day of the week the commit was done. To do this look for lines
that start with “From”, then look for the third word and keep a running
count of each of the days of the week. At the end of the program print
out the contents of your dictionary (order does not matter).
Sample Line:
From stephen.marquard@uct.ac.za Sat Jan 5 09:14:16 2008
Sample Execution:
python dow.py
Enter a file name: mbox-short.txt
{'Fri': 20, 'Thu': 6, 'Sat': 1}
116 CHAPTER 9. DICTIONARIES
"""
days = {}
fhand = open("mbox-short.txt", 'r')

for line in fhand:
    if line.startswith("From "):
        day = line.split()[2]
        days[day] = days.get(day, 0) + 1

print(days)

"""
Exercise 3: Write a program to read through a mail log, build a histogram using a dictionary to count how many messages have come from each email address, and print the dictionary.
Exercise 4: Add code to the above program to figure out who has the most messages in the file. After all the data has been read and the dictionary has been created, look through the dictionary using a maximum loop (see Chapter 5: Maximum and minimum loops) to find who has the most messages and print how many messages the person has.

"""

logs = {}
fhand = open("mbox.txt", 'r')

for line in fhand:
    if line.startswith("From "):
        address = line.split()[1]
        logs[address] = logs.get(address, 0) + 1

print(logs)

max_address = None
max_emails = 0
for k in logs:
    if logs[k] > max_emails:
        max_address = k
        max_emails = logs[k]

print(max_address, max_emails)
"""
Exercise 5: This program records the domain name (instead of the address) where the message was sent from instead of who the mail came from (i.e., the whole email address). At the end of the program, print out the contents of your dictionary.
"""
doms = {}
fhand = open("mbox.txt", 'r')

for line in fhand:
    if line.startswith("From "):
        address = line.split()[1]
        domain = address.split("@")[1]
        doms[domain] = doms.get(domain, 0) + 1

print(doms)