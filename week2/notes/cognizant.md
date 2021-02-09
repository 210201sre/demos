# Categories to address with a focus on Resilience & Reliability
- Performance
    - Individual workflows are meeting their individual requirements
- Scalability
    - Can scale to whatever user-base or throughput that is required
- Availiability
    - High-Uptime
- Resiliency
    - Fault-Tolerance
- Reliability
    - Consistency is key
    - Whatever the behavior is, the results should be consistent all the time

## Characteristics of Good Employees
- Able to learn new things
    - Ask for help if needed, but don't need to be completely guided step by step
- Try to be detail-oriented, and achieve a thorough understanding of the services that you are maintaining

## Need for SRE for Digital Businesses
- Developers want to develop features and throw new features to Operations team after perhaps a little testing
- Then Operations has to deploy the application with whatever configuration is needed, and keep it running
- There was always a gap between the teams
    - They would not understand the challenges/perspective of the other team
- Customers want new stuff all the time
    - They expect things to happen quickly
    - Regardless of Industry
- In order to support these requirements, developers need to develop/deploy very quickly
    - But were not able to due to the wall between the teams
- Operations only want to keep the application up and running
- High profile outages are generally caused by some changes introduced to the production system
    - So Operations team is against changing the production app, since it can bring the service down
- SRE sits between Devs and Operations
    - Help Devs in terms of building applications with a focus on best practices in regards to reliability (e.g. fault-tolerance, etc)
    - Help design the needed architecture to ease deployment
    - Focus on Observability
    - Post-Mortems in regards to Incident Management w/ Operations
        - Why did the bad thing happen?
        - Come up with an action-plan to prevent that from happening again
    - Breaks the wall down between teams

# Questions
- What growth do you see in smaller projects that don't generally need scalability/reliability?
    - To clarify, even smaller projects *do* need to keep non-functional requirements in mind
    - If one service has poor non-functional performance, users can always choose a competitive service
        - Even for very small scale businesses
    - These non-functional requirements directly impact the success/failure of a production service, regardless of scale
        - Primarily due to the expectations that customers have in modern days
    - In conclusion, there is not really a distinction between smaller/larger projects in terms of growth of SRE
- What kind of size do teams have?
    - SRE works as part of the entire product lifecycle
    - From inception and onwards
    - During requirements analysis, help design non-functional requirements
    - Help during design to factor in reliability
    - Fine tuning code that is developed
    - During testing cycle, help team execute performance tests
        - Leverage Chaos Engineering
        - Outline different failure scenarios
    - Also help with alerting and incident management
    - In conclusion, whatever the client is asking the team to do, will impact the size of the team
        - Minimum 5 up to 20 - 25 for interacting with system for entire lifecycle
        - Could have 2 - 3 members for an SOS scenario
- How does this relate to our batch? Will we be all one group?
    - Unlikely to all be one group
    - Could be sent to any client, but not be alone
    - Will work with a more senior member
- Any recommended certifications?
    - There are some course materials available, but don't have a dedicated cert for SRE
    - No standardized certification overall, but there is an internal certification test structure internally within Cognizant