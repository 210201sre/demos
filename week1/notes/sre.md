# Site Reliability Engineering

A Site Reliability Engineer is responsible for maintaining the production state of a service. We work together with application developers to spend the Error Budget to perform releases. SREs will spend some of their time performing "toil". Which is a description for mundane tasks. Generally toil is repetitive, it is manual, it is lacking any enduring value. The remainder of an SRE's time is spent engineering automated processes to improve the lasting reliability of a service.

## Definitions

- Service-Level Objectives (SLO)
    - Target goal/objective for the uptime of a service
    - Percentage of time that the service must be available
    - Over timespans of a few ranges
        - Monthly
        - Quarterly
        - Yearly
    - It is NOT 100%
        - If you are working for some government contract, you might not be able to have complete downtime
        - But might be able to support degraded service
    - 99% or 99.99% or some amount of 9s
- Service-Level Agreements (SLA)
    - A target uptime for a service that you enter into a contract with someone else who uses the service
    - Generally speaking, the uptime agreed upon, is lower the SLO
    - There are penalties outlined for breaking the agreement
- Service-Level Indicators (SLI)
    - Metrics that we use to calculate the uptime/availability of a service
    - Calculate the ratio of successful requests to the total number of requests
    - The ratio of requests that are processed within a certain target time (300 ms)
    - Represented as a percentage

- Error Budget
    - The allowed downtime for a service based on the SLO.
    - 100% - SLO = Error Budget
    - Generally over timespans of a few ranges
    - We spend the Error Budget to release new features
        - To make changes to the production system