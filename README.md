# Overview
The application for displaying baking recipes from fixed URL.

## Characteristic

This app is build with the MVVM architecture. Its main goal is to present separate layouts
for different devices - tablet and phones with the use of common master-detail panel. 

Libraries used:
* [Dagger](https://google.github.io/dagger/)
* [RxJava](https://github.com/ReactiveX/RxJava) & [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [DataBinding](https://developer.android.com/topic/libraries/data-binding/index.html)
* [Retrofit](http://square.github.io/retrofit/)
* [Moshi](https://github.com/square/moshi)
* [OkHttp](http://square.github.io/okhttp/)
* [Parceler](https://github.com/johncarl81/parceler)
* [Picasso](http://square.github.io/picasso/)
* [Mockito](http://site.mockito.org/) (will be used in unit tests)
* [ExoPlayer](http://google.github.io/ExoPlayer/)

In-app features:
* `RecyclerView` list / grid for displaying recipes and steps
* Hitting fixed URL (with `Retrofit`) to retrieve data in form of JSON file
* Supporting different screen sizes with the use of `Fragment`s (phones / tablets)
* Video playback from URL source (achieved with `ExoPlayer`)
