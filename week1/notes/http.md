# HTTP

HyperText Transfer Protocol. It is a protocol to send information in some meaningful way. It is generally used to send information across the internet.

HTTP has a Request/Response.

## Request

- Request Headers
    - General metadata about the request
- URL
    - Destination
    - Where are we sending this request
- Query Parameters
    - Additional information specifying the request in some way
- HTTP Version
- Request Body
    - The core information that the request
- HTTP Method/Verb
    - Clarifying the goal of the request
    - GET
    - POST
        - Send information
    - PUT
        - Trying to update information
    - PATCH
        - Similar to PUT, except only a partial update
    - DELETE
        - Delete some information
    - OPTIONS
        - Special Method, that is used for CORS
        - Am I allowed to share resources with this destination
    - HEAD
        - Get information about the Headers

## Response

- Response Headers
- HTTP Version
- Response Body
- Response Status Code
    - 1XX Series
        - Informational
    - 2XX Series
        - Successful
        - 200 OK
        - 201 Created
        - 202 Accepted
        - 204 No Content
    - 3XX Series
        - Redirections
        - 300 Multiple Choices
        - 301 Moved Permanently
        - 307 Temporary Redirect
        - 308 Permanent Redirect
    - 4XX Series
        - Client Side Error
        - 400 Bad Request
        - 401 Unauthorized
        - 403 Forbidden
        - 404 Not Found
        - 418 I'm a Teapot
    - 5XX Series
        - Server Side Error
        - 500 Internal Server Error
        - 501 Not Implemented
        - 502 Bad Gateway
        - 503 Service Unavailable