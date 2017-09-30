# Sprint 2 - *T25* - *NO NAME GROUP*

## Goal

### A shorter trip with a better itinerary.

## Definition of Done

* Ready for the demo.
* Sprint Review and Restrospectives completed.
* Product Increment release created on GitHub with appropriate version number and name, a description based on the template, and a JAR file containing the executables to be used for the demo. 
* Unit tests for all new features and public methods at a minimum.
* Clean continuous integration build/test on master branch.
* No outstanding branches, commits, pull requests.

## Policies

* Master is never broken.  If broken, it is fixed immediately.
* Continuous integration always builds and tests successfully.
* Tests are written before/with code.  
* All changes are built and tested before they are committed.
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the affected code.

## Plan 

User stories (epics) in the Sprint Backlog: *#45, #46, #47, #48*.  

Total planned tasks / issues in the Sprint Backlog: *25* 

## Daily Scrums

Team Member | Date | Tasks done this time | Tasks done next time | Impediments | Confidence
| :--- | :--- | :--- | :--- | :--- | :---
N/A |9/18/2017 | Discussed task delegation and forthcoming issues during sprint| N/A | None|High 
N/A |9/20/2017 | Discussed hurdles with nearest neighbor path, grid calculations, column placement| Prepare for midterm| None|High
Charles | 9/20/2017 | Displaying all relevant data in website | Passed data fields to create table from data in website | Time | High
Charles | 9/23/2017 | Expanded data structures for javascript | Recognizes header categories from input CSV file | Time | High
Michael | 9/25/2017 | Finished #56, and spent some time fixing maven issues | Finish nearest neighbor Algorithm | Time; we're winding down in this sprint | High
Leif | 9/25/2017 | Fixed SVG file output | Fix Cartesian to SVG equation, replace brewrieslist with Point | None | High
Will | 9/25/2017 | None | Add checkboxes to website | None | High
member | 9/25/2017 | issues done | issues to do | impediments | confidence
Charles | 9/26/2017 | Finished bringing data structures to javascript | Passing arraylists to js | Time | High
Leif | 9/27/2017 | Fixed SVG path, per charles reccomendation | use modeldata instead of brewries list | None | High
Michael | 9/27/2017 | Finished algorithm implementation | Construct more thorough tests, review and finalize | Time | High
Charles | 9/27/2017 | Fixed dynamic column rendering and checkboxes | Made small adjustments to get it to render properly | Time | High
Charles | 9/27/2017 | Fixed Absolute Path | Changed an absolute path to a relative one to fix some build errors | Time | High
member | 9/27/2017 | issues done | issues to do | impediments | confidence
member | 9/27/2017 | issues done | issues to do | impediments | confidence

## Review

#### Completed user stories (epics) in Sprint Backlog 
* *#45* Cumulative distances
* *#57* Compute nearest neighbor for any given leg
* *#56* From starting ciy, compute nearest neighbor
* *#47* 
* *#46* Useful Information in Itinerary
* *#52* Data Structure for CSV

Completed *4* tasks / issues with these tasks

#### Incomplete user stories / epics in Sprint Backlog 
* *#46*: Dynanmic data doesn't relate to the column that it's in

#### What went well
* Constructive collaboration that ensured the success of the sprint.

#### Problems encountered and resolutions
* Demo computer gave issues, Charles acted quickly to resolve the situation with success

## Retrospective

Topic | Teamwork | Process | Tools
:--- | :--- | :--- | :---
What we will change this time | We worked together more than last sprint | We did do an overview of the project and all of its components, albeit it a bit late | More github activity 
What we did well | Collaborating and communicating | Helped each other overcome development issues | Working under adverse conditions when demo computer failed
What we need to work on |  Coming together to discuss the sprint earlier | More scrum involvment | Ensuring demo computer is double checked
What we will change next time | Focus on Scrum and structure, and better communication channels | Individually write more tests | Have two demo computers ready to go in case of tech failure
