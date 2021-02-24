# SDLC = Software Development LifeCycle
Process for planning, creating, testing, and displaying information systems.

## General Steps
1. Requirements Phase
    - Existing system is evaluated so that the existing flaws can be determined
    - Performed by Business Analysts (BAs)
    - Could be as simple as saying "The current flaw is that it doesn't exist"
        - Move on to outline what is needed
2. Analysis Phase
    - The new system requirements are defined. In particular, deficiencies are addressed with proposal for improvement
    - BAs + Senior members of the team
3. Design Phase
    - The proposed system is actually designed
    - No coding is done yet, the product features are planned out
    - Architects and Senior Devs
4. Development Phase
    - Software is built
    - Code for the product is actually written
    - Junior & Senior Devs, etc
5. Testing Phase
    - Software is tested to ensure it functions as intended
    - Devs & Testers
6. Deployment & Maintenance Phase
    - Product is delivered/deployed to the customer
    - Maintenence is rigorously kept up
    - Operations Team / DevOps Engineers / SREs

This is a high level description of what steps we take during the SDLC. The specific details will often vary, depending on which implementation you use to follow the SDLC.

## Waterfall Method

Is a traditional way of following the SDLC
The idea is that you completely finish one phase of the before moving on to the next
You do not return to any previous phase at any point (until the cycle is complete)

- Progress is seen as only going "downwards"
- Best suited for smaller, short term projects or where project requirements do not change (such as Government Projects)
- Generally considered not as efficient money-wise
- Cannot go back up the waterfall
    - Considered inflexible
- The benefits are that you get a full outline of the plan before you start
    - This can really help in terms of organization

## Agile Method

- Another method for following the SDLC
- An approach to Software Development based on iterative development where requirements and solutions evolve through the collaboration of cross-functional teams
- Agile is more of a mentality or a philosophy
    - It is not "a thing that you do", but more of a mindset or an approach to software development
- Four core values to Agile (From Agile Manifesto)
    1. Individuals and Interactions over processes and tools
    2. Working software over comprehensive documentation
        - We are not "abandoning documentation"
        - Documentation is still incredibly important
    3. Customer Collaboration over contract negotation
    4. Responding to change over following a plan

- There are Agile methods (often called frameworks), which are comprehensive approaches to the SDLC
    - "Scrum" is the most common Agile Framework
        - Not the only one
        - (SAFe = Scaled Agile Framework)
- Agile practices (listed below) are closely tied to DevOps
    - CI/CD or CI/CD/CD
    - Continuous Integration (CI)
        - The idea or process or practice of continuously merging multiple developer's code (usually in a repository) *frequently*
        - This should be MULTIPLE times a day. It is meant to prevent large errors from accumulating. The sooner you find them, the sooner they will be fixed
    - Continuous Delivery (CD)
        - Automate the testing of your code, and the process we use to prepare our code so that we can deploy the code "at the push of a button"
        - Generally, we have a big "release day"
    - Continuous Deployment (CD)
        - Every change that passes all stages of the product pipeline (various tests, quality checks, etc) is released to the customer immediately. There is no "release day".
        - This is particularly valuable because it speeds up feedback from the customer

## Agile Scrum Methodology
- In a "Scrum", the project is divided into what are called "sprints"
- Sprint
    - A specified timeline (generally 2 weeks, but could be 1 - 4 weeks)
    - Timeline is agreed upon during a "Sprint Planning Meeting"
- User Story
    - Informal, natural language sentence/description of one or more features
    - It is usually written from the perspective of a user
        - "As a User, when on on the login page, I can enter a valid username and password, and be redirected to my profile page"
    - These are agreed upon and split during the Sprint Planning Meeting
- Epic
    - A large body or collection of user stories that can be broken down further

### Roles
- Scrum Master
    - Facilitator for the Scrum devleopment team
    - Clarifies questions and organize the team and focuses on the return on investment
- Product Owner
    - Usually the client and they will act as a point of contact from the client side
    - Prioritize the product backlog and when the scrum team should finish and release
- Scrum Dev Team: Devs & QA
    - This is the team that decides "effort estimation" to complete a product backlog item
    - Often uses "User Story Points" or just "Story Points" to describe an amount of effort for a user story
- Scrum Team
    - Product Owner + BAs + Devs + QA + Scrum Master
    - Recommended Size is 5 - 9

### Artifacts
- Product Backlog
    - This is a repository where the items that product owner wants to accomplish are kept
    - Trello, Asana, JIRA, etc
        - Some Kanban Board
- Sprint Backlog
    - A subset of the product backlog that contains the items to be completed during the current sprint
- Burndown Chart (Burnup chart)
    - Graph that shows how many user stories (or story points) are left to complete during the sprint
        - Burnup chart would be how many are completed so far
    - Often use this to calculate the overall "velocity" for a sprint
    - Velocity is the number of story points completed over time
        - Could be total story points completed per 2 week sprint

### Meetings
- Sprint Planning Meeting
    - Plan for what you will accomplish during that sprint
    - Can be several hours in some cases
- Daily Standup Meeting
    - Short meeting (like 15 minutes at most) that happens once a day for the scrum team
    - You talk about what you did yesterday, what you plan to do today, and if you have any "blockers"
        - A particular person or group who you are waiting on
    - Generally led by the Scrum Master
- Sprint Review Meeting
    - Happens at the end of a sprint
    - You basically showcase the features that were accomplished during the sprint
    - Decide if the product is complete or if it needs more work
- Sprint Retrospective Meeting
    - The scrum team meets and talks about what went well and what went poorly
    - What can be done to improve?
    - What did you learn?
    - etc