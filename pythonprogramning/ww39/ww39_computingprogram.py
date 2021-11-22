a = input("Input hours here: ")
b = input("Enter rate here")
try:
      hours = int(a)
      rate = int(b)
      if hours > 40:
            hours = hours - 40
            increasedPay = hours * rate * 1.5
            normPay = rate * 40
            finalPay = normPay + increasedPay
      else:
            finalPay = (rate * hours)

      print("The employees pay is " + str(finalPay) + "€.")
except:
      print("Please use a numerical value.")
