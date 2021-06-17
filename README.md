# overengineered-flappy-bird-clone
3rd year 2nd term object oriented software engineering course project

### Wait, I can explain!

Flappy Bird implementation using Abstract Factory, Facade and State design patterns and [Jaylib](https://github.com/electronstudio/jaylib)(A [Raylib](https://www.raylib.com/) binding to Java)

### How to run this game?

Files are Apache NetBeans project files. I don't have experience with Maven. Probably I did the dependency stuff completely wrong. However only external dependency, which is Jaylib is included as a .jar file. If you are going to use NetBeans, Eclipse or IntelliJ IDEA just put my source code within related directories and add **_jaylib.jar_** as an external dependency.

### Unit Test

I tried to write unit tests but this library doesn't support some cases. Test will fail time to time. I think it is because of System.exit(). I don't know how to fix it. Delete if problems occur.

### Assets
  - [Flappy Beans](https://opengameart.org/content/flappy-beans)
  - [SFX created with sfxr](https://www.drpetter.se/project_sfxr.html)
