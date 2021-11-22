"""
Exercise 1: Write a while loop that starts at the last character in the string and works its way backwards to the first character in the string, printing each letter on a separate line, except backwards.
"""
fruit = "Apple"
index = len(fruit) - 1 #the lenght of the string, subtracting one from the string length, since python starts couting from 0.
while index > -1: #the index must be greater than -1 because of reason above
      letter = fruit[index] #assigns the letter to corresponding index.
      print(letter) #prints the letter
      index -= 1 #subtracts from the index
"""
Excercise 2: Given that fruit is a string, what does fruit[:] mean?
"""
print(fruit[:]) # prints out the entire string.
"""
Exercise 3: Encapsulate this code in a function named count, and generalize it so that it accepts the string and the letter as arguments.
"""
def count(word, letter):
      counter = 0
      for letterSeek in word:
            if letterSeek == letter:
                  counter = counter + 1
      return counter

word = input("Input the word: ")
letter = input("Which letter do you need to count? ")
print("The letter " + letter + " appears in the word " + word + " " + str(count(word,letter)) + " times.")

"""
Exercise 4: There is a string method called count that is similar to the function in the previous exercise. Read the documentation of this method at:
https://docs.python.org/library/stdtypes.html#string-methods
Write an invocation that counts the number of times the letter a occurs in “banana”.
"""
def count(word, letter):
      counter = word.count(letter, 0 , len(word) + 1)
      return counter

word = input("Input the word: ")
letter = input("Which letter do you need to count? ")
print("The letter " + letter + " appears in the word " + word + " " + str(count(word,letter)) + " times.")
"""
Excercise 5: Take the following Python code that stores a string:
str = 'X-DSPAM-Confidence:0.8475'
Use find and string slicing to extract the portion of the string after the colon character and then use the float function to convert the extracted string into a floating point number.
"""
string = 'X-DSPAM-Confidence:0.8475'

dash = string.find("-")
print(dash) #finding the first dash, prints out 1

dash2 = string.find("-", 2)
print(dash2) #finding the second dash, prints out 7

colon = string.find(":")
print(colon) #finding the colon, prints out 18

numbers = float(string[19:]) #slicing the string into the numbers only, starting on the 19th character
print (numbers)                 #also converting the found value into float
"""
Excercise 6: Read the documentation of the string methods at https://docs.python.org/library/stdtypes.html#string-methods You might want to experiment with some of them to make sure you understand how they work. strip and replace are particularly useful
"""
print("INITIALIZING SENTENCE EDITOR BETA V002")
def capital(inpSentence):
      capOutput = inpSentence.upper()
      return capOutput

def low(inpSentence):
      lowOutput = inpSentence.lower()
      return lowOutput

def encode(inpSentence):
      encOutput = inpSentence.encode()
      return encOutput

inpSentence = input("Put the sentence here: ")
choice = input("What do you wish to do with the sentence? \nCHOICES: \n1. make all leters capital\n2. make all leters lowercase\n3.encode the following sentence\nPut in your choice here:")
while True:
      if choice == "1":
            print(capital(inpSentence))
            break
      if choice == "2":
            print(low(inpSentence))
            break
      if choice == "3":
            print(encode(inpSentence))
            break
      else:
            print("Not an option.")
            continue
