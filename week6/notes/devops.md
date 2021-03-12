# DevOps

DevOps is a blend between CS and IT. In the context of SREs, you're the bridge between Deveopers and the production environment. Not strictly application developers or purely operations of the production environment. SREs act as the in-between.

To start off, the term "DevOps" comes from the combination of two teams: Development and Operations.

The Development team focused on developing an application. They are interested in creating new features to be used by the end users.

The Operations team were the IT part, focusing on the production environment, and making sure that the application that was developed is actually available to the end users.

Historically, there has been a lot of friction between Development and Operations teams. The Development team wants rapid changes and high velocity, whereas the Operations team wants a static production environment to reduce the chances of any change dropping the availability of the application.

Due to this difference in perspective, Operations teams tended to enforce miscellaneous requirements on the Development team, which lead to poor programmming practices, such as feature flags/switches, amongst others.

This can lead to some unforseen issues.

When combined with manual deployment processes that are prone to human error, to lead to massive outages and losses of money.

DevOps has the three practices: CI/CD/CD to _automate_ our deployment processes to be as free from human error as possible.

- Continuous Integration
    - The practice of frequently (multiple times a day) merging multiple developers code together
    - Prevents smaller errors/issues from piling up
    - Solve issues when they are still small
- Continuous Delivery
    - Automating the deployment process, so that each commit will be tested and prepared for deployment
        - As well as any other step that you want to be part of the process or "pipeline"
    - The release is ready "at the push of a button"
        - The developers do not have to take any action to deploy the new release/version, aside from effectively hitting a button
    - Involves big "release days"
- Continious Deployment
    - Relates to the "Facebook Model"
    - Every change/commit that successfully passes every stage of the Continuous Delivery Pipeline (listed above) will immediately be released, without waiting for any approval/button press
        - Similar to Continuous Delivery, except even the "button press" is additionally automated
    - With this fully automated process, it becomes more beneficial to push changes very frequently, as you can feedback from your customer as soon as possible

## Continuous Delivery vs Continuous Deployment

For Continuous Delivery, we want each of our commits/pushes to go through a series of steps of what we call a "pipeline". Each step will consist of something like testing, code quality analysis (sonarcloud), amongst a variety of others.

At the end of our pipeline, we should prepare our release, so that the only action we need to take to fully release this change is with a very simple action. It could be, responding to a prompt (yes/no). It could be executing a script. It could be sending an HTTP Request, or any other variety of action. But the key characteristic, is that this action has no complexity to it. There is easy way for human error to prevent this from functioning as expected.

For Continuous Deployment, we want to not have any interaction with the system or pipeline in order to allow this release to occur. We want to design the system to make its own decisions about the release process. For example, it could just automatically assume the prompt is "yes". It could automatically execute the script, etc.

Continious Deployment will have every change that successfully passes through the pipeline immediately deployed, without human interaction.
This is not to say that Continuous Deployment cannot have human interaction. It is perfectly reasonable (and even suggested) that you can have abort methods. We should have the ability to interrupt the system if needed. But generally speaking, it will operate without human interaction.

## Why would we want Continuous Deployment?

It is perfectly reasonable to point out that Continuous Deployment might lead to higher issues with the production system. The benefits are in terms of velocity of release and the immediacy of user feedback to changes of the system.

It is impossible to act as Facebook does, with releases happening on average every 15 minutes, with a Continuous Delivery process alone.

To combat the downsides, we don't actually mind if our system is not perfect. Our SLOs are not 100% anyways. As long our process can meet our SLOs, we are perfectly happy with a bit of downtime.
There is a problem if this Continuous Deployment process results in failing our SLOs. Which is why we need to engineer our system to rapidly and effectively roll back changes while impacting our SLOs as little as possible.

This is additionally why a full Continuous Deployment process is not always trivial. Production environments are large, with many moving components.


## Deployment Strategies

We already saw that Deployments in Kubernetes support a default rollout strategy of "Rolling Deployment".
This means that the series of Pods associated with the Deployment are updated to a new release in a "rolling" manner. With a 25% surge/drop. The idea is to release these changes without interrupting service or impacting "capacity".

"Capacity" is referring to the total bandwidth of requests that we can respond to. Vertical and Horizontal Scaling increase our "capacity". We also plan ahead for how much capacity our system is predicted to need, which we refer to as "capacity planning".

There are other common strategies, of which we will discuss 2: Blue/Green Deployments and Canary Deployments.

### Blue/Green Deployments

The idea is that you keep 2 copies of your production environment, labelled "blue" and "green".

The current production system is in only 1 of the environments at a time. It is either in "blue" or "green".

When you make a new change, you release to the environment that is not in use. If the production system was in the "green" environment, you would release the next version in the "blue" environment.

Then, you steadily monitor the system, as you shift traffic over to the new release, a little bit at a time. If the new release behaves well, you ultimately fully shift traffic to the new environment. At the same time, you can steadily decrease the capacity in the original environment, as you steadily increase the capacity in the new environment.

### Canary Deployments

Similar to the idea of canaries in coal mines, we release new versions as a "canary".
We steadily route some traffic to the "canary", and monitor it. If it behaves well, we can increase capacity of the new version (or "canary"), and decrease capacity of the original version.

By the time we have full capacity to the canary, and all traffic is routed to the new version, the "canary" becomes the new production system.

In either Blue/Green or Canary deployments, if something is wrong with the new version, we rollback. This means we revert traffic to the original environment, or we remove the canaries.