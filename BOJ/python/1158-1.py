n, k = map(int,input().split())
people = list(0 for i in range(n))
result = "<"
i, j, done = 0, k-1, 0
while(done < n) : # not empty
    while(j > 0 or people[i] == 1) : # move
        if(people[i] == 0):
            j -= 1
            i += 1
        else: i += 1
        if(i > (n-1)): # out of index
            i -= n
    
    people[i] = 1
    done += 1
    if(done > 1): result += ", "
    result += str(i+1)

    j = k-1
    i += 1
    if(i > n-1): i -= 1

result += ">"
print(result)

