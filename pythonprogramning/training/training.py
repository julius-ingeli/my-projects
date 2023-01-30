#REKURZIA

def f1(n):
    if n == 0:
        return 0
    return n+f1(n-1)

#print(f1(9))

def f2(n):
    if n == 0:
        return 0
    a = int(input("Zadajte číslo:"))
    return a+f2(n-1)
#print(f2(4))

def f3(n):
    if n == 0:
        return 0
    a = int(input("Cislo: "))
    if a%2 == 0:
        return 1 + f3(n-1)
    else:
        return f3(n-1)
    
#print(f3(4))

def f4(n):
    a =f4_core(n)
    if a%2 == 0:
        return True
    else:
        return False
def f4_core(n):
    if n == 0:
        return 0
    a = int(input("Cislo: "))
    return a + f4_core(n-1)

#print(f4(6))

def f5(n):
    if n == 0:
        return 0
    else:
        a=int(input("Cislo: "))
        b=f5(n-1)
        if a > b:
            return a
        else:
            return b
        
#print(f5(4))



matica = [[0,3,3,4],
          [0,3,3,3],
          [2,3,3,2],
          [6,3,3,1]]



'''4. Definujte funkciu, ktorej argumentom bude matica A. Funkcia vrati
poziciu najvacsieho cisla v matici. Poziciu maxima reprezentujte zoznamom
dvoch cisel (prve cislo bude index riadka, druhe cislo bude index
stlpca). Riadky a stlpce v matici indexujte od nuly. V pripade, ze sa
najvacsie cislo v matici vyskytuje viackrat, funkcia vrati poziciu toho
maxima, na ktore natrafime ako na prve, ked citame prvky v matici v
smere, v akom citame normalny text.
Priklad: Pre maticu [[0,0,1],[1,0,0]] funkcia vrati [0,2], lebo prve
maximum sa vyskytuje v nultom riadku a v druhom stlpci.'''
def f6(A):
    maxNN = 0
    for r in A:
        maxN = max(r)
        if maxN > maxNN:
            maxNN = maxN
            
    for i,j in enumerate(A):
        for k,l in enumerate(j):
            if l ==maxNN:
                return [i,k]
#print(f6(matica))
'''Definujte funkciu, ktorej argumentom bude matica A. Funkcia vrati
pocet takych stlpcov v matici, ktore obsahuju same nuly.'''

def f7(A):
    poc = 0
    for i in range(len(A)):
        col = [row[i]for row in A]
        if set(col) == {0}:
            poc += 1
    return poc
#print(f7(matica))
'''6*. Definujte funkciu, ktorej argumentom bude matica A. Funkcia vrati
hodnotu True, ak matica obsahuje aspon jeden stlpec, v ktorom su vsetky
cisla rovnake. Inak funkcia vrati hodnotu False.'''

def f8(A):
    for i in range(len(A)):
        col = [row[i]for row in A]
        if len(set(col)) == 1:
            return True
    return False
#print(f8(matica))
'''7*. Definujte funkciu, ktorej argumentom bude matica A. Funkcia vrati
hodnotu True, ak matica obsahuje aspon jeden stlpec, v ktorom su vsetky
cisla rozne. Inak funkcia vrati hodnotu False.'''
def f9(A):
    sumcols = []
    for i in range(len(A)):
        col = [row[i]for row in A]
        sumcol = sum(col)
        sumcols.append(sumcol)
        
    maxcol = max(sumcols)
    maxcolind = sumcols.index(maxcol)
    return maxcolind

#print(f9(matica))


'''8*. Definujte funkciu, ktorej argumentom bude matica A. Funkcia vrati
index stlpca v matici s najvacsim suctom. Stlpce v matici indexujte od
nuly. V pripade, ze viacero stlpcov v matici nadobuda maximalny sucet,
funkcia vrati index stlpca s maximalnym suctom, ktory je najviac vlavo.'''

def f10(A):
    sumcols = []
    for i in range(len(A)):
        col = [row[i]for row in A]
        sumcol = sum(col)
        sumcols.append(sumcol)
        
    maxcol = max(sumcols)
    if sumcols.count(maxcol) > 1:
        return True
    return False

#print(f10(matica))

inter = [[1,5],[2,4],[0,9]]
def f11(t,x):
    poc = 0
    for i in t:
        if i[0] < x < i[1]:
            poc += 1
            
    return poc

#print(f11(inter,2))

"""2. Definujte funkciu, s nasledovnymi vlastnostami. Argumentami funkcie
budu zoznam intervalov (oznacme tento zoznam ako zoznam t) a cislo x.
Funkcia vrati prvy interval v zozname t, v ktorom sa nachadza cislo x. Ak
sa cislo x nenachadza v ziadnom intervale zo zoznamu t, funkcia vrati
hodnotu None."""
def f12(t,x):
    for i in t:
        if i[0] < x < i[1]:
            return i
    return None

#print(f12(inter,3))

"""3. Definujte funkciu, s nasledovnymi vlastnostami. Argumentami funkcie
budu zoznam intervalov (oznacme tento zoznam ako zoznam t) a cislo x.
Funkcia vrati zoznam tych intervalov zo zoznamu t, v ktorych sa nachadza
cislo x."""
def f12(t,x):
    lInter = [] 
    for i in t:
        if i[0] < x < i[1]:
            lInter.append(i)
    return lInter

#print(f12(inter,4))
"""4*. Definujte funkciu, s nasledovnymi vlastnostami. Argumentom funkcie
bude zoznam intervalov (oznacme tento zoznam ako zoznam t). Funkcia vrati
hodnotu True, ak sa v zozname t nachadza taky interval, ktory nema
prienik zo ziadnym inym intervalom v zozname t. Inak funkcia vrati
hodnotu False.
Priklad: Pre zoznam [[1,2],[3,5],[4,6]] vrati funkcia hodnotu True, lebo
interval [1,2] nema prienik ani s intervalom [3,5], ani s intervalom
[4,6]."""


def f13(t):
   pass
#print(f13(inter))


"""5*. Definujte funkciu, s nasledovnymi vlastnostami. Argumentom funkcie
bude zoznam intervalov (oznacme tento zoznam ako zoznam t). Funkcia vrati
hodnotu True, ak sa v zozname t nachadza taky interval, ktory obsahuje
vsetky ostatne intervaly zo zoznamu t. Inak funkcia vrati hodnotu False.
Priklad: Pre zoznam [[1,2],[3,5],[0,6]] vrati funkcia hodnotu True, lebo
interval [0,6] obsahuje intervaly [1,2] a [3,5]."""

def f14(t):
    zoznList = []
    for i in t:
        zozn = []
        for x in range(i[0],i[1]):
            zozn.append(x)
        zoznList.append(zozn)
        
    zoznDlzka = 0
    maxDlzkaindex = 0
    for z in zoznList:
        zLen = len(z)
        if zLen > zoznDlzka:
            zoznDlzka = zLen
            maxDlzkaindex = zoznList.index(z)
            
    return maxDlzkaindex
            
  
        
        
print(f14(inter))