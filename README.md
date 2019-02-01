[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
# Simple Banner
A simple library to display a simple banner within your app

<img src="demo/demo.gif" height="500" alt="Gif Showing demo of the simple banner library in action"/>

### Installing

Add this to your `build.gradle` file

TODO

### Usage

Inside your `Application` Class

```
   SimpleBanner.init(this)
```

Or you can predefine the content 
```
   SimpleBanner.init(this, Color.GREEN, "Build #${BuildConfig.VERSION_CODE}, BuildType ${BuildConfig.BUILD_TYPE}")
```

Either method above can be used but updateContent will allow you to update content after the library is initialized
```
   SimpleBanner.updateContent(Color.GREEN, "Build #${BuildConfig.VERSION_CODE}, BuildType ${BuildConfig.BUILD_TYPE}")
```




