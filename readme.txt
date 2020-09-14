Grace Kwok

Assumption:
- The Json files are valid and there aren't any duplicate key per each entry.
- The search is only searching the first level of keys.
- Matching of values is an exact match.  For instance, "abc" is not the same as "Abc".  You may type in "" as value or null as value for your search. Typing in "" will match key:"" and typing in null will match key:null. However, if the key is missing for an entry, it is not considered as "" or null.  So, that result will not be returned.  I trim the value in the code so if you enter " abc " it will be search as "abc".
- It is assume that the Json data is unchanging once the java program starts.  The program loads the data in memory once at start up.

How to run the program:
- Please use java 1.8 or above.
- You could use "mvn clean install" to get the dependencies and compile the program.  Note that if you have firewall or certain proxy configuration, you might have trouble downloading the dependencies.
- To run, the main file is located at: com.zendesk.Main. 

Requirement limitation:
- The requirement states that as we enter "quit", it will quit the program.  That limits the ability to search of entries in which the key is "quit" or the value to be searched is "quit."  For requirement enhancement, we might consider using another command.

Requirement Enhancement:
- The requirement states that "Press 'Enter' to continue".  I am not sure what this implies.  But I have added another command "M" to go to the main menu.  It works like "quit".  At any given point in time, you could enter "M" and it will go back to the main menu.  Of course, that also present the same problem as mentioned earlier in which we can't search a key or a value as "M".
- The searchable fields are listed in sorted ascending string order.

Testing:
The unit tests are resided in src\test\ 

Libraries used for the Json:
- https://www.baeldung.com/guide-to-jayway-jsonpath  
  https://github.com/json-path/JsonPath/blob/master/README.md
- com.google.code.gson
Other libaries used for other purposes can be found on the pom.xml.


Extendability:
- In the future, if one needs to add json files, one just needs to add an enum to this class DataCategory and follows the self explanatory code.
- In the future, if ones needs to search in more complexity, jsonpath has the functionality to archieve that.  It also has the functionality for one to write custom caching of most searched queries.

Memory usage:
-This program loads the json data in memory and cache it at start up.  This is a small set of data so it is fine for this case.




