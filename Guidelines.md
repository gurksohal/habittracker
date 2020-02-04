#Variables 
use Camel Case when necessary

| Prefix | Type    | Example     |
|--------|---------|-------------|
| b      | boolean | bSuccess    |
| i      | integer | iCustomerID |
| f      | float   | fTotalCost  |
| s      | String  | sFirstName  |
| l      | long    | lDistance   |
| a      | array   | aReports    |
| o      | Object  | oContact    |

# Design Elements (GUI) 
use Camel Case when necessary

| Prefix | Type       | Example     |
|--------|------------|-----------|
| btn    | button     | btnAccept   |
| tgl    | toggle     | tglOff      |
| txt    | Text Field | txtNotes    |


#Acceptable abbreviations 
Num, Max, Min, Avg
#Functions
1. When naming a function, use a verb followed by an object to describe what the function does. For example, getClientData()
2. Function names will begin with a word beginning with a lowercase letter and each consecutive word will begin with an uppercase letter. 
3. Block Comments should appear before the function header and should follow this outline, unless the function header fully describes its purpose:

``` java
/*
 *  Function Name
 * Description of the function
 * Input Type
 * Output Type
 */
 ```
4. Functions should have only one return statement.
5. Case statements are allowed. 
6. For Loops
    * Use the standard i, j, k loop variables. If there are more than 3 nested loops, continue naming the loop variables alphabetically from k. 
    * For Loops will be written like: 
``` java
for(int i = 0; i <10; i++){
…
}
or like:
	
for(Object obj: objList){
….
}

```


#If Statements
If statements will be written as follows: 
``` java
averageNum(..){
….
if (bool){ //inline comment, if necessary
….
} else {
….
}
	}

```
