import itertools
m_ingredients = int (input())
ingredients = []
MinScore = 1000000000
while True:
    if(m_ingredients<=10 and m_ingredients>=1):
        for i in range(0, m_ingredients):
            sourVbitter = list(map(int, input().split()))
            print("sourVbitter: ",sourVbitter)#debug
            ingredients.append([sourVbitter[0], sourVbitter[1]])
            print("ingredients: ",ingredients)#debug
        l = len(ingredients)
        comboList = []
        for i in range(1, l+1):
            comboList = comboList + list(itertools.combinations(ingredients, i))
            print("comboList: ",comboList)#debug
        for total in comboList:
            product = 1
            sum = 0
            for num in total:
                product=product * num[0]
                sum=sum + num[1]
            if abs(product-sum)<MinScore:
                MinScore=abs(product-sum)
        print(MinScore)
        break
    else:
        m_ingredients = int (input())
