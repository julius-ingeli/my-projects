import random #importing the library for randomization
import time #importing time because why not
print("INITIALIZING NUMBER GUESSING GAME V1 ")
time.sleep(3)
print("\nThe goal of this game is to guess a number between 0 and 9. \nYou have 3 attempts to guess the number!")
attempt = 3 #assigning 3 attempts     
guessNum = str(random.randint(0,9)) #assigning the guessed number as a random
print(guessNum)
while True:
      userInput = str(input("Insert number here: "))
      if attempt == 1:
                  decision1 = input("You lose!\nDo you want to play again?\n y for Yes, n for No ")
                  if decision1 == "y":
                        attempt = 3
                        print("Very well, new game it is!")
                        continue
                  if decision1 == "n":
                        print("Shutting down the game.... \n Thank you for playing! \n Made by Julius.")
                        exit(-1)
                  else:
                        attempt = 3
                        print("Not an option. You shall play again!")
                        continue
      if userInput.isdigit() == False:
            print("Not a number! Enter a number!")
            continue
      if userInput != guessNum:
             attempt -= 1
             print("Wrong number! Try again.\nNumber of attempts: " + str(attempt) + ".")
             continue
      if userInput == guessNum:
            decision1 = input("You won... do you want to play a new game? \n y for Yes, n for No")
            if decision1 == "y":
                  attempt = 3
                  print("Very well, new game it is!")
                  continue
            if decision1 == "n":
                  print("Shutting down the game.... \n Thank you for playing! \n Made by Julius.")
                  exit(-1)
            else:
                  attempt = 3
                  print("Not an option. You shall play again!")
                  continue

      


