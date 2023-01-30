"""
def f(n):
    while n > 0:
        a = int(input("Zadaj číslo: "))
        b = int(input("Zadaj číslo: "))
"""

def f5(n):
    f = int(input())
    s = int(input())
    poc = 0
    for i in range(n-2):
        t = int(input())
        if f < s > t:
            poc+=1
        f = s
        s = t
    return poc
print(f5(9))