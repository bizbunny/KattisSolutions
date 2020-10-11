m = int(input())
possibleOperations = ['*','+','-','//']
answerArray = {}

for j in range(0,m):
    n = int(input())
    for op1 in possibleOperations:
        for op2 in possibleOperations:
            for op3 in possibleOperations:
                expression = f'4 {op1} 4 {op2} 4 {op3} 4'
                evaluatedExpression = eval(expression)
                expression = expression.replace('//','/')
                formattedExpression = (f'{expression} = {evaluatedExpression}')
                answerArray[evaluatedExpression]=formattedExpression
    if n>256 or n <-60 or n not in answerArray:
        print("no solution")
    else:
        print(answerArray[n])
