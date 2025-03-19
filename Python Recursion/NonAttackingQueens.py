def queens(ar, ar2, y, n):
    total = 0
    for x in range (n):
        if y == (n - 1):
            if check(ar, ar2, y, x) == 1:
                total = total + 1
                remove(ar, ar2)
        else:
            if check(ar, ar2, y, x) == 1:
                total = total + queens(ar, ar2, y + 1, n)
                remove(ar, ar2)
    return total
def remove(ar, ar2):
    if (len(ar) > 0):
        ar.pop(len(ar) - 1)
        ar2.pop(len(ar2)-1)
        
def check(ar, ar2, y, x):
    n = len(ar)
    for i in range(n):
        j = ar[i]
        j2 = ar2[i]
        if j == x:
            return 0
        if j2 == y:
            return 0
        calc = j - x
        calc2 = j2 - y
        if abs(calc) == abs(calc2):
            return 0
    ar.append(x)
    ar2.append(y)
    return 1

ar = []
ar2 = []
n = int(input("Please input the number of queens "))
forprint = queens(ar, ar2, 0, n)
print("The number of possibilities are:", forprint)
