# Sprint 5 - T25 - NO NAME GROUP

## Goal

### Reliable first release with clean code!

## Definition of Done

* Ready for demo / customer release.
* Sprint Review and Restrospectives completed.
* Product Increment release `v5.0` created on GitHub with appropriate version number and name, a description based on the template, and an executable JAR file for the demo.
* Version in pom.xml should be `<version>5.0.0</version>`.
* Unit tests for all new features and public methods at a minimum.
* Coverage at least 50% overall and for each class.
* Clean continuous integration build/test on master branch.

## Policies

* Tests and Javadoc are written before/with code.  
* All pull requests include tests for the added or modified code.
* Master is never broken.  If broken, it is fixed immediately.
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* Continuous integration always builds and tests successfully.
* All commits with more than 1 line of change include a task/issue number.
* All Java dependencies in pom.xml.


## Metrics

Statistic | Planned | Completed
--- | ---: | ---:
Tasks |  21  | *value* 
Story Points |  67  | *value* 


Statistic | Start | End
--- | ---: | ---:
Overall Test Coverage | 36% | *value* 
Smells | 14 | *value* 
Duplication | 27 | *value* 
Technical Debt Ratio | 16% | *value* 

## Plan

Epics planned for this release.

* Test coverage(Does not include travis database testing).
* Clean code.
* Map with zoom and pan.
* Optimization selection.
* Trip destinations.
* Select a starting location. 
* Fix sprint 4 issues.

## Daily Scrums

Date | Tasks done now | Tasks done next | Impediments | Coverage | Smells | Duplication | Technical Debt Ratio
:--- | :--- | :--- | :--- | ---: | ---: | ---: | ---:
11/30/17 | #145, #146, #207 | #208, #209 | High | 36* | 14 | 27 | 16%
12/4/17 | #144, #145, #205, #193, #202, #219 | #136, #210, #132, #212, #204, #133, #142 | Medium | 35* | 27 | 48 | 14%
12/6/17| #212, #210, #148, #199, #207, #208, #209 | #196, #206, #201, #197, #203, #132, #142 | Low | 45* | 27 | 48 | 14%

*Doesn't include database coverage
 

## Review

#### Completed user stories (epics) in Sprint Backlog 
* Test Coverage: Increased overall coverage
* Clean Code
* Trip Destinations
* Optimization Selection
* Map with zoom and pan

#### Incomplete user stories / epics in Sprint Backlog 
* Select a starting location: Ended up not having enough time
* Add Reorder: Same as above

#### What went well
* No huge roadblocks, steady development


#### Problems encountered and resolutions
* Hidden bugs, which were solved once we got everything together
* Unsolved problem with 3-opt, some crossed lines

## Retrospective

Topic | Teamwork | Process | Tools
:--- | :--- | :--- | :---
What we will change this time | N/A | N/A | N/A
What we did well | Communicated effectively, worked together and tackled issues as a team | Problem solved methodically | Utilized multiple machines to work together on different tasks 
What we need to work on | Making larger contributions throughout the sprint | Streamlining the testing process | Making sure everyone has the tools to test coverage, and the remote server
What we will change next time | N/A | N/A | N/A
