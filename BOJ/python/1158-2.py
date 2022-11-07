n, k = map(int,input().split())
people = [i for i in range(n, 0, -1)]
done = 0
result = "<"

while(done < n) :
    for i in range(k-1):
        people.insert(0, people.pop())
    if(done != 0): result += ", "
    result += str(people.pop())
    done += 1

result += ">"
print(result)

