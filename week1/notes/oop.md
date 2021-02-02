# Object Oriented Programming

Object Oriented Programming focuses on the concepts of Classes and Objects. Where objects are representations of real world objects, in code. Classes are blueprints for these objects.

We define Objects as having state and behavior: properties/characteristics/fields as well as methods/functions.

Object Oriented Design has 4 pillars: Abstraction, Polymorphism, Inheritance, and Encapsulation (A-PIE)

## Abstraction

You do not show the underlying details, you only show what is needed.
This is accomplished occasionally with private methods, that contain complex logic that isn't important. As well as through the use of interfaces and abstract classes. In general, we combine this pillar with the pillar of Polymorphism to hide away the underlying details, and only show the contract to interact in a particular way (such as with the List interface).

## Polymorphism

Polymorphism as an English word means to take on many forms. More specifically, it means an Object can take on different forms/characteristics. An Object can take on the form of any Class within its inheritance heirarchy. An Example would be that a Rabbit can take on the form of the Animal Class.

There are additionally 2 techniques we can use to accomplish Polymorphism: Method Overloading and Method Overriding.

Method Overriding is when a subclass replaces the implementation of a parent class's method, generally with a more specific implementation. The subclass must have the exact same method name and parameters (together are called the "method signature"). The return type can be changed on a limited scope. If the parent class's method had an Object as the return type, the subclass may change the return type to be a subclass of the original return type. For example, if the parent class returned an `Object`, the suclass could change the return type to `Animal`. However, if the parent class's return type was `void` or a primitive, the subclass cannot change it.

Method Overloading is when you define multiple methods with the same name, but with a different method signature (in particular, this means there will be a different list of parameter types). You cannot overload a method by simply having a different return type. However, overloaded methods may have different return types. Under the hood in Java, overloaded methods are simply independent and unrelated methods. They may have different types because of this.

When we declare multiple Constructors, this _is_ Method Overloading, or Constructor Overloading.

Method Overriding is referred to as "Runtime Polymorphism". Method Oveerloading is referred to as "Compile-time Polymorphism".

Another technique that ties into Polymorphism is called "Covariance" or "Covariant Return Types".
This where the reference variable is of a different type from the Object it points to. This ties into the pillar of Abstraction as well, since we can effectively hide the details of the specific subclass if it is not important.

## Inheritance

The pillar of Inheritance stipulates that Classes are able to inherit properties and behaviors from a Parent class. In particular, subclasses are "more specific" versions of a parent class. Examples might be inheriting from a `Animal` class to define a `Dog` class. Or inheriting from a `Person` class to define a `Employee` class.

In Object Oriented Design, there are different types of Inheritance, such as "Multiple Inheritance" or "Multi-Level Inheritance". Multiple Inheritance is that a single class can inherit from multiple parent classes. Multi-Level Inheritance is that inheritance can chain across multiple generations. For Example, `Animal` - `Person` - `Student` - `MathStudent`

Java supports Multi-Level Inheritance, but does not support multiple Inheritance for classes. However, interfaces do support multiple inheritance. This is allowed through the various restrictions placed on interfaces. For example, interfaces can only have `public` and `abstract` methods (or must use the `default` or `static` keywords).

## Encapsulation

The idea of Encapsulation is that Objects should restrict access to the data (state/properties) to be used in proper ways. For example, if an object has an `age` property, it should restrict access so that this property cannot be set to a negative value.

We accomplish Encapsulation through the use of the 4 access modifiers (`public`, `protected`, `private`, and `default`) along with public getter/setter methods to control the manipulation/access to the data.