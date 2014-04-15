This was the hardest one from 2014 qualification, and not only for me as you can see by GCJ site statistics.
The reason I failed to solve this in time is that my first algorithm was completely wrong and I've found it too late.
The new algorithm works right, but I could check it only on the next day.

I haven't found any of the solution in this Stack Overflow question that might looks like mine so I decided to show my algorithm below.

I know that my solution is not the best one and even not mathematically strict, but at least it's done completely by myself ^)

The main idea is that every mine field may be smth like:
1) No mines. The problem is solved. Obviously.
2) The mine field is 2x2 and must contain 3 mines. Whenever we clicking the cell, all other cells will contain the mines. Problem is solved.
3) The mine field is 2x2 and must contain less then 3 mines (but non-zero of course. We have already checked it in the first place). This situation leads to failure - there are no possibilities to solve this riddle in one click.
4) Every other field may be solved by filling the one (narrow) side with mines and reduce the problem to the new field without that one row/column and without the placed mines. GO TO 1 :)

There is one issue with this algorithm - there is no reasonable solution when only one empty cell left between the mine and the wall. This possible situation should be checked explicitly every time we found that there are left less mines than cells in narrow row/column.
In this situation we need to left exactly two empty cell from wall and then reduce the problem to previous again.
