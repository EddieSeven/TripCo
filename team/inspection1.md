# Team *T25* - Inspection *1*
 
Inspection | Details
----- | -----
Subject | *Server Response Inspection*
Meeting | *10/31/2017, 7:00PM, Remote (Discord)*
Checklist | *Server.java, SearchBar.jsx (Lines 135-194)*

### Roles
Name | Role | Preparation Time
---- | ---- | ----
Charles | Moderator |
Leif | End-user |
Michael | Tester |
Will | Maintainer |

### Log
file:line | defect | h/m/l | github# | who
--- | --- |:---:|:---:| ---
SearchBar.jsx: lines 161-165, 168 | JSON parsed without an SVG | h | | Charles
SearchBar.jsx, line 142-150 | Check if type is something other than query or SVG | h | | Leif
SearchBar.jsx, line 126 | Div meant for SVG not displaying due to no data in sag variable | h | | Charles
Server.java, line 51-63 | When called serveSVG does not render | h | | Charles
Server.java, line 51-63 | testServe could be a JUnit test | l | | Michael
Server.java, line 38 | Blank string being received from View class | l | | Michael
Server.java, line 54 | For console output, an arraylist object is appended to the output string | l | | Michael
