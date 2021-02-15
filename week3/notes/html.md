# HTML

HyperText Markup Language. It is a "Markup" Language that defines the structure of a web-page.

A Markup Language defines rules for a document that uses "tags".

XML = eXtensible Markup Language, is similar to HTML in that they both use these tags.

These Markup Languages are NOT programming languages. They simply define some information. XML is used often to transmit data/information or to specify configuration files.

HTML is similar, in that it defines the structure of a web-page.

The first line of an HTML document is the DOCTYPE declaration: `<!DOCTYPE html>`. This is a declaration that defines what version/markup rules are used for this document. The default is HTML 5.

## Tags

- `html`
    - Root tag of the html document
    - `head`
        - Metadata for the webpage
        - `title`
        - `meta`
            - Miscellaneous Attributes
            - `charset`
            - `viewport`
        - `link`
            - Link to external "sources" for the webpage
            - Link to css
    - `body`
        - The primary tag that will contain most of the information for a web-page
        - All of the content that will be displayed will be included here
        - `h1` through `h6`
            - Different sized headings, from biggest to smallest
        - `a`
            - anchor tags, which create hyperlinks
            - destination url will be in an attribute
            - Text shown will be in the innerHTML
        - `p`
            - paragraph tag
            - Content will be included in the innerHTML of the tag
        - `div` & `span`
            - Containers for separating/organizing the contents
                - Generally for styling
            - `div` is Block-Level
                - Takes entire width available
            - `span` is in-line
                - Takes only the width it needs
    - `footer`
    - `script`
        - Allows you to directly write JavaScript Syntax
        - Also use the script tag to link to other javascript source files which will be executed

## Semantic Tags
Tags that do not necessarily have any visual content to render. They are simply there to help with organization.

## Lists

- `ol`
    - Ordered Lists
    - Numbered/Indexed Entries
- `ul`
    - Unordered Lists
    - Bullet Points
- `li`
    - List Item

## Tables

- `table`
    - `thead` (Semantic)
    - `tbody` (Semantic)
    - `tfoot` (Semantic)
    - `tr` Table Row
    - `th` Table Heading
    - `td` Table Data (a single entry)

## Attributes

There are many properties that can be added as metadata to indiviudal tags. These are called `attributes` and are added in the opening tag of an "element".

Note: Element is another term for "tag". Nuances are that "tag" refers to the html syntax, and "element" refers to the rendered content on the page, from that tag. For the most part, they can be used interchangeably.

Some are universal
- `id`
    - Similar to IDs in DBs, which should be a unique identifier for an element
    - Technically, the HTML will still render if you re-use an ID, however, the second tag with the same ID will not be able to be properly referenced with that ID
        - HTML will find the first occurrence of an ID
- `class`
    - Identifier to group several elements together
    - Often used for CSS (Cascading Style Sheets)
        - You can create styling rules that apply to specific classes
    - Can have more than 1
        - Separate them with a space
- `style`
    - Allows us to define styling for a specific element, inline
    - Using CSS properties
    - [CSS Property Reference](https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Properties_Reference)
- `name`


## CSS

We can have separate files that detail out a stylesheet for a webpage.

It uses CSS Selectors along with CSS properties to style a web-page.

- Selectors
    - Different means by which to "select" elements on the page to apply style rules to
    - `Element Selector`
        - Allows to select an entire class of elements
        - Apply these rules to every element that is of this tag
        - `h1`
        - `p`
        - `body`
    - `Universal Selector`
        - Uses `*`
        - Apply these rules to everything
    - `Class Selector`
        - Uses `.` as a prefix for a classname
        - `.myclass`
        - Apply these rules to every element with this class
    - `ID Selector`
        - Uses `#` as a prefix for an id
        - `#myid`
        - Apply these rules to the specific individual element that has this ID
    - `Attribute Selector`
        - Uses `[]` syntax with a tag
        - Apply these rules to the specific element with a corresponding value for a tag
    - There are many more
        - `Psuedo Element Selector`
        - `Ancestor Selector`
            - `div` > `p`
        - `Sibling Selector`
            - `h1 p`

CSS can be inluded in 3 ways: Inline, Internal document, and External document

- Inline: Uses `style` attribute
- Internal Document: Uses `style` tag
- External Document: Uses `link` tag (With `rel="stylesheet"`)

### Syntax

```css
selector {
    property: value;
    propert2: value;
    .
    .
    .
}
```

```css
* {
    background-color: blue;
}

body {
    margin: 20%;
}
```

## Forms

HTML Forms allow us to receive user input. Uses the `form` and `input` tags.

[Input types Reference](https://www.w3schools.com/html/html_form_input_types.asp)

```html
<form method="" action="">
    <input type="text">
    <input type="password">
    <input type="checkbox">
    <input type="radio">

</form>
```

The `method` attribute defines an HTTP Method (Generally, GET or POST), and the `action` attribute defines the url that the request will be sent to.

When a GET request is used, the form parameters are included as Query Parameters.
When a POST request is used, they are stored in the body as either `form-data` or `x-www-form-urlencoded`.

These can be changed. When you submit a form, the webpage will reload. The response of the request will act as the new webpage to display. This means that in the response of your server, you would not send back JSON, you should instead send back HTML. This is most often used with Server-Side Rendering backends, using technologies like Jinja Templates.

In general, if you want to send data from a web-page without reloading the page, you would use JavaScript. In particular, there are some APIs such as AJAX, that allow you to do this. This type of request generally will send/receive JSON/XML.

## Context

As we are SREs, we may not spend much time actively developing webpages. Instead, our focus will be to deploy them.

In general, static html/js/css files can be included as part of a Web Server that can be served to GET requests. For Tomcat, we would put this in its `webapps` folder. Often, there is a sub-folder called `STATIC` or `ROOT`, but not always.