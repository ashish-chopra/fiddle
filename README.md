Fiddle
==============

I have a pool of friends who keep visiting around the globe all around the year!

Fiddle is a web based app to pin their visited locations and share their memorable experiences at one place. It is a work in progress application, and many more features have been thought to introduce into this app.

![alt text](https://github.com/ashish-chopra/fiddle/blob/master/src/main/webapp/images/screenshot)


Current Release
===================
`master` branch is the currently active development branch of the code base. When a stable version is released, it is released in its own branch.

Releases Information is found below:

| Version | Release  |
| ------- | -------- |
| 1.0     | Vanilla Release |
| 2.0     | Struts-1.3 + ant based release |

How to setup?
===============
In order to setup the code at your development environment, you must have `Git` and `Maven` installed. And then follow the steps in the order given below:

   1. Clone the project using command git clone <project-url>.
   2. browse to the folder `fiddle` via cmd/terminal run `mvn eclipse:eclipse`. This will create the eclipse boilerplate project structure for this project.
   3. Open your Eclipse, go to File >  Import > Existing Maven projects > fiddle. And you are good to go.
   

Upcoming Features
===================
   1. Converting the project into to Struts 2 framework and then into REST based backend and seperate front-end architecture.
   2. Feature to pin new locations using Fiddle UI is required. Currently the entry is added into the database which is rendered on the screen upon refresh!! This was the agile approach.
   2. Provide a toggle on the screen to toggle the pins as per the visitor. For e.g. if i want to see Ashish's visited pins, then only those will be visible. For this, i have planned a new approach of clustering algorithms in Machine Learning. The application learns from the data and cluster the posts based on identified visitors in the database. No hard code programming will be given to the computer!
   3. Gradually rolling out an android app based on Material Design principle given in Google IO 2014.


Credits
============
Well this app was a joint effort. Thank you Naseem for completing it. I was involved in design and architecture part.

Although, I am artistically challenged. That's why i borrowed icons and images from internet. I thanks Google for creating beautiful icons and images for people with special needs like me!

All credits for graphics to Google!


Licenses
=============
Well, I uploaded this not to share with public, but to keep it safe here.<br/>
So that after i die, it will be available in historic records of Internet atleast!

MIT Licensed.
