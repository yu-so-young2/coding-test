n, k = map(int,input().split())
people = [i for i in range(1, n+1)]
done = 0
result = []

index = k
while(len(people) > 0):
    while(index > len(people)):
        index -= len(people)

    result.append(str(people.pop(index-1)))
    index += k-1

print("<", ", ".join(result) ,">", sep='')
