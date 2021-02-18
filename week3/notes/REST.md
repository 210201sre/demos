# REST
- REpresentational State Transfer
- Represent everything on the server as a "resource"
- We also talked about the URI structure in a RESTful Web Application
- Chracteristics/Traits/Properties
    1. Stateless
        - No request should save information about a previous request
        - Partially violating this characteristic with sessions, but that is okay
        - Our applications can have a RESTful API in addition to some other features
    2. Cacheable
        - If requests for the same resources are repeated/frequent, then the server should cache the data to save on some performance
        - With Javalin/JDBC, we were not doing this, but with Spring Data, which is using Hibernate, we are
            - Hibernate contains a cache (Session-Scoped Cache)
    3. Uniform Interface
        - Primarily in reference to the URI structure along with the JSON/XML responses
        - Requests can be the sent to the server in a consistent manner
        - Different requests will have similar types of responses
    4. Layered Architecture
        - API can be structured as multiple servers that communicate to each other, but all of this is hidden from the user
        - The API is used in the same regardless of how many nested servers are used under the hood
    5. Client/Server Relationship
        - The API develops a relationship, generally through/tied with its uniform interface
        - Through the use of `url` properties and similar, the client is able to continuously use the API, even as resources are moved and other changes occur
            - We say that the client and server communicate and develop a "relationship"
    6. (Optional) Code on Demand
        - In some way/shape/form, the server should be able to provide "code" on demand
            - One idea is that the server can execute certain functionality when requested
            - A different idea could be that "code" itself is represented as a resource
                - Can be manipulated in all the ways any other resource can be

## Richardson Maturity Model (RMM)
- Leonard Richardson broke down the steps of creating a RESTful API into three steps/levels
0. Remote Procedure Calls (RPC)
    - Absolute Chaos
    - No RESTful traits
    - Any HTTP methods are used for any request
        - Using GET requests to send information
        - DELETE requests to obtain information
    - No attention is paid to Response Status Codes
        - Often 200 OK is used even under scenarios where it doesn't make sense
    - Often executing functionality instead of structured as "resources"
1. Resources
    - API is now structured as a collection of resources
    - Includes URI naming conventions
    - But, still little attention is paid to HTTP Methods and Status Codes
2. HTTP Verb
    - Now correctly uses context of HTTP Verbs/Methods
        - GET: Request a Resource (Generally doesn't have a Request Body)
        - POST: Send a resource/information to server (Generally does use the body)
        - PUT: Update a resource (Replacement of this resource, aside from ID)
        - PATCH: Update a resource (Partial Update)
        - DELETE: Deletes a resource (Generally doesn't use ID, but sometimes might)
        - TRACE: Returns the path the requests takes through the internet
        - HEAD: Returns the headers of the Request
        - OPTIONS: Used for CORS Pre-Flight Requests
    - There is a concept of Safe Requests & Idempotent Requests
    - They are not mutually exclusive
        - Safe: The request does not alter the state of the server
            - GET, HEAD, TRACE, OPTIONS
        - Idempotent: Repeating the operation in sequence will always have the same result
            - OPTIONS, PUT, GET, PATCH, DELETE, HEAD
    - Proper Status Code Responses are provided
3. Hypermedia Controls - HATEOAS (Hypermedia As The Engine Of Application State)
    - Providing hyperlinks and similar to control the application state
    - Information on how to use the API is included in the response
        - Ex: Think of the Poke API, with paging and sorting feature
        - `url` attribute
        - Alternatively create a `links` attribute
            - `href` or `src` underneath
    - Helps clients of the API to continue to work across changes

Let's say we sent an HTTP Post request to create a book resource:
The response could be:

```json
201 Created
{
    "resource": {
        "data": {
            ...
        },
        "links": [
            {
                "method": "GET",
                "href": "/books/5"
            },
            {
                "method": "DELETE",
                "href": "/books/5"
            },
            {
                "method": "GET",
                "href": "/author/book/5"
            },
            ...
        ]
    }
}
```

The RMM is a good way to get started with creating a RESTful API / RESTful Web Service, however it is not the end.

There are many more aspects that we can imporve.

For example, HTTP is not necessarily the only protocol that we can support.
We could expand the range of HTTP Verbs that support. We could support ALL HTTP Verbs.
You could add support for "Vary" headers, which allow you to customize responses based on other headers such as "Content-Type". You could send back JSON, XML, or something else.