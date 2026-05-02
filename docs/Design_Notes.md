# Design Notes

## Why ArrayList instead of Array?

Arrays in Java have a fixed size decided at creation time. For a student management system
where the number of students is unknown and grows over time, a fixed array would require us
to predict the size upfront, or write complex resizing logic manually.

`ArrayList` is a dynamic, resizable list backed by an array internally. It grows automatically
as elements are added, provides helpful methods like `add()`, `remove()`, and `size()`,
and works naturally with Java's for-each loop. For this project where all data is in-memory
and the collection size is unpredictable, `ArrayList` is clearly the right choice.

---

## Where Static Members Were Used and Why

Static members belong to the *class*, not to any specific object instance.

In `IdGenerator.java`, the counters `studentIdCounter`, `courseIdCounter`, and
`enrollmentIdCounter` are static because there should be exactly one counter for the
entire application — not one counter per `IdGenerator` object. The methods
`getNextStudentId()` etc. are also static so they can be called as `IdGenerator.getNextStudentId()`
without creating an object, which makes the intent clear.

In `AppConstants.java` and `MenuOptions.java`, all fields are `static final` (constants)
because they are fixed values shared across the whole application and never change.
Making them static avoids creating unnecessary objects just to read a constant.

---

## Where Inheritance Was Used and What Was Gained

`Student` extends `Person`. The `Person` class holds fields common to any person in the system
(`id`, `firstName`, `lastName`, `email`) and a `getDisplayName()` method.

By inheriting from `Person`, `Student` gets all these fields and methods for free without
duplicating code. If a `Trainer` class were added later, it would also extend `Person`
and immediately have all personal fields available.

`Student` overrides `getDisplayName()` to include the batch name, demonstrating polymorphism —
the same method call can produce different output depending on which class you are working with.

The use of `super(id, firstName, lastName, email)` in `Student`'s constructor shows how
child classes delegate initialization of inherited fields to the parent constructor,
keeping each class responsible only for its own new fields.
