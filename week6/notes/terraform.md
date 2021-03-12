# Terraform

Terraform allows us to configure our infrastructure in the same way we work with the rest of our source code.

It follows the idea of "Infrastructure as Code". Very similar principle to "Configuration as Code" with Jenkinsfiles.

Infrastructure as Code is an even more low level representation than Configuration as Code. Configuration as Code defines how we use different tools, whereas Infrastructure as Code defines which tools we will even use, and how many of them.

Together they both work to eliminate more aspects of human error, but still allows the system to be evolved over time.

## How Terraform is Structured

Terraform takes a view about Infrastructure similar to how Kubernetes views it's environment. Terraform compres a real world scenario (what actually exists) and compares it to a "Desired Configuration".

This "Desired Configuration" is outlined in a Terraform Config file, written in their Terraform language.

4 Phases
- Refresh
    - Captures the current state of the infrastructure and saves it locally
- Plan
    - Compare the "Desired Configuration" from the config file with the reality of the infrastructure, and computes a series of actions that must be taken to convert the current infrastructure into the desired infrastructure
- Apply
    - Applying the changes outlined in the plan from the previous phase
    - This should achieve the Desired Configuration
- Destroy
    - Removes the infrastructure components that were associated with the current Terraform Config
    - The Terraform Config file does not necessarily include every single infrastructure component on the backend
    - We only remove the few pieces that are associated with _this_ configuration

## Cloud Agnostic

The idea is that it does not matter which cloud providers are being used. You can use any cloud platform to accomplish the ultimate goal. For example, you could have most of your infrastructure in GCP, but have a Kubernetes Cluster in Azure, that interacts with the rest of your infrastructure in GCP.

This has benefits in that you can choose whichever components are the best fit or the best value across all of the different competing cloud platforms.

However, this does add many layers of complexity for the interaction of the infrastructure components.
We saw with AWS, that there are many integrations provided when working with services inside the same cloud platform. But this would not be the case for cross-platform.

Terraform, with its many different providers, could allow us to take a Cloud Agnostic approach when designing our infrastructure. However it is not a decision to be taken lightly. The added complexity can be quite severe in some cases.