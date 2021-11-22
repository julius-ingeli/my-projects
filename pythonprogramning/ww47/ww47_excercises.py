"""
Exercise 1: Revise a previous program as follows: Read and parse the “From” lines and pull out the addresses from the line. Count the number of messages from each person using a dictionary. After all the data has been read, print the person with the most commits by creating a list of (count, email) tuples from the dictionary. Then sort the list in reverse order and print out the person who has the most commits.
"""
fhand = open("mbox-short.txt", "r")
email_address = {}
for entry in fhand:
    if entry.startswith("From "):
        email = entry.split()[1]
        email_address[email] = email_address.get(email,0) + 1
lst = []
for key, val in email_address.items():
    lst.append((val, key))

lst.sort(reverse=True)
tuple = lst[0]
print(tuple[1], tuple[0])

"""
Exercise 2: This program counts the distribution of the hour of the day for each of the messages. You can pull the hour from the “From” line by finding the time string and then splitting that string into parts using the colon character. Once you have accumulated the counts for each hour, print out the counts, one per line, sorted by hour as shown below.
"""
fhand = open("mbox-short.txt", "r")
hours = {}
for line in fhand:
    if line.startswith("From "):
        time = line.split()[5]
        hour = time.split(":")[0]
        hours[hour] = hours.get(hour, 0) + 1

lst = list(hours.items())
lst.sort()
for t in lst:
    print(t[0], "-", t[1])

""""
Exercise 3: Write a program that reads a file and prints the letters in decreasing order of frequency. Your program should convert all the input to lower case and only count the letters a-z. Your program should not count spaces, digits, punctuation, or anything other than the letters a-z. Find text samples from several different languages and see how letter frequency varies between languages. Compare your results with the tables at https://wikipedia.org/wiki/Letter_frequencies.
"""

alphabet = 'abcdefghijklmnopqrstuvxwyz'

fhand = open("mbox-short.txt", 'r')
text = fhand.read()

freq_dict = {}
for ch in text.lower():
    if ch in alphabet:
        freq_dict[ch] = freq_dict.get(ch, 0) + 1

lst = []
for key, value in freq_dict.items():
    lst.append((value,key))

lst.sort(reverse=True)
for freq, letter in lst:
    print(letter, "-", freq)