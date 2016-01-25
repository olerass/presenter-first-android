# Presenter First (MVP) Android Examples

Examples of how to implement the [Presenter First][pf] pattern in Android based on the code from the book [Android Programming: The Big Nerd Ranch Guide (2nd Edition)][book]. Projects will be continually committed as I get further into the book.

All examples are targeting API level 22 (Lollipop 5.1) with a minSdk of 16 (4.1 Jellybean).

[pf]: https://atomicobject.com/resources/presenter-first
[book]: https://www.bignerdranch.com/we-write/android-programming/


## Get Started
The repository is organized according to the progression of the book with a single directory for each separate project. 

To build and run the projects you need both JDK 7 and 8 installed. This is a requirement of [Retrolambda][retrolambda] which is used to compile Java 8 lambdas to Android compatible Java 7 bytecode (see [Third-Party Libraries](#third-party-libraries)). If you use Java 8 to compile the code you must set the `JAVA7_HOME` environment variable and point it to the JDK7 location. If you use Java 7 to compile set the `JAVA8_HOME` variable and point it to JDK8.

## Projects Overview
### GeoQuiz - Q/A app
A simple app that introduces the basic Android concepts like activities, the activity lifecycle, intents, bundles and widgets.

| Chapter  | Description   | Screenshots |
| -------- | ------------- | ----------- |
| 1        | Single-activity app that displays a question. The user may answer true or false and will be notified whether the answer was correct or not. | <img src="chapter1-geoquiz/screenshots.png?raw=true" width="350"/> |
| 2        | Chapter 1 app expanded with functionality for showing 5 different questions and forwards-cycling through them using a next button. | <img src="chapter2-geoquiz/screenshots.png?raw=true" width="350"/> |
| 2(challenges) | Chapter 2 app with added functionality for cycling both back and forth between the questions. Furthermore, the navigation buttons now use images and touching the textview advances the question. | <img src="chapter2-geoquiz-challenges/screenshots.png?raw=true" width="350"/> |
| 3 | Chapter 2 app expanded with functionality for saving/restoring the active question across rotations by overriding `onSaveInstanceState` . | Same as chapter 2 app. |
| 5 | Chapter 3 app expanded with a second activity that allows cheating on a question to reveal its answer. Intents are used to pass data between the activities. | <img src="chapter5-geoquiz/screenshots.png?raw=true" width="350"/> |
| 5(challenges) | Chapter 5 app with added functionality that prevents users from cheating when cheating (!). This version ensures that the cheating status is saved across rotations. Furthermore it's saved for each question so that it's not possible to cycle through the questions to reset the status. | Same as chapter 5 app. |
| 6(challanges) | Chapter 5 app with an extra `TextView` in the cheat activity that shows the API level of the device. | <img src="chapter6-geoquiz-challenges/screenshots.png?raw=true" width="350"/> |

### CriminalIntent - Office "crime" recorder
A more advanced app with several activities, master/detail views and fragments.

| Chapter  | Description   | Screenshots |
| -------- | ------------- | ----------- |
| 7        | Single-activity app that allows entering a crime title (the crime view). The title is synced with a in-memory model behind the scenes. Not much UI yet, but a lot of the domain classes are created to prepare for the following chapters. | <img src="chapter7-criminalintent/screenshots.png?raw=true" width="170"/> |
| 8        | Chapter 7 app expanded with more fields in the crime view including a date and solved status. | <img src="chapter8-criminalintent/screenshots.png?raw=true" width="170"/> |
| 8(challenges) | Chapter 8 app with a custom crime date format using `DateFormat.format`. | <img src="chapter8-criminalintent-challenges/screenshots.png?raw=true" width="170"/> |
| 9 | Chapter 8 app expanded with a new activity for displaying a list of crimes using a `RecyclerView`. The crime view is not connected to the list view yet. | <img src="chapter9-criminalintent/screenshots.png?raw=true" width="170"/> |

## About
The Android architecture leaves a lot to be desired with regards to testability and separation of concerns. Presenter First is a variant of the Model-View-Presenter (MVP) pattern designed for maximum testability and in particular TDD. While originally intended for GUI-heavy desktop applications it can also be applied to apps. Doing so provides a clear and consistent way to develop any app guided by tests  from a simple single-activity calculator to enterprise apps with dozens of activities, database access, and third-party communications.

### The Code
To illustrate the feasibility of the pattern as much of the code as possible has been developed using TDD. What you see here is the result of mercilessly applying the algorithm for designing Presenter First triads driven by user stories as described in the Agile 2006 Conference paper (Presenter First: Organizing Complex GUI Applications for Test-Driven Development, p. 3).

When given the choice the pedagogical approach has been chosen over elegancy. A good example of this is how all the code is separated into two packages; domain or platform. This is done to emphasize how much of the code is platform agnostic (the domain). When done right Presenter First can help isolate a big part of the domain from the underlying technology and platform. The immediate benefit is testability and the ability to switch it out when needed - for example by using the same code on Android and iOS. In an ideal world the only thing that's switched out between platforms is the view implementations.

It should be noted that the solutions are implemented upon my personal interprertation of the finer details of the Presenter First pattern. It is far from perfect, especially since I hadn't written a single line of Android code before reading the book. **Comments, suggestions and pull-requests are more than welcome. So if you have any ideas don't hesitate to share!**

### Third-Party Libraries
While there are many great libraries for Android development which no-doubt should be used in real-life projects, I have chosen not to follow that path here. I want to keep the code as close as possible to the book to avoid confusion. Furthermore, keeping the code close to vanilla Android reaches the broadest audience.

With that said, due to nature of Presenter First a lot of event-based communication is neccesary both within and outside the MVP triads. In Java 7 and below this means creating dozens of anonymous inner classes and passing them around which quickly becomes a mess. [Retrolambda][retrolambda] is employed to allow the usage of Java 8 lambas in Java 7 and below. This cleans up the event code considerably.

[retrolambda]: https://github.com/orfjackal/retrolambda