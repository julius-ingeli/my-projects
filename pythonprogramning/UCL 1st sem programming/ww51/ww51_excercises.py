import datetime
from datetime import date


print("Exercise 0 and 1:")



class Person:

    def __init__(self):
        self.name = input("Input the name here:")
        self.dateinput = input("Enter date in the YYYY-MM-DD format: (example: 1984-05-08)\n")
        self.gender = input("Select gender: M - Male, F - Female, O - Other\n")
        self.address = input("Enter the address: (example: Gadevej 42, Bornholm, 6330, DK)\n")
        self.pet_type = input("Enter the type of pet:\n")
        self.pet_name = input("Enter pet name:\n")
        self.income = input("Enter monthly income:\n")

    def get_firstname(self):
        name = self.name
        nameList = name.split()
        firstName = nameList[0]
        return firstName

    def get_lastname(self):
        name = self.name
        nameList = name.split()
        lastName = nameList[-1]
        return lastName

    def get_age(self):
        birthDate = datetime.date.fromisoformat(self.dateinput)
        today = date.today()
        age = today.year - birthDate.year -((today.month, today.day) < (birthDate.month, birthDate.day))
        return age

    def get_gender(self):
        if self.gender == "M":
            setGender = "Male"
            return(setGender)
        if self.gender == "F":
            setGender = "Female"
            return setGender
        if self.gender == "O":
            setGender = "Other"
            return setGender
        else:
            print("Invalid gender data! Setting gender to Other.")
            setGender = "Other"
            return setGender

    def get_address(self):
        listAddress = self.address
        return listAddress

    def get_pet(self):
        animal = str.upper(self.pet_type)
        petname = self.pet_name
        fullPetInfo = animal +  "- " + petname
        return fullPetInfo

    def get_income(self):
        income = self.income
        return income

    def intel(self):
        full_info = {"First Name:": info.get_firstname(), "Second Name:": info.get_lastname(),"Gender:": info.get_gender(), "Age:": info.get_age(), "Address:": info.get_address(), "Pet:": info.get_pet(), "Income:": info.get_income()}
        for key in full_info:
            print(key, full_info[key])


for t in range(3):
    info = Person()
    info.intel()
