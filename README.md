# question-and-answer
## A Java program, a site that displays a list of questions stored in the file. Users can read questions and answers, and answer questions.
[Java Doc](api/com/example/package-summary.html)

I built map to store the questions and answers, the index (or key) is the index of the question,  
and the value is my own class that have a String to the question and ArrayList to answer of this question.  
Also I used four Servlets to manage the program.
1. ShowQuestions (index-servlet) - reads the all questions and display them.
2. WriteAnswer - display an HTML form to answer a question.
3. AddAnswerToArray - add the answer to ArrayList in the QandA.
4. SendAnswer - send ArrayList of answers (by get method).  
   and one more js file to frontend issues.  
