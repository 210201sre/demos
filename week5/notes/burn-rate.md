# Burn Rates

Once we have a defined SLO, we are able to determine what availability target we have for some period of time. Let's say for example, we aim for 99% successful requests.

This allows us to have an approximate downtime of 7.2 hours in a 30 day period. This is assuming that all requests are successful when the service is "up" and all requests are failures when the service is "down".

This is a major simplification, but it allows us to discuss the concepts more clearly.

If our service is "down" (serving errors) for 14.4 minutes every day, then by the end of a 30 day period, we will have accumulated 7.2 hours of downtime, which is the entire Error Budget.

We say in this case that we have a "Burn Rate" of 1. At this rate, by the end of our SLO Duration (generally 30 days), we will have consumed 1 times our Error Budget. Or thinking in a different perspective, we have 100% of our SLO Duration before we run out of our Error Budget.

If we instead had 28.8 minutes of downtime every day, we would have a burn rate of 2.
This means that by the end of the 30 days, we would have consumed twice our error budget. Or alternatively thinking, we will consume our entire error budget in 50% of our SLO Duration.

Higher burn rates indicate more severe outages.
If we have some issue that is causing our Burn Rate to be 1, then it is an issue that we need to solve, but it isn't so major that we must wake an engineer up in the middle of the night.

However, with higher burn rates, there comes a time where we might want to wake someone up to fix the issue.

For example, if our burn rate is 15, then we will run out of our Error Budget in 2 days. This rate of consumption is so severe, that we should interrupt someone to fix the issue as quickly as possible.


We can define different severities based on our burn rates. With a burn rate of 1, the severity is low and so can likely just create a ticket that is added to a system such as JIRA.

A more severe category could have us send an email with the issue.
Past that, perhaps we should start paging engineers. Many of you are already familiar with what pagers are. We don't necessarily need a physical pager, but the concept still works well.

We can use software tools such as PagerDuty, that allow us to page engineers on their phones/emails or what have you (phones are most common).

The idea is that we want to alert engineers about the issues in a effective manner.
- Avoid alerting too much
    - Alerts should indicate "user pain"
- We want to know if the end user is affected
    - Examples could be 500 HTTP Status Codes being returned
    - Higher latency rates
    - Other forms of degraded service
- There are more best practices
    - Such as having effective output channels (appropriate severities)

Google has analyzed a few different approaches to alerting based on relevant metrics that tie in considerations such as Detection time and lengthy alerts, and more.

Ultimately, they have found that Multi-Window Multi-Burn Rate Alerts work the most effectively. As such, this is the approach that we will be leveraging in training.