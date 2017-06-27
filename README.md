# NDock

NDock is a simple library which allows you to add swipe dock view for android.

![Alt text](./demo.gif?raw=true "Demo")

Basic features:

 * Pop dock up
 * Swipe down to close
 * Customizable RelativeLayout base

 Installation:
 * Download ndock-release.aar and drop it to your libs folder
 * In app build.graddle include lib source:
 ```java
 allprojects {
   repositories {
      jcenter()
      flatDir {
        dirs 'libs'
      }
   }
}
});
```
* Add compile path to your build.graddle:
 ```java
compile(name:'ndock-release', ext:'aar')
```
 * Done!

 Usage:
 * Initialise NDock object and pass root ViewGroup to install method:
 ```java
         RelativeLayout main = (RelativeLayout) findViewById(R.id.main_layout);
         NDock dock = new NDock(getApplicationContext());
         dock.install(main);
 ```
* Swipe down to close dock. To make it visible again:
 ```java
         dock.pop();
 ```
 * To customize dock (CalendarView is defaults) get panel layout(RelativeLayout) and add your own views:
  ```java
          dock.getPanel().addView(...);
  ```
ToDo:

 * Put project to jcenter
 * Add more gestures
 * Check compatibility with other layouts
